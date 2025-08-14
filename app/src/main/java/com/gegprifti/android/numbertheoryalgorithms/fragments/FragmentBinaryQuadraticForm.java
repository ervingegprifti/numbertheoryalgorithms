package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.app.Activity;
import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.InputGroup;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.GridAdapter;
import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.settings.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.RowItem;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class FragmentBinaryQuadraticForm extends FragmentBase implements Callback {
    private final static String TAG = FragmentBinaryQuadraticForm.class.getSimpleName();
    // Navigation controls
    TextView textViewBackToAlgorithms;
    TextView textViewTitle;
    // Cache view state
    boolean isCompactInputView = false;
    // Extended input view
    LinearLayout linearLayoutExtendedInputView;
    TextView textViewLabelB;
    TextView textViewLabelElasticB;
    TextView textViewMinusB;
    TextView textViewPlusB;
    TextView textViewCopyB;
    TextView textViewPasteB;
    TextView textViewClearB;
    EditText editTextB;
    TextView textViewLabelD;
    TextView textViewLabelElasticD;
    TextView textViewMinusD;
    TextView textViewPlusD;
    TextView textViewCopyD;
    TextView textViewPasteD;
    TextView textViewClearD;
    EditText editTextD;
    TextView textViewLabelE;
    TextView textViewLabelElasticE;
    TextView textViewMinusE;
    TextView textViewPlusE;
    TextView textViewCopyE;
    TextView textViewPasteE;
    TextView textViewClearE;
    EditText editTextE;
    TextView textViewLabelF;
    TextView textViewLabelElasticF;
    TextView textViewMinusF;
    TextView textViewPlusF;
    TextView textViewCopyF;
    TextView textViewPasteF;
    TextView textViewClearF;
    EditText editTextF;
    // Compact input view
    LinearLayout linearLayoutCompactInputView;
    TextView textViewLabelCompactB;
    TextView textViewMinusCompactB;
    TextView textViewPlusCompactB;
    TextView textViewCopyCompactB;
    TextView textViewPasteCompactB;
    TextView textViewClearCompactB;
    EditText editTextCompactB;
    TextView textViewLabelCompactD;
    TextView textViewMinusCompactD;
    TextView textViewPlusCompactD;
    TextView textViewCopyCompactD;
    TextView textViewPasteCompactD;
    TextView textViewClearCompactD;
    EditText editTextCompactD;
    TextView textViewLabelCompactE;
    TextView textViewMinusCompactE;
    TextView textViewPlusCompactE;
    TextView textViewCopyCompactE;
    TextView textViewPasteCompactE;
    TextView textViewClearCompactE;
    EditText editTextCompactE;
    TextView textViewLabelCompactF;
    TextView textViewMinusCompactF;
    TextView textViewPlusCompactF;
    TextView textViewCopyCompactF;
    TextView textViewPasteCompactF;
    TextView textViewClearCompactF;
    EditText editTextCompactF;
    // Example run buttons
    LinearLayout linearLayoutExamplesContainer;
    Button buttonRunExample1;
    Button buttonRunExample2;
    Button buttonRunExample3;
    Button buttonRunExample4;
    Button buttonRunExample5;
    // Run buttons
    Button buttonRun;
    Button buttonRun1;
    Button buttonRun2;
    LinearLayout linearLayoutFModMContainer;
    Button buttonMMinus;
    Button buttonM;
    Button buttonMPlus;
    Button buttonRMinus;
    Button buttonR;
    Button buttonRPlus;
    // Result controls
    TextView textViewLabelResult;
    TextView textViewLabelElasticResult;
    TextView textViewExpandResult;
    TextView textViewCopyResult;
    TextView textViewClearResult;
    LinearLayout linearLayoutResultContainer;
    EditText editTextResult;
    LinearLayout linearLayoutResultGridContainer1;
    LinearLayout linearLayoutStaticColumnHeader1;
    ListView listViewResult1;
    LinearLayout linearLayoutResultGridContainer2;
    LinearLayout linearLayoutStaticColumnHeader2;
    ListView listViewResult2;
    // Menu
    MenuItem menuItemIncludeTrivialSolutions;
    MenuItem menuItemIncludeOnlyPositiveSolutions;
    MenuItem menuItemIncludeOnlyNegativeSolutions;
    // Flags to prevent recursive updates
    AtomicBoolean isUpdatingEditTextB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextD = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactD = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextE = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactE = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextF = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactF = new AtomicBoolean(false);

    // Define the parent fragment
    private TabFragmentAlgorithms tabFragmentAlgorithms;
    //public TabFragmentAlgorithms getFragmentTabAlgorithms() { return tabFragmentAlgorithms; }
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
            inflater = layoutInflater.inflate(R.layout.fragment_binary_quadratic_form, container, false);
            // Navigation controls
            this.textViewBackToAlgorithms = inflater.findViewById(R.id.TextViewBackToAlgorithms);
            this.textViewTitle = inflater.findViewById(R.id.TextViewTitle);
            // Extended input view
            linearLayoutExtendedInputView = inflater.findViewById(R.id.LinearLayoutExtendedInputView);
            textViewLabelB = inflater.findViewById(R.id.TextViewLabelB);
            textViewLabelElasticB = inflater.findViewById(R.id.TextViewLabelElasticB);
            textViewMinusB = inflater.findViewById(R.id.TextViewMinusB);
            textViewPlusB = inflater.findViewById(R.id.TextViewPlusB);
            textViewCopyB = inflater.findViewById(R.id.TextViewCopyB);
            textViewPasteB = inflater.findViewById(R.id.TextViewPasteB);
            textViewClearB = inflater.findViewById(R.id.TextViewClearB);
            editTextB = inflater.findViewById(R.id.EditTextB);
            textViewLabelD = inflater.findViewById(R.id.TextViewLabelD);
            textViewLabelElasticD = inflater.findViewById(R.id.TextViewLabelElasticD);
            textViewMinusD = inflater.findViewById(R.id.TextViewMinusD);
            textViewPlusD = inflater.findViewById(R.id.TextViewPlusD);
            textViewCopyD = inflater.findViewById(R.id.TextViewCopyD);
            textViewPasteD = inflater.findViewById(R.id.TextViewPasteD);
            textViewClearD = inflater.findViewById(R.id.TextViewClearD);
            editTextD = inflater.findViewById(R.id.EditTextD);
            textViewLabelE = inflater.findViewById(R.id.TextViewLabelE);
            textViewLabelElasticE = inflater.findViewById(R.id.TextViewLabelElasticE);
            textViewMinusE = inflater.findViewById(R.id.TextViewMinusE);
            textViewPlusE = inflater.findViewById(R.id.TextViewPlusE);
            textViewCopyE = inflater.findViewById(R.id.TextViewCopyE);
            textViewPasteE = inflater.findViewById(R.id.TextViewPasteE);
            textViewClearE = inflater.findViewById(R.id.TextViewClearE);
            editTextE = inflater.findViewById(R.id.EditTextE);
            textViewLabelF = inflater.findViewById(R.id.TextViewLabelF);
            textViewLabelElasticF = inflater.findViewById(R.id.TextViewLabelElasticF);
            textViewMinusF = inflater.findViewById(R.id.TextViewMinusF);
            textViewPlusF = inflater.findViewById(R.id.TextViewPlusF);
            textViewCopyF = inflater.findViewById(R.id.TextViewCopyF);
            textViewPasteF = inflater.findViewById(R.id.TextViewPasteF);
            textViewClearF = inflater.findViewById(R.id.TextViewClearF);
            editTextF = inflater.findViewById(R.id.EditTextF);
            // Compact input view
            linearLayoutCompactInputView = inflater.findViewById(R.id.LinearLayoutCompactInputView);
            textViewLabelCompactB = inflater.findViewById(R.id.TextViewLabelCompactB);
            textViewMinusCompactB = inflater.findViewById(R.id.TextViewMinusCompactB);
            textViewPlusCompactB = inflater.findViewById(R.id.TextViewPlusCompactB);
            textViewCopyCompactB = inflater.findViewById(R.id.TextViewCopyCompactB);
            textViewPasteCompactB = inflater.findViewById(R.id.TextViewPasteCompactB);
            textViewClearCompactB = inflater.findViewById(R.id.TextViewClearCompactB);
            editTextCompactB = inflater.findViewById(R.id.EditTextCompactB);
            textViewLabelCompactD = inflater.findViewById(R.id.TextViewLabelCompactD);
            textViewMinusCompactD = inflater.findViewById(R.id.TextViewMinusCompactD);
            textViewPlusCompactD = inflater.findViewById(R.id.TextViewPlusCompactD);
            textViewCopyCompactD = inflater.findViewById(R.id.TextViewCopyCompactD);
            textViewPasteCompactD = inflater.findViewById(R.id.TextViewPasteCompactD);
            textViewClearCompactD = inflater.findViewById(R.id.TextViewClearCompactD);
            editTextCompactD = inflater.findViewById(R.id.EditTextCompactD);
            textViewLabelCompactE = inflater.findViewById(R.id.TextViewLabelCompactE);
            textViewMinusCompactE = inflater.findViewById(R.id.TextViewMinusCompactE);
            textViewPlusCompactE = inflater.findViewById(R.id.TextViewPlusCompactE);
            textViewCopyCompactE = inflater.findViewById(R.id.TextViewCopyCompactE);
            textViewPasteCompactE = inflater.findViewById(R.id.TextViewPasteCompactE);
            textViewClearCompactE = inflater.findViewById(R.id.TextViewClearCompactE);
            editTextCompactE = inflater.findViewById(R.id.EditTextCompactE);
            textViewLabelCompactF = inflater.findViewById(R.id.TextViewLabelCompactF);
            textViewMinusCompactF = inflater.findViewById(R.id.TextViewMinusCompactF);
            textViewPlusCompactF = inflater.findViewById(R.id.TextViewPlusCompactF);
            textViewCopyCompactF = inflater.findViewById(R.id.TextViewCopyCompactF);
            textViewPasteCompactF = inflater.findViewById(R.id.TextViewPasteCompactF);
            textViewClearCompactF = inflater.findViewById(R.id.TextViewClearCompactF);
            editTextCompactF = inflater.findViewById(R.id.EditTextCompactF);
            // Example run buttons
            this.linearLayoutExamplesContainer = inflater.findViewById(R.id.LinearLayoutExamplesContainer);
            this.buttonRunExample1 = inflater.findViewById(R.id.ButtonRunExample1);
            this.buttonRunExample2 = inflater.findViewById(R.id.ButtonRunExample2);
            this.buttonRunExample3 = inflater.findViewById(R.id.ButtonRunExample3);
            this.buttonRunExample4 = inflater.findViewById(R.id.ButtonRunExample4);
            this.buttonRunExample5 = inflater.findViewById(R.id.ButtonRunExample5);
            // Run buttons
            this.buttonRun = inflater.findViewById(R.id.ButtonRun);
            this.buttonRun1 = inflater.findViewById(R.id.ButtonRun1);
            this.buttonRun2 = inflater.findViewById(R.id.ButtonRun2);
            this.buttonMMinus = inflater.findViewById(R.id.ButtonMMinus);
            this.buttonM = inflater.findViewById(R.id.ButtonM);
            this.buttonMPlus = inflater.findViewById(R.id.ButtonMPlus);
            this.buttonRMinus = inflater.findViewById(R.id.ButtonRMinus);
            this.buttonR = inflater.findViewById(R.id.ButtonR);
            this.buttonRPlus = inflater.findViewById(R.id.ButtonRPlus);
            // Result controls
            this.textViewLabelResult = inflater.findViewById(R.id.TextViewLabelResult);
            this.textViewLabelElasticResult = inflater.findViewById(R.id.TextViewLabelElasticResult);
            this.textViewExpandResult = inflater.findViewById(R.id.TextViewExpandResult);
            this.textViewCopyResult = inflater.findViewById(R.id.TextViewCopyResult);
            this.textViewClearResult = inflater.findViewById(R.id.TextViewClearResult);
            this.linearLayoutResultContainer = inflater.findViewById(R.id.LinearLayoutResultContainer);
            this.editTextResult = inflater.findViewById(R.id.EditTextResult);
            this.linearLayoutResultGridContainer1 = inflater.findViewById(R.id.LinearLayoutResultGridContainer1);
            this.linearLayoutFModMContainer = inflater.findViewById(R.id.LinearLayoutFModMContainer);
            this.linearLayoutStaticColumnHeader1 = inflater.findViewById(R.id.LinearLayoutStaticColumnHeader1);
            this.listViewResult1 = inflater.findViewById(R.id.ListViewResult1);
            this.linearLayoutResultGridContainer2 = inflater.findViewById(R.id.LinearLayoutResultGridContainer2);
            this.linearLayoutStaticColumnHeader2 = inflater.findViewById(R.id.LinearLayoutStaticColumnHeader2);
            this.listViewResult2 = inflater.findViewById(R.id.ListViewResult2);

            // Constrain extended input
            editTextB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextD.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextE.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextF.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            // Constrain compact input
            editTextCompactB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactD.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactE.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactF.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Navigation vents
            this.textViewBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });

            // Extended input events
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
                    String labelText = "b" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelB.setText(labelText);
                    resetResult(false);
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
            editTextD.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextD.get()) return; // editTextD is locked
                    // Other work
                    String labelText = "d" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelD.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactD
                    isUpdatingEditTextCompactD.set(true); // Lock editTextCompactD
                    try {
                        editTextCompactD.setText(s.toString());
                        // editTextCompactD.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactD.set(false); // Unlock editTextCompactD
                    }
                }
            });
            editTextE.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextE.get()) return; // editTextE is locked
                    // Other work
                    String labelText = "e" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelE.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactE
                    isUpdatingEditTextCompactE.set(true); // Lock editTextCompactE
                    try {
                        editTextCompactE.setText(s.toString());
                        // editTextCompactE.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactE.set(false); // Unlock editTextCompactE
                    }
                }
            });
            editTextF.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextF.get()) return; // editTextF is locked
                    // Other work
                    String labelText = "f" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelF.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactF
                    isUpdatingEditTextCompactF.set(true); // Lock editTextCompactF
                    try {
                        editTextCompactF.setText(s.toString());
                        // editTextCompactF.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactF.set(false); // Unlock editTextCompactF
                    }
                }
            });

            // Compact input events
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
                    resetResult(false);
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
            editTextCompactD.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactD.get()) return; // editTextCompactD is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextD
                    isUpdatingEditTextD.set(true); // Lock editTextD
                    try {
                        editTextD.setText(s.toString());
                        // editTextD.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextD.set(false); // unlock editTextD
                    }
                }
            });
            editTextCompactE.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactE.get()) return; // editTextCompactE is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextE
                    isUpdatingEditTextE.set(true); // Lock editTextE
                    try {
                        editTextE.setText(s.toString());
                        // editTextE.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextE.set(false); // unlock editTextE
                    }
                }
            });
            editTextCompactF.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactF.get()) return; // editTextCompactF is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextF
                    isUpdatingEditTextF.set(true); // Lock editTextF
                    try {
                        editTextF.setText(s.toString());
                        // editTextF.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextF.set(false); // unlock editTextF
                    }
                }
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

            // Extended input d clipboard button events
            textViewMinusD.setOnClickListener(v -> {
                decreaseByOne(editTextD);
                resetAllAndSelectTheLastButtonClicked(textViewMinusD);
            });
            textViewPlusD.setOnClickListener(v -> {
                increaseByOne(editTextD);
                resetAllAndSelectTheLastButtonClicked(textViewPlusD);
            });
            textViewCopyD.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextD);
                resetAllAndSelectTheLastButtonClicked(textViewCopyD);
            });
            textViewPasteD.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextD);
                resetAllAndSelectTheLastButtonClicked(textViewPasteD);
            });
            textViewClearD.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextD);
                resetAllAndSelectTheLastButtonClicked(textViewClearD);
            });

            // Extended input e clipboard button events
            textViewMinusE.setOnClickListener(v -> {
                decreaseByOne(editTextE);
                resetAllAndSelectTheLastButtonClicked(textViewMinusE);
            });
            textViewPlusE.setOnClickListener(v -> {
                increaseByOne(editTextE);
                resetAllAndSelectTheLastButtonClicked(textViewPlusE);
            });
            textViewCopyE.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextE);
                resetAllAndSelectTheLastButtonClicked(textViewCopyE);
            });
            textViewPasteE.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextE);
                resetAllAndSelectTheLastButtonClicked(textViewPasteE);
            });
            textViewClearE.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextE);
                resetAllAndSelectTheLastButtonClicked(textViewClearE);
            });

            // Extended input f clipboard button events
            textViewMinusF.setOnClickListener(v -> {
                decreaseByOne(editTextF);
                resetAllAndSelectTheLastButtonClicked(textViewMinusF);
            });
            textViewPlusF.setOnClickListener(v -> {
                increaseByOne(editTextF);
                resetAllAndSelectTheLastButtonClicked(textViewPlusF);
            });
            textViewCopyF.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextF);
                resetAllAndSelectTheLastButtonClicked(textViewCopyF);
            });
            textViewPasteF.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextF);
                resetAllAndSelectTheLastButtonClicked(textViewPasteF);
            });
            textViewClearF.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextF);
                resetAllAndSelectTheLastButtonClicked(textViewClearF);
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

            // Compact input d clipboard button events
            textViewMinusCompactD.setOnClickListener(v -> {
                decreaseByOne(editTextCompactD);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactD);
            });
            textViewPlusCompactD.setOnClickListener(v -> {
                increaseByOne(editTextCompactD);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactD);
            });
            textViewCopyCompactD.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactD);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactD);
            });
            textViewPasteCompactD.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactD);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactD);
            });
            textViewClearCompactD.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactD);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactD);
            });

            // Compact input e clipboard button events
            textViewMinusCompactE.setOnClickListener(v -> {
                decreaseByOne(editTextCompactE);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactE);
            });
            textViewPlusCompactE.setOnClickListener(v -> {
                increaseByOne(editTextCompactE);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactE);
            });
            textViewCopyCompactE.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactE);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactE);
            });
            textViewPasteCompactE.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactE);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactE);
            });
            textViewClearCompactE.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactE);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactE);
            });

            // Compact input f clipboard button events
            textViewMinusCompactF.setOnClickListener(v -> {
                decreaseByOne(editTextCompactF);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactF);
            });
            textViewPlusCompactF.setOnClickListener(v -> {
                increaseByOne(editTextCompactF);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactF);
            });
            textViewCopyCompactF.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactF);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactF);
            });
            textViewPasteCompactF.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactF);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactF);
            });
            textViewClearCompactF.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactF);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactF);
            });

            // Example run button events
            this.buttonRunExample1.setOnClickListener(v -> onButtonRunExample1(container));
            this.buttonRunExample2.setOnClickListener(v -> onButtonRunExample2(container));
            this.buttonRunExample3.setOnClickListener(v -> onButtonRunExample3(container));
            this.buttonRunExample4.setOnClickListener(v -> onButtonRunExample4(container));
            this.buttonRunExample5.setOnClickListener(v -> onButtonRunExample5(container));

            // Run button events
            this.buttonRun.setOnClickListener(v -> onButtonRun(container, buttonRun, false));
            this.buttonRun1.setOnClickListener(v -> onButtonRun1(container));
            this.buttonRun2.setOnClickListener(v -> onButtonRun2(container));

            // Run button events
            this.buttonMMinus.setOnClickListener(v -> {
                String mText = buttonM.getText().toString();
                if (!mText.isEmpty()) {
                    int m = Integer.parseInt(mText);
                    if (m <= 1) { // m value should not be 0 otherwise we get "modulus not positive" or "m.signum() <= 0" exceptions when doing m.mod(r)
                        buttonM.setText("");
                    } else {
                        m = m - 1;
                        buttonM.setText(String.valueOf(m));
                    }
                }
                calculateFModM();
            });
            this.buttonMPlus.setOnClickListener(v -> {
                String mText = buttonM.getText().toString();
                if (mText.isEmpty()) {
                    buttonM.setText("1"); // m value should not be 0 otherwise we get "modulus not positive" or "m.signum() <= 0" exceptions when doing m.mod(r)
                } else {
                    int m = Integer.parseInt(mText);
                    m = m + 1;
                    buttonM.setText(String.valueOf(m));
                }
                calculateFModM();
            });
            this.buttonRMinus.setOnClickListener(v -> {
                String rText = buttonR.getText().toString();
                if (!rText.isEmpty()) {
                    int r = Integer.parseInt(rText);
                    if (r <= 0) {
                        buttonR.setText("");
                    } else {
                        r -= 1;
                        buttonR.setText(String.valueOf(r));
                    }
                }
                calculateFModM();
            });
            this.buttonRPlus.setOnClickListener(v -> {
                String rText = buttonR.getText().toString();
                if (rText.isEmpty()) {
                    buttonR.setText("0");
                } else {
                    String mText = buttonM.getText().toString();
                    if (mText.isEmpty()) {
                        buttonR.setText("");
                    } else {
                        int m = Integer.parseInt(mText);
                        int r = Integer.parseInt(rText);
                        r += 1;
                        if (r < m) {
                            buttonR.setText(String.valueOf(r));
                        }
                    }
                }
                calculateFModM();
            });

            // Result clipboard button events
            this.textViewExpandResult.setOnClickListener(v -> {
                expandResult();
                resetAllAndSelectTheLastButtonClicked(textViewExpandResult);
            });
            this.textViewCopyResult.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextResult);
                resetAllAndSelectTheLastButtonClicked(textViewCopyResult);
            });
            this.textViewClearResult.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextResult);
                resetResult(false);
                resetAllAndSelectTheLastButtonClicked(textViewClearResult);
            });

            // Result events
            initDoubleTapDetector(editTextResult);
            editTextResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.length() == 0){
                        textViewExpandResult.setVisibility(View.GONE);
                    } else {
                        textViewExpandResult.setVisibility(View.VISIBLE);
                    }
                }
            });
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        return inflater;
    }
    //endregion CREATE


    @Override
    protected void fireOnDoubleTap(View view) {
        if (view == editTextResult){
            expandResult();
            resetAllAndSelectTheLastButtonClicked(textViewExpandResult);
        }
    }
    private void expandResult() {
        if (linearLayoutResultContainer.getVisibility() == View.VISIBLE) {
            PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), editTextResult.getText());
            popupResult.show();
        } else if (linearLayoutResultGridContainer1.getVisibility() == View.VISIBLE) {
            PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), linearLayoutStaticColumnHeader1, listViewResult1);
            popupResult.show();
        } else if (linearLayoutResultGridContainer2.getVisibility() == View.VISIBLE) {
            PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), linearLayoutStaticColumnHeader2, listViewResult2);
            popupResult.show();
        }
    }


    // region MENU
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        try {
            menuInflater.inflate(R.menu.menu_fragment_binary_quadratic_form, menu);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
        this.menuItemIncludeTrivialSolutions = menu.findItem(R.id.quadratic_form_menu_include_trivial_solutions);
        this.menuItemIncludeOnlyPositiveSolutions = menu.findItem(R.id.quadratic_form_menu_include_only_positive_solutions);
        this.menuItemIncludeOnlyNegativeSolutions = menu.findItem(R.id.quadratic_form_menu_include_only_negative_solutions);
        // Get the value from shared preferences.
        boolean includeTrivialSolutions = UserSettings.getBQFIncludeTrivialSolutions(requireContext());
        menuItemIncludeTrivialSolutions.setChecked(includeTrivialSolutions);
        boolean includeOnlyPositiveSolutions = UserSettings.getBQFIncludeOnlyPositiveSolutions(requireContext());
        menuItemIncludeOnlyPositiveSolutions.setChecked(includeOnlyPositiveSolutions);
        boolean includeOnlyNegativeSolutions = UserSettings.getBQFIncludeOnlyNegativeSolutions(requireContext());
        menuItemIncludeOnlyNegativeSolutions.setChecked(includeOnlyNegativeSolutions);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        try {
            // Handle menu item clicks here based on their ID.
            int id = menuItem.getItemId();
            if (id == R.id.quadratic_form_menu_include_trivial_solutions) {
                boolean isChecked = !menuItemIncludeTrivialSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setBQFIncludeTrivialSolutions(requireContext(), isChecked);
                menuItemIncludeTrivialSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_include_only_positive_solutions) {
                boolean isChecked = !menuItemIncludeOnlyPositiveSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setBQFIncludeOnlyPositiveSolutions(requireContext(), isChecked);
                menuItemIncludeOnlyPositiveSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_include_only_negative_solutions) {
                boolean isChecked = !menuItemIncludeOnlyNegativeSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setBQFIncludeOnlyNegativeSolutions(requireContext(), isChecked);
                menuItemIncludeOnlyNegativeSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_1) {
                String b = "8";
                String d = "3";
                String e = "3";
                String f = "88";
                String m = "8";
                String r = "0";
                this.editTextB.setText(b);
                this.editTextD.setText(d);
                this.editTextE.setText(e);
                this.editTextF.setText(f);
                this.buttonM.setText(m);
                this.buttonR.setText(r);
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_2) {
                String b = "16";
                String d = "7";
                String e = "3";
                String f = "113";
                String m = "16";
                String r = "1";
                this.editTextB.setText(b);
                this.editTextD.setText(d);
                this.editTextE.setText(e);
                this.editTextF.setText(f);
                this.buttonM.setText(m);
                this.buttonR.setText(r);
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_3) {
                String b = "8";
                String d = "7";
                String e = "7";
                String f = "83";
                String m = "8";
                String r = "3";
                this.editTextB.setText(b);
                this.editTextD.setText(d);
                this.editTextE.setText(e);
                this.editTextF.setText(f);
                this.buttonM.setText(m);
                this.buttonR.setText(r);
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_4) {
                String b = "16";
                String d = "15";
                String e = "11";
                String f = "104";
                String m = "16";
                String r = "8";
                this.editTextB.setText(b);
                this.editTextD.setText(d);
                this.editTextE.setText(e);
                this.editTextF.setText(f);
                this.buttonM.setText(m);
                this.buttonR.setText(r);
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_4));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_5) {
                String b = "8";
                String d = "3";
                String e = "5";
                String f = "14";
                String m = "8";
                String r = "6";
                this.editTextB.setText(b);
                this.editTextD.setText(d);
                this.editTextE.setText(e);
                this.editTextF.setText(f);
                this.buttonM.setText(m);
                this.buttonR.setText(r);
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_5));
                resetResult(true);
                return true;
            }
            if (id == R.id.menu_documentation) {
                DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.BINARY_QUADRATIC_FORM_PDF).show(getParentFragmentManager(), "BINARY_QUADRATIC_FORM_PDF");
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


    private void refreshShowInputDecreaseIncreaseButtons() {
        try {
            boolean showInputDecreaseIncreaseButtons = UserSettings.getShowInputDecreaseIncreaseButtons(requireContext());
            if (showInputDecreaseIncreaseButtons) {
                textViewMinusB.setVisibility(View.VISIBLE);
                textViewPlusB.setVisibility(View.VISIBLE);
                textViewMinusCompactB.setVisibility(View.VISIBLE);
                textViewPlusCompactB.setVisibility(View.VISIBLE);
                textViewMinusD.setVisibility(View.VISIBLE);
                textViewPlusD.setVisibility(View.VISIBLE);
                textViewMinusCompactD.setVisibility(View.VISIBLE);
                textViewPlusCompactD.setVisibility(View.VISIBLE);
                textViewMinusE.setVisibility(View.VISIBLE);
                textViewPlusE.setVisibility(View.VISIBLE);
                textViewMinusCompactE.setVisibility(View.VISIBLE);
                textViewPlusCompactE.setVisibility(View.VISIBLE);
                textViewMinusF.setVisibility(View.VISIBLE);
                textViewPlusF.setVisibility(View.VISIBLE);
                textViewMinusCompactF.setVisibility(View.VISIBLE);
                textViewPlusCompactF.setVisibility(View.VISIBLE);
            } else {
                textViewMinusB.setVisibility(View.GONE);
                textViewPlusB.setVisibility(View.GONE);
                textViewMinusCompactB.setVisibility(View.GONE);
                textViewPlusCompactB.setVisibility(View.GONE);
                textViewMinusD.setVisibility(View.GONE);
                textViewPlusD.setVisibility(View.GONE);
                textViewMinusCompactD.setVisibility(View.GONE);
                textViewPlusCompactD.setVisibility(View.GONE);
                textViewMinusE.setVisibility(View.GONE);
                textViewPlusE.setVisibility(View.GONE);
                textViewMinusCompactE.setVisibility(View.GONE);
                textViewPlusCompactE.setVisibility(View.GONE);
                textViewMinusF.setVisibility(View.GONE);
                textViewPlusF.setVisibility(View.GONE);
                textViewMinusCompactF.setVisibility(View.GONE);
                textViewPlusCompactF.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.linearLayoutExamplesContainer.getVisibility() == View.VISIBLE;
            boolean hideExampleButtons = UserSettings.getHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonRun.setText(requireContext().getText(R.string.binary_quadratic_form_run_short));
                this.linearLayoutExamplesContainer.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonRun.setText(requireContext().getText(R.string.binary_quadratic_form_run_short));
                this.linearLayoutExamplesContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshBiggerControls() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons
            ControlDisplay.setClipboardButtonFontSize(textViewMinusB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearF, biggerControls);
            //
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactD, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactE, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactF, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactF, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult, biggerControls);
            // Extended input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticB, biggerControls);
            ControlDisplay.setInputFontSize(editTextB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelD, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticD, biggerControls);
            ControlDisplay.setInputFontSize(editTextD, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelE, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticE, biggerControls);
            ControlDisplay.setInputFontSize(editTextE, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelF, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticF, biggerControls);
            ControlDisplay.setInputFontSize(editTextF, biggerControls);
            // Compact input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactB, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactD, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactD, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactE, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactE, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactF, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactF, biggerControls);
            // Example run buttons
            ControlDisplay.setButtonFontSize(buttonRunExample1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample3, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample4, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample5, biggerControls);
            // Run buttons
            ControlDisplay.setButtonFontSize(buttonRun, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRun1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRun2, biggerControls);
            // Run Buttons
            ControlDisplay.setButtonFontSize(buttonMMinus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonM, biggerControls);
            ControlDisplay.setButtonFontSize(buttonMPlus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRMinus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonR, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRPlus, biggerControls);
            // Output result
            ControlDisplay.setInputLabelFontSize(this.textViewLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewLabelElasticResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshBiggerResultDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerResultDisplay(requireContext());
            // Output result
            ControlDisplay.setOutputFontSize(editTextResult, biggerControls);
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
        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString, Html.FROM_HTML_MODE_LEGACY);
                editTextResult.setText(resultFromHtml);
            }
        }

        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM_1) {
            if (progressStatus == ProgressStatus.CANCELED) {
                cancelShowResult(listViewResult1);
            } else {
                @SuppressWarnings("unchecked")
                List<List<RowItem>> sqfResultList1 = (List<List<RowItem>>)result;
                showResult1(sqfResultList1);
            }
        }

        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM_2) {
            if (progressStatus == ProgressStatus.CANCELED) {
                cancelShowResult(listViewResult2);
            } else {
                @SuppressWarnings("unchecked")
                List<List<RowItem>> sqfResultList2 = (List<List<RowItem>>)result;
                showResult2(sqfResultList2);
            }
        }
    }
    private void cancelShowResult(ListView listView) {
        ArrayList<String> listItems=new ArrayList<>();
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(requireContext(), R.layout.row_item_canceled, R.id.RowItemCanceled, listItems);
        setListViewAdapter(listView, adapter);
        listItems.add(requireContext().getResources().getString(R.string.canceled));
        adapter.notifyDataSetChanged();
    }
    private void showResult1(List<List<RowItem>> rows) {
        try {
            if(rows == null || rows.isEmpty()) {
                return;
            }

            // Get max text length per each column.
            List<String> columnsMaxText = new ArrayList<>();
            for(int r = 0; r < rows.size(); r++) {
                List<RowItem> row = rows.get(r);
                for(int c = 0; c < row.size(); c++) {
                    RowItem rowItem = row.get(c);
                    if (r == 0) {
                        // Populate with the first row column items.
                        columnsMaxText.add(rowItem.getValue());
                    } else {
                        String existingMaxText = columnsMaxText.get(c);
                        if (rowItem.getValue().length() > existingMaxText.length()) {
                            columnsMaxText.set(c, rowItem.getValue());
                        }
                    }
                }
            }

            // Get the value from shared preferences.
            boolean biggerResultDisplay = UserSettings.getBiggerResultDisplay(requireContext());

            // Get and set the max row item width per each column.
            List<Integer> rowItemWidths = new ArrayList<>();
            for (int c = 0; c < columnsMaxText.size(); c++) {
                // Start the pre-calculate
                LayoutInflater layoutInflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                TextView textViewTemp;
                int rowItemResource = biggerResultDisplay ? R.layout.row_item_big : R.layout.row_item_small;
                textViewTemp = (TextView) layoutInflater.inflate(rowItemResource, null);
                String maxText = columnsMaxText.get(c);
                textViewTemp.setText(maxText);
                //must call measure!
                textViewTemp.measure(0, 0);
                // get width
                int maxColumnWidth = textViewTemp.getMeasuredWidth();
                rowItemWidths.add(maxColumnWidth + 20); // Just add some extra space
            }
            int rowItemWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
            int rowItemHeight = LinearLayout.LayoutParams.WRAP_CONTENT;

            // Set the listview row space
            if(biggerResultDisplay) {
                listViewResult1.setDividerHeight((int) UIHelper.convertDpToPixel(4F, requireContext()));
            } else {
                listViewResult1.setDividerHeight((int) UIHelper.convertDpToPixel(1F, requireContext()));
            }

            // Create and set the adapter.
            GridAdapter adapter = new GridAdapter(requireContext(), linearLayoutStaticColumnHeader1, rows, rowItemWidth, rowItemWidths, rowItemHeight, null, biggerResultDisplay);
            setListViewAdapter(listViewResult1, adapter);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void showResult2(List<List<RowItem>> rows) {
        try {
            if(rows == null || rows.isEmpty()) {
                return;
            }

            // Get the values from shared preferences.
            boolean biggerResultDisplay = UserSettings.getBiggerResultDisplay(requireContext());
            boolean squareResultDisplay = UserSettings.getSquareResultDisplay(requireContext());

            BigInteger m = null;
            BigInteger r = null;
            String mText = this.buttonM.getText().toString();
            if (!mText.isEmpty()) {
                m = new BigInteger(mText);
            }
            String rText = this.buttonR.getText().toString();
            if (!rText.isEmpty()) {
                r = new BigInteger(rText);
            }

            // Get the last item in the last row
            List<RowItem> lastRow = rows.get(rows.size()-1);
            String lastRowLastValue = lastRow.get(lastRow.size()-1).getValue();
            int maxTextLength = lastRowLastValue.length();
            maxTextLength = maxTextLength + 1; // Add 1 for easy reading.
            // Construct the maxText. if maxTextLength = 6 the maxText = "000000"
            StringBuilder maxText = new StringBuilder(maxTextLength);
            for(int i = 0; i < maxTextLength; i++) {
                maxText.append("0");
            }
            LayoutInflater layoutInflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TextView textViewTemp;
            int rowItemResource = biggerResultDisplay ? R.layout.row_item_big : R.layout.row_item_small;
            textViewTemp = (TextView) layoutInflater.inflate(rowItemResource, null);
            textViewTemp.setText(maxText.toString());
            textViewTemp.measure(0, 0); //must call measure!
            int rowItemWidth = textViewTemp.getMeasuredWidth();
            int rowItemHeight = squareResultDisplay ? rowItemWidth : LinearLayout.LayoutParams.WRAP_CONTENT;

            // Set the listview row space.
            if(biggerResultDisplay) {
                listViewResult2.setDividerHeight((int) UIHelper.convertDpToPixel(4F, requireContext()));
            } else {
                listViewResult2.setDividerHeight((int) UIHelper.convertDpToPixel(1F, requireContext()));
            }

            // Create and set the adapter.
            GridAdapter adapter = new GridAdapter(requireContext(), linearLayoutStaticColumnHeader2, rows, rowItemWidth, null, rowItemHeight, null, biggerResultDisplay);
            showResultFModM2(adapter, m, r);
            setListViewAdapter(listViewResult2, adapter);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void setListViewAdapter(ListView listView, ListAdapter listAdapter) {
        listView.setAdapter(listAdapter);
        if (listAdapter == null) {
            textViewExpandResult.setVisibility(View.GONE);
        } else {
            textViewExpandResult.setVisibility(View.VISIBLE);
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void onButtonRunExample1(ViewGroup container) {
        try {
            String b = "8";
            String d = "3";
            String e = "3";
            String f = "88";
            String m = "8";
            String r = "0";
            this.editTextB.setText(b);
            this.editTextD.setText(d);
            this.editTextE.setText(e);
            this.editTextF.setText(f);
            this.buttonM.setText(m);
            this.buttonR.setText(r);
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
            onButtonRun(container, buttonRunExample1, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample2(ViewGroup container) {
        try {
            String b = "16";
            String d = "7";
            String e = "3";
            String f = "113";
            String m = "16";
            String r = "1";
            this.editTextB.setText(b);
            this.editTextD.setText(d);
            this.editTextE.setText(e);
            this.editTextF.setText(f);
            this.buttonM.setText(m);
            this.buttonR.setText(r);
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
            onButtonRun(container, buttonRunExample2, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample3(ViewGroup container) {
        try {
            String b = "8";
            String d = "7";
            String e = "7";
            String f = "83";
            String m = "8";
            String r = "3";
            this.editTextB.setText(b);
            this.editTextD.setText(d);
            this.editTextE.setText(e);
            this.editTextF.setText(f);
            this.buttonM.setText(m);
            this.buttonR.setText(r);
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
            onButtonRun(container, buttonRunExample3, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample4(ViewGroup container) {
        try {
            String b = "16";
            String d = "15";
            String e = "11";
            String f = "104";
            String m = "16";
            String r = "8";
            this.editTextB.setText(b);
            this.editTextD.setText(d);
            this.editTextE.setText(e);
            this.editTextF.setText(f);
            this.buttonM.setText(m);
            this.buttonR.setText(r);
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_4));
            onButtonRun(container, buttonRunExample4, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample5(ViewGroup container) {
        try {
            String b = "8";
            String d = "3";
            String e = "5";
            String f = "14";
            String m = "8";
            String r = "6";
            this.editTextB.setText(b);
            this.editTextD.setText(d);
            this.editTextE.setText(e);
            this.editTextF.setText(f);
            this.buttonM.setText(m);
            this.buttonR.setText(r);
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_5));
            onButtonRun(container, buttonRunExample5, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private InputGroup getInputGroupB() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelB, "b", textViewLabelElasticB)
                .setInput(editTextB)
                .setCompactControls(textViewLabelCompactB, editTextCompactB)
                .build();
    }
    private InputGroup getInputGroupD() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelD, "d", textViewLabelElasticD)
                .setInput(editTextD)
                .setCompactControls(textViewLabelCompactD, editTextCompactD)
                .build();
    }
    private InputGroup getInputGroupE() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelE, "e", textViewLabelElasticE)
                .setInput(editTextE)
                .setCompactControls(textViewLabelCompactE, editTextCompactE)
                .build();
    }
    private InputGroup getInputGroupF() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelF, "f", textViewLabelElasticF)
                .setInput(editTextF)
                .setCompactControls(textViewLabelCompactF, editTextCompactF)
                .build();
    }


    private void onButtonRun(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            resetResult(skipLabelResult);

            // Check.
            InputGroup inputGroupB = getInputGroupB();
            InputGroup inputGroupD = getInputGroupD();
            InputGroup inputGroupE = getInputGroupE();
            InputGroup inputGroupF = getInputGroupF();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupD)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupE)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupF)) {
                return;
            }

            // Get numbers
            BigInteger b = new BigInteger(editTextB.getText().toString());
            BigInteger d = new BigInteger(editTextD.getText().toString());
            BigInteger e = new BigInteger(editTextE.getText().toString());
            BigInteger f = new BigInteger(editTextF.getText().toString());

            // Check b ≠ 0
            if (b.compareTo(BigInteger.ZERO) == 0) {
                UIHelper.showCustomToastLight(requireContext(), "The value of b must be other than 0");
                return;
            }

            setResultVisibilityFromButtonRun(skipLabelResult);

            // Before action performing.
            beforeActionPerforming(button);

            boolean includeTrivialSolutions = UserSettings.getBQFIncludeTrivialSolutions(requireContext());
            boolean includeOnlyPositiveSolutions = UserSettings.getBQFIncludeOnlyPositiveSolutions(requireContext());
            boolean includeOnlyNegativeSolutions = UserSettings.getBQFIncludeOnlyNegativeSolutions(requireContext());

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM, this);
            algorithmParameters.setInput1(b);
            algorithmParameters.setInput2(d);
            algorithmParameters.setInput3(e);
            algorithmParameters.setInput4(f);
            algorithmParameters.setIncludeTrivialSolutions(includeTrivialSolutions);
            algorithmParameters.setIncludeOnlyPositiveSolutions(includeOnlyPositiveSolutions);
            algorithmParameters.setIncludeOnlyNegativeSolutions(includeOnlyNegativeSolutions);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRun1(ViewGroup container) {
        try {
            resetResult(false);

            // Check.
            InputGroup inputGroupB = getInputGroupB();
            InputGroup inputGroupD = getInputGroupD();
            InputGroup inputGroupE = getInputGroupE();
            InputGroup inputGroupF = getInputGroupF();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupD)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupE)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupF)) {
                return;
            }

            // Get numbers
            BigInteger b = new BigInteger(editTextB.getText().toString());
            BigInteger d = new BigInteger(editTextD.getText().toString());
            BigInteger e = new BigInteger(editTextE.getText().toString());
            BigInteger f = new BigInteger(editTextF.getText().toString());

            // Check b ≠ 0
            if (b.compareTo(BigInteger.ZERO) == 0) {
                UIHelper.showCustomToastLight(requireContext(), "The value of b must be other than 0");
                return;
            }

            setResultVisibilityFromButtonRun1();

            // Before action performing.
            beforeActionPerforming(buttonRun1);

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM_1, this);
            algorithmParameters.setInput1(b);
            algorithmParameters.setInput2(d);
            algorithmParameters.setInput3(e);
            algorithmParameters.setInput4(f);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRun2(ViewGroup container) {
        try {
            resetResult(false);

            // Check.
            InputGroup inputGroupB = getInputGroupB();
            InputGroup inputGroupD = getInputGroupD();
            InputGroup inputGroupE = getInputGroupE();
            InputGroup inputGroupF = getInputGroupF();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupD)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupE)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupF)) {
                return;
            }

            // Get numbers
            BigInteger a = BigInteger.ZERO;
            BigInteger b = new BigInteger(editTextB.getText().toString());
            BigInteger c = BigInteger.ZERO;
            BigInteger d = new BigInteger(editTextD.getText().toString());
            BigInteger e = new BigInteger(editTextE.getText().toString());
            BigInteger f = new BigInteger(editTextF.getText().toString());

            setResultVisibilityFromButtonRun2();

            // Before action performing.
            beforeActionPerforming(buttonRun2);

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM_2, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(c);
            algorithmParameters.setInput4(d);
            algorithmParameters.setInput5(e);
            algorithmParameters.setInput6(f);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void calculateFModM() {
        if (this.listViewResult2.getAdapter() != null) {
            GridAdapter gridAdapter = (GridAdapter) this.listViewResult2.getAdapter();

            BigInteger m = null;
            BigInteger r = null;

            String mText = this.buttonM.getText().toString();
            String rText = this.buttonR.getText().toString();

            if (!mText.isEmpty()) {
                m = new BigInteger(mText);
            }
            if (!rText.isEmpty()) {
                r = new BigInteger(rText);
            }

            showResultFModM2(gridAdapter, m, r);
        }
    }
    public static void showResultFModM2(GridAdapter gridAdapter, BigInteger m, BigInteger r) {
        try {
            if (gridAdapter == null) {
                return;
            }

            if (m != null && r == null) {
                // Display f (mod m) and reset if highlighted.
                for(int rIndex = 0; rIndex < gridAdapter.getCount(); rIndex++) {
                    List<RowItem> row = gridAdapter.getItem(rIndex);
                    for(int iIndex = 0; iIndex < row.size(); iIndex++) {
                        RowItem item = row.get(iIndex);
                        if (!item.getIsHeader()) {
                            String value1 = item.getValue1();
                            BigInteger f = new BigInteger(value1);
                            BigInteger fmodm1 = f.mod(m);
                            String value2 = fmodm1.toString();
                            item.setValue2(value2);
                            item.setValueToDisplay(RowItem.ValueToDisplay.VALUE_2);
                            if (item.getIsSelected()) {
                                item.setValueStyle(RowItem.ValueStyle.ORANGE);
                            } else {
                                // Reset if highlighted.
                                item.setValueStyle(RowItem.ValueStyle.DEFAULT);
                            }
                        }
                    }
                }
            } else if (m != null && r != null) {
                // Display f and highlight f (mod m) = r
                for(int rIndex = 0; rIndex < gridAdapter.getCount(); rIndex++) {
                    List<RowItem> row = gridAdapter.getItem(rIndex);
                    for(int iIndex = 0; iIndex < row.size(); iIndex++) {
                        RowItem item = row.get(iIndex);
                        if (!item.getIsHeader()) {
                            if (item.getValueToDisplay() != RowItem.ValueToDisplay.VALUE_1) {
                                item.setValueToDisplay(RowItem.ValueToDisplay.VALUE_1);
                            }
                            String value1 = item.getValue1();
                            BigInteger f = new BigInteger(value1);
                            BigInteger fmodm1 = f.mod(m);
                            if (fmodm1.compareTo(r) == 0) {
                                // Highlight.
                                if (item.getIsSelected()) {
                                    item.setValueStyle(RowItem.ValueStyle.ORANGE_STRESSED);
                                } else {
                                    item.setValueStyle(RowItem.ValueStyle.YELLOW_STRESSED);
                                }
                            } else {
                                // Reset if highlighted.
                                if (item.getIsSelected()) {
                                    item.setValueStyle(RowItem.ValueStyle.ORANGE);
                                } else {
                                    item.setValueStyle(RowItem.ValueStyle.DEFAULT);
                                }
                            }
                        }
                    }
                }
            } else {
                // Display f and reset if highlighted.
                for(int rIndex = 0; rIndex < gridAdapter.getCount(); rIndex++) {
                    List<RowItem> row = gridAdapter.getItem(rIndex);
                    for(int iIndex = 0; iIndex < row.size(); iIndex++) {
                        RowItem item = row.get(iIndex);
                        if (!item.getIsHeader()) {
                            if (item.getValueToDisplay() != RowItem.ValueToDisplay.VALUE_1) {
                                item.setValueToDisplay(RowItem.ValueToDisplay.VALUE_1);
                            }
                            if (item.getIsSelected()) {
                                item.setValueStyle(RowItem.ValueStyle.ORANGE);
                            } else {
                                // Reset if highlighted.
                                item.setValueStyle(RowItem.ValueStyle.DEFAULT);
                            }
                        }
                    }
                }
            }
            gridAdapter.refresh();
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
        editTextB.clearFocus();
        editTextD.clearFocus();
        editTextE.clearFocus();
        editTextF.clearFocus();
        editTextCompactB.clearFocus();
        editTextCompactD.clearFocus();
        editTextCompactE.clearFocus();
        editTextCompactF.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastButtonClicked() {
        resetAllAndSelectTheLastButtonClicked(null);
    }
    private void resetAllAndSelectTheLastButtonClicked(View view) {
        //
        textViewMinusB.setSelected(false);
        textViewPlusB.setSelected(false);
        textViewCopyB.setSelected(false);
        textViewPasteB.setSelected(false);
        textViewClearB.setSelected(false);
        textViewMinusD.setSelected(false);
        textViewPlusD.setSelected(false);
        textViewCopyD.setSelected(false);
        textViewPasteD.setSelected(false);
        textViewClearD.setSelected(false);
        textViewMinusE.setSelected(false);
        textViewPlusE.setSelected(false);
        textViewCopyE.setSelected(false);
        textViewPasteE.setSelected(false);
        textViewClearE.setSelected(false);
        textViewMinusF.setSelected(false);
        textViewPlusF.setSelected(false);
        textViewCopyF.setSelected(false);
        textViewPasteF.setSelected(false);
        textViewClearF.setSelected(false);
        //
        textViewMinusCompactB.setSelected(false);
        textViewPlusCompactB.setSelected(false);
        textViewCopyCompactB.setSelected(false);
        textViewPasteCompactB.setSelected(false);
        textViewClearCompactB.setSelected(false);
        textViewMinusCompactD.setSelected(false);
        textViewPlusCompactD.setSelected(false);
        textViewCopyCompactD.setSelected(false);
        textViewPasteCompactD.setSelected(false);
        textViewClearCompactD.setSelected(false);
        textViewMinusCompactE.setSelected(false);
        textViewPlusCompactE.setSelected(false);
        textViewCopyCompactE.setSelected(false);
        textViewPasteCompactE.setSelected(false);
        textViewClearCompactE.setSelected(false);
        textViewMinusCompactF.setSelected(false);
        textViewPlusCompactF.setSelected(false);
        textViewCopyCompactF.setSelected(false);
        textViewPasteCompactF.setSelected(false);
        textViewClearCompactF.setSelected(false);
        //
        buttonRunExample1.setSelected(false);
        buttonRunExample2.setSelected(false);
        buttonRunExample3.setSelected(false);
        buttonRunExample4.setSelected(false);
        buttonRunExample5.setSelected(false);
        buttonRun.setSelected(false);
        buttonRun1.setSelected(false);
        buttonRun2.setSelected(false);
        //
        textViewExpandResult.setSelected(false);
        textViewCopyResult.setSelected(false);
        textViewClearResult.setSelected(false);
        // Select the last button clicked.
        if (view != null) {
            UIHelper.vibrateOnButtonTap(requireContext());
            view.setSelected(true);
        }
    }
    private void resetResult(boolean skipLabelResult) {
        resetAllAndSelectTheLastButtonClicked();
        //
        if(!skipLabelResult) {
            textViewLabelResult.setText(requireContext().getText(R.string.result));
        }
        this.editTextResult.setText("");
        this.linearLayoutStaticColumnHeader1.removeAllViews();
        this.linearLayoutStaticColumnHeader2.removeAllViews();
        setListViewAdapter(listViewResult1, null);
        setListViewAdapter(listViewResult2, null);
    }
    private void setResultVisibilityFromButtonRun(boolean skipLabelResult) {
        if(!skipLabelResult) {
            textViewLabelResult.setText(requireContext().getText(R.string.result));
        }
        this.linearLayoutFModMContainer.setVisibility(View.GONE);
        this.textViewCopyResult.setVisibility(View.VISIBLE);
        this.textViewClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutResultContainer.setVisibility(View.VISIBLE);
        this.linearLayoutResultGridContainer1.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer2.setVisibility(View.GONE);
    }
    private void setResultVisibilityFromButtonRun1() {
        textViewLabelResult.setText(requireContext().getText(R.string.binary_quadratic_form_result_fxy));
        this.linearLayoutFModMContainer.setVisibility(View.GONE);
        this.textViewCopyResult.setVisibility(View.GONE);
        this.textViewClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutResultContainer.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer1.setVisibility(View.VISIBLE);
        this.linearLayoutResultGridContainer2.setVisibility(View.GONE);
    }
    private void setResultVisibilityFromButtonRun2() {
        textViewLabelResult.setText(requireContext().getText(R.string.binary_quadratic_form_result_fxy_f_mod_m_r));
        this.linearLayoutFModMContainer.setVisibility(View.VISIBLE);
        this.textViewCopyResult.setVisibility(View.GONE);
        this.textViewClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutResultContainer.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer1.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer2.setVisibility(View.VISIBLE);
    }
    //endregion RESULT
}