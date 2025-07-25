package com.gegprifti.android.numbertheoryalgorithms;


import android.content.Context;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;


final class ProgressDialog {
    private final static String TAG = ProgressDialog.class.getSimpleName();

    static PopupWindow popupWindow;
    private static Run run;

    static void StartWork(Context context, ViewGroup container, final AlgorithmParameters algPrm, boolean displayProgressDialog) {
        try {
            // Log some things here.
            String startWorkLog = TAG + " -> " + "StartWork" + " -> " +
                    "container: " + ((container == null) ? "null" : "not null") + "; " +
                    "displayProgressDialog: " + displayProgressDialog + "; " +
                    "AlgorithmParameters: " + algPrm.toString();

            // Source: http://androidexample.com/Thread_With_Handlers_-_Android_Example/index.php?view=article_discription&aid=58&aaid=83
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (layoutInflater == null) {
                return;
            }

            // Run the background process async task.
            run = new Run();
            run.execute(algPrm);

            // This is to prevent the Non-fatal Exception: android.view.WindowManager$BadTokenException: Unable to add window -- token null is not valid; is your activity running?
            // If this is the first time the user is using a particular algorithm then do not display the popup window.
            if (!displayProgressDialog) {
                return;
            }

            // Display the progress dialog.
            View viewProgressDialog;
            if (container == null) {
                // Inflate the view from a predefined XML layout
                viewProgressDialog = layoutInflater.inflate(R.layout.popup_progress, null, false);
                // Helper.CustomToastLight(context, "container is null");
            } else {
                // Inflate the view from a predefined XML layout
                viewProgressDialog = layoutInflater.inflate(R.layout.popup_progress, container, false);
            }
            Chronometer chronometerProgress = viewProgressDialog.findViewById(R.id.ChronometerProgress);
            ProgressBar progressBarProgress = viewProgressDialog.findViewById(R.id.ProgressBarSpin);
            TextView textViewProgressCancel = viewProgressDialog.findViewById(R.id.TextViewProgressCancel);

            // Start the timer
            chronometerProgress.start();
            // Set the progressbar color
            progressBarProgress.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.colorGeneralBg), PorterDuff.Mode.MULTIPLY); // MULTIPLY, SRC_ATOP

            // The window
            popupWindow = new PopupWindow(viewProgressDialog, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true); // Full screen.

            // Events
            textViewProgressCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Dismiss the progress dialog
                    if (popupWindow != null) {
                        popupWindow.dismiss();
                    }
                }
            });
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    if(run != null && run.getStatus() != AsyncTask.Status.FINISHED) {
                        // Async task is pending or running
                        if(!run.isCancelled()) {
                            run.cancel(true);
                        }
                    }
                }
            });

            // Show only if the run is not finished.
            if(run != null && run.getStatus() != AsyncTask.Status.FINISHED) {
                popupWindow.showAtLocation(viewProgressDialog, Gravity.CENTER, 0, 0);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    // Source: https://www.youtube.com/watch?v=-Ld1IoOF_uk
    // Source: https://developer.android.com/reference/android/os/AsyncTask.html
    // The three types used by an asynchronous task are the following:
    // 1. Params, the type of the parameters sent to the task upon execution.
    // 2. Progress, the type of the progress units published during the background computation.
    // 3. Result, the type of the result of the background computation.
    // Not all types are always used by an asynchronous task. To mark a type as unused, simply use the type Void:
    static class Run extends AsyncTask<AlgorithmParameters, Integer, Object> {
        private AlgorithmParameters algPrm; // Remove later and use it only in doInBackground

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Start progressbar
        }

        @Override
        protected Object doInBackground(AlgorithmParameters... parameters) { // protected Object doInBackground(AlgorithmParameters... parameters)
            Object result = null;
            try {
                this.algPrm = parameters[0];

                switch (this.algPrm.getAlgorithmName()) {
                    //case CALCULATOR_ADDITION :
                    //    result = Algorithms.Addition(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_SUBTRACTION :
                    //    result = Algorithms.Subtraction(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_MULTIPLICATION :
                    //    result = Algorithms.Multiplication(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_DIVISION :
                    //    result = Algorithms.Division(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_POWER :
                    //    result = Algorithms.Power(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_ROOT :
                    //    result = Algorithms.Root(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_GCD :
                    //    result = Algorithms.Gcd(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_LCM :
                    //    result = Algorithms.Lcm(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_MOD :
                    //    result = Algorithms.Mod(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_MOD_INVERSE :
                    //    result = Algorithms.ModInverse(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_IS_PROBABLE_PRIME :
                    //    result = Algorithms.IsProbablePrime(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_EULER_PHI :
                    //    result = Algorithms.EulerPhi(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_FACTORIAL :
                    //    result = Algorithms.Factorial(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_NEXT_PROBABLE_PRIME:
                    //    result = Algorithms.NextProbablePrime(this, this.algPrm);
                    //    break;
                    //case CALCULATOR_NEXT_PROBABLE_TWIN_PRIME_PAIR :
                    //    result = Algorithms.NextProbableTwinPrimePair(this, this.algPrm);
                    //    break;
                    case QUADRATIC_FORM:
                        result = Algorithms.QuadraticFormRun(this, this.algPrm);
                        break;
                    case QUADRATIC_FORM_1:
                        result = Algorithms.QuadraticFormRun1(this, this.algPrm);
                        break;
                    case QUADRATIC_FORM_2:
                        result = Algorithms.QuadraticFormRun2(this, this.algPrm);
                        break;
                    //case EUCLIDEAN_ALGORITHM:
                    //    result = Algorithms.EuclideanAlgorithm(this, this.algPrm);
                    //    break;
                    //case EXTENDED_EUCLIDEAN_ALGORITHM :
                    //    result = Algorithms.ExtendedEuclideanAlgorithm(this, this.algPrm);
                    //    break;
                    //case LINEAR_CONGRUENCE_IN_ONE_VARIABLE :
                    //    result = Algorithms.LinearCongruenceInOneVariable(this, this.algPrm);
                    //    break;
                    //case LINEAR_CONGRUENCE_IN_TWO_VARIABLES :
                    //    result = Algorithms.LinearCongruenceInTwoVariables(this, this.algPrm);
                    //    break;
                    //case LINEAR_DIOPHANTINE_EQUATION_IN_TWO_VARIABLES :
                    //    result = Algorithms.LinearDiophantineEquation(this, this.algPrm);
                    //    break;
                    //case TONELLI_SHANKS_ALGORITHM :
                    //    result = Algorithms.TonelliShanksAlgorithm(this, this.algPrm);
                    //    break;
                    //case MOD_FACTORS :
                    //    result = Algorithms.ModFactors(this, this.algPrm);
                    //    break;
                    //case MOD_FACTORS_COUNT :
                    //    result = Algorithms.ModFactorsCount(this, this.algPrm);
                    //    break;
                    case PRIMES_LIST :
                        result = Algorithms.PrimesList(this, this.algPrm);
                        break;
                    default:
                        break;
                }
            } catch (Exception ex) {
                Log.e(TAG, "" + ex);
            }

            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // TODO. Implement if needed
            // int percent = values[0];
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            try {
                if(this.algPrm != null) {
                    algPrm.getCallback().callbackResult(algPrm.getAlgorithmName(), result, AlgorithmStatus.FINISHED);
                }
                // Dismiss the progress dialog
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            } catch (Exception ex) {
                Log.e(TAG, "" + ex);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            try {
                if(this.algPrm != null) {
                    algPrm.getCallback().callbackResult(algPrm.getAlgorithmName(), null, AlgorithmStatus.CANCELED);
                }
            } catch (Exception ex) {
                Log.e(TAG, "" + ex);
            }
        }
    }
}