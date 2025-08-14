package com.gegprifti.android.numbertheoryalgorithms.fragments;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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

import com.gegprifti.android.numbertheoryalgorithms.AboutActivity;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.InputGroup;
import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.SettingsActivity;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.settings.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;

import java.math.BigInteger;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;


public class TabFragmentCalculator extends FragmentBase implements Callback {
    private final static String TAG = TabFragmentCalculator.class.getSimpleName();
    static final BigInteger ZERO = BigInteger.ZERO;
    static final BigInteger TWO = BigInteger.valueOf(2L);
    BigInteger INTEGER_MAX_VALUE = new BigInteger(Integer.toString(Integer.MAX_VALUE));
    // Cache settings flags
    boolean isCompactInputView = false;
    boolean enableResultsHistory = false;
    // Extended input view
    LinearLayout linearLayoutExtendedInputView;
    TextView textViewLabelA;
    TextView textViewLabelElasticA;
    TextView textViewMinusA;
    TextView textViewPlusA;
    TextView textViewCopyA;
    TextView textViewPasteA;
    TextView textViewClearA;
    EditText editTextA;
    TextView textViewLabelB;
    TextView textViewLabelElasticB;
    TextView textViewMinusB;
    TextView textViewPlusB;
    TextView textViewCopyB;
    TextView textViewPasteB;
    TextView textViewClearB;
    EditText editTextB;
    LinearLayout linearLayoutRowContainerC;
    TextView textViewLabelC;
    TextView textViewLabelElasticC;
    TextView textViewMinusC;
    TextView textViewPlusC;
    TextView textViewCopyC;
    TextView textViewPasteC;
    TextView textViewClearC;
    EditText editTextC;
    // Compact input view
    LinearLayout linearLayoutCompactInputView;
    TextView textViewLabelCompactA;
    EditText editTextCompactA;
    TextView textViewMinusCompactA;
    TextView textViewPlusCompactA;
    TextView textViewCopyCompactA;
    TextView textViewPasteCompactA;
    TextView textViewClearCompactA;
    TextView textViewLabelCompactB;
    EditText editTextCompactB;
    TextView textViewMinusCompactB;
    TextView textViewPlusCompactB;
    TextView textViewCopyCompactB;
    TextView textViewPasteCompactB;
    TextView textViewClearCompactB;
    LinearLayout linearLayoutRowContainerCompactC;
    TextView textViewLabelCompactC;
    EditText editTextCompactC;
    TextView textViewMinusCompactC;
    TextView textViewPlusCompactC;
    TextView textViewCopyCompactC;
    TextView textViewPasteCompactC;
    TextView textViewClearCompactC;
    // Run buttons
    Button buttonAddition;
    Button buttonSubtraction;
    Button buttonMultiplication;
    Button buttonDivision;
    Button buttonPower;
    Button buttonRoot;
    Button buttonGcd;
    Button buttonLcm;
    Button buttonMod;
    Button buttonModInverse;
    Button buttonModularPower;
    Button buttonIsProbablePrime;
    Button buttonEulerPhi;
    Button buttonFactorial;
    Button buttonNextProbablePrime;
    Button buttonNextProbableTwinPrime;
    // Result controls
    TextView textViewLabelResult1;
    TextView textViewLabelElasticResult1;
    TextView textViewCopyResult1;
    TextView textViewClearResult1;
    EditText editTextResult1;
    LinearLayout linearLayoutResult2Labels;
    LinearLayout linearLayoutResult2;
    TextView textViewLabelResult2;
    TextView textViewLabelElasticResult2;
    TextView textViewCopyResult2;
    TextView textViewClearResult2;
    EditText editTextResult2;
    // Temp controls
    LinearLayout linearLayoutTemporaryField;
    TextView textViewLabelTemp;
    TextView textViewLabelElasticTemp;
    TextView textViewCopyTemp;
    TextView textViewPasteTemp;
    TextView textViewClearTemp;
    EditText editTextTemp;
    // History controls
    LinearLayout linearLayoutHistory;
    TextView textViewLabelHistory;
    TextView textViewLabelElasticHistory;
    TextView textViewCopyHistory;
    TextView textViewClearHistory;
    EditText editTextHistory;
    // Flags to prevent recursive updates
    AtomicBoolean isUpdatingEditTextA = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactA = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextC = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactC = new AtomicBoolean(false);

    //region CREATE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
		View inflater = null;
        try {
            inflater = layoutInflater.inflate(R.layout.fragment_tab_calculator, container, false);
            // Extended input view
            linearLayoutExtendedInputView = inflater.findViewById(R.id.LinearLayoutExtendedInputView);
            textViewLabelA = inflater.findViewById(R.id.TextViewLabelA);
            textViewLabelElasticA = inflater.findViewById(R.id.TextViewLabelElasticA);
            textViewMinusA = inflater.findViewById(R.id.TextViewMinusA);
            textViewPlusA = inflater.findViewById(R.id.TextViewPlusA);
            textViewCopyA = inflater.findViewById(R.id.TextViewCopyA);
            textViewPasteA = inflater.findViewById(R.id.TextViewPasteA);
            textViewClearA = inflater.findViewById(R.id.TextViewClearA);
            editTextA = inflater.findViewById(R.id.EditTextA);
            textViewLabelB = inflater.findViewById(R.id.TextViewLabelB);
            textViewLabelElasticB = inflater.findViewById(R.id.TextViewLabelElasticB);
            textViewMinusB = inflater.findViewById(R.id.TextViewMinusB);
            textViewPlusB = inflater.findViewById(R.id.TextViewPlusB);
            textViewCopyB = inflater.findViewById(R.id.TextViewCopyB);
            textViewPasteB = inflater.findViewById(R.id.TextViewPasteB);
            textViewClearB = inflater.findViewById(R.id.TextViewClearB);
            editTextB = inflater.findViewById(R.id.EditTextB);
            linearLayoutRowContainerC = inflater.findViewById(R.id.RowContainerC);
            textViewLabelC = inflater.findViewById(R.id.TextViewLabelC);
            textViewLabelElasticC = inflater.findViewById(R.id.TextViewLabelElasticC);
            textViewMinusC = inflater.findViewById(R.id.TextViewMinusC);
            textViewPlusC = inflater.findViewById(R.id.TextViewPlusC);
            textViewCopyC = inflater.findViewById(R.id.TextViewCopyC);
            textViewPasteC = inflater.findViewById(R.id.TextViewPasteC);
            textViewClearC = inflater.findViewById(R.id.TextViewClearC);
            editTextC = inflater.findViewById(R.id.EditTextC);
            // Compact input view
            linearLayoutCompactInputView = inflater.findViewById(R.id.LinearLayoutCompactInputView);
            textViewLabelCompactA = inflater.findViewById(R.id.TextViewLabelCompactA);
            textViewMinusCompactA = inflater.findViewById(R.id.TextViewMinusCompactA);
            textViewPlusCompactA = inflater.findViewById(R.id.TextViewPlusCompactA);
            textViewCopyCompactA = inflater.findViewById(R.id.TextViewCopyCompactA);
            textViewPasteCompactA = inflater.findViewById(R.id.TextViewPasteCompactA);
            textViewClearCompactA = inflater.findViewById(R.id.TextViewClearCompactA);
            editTextCompactA = inflater.findViewById(R.id.EditTextCompactA);
            textViewLabelCompactB = inflater.findViewById(R.id.TextViewLabelCompactB);
            textViewMinusCompactB = inflater.findViewById(R.id.TextViewMinusCompactB);
            textViewPlusCompactB = inflater.findViewById(R.id.TextViewPlusCompactB);
            textViewCopyCompactB = inflater.findViewById(R.id.TextViewCopyCompactB);
            textViewPasteCompactB = inflater.findViewById(R.id.TextViewPasteCompactB);
            textViewClearCompactB = inflater.findViewById(R.id.TextViewClearCompactB);
            editTextCompactB = inflater.findViewById(R.id.EditTextCompactB);
            linearLayoutRowContainerCompactC = inflater.findViewById(R.id.RowContainerCompactC);
            textViewLabelCompactC = inflater.findViewById(R.id.TextViewLabelCompactC);
            textViewMinusCompactC = inflater.findViewById(R.id.TextViewMinusCompactC);
            textViewPlusCompactC = inflater.findViewById(R.id.TextViewPlusCompactC);
            textViewCopyCompactC = inflater.findViewById(R.id.TextViewCopyCompactC);
            textViewPasteCompactC = inflater.findViewById(R.id.TextViewPasteCompactC);
            textViewClearCompactC = inflater.findViewById(R.id.TextViewClearCompactC);
            editTextCompactC = inflater.findViewById(R.id.EditTextCompactC);
            // Run buttons
            buttonAddition = inflater.findViewById(R.id.ButtonAddition);
            buttonSubtraction = inflater.findViewById(R.id.ButtonSubtraction);
            buttonMultiplication = inflater.findViewById(R.id.ButtonMultiplication);
            buttonDivision = inflater.findViewById(R.id.ButtonDivision);
            buttonPower = inflater.findViewById(R.id.ButtonPower);
            buttonRoot = inflater.findViewById(R.id.ButtonRoot);
            buttonGcd = inflater.findViewById(R.id.ButtonGcd);
            buttonLcm = inflater.findViewById(R.id.ButtonLcm);
            buttonMod = inflater.findViewById(R.id.ButtonMod);
            buttonModInverse = inflater.findViewById(R.id.ButtonModInverse);
            buttonModularPower = inflater.findViewById(R.id.ButtonModularPower);
            buttonIsProbablePrime = inflater.findViewById(R.id.ButtonIsProbablePrime);
            buttonEulerPhi = inflater.findViewById(R.id.ButtonEulerPhi);
            buttonFactorial = inflater.findViewById(R.id.ButtonFactorial);
            buttonNextProbablePrime = inflater.findViewById(R.id.ButtonNextProbablePrime);
            buttonNextProbableTwinPrime = inflater.findViewById(R.id.ButtonNextProbableTwinPrime);
            // Result
            textViewLabelResult1 = inflater.findViewById(R.id.TextViewLabelResult1);
            textViewLabelElasticResult1 = inflater.findViewById(R.id.TextViewLabelElasticResult1);
            textViewCopyResult1 = inflater.findViewById(R.id.TextViewCopyResult1);
            textViewClearResult1 = inflater.findViewById(R.id.TextViewClearResult1);
            editTextResult1 = inflater.findViewById(R.id.EditTextResult1);
            linearLayoutResult2Labels = inflater.findViewById(R.id.LinearLayoutResult2Labels);
            linearLayoutResult2 = inflater.findViewById(R.id.LinearLayoutResult2);
            textViewLabelResult2 = inflater.findViewById(R.id.TextViewLabelResult2);
            textViewLabelElasticResult2 = inflater.findViewById(R.id.TextViewLabelElasticResult2);
            textViewCopyResult2 = inflater.findViewById(R.id.TextViewCopyResult2);
            textViewClearResult2 = inflater.findViewById(R.id.TextViewClearResult2);
            editTextResult2 = inflater.findViewById(R.id.EditTextResult2);
            linearLayoutTemporaryField = inflater.findViewById(R.id.LinearLayoutTemporaryField);
            textViewLabelTemp = inflater.findViewById(R.id.TextViewLabelTemp);
            textViewLabelElasticTemp = inflater.findViewById(R.id.TextViewLabelElasticTemp);
            textViewCopyTemp = inflater.findViewById(R.id.TextViewCopyTemp);
            textViewPasteTemp = inflater.findViewById(R.id.TextViewPasteTemp);
            textViewClearTemp = inflater.findViewById(R.id.TextViewClearTemp);
            editTextTemp = inflater.findViewById(R.id.EditTextTemp);
            linearLayoutHistory = inflater.findViewById(R.id.LinearLayoutHistory);
            textViewLabelHistory = inflater.findViewById(R.id.TextViewLabelHistory);
            textViewLabelElasticHistory = inflater.findViewById(R.id.TextViewLabelElasticHistory);
            textViewCopyHistory = inflater.findViewById(R.id.TextViewCopyHistory);
            textViewClearHistory = inflater.findViewById(R.id.TextViewClearHistory);
            editTextHistory = inflater.findViewById(R.id.EditTextHistory);
            // Constrain extended input
            editTextA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextC.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            // Constrain compact input
            editTextCompactA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactC.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Extended input events
            editTextA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextA.get()) return; // editTextA is locked
                    // Other work
                    String calculatorLabelA = "a" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelA.setText(calculatorLabelA);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactA
                    isUpdatingEditTextCompactA.set(true); // Lock editTextCompactA
                    try {
                        editTextCompactA.setText(s.toString());
                        // editTextCompactA.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactA.set(false); // Unlock editTextCompactA
                    }
                }
            });
            editTextB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextB.get()) return; // editTextB is locked
                    // Other work
                    String calculatorLabelB = "b" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelB.setText(calculatorLabelB);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactB
                    isUpdatingEditTextCompactB.set(true); // Lock editTextCompactB
                    try {
                        editTextCompactB.setText(s.toString());
                        // editTextCompactB.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactB.set(false); // Unlock editTextCompactB
                    }
                }
            });
            editTextC.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextC.get()) return; // editTextC is locked
                    // Other work
                    String calculatorLabelC = "c" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelC.setText(calculatorLabelC);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactC
                    isUpdatingEditTextCompactC.set(true); // Lock editTextCompactC
                    try {
                        editTextCompactC.setText(s.toString());
                        // editTextCompactC.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactC.set(false); // Unlock editTextCompactC
                    }
                }
            });

            // Compact input events
            editTextCompactA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactA.get()) return; // editTextCompactA is locked
                    // Other work
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextA
                    isUpdatingEditTextA.set(true); // Lock editTextA
                    try {
                        editTextA.setText(s.toString());
                        // editTextA.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextA.set(false); // unlock editTextA
                    }
                }
            });
            editTextCompactB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactB.get()) return; // editTextCompactB is locked
                    // Other work
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextB
                    isUpdatingEditTextB.set(true); // Lock editTextB
                    try {
                        editTextB.setText(s.toString());
                        // editTextB.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextB.set(false); // unlock editTextB
                    }
                }
            });
            editTextCompactC.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactC.get()) return; // editTextCompactC is locked
                    // Other work
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextC
                    isUpdatingEditTextC.set(true); // Lock editTextC
                    try {
                        editTextC.setText(s.toString());
                        // editTextC.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextC.set(false); // unlock editTextC
                    }
                }
            });

            // Extended input a clipboard button events
            textViewMinusA.setOnClickListener(v -> {
                decreaseByOne(editTextA);
                resetAllAndSelectTheLastButtonClicked(textViewMinusA);
            });
            textViewPlusA.setOnClickListener(v -> {
                increaseByOne(editTextA);
                resetAllAndSelectTheLastButtonClicked(textViewPlusA);
            });
            textViewCopyA.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextA);
                resetAllAndSelectTheLastButtonClicked(textViewCopyA);
            });
            textViewPasteA.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextA);
                resetAllAndSelectTheLastButtonClicked(textViewPasteA);
            });
            textViewClearA.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextA);
                resetAllAndSelectTheLastButtonClicked(textViewClearA);
            });

            // Extended input b clipboard button events
            textViewMinusB.setOnClickListener(v -> {
                decreaseByOne(editTextB);
                resetAllAndSelectTheLastButtonClicked(textViewMinusB);
            });
            textViewPlusB.setOnClickListener(v -> {
                increaseByOne(editTextB);
                resetAllAndSelectTheLastButtonClicked(textViewPlusB);
            });
            textViewCopyB.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextB);
                resetAllAndSelectTheLastButtonClicked(textViewCopyB);
            });
            textViewPasteB.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextB);
                resetAllAndSelectTheLastButtonClicked(textViewPasteB);
            });
            textViewClearB.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextB);
                resetAllAndSelectTheLastButtonClicked(textViewClearB);
            });

            // Extended input c clipboard button events
            textViewMinusC.setOnClickListener(v -> {
                decreaseByOne(editTextC);
                resetAllAndSelectTheLastButtonClicked(textViewMinusC);
            });
            textViewPlusC.setOnClickListener(v -> {
                increaseByOne(editTextC);
                resetAllAndSelectTheLastButtonClicked(textViewPlusC);
            });
            textViewCopyC.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextC);
                resetAllAndSelectTheLastButtonClicked(textViewCopyC);
            });
            textViewPasteC.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextC);
                resetAllAndSelectTheLastButtonClicked(textViewPasteC);
            });
            textViewClearC.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextC);
                resetAllAndSelectTheLastButtonClicked(textViewClearC);
            });

            // Compact input a clipboard button events
            textViewMinusCompactA.setOnClickListener(v -> {
                decreaseByOne(editTextCompactA);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactA);
            });
            textViewPlusCompactA.setOnClickListener(v -> {
                increaseByOne(editTextCompactA);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactA);
            });
            textViewCopyCompactA.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactA);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactA);
            });
            textViewPasteCompactA.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactA);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactA);
            });
            textViewClearCompactA.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactA);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactA);
            });

            // Compact input b clipboard button events
            textViewMinusCompactB.setOnClickListener(v -> {
                decreaseByOne(editTextCompactB);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactB);
            });
            textViewPlusCompactB.setOnClickListener(v -> {
                increaseByOne(editTextCompactB);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactB);
            });
            textViewCopyCompactB.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactB);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactB);
            });
            textViewPasteCompactB.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactB);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactB);
            });
            textViewClearCompactB.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactB);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactB);
            });

            // Compact input c clipboard button events
            textViewMinusCompactC.setOnClickListener(v -> {
                decreaseByOne(editTextCompactC);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactC);
            });
            textViewPlusCompactC.setOnClickListener(v -> {
                increaseByOne(editTextCompactC);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactC);
            });
            textViewCopyCompactC.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactC);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactC);
            });
            textViewPasteCompactC.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactC);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactC);
            });
            textViewClearCompactC.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactC);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactC);
            });

            // Run button events
            buttonAddition.setOnClickListener(v -> onButtonAddition(container));
            buttonSubtraction.setOnClickListener(v -> onButtonSubtraction(container));
            buttonMultiplication.setOnClickListener(v -> onButtonMultiplication(container));
            buttonDivision.setOnClickListener(v -> onButtonDivision(container));
            buttonPower.setOnClickListener(v -> onButtonPower(container));
            buttonRoot.setOnClickListener(v -> onButtonRoot(container));
            buttonIsProbablePrime.setOnClickListener(view -> onButtonIsProbablePrime(container));
            buttonEulerPhi.setOnClickListener(view -> onButtonEulerPhi(container));
            buttonFactorial.setOnClickListener(view -> onButtonFactorial(container));
            buttonNextProbablePrime.setOnClickListener(view -> onButtonNextProbablePrime(container));
            buttonNextProbableTwinPrime.setOnClickListener(view -> onButtonNextProbableTwinPrime(container));
            buttonGcd.setOnClickListener(v -> onButtonGcd(container));
            buttonLcm.setOnClickListener(v -> onButtonLcm(container));
            buttonMod.setOnClickListener(v -> onButtonMod(container));
            buttonModInverse.setOnClickListener(v -> onButtonModInverse(container));
            buttonModularPower.setOnClickListener(v -> onButtonModularPower(container));

            // Result1 clipboard button events
            textViewCopyResult1.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextResult1);
                resetAllAndSelectTheLastButtonClicked(textViewCopyResult1);
            });
            textViewClearResult1.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextResult1);
                resetResult1();
                resetAllAndSelectTheLastButtonClicked(textViewClearResult1);
            });

            // Result2 clipboard button events
            textViewCopyResult2.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextResult2);
                resetAllAndSelectTheLastButtonClicked(textViewCopyResult2);
            });
            textViewClearResult2.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextResult2);
                resetResult2();
                resetAllAndSelectTheLastButtonClicked(textViewClearResult2);
            });

            // Temp events
            textViewCopyTemp.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextTemp);
                resetAllAndSelectTheLastButtonClicked(textViewCopyTemp);
            });
            textViewPasteTemp.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextTemp);
                resetAllAndSelectTheLastButtonClicked(textViewPasteTemp);
            });
            textViewClearTemp.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextTemp);
                resetAllAndSelectTheLastButtonClicked(textViewClearTemp);
            });

            // History events
            textViewCopyHistory.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextHistory);
                resetAllAndSelectTheLastButtonClicked(textViewCopyHistory);
            });
            textViewClearHistory.setOnClickListener(v -> {
                if (editTextHistory.getText() != null && !editTextHistory.getText().toString().isEmpty()) {
                    AlertDialog dialog = new AlertDialog.Builder(requireContext())
                            .setTitle("Clear results history?")
                            .setMessage("Are you sure you want to clear the results history?")
                            .setPositiveButton("Yes", (dialogInterface, which) -> {
                                UIHelper.clearEditText(requireContext(), editTextHistory);
                            })
                            .setNegativeButton("No", null) // Dismiss
                            .show();
                    Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.color.colorYellow);
                }
                resetAllAndSelectTheLastButtonClicked(textViewClearHistory);
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
            menuInflater.inflate(R.menu.menu_fragment_tab_calculator, menu);
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
            if (id == R.id.action_main_settings) {
                Intent intent = new Intent(requireActivity(), SettingsActivity.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.action_main_about) {
                Intent intent = new Intent(requireActivity(), AboutActivity.class);
                startActivity(intent);
                return true;
            } else if (id == R.id.menu_documentation) {
                DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.CALCULATOR_PDF).show(getParentFragmentManager(), "CALCULATOR_PDF");
                return true;
            } else if (id == R.id.menu_rsa_100) {
                String rsa_100_pq = "1522605027922533360535618378132637429718068114961380688657908494580122963258952897654000350692006139";
                String rsa_100_p = "37975227936943673922808872755445627854565536638199";
                editTextA.setText(rsa_100_pq);
                editTextB.setText(rsa_100_p);
                // a: 37975227936943673922808872755445627854565536638199
                // b: 40094690950920881030683735292761468389214899724061
                // Result: 1522605027922533360535618378132637429718068114961380688657908494580122963258952897654000350692006139
                // Reset the last button clicked.
                resetAllAndSelectTheLastButtonClicked();
                UIHelper.hideSoftKeyBoard(requireActivity());
                editTextA.clearFocus();
                editTextB.clearFocus();
                // Reset result
                resetResult();
                setVisibilityToGoneResult2();
                return true;
            } else if (id == R.id.menu_rsa_576) {
                String rsa_576_pq = "188198812920607963838697239461650439807163563379417382700763356422988859715234665485319060606504743045317388011303396716199692321205734031879550656996221305168759307650257059";
                String rsa_576_p ="398075086424064937397125500550386491199064362342526708406385189575946388957261768583317";
                editTextA.setText(rsa_576_pq);
                editTextB.setText(rsa_576_p);
                // a: 398075086424064937397125500550386491199064362342526708406385189575946388957261768583317
                // b: 472772146107435302536223071973048224632914695302097116459852171130520711256363590397527
                // Result: 188198812920607963838697239461650439807163563379417382700763356422988859715234665485319060606504743045317388011303396716199692321205734031879550656996221305168759307650257059
                // Reset the last button clicked.
                resetAllAndSelectTheLastButtonClicked();
                UIHelper.hideSoftKeyBoard(requireActivity());
                editTextA.clearFocus();
                editTextB.clearFocus();
                // Reset result
                resetResult();
                setVisibilityToGoneResult2();
                return true;
            } else if (id == R.id.menu_rsa_768) {
                String rsa_768_pq = "1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413";
                String rsa_768_p = "33478071698956898786044169848212690817704794983713768568912431388982883793878002287614711652531743087737814467999489";
                editTextA.setText(rsa_768_pq);
                editTextB.setText(rsa_768_p);
                // a: 33478071698956898786044169848212690817704794983713768568912431388982883793878002287614711652531743087737814467999489
                // b: 36746043666799590428244633799627952632279158164343087642676032283815739666511279233373417143396810270092798736308917
                // Result: 1230186684530117755130494958384962720772853569595334792197322452151726400507263657518745202199786469389956474942774063845925192557326303453731548268507917026122142913461670429214311602221240479274737794080665351419597459856902143413
                // Reset the last button clicked.
                resetAllAndSelectTheLastButtonClicked();
                UIHelper.hideSoftKeyBoard(requireActivity());
                editTextA.clearFocus();
                editTextB.clearFocus();
                // Reset result
                resetResult();
                setVisibilityToGoneResult2();
                return true;
            } else if (id == R.id.menu_rsa_1536) {
                String rsa_1536_pq ="1847699703211741474306835620200164403018549338663410171471785774910651696711161249859337684305435744585616061544571794052229717732524660960646946071249623720442022269756756687378427562389508764678440933285157496578843415088475528298186726451339863364931908084671990431874381283363502795470282653297802934916155811881049844908319545009848393775227257052578591944993870073695755688436933812779613089230392569695253261620823676490316036551371447913932347169566988069";
                editTextA.setText(rsa_1536_pq);
                editTextB.setText("");
                // a:
                // b:
                // Result: 1847699703211741474306835620200164403018549338663410171471785774910651696711161249859337684305435744585616061544571794052229717732524660960646946071249623720442022269756756687378427562389508764678440933285157496578843415088475528298186726451339863364931908084671990431874381283363502795470282653297802934916155811881049844908319545009848393775227257052578591944993870073695755688436933812779613089230392569695253261620823676490316036551371447913932347169566988069
                // Reset the last button clicked.
                resetAllAndSelectTheLastButtonClicked();
                UIHelper.hideSoftKeyBoard(requireActivity());
                editTextA.clearFocus();
                editTextB.clearFocus();
                // Reset result
                resetResult();
                setVisibilityToGoneResult2();
                return true;
            } else if (id == R.id.menu_rsa_2048) {
                String rsa_2048_pq = "25195908475657893494027183240048398571429282126204032027777137836043662020707595556264018525880784406918290641249515082189298559149176184502808489120072844992687392807287776735971418347270261896375014971824691165077613379859095700097330459748808428401797429100642458691817195118746121515172654632282216869987549182422433637259085141865462043576798423387184774447920739934236584823824281198163815010674810451660377306056201619676256133844143603833904414952634432190114657544454178424020924616515723350778707749817125772467962926386356373289912154831438167899885040445364023527381951378636564391212010397122822120720357";
                editTextA.setText(rsa_2048_pq);
                editTextB.setText("");
                // a:
                // b:
                // Result: 25195908475657893494027183240048398571429282126204032027777137836043662020707595556264018525880784406918290641249515082189298559149176184502808489120072844992687392807287776735971418347270261896375014971824691165077613379859095700097330459748808428401797429100642458691817195118746121515172654632282216869987549182422433637259085141865462043576798423387184774447920739934236584823824281198163815010674810451660377306056201619676256133844143603833904414952634432190114657544454178424020924616515723350778707749817125772467962926386356373289912154831438167899885040445364023527381951378636564391212010397122822120720357
                // Reset the last button clicked.
                resetAllAndSelectTheLastButtonClicked();
                UIHelper.hideSoftKeyBoard(requireActivity());
                editTextA.clearFocus();
                editTextB.clearFocus();
                // Reset result
                resetResult();
                setVisibilityToGoneResult2();
                return true;
            } else if (id == R.id.menu_more_rsa) {
                UIHelper.openWith(requireActivity(), "https://en.wikipedia.org/wiki/RSA_Factoring_Challenge");
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
        refreshShowInputDecreaseIncreaseButtons();
        refreshBiggerControls();
        refreshBiggerResultDisplay();
        refreshShowTemporaryField();
        refreshEnableResultsHistory();
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


    private void refreshShowInputDecreaseIncreaseButtons() {
        try {
            boolean showInputDecreaseIncreaseButtons = UserSettings.getShowInputDecreaseIncreaseButtons(requireContext());
            if (showInputDecreaseIncreaseButtons) {
                textViewMinusA.setVisibility(View.VISIBLE);
                textViewPlusA.setVisibility(View.VISIBLE);
                textViewMinusCompactA.setVisibility(View.VISIBLE);
                textViewPlusCompactA.setVisibility(View.VISIBLE);
                textViewMinusB.setVisibility(View.VISIBLE);
                textViewPlusB.setVisibility(View.VISIBLE);
                textViewMinusCompactB.setVisibility(View.VISIBLE);
                textViewPlusCompactB.setVisibility(View.VISIBLE);
                textViewMinusC.setVisibility(View.VISIBLE);
                textViewPlusC.setVisibility(View.VISIBLE);
                textViewMinusCompactC.setVisibility(View.VISIBLE);
                textViewPlusCompactC.setVisibility(View.VISIBLE);
            } else {
                textViewMinusA.setVisibility(View.GONE);
                textViewPlusA.setVisibility(View.GONE);
                textViewMinusCompactA.setVisibility(View.GONE);
                textViewPlusCompactA.setVisibility(View.GONE);
                textViewMinusB.setVisibility(View.GONE);
                textViewPlusB.setVisibility(View.GONE);
                textViewMinusCompactB.setVisibility(View.GONE);
                textViewPlusCompactB.setVisibility(View.GONE);
                textViewMinusC.setVisibility(View.GONE);
                textViewPlusC.setVisibility(View.GONE);
                textViewMinusCompactC.setVisibility(View.GONE);
                textViewPlusCompactC.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshBiggerControls() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Extended input a
            ControlDisplay.setInputLabelFontSize(textViewLabelA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearA, biggerControls);
            ControlDisplay.setInputFontSize(editTextA, biggerControls);
            // Compact input a
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactA, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactA, biggerControls);
            // Extended input b
            ControlDisplay.setInputLabelFontSize(textViewLabelB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearB, biggerControls);
            ControlDisplay.setInputFontSize(editTextB, biggerControls);
            // Compact input b
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactB, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactB, biggerControls);
            // Extended input c
            ControlDisplay.setInputLabelFontSize(textViewLabelC, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearC, biggerControls);
            ControlDisplay.setInputFontSize(editTextC, biggerControls);
            // Compact input c
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactC, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactC, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactC, biggerControls);
            // Run buttons
            ControlDisplay.setButtonFontSize(buttonAddition, biggerControls);
            ControlDisplay.setButtonFontSize(buttonSubtraction, biggerControls);
            ControlDisplay.setButtonFontSize(buttonMultiplication, biggerControls);
            ControlDisplay.setButtonFontSize(buttonDivision, biggerControls);
            ControlDisplay.setButtonFontSize(buttonPower, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRoot, biggerControls);
            ControlDisplay.setButtonFontSize(buttonGcd, biggerControls);
            ControlDisplay.setButtonFontSize(buttonLcm, biggerControls);
            ControlDisplay.setButtonFontSize(buttonMod, biggerControls);
            ControlDisplay.setButtonFontSize(buttonModInverse, biggerControls);
            ControlDisplay.setButtonFontSize(buttonModularPower, biggerControls);
            ControlDisplay.setButtonFontSize(buttonIsProbablePrime, biggerControls);
            ControlDisplay.setButtonFontSize(buttonEulerPhi, biggerControls);
            ControlDisplay.setButtonFontSize(buttonFactorial, biggerControls);
            ControlDisplay.setButtonFontSize(buttonNextProbablePrime, biggerControls);
            ControlDisplay.setButtonFontSize(buttonNextProbableTwinPrime, biggerControls);
            // Output result1
            ControlDisplay.setInputLabelFontSize(textViewLabelResult1, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticResult1, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyResult1, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult1, biggerControls);
            // Output result2
            ControlDisplay.setInputLabelFontSize(textViewLabelResult2, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticResult2, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyResult2, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult2, biggerControls);
            // Temp
            ControlDisplay.setInputLabelFontSize(textViewLabelTemp, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticTemp, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyTemp, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteTemp, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearTemp, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshBiggerResultDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerResultDisplay(requireContext());
            ControlDisplay.setOutputFontSize(editTextResult1, biggerControls);
            ControlDisplay.setOutputFontSize(editTextResult2, biggerControls);
            ControlDisplay.setOutputFontSize(editTextTemp, biggerControls);
            ControlDisplay.setOutputFontSize(editTextHistory, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshShowTemporaryField() {
        try {
            boolean showTemporaryField = UserSettings.getShowTemporaryFieldInCalculator(requireContext());
            if (showTemporaryField) {
                linearLayoutTemporaryField.setVisibility(View.VISIBLE);
            } else {
                linearLayoutTemporaryField.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshEnableResultsHistory() {
        try {
            enableResultsHistory = UserSettings.getEnableResultsHistoryInCalculator(requireContext());
            if (enableResultsHistory) {
                linearLayoutHistory.setVisibility(View.VISIBLE);
            } else {
                linearLayoutHistory.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Refresh UI


    //region Callback
    @Override
    public void callbackResult(AlgorithmName algorithmName, Object result, ProgressStatus progressStatus) {
        Activity activity = getActivity();
        if (activity == null || !this.isAdded()) {
            return;
        }
        // Inputs
        String a = editTextA.getText().toString();
        String b = editTextB.getText().toString();
        String c = editTextC.getText().toString();
        // Results text
        String operationResult1 = null;
        String operationResult2 = null;
        // Results
        String result1 = null;
        String result2 = null;
        //
        switch (algorithmName) {
            case CALCULATOR_ADDITION:
                operationResult1 = requireContext().getResources().getString(R.string.calculator_addition_label_result1) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_SUBTRACTION:
                operationResult1 = requireContext().getResources().getString(R.string.calculator_subtraction_label_result1) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_MULTIPLICATION:
                operationResult1 = requireContext().getResources().getString(R.string.calculator_multiplication_label_result1) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_DIVISION:
                String[] quotient_remainder = new String[] {null, null};
                if (result != null && !(((String)result).isEmpty())) {
                    quotient_remainder = ((String)result).split("_"); // quotient_remainder
                }
                // Result 1
                operationResult1 = requireContext().getResources().getString(R.string.calculator_division_label_result1) + UIHelper.getNrOfDigits(quotient_remainder[0]);
                result1 = quotient_remainder[0];
                // Result 2
                operationResult2 = requireContext().getResources().getString(R.string.calculator_division_label_result2) + UIHelper.getNrOfDigits(quotient_remainder[1]);
                result2 = quotient_remainder[1];
                break;
            case CALCULATOR_POWER:
                operationResult1 = requireContext().getResources().getString(R.string.calculator_power_label_result1) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_ROOT:
                String[] root_remainder = new String[] {null, null};
                if (result != null && !(((String)result).isEmpty())) {
                    root_remainder = ((String)result).split("_"); // root_remainder
                }
                // Result 1
                operationResult1 = requireContext().getResources().getString(R.string.calculator_root_label_result1) + UIHelper.getNrOfDigits(root_remainder[0]);
                result1 = root_remainder[0];
                // Result 2
                operationResult2 = requireContext().getResources().getString(R.string.calculator_root_label_result2) + UIHelper.getNrOfDigits(root_remainder[1]);
                result2 = root_remainder[1];
                break;
            case CALCULATOR_GCD:
                operationResult1 = requireContext().getResources().getString(R.string.calculator_gcd_label_result1) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_LCM:
                operationResult1 = requireContext().getResources().getString(R.string.calculator_lcm_label_result1) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_MOD:
                operationResult1 = requireContext().getResources().getString(R.string.calculator_mod_label_result1) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_MOD_INVERSE :
                operationResult1 = requireContext().getResources().getString(R.string.calculator_mod_inverse_label_result1) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_MODULAR_POWER:
                operationResult1 = requireContext().getResources().getString(R.string.calculator_modular_power_label_result1) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_IS_PROBABLE_PRIME :
                operationResult1 = requireContext().getResources().getString(R.string.calculator_is_a_prime);
                if(result.equals("1")) {
                    result = requireContext().getResources().getString(R.string.calculator_a_is_prime_yes);
                } else {
                    result = requireContext().getResources().getString(R.string.calculator_a_is_prime_no);
                }
                result1 = (String)result;
                break;
            case CALCULATOR_EULER_PHI :
                b = null;
                operationResult1 = requireContext().getResources().getString(R.string.calculator_euler_phi_of_a) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_FACTORIAL :
                b = null;
                operationResult1 = requireContext().getResources().getString(R.string.calculator_a_factorial) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_NEXT_PROBABLE_PRIME :
                b = null;
                operationResult1 = requireContext().getResources().getString(R.string.calculator_next_prime_label_result1) + UIHelper.getNrOfDigits((String)result);
                result1 = (String)result;
                break;
            case CALCULATOR_NEXT_PROBABLE_TWIN_PRIME_PAIR :
                b = null;
                String[] twin_prime_pair = new String[] {null, null};
                if (result != null && !(((String)result).isEmpty())) {
                    twin_prime_pair = ((String)result).split("_"); // prime1_prime2
                }
                // Result 1
                operationResult1 = requireContext().getResources().getString(R.string.calculator_next_twin_prime_label_result1) + UIHelper.getNrOfDigits((String)twin_prime_pair[0]);
                result1 = twin_prime_pair[0];
                // Result 2
                operationResult2 = requireContext().getResources().getString(R.string.calculator_next_twin_prime_label_result2) + UIHelper.getNrOfDigits((String)twin_prime_pair[1]);
                result2 = twin_prime_pair[1];
                break;
            default:
                String labelResultText = requireContext().getResources().getString(R.string.result);
                operationResult1 = labelResultText + "₁";
                operationResult2 = labelResultText + "₂";
                result1 = "";
                result2 = "";
                break;
        }
        // Check if canceled
        if (progressStatus == ProgressStatus.CANCELED) {
            String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
            result1 = resultCanceledText;
            result2 = resultCanceledText;
        }
        // Label text
        textViewLabelResult1.setText(operationResult1);
        textViewLabelResult2.setText(operationResult2);
        // Result value
        editTextResult1.setText(result1);
        editTextResult2.setText(result2);
        // Results history
        writeResultsHistory(a, b, c, operationResult1, operationResult2, result1, result2);
    }
    private void writeResultsHistory(String a, String b, String c, String operationResult1, String operationResult2, String result1, String result2) {
        try {
            if (enableResultsHistory) {
                String resultHistory = getResultHistory(a, b, c, operationResult1, operationResult2, result1, result2);
                String newResultHistory;
                String oldResultHistory = editTextHistory.getText().toString();
                if (oldResultHistory.isEmpty()){
                    newResultHistory = resultHistory;
                } else {
                    newResultHistory = resultHistory + "\n\n" + oldResultHistory;
                }
                editTextHistory.setText(newResultHistory);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    @NonNull
    private static String getResultHistory(String a, String b, String c, String operationResult1, String operationResult2, String result1, String result2) {
        StringBuilder resultHistory = new StringBuilder();
        resultHistory.append("a: ").append(a).append("\n");
        if(b != null && !b.isEmpty()) {
            resultHistory.append("b: ").append(b).append("\n");
        }
        if(c != null && !c.isEmpty()) {
            resultHistory.append("c: ").append(c).append("\n");
        }
        resultHistory.append("o: ").append(operationResult1).append("\n");
        if(result2 == null || result2.isEmpty() || result2.equalsIgnoreCase("Canceled")){
            resultHistory.append("r: ").append(result1);
        } else {
            resultHistory.append("r: ").append(result1).append("\n");
            resultHistory.append("o: ").append(operationResult2).append("\n");
            resultHistory.append("r: ").append(result2);
        }
        return resultHistory.toString();
    }
    //endregion Callback


    //region Run buttons
    private InputGroup getInputGroupA() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelA, "a", textViewLabelElasticA)
                .setInput(editTextA)
                .setCompactControls(textViewLabelCompactA, editTextCompactA)
                .build();
    }


    private InputGroup getInputGroupB() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelB, "b", textViewLabelElasticB)
                .setInput(editTextB)
                .setCompactControls(textViewLabelCompactB, editTextCompactB)
                .build();
    }


    private InputGroup getInputGroupC() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelC, "c", textViewLabelElasticC)
                .setInput(editTextC)
                .setCompactControls(textViewLabelCompactC, editTextCompactC)
                .build();
    }


    private void onButtonAddition(ViewGroup container) {
        try  {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonAddition);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_ADDITION, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonSubtraction(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonSubtraction);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_SUBTRACTION, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonMultiplication(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonMultiplication);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_MULTIPLICATION, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonDivision(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeOtherThanZero(requireContext(), inputGroupB)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToVisibleResult2();

            // Before action performing.
            beforeActionPerforming(buttonDivision);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_DIVISION, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonPower(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), inputGroupB, ZERO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            final BigInteger a = new BigInteger(editTextA.getText().toString());
            final BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonPower);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_POWER, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonRoot(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupA, ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), inputGroupB, ONE, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToVisibleResult2();

            // Before action performing.
            beforeActionPerforming(buttonRoot);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_ROOT, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonGcd(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonGcd);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_GCD, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonLcm(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            if (a.compareTo(ZERO) == 0 && b.compareTo(ZERO) == 0) {
                UIHelper.showCustomToastLight(requireContext(), "Not both a and b can be zero!");
                return;
            }

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonLcm);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_LCM, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonMod(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupB, ONE)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonMod);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_MOD, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonModInverse(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupB, ONE)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonModInverse);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_MOD_INVERSE, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonModularPower(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            InputGroup inputGroupC = getInputGroupC();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupB, ONE)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupC, ONE)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());
            BigInteger c = new BigInteger(editTextC.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonModularPower);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_MODULAR_POWER, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(c);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonIsProbablePrime(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupA, TWO)) {
                return;
            }
            if (UIHelper.checkInputMustBeFilled(requireContext(), inputGroupB, "Must specify the certainty in input <b>%s</b>")) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), inputGroupB, ONE, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonIsProbablePrime);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_IS_PROBABLE_PRIME, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonEulerPhi(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            if (UIHelper.checkInputMustBeGreaterThanMin(requireContext(), inputGroupA, ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonEulerPhi);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_EULER_PHI, this);
            algorithmParameters.setInput1(a);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonFactorial(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupA, ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonFactorial);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_FACTORIAL, this);
            algorithmParameters.setInput1(a);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonNextProbablePrime(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupA, TWO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());

            // Reset result
            resetResult();
            setVisibilityToGoneResult2();

            // Before action performing.
            beforeActionPerforming(buttonNextProbablePrime);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_NEXT_PROBABLE_PRIME, this);
            algorithmParameters.setInput1(a);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonNextProbableTwinPrime(ViewGroup container) {
        try {
            // Make the second result gone.
            setVisibilityToGoneResult2();

            // (3, 5), (5, 7), (11, 13), (17, 19), (29, 31), (41, 43), (59, 61), (71, 73), (101, 103), (107, 109), (137, 139), ...

            // Check.
            InputGroup inputGroupA = getInputGroupA();
            if (UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }

            // Get the input numbers.
            BigInteger a = new BigInteger(editTextA.getText().toString());

            // Reset result
            resetResult();
            // Make the second result visible.
            setVisibilityToVisibleResult2();

            // Before action performing.
            beforeActionPerforming(buttonNextProbableTwinPrime);

            // Perform action.
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.CALCULATOR_NEXT_PROBABLE_TWIN_PRIME_PAIR, this);
            algorithmParameters.setInput1(a);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Run buttons


    //region RESULT
    private void beforeActionPerforming(Button button) {
        UIHelper.hideSoftKeyBoard(requireActivity());
        // Clear the focus.
        editTextA.clearFocus();
        editTextB.clearFocus();
        editTextC.clearFocus();
        editTextCompactA.clearFocus();
        editTextCompactB.clearFocus();
        editTextCompactC.clearFocus();
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastButtonClicked() {
        resetAllAndSelectTheLastButtonClicked(null);
    }
    private void resetAllAndSelectTheLastButtonClicked(TextView textView) {
        textViewMinusA.setSelected(false);
        textViewPlusA.setSelected(false);
        textViewCopyA.setSelected(false);
        textViewPasteA.setSelected(false);
        textViewClearA.setSelected(false);
        textViewMinusB.setSelected(false);
        textViewPlusB.setSelected(false);
        textViewCopyB.setSelected(false);
        textViewPasteB.setSelected(false);
        textViewClearB.setSelected(false);
        textViewMinusC.setSelected(false);
        textViewPlusC.setSelected(false);
        textViewCopyC.setSelected(false);
        textViewPasteC.setSelected(false);
        textViewClearC.setSelected(false);
        //
        textViewMinusCompactA.setSelected(false);
        textViewPlusCompactA.setSelected(false);
        textViewCopyCompactA.setSelected(false);
        textViewPasteCompactA.setSelected(false);
        textViewClearCompactA.setSelected(false);
        textViewMinusCompactB.setSelected(false);
        textViewPlusCompactB.setSelected(false);
        textViewCopyCompactB.setSelected(false);
        textViewPasteCompactB.setSelected(false);
        textViewClearCompactB.setSelected(false);
        textViewMinusCompactC.setSelected(false);
        textViewPlusCompactC.setSelected(false);
        textViewCopyCompactC.setSelected(false);
        textViewPasteCompactC.setSelected(false);
        textViewClearCompactC.setSelected(false);
        //
        buttonAddition.setSelected(false);
        buttonSubtraction.setSelected(false);
        buttonMultiplication.setSelected(false);
        buttonDivision.setSelected(false);
        buttonPower.setSelected(false);
        buttonRoot.setSelected(false);
        buttonGcd.setSelected(false);
        buttonLcm.setSelected(false);
        buttonMod.setSelected(false);
        buttonModInverse.setSelected(false);
        buttonModularPower.setSelected(false);
        buttonMod.setSelected(false);
        buttonIsProbablePrime.setSelected(false);
        buttonEulerPhi.setSelected(false);
        buttonFactorial.setSelected(false);
        buttonNextProbablePrime.setSelected(false);
        buttonNextProbableTwinPrime.setSelected(false);
        //
        textViewCopyResult1.setSelected(false);
        textViewClearResult1.setSelected(false);
        textViewCopyResult2.setSelected(false);
        textViewClearResult2.setSelected(false);
        //
        textViewCopyTemp.setSelected(false);
        textViewPasteTemp.setSelected(false);
        textViewClearTemp.setSelected(false);
        //
        textViewCopyHistory.setSelected(false);
        textViewClearHistory.setSelected(false);
        // Select the last button clicked.
        if (textView != null) {
            UIHelper.vibrateOnButtonTap(requireContext());
            textView.setSelected(true);
        }
    }
    private void resetResult() {
        resetAllAndSelectTheLastButtonClicked();
        resetResult1();
        resetResult2();
    }
    private void resetResult1() {
        textViewLabelResult1.setText(R.string.result);
        editTextResult1.setText("");
    }
    private void resetResult2() {
        textViewLabelResult2.setText(R.string.result);
        editTextResult2.setText("");
    }
    private void setVisibilityToGoneResult2() {
        linearLayoutResult2Labels.setVisibility(View.GONE);
        linearLayoutResult2.setVisibility(View.GONE);
    }
    private void setVisibilityToVisibleResult2() {
        linearLayoutResult2Labels.setVisibility(View.VISIBLE);
        linearLayoutResult2.setVisibility(View.VISIBLE);
    }
    //endregion RESULT
}