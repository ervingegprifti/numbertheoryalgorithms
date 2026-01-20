package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.annotation.SuppressLint;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.gegprifti.android.numbertheoryalgorithms.cyclesets.rsa.RSA;
import com.gegprifti.android.numbertheoryalgorithms.cyclesets.rsa.RSASet;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.InputGroup;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.settings.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class FragmentModFactors extends FragmentBase implements Callback {
    private final static String TAG = FragmentModFactors.class.getSimpleName();
    static final BigInteger TWO = BigInteger.valueOf(2L);
    BigInteger INTEGER_MAX_VALUE = new BigInteger(Integer.toString(Integer.MAX_VALUE));
    // Navigation controls
    TextView textViewBackToAlgorithms;
    TextView textViewTitle;
    TextView textViewInputCycleRSA;
    // Cache view state
    boolean isCompactInputView = false;
    // Expanded input view
    LinearLayout linearLayoutExpandedInputView;
    TextView textViewLabelN;
    TextView textViewLabelElasticN;
    TextView textViewMinusN;
    TextView textViewPlusN;
    TextView textViewCopyN;
    TextView textViewPasteN;
    TextView textViewClearN;
    EditText editTextN;
    TextView textViewLabelB;
    TextView textViewLabelElasticB;
    TextView textViewMinusB;
    TextView textViewPlusB;
    TextView textViewCopyB;
    TextView textViewPasteB;
    TextView textViewClearB;
    EditText editTextB;
    // Compact input view
    LinearLayout linearLayoutCompactInputView;
    TextView textViewLabelCompactN;
    EditText editTextCompactN;
    TextView textViewMinusCompactN;
    TextView textViewPlusCompactN;
    TextView textViewCopyCompactN;
    TextView textViewPasteCompactN;
    TextView textViewClearCompactN;
    TextView textViewLabelCompactB;
    EditText editTextCompactB;
    TextView textViewMinusCompactB;
    TextView textViewPlusCompactB;
    TextView textViewCopyCompactB;
    TextView textViewPasteCompactB;
    TextView textViewClearCompactB;
    // Example run buttons
    LinearLayout linearLayoutExamplesContainer;
    Button buttonRunExample1;
    Button buttonRunExample2;
    Button buttonRunExample3;
    Button buttonRunExample4;
    Button buttonRunExample5;
    Button buttonRunExample6;
    // Run buttons
    Button buttonRun1;
    Button buttonRun2;
    Button buttonRun3;
    Button buttonCountRun;
    // Result controls
    TextView textViewLabelResult;
    TextView textViewLabelElasticResult;
    TextView textViewToggleUpDownResult;
    TextView textViewExpandResult;
    TextView textViewCopyResult;
    TextView textViewClearResult;
    LinearLayout linearLayoutResultContainer;
    ScrollView scrollViewResultContainer;
    LinearLayout linearLayoutResultModFactors;
    EditText editTextResult;
    // Flags to prevent recursive updates
    AtomicBoolean isUpdatingEditTextN = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactN = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextB = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactB = new AtomicBoolean(false);

    private RSASet rsaSet;

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
            inflater = layoutInflater.inflate(R.layout.fragment_mod_factors, container, false);
            // Navigation controls
            textViewBackToAlgorithms = inflater.findViewById(R.id.TextViewBackToAlgorithms);
            textViewTitle = inflater.findViewById(R.id.TextViewTitle);
            textViewInputCycleRSA = inflater.findViewById(R.id.TextViewInputCycleRSA);
            // Expanded input view
            linearLayoutExpandedInputView = inflater.findViewById(R.id.LinearLayoutExpandedInputView);
            textViewLabelN = inflater.findViewById(R.id.TextViewLabelN);
            textViewLabelElasticN = inflater.findViewById(R.id.TextViewLabelElasticN);
            textViewMinusN = inflater.findViewById(R.id.TextViewMinusN);
            textViewPlusN = inflater.findViewById(R.id.TextViewPlusN);
            textViewCopyN = inflater.findViewById(R.id.TextViewCopyN);
            textViewPasteN = inflater.findViewById(R.id.TextViewPasteN);
            textViewClearN = inflater.findViewById(R.id.TextViewClearN);
            editTextN = inflater.findViewById(R.id.EditTextN);
            textViewLabelB = inflater.findViewById(R.id.TextViewLabelB);
            textViewLabelElasticB = inflater.findViewById(R.id.TextViewLabelElasticB);
            textViewMinusB = inflater.findViewById(R.id.TextViewMinusB);
            textViewPlusB = inflater.findViewById(R.id.TextViewPlusB);
            textViewCopyB = inflater.findViewById(R.id.TextViewCopyB);
            textViewPasteB = inflater.findViewById(R.id.TextViewPasteB);
            textViewClearB = inflater.findViewById(R.id.TextViewClearB);
            editTextB = inflater.findViewById(R.id.EditTextB);
            // Compact input view
            linearLayoutCompactInputView = inflater.findViewById(R.id.LinearLayoutCompactInputView);
            textViewLabelCompactN = inflater.findViewById(R.id.TextViewLabelCompactN);
            editTextCompactN = inflater.findViewById(R.id.EditTextCompactN);
            textViewMinusCompactN = inflater.findViewById(R.id.TextViewMinusCompactN);
            textViewPlusCompactN = inflater.findViewById(R.id.TextViewPlusCompactN);
            textViewCopyCompactN = inflater.findViewById(R.id.TextViewCopyCompactN);
            textViewPasteCompactN = inflater.findViewById(R.id.TextViewPasteCompactN);
            textViewClearCompactN = inflater.findViewById(R.id.TextViewClearCompactN);
            textViewLabelCompactB = inflater.findViewById(R.id.TextViewLabelCompactB);
            editTextCompactB = inflater.findViewById(R.id.EditTextCompactB);
            textViewMinusCompactB = inflater.findViewById(R.id.TextViewMinusCompactB);
            textViewPlusCompactB = inflater.findViewById(R.id.TextViewPlusCompactB);
            textViewCopyCompactB = inflater.findViewById(R.id.TextViewCopyCompactB);
            textViewPasteCompactB = inflater.findViewById(R.id.TextViewPasteCompactB);
            textViewClearCompactB = inflater.findViewById(R.id.TextViewClearCompactB);
            // Example run buttons
            linearLayoutExamplesContainer = inflater.findViewById(R.id.LinearLayoutExamplesContainer);
            buttonRunExample1 = inflater.findViewById(R.id.ButtonRunExample1);
            buttonRunExample2 = inflater.findViewById(R.id.ButtonRunExample2);
            buttonRunExample3 = inflater.findViewById(R.id.ButtonRunExample3);
            buttonRunExample4 = inflater.findViewById(R.id.ButtonRunExample4);
            buttonRunExample5 = inflater.findViewById(R.id.ButtonRunExample5);
            buttonRunExample6 = inflater.findViewById(R.id.ButtonRunExample6);
            // Run buttons
            buttonRun1 = inflater.findViewById(R.id.ButtonRun1);
            buttonRun2 = inflater.findViewById(R.id.ButtonRun2);
            buttonRun3 = inflater.findViewById(R.id.ButtonRun3);
            buttonCountRun = inflater.findViewById(R.id.ButtonCountRun);
            // Result controls
            textViewLabelResult = inflater.findViewById(R.id.TextViewLabelResult);
            textViewLabelElasticResult = inflater.findViewById(R.id.TextViewLabelElasticResult);
            textViewToggleUpDownResult = inflater.findViewById(R.id.TextViewToggleUpDownResult);
            textViewExpandResult = inflater.findViewById(R.id.TextViewExpandResult);
            textViewCopyResult = inflater.findViewById(R.id.TextViewCopyResult);
            textViewClearResult = inflater.findViewById(R.id.TextViewClearResult);
            linearLayoutResultContainer = inflater.findViewById(R.id.LinearLayoutResultContainer);
            scrollViewResultContainer = inflater.findViewById(R.id.ScrollViewResultContainer);
            linearLayoutResultModFactors = inflater.findViewById(R.id.LinearLayoutResultModFactors);
            editTextResult = inflater.findViewById(R.id.EditTextResult);

            // Constrain expanded input
            editTextN.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            // Constrain compact input
            editTextCompactN.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Title bar events
            textViewBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });
            this.textViewInputCycleRSA.setOnClickListener(view -> {
                if (this.rsaSet == null) {
                    this.rsaSet = new RSASet();
                }
                RSA rsa = rsaSet.getNextRSA();
                resetAllAndSelectTheLastButtonClicked(this.textViewInputCycleRSA);
                //noinspection SetTextI18n
                this.editTextN.setText(rsa.getN().toString());
                this.textViewInputCycleRSA.setText(rsa.getName());
            });

            // Expanded input events
            editTextN.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextN.get()) return; // editTextN is locked
                    // Other work
                    String labelText = "n" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelN.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactN
                    isUpdatingEditTextCompactN.set(true); // Lock editTextCompactN
                    try {
                        editTextCompactN.setText(s.toString());
                        // editTextCompactN.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactN.set(false); // Unlock editTextCompactN
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

            // Compact input events
            editTextCompactN.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactN.get()) return; // editTextCompactN is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextN
                    isUpdatingEditTextN.set(true); // Lock editTextN
                    try {
                        editTextN.setText(s.toString());
                        // editTextN.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextN.set(false); // unlock editTextN
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

            // Expanded input n clipboard button events
            textViewMinusN.setOnClickListener(v -> {
                decreaseByOne(editTextN);
                resetAllAndSelectTheLastButtonClicked(textViewMinusN);
            });
            textViewPlusN.setOnClickListener(v -> {
                increaseByOne(editTextN);
                resetAllAndSelectTheLastButtonClicked(textViewPlusN);
            });
            textViewCopyN.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextN);
                resetAllAndSelectTheLastButtonClicked(textViewCopyN);
            });
            textViewPasteN.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextN);
                resetAllAndSelectTheLastButtonClicked(textViewPasteN);
            });
            textViewClearN.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextN);
                resetAllAndSelectTheLastButtonClicked(textViewClearN);
            });

            // Expanded input b clipboard button events
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

            // Compact input n clipboard button events
            textViewMinusCompactN.setOnClickListener(v -> {
                decreaseByOne(editTextCompactN);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactN);
            });
            textViewPlusCompactN.setOnClickListener(v -> {
                increaseByOne(editTextCompactN);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactN);
            });
            textViewCopyCompactN.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactN);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactN);
            });
            textViewPasteCompactN.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactN);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactN);
            });
            textViewClearCompactN.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactN);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactN);
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

            // Example run button events
            buttonRunExample1.setOnClickListener(v -> onButtonRunExample1(container));
            buttonRunExample2.setOnClickListener(v -> onButtonRunExample2(container));
            buttonRunExample3.setOnClickListener(v -> onButtonRunExample3(container));
            buttonRunExample4.setOnClickListener(v -> onButtonRunExample4(container));
            buttonRunExample5.setOnClickListener(v -> onButtonRunExample5(container));
            buttonRunExample6.setOnClickListener(v -> onButtonRunExample6(container));

            // Run button events
            buttonRun1.setOnClickListener(v -> onButtonRun1(container, buttonRun1, false));
            buttonRun2.setOnClickListener(v -> onButtonRun2(container, buttonRun2, false));
            buttonRun3.setOnClickListener(v -> onButtonRun3(container, buttonRun3, false));
            buttonCountRun.setOnClickListener(v -> onButtonCountRun(container));

            // Result clipboard button events
            textViewToggleUpDownResult.setOnClickListener(v -> {
                toggleUpDownResult();
            });
            textViewExpandResult.setOnClickListener(v -> {
                expandResult();
            });
            textViewCopyResult.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextResult);
                resetAllAndSelectTheLastButtonClicked(textViewCopyResult);
            });
            textViewClearResult.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextResult);
                linearLayoutResultModFactors.removeAllViews();
                resetAllAndSelectTheLastButtonClicked(textViewClearResult);
            });

            // Result events
            editTextResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) { }
            });
            initDoubleTapDetector(editTextResult);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }

        return inflater;
    }
    //endregion CREATE


    @Override
    protected void fireOnDoubleTap(View view) {
        if (view == this.editTextResult){
            expandResult();
            resetAllAndSelectTheLastButtonClicked(textViewExpandResult);
        }
    }


    private void expandResult() {
        PopupResult popupResult;
        if (linearLayoutResultModFactors.getChildCount() == 0) {
            popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), this.editTextResult.getText());
        } else {
            popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), linearLayoutResultContainer);
        }
        popupResult.show();
        resetAllAndSelectTheLastButtonClicked(textViewExpandResult);
    }


    private void toggleUpDownResult() {
        scrollViewResultContainer.post(() -> {
            CharSequence current = textViewToggleUpDownResult.getText();
            boolean shouldScrollDown = current != null && current.toString().equals(getString(R.string.fa_chevron_down));
            if (shouldScrollDown) {
                // Scroll DOWN
                scrollViewResultContainer.fullScroll(View.FOCUS_DOWN);
                textViewToggleUpDownResult.setText(R.string.fa_chevron_up);
            } else {
                // Scroll UP
                scrollViewResultContainer.fullScroll(View.FOCUS_UP);
                textViewToggleUpDownResult.setText(R.string.fa_chevron_down);
            }
        });
        resetAllAndSelectTheLastButtonClicked(textViewToggleUpDownResult);
    }


    //region MENU
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        try {
            menuInflater.inflate(R.menu.menu_fragment_mod_factors, menu);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
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
            if (id == R.id.mod_factors_menu_example_1) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_1_n));
                this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_1_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_2) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_2_n));
                this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_2_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_3) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_3_n));
                this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_3_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_4) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_4_n));
                this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_4_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_4));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_5) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_5_n));
                this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_5_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_5));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_6) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_6_n));
                this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_6_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_6));
                resetResult(true);
                return true;
            }
            if (id == R.id.menu_documentation) {
                DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.MOD_FACTORS_PDF).show(getParentFragmentManager(), "MOD_FACTORS_PDF");
                return true;
            }
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
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
                linearLayoutExpandedInputView.setVisibility(View.GONE);
                linearLayoutCompactInputView.setVisibility(View.VISIBLE);
            } else {
                linearLayoutExpandedInputView.setVisibility(View.VISIBLE);
                linearLayoutCompactInputView.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }


    private void refreshShowInputDecreaseIncreaseButtons() {
        try {
            boolean showInputDecreaseIncreaseButtons = UserSettings.getShowInputDecreaseIncreaseButtons(requireContext());
            if (showInputDecreaseIncreaseButtons) {
                textViewMinusN.setVisibility(View.VISIBLE);
                textViewPlusN.setVisibility(View.VISIBLE);
                textViewMinusCompactN.setVisibility(View.VISIBLE);
                textViewPlusCompactN.setVisibility(View.VISIBLE);
                textViewMinusB.setVisibility(View.VISIBLE);
                textViewPlusB.setVisibility(View.VISIBLE);
                textViewMinusCompactB.setVisibility(View.VISIBLE);
                textViewPlusCompactB.setVisibility(View.VISIBLE);
            } else {
                textViewMinusN.setVisibility(View.GONE);
                textViewPlusN.setVisibility(View.GONE);
                textViewMinusCompactN.setVisibility(View.GONE);
                textViewPlusCompactN.setVisibility(View.GONE);
                textViewMinusB.setVisibility(View.GONE);
                textViewPlusB.setVisibility(View.GONE);
                textViewMinusCompactB.setVisibility(View.GONE);
                textViewPlusCompactB.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }


    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.linearLayoutExamplesContainer.getVisibility() == View.VISIBLE;
            boolean hideExampleButtons = UserSettings.getHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.linearLayoutExamplesContainer.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.linearLayoutExamplesContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }


    private void refreshControlsDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons
            ControlDisplay.setClipboardButtonFontSize(textViewMinusN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactB, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewToggleUpDownResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult, biggerControls);
            // Expanded input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelN, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticN, biggerControls);
            ControlDisplay.setInputFontSize(editTextN, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticB, biggerControls);
            ControlDisplay.setInputFontSize(editTextB, biggerControls);
            // Compact input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactN, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactN, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactB, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactB, biggerControls);
            // Example run buttons
            ControlDisplay.setButtonFontSize(buttonRunExample1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample3, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample4, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample5, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample6, biggerControls);
            // Run buttons
            ControlDisplay.setButtonFontSize(buttonRun1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRun2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRun3, biggerControls);
            ControlDisplay.setButtonFontSize(buttonCountRun, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }


    private void refreshResultDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerResultDisplay(requireContext());
            // Output result
            ControlDisplay.setOutputFontSize(editTextResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
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

        if (algorithmName == AlgorithmName.MOD_FACTORS_ALG1) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String) result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString, Html.FROM_HTML_MODE_LEGACY);
                editTextResult.setText(resultFromHtml);
            }
            linearLayoutResultModFactors.removeAllViews();
        }

        if (algorithmName == AlgorithmName.MOD_FACTORS_ALG2) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String) result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString, Html.FROM_HTML_MODE_LEGACY);
                editTextResult.setText(resultFromHtml);
            }
            linearLayoutResultModFactors.removeAllViews();
        }

        if (algorithmName == AlgorithmName.MOD_FACTORS_ALG3) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextResult.setText(resultCanceledText);
            } else {
                if (result instanceof List<?>) {
                    @SuppressWarnings("unchecked")
                    List<String> modFactors = (List<String>) result;
                    String modFactorsString = modFactors.get(0);
                    CharSequence resultFromHtml = Html.fromHtml(modFactorsString, Html.FROM_HTML_MODE_LEGACY);
                    editTextResult.setText(resultFromHtml);

                    //
                    LayoutInflater inflater = LayoutInflater.from(requireContext());
                    linearLayoutResultModFactors.removeAllViews(); // Clear old results
                    for (int i = 1; i < modFactors.size(); i++) {
                        String modFactor = modFactors.get(i);
                        TextView textView = (TextView) inflater.inflate(R.layout.mod_factor, linearLayoutResultModFactors, false);
                        textView.setText(modFactor);

                        textView.setOnClickListener(v -> {
                            UIHelper.copyTextIntoClipboardWithoutNotification(requireContext(), modFactor);
                        });

                        //textView.setOnLongClickListener(v -> {
                        //    UIHelper.copyTextIntoClipboardWithoutNotification(requireContext(), modFactor);
                        //    return true;
                        //});

                        linearLayoutResultModFactors.addView(textView);
                    }
                }
            }
        }

        if (algorithmName == AlgorithmName.MOD_FACTORS_COUNT) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString, Html.FROM_HTML_MODE_LEGACY);
                editTextResult.setText(resultFromHtml);
            }
            linearLayoutResultModFactors.removeAllViews();
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void onButtonRunExample1(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_1_n));
            this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_1_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            onButtonRun1(container, buttonRunExample1, true);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
    private void onButtonRunExample2(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_2_n));
            this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_2_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            onButtonRun1(container, buttonRunExample2, true);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
    private void onButtonRunExample3(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_3_n));
            this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_3_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            onButtonRun1(container, buttonRunExample3, true);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
    private void onButtonRunExample4(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_4_n));
            this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_4_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_4));
            //
            onButtonRun1(container, buttonRunExample4, true);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
    private void onButtonRunExample5(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_5_n));
            this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_5_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_5));
            //
            onButtonRun1(container, buttonRunExample5, true);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
    private void onButtonRunExample6(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_6_n));
            this.editTextB.setText(requireContext().getText(R.string.mod_factors_example_6_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_6));
            //
            onButtonRun1(container, buttonRunExample6, true);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
    private InputGroup getInputGroupN() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setExpandedControls(textViewLabelN, "n", textViewLabelElasticN, editTextN)
                .setCompactControls(textViewLabelCompactN, editTextCompactN)
                .build();
    }
    private InputGroup getInputGroupB() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setExpandedControls(textViewLabelB, "b", textViewLabelElasticB,editTextB )
                .setCompactControls(textViewLabelCompactB, editTextCompactB)
                .build();
    }
    private void onButtonRun1(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            // Check.
            InputGroup inputGroupN = getInputGroupN();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupN, BigInteger.ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), inputGroupB, TWO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger n = new BigInteger(editTextN.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult(skipLabelResult);

            // Before action performing.
            beforeActionPerforming(button);

            // Perform the mod factors
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.MOD_FACTORS_ALG1, this);
            algorithmParameters.setInput1(n);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
    private void onButtonRun2(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            // Check.
            InputGroup inputGroupN = getInputGroupN();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupN, BigInteger.ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), inputGroupB, TWO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger n = new BigInteger(editTextN.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult(skipLabelResult);

            // Before action performing.
            beforeActionPerforming(button);

            // Perform the mod factors
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.MOD_FACTORS_ALG2, this);
            algorithmParameters.setInput1(n);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
    private void onButtonRun3(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            // Check.
            InputGroup inputGroupN = getInputGroupN();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupN, BigInteger.ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), inputGroupB, TWO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger n = new BigInteger(editTextN.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult(skipLabelResult);

            // Before action performing.
            beforeActionPerforming(button);

            // Perform the mod factors
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.MOD_FACTORS_ALG3, this);
            algorithmParameters.setInput1(n);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
    private void onButtonCountRun(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupN = getInputGroupN();
            InputGroup inputGroupB = getInputGroupB();
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupN, BigInteger.ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), inputGroupB, TWO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger n = new BigInteger(editTextN.getText().toString());
            BigInteger b = new BigInteger(editTextB.getText().toString());

            // Reset result
            resetResult(false);

            // Before action performing.
            beforeActionPerforming(buttonCountRun);

            // Perform the mod factors
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.MOD_FACTORS_COUNT, this);
            algorithmParameters.setInput1(n);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
        }
    }
    //endregion BUTTON ACTIONS


    //region RESULT
    private void beforeActionPerforming(Button button) {
        // Hide the keyboard.
        UIHelper.hideSoftKeyBoard(requireActivity());
        // Clear the focus.
        editTextN.clearFocus();
        editTextB.clearFocus();
        editTextCompactN.clearFocus();
        editTextCompactB.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastButtonClicked() {
        resetAllAndSelectTheLastButtonClicked(null);
    }
    private void resetAllAndSelectTheLastButtonClicked(TextView textView) {
        //
        this.textViewInputCycleRSA.setText(requireContext().getString(R.string.rsa));
        this.textViewInputCycleRSA.setSelected(false);
        //
        textViewMinusN.setSelected(false);
        textViewPlusN.setSelected(false);
        textViewCopyN.setSelected(false);
        textViewPasteN.setSelected(false);
        textViewClearN.setSelected(false);
        textViewMinusB.setSelected(false);
        textViewPlusB.setSelected(false);
        textViewCopyB.setSelected(false);
        textViewPasteB.setSelected(false);
        textViewClearB.setSelected(false);
        textViewMinusCompactN.setSelected(false);
        textViewPlusCompactN.setSelected(false);
        textViewCopyCompactN.setSelected(false);
        textViewPasteCompactN.setSelected(false);
        textViewClearCompactN.setSelected(false);
        textViewMinusCompactB.setSelected(false);
        textViewPlusCompactB.setSelected(false);
        textViewCopyCompactB.setSelected(false);
        textViewPasteCompactB.setSelected(false);
        textViewClearCompactB.setSelected(false);
        //
        buttonRunExample1.setSelected(false);
        buttonRunExample2.setSelected(false);
        buttonRunExample3.setSelected(false);
        buttonRunExample4.setSelected(false);
        buttonRunExample5.setSelected(false);
        buttonRunExample6.setSelected(false);
        //
        buttonRun1.setSelected(false);
        buttonRun2.setSelected(false);
        buttonRun3.setSelected(false);
        buttonCountRun.setSelected(false);
        //
        textViewToggleUpDownResult.setSelected(false);
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
        linearLayoutResultModFactors.removeAllViews();
    }
    //endregion RESULT
}