package com.gegprifti.android.numbertheoryalgorithms;


import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import android.os.Handler;
import android.os.Looper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.*;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.Addition;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.Division;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.EulerPhi;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.Factorial;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.Gcd;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.IsProbablePrime;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.Lcm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.Mod;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.ModInverse;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.Multiplication;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.NextProbablePrime;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.NextProbableTwinPrimePair;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.Power;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.Root;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.Subtraction;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.common.Helper;


/**
 * Manages a background task with an optional progress UI.
 * This class is designed to be instantiated for each operation.
 */
public final class ProgressManager {
    private final static String TAG = ProgressManager.class.getSimpleName();

    private PopupWindow popupWindow;
    private Future<?> runningTask;
    private ExecutorService executor;
    private final Activity activity;
    private final Handler handler;


    /**
     * Constructs a new ProgressManager.
     *
     * @param activity The activity
     */
    public ProgressManager(Activity activity) {
        this.activity = activity;
        this.handler = new Handler(Looper.getMainLooper());
    }


    /**
     * Starts the background algorithm execution.
     *
     * @param container             The container for the progress view, can be null.
     * @param algPrm                The parameters for the algorithm.
     * @param displayProgressDialog True to display the progress popup, false to run silently.
     */
    public void startWork(ViewGroup container, final AlgorithmParameters algPrm, boolean displayProgressDialog) {
        try {
            // Ensure any previous task on this instance is cancelled before starting a new one.
            if (runningTask != null && !runningTask.isDone()) {
                runningTask.cancel(true);
            }

            executor = Executors.newSingleThreadExecutor();

            // --- Start Background Task ---
            runningTask = executor.submit(() -> {
                try {
                    Object result = work(algPrm);
                    handler.post(() -> onPostExecute(algPrm, result));
                } catch (Exception ex) {
                    if (ex instanceof InterruptedException) {
                        Thread.currentThread().interrupt();
                        Log.w(TAG, "Task was cancelled via interruption.");
                        // The cancellation callback is handled in the dismiss listener or cancel() method.
                    } else {
                        Log.e(TAG, "An error occurred during background work.", ex);
                        handler.post(() -> onError(algPrm));
                    }
                } finally {
                    if (executor != null) {
                        executor.shutdown();
                    }
                }
            });

            if (!displayProgressDialog) {
                return;
            }

            // --- Setup Progress UI ---
            setupPopupWindow(container, algPrm);

        } catch (Exception ex) {
            Log.e(TAG, "An error occurred in startWork.", ex);
        }
    }


    /**
     * Sets up and displays the PopupWindow.
     */
    private void setupPopupWindow(ViewGroup container, final AlgorithmParameters algPrm) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View viewProgressDialog = layoutInflater.inflate(R.layout.popup_progress, container, false);

        Chronometer chronometerProgress = viewProgressDialog.findViewById(R.id.ChronometerProgress);
        TextView textViewProgressCancel = viewProgressDialog.findViewById(R.id.TextViewProgressCancel);

        chronometerProgress.start();

        // Create the PopupWindow to fill the entire screen
        popupWindow = new PopupWindow(viewProgressDialog, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        if (runningTask != null && !runningTask.isDone()) {
            Helper.setFullScreenImmersive(activity);
            popupWindow.showAtLocation(viewProgressDialog, Gravity.CENTER, 0, 0);
        }

        textViewProgressCancel.setOnClickListener(view -> dismiss());

        popupWindow.setOnDismissListener(() -> {
            onDismiss(algPrm);
            Helper.exitFullScreen(activity);
        });
    }


    /**
     * Cancels the running task and dismisses the dialog.
     */
    public void cancel() {
        dismiss(); // Dismissing the window triggers the cancellation logic in onDismiss.
    }


    private void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }


    private void onDismiss(AlgorithmParameters algPrm) {
        if (runningTask != null && !runningTask.isDone()) {
            runningTask.cancel(true);
            if (algPrm != null && algPrm.getCallback() != null) {
                algPrm.getCallback().callbackResult(algPrm.getAlgorithmName(), null, ProgressStatus.CANCELED);
            }
        }
        // Clean up to prevent leaks
        popupWindow = null;
    }


    private void onPostExecute(AlgorithmParameters algPrm, Object result) {
        if (algPrm == null || algPrm.getCallback() == null) {
            dismiss();
            return;
        }

        // Update the progress popup window UI to show the "Writing results..." message.
        onPostExecuteUpdateUI();

        // Post the long-running UI work to the handler.
        // This allows the progress popup window UI update to render *before* the UI thread gets blocked.
        handler.post(() -> {
            try {
                // Write result to the main UI.
                // Calling setText on EditText is a time consuming process when string to set is huge.
                algPrm.getCallback().callbackResult(algPrm.getAlgorithmName(), result, ProgressStatus.FINISHED);
            } finally {
                // Dismiss the progress popup window after the calculate + write results to UI is complete.
                dismiss();
            }
        });
    }


    private void onError(AlgorithmParameters algPrm) {
        if (algPrm != null && algPrm.getCallback() != null) {
            algPrm.getCallback().callbackResult(algPrm.getAlgorithmName(), null, ProgressStatus.ERROR);
        }
        dismiss();
    }


    /**
     * Updates the progress UI to indicate that the results are being processed.
     */
    private void onPostExecuteUpdateUI() {
        if (popupWindow != null && popupWindow.isShowing()) {
            View contentView = popupWindow.getContentView();
            if (contentView == null) {
                return;
            }

            TextView textViewProgressCancel = contentView.findViewById(R.id.TextViewProgressCancel);
            if (textViewProgressCancel != null) {
                textViewProgressCancel.setEnabled(false);
                textViewProgressCancel.setText(R.string.progress_dialog_writing_results);
                textViewProgressCancel.setBackground(null);
            }
        }
    }


    /**
     * Executes the appropriate algorithm based on the algorithm parameters.
     */
    private Object work(AlgorithmParameters algPrm) throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }

        switch (algPrm.getAlgorithmName()) {
            case CALCULATOR_ADDITION : return new Addition(algPrm).calculate();
            case CALCULATOR_SUBTRACTION : return new Subtraction(algPrm).calculate();
            case CALCULATOR_MULTIPLICATION : return new Multiplication(algPrm).calculate();
            case CALCULATOR_DIVISION : return new Division(algPrm).calculate();
            case CALCULATOR_POWER : return new Power(algPrm).calculate();
            case CALCULATOR_ROOT : return new Root(algPrm).calculate();
            case CALCULATOR_GCD : return new Gcd(algPrm).calculate();
            case CALCULATOR_LCM : return new Lcm(algPrm).calculate();
            case CALCULATOR_MOD : return new Mod(algPrm).calculate();
            case CALCULATOR_MOD_INVERSE : return new ModInverse(algPrm).calculate();
            case CALCULATOR_IS_PROBABLE_PRIME : return new IsProbablePrime(algPrm).calculate();
            case CALCULATOR_EULER_PHI : return new EulerPhi(algPrm).calculate();
            case CALCULATOR_FACTORIAL : return new Factorial(algPrm).calculate();
            case CALCULATOR_NEXT_PROBABLE_PRIME: return new NextProbablePrime(algPrm).calculate();
            case CALCULATOR_NEXT_PROBABLE_TWIN_PRIME_PAIR : return new NextProbableTwinPrimePair(algPrm).calculate();
            case QUADRATIC_FORM: return new QuadraticFormRun(algPrm).calculate();
            case QUADRATIC_FORM_1: return new QuadraticFormRun1(algPrm).calculate();
            case QUADRATIC_FORM_2: return new QuadraticFormRun2(algPrm).calculate();
            case EUCLIDEAN_ALGORITHM: return new EuclideanAlgorithm(algPrm).calculate();
            case EXTENDED_EUCLIDEAN_ALGORITHM : return new ExtendedEuclideanAlgorithm(algPrm).calculate();
            case LINEAR_CONGRUENCE_IN_ONE_VARIABLE : return new LinearCongruenceInOneVariable(algPrm).calculate();
            case LINEAR_CONGRUENCE_IN_TWO_VARIABLES : return new LinearCongruenceInTwoVariables(algPrm).calculate();
            case LINEAR_DIOPHANTINE_EQUATION_IN_TWO_VARIABLES : return new LinearDiophantineEquation(algPrm).calculate();
            case TONELLI_SHANKS_ALGORITHM : return new TonelliShanksAlgorithm(algPrm).calculate();
            case MOD_FACTORS : return new ModFactors(algPrm).calculate();
            case MOD_FACTORS_COUNT : return new ModFactorsCount(algPrm).calculate();
            case PRIMES_LIST : return new PrimesList(algPrm).calculate();
            default: return null;
        }
    }
}