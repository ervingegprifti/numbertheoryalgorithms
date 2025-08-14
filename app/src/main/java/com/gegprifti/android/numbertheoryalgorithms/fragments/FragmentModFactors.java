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
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;


public class FragmentModFactors extends FragmentBase implements Callback {
    private final static String TAG = FragmentModFactors.class.getSimpleName();
    static final BigInteger TWO = BigInteger.valueOf(2L);
    BigInteger INTEGER_MAX_VALUE = new BigInteger(Integer.toString(Integer.MAX_VALUE));
    // Navigation controls
    TextView textViewBackToAlgorithms;
    TextView textViewTitle;
    // Cache view state
    boolean isCompactInputView = false;
    // Extended input view
    LinearLayout linearLayoutExtendedInputView;
    TextView textViewLabelN;
    TextView textViewLabelElasticN;
    TextView textViewMinusN;
    TextView textViewPlusN;
    TextView textViewCopyN;
    TextView textViewPasteN;
    TextView textViewClearN;
    EditText editTextN;
    TextView textViewLabelA;
    TextView textViewLabelElasticA;
    TextView textViewMinusA;
    TextView textViewPlusA;
    TextView textViewCopyA;
    TextView textViewPasteA;
    TextView textViewClearA;
    EditText editTextA;
    // Compact input view
    LinearLayout linearLayoutCompactInputView;
    TextView textViewLabelCompactN;
    EditText editTextCompactN;
    TextView textViewMinusCompactN;
    TextView textViewPlusCompactN;
    TextView textViewCopyCompactN;
    TextView textViewPasteCompactN;
    TextView textViewClearCompactN;
    TextView textViewLabelCompactA;
    EditText editTextCompactA;
    TextView textViewMinusCompactA;
    TextView textViewPlusCompactA;
    TextView textViewCopyCompactA;
    TextView textViewPasteCompactA;
    TextView textViewClearCompactA;
    // Example run buttons
    LinearLayout linearLayoutExamplesContainer;
    Button buttonRunExample1;
    Button buttonRunExample2;
    Button buttonRunExample3;
    Button buttonRunExample4;
    Button buttonRunExample5;
    Button buttonRunExample6;
    // Run buttons
    Button buttonRun;
    Button buttonCountRun;
    // Result controls
    TextView textViewLabelResult;
    TextView textViewLabelElasticResult;
    TextView textViewExpandResult;
    TextView textViewCopyResult;
    TextView textViewClearResult;
    EditText editTextResult;
    // Flags to prevent recursive updates
    AtomicBoolean isUpdatingEditTextN = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactN = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextA = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactA = new AtomicBoolean(false);


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
            // Extended input view
            linearLayoutExtendedInputView = inflater.findViewById(R.id.LinearLayoutExtendedInputView);
            textViewLabelN = inflater.findViewById(R.id.TextViewLabelN);
            textViewLabelElasticN = inflater.findViewById(R.id.TextViewLabelElasticN);
            textViewMinusN = inflater.findViewById(R.id.TextViewMinusN);
            textViewPlusN = inflater.findViewById(R.id.TextViewPlusN);
            textViewCopyN = inflater.findViewById(R.id.TextViewCopyN);
            textViewPasteN = inflater.findViewById(R.id.TextViewPasteN);
            textViewClearN = inflater.findViewById(R.id.TextViewClearN);
            editTextN = inflater.findViewById(R.id.EditTextN);
            textViewLabelA = inflater.findViewById(R.id.TextViewLabelA);
            textViewLabelElasticA = inflater.findViewById(R.id.TextViewLabelElasticA);
            textViewMinusA = inflater.findViewById(R.id.TextViewMinusA);
            textViewPlusA = inflater.findViewById(R.id.TextViewPlusA);
            textViewCopyA = inflater.findViewById(R.id.TextViewCopyA);
            textViewPasteA = inflater.findViewById(R.id.TextViewPasteA);
            textViewClearA = inflater.findViewById(R.id.TextViewClearA);
            editTextA = inflater.findViewById(R.id.EditTextA);
            // Compact input view
            linearLayoutCompactInputView = inflater.findViewById(R.id.LinearLayoutCompactInputView);
            textViewLabelCompactN = inflater.findViewById(R.id.TextViewLabelCompactN);
            editTextCompactN = inflater.findViewById(R.id.EditTextCompactN);
            textViewMinusCompactN = inflater.findViewById(R.id.TextViewMinusCompactN);
            textViewPlusCompactN = inflater.findViewById(R.id.TextViewPlusCompactN);
            textViewCopyCompactN = inflater.findViewById(R.id.TextViewCopyCompactN);
            textViewPasteCompactN = inflater.findViewById(R.id.TextViewPasteCompactN);
            textViewClearCompactN = inflater.findViewById(R.id.TextViewClearCompactN);
            textViewLabelCompactA = inflater.findViewById(R.id.TextViewLabelCompactA);
            editTextCompactA = inflater.findViewById(R.id.EditTextCompactA);
            textViewMinusCompactA = inflater.findViewById(R.id.TextViewMinusCompactA);
            textViewPlusCompactA = inflater.findViewById(R.id.TextViewPlusCompactA);
            textViewCopyCompactA = inflater.findViewById(R.id.TextViewCopyCompactA);
            textViewPasteCompactA = inflater.findViewById(R.id.TextViewPasteCompactA);
            textViewClearCompactA = inflater.findViewById(R.id.TextViewClearCompactA);
            // Example run buttons
            this.linearLayoutExamplesContainer = inflater.findViewById(R.id.LinearLayoutExamplesContainer);
            this.buttonRunExample1 = inflater.findViewById(R.id.ButtonRunExample1);
            this.buttonRunExample2 = inflater.findViewById(R.id.ButtonRunExample2);
            this.buttonRunExample3 = inflater.findViewById(R.id.ButtonRunExample3);
            this.buttonRunExample4 = inflater.findViewById(R.id.ButtonRunExample4);
            this.buttonRunExample5 = inflater.findViewById(R.id.ButtonRunExample5);
            this.buttonRunExample6 = inflater.findViewById(R.id.ButtonRunExample6);
            // Run buttons
            buttonRun = inflater.findViewById(R.id.ButtonRun);
            buttonCountRun = inflater.findViewById(R.id.ButtonCountRun);
            // Result controls
            textViewLabelResult = inflater.findViewById(R.id.TextViewLabelResult);
            textViewLabelElasticResult = inflater.findViewById(R.id.TextViewLabelElasticResult);
            textViewExpandResult = inflater.findViewById(R.id.TextViewExpandResult);
            textViewCopyResult = inflater.findViewById(R.id.TextViewCopyResult);
            textViewClearResult = inflater.findViewById(R.id.TextViewClearResult);
            editTextResult = inflater.findViewById(R.id.EditTextResult);

            // Constrain extended input
            editTextN.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            // Constrain compact input
            editTextCompactN.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Navigation vents
            textViewBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });

            // Extended input events
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

            // Extended input n clipboard button events
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

            // Example run button events
            this.buttonRunExample1.setOnClickListener(v -> onButtonRunExample1(container));
            this.buttonRunExample2.setOnClickListener(v -> onButtonRunExample2(container));
            this.buttonRunExample3.setOnClickListener(v -> onButtonRunExample3(container));
            this.buttonRunExample4.setOnClickListener(v -> onButtonRunExample4(container));
            this.buttonRunExample5.setOnClickListener(v -> onButtonRunExample5(container));
            this.buttonRunExample6.setOnClickListener(v -> onButtonRunExample6(container));

            // Run button events
            buttonRun.setOnClickListener(v -> onButtonRun(container, buttonRun, false));
            buttonCountRun.setOnClickListener(v -> onButtonCountRun(container));

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
            menuInflater.inflate(R.menu.menu_fragment_mod_factors, menu);
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
            if (id == R.id.mod_factors_menu_example_1) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_1_n));
                this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_1_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_2) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_2_n));
                this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_2_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_3) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_3_n));
                this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_3_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_4) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_4_n));
                this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_4_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_4));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_5) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_5_n));
                this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_5_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_5));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_6) {
                this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_6_n));
                this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_6_a));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_6));
                resetResult(true);
                return true;
            }
            if (id == R.id.menu_documentation) {
                DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.MOD_FACTORS_PDF).show(getParentFragmentManager(), "MOD_FACTORS_PDF");
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
        this.refreshBiggerControls();
        this.refreshHideExampleButtons();
        refreshBiggerResultDisplay();
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
                textViewMinusN.setVisibility(View.VISIBLE);
                textViewPlusN.setVisibility(View.VISIBLE);
                textViewMinusCompactN.setVisibility(View.VISIBLE);
                textViewPlusCompactN.setVisibility(View.VISIBLE);
                textViewMinusA.setVisibility(View.VISIBLE);
                textViewPlusA.setVisibility(View.VISIBLE);
                textViewMinusCompactA.setVisibility(View.VISIBLE);
                textViewPlusCompactA.setVisibility(View.VISIBLE);
            } else {
                textViewMinusN.setVisibility(View.GONE);
                textViewPlusN.setVisibility(View.GONE);
                textViewMinusCompactN.setVisibility(View.GONE);
                textViewPlusCompactN.setVisibility(View.GONE);
                textViewMinusA.setVisibility(View.GONE);
                textViewPlusA.setVisibility(View.GONE);
                textViewMinusCompactA.setVisibility(View.GONE);
                textViewPlusCompactA.setVisibility(View.GONE);
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
                this.buttonRun.setText(requireContext().getText(R.string.mod_factors_run_long));
                this.linearLayoutExamplesContainer.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonRun.setText(requireContext().getText(R.string.mod_factors_run_short));
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
            ControlDisplay.setClipboardButtonFontSize(textViewMinusN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactA, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult, biggerControls);
            // Extended input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelN, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticN, biggerControls);
            ControlDisplay.setInputFontSize(editTextN, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticA, biggerControls);
            ControlDisplay.setInputFontSize(editTextA, biggerControls);
            // Compact input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactN, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactN, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactA, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactA, biggerControls);
            // Example run buttons
            ControlDisplay.setButtonFontSize(buttonRunExample1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample3, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample4, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample5, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample6, biggerControls);
            // Run buttons
            ControlDisplay.setButtonFontSize(buttonRun, biggerControls);
            ControlDisplay.setButtonFontSize(buttonCountRun, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticResult, biggerControls);
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
        if (algorithmName == AlgorithmName.MOD_FACTORS) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String) result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString, Html.FROM_HTML_MODE_LEGACY);
                editTextResult.setText(resultFromHtml);
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
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void onButtonRunExample1(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_1_n));
            this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_1_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            onButtonRun(container, buttonRunExample1, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample2(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_2_n));
            this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_2_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            onButtonRun(container, buttonRunExample2, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample3(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_3_n));
            this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_3_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            onButtonRun(container, buttonRunExample3, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample4(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_4_n));
            this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_4_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_4));
            //
            onButtonRun(container, buttonRunExample4, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample5(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_5_n));
            this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_5_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_5));
            //
            onButtonRun(container, buttonRunExample5, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample6(ViewGroup container) {
        try {
            this.editTextN.setText(requireContext().getText(R.string.mod_factors_example_6_n));
            this.editTextA.setText(requireContext().getText(R.string.mod_factors_example_6_a));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_6));
            //
            onButtonRun(container, buttonRunExample6, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private InputGroup getInputGroupN() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelN, "n", textViewLabelElasticN)
                .setInput(editTextN)
                .setCompactControls(textViewLabelCompactN, editTextCompactN)
                .build();
    }
    private InputGroup getInputGroupA() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelA, "a", textViewLabelElasticA)
                .setInput(editTextA)
                .setCompactControls(textViewLabelCompactA, editTextCompactA)
                .build();
    }
    private void onButtonRun(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            // Check.
            InputGroup inputGroupN = getInputGroupN();
            InputGroup inputGroupA = getInputGroupA();
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupN, BigInteger.ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), inputGroupA, TWO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger n = new BigInteger(editTextN.getText().toString());
            BigInteger a = new BigInteger(editTextA.getText().toString());

            // Reset result
            resetResult(skipLabelResult);

            // Before action performing.
            beforeActionPerforming(button);

            // Perform the mod factors
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.MOD_FACTORS, this);
            algorithmParameters.setInput1(n);
            algorithmParameters.setInput2(a);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonCountRun(ViewGroup container) {
        try {
            // Check.
            InputGroup inputGroupN = getInputGroupN();
            InputGroup inputGroupA = getInputGroupA();
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupN, BigInteger.ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), inputGroupA, TWO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger n = new BigInteger(editTextN.getText().toString());
            BigInteger a = new BigInteger(editTextA.getText().toString());

            // Reset result
            resetResult(false);

            // Before action performing.
            beforeActionPerforming(buttonCountRun);

            // Perform the mod factors
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.MOD_FACTORS_COUNT, this);
            algorithmParameters.setInput1(n);
            algorithmParameters.setInput2(a);
            progressManager.startWork(container, algorithmParameters);
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
        editTextN.clearFocus();
        editTextA.clearFocus();
        editTextCompactN.clearFocus();
        editTextCompactA.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastButtonClicked() {
        resetAllAndSelectTheLastButtonClicked(null);
    }
    private void resetAllAndSelectTheLastButtonClicked(TextView textView) {
        textViewMinusN.setSelected(false);
        textViewPlusN.setSelected(false);
        textViewCopyN.setSelected(false);
        textViewPasteN.setSelected(false);
        textViewClearN.setSelected(false);
        textViewMinusA.setSelected(false);
        textViewPlusA.setSelected(false);
        textViewCopyA.setSelected(false);
        textViewPasteA.setSelected(false);
        textViewClearA.setSelected(false);
        textViewMinusCompactN.setSelected(false);
        textViewPlusCompactN.setSelected(false);
        textViewCopyCompactN.setSelected(false);
        textViewPasteCompactN.setSelected(false);
        textViewClearCompactN.setSelected(false);
        textViewMinusCompactA.setSelected(false);
        textViewPlusCompactA.setSelected(false);
        textViewCopyCompactA.setSelected(false);
        textViewPasteCompactA.setSelected(false);
        textViewClearCompactA.setSelected(false);
        //
        buttonRunExample1.setSelected(false);
        buttonRunExample2.setSelected(false);
        buttonRunExample3.setSelected(false);
        buttonRunExample4.setSelected(false);
        buttonRunExample5.setSelected(false);
        buttonRunExample6.setSelected(false);
        buttonRun.setSelected(false);
        buttonCountRun.setSelected(false);
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