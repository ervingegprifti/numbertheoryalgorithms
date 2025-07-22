package com.gegprifti.android.numbertheoryalgorithms.common;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;


public final class PopupDocumentation {
    private final static String TAG = "PopupDocumentation";

    public final static String CALCULATOR_PDF = "documentation/Calculator.pdf";
    public final static String QUADRATIC_FORM_PDF = "documentation/SimpleQuadraticForm.pdf";
    public final static String EUCLIDEAN_ALGORITHM_PDF = "documentation/EuclideanAlgorithm.pdf";
    public final static String EXTENDED_EUCLIDEAN_ALGORITHM_PDF = "documentation/ExtendedEuclideanAlgorithm.pdf";
    public final static String LINEAR_CONGRUENCE_IN_ONE_VARIABLE_PDF = "documentation/LinearCongruenceInOneVariable.pdf";
    public final static String LINEAR_CONGRUENCE_IN_TWO_VARIABLES_PDF = "documentation/LinearCongruenceInTwoVariables.pdf";
    public final static String LINEAR_DIOPHANTINE_EQUATION_IN_TWO_VARIABLES_PDF = "documentation/LinearDiophantineEquationInTwoVariables.pdf";
    public final static String TONELLI_SHANKS_ALGORITHM_PDF = "documentation/TonelliShanksAlgorithm.pdf";
    public final static String MOD_FACTORS_PDF = "documentation/ModFactors.pdf";
    public final static String PRIMES_LIST_PDF = "documentation/PrimesList.pdf";

    private final FragmentActivity fragmentActivity;
    private final Context context;
    private final String pdfFilePath;
    private PopupWindow popupWindow;

    public PopupDocumentation(final FragmentActivity fragmentActivity, final Context context, String pdfFilePath) {
        this.fragmentActivity = fragmentActivity;
        this.context = context;
        this.pdfFilePath = pdfFilePath;
    }

    public void Show() {
        try {
            LayoutInflater layoutInflater = (LayoutInflater)fragmentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Inflate the view from a predefined XML layout
            @SuppressLint("InflateParams") View viewDocumentationDialog = layoutInflater.inflate(R.layout.popup_documentation, null, false);
            PDFView pdfViewDocumentation = viewDocumentationDialog.findViewById(R.id.PDFViewDocumentation);
            TextView textViewDocumentationPdfProblem = viewDocumentationDialog.findViewById(R.id.TextViewDocumentationPdfProblem);
            TextView textViewDocumentationDismiss = viewDocumentationDialog.findViewById(R.id.TextViewDocumentationDismiss);

            if(pdfFilePath == null || pdfFilePath.isEmpty()) {
                // Display the pdf problem
                textViewDocumentationPdfProblem.setVisibility(View.VISIBLE);
            } else {
                textViewDocumentationPdfProblem.setVisibility(View.GONE);

                // To load pdf from assets.
                // In Assets folder place the calculator.pdf file
                // pdfViewInfo.fromAsset("calculator.pdf").load();
                pdfViewDocumentation.fromAsset(pdfFilePath)
                        .onError(new OnErrorListener() {
                            @Override
                            public void onError(Throwable t) {
                                textViewDocumentationPdfProblem.setVisibility(View.VISIBLE);
                                String message = context.getString(R.string.documentation_problem, System.getProperty("line.separator") + t.toString());
                                textViewDocumentationPdfProblem.setText(message);
                            }
                        })
                        .enableAntialiasing(true)
                        .onTap(new OnTapListener() {
                            @Override
                            public boolean onTap(MotionEvent e) {
                                if (popupWindow != null) {
                                    Helper.setFullScreenImmersive(fragmentActivity);
                                }
                                return false;
                            }
                        })
                        .load();
            }

            // Create the PopupWindow to fill the entire screen
            popupWindow = new PopupWindow(viewDocumentationDialog, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true); // true for focusable initially, can be adjusted

            // Set initial focus and touchability.
            // Setting focusable to false initially can sometimes allow underlying views to receive touches
            // but for a splash screen, you usually want it to capture all touches.
            // Setting it to true then updating is a common pattern to ensure it takes focus.
            popupWindow.setFocusable(false);
            popupWindow.setOutsideTouchable(true);
            popupWindow.update();

            // Show the popup window at the center of the screen
            popupWindow.showAtLocation(viewDocumentationDialog, Gravity.CENTER, 0, 0);

            // Apply full-screen immersive mode to the Activity's window.
            // The PopupWindow will then naturally occupy this full-screen space.
            Helper.setFullScreenImmersive(fragmentActivity);

            // Make the popup window focusable after showing it, ensuring it handles input.
            popupWindow.setFocusable(true);
            popupWindow.update();

            // Events
            textViewDocumentationDismiss.setOnClickListener(view -> popupWindow.dismiss());

            // Add a listener for when the popup window is dismissed.
            // This is crucial to revert the Activity's window from full-screen mode.
            popupWindow.setOnDismissListener(() -> {
                if (fragmentActivity.getWindow() != null) {
                    Helper.exitFullScreen(fragmentActivity.getWindow());
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

}