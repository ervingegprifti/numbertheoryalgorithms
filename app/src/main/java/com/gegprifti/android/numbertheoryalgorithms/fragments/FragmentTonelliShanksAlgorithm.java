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


/**
 * Quadratic residue modulo
 * @see <a href="http://www.math.vt.edu/people/brown/doc/sqrts.pdf">sqrt</a>
 */
public class FragmentTonelliShanksAlgorithm extends FragmentBase implements Callback {
    private final static String TAG = FragmentTonelliShanksAlgorithm.class.getSimpleName();
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
    TextView textViewLabelP;
    TextView textViewLabelElasticP;
    TextView textViewMinusP;
    TextView textViewPlusP;
    TextView textViewCopyP;
    TextView textViewPasteP;
    TextView textViewClearP;
    EditText editTextP;
    // Compact input view
    LinearLayout linearLayoutCompactInputView;
    TextView textViewLabelCompactA;
    EditText editTextCompactA;
    TextView textViewMinusCompactA;
    TextView textViewPlusCompactA;
    TextView textViewCopyCompactA;
    TextView textViewPasteCompactA;
    TextView textViewClearCompactA;
    TextView textViewLabelCompactP;
    EditText editTextCompactP;
    TextView textViewMinusCompactP;
    TextView textViewPlusCompactP;
    TextView textViewCopyCompactP;
    TextView textViewPasteCompactP;
    TextView textViewClearCompactP;
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
    AtomicBoolean isUpdatingEditTextP = new AtomicBoolean(false);
    AtomicBoolean isUpdatingEditTextCompactP = new AtomicBoolean(false);


    // Define the parent fragment
    private TabFragmentAlgorithms tabFragmentAlgorithms;
    // public TabFragmentAlgorithms getFragmentTabAlgorithms() { return tabFragmentAlgorithms; }
    public void setFragmentTabAlgorithms(TabFragmentAlgorithms tabFragmentAlgorithms) { this.tabFragmentAlgorithms = tabFragmentAlgorithms; }
    //
    // private final static BigInteger TWO = BigInteger.valueOf(2L);
    // private final static BigInteger THREE = BigInteger.valueOf(3L);
    // private final static BigInteger FOUR = BigInteger.valueOf(4L);

    // Important
    // All Fragment classes you create must have a public, no-arg constructor.
    // In general, the best practice is to simply never define any constructors at all and rely on Java to generate the default constructor for you.

    //region CREATE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View inflater = null;
        try {
            inflater = layoutInflater.inflate(R.layout.fragment_tonelli_shanks_algorithm, container, false);
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
            textViewLabelP = inflater.findViewById(R.id.TextViewLabelP);
            textViewLabelElasticP = inflater.findViewById(R.id.TextViewLabelElasticP);
            textViewMinusP = inflater.findViewById(R.id.TextViewMinusP);
            textViewPlusP = inflater.findViewById(R.id.TextViewPlusP);
            textViewCopyP = inflater.findViewById(R.id.TextViewCopyP);
            textViewPasteP = inflater.findViewById(R.id.TextViewPasteP);
            textViewClearP = inflater.findViewById(R.id.TextViewClearP);
            editTextP = inflater.findViewById(R.id.EditTextP);
            // Compact input view
            linearLayoutCompactInputView = inflater.findViewById(R.id.LinearLayoutCompactInputView);
            textViewLabelCompactA = inflater.findViewById(R.id.TextViewLabelCompactA);
            editTextCompactA = inflater.findViewById(R.id.EditTextCompactA);
            textViewMinusCompactA = inflater.findViewById(R.id.TextViewMinusCompactA);
            textViewPlusCompactA = inflater.findViewById(R.id.TextViewPlusCompactA);
            textViewCopyCompactA = inflater.findViewById(R.id.TextViewCopyCompactA);
            textViewPasteCompactA = inflater.findViewById(R.id.TextViewPasteCompactA);
            textViewClearCompactA = inflater.findViewById(R.id.TextViewClearCompactA);
            textViewLabelCompactP = inflater.findViewById(R.id.TextViewLabelCompactP);
            editTextCompactP = inflater.findViewById(R.id.EditTextCompactP);
            textViewMinusCompactP = inflater.findViewById(R.id.TextViewMinusCompactP);
            textViewPlusCompactP = inflater.findViewById(R.id.TextViewPlusCompactP);
            textViewCopyCompactP = inflater.findViewById(R.id.TextViewCopyCompactP);
            textViewPasteCompactP = inflater.findViewById(R.id.TextViewPasteCompactP);
            textViewClearCompactP = inflater.findViewById(R.id.TextViewClearCompactP);
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
            editTextP.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            // Constrain compact input
            editTextCompactA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextCompactP.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

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
            editTextP.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextP.get()) return; // editTextP is locked
                    // Other work
                    String labelText = "p" + UIHelper.getNrOfDigits(s.toString());
                    textViewLabelP.setText(labelText);
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextCompactP
                    isUpdatingEditTextCompactP.set(true); // Lock editTextCompactP
                    try {
                        editTextCompactP.setText(s.toString());
                        // editTextCompactP.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextCompactP.set(false); // Unlock editTextCompactP
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
            editTextCompactP.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // Prevent recursive updates
                    if (isUpdatingEditTextCompactP.get()) return; // editTextCompactP is locked
                    // Other work
                    resetResult(false);
                    resetAllAndSelectTheLastButtonClicked();
                    // Sync to editTextP
                    isUpdatingEditTextP.set(true); // Lock editTextP
                    try {
                        editTextP.setText(s.toString());
                        // editTextP.setSelection(s.length()); // Set cursor to the end
                    } finally {
                        isUpdatingEditTextP.set(false); // unlock editTextP
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

            // Extended input p clipboard button events
            textViewMinusP.setOnClickListener(v -> {
                decreaseByOne(editTextP);
                resetAllAndSelectTheLastButtonClicked(textViewMinusP);
            });
            textViewPlusP.setOnClickListener(v -> {
                increaseByOne(editTextP);
                resetAllAndSelectTheLastButtonClicked(textViewPlusP);
            });
            textViewCopyP.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextP);
                resetAllAndSelectTheLastButtonClicked(textViewCopyP);
            });
            textViewPasteP.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextP);
                resetAllAndSelectTheLastButtonClicked(textViewPasteP);
            });
            textViewClearP.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextP);
                resetAllAndSelectTheLastButtonClicked(textViewClearP);
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

            // Compact input p clipboard button events
            textViewMinusCompactP.setOnClickListener(v -> {
                decreaseByOne(editTextCompactP);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactP);
            });
            textViewPlusCompactP.setOnClickListener(v -> {
                increaseByOne(editTextCompactP);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactP);
            });
            textViewCopyCompactP.setOnClickListener(v -> {
                UIHelper.copyTextFromEditTextIntoClipboard(requireContext(), editTextCompactP);
                resetAllAndSelectTheLastButtonClicked(textViewCopyCompactP);
            });
            textViewPasteCompactP.setOnClickListener(v -> {
                UIHelper.pasteTextFromClipboardIntoEditText(requireContext(), editTextCompactP);
                resetAllAndSelectTheLastButtonClicked(textViewPasteCompactP);
            });
            textViewClearCompactP.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactP);
                resetAllAndSelectTheLastButtonClicked(textViewClearCompactP);
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
            menuInflater.inflate(R.menu.menu_fragment_tonelli_shanks_algorithm, menu);
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
            if (id == R.id.tonelli_shanks_menu_example_1) {
                editTextA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_1_a));
                editTextP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_1_p));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
                // ResultReset(true);
                // Reset the last button clicked.
                resetAllAndSelectTheLastButtonClicked();
                return true;
            }
            if (id == R.id.tonelli_shanks_menu_example_2) {
                editTextA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_a));
                editTextP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_p));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
                // ResultReset(true);
                // Reset the last button clicked.
                resetAllAndSelectTheLastButtonClicked();
                return true;
            }
            if (id == R.id.tonelli_shanks_menu_example_3) {
                editTextA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_a));
                editTextP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_p));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
                // ResultReset(true);
                // Reset the last button clicked.
                resetAllAndSelectTheLastButtonClicked();
                return true;
            }
            if (id == R.id.menu_documentation) {
                DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.TONELLI_SHANKS_ALGORITHM_PDF).show(getParentFragmentManager(), "TONELLI_SHANKS_ALGORITHM_PDF");
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
                textViewMinusP.setVisibility(View.VISIBLE);
                textViewPlusP.setVisibility(View.VISIBLE);
                textViewMinusCompactP.setVisibility(View.VISIBLE);
                textViewPlusCompactP.setVisibility(View.VISIBLE);
            } else {
                textViewMinusA.setVisibility(View.GONE);
                textViewPlusA.setVisibility(View.GONE);
                textViewMinusCompactA.setVisibility(View.GONE);
                textViewPlusCompactA.setVisibility(View.GONE);
                textViewMinusP.setVisibility(View.GONE);
                textViewPlusP.setVisibility(View.GONE);
                textViewMinusCompactP.setVisibility(View.GONE);
                textViewPlusCompactP.setVisibility(View.GONE);
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
                this.buttonRun.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_run_long));
                this.buttonRunExample1.setVisibility(View.GONE);
                this.buttonRunExample2.setVisibility(View.GONE);
                this.buttonRunExample3.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonRun.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_run_short));
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
            ControlDisplay.setClipboardButtonFontSize(textViewMinusP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactP, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult, biggerControls);
            // Extended input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticA, biggerControls);
            ControlDisplay.setInputFontSize(editTextA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelP, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelElasticP, biggerControls);
            ControlDisplay.setInputFontSize(editTextP, biggerControls);
            // Compact input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactA, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactP, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactP, biggerControls);
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
        if (algorithmName == AlgorithmName.TONELLI_SHANKS_ALGORITHM) {
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


    private InputGroup getInputGroupP() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelP, "p", textViewLabelElasticP)
                .setInput(editTextP)
                .setCompactControls(textViewLabelCompactP, editTextCompactP)
                .build();
    }


    private void onButtonRun(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            // Check.
            InputGroup inputGroupA = getInputGroupA();
            InputGroup inputGroupP = getInputGroupP();
            if(UIHelper.checkInputMustBeNumber(requireContext(), inputGroupA)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), inputGroupP, BigInteger.valueOf(2L))) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextA.getText().toString());
            BigInteger p = new BigInteger(editTextP.getText().toString());

            // Reset result
            resetResult(skipLabelResult);

            // Before action performing.
            beforeActionPerforming(button);

            // Perform the Tonelli Shanks Algorithm
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.TONELLI_SHANKS_ALGORITHM, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(p);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample1(ViewGroup container) {
        try {
            editTextA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_1_a));
            editTextP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_1_p));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            onButtonRun(container, buttonRunExample1, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample2(ViewGroup container) {
        try {
            editTextA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_a));
            editTextP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_p));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            onButtonRun(container, buttonRunExample2, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample3(ViewGroup container) {
        try {
            editTextA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_a));
            editTextP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_p));
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
        UIHelper.hideSoftKeyBoard(requireActivity());
        // Clear the focus.
        editTextA.clearFocus();
        editTextP.clearFocus();
        editTextCompactA.clearFocus();
        editTextCompactP.clearFocus();
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
        textViewMinusP.setSelected(false);
        textViewPlusP.setSelected(false);
        textViewCopyP.setSelected(false);
        textViewPasteP.setSelected(false);
        textViewClearP.setSelected(false);
        textViewMinusCompactA.setSelected(false);
        textViewPlusCompactA.setSelected(false);
        textViewCopyCompactA.setSelected(false);
        textViewPasteCompactA.setSelected(false);
        textViewClearCompactA.setSelected(false);
        textViewMinusCompactP.setSelected(false);
        textViewPlusCompactP.setSelected(false);
        textViewCopyCompactP.setSelected(false);
        textViewPasteCompactP.setSelected(false);
        textViewClearCompactP.setSelected(false);
        //
        buttonRun.setSelected(false);
        buttonRunExample1.setSelected(false);
        buttonRunExample2.setSelected(false);
        buttonRunExample3.setSelected(false);
        //
        textViewExpandResult.setSelected(false);
        textViewCopyResult.setSelected(false);
        textViewClearResult.setSelected(false);
        // Select the last button clicked.
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