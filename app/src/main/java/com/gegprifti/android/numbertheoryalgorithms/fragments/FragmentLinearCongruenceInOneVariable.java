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
    TextView textViewDocumentationFile;
    // Cache view state
    boolean isCompactInputView = false;
    // Extended input view
    LinearLayout linearLayoutExtendedInputView;
    TextView textViewLabelA;
    TextView textViewLabelElasticA;
    TextView textViewCopyA;
    TextView textViewPasteA;
    TextView textViewClearA;
    EditText editTextA;
    TextView textViewLabelB;
    TextView textViewLabelElasticB;
    TextView textViewCopyB;
    TextView textViewPasteB;
    TextView textViewClearB;
    EditText editTextB;
    TextView textViewLabelM;
    TextView textViewLabelElasticM;
    TextView textViewCopyM;
    TextView textViewPasteM;
    TextView textViewClearM;
    EditText editTextM;
    // Compact input view
    LinearLayout linearLayoutCompactInputView;
    TextView textViewLabelCompactA;
    TextView textViewCopyCompactA;
    TextView textViewPasteCompactA;
    TextView textViewClearCompactA;
    EditText editTextCompactA;
    TextView textViewLabelCompactB;
    TextView textViewCopyCompactB;
    TextView textViewPasteCompactB;
    TextView textViewClearCompactB;
    EditText editTextCompactB;
    TextView textViewLabelCompactM;
    TextView textViewCopyCompactM;
    TextView textViewPasteCompactM;
    TextView textViewClearCompactM;
    EditText editTextCompactM;
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
            textViewDocumentationFile = inflater.findViewById(R.id.TextViewDocumentationFile);
            // Extended input view
            linearLayoutExtendedInputView = inflater.findViewById(R.id.LinearLayoutExtendedInputView);
            textViewLabelA = inflater.findViewById(R.id.TextViewLabelA);
            textViewLabelElasticA = inflater.findViewById(R.id.TextViewLabelElasticA);
            textViewCopyA = inflater.findViewById(R.id.TextViewCopyA);
            textViewPasteA = inflater.findViewById(R.id.TextViewPasteA);
            textViewClearA = inflater.findViewById(R.id.TextViewClearA);
            editTextA = inflater.findViewById(R.id.EditTextA);
            textViewLabelB = inflater.findViewById(R.id.TextViewLabelB);
            textViewLabelElasticB = inflater.findViewById(R.id.TextViewLabelElasticB);
            textViewCopyB = inflater.findViewById(R.id.TextViewCopyB);
            textViewPasteB = inflater.findViewById(R.id.TextViewPasteB);
            textViewClearB = inflater.findViewById(R.id.TextViewClearB);
            editTextB = inflater.findViewById(R.id.EditTextB);
            textViewLabelM = inflater.findViewById(R.id.TextViewLabelM);
            textViewLabelElasticM = inflater.findViewById(R.id.TextViewLabelElasticM);
            textViewCopyM = inflater.findViewById(R.id.TextViewCopyM);
            textViewPasteM = inflater.findViewById(R.id.TextViewPasteM);
            textViewClearM = inflater.findViewById(R.id.TextViewClearM);
            editTextM = inflater.findViewById(R.id.EditTextM);
            // Compact input view
            linearLayoutCompactInputView = inflater.findViewById(R.id.LinearLayoutCompactInputView);
            textViewLabelCompactA = inflater.findViewById(R.id.TextViewLabelCompactA);
            textViewCopyCompactA = inflater.findViewById(R.id.TextViewCopyCompactA);
            textViewPasteCompactA = inflater.findViewById(R.id.TextViewPasteCompactA);
            textViewClearCompactA = inflater.findViewById(R.id.TextViewClearCompactA);
            editTextCompactA = inflater.findViewById(R.id.EditTextCompactA);
            textViewLabelCompactB = inflater.findViewById(R.id.TextViewLabelCompactB);
            textViewCopyCompactB = inflater.findViewById(R.id.TextViewCopyCompactB);
            textViewPasteCompactB = inflater.findViewById(R.id.TextViewPasteCompactB);
            textViewClearCompactB = inflater.findViewById(R.id.TextViewClearCompactB);
            editTextCompactB = inflater.findViewById(R.id.EditTextCompactB);
            textViewLabelCompactM = inflater.findViewById(R.id.TextViewLabelCompactM);
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
            textViewDocumentationFile.setOnClickListener(view -> DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.LINEAR_CONGRUENCE_IN_ONE_VARIABLE_PDF).show(getParentFragmentManager(), "LINEAR_CONGRUENCE_IN_ONE_VARIABLE_PDF"));

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
            textViewCopyA.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewCopyA);
            });
            textViewPasteA.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewPasteA);
            });
            textViewClearA.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewClearA);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Extended input b clipboard button events
            textViewCopyB.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewCopyB);
            });
            textViewPasteB.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewPasteB);
            });
            textViewClearB.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewClearB);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Extended input m clipboard button events
            textViewCopyM.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewCopyM);
            });
            textViewPasteM.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewPasteM);
            });
            textViewClearM.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewClearM);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Compact input a clipboard button events
            textViewCopyCompactA.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextCompactA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewCopyCompactA);
            });
            textViewPasteCompactA.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextCompactA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewPasteCompactA);
            });
            textViewClearCompactA.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactA);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewClearCompactA);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Compact input b clipboard button events
            textViewCopyCompactB.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextCompactB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewCopyCompactB);
            });
            textViewPasteCompactB.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextCompactB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewPasteCompactB);
            });
            textViewClearCompactB.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactB);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewClearCompactB);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Compact input m clipboard button events
            textViewCopyCompactM.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextCompactM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewCopyCompactM);
            });
            textViewPasteCompactM.setOnClickListener(v -> {
                UIHelper.pasteEditText(requireContext(), editTextCompactM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewPasteCompactM);
            });
            textViewClearCompactM.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextCompactM);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewClearCompactM);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Run button events
            buttonRun.setOnClickListener(v -> onButtonRun(container, buttonRun, false));
            buttonRunExample1.setOnClickListener(v -> onButtonRunExample1(container));
            buttonRunExample2.setOnClickListener(v -> onButtonRunExample2(container));
            buttonRunExample3.setOnClickListener(v -> onButtonRunExample3(container));

            // Result clipboard button events
            textViewExpandResult.setOnClickListener(v -> {
                PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), editTextResult.getText());
                popupResult.show();
                resetAllAndSelectTheLastClipboardButtonClicked(textViewExpandResult);
            });
            textViewCopyResult.setOnClickListener(v -> {
                UIHelper.copyEditText(requireContext(), editTextResult);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewCopyResult);
            });
            textViewClearResult.setOnClickListener(v -> {
                UIHelper.clearEditText(requireContext(), editTextResult);
                resetAllAndSelectTheLastClipboardButtonClicked(textViewClearResult);
                resetAllAndSelectTheLastButtonClicked();
            });

            // Result events
            editTextResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.toString().isEmpty()) {
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


    private void refreshBiggerControls() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons
            ControlDisplay.setClipboardButtonFontSize(textViewCopyA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteCompactB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearCompactB, biggerControls);
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
    private void resetAllAndSelectTheLastClipboardButtonClicked() {
        resetAllAndSelectTheLastClipboardButtonClicked(null);
    }
    private void resetAllAndSelectTheLastClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewCopyA.setSelected(false);
        textViewPasteA.setSelected(false);
        textViewClearA.setSelected(false);
        textViewCopyB.setSelected(false);
        textViewPasteB.setSelected(false);
        textViewClearB.setSelected(false);
        textViewCopyM.setSelected(false);
        textViewPasteM.setSelected(false);
        textViewClearM.setSelected(false);
        //
        textViewCopyCompactA.setSelected(false);
        textViewPasteCompactA.setSelected(false);
        textViewClearCompactA.setSelected(false);
        textViewCopyCompactB.setSelected(false);
        textViewPasteCompactB.setSelected(false);
        textViewClearCompactB.setSelected(false);
        textViewCopyCompactM.setSelected(false);
        textViewPasteCompactM.setSelected(false);
        textViewClearCompactM.setSelected(false);
        //
        textViewExpandResult.setSelected(false);
        textViewCopyResult.setSelected(false);
        textViewClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void resetAllAndSelectTheLastButtonClicked() {
        resetAllAndSelectTheLastButtonClicked(null);
    }
    private void resetAllAndSelectTheLastButtonClicked(Button button) {
        buttonRun.setSelected(false);
        buttonRunExample1.setSelected(false);
        buttonRunExample2.setSelected(false);
        buttonRunExample3.setSelected(false);
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
            textViewLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextResult.setText("");
    }
    //endregion RESULT
}