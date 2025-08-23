package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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


public class FragmentLinearCongruenceInOneVariable extends FragmentBase implements Callback {
    private final static String TAG = FragmentLinearCongruenceInOneVariable.class.getSimpleName();
    // Navigation controls
    TextView textViewBackToAlgorithms;
    TextView textViewTitle;
    // Cache view state
    boolean isCompactInputView = false;
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
    TextView textViewLabelM;
    TextView textViewLabelElasticM;
    TextView textViewMinusM;
    TextView textViewPlusM;
    TextView textViewCopyM;
    TextView textViewPasteM;
    TextView textViewClearM;
    EditText editTextM;
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
    TextView textViewLabelCompactM;
    EditText editTextCompactM;
    TextView textViewMinusCompactM;
    TextView textViewPlusCompactM;
    TextView textViewCopyCompactM;
    TextView textViewPasteCompactM;
    TextView textViewClearCompactM;
    // Run buttons
    Button buttonRun;
    Button buttonRunExample1;
    Button buttonRunExample2;
    Button buttonRunExample3;
    // Result controls
    TextView textViewLabelResult;
    TextView textViewLabelElasticResult;
    TextView textViewExpandResult;
    TextView textViewCopyResult;
    TextView textViewClearResult;
    EditText editTextResult;
    // Flags to prevent recursive updates
    AtomicBoolean isUpdatingEditTextA = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactA = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextM = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactM = new AtomicBoolean(false);


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
            inflater = layoutInflater.inflate(R.layout.fragment_linear_congruence_in_one_variable, container, false);
            // Navigation controls
            textViewBackToAlgorithms = inflater.findViewById(R.id.TextViewBackToAlgorithms);
            textViewTitle = inflater.findViewById(R.id.TextViewTitle);
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
            textViewLabelM = inflater.findViewById(R.id.TextViewLabelM);
            textViewLabelElasticM = inflater.findViewById(R.id.TextViewLabelElasticM);
            textViewMinusM = inflater.findViewById(R.id.TextViewMinusM);
            textViewPlusM = inflater.findViewById(R.id.TextViewPlusM);
            textViewCopyM = inflater.findViewById(R.id.TextViewCopyM);
            textViewPasteM = inflater.findViewById(R.id.TextViewPasteM);
            textViewClearM = inflater.findViewById(R.id.TextViewClearM);
            editTextM = inflater.findViewById(R.id.EditTextM);
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
            textViewLabelCompactM = inflater.findViewById(R.id.TextViewLabelCompactM);
            textViewMinusCompactM = inflater.findViewById(R.id.TextViewMinusCompactM);
            textViewPlusCompactM = inflater.findViewById(R.id.TextViewPlusCompactM);
            textViewCopyCompactM = inflater.findViewById(R.id.TextViewCopyCompactM);
            textViewPasteCompactM = inflater.findViewById(R.id.TextViewPasteCompactM);
            textViewClearCompactM = inflater.findViewById(R.id.TextViewClearCompactM);
            editTextCompactM = inflater.findViewById(R.id.EditTextCompactM);
            // Run buttons
            buttonRun = inflater.findViewById(R.id.ButtonRun);
            buttonRunExample1 = inflater.findViewById(R.id.ButtonRunExample1);
            buttonRunExample2 = inflater.findViewById(R.id.ButtonRunExample2);
            buttonRunExample3 = inflater.findViewById(R.id.ButtonRunExample3);
            // Result controls
            textViewLabelResult = inflater.findViewById(R.id.TextViewLabelResult);
            textViewLabelElasticResult = inflater.findViewById(R.id.TextViewLabelElasticResult);
            textViewExpandResult = inflater.findViewById(R.id.TextViewExpandResult);
            textViewCopyResult = inflater.findViewById(R.id.TextViewCopyResult);
            textViewClearResult = inflater.findViewById(R.id.TextViewClearResult);
            editTextResult = inflater.findViewById(R.id.EditTextResult);

            // Constrain extended input
            editTextA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextM.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            // Constrain compact input
            editTextCompactA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactM.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Navigation vents
            textViewBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });

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
                    String labelText = "a" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelA.setText(labelText);
                    resetResult(false);
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
            editTextM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextM.get()) return; // editTextM is locked
                    // Other work
                    String labelText = "m" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelM.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactM
                    isUpdatingEditTextCompactM.set(true); // Lock editTextCompactM
                    try {
                        editTextCompactM.setText(s.toString());
                        // editTextCompactM.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactM.set(false); // Unlock editTextCompactM
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
                    resetResult(false);
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
            editTextCompactM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactM.get()) return; // editTextCompactM is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextM
                    isUpdatingEditTextM.set(true); // Lock editTextM
                    try {
                        editTextM.setText(s.toString());
                        // editTextM.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextM.set(false); // unlock editTextM
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

            // Extended input m clipboard button events
            textViewMinusM.setOnClickListener(v -> {
                decreaseByOne(editTextM);
                resetAllAndSelectTheLastButtonClicked(textViewMinusM);
            });
            textViewPlusM.setOnClickListener(v -> {
                increaseByOne(editTextM);
                resetAllAndSelectTheLastButtonClicked(textViewPlusM);
            });
            textViewCopyM.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextM);
                resetAllAndSelectTheLastButtonClicked(textViewCopyM);
            });
            textViewPasteM.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextM);
                resetAllAndSelectTheLastButtonClicked(textViewPasteM);
            });
            textViewClearM.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextM);
                resetAllAndSelectTheLastButtonClicked(textViewClearM);
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

            // Compact input m clipboard button events
            textViewMinusCompactM.setOnClickListener(v -> {
                decreaseByOne(editTextCompactM);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactM);
            });
            textViewPlusCompactM.setOnClickListener(v -> {
                increaseByOne(editTextCompactM);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactM);
            });
            textViewCopyCompactM.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactM);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactM);
            });
            textViewPasteCompactM.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactM);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactM);
            });
            textViewClearCompactM.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactM);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactM);
            });

            // Run button events
            buttonRun.setOnClickListener(v -> onButtonRun(container, buttonRun, false));
            buttonRunExample1.setOnClickListener(v -> onButtonRunExample1(container));
            buttonRunExample2.setOnClickListener(v -> onButtonRunExample2(container));
            buttonRunExample3.setOnClickListener(v -> onButtonRunExample3(container));

            // Result clipboard button events
            textViewExpandResult.setOnClickListener(v -> {
                expandResult();
            });
            textViewCopyResult.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextResult);
                resetAllAndSelectTheLastButtonClicked(textViewCopyResult);
            });
            textViewClearResult.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextResult);
                resetAllAndSelectTheLastButtonClicked(textViewClearResult);
            });

            // Result events
            editTextResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.length() == 0) {
                        textViewExpandResult.setVisibility(View.GONE);
                    } else {
                        textViewExpandResult.setVisibility(View.VISIBLE);
                    }
                }
            });
            initDoubleTapDetector(editTextResult);
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
        PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), editTextResult.getText());
        popupResult.show();
        resetAllAndSelectTheLastButtonClicked(textViewExpandResult);
    }


    //region MENU
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        try {
            menuInflater.inflate(R.menu.menu_fragment_linear_congruence_in_one_variable, menu);
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
            if (id == R.id.linear_congruence_in_one_variable_menu_example_1) {
                this.editTextA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_a));
                this.editTextB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_b));
                this.editTextM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_m));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
                resetResult(true);
                return true;
            }
            if (id == R.id.linear_congruence_in_one_variable_menu_example_2) {
                this.editTextA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_a));
                this.editTextB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_b));
                this.editTextM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_m));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
                resetResult(true);
                return true;
            }
            if (id == R.id.linear_congruence_in_one_variable_menu_example_3) {
                this.editTextA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_a));
                this.editTextB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_b));
                this.editTextM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_m));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
                resetResult(true);
                return true;
            }
            if (id == R.id.menu_documentation) {
                DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.LINEAR_CONGRUENCE_IN_ONE_VARIABLE_PDF).show(getParentFragmentManager(), "LINEAR_CONGRUENCE_IN_ONE_VARIABLE_PDF");
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
        refreshControlsDisplay();
        refreshHideExampleButtons();
        refreshResultDisplay();
    }


    //region Display
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
                textViewMinusM.setVisibility(View.VISIBLE);
                textViewPlusM.setVisibility(View.VISIBLE);
                textViewMinusCompactM.setVisibility(View.VISIBLE);
                textViewPlusCompactM.setVisibility(View.VISIBLE);
            } else {
                textViewMinusA.setVisibility(View.GONE);
                textViewPlusA.setVisibility(View.GONE);
                textViewMinusCompactA.setVisibility(View.GONE);
                textViewPlusCompactA.setVisibility(View.GONE);
                textViewMinusB.setVisibility(View.GONE);
                textViewPlusB.setVisibility(View.GONE);
                textViewMinusCompactB.setVisibility(View.GONE);
                textViewPlusCompactB.setVisibility(View.GONE);
                textViewMinusM.setVisibility(View.GONE);
                textViewPlusM.setVisibility(View.GONE);
                textViewMinusCompactM.setVisibility(View.GONE);
                textViewPlusCompactM.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.buttonRunExample1.getVisibility() == View.VISIBLE; // Just check one.
            boolean hideExampleButtons = UserSettings.getHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonRun.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_run_long));
                this.buttonRunExample1.setVisibility(View.GONE);
                this.buttonRunExample2.setVisibility(View.GONE);
                this.buttonRunExample3.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonRun.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_run_short));
                this.buttonRunExample1.setVisibility(View.VISIBLE);
                this.buttonRunExample2.setVisibility(View.VISIBLE);
                this.buttonRunExample3.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshControlsDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons
            ControlDisplay.setClipboardButtonFontSize(textViewMinusA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactM, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult, biggerControls);
            // Extended input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticA, biggerControls);
            ControlDisplay.setInputFontSize(editTextA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticB, biggerControls);
            ControlDisplay.setInputFontSize(editTextB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelM, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticM, biggerControls);
            ControlDisplay.setInputFontSize(editTextM, biggerControls);
            // Compact input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactA, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactB, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactM, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactM, biggerControls);
            // Run buttons
            ControlDisplay.setButtonFontSize(buttonRun, biggerControls);
            // Example run buttons
            ControlDisplay.setButtonFontSize(buttonRunExample1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample3, biggerControls);
            // Output result
            ControlDisplay.setInputLabelFontSize(textViewLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshResultDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerResultDisplay(requireContext());
            // Output result
            ControlDisplay.setOutputFontSize(editTextResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Display


    //region Callback
    @Override
    public void callbackResult(AlgorithmName algorithmName, Object result, ProgressStatus progressStatus) {
        // This is to prevent the error: Non-fatal Exception: java.lang.IllegalStateException: Fragment FragmentPrimesList{94d7331} (36e8cdd6-9d00-4c2a-bd07-ab5550e2c88b) not attached to a context.
        // java.lang.IllegalStateException: Fragment not attached to Activity -> https://stackoverflow.com/questions/28672883/java-lang-illegalstateexception-fragment-not-attached-to-activity
        Activity activity = getActivity();
        if (activity == null || !this.isAdded()) {
            return;
        }
        if (algorithmName == AlgorithmName.LINEAR_CONGRUENCE_IN_ONE_VARIABLE) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString, Html.FROM_HTML_MODE_LEGACY);
                editTextResult.setText(resultFromHtml);
            }
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
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


    private InputGroup getInputGroupM() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelM, "m", textViewLabelElasticM)
                .setInput(editTextM)
                .setCompactControls(textViewLabelCompactM, editTextCompactM)
                .build();
    }


    private void onButtonRun(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupB = getInputGroupB();
            InputGroup inputGroupM = getInputGroupM();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupB)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanMin(requireActivity(), inputGroupM, BigInteger.ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());
            BigInteger m = new BigInteger(editTextM.getText().toString());

            // Reset result
            resetResult(skipLabelResult);

            // Before action performing.
            beforeActionPerforming(button);

            // Perform the linear congruence solver
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.LINEAR_CONGRUENCE_IN_ONE_VARIABLE, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(m);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonRunExample1(ViewGroup container) {
        try {
            editTextA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_a));
            editTextB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_b));
            editTextM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_m));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            onButtonRun(container, buttonRunExample1, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonRunExample2(ViewGroup container) {
        try {
            editTextA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_a));
            editTextB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_b));
            editTextM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_m));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            onButtonRun(container, buttonRunExample2, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void onButtonRunExample3(ViewGroup container) {
        try {
            editTextA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_a));
            editTextB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_b));
            editTextM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_m));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            onButtonRun(container, buttonRunExample3, true);
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
        editTextA.clearFocus();
        editTextB.clearFocus();
        editTextM.clearFocus();
        editTextCompactA.clearFocus();
        editTextCompactB.clearFocus();
        editTextCompactM.clearFocus();
        // Select the last button clicked.
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
        textViewMinusM.setSelected(false);
        textViewPlusM.setSelected(false);
        textViewCopyM.setSelected(false);
        textViewPasteM.setSelected(false);
        textViewClearM.setSelected(false);
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
        textViewMinusCompactM.setSelected(false);
        textViewPlusCompactM.setSelected(false);
        textViewCopyCompactM.setSelected(false);
        textViewPasteCompactM.setSelected(false);
        textViewClearCompactM.setSelected(false);
        //
        buttonRun.setSelected(false);
        buttonRunExample1.setSelected(false);
        buttonRunExample2.setSelected(false);
        buttonRunExample3.setSelected(false);
        //
        textViewExpandResult.setSelected(false);
        textViewCopyResult.setSelected(false);
        textViewClearResult.setSelected(false);
        // Select he last button clicked.
        if (textView != null) {
            UIHelper.vibrateOnButtonTap(requireContext());
            textView.setSelected(true);
        }
    }
    private void resetResult(boolean skipLabelResult) {
        resetAllAndSelectTheLastButtonClicked();
        //
        if(!skipLabelResult) {
            textViewLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextResult.setText("");
    }
    //endregion RESULT
}