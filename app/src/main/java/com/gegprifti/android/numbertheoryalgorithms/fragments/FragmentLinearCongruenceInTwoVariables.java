package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gegprifti.android.numbertheoryalgorithms.fragments.common.InputGroup;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.settings.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;


public class FragmentLinearCongruenceInTwoVariables extends FragmentBase implements Callback {
    private final static String TAG = FragmentLinearCongruenceInTwoVariables.class.getSimpleName();
    // Navigation controls
    TextView textViewLinearCongruenceInTwoVariablesBackToAlgorithms;
    TextView textViewLinearCongruenceInTwoVariablesTitle;
    TextView textViewLinearCongruenceInTwoVariablesDocumentationFile;
    // Cache view state
    boolean isCompactInputView = false;
    // Extended input view
    LinearLayout linearLayoutExtendedInputView;
    TextView textViewLinearCongruenceInTwoVariablesLabelA;
    TextView textViewLinearCongruenceInTwoVariablesLabelElasticA;
    TextView textViewLinearCongruenceInTwoVariablesCopyA;
    TextView textViewLinearCongruenceInTwoVariablesPasteA;
    TextView textViewLinearCongruenceInTwoVariablesClearA;
    EditText editTextLinearCongruenceInTwoVariablesA;
    TextView textViewLinearCongruenceInTwoVariablesLabelB;
    TextView textViewLinearCongruenceInTwoVariablesLabelElasticB;
    TextView textViewLinearCongruenceInTwoVariablesCopyB;
    TextView textViewLinearCongruenceInTwoVariablesPasteB;
    TextView textViewLinearCongruenceInTwoVariablesClearB;
    EditText editTextLinearCongruenceInTwoVariablesB;
    TextView textViewLinearCongruenceInTwoVariablesLabelC;
    TextView textViewLinearCongruenceInTwoVariablesLabelElasticC;
    TextView textViewLinearCongruenceInTwoVariablesCopyC;
    TextView textViewLinearCongruenceInTwoVariablesPasteC;
    TextView textViewLinearCongruenceInTwoVariablesClearC;
    EditText editTextLinearCongruenceInTwoVariablesC;
    TextView textViewLinearCongruenceInTwoVariablesLabelM;
    TextView textViewLinearCongruenceInTwoVariablesLabelElasticM;
    TextView textViewLinearCongruenceInTwoVariablesCopyM;
    TextView textViewLinearCongruenceInTwoVariablesPasteM;
    TextView textViewLinearCongruenceInTwoVariablesClearM;
    EditText editTextLinearCongruenceInTwoVariablesM;
    // Compact input view
    LinearLayout linearLayoutCompactInputView;
    TextView textViewLinearCongruenceInTwoVariablesLabelCompactA;
    TextView textViewLinearCongruenceInTwoVariablesCopyCompactA;
    TextView textViewLinearCongruenceInTwoVariablesPasteCompactA;
    TextView textViewLinearCongruenceInTwoVariablesClearCompactA;
    EditText editTextLinearCongruenceInTwoVariablesCompactA;
    TextView textViewLinearCongruenceInTwoVariablesLabelCompactB;
    TextView textViewLinearCongruenceInTwoVariablesCopyCompactB;
    TextView textViewLinearCongruenceInTwoVariablesPasteCompactB;
    TextView textViewLinearCongruenceInTwoVariablesClearCompactB;
    EditText editTextLinearCongruenceInTwoVariablesCompactB;
    TextView textViewLinearCongruenceInTwoVariablesLabelCompactC;
    TextView textViewLinearCongruenceInTwoVariablesCopyCompactC;
    TextView textViewLinearCongruenceInTwoVariablesPasteCompactC;
    TextView textViewLinearCongruenceInTwoVariablesClearCompactC;
    EditText editTextLinearCongruenceInTwoVariablesCompactC;
    TextView textViewLinearCongruenceInTwoVariablesLabelCompactM;
    TextView textViewLinearCongruenceInTwoVariablesCopyCompactM;
    TextView textViewLinearCongruenceInTwoVariablesPasteCompactM;
    TextView textViewLinearCongruenceInTwoVariablesClearCompactM;
    EditText editTextLinearCongruenceInTwoVariablesCompactM;
    // Run buttons
    Button buttonLinearCongruenceInTwoVariablesRun;
    Button buttonLinearCongruenceInTwoVariablesRunExample1;
    Button buttonLinearCongruenceInTwoVariablesRunExample2;
    Button buttonLinearCongruenceInTwoVariablesRunExample3;
    // Result controls
    TextView textViewLinearCongruenceInTwoVariablesLabelResult;
    TextView textViewLinearCongruenceInTwoVariablesLabelElasticResult;
    TextView textViewLinearCongruenceInTwoVariablesExpandResult;
    TextView textViewLinearCongruenceInTwoVariablesCopyResult;
    TextView textViewLinearCongruenceInTwoVariablesClearResult;
    EditText editTextLinearCongruenceInTwoVariablesResult;
    // Flags to prevent recursive updates
    AtomicBoolean isUpdatingEditTextLinearCongruenceInTwoVariablesA = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextLinearCongruenceInTwoVariablesCompactA = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextLinearCongruenceInTwoVariablesB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextLinearCongruenceInTwoVariablesCompactB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextLinearCongruenceInTwoVariablesC = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextLinearCongruenceInTwoVariablesCompactC = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextLinearCongruenceInTwoVariablesM = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextLinearCongruenceInTwoVariablesCompactM = new AtomicBoolean(false);


    // Define the parent fragment
    private TabFragmentAlgorithms tabFragmentAlgorithms;
    // public TabFragmentAlgorithms getFragmentTabAlgorithms() { return tabFragmentAlgorithms; }
    public void setFragmentTabAlgorithms(TabFragmentAlgorithms tabFragmentAlgorithms) { this.tabFragmentAlgorithms = tabFragmentAlgorithms; }

    // Important
    // All Fragment classes you create must have a public, no-arg constructor.
    // In general, the best practice is to simply never define any constructors at all and rely on Java to generate the default constructor for you.

    //region CREATE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View inflater = null;
        try {
            inflater = layoutInflater.inflate(R.layout.fragment_linear_congruence_in_two_variables, container, false);
            // Navigation controls
            textViewLinearCongruenceInTwoVariablesBackToAlgorithms = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesBackToAlgorithms);
            textViewLinearCongruenceInTwoVariablesTitle = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesTitle);
            textViewLinearCongruenceInTwoVariablesDocumentationFile = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesDocumentationFile);
            // Extended input view
            linearLayoutExtendedInputView = inflater.findViewById(R.id.LinearLayoutExtendedInputView);
            textViewLinearCongruenceInTwoVariablesLabelA = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelA);
            textViewLinearCongruenceInTwoVariablesLabelElasticA = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelElasticA);
            textViewLinearCongruenceInTwoVariablesCopyA = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesCopyA);
            textViewLinearCongruenceInTwoVariablesPasteA = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesPasteA);
            textViewLinearCongruenceInTwoVariablesClearA = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesClearA);
            editTextLinearCongruenceInTwoVariablesA = inflater.findViewById(R.id.EditTextLinearCongruenceInTwoVariablesA);
            textViewLinearCongruenceInTwoVariablesLabelB = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelB);
            textViewLinearCongruenceInTwoVariablesLabelElasticB = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelElasticB);
            textViewLinearCongruenceInTwoVariablesCopyB = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesCopyB);
            textViewLinearCongruenceInTwoVariablesPasteB = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesPasteB);
            textViewLinearCongruenceInTwoVariablesClearB = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesClearB);
            editTextLinearCongruenceInTwoVariablesB = inflater.findViewById(R.id.EditTextLinearCongruenceInTwoVariablesB);
            textViewLinearCongruenceInTwoVariablesLabelC = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelC);
            textViewLinearCongruenceInTwoVariablesLabelElasticC = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelElasticC);
            textViewLinearCongruenceInTwoVariablesCopyC = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesCopyC);
            textViewLinearCongruenceInTwoVariablesPasteC = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesPasteC);
            textViewLinearCongruenceInTwoVariablesClearC = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesClearC);
            editTextLinearCongruenceInTwoVariablesC = inflater.findViewById(R.id.EditTextLinearCongruenceInTwoVariablesC);
            textViewLinearCongruenceInTwoVariablesLabelM = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelM);
            textViewLinearCongruenceInTwoVariablesLabelElasticM = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelElasticM);
            textViewLinearCongruenceInTwoVariablesCopyM = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesCopyM);
            textViewLinearCongruenceInTwoVariablesPasteM = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesPasteM);
            textViewLinearCongruenceInTwoVariablesClearM = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesClearM);
            editTextLinearCongruenceInTwoVariablesM = inflater.findViewById(R.id.EditTextLinearCongruenceInTwoVariablesM);
            // Compact input view
            linearLayoutCompactInputView = inflater.findViewById(R.id.LinearLayoutCompactInputView);
            textViewLinearCongruenceInTwoVariablesLabelCompactA = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelCompactA);
            textViewLinearCongruenceInTwoVariablesCopyCompactA = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesCopyCompactA);
            textViewLinearCongruenceInTwoVariablesPasteCompactA = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesPasteCompactA);
            textViewLinearCongruenceInTwoVariablesClearCompactA = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesClearCompactA);
            editTextLinearCongruenceInTwoVariablesCompactA = inflater.findViewById(R.id.EditTextLinearCongruenceInTwoVariablesCompactA);
            textViewLinearCongruenceInTwoVariablesLabelCompactB = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelCompactB);
            textViewLinearCongruenceInTwoVariablesCopyCompactB = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesCopyCompactB);
            textViewLinearCongruenceInTwoVariablesPasteCompactB = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesPasteCompactB);
            textViewLinearCongruenceInTwoVariablesClearCompactB = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesClearCompactB);
            editTextLinearCongruenceInTwoVariablesCompactB = inflater.findViewById(R.id.EditTextLinearCongruenceInTwoVariablesCompactB);
            textViewLinearCongruenceInTwoVariablesLabelCompactC = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelCompactC);
            textViewLinearCongruenceInTwoVariablesCopyCompactC = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesCopyCompactC);
            textViewLinearCongruenceInTwoVariablesPasteCompactC = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesPasteCompactC);
            textViewLinearCongruenceInTwoVariablesClearCompactC = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesClearCompactC);
            editTextLinearCongruenceInTwoVariablesCompactC = inflater.findViewById(R.id.EditTextLinearCongruenceInTwoVariablesCompactC);
            textViewLinearCongruenceInTwoVariablesLabelCompactM = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelCompactM);
            textViewLinearCongruenceInTwoVariablesCopyCompactM = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesCopyCompactM);
            textViewLinearCongruenceInTwoVariablesPasteCompactM = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesPasteCompactM);
            textViewLinearCongruenceInTwoVariablesClearCompactM = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesClearCompactM);
            editTextLinearCongruenceInTwoVariablesCompactM = inflater.findViewById(R.id.EditTextLinearCongruenceInTwoVariablesCompactM);
            // Run buttons
            buttonLinearCongruenceInTwoVariablesRun = inflater.findViewById(R.id.ButtonLinearCongruenceInTwoVariablesRun);
            buttonLinearCongruenceInTwoVariablesRunExample1 = inflater.findViewById(R.id.ButtonLinearCongruenceInTwoVariablesRunExample1);
            buttonLinearCongruenceInTwoVariablesRunExample2 = inflater.findViewById(R.id.ButtonLinearCongruenceInTwoVariablesRunExample2);
            buttonLinearCongruenceInTwoVariablesRunExample3 = inflater.findViewById(R.id.ButtonLinearCongruenceInTwoVariablesRunExample3);
            // Result controls
            textViewLinearCongruenceInTwoVariablesLabelResult = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelResult);
            textViewLinearCongruenceInTwoVariablesLabelElasticResult = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelElasticResult);
            textViewLinearCongruenceInTwoVariablesExpandResult = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesExpandResult);
            textViewLinearCongruenceInTwoVariablesCopyResult = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesCopyResult);
            textViewLinearCongruenceInTwoVariablesClearResult = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesClearResult);
            editTextLinearCongruenceInTwoVariablesResult = inflater.findViewById(R.id.EditTextLinearCongruenceInTwoVariablesResult);

            // Constrain extended input
            editTextLinearCongruenceInTwoVariablesA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextLinearCongruenceInTwoVariablesB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextLinearCongruenceInTwoVariablesC.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextLinearCongruenceInTwoVariablesM.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            // Constrain compact input
            editTextLinearCongruenceInTwoVariablesCompactA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextLinearCongruenceInTwoVariablesCompactB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextLinearCongruenceInTwoVariablesCompactC.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextLinearCongruenceInTwoVariablesCompactM.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Navigation vents
            textViewLinearCongruenceInTwoVariablesBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });
            textViewLinearCongruenceInTwoVariablesDocumentationFile.setOnClickListener(view -> DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.LINEAR_CONGRUENCE_IN_TWO_VARIABLES_PDF).show(getParentFragmentManager(), "LINEAR_CONGRUENCE_IN_TWO_VARIABLES_PDF"));

            // Extended input events
            editTextLinearCongruenceInTwoVariablesA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextLinearCongruenceInTwoVariablesA.get()) return; // editTextLinearCongruenceInTwoVariablesA is locked
                    // Other work
                    String linearCongruenceInTwoVariablesLabelA = "a" + UIHelper.getNrOfDigits(s.toString());
                    textViewLinearCongruenceInTwoVariablesLabelA.setText(linearCongruenceInTwoVariablesLabelA);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextLinearCongruenceInTwoVariablesCompactA
                    isUpdatingEditTextLinearCongruenceInTwoVariablesCompactA.set(true); // Lock editTextLinearCongruenceInTwoVariablesCompactA
                    try {
                        editTextLinearCongruenceInTwoVariablesCompactA.setText(s.toString());
                        // editTextLinearCongruenceInTwoVariablesCompactA.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextLinearCongruenceInTwoVariablesCompactA.set(false); // Unlock editTextLinearCongruenceInTwoVariablesCompactA
                    }
                }
            });
            editTextLinearCongruenceInTwoVariablesB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextLinearCongruenceInTwoVariablesB.get()) return; // editTextLinearCongruenceInTwoVariablesB is locked
                    // Other work
                    String linearCongruenceInTwoVariablesLabelB = "b" + UIHelper.getNrOfDigits(s.toString());
                    textViewLinearCongruenceInTwoVariablesLabelB.setText(linearCongruenceInTwoVariablesLabelB);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextLinearCongruenceInTwoVariablesCompactB
                    isUpdatingEditTextLinearCongruenceInTwoVariablesCompactB.set(true); // Lock editTextLinearCongruenceInTwoVariablesCompactB
                    try {
                        editTextLinearCongruenceInTwoVariablesCompactB.setText(s.toString());
                        // editTextLinearCongruenceInTwoVariablesCompactB.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextLinearCongruenceInTwoVariablesCompactB.set(false); // Unlock editTextLinearCongruenceInTwoVariablesCompactB
                    }
                }
            });
            editTextLinearCongruenceInTwoVariablesC.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextLinearCongruenceInTwoVariablesC.get()) return; // editTextLinearCongruenceInTwoVariablesC is locked
                    // Other work
                    String linearCongruenceInTwoVariablesLabelB = "c" + UIHelper.getNrOfDigits(s.toString());
                    textViewLinearCongruenceInTwoVariablesLabelC.setText(linearCongruenceInTwoVariablesLabelB);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextLinearCongruenceInTwoVariablesCompactC
                    isUpdatingEditTextLinearCongruenceInTwoVariablesCompactC.set(true); // Lock editTextLinearCongruenceInTwoVariablesCompactC
                    try {
                        editTextLinearCongruenceInTwoVariablesCompactC.setText(s.toString());
                        // editTextLinearCongruenceInTwoVariablesCompactC.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextLinearCongruenceInTwoVariablesCompactC.set(false); // Unlock editTextLinearCongruenceInTwoVariablesCompactC
                    }
                }
            });
            editTextLinearCongruenceInTwoVariablesM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextLinearCongruenceInTwoVariablesM.get()) return; // editTextLinearCongruenceInTwoVariablesM is locked
                    // Other work
                    String linearCongruenceInTwoVariablesLabelM = "m" + UIHelper.getNrOfDigits(s.toString());
                    textViewLinearCongruenceInTwoVariablesLabelM.setText(linearCongruenceInTwoVariablesLabelM);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextLinearCongruenceInTwoVariablesCompactM
                    isUpdatingEditTextLinearCongruenceInTwoVariablesCompactM.set(true); // Lock editTextLinearCongruenceInTwoVariablesCompactM
                    try {
                        editTextLinearCongruenceInTwoVariablesCompactM.setText(s.toString());
                        // editTextLinearCongruenceInTwoVariablesCompactM.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextLinearCongruenceInTwoVariablesCompactM.set(false); // Unlock editTextLinearCongruenceInTwoVariablesCompactM
                    }
                }
            });

            // Compact input events
            editTextLinearCongruenceInTwoVariablesCompactA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextLinearCongruenceInTwoVariablesCompactA.get()) return; // editTextLinearCongruenceInTwoVariablesCompactA is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextLinearCongruenceInTwoVariablesA
                    isUpdatingEditTextLinearCongruenceInTwoVariablesA.set(true); // Lock editTextLinearCongruenceInTwoVariablesA
                    try {
                        editTextLinearCongruenceInTwoVariablesA.setText(s.toString());
                        // editTextLinearCongruenceInTwoVariablesA.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextLinearCongruenceInTwoVariablesA.set(false); // unlock editTextLinearCongruenceInTwoVariablesA
                    }
                }
            });
            editTextLinearCongruenceInTwoVariablesCompactB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextLinearCongruenceInTwoVariablesCompactB.get()) return; // editTextLinearCongruenceInTwoVariablesCompactB is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextLinearCongruenceInTwoVariablesB
                    isUpdatingEditTextLinearCongruenceInTwoVariablesB.set(true); // Lock editTextLinearCongruenceInTwoVariablesB
                    try {
                        editTextLinearCongruenceInTwoVariablesB.setText(s.toString());
                        // editTextLinearCongruenceInTwoVariablesB.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextLinearCongruenceInTwoVariablesB.set(false); // unlock editTextLinearCongruenceInTwoVariablesB
                    }
                }
            });
            editTextLinearCongruenceInTwoVariablesCompactC.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextLinearCongruenceInTwoVariablesCompactC.get()) return; // editTextLinearCongruenceInTwoVariablesCompactC is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextLinearCongruenceInTwoVariablesC
                    isUpdatingEditTextLinearCongruenceInTwoVariablesC.set(true); // Lock editTextLinearCongruenceInTwoVariablesC
                    try {
                        editTextLinearCongruenceInTwoVariablesC.setText(s.toString());
                        // editTextLinearCongruenceInTwoVariablesC.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextLinearCongruenceInTwoVariablesC.set(false); // unlock editTextLinearCongruenceInTwoVariablesC
                    }
                }
            });
            editTextLinearCongruenceInTwoVariablesCompactM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextLinearCongruenceInTwoVariablesCompactM.get()) return; // editTextLinearCongruenceInTwoVariablesCompactM is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextLinearCongruenceInTwoVariablesM
                    isUpdatingEditTextLinearCongruenceInTwoVariablesM.set(true); // Lock editTextLinearCongruenceInTwoVariablesM
                    try {
                        editTextLinearCongruenceInTwoVariablesM.setText(s.toString());
                        // editTextLinearCongruenceInTwoVariablesM.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextLinearCongruenceInTwoVariablesM.set(false); // unlock editTextLinearCongruenceInTwoVariablesM
                    }
                }
            });

            // Extended input a clipboard button events
            textViewLinearCongruenceInTwoVariablesCopyA.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyA);
            });
            textViewLinearCongruenceInTwoVariablesPasteA.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteA);
            });
            textViewLinearCongruenceInTwoVariablesClearA.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearA);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Extended input b clipboard button events
            textViewLinearCongruenceInTwoVariablesCopyB.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyB);
            });
            textViewLinearCongruenceInTwoVariablesPasteB.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteB);
            });
            textViewLinearCongruenceInTwoVariablesClearB.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearB);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Extended input c clipboard button events
            textViewLinearCongruenceInTwoVariablesCopyC.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesC);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyC);
            });
            textViewLinearCongruenceInTwoVariablesPasteC.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesC);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteC);
            });
            textViewLinearCongruenceInTwoVariablesClearC.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesC);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearC);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Extended input m clipboard button events
            textViewLinearCongruenceInTwoVariablesCopyM.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyM);
            });
            textViewLinearCongruenceInTwoVariablesPasteM.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteM);
            });
            textViewLinearCongruenceInTwoVariablesClearM.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearM);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Compact input a clipboard button events
            textViewLinearCongruenceInTwoVariablesCopyCompactA.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyCompactA);
            });
            textViewLinearCongruenceInTwoVariablesPasteCompactA.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteCompactA);
            });
            textViewLinearCongruenceInTwoVariablesClearCompactA.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearCompactA);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Compact input b clipboard button events
            textViewLinearCongruenceInTwoVariablesCopyCompactB.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyCompactB);
            });
            textViewLinearCongruenceInTwoVariablesPasteCompactB.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteCompactB);
            });
            textViewLinearCongruenceInTwoVariablesClearCompactB.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearCompactB);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Compact input c clipboard button events
            textViewLinearCongruenceInTwoVariablesCopyCompactC.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactC);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyCompactC);
            });
            textViewLinearCongruenceInTwoVariablesPasteCompactC.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactC);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteCompactC);
            });
            textViewLinearCongruenceInTwoVariablesClearCompactC.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactC);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearCompactC);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Compact input m clipboard button events
            textViewLinearCongruenceInTwoVariablesCopyCompactM.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyCompactM);
            });
            textViewLinearCongruenceInTwoVariablesPasteCompactM.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteCompactM);
            });
            textViewLinearCongruenceInTwoVariablesClearCompactM.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesCompactM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearCompactM);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Run button events
            buttonLinearCongruenceInTwoVariablesRun.setOnClickListener(v -> onButtonRun(container, buttonLinearCongruenceInTwoVariablesRun, false));
            buttonLinearCongruenceInTwoVariablesRunExample1.setOnClickListener(v -> onButtonRunExample1(container));
            buttonLinearCongruenceInTwoVariablesRunExample2.setOnClickListener(v -> onButtonRunExample2(container));
            buttonLinearCongruenceInTwoVariablesRunExample3.setOnClickListener(v -> onButtonRunExample3(container));

            // Result clipboard button events
            textViewLinearCongruenceInTwoVariablesExpandResult.setOnClickListener(v -> {
                PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewLinearCongruenceInTwoVariablesTitle.getText().toString(), editTextLinearCongruenceInTwoVariablesResult.getText());
                popupResult.show();
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesExpandResult);
            });
            textViewLinearCongruenceInTwoVariablesCopyResult.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesResult);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyResult);
            });
            textViewLinearCongruenceInTwoVariablesClearResult.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesResult);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearResult);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Result events
            editTextLinearCongruenceInTwoVariablesResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.toString().isEmpty()) {
                        textViewLinearCongruenceInTwoVariablesExpandResult.setVisibility(View.GONE);
                    } else {
                        textViewLinearCongruenceInTwoVariablesExpandResult.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        return inflater;
    }
    //endregion CREATE


    //region MENU
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        try {
            menuInflater.inflate(R.menu.menu_fragment_linear_congruence_in_two_variables, menu);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        try {
            // Handle menu item clicks here based on their ID.
            int id = menuItem.getItemId();
            if (id == R.id.linear_congruence_in_two_variables_menu_example_1) {
                this.editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_a));
                this.editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_b));
                this.editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_c));
                this.editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_m));
                this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_1));
                resetResult(true);
                return true;
            }
            if (id == R.id.linear_congruence_in_two_variables_menu_example_2) {
                this.editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_a));
                this.editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_b));
                this.editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_c));
                this.editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_m));
                this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_2));
                resetResult(true);
                return true;
            }
            if (id == R.id.linear_congruence_in_two_variables_menu_example_3) {
                this.editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_a));
                this.editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_b));
                this.editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_c));
                this.editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_m));
                this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_3));
                resetResult(true);
                return true;
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        // If the menu item was not handled by this fragment, return false
        // so that the hosting Activity or other MenuProviders can handle it.
        return false;
    }
    //endregion MENU


    @Override
    public void onResume() {
        super.onResume();
        refreshInputViewMode();
        refreshBiggerControls();
        refreshHideExampleButtons();
        refreshBiggerResultDisplay();
    }


    //region Refresh UI
    private void refreshInputViewMode() {
        try {
            this.isCompactInputView = UserSettings.getCompactInputView(requireContext());
            if(isCompactInputView){
                linearLayoutExtendedInputView.setVisibility(View.GONE);
                linearLayoutCompactInputView.setVisibility(View.VISIBLE);
            } else {
                linearLayoutExtendedInputView.setVisibility(View.VISIBLE);
                linearLayoutCompactInputView.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.buttonLinearCongruenceInTwoVariablesRunExample1.getVisibility() == View.VISIBLE; // Just check one.
            boolean hideExampleButtons = UserSettings.getHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonLinearCongruenceInTwoVariablesRun.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_run_long));
                this.buttonLinearCongruenceInTwoVariablesRunExample1.setVisibility(View.GONE);
                this.buttonLinearCongruenceInTwoVariablesRunExample2.setVisibility(View.GONE);
                this.buttonLinearCongruenceInTwoVariablesRunExample3.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonLinearCongruenceInTwoVariablesRun.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_run_short));
                this.buttonLinearCongruenceInTwoVariablesRunExample1.setVisibility(View.VISIBLE);
                this.buttonLinearCongruenceInTwoVariablesRunExample2.setVisibility(View.VISIBLE);
                this.buttonLinearCongruenceInTwoVariablesRunExample3.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshBiggerControls() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyCompactM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteCompactM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearCompactM, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearResult, biggerControls);
            // Extended input a controls
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelElasticA, biggerControls);
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInTwoVariablesA, biggerControls);
            // Extended input b controls
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelElasticB, biggerControls);
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInTwoVariablesB, biggerControls);
            // Extended input c controls
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelC, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelElasticC, biggerControls);
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInTwoVariablesC, biggerControls);
            // Extended input m controls
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelM, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelElasticM, biggerControls);
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInTwoVariablesM, biggerControls);
            // Compact input a controls
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelCompactA, biggerControls);
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInTwoVariablesCompactA, biggerControls);
            // Compact input b controls
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelCompactB, biggerControls);
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInTwoVariablesCompactB, biggerControls);
            // Compact input c controls
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelCompactC, biggerControls);
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInTwoVariablesCompactC, biggerControls);
            // Compact input m controls
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelCompactM, biggerControls);
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInTwoVariablesCompactM, biggerControls);
            // Buttons
            ControlDisplay.setButtonFontSize(buttonLinearCongruenceInTwoVariablesRun, biggerControls);
            ControlDisplay.setButtonFontSize(buttonLinearCongruenceInTwoVariablesRunExample1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonLinearCongruenceInTwoVariablesRunExample2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonLinearCongruenceInTwoVariablesRunExample3, biggerControls);
            // Output result
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelElasticResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshBiggerResultDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerResultDisplay(requireContext());
            // Output result
            ControlDisplay.setOutputFontSize(editTextLinearCongruenceInTwoVariablesResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Refresh UI


    //region Callback
    @Override
    public void callbackResult(AlgorithmName algorithmName, Object result, ProgressStatus progressStatus) {
        // This is to prevent the error: Non-fatal Exception: java.lang.IllegalStateException: Fragment FragmentPrimesList{94d7331} (36e8cdd6-9d00-4c2a-bd07-ab5550e2c88b) not attached to a context.
        // java.lang.IllegalStateException: Fragment not attached to Activity -> https://stackoverflow.com/questions/28672883/java-lang-illegalstateexception-fragment-not-attached-to-activity
        Activity activity = getActivity();
        if (activity == null || !this.isAdded()) {
            return;
        }
        if (algorithmName == AlgorithmName.LINEAR_CONGRUENCE_IN_TWO_VARIABLES) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextLinearCongruenceInTwoVariablesResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextLinearCongruenceInTwoVariablesResult.setText(resultFromHtml);
            }
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private InputGroup getInputGroupA() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLinearCongruenceInTwoVariablesLabelA, "a", textViewLinearCongruenceInTwoVariablesLabelElasticA)
                .setInput(editTextLinearCongruenceInTwoVariablesA)
                .setCompactControls(textViewLinearCongruenceInTwoVariablesLabelCompactA, editTextLinearCongruenceInTwoVariablesCompactA)
                .build();
    }


    private InputGroup getInputGroupB() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLinearCongruenceInTwoVariablesLabelB, "b", textViewLinearCongruenceInTwoVariablesLabelElasticB)
                .setInput(editTextLinearCongruenceInTwoVariablesB)
                .setCompactControls(textViewLinearCongruenceInTwoVariablesLabelCompactB, editTextLinearCongruenceInTwoVariablesCompactB)
                .build();
    }


    private InputGroup getInputGroupC() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLinearCongruenceInTwoVariablesLabelC, "c", textViewLinearCongruenceInTwoVariablesLabelElasticC)
                .setInput(editTextLinearCongruenceInTwoVariablesC)
                .setCompactControls(textViewLinearCongruenceInTwoVariablesLabelCompactC, editTextLinearCongruenceInTwoVariablesCompactC)
                .build();
    }


    private InputGroup getInputGroupM() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLinearCongruenceInTwoVariablesLabelM, "m", textViewLinearCongruenceInTwoVariablesLabelElasticM)
                .setInput(editTextLinearCongruenceInTwoVariablesM)
                .setCompactControls(textViewLinearCongruenceInTwoVariablesLabelCompactM, editTextLinearCongruenceInTwoVariablesCompactM)
                .build();
    }


    private void onButtonRun(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            InputGroup inputGroupC = getInputGroupC();
            InputGroup inputGroupM = getInputGroupM();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupC)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanMin(requireActivity(), inputGroupM, BigInteger.ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextLinearCongruenceInTwoVariablesA.getText().toString());
            BigInteger b = new BigInteger(editTextLinearCongruenceInTwoVariablesB.getText().toString());
            BigInteger c = new BigInteger(editTextLinearCongruenceInTwoVariablesC.getText().toString());
            BigInteger m = new BigInteger(editTextLinearCongruenceInTwoVariablesM.getText().toString());

            // Reset result
            resetResult(skipLabelResult);

            // Before action performing.
            beforeActionPerforming(button);

            // Perform the linear congruence solver
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.LINEAR_CONGRUENCE_IN_TWO_VARIABLES, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(c);
            algorithmParameters.setInput4(m);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonRunExample1(ViewGroup container) {
        try {
            editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_a));
            editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_b));
            editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_c));
            editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_m));
            this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            onButtonRun(container, buttonLinearCongruenceInTwoVariablesRunExample1, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample2(ViewGroup container) {
        try {
            editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_a));
            editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_b));
            editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_c));
            editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_m));
            this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            onButtonRun(container, buttonLinearCongruenceInTwoVariablesRunExample2, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample3(ViewGroup container) {
        try {
            editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_a));
            editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_b));
            editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_c));
            editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_m));
            this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            onButtonRun(container, buttonLinearCongruenceInTwoVariablesRunExample3, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion BUTTON ACTIONS


    //region RESULT
    private void beforeActionPerforming(Button button) {
        // Hide the keyboard.
        UIHelper.hideSoftKeyBoard(requireActivity());
        // Clear the focus.
        editTextLinearCongruenceInTwoVariablesA.clearFocus();
        editTextLinearCongruenceInTwoVariablesB.clearFocus();
        editTextLinearCongruenceInTwoVariablesC.clearFocus();
        editTextLinearCongruenceInTwoVariablesM.clearFocus();
        editTextLinearCongruenceInTwoVariablesCompactA.clearFocus();
        editTextLinearCongruenceInTwoVariablesCompactB.clearFocus();
        editTextLinearCongruenceInTwoVariablesCompactC.clearFocus();
        editTextLinearCongruenceInTwoVariablesCompactM.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastClipboardButtonClicked() {
        resetAllAndSelectTheLastClipboardButtonClicked(null);
    }
    private void resetAllAndSelectTheLastClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewLinearCongruenceInTwoVariablesCopyA.setSelected(false);
        textViewLinearCongruenceInTwoVariablesPasteA.setSelected(false);
        textViewLinearCongruenceInTwoVariablesClearA.setSelected(false);
        textViewLinearCongruenceInTwoVariablesCopyB.setSelected(false);
        textViewLinearCongruenceInTwoVariablesPasteB.setSelected(false);
        textViewLinearCongruenceInTwoVariablesClearB.setSelected(false);
        textViewLinearCongruenceInTwoVariablesCopyC.setSelected(false);
        textViewLinearCongruenceInTwoVariablesPasteC.setSelected(false);
        textViewLinearCongruenceInTwoVariablesClearC.setSelected(false);
        textViewLinearCongruenceInTwoVariablesCopyM.setSelected(false);
        textViewLinearCongruenceInTwoVariablesPasteM.setSelected(false);
        textViewLinearCongruenceInTwoVariablesClearM.setSelected(false);
        //
        textViewLinearCongruenceInTwoVariablesCopyCompactA.setSelected(false);
        textViewLinearCongruenceInTwoVariablesPasteCompactA.setSelected(false);
        textViewLinearCongruenceInTwoVariablesClearCompactA.setSelected(false);
        textViewLinearCongruenceInTwoVariablesCopyCompactB.setSelected(false);
        textViewLinearCongruenceInTwoVariablesPasteCompactB.setSelected(false);
        textViewLinearCongruenceInTwoVariablesClearCompactB.setSelected(false);
        textViewLinearCongruenceInTwoVariablesCopyCompactC.setSelected(false);
        textViewLinearCongruenceInTwoVariablesPasteCompactC.setSelected(false);
        textViewLinearCongruenceInTwoVariablesClearCompactC.setSelected(false);
        textViewLinearCongruenceInTwoVariablesCopyCompactM.setSelected(false);
        textViewLinearCongruenceInTwoVariablesPasteCompactM.setSelected(false);
        textViewLinearCongruenceInTwoVariablesClearCompactM.setSelected(false);
        //
        textViewLinearCongruenceInTwoVariablesExpandResult.setSelected(false);
        textViewLinearCongruenceInTwoVariablesCopyResult.setSelected(false);
        textViewLinearCongruenceInTwoVariablesClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void resetAllAndSelectTheLastButtonClicked() {
        resetAllAndSelectTheLastButtonClicked(null);
    }
    private void resetAllAndSelectTheLastButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonLinearCongruenceInTwoVariablesRun.setSelected(false);
        buttonLinearCongruenceInTwoVariablesRunExample1.setSelected(false);
        buttonLinearCongruenceInTwoVariablesRunExample2.setSelected(false);
        buttonLinearCongruenceInTwoVariablesRunExample3.setSelected(false);
        // Select he last button clicked.
        if (button != null) {
            button.setSelected(true);
        }
    }
    private void resetResult(boolean skipLabelResult) {
        resetAllAndSelectTheLastClipboardButtonClicked();
        resetAllAndSelectTheLastButtonClicked();
        //
        if(!skipLabelResult) {
            textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextLinearCongruenceInTwoVariablesResult.setText("");
    }
    //endregion RESULT

}