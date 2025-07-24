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
import com.gegprifti.android.numbertheoryalgorithms.common.Helper;


/**
 * Manages a background task with an optional progress UI.
 * This class is designed to be instantiated for each operation.
 */
public final class ProgressManager {
    private final static String TAG = "ProgressManager";

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
                algPrm.getCallback().callbackResult(algPrm.getAlgorithmName(), null, AlgorithmStatus.CANCELED);
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
                algPrm.getCallback().callbackResult(algPrm.getAlgorithmName(), result, AlgorithmStatus.FINISHED);
            } finally {
                // Dismiss the progress popup window after the long operation is complete.
                dismiss();
            }
        });
    }


    private void onError(AlgorithmParameters algPrm) {
        if (algPrm != null && algPrm.getCallback() != null) {
            algPrm.getCallback().callbackResult(algPrm.getAlgorithmName(), null, AlgorithmStatus.ERROR);
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
                textViewProgressCancel.setText(R.string.writing_results);
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
            // TODO. case CALCULATOR_ADDITION : return Algorithms.Addition(algPrm);
            // TODO. case CALCULATOR_SUBTRACTION : return Algorithms.Subtraction(algPrm);
            // TODO. case CALCULATOR_MULTIPLICATION : return Algorithms.Multiplication(algPrm);
            // TODO. case CALCULATOR_DIVISION : return Algorithms.Division(algPrm);
            // TODO. case CALCULATOR_POWER : return Algorithms.Power(algPrm);
            // TODO. case CALCULATOR_ROOT : return Algorithms.Root(algPrm);
            // TODO. case CALCULATOR_GCD : return Algorithms.Gcd(algPrm);
            // TODO.  case CALCULATOR_LCM : return Algorithms.Lcm(algPrm);
            // TODO. case CALCULATOR_MOD : return Algorithms.Mod(algPrm);
            // TODO. case CALCULATOR_MOD_INVERSE : return Algorithms.ModInverse(algPrm);
            // TODO. case CALCULATOR_IS_PROBABLE_PRIME : return Algorithms.IsProbablePrime(algPrm);
            // TODO. case CALCULATOR_EULER_PHI : return Algorithms.EulerPhi(algPrm);
            case CALCULATOR_FACTORIAL : return new Factorial(algPrm).calculate();
            // TODO. case CALCULATOR_NEXT_PROBABLE_PRIME: return Algorithms.NextProbablePrime(algPrm);
            // TODO. case CALCULATOR_NEXT_PROBABLE_TWIN_PRIME_PAIR : return Algorithms.NextProbableTwinPrimePair(algPrm);
            // TODO. case QUADRATIC_FORM: return Algorithms.QuadraticFormRun(algPrm);
            // TODO. case QUADRATIC_FORM_1: return Algorithms.QuadraticFormRun1(algPrm);
            // TODO. case QUADRATIC_FORM_2: return Algorithms.QuadraticFormRun2(algPrm);
            // TODO. case EUCLIDEAN_ALGORITHM: return Algorithms.EuclideanAlgorithm(algPrm);
            // TODO. case EXTENDED_EUCLIDEAN_ALGORITHM : return Algorithms.ExtendedEuclideanAlgorithm(algPrm);
            // TODO. case LINEAR_CONGRUENCE_IN_ONE_VARIABLE : return Algorithms.LinearCongruenceInOneVariable(algPrm);
            // TODO. case LINEAR_CONGRUENCE_IN_TWO_VARIABLES : return Algorithms.LinearCongruenceInTwoVariables(algPrm);
            // TODO. case LINEAR_DIOPHANTINE_EQUATION_IN_TWO_VARIABLES : return Algorithms.LinearDiophantineEquation(algPrm);
            // TODO. case TONELLI_SHANKS_ALGORITHM : return Algorithms.TonelliShanksAlgorithm(algPrm);
            // TODO. case MOD_FACTORS : return Algorithms.ModFactors(algPrm);
            // TODO. case MOD_FACTORS_COUNT : return Algorithms.ModFactorsCount(algPrm);
            // TODO. case PRIMES_LIST : return Algorithms.PrimesList(algPrm);
            default: return null;
        }
    }
}