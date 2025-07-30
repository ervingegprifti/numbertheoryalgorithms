package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.app.Dialog;
import android.os.Build;
import android.os.ext.SdkExtensions;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.pdf.viewer.fragment.PdfViewerFragment;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;


public class DialogFragmentPdfViewer extends DialogFragment {
    private static final String ARG_PDF_FILE_PATH = "pdf_file_path";
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
    private String pdfAssetPath;


    // Use a factory method to pass the PDF path safely
    public static DialogFragmentPdfViewer newInstance(String pdfAssetPath) {
        DialogFragmentPdfViewer fragment = new DialogFragmentPdfViewer();
        Bundle args = new Bundle();
        args.putString(ARG_PDF_FILE_PATH, pdfAssetPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make this dialog full screen.
        setStyle(DialogFragment.STYLE_NORMAL, R.style.style_full_screen_dialog);
        if (getArguments() != null) {
            pdfAssetPath = getArguments().getString(ARG_PDF_FILE_PATH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.popup_documentation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textViewDocumentationDismiss = view.findViewById(R.id.TextViewDocumentationDismiss);
        TextView textViewDocumentationPdfProblem = view.findViewById(R.id.TextViewDocumentationPdfProblem);
        View pdfContainer = view.findViewById(R.id.pdfViewerFragmentContainer);

        textViewDocumentationDismiss.setOnClickListener(v -> dismiss());

        if (pdfAssetPath == null || pdfAssetPath.isEmpty()) {
            pdfContainer.setVisibility(View.GONE);
            textViewDocumentationPdfProblem.setVisibility(View.VISIBLE);
            return;
        }

        if (SdkExtensions.getExtensionVersion(Build.VERSION_CODES.S) >= 13) {
            try {
                File pdfFile = copyAssetToCache(requireContext(), pdfAssetPath);
                Uri pdfUri = Uri.fromFile(pdfFile);
                PdfViewerFragment pdfFragment = new PdfViewerFragment();
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.pdfViewerFragmentContainer, pdfFragment)
                        .commit();
                view.post(() -> {
                    pdfFragment.setDocumentUri(pdfUri);
                });
            } catch (IOException e) {
                pdfContainer.setVisibility(View.GONE);
                textViewDocumentationPdfProblem.setVisibility(View.VISIBLE);
                String message = getString(R.string.documentation_problem, System.lineSeparator() + e.getMessage());
                textViewDocumentationPdfProblem.setText(message);
            }
        } else {
            pdfContainer.setVisibility(View.GONE);
            textViewDocumentationPdfProblem.setVisibility(View.VISIBLE);
            String message = getString(R.string.documentation_problem, System.lineSeparator() + R.string.pdf_viewer_not_supported);
            textViewDocumentationPdfProblem.setText(message);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Make the dialog full-screen
        Dialog dialog = getDialog();
        if (dialog != null) {
            UIHelper.setFullScreenImmersive(dialog);
            UIHelper.setEdgeToEdgeFullscreen(dialog);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Dialog dialog = getDialog();
        if (dialog != null) {
            UIHelper.exitFullScreen(dialog);
        }
    }

    // Helper method to copy a file from assets to a temporary cache file
    private File copyAssetToCache(Context context, String assetName) throws IOException {
        File cacheFile = new File(context.getCacheDir(), assetName.substring(assetName.lastIndexOf('/') + 1));
        if (cacheFile.exists()) {
            // Avoid re-copying if it's already there.
            return cacheFile;
        }
        try (InputStream inputStream = context.getAssets().open(assetName);
             OutputStream outputStream = Files.newOutputStream(cacheFile.toPath())) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
        }
        return cacheFile;
    }
}