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
import android.widget.TextView;
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


public class FragmentLinearCongruenceInOneVariable extends FragmentBase implements Callback {
    private final static String TAG = FragmentLinearCongruenceInOneVariable.class.getSimpleName();

    TextView textViewLinearCongruenceInOneVariableBackToAlgorithms;
    TextView textViewLinearCongruenceInOneVariableTitle;
    TextView textViewLinearCongruenceInOneVariableDocumentationFile;
    TextView textViewLinearCongruenceInOneVariableLabelA;
    TextView textViewLinearCongruenceInOneVariableLabelElasticA;
    TextView textViewLinearCongruenceInOneVariableCopyA;
    TextView textViewLinearCongruenceInOneVariablePasteA;
    TextView textViewLinearCongruenceInOneVariableClearA;
    EditText editTextLinearCongruenceInOneVariableA;
    TextView textViewLinearCongruenceInOneVariableLabelB;
    TextView textViewLinearCongruenceInOneVariableLabelElasticB;
    TextView textViewLinearCongruenceInOneVariableCopyB;
    TextView textViewLinearCongruenceInOneVariablePasteB;
    TextView textViewLinearCongruenceInOneVariableClearB;
    EditText editTextLinearCongruenceInOneVariableB;
    TextView textViewLinearCongruenceInOneVariableLabelM;
    TextView textViewLinearCongruenceInOneVariableLabelElasticM;
    TextView textViewLinearCongruenceInOneVariableCopyM;
    TextView textViewLinearCongruenceInOneVariablePasteM;
    TextView textViewLinearCongruenceInOneVariableClearM;
    EditText editTextLinearCongruenceInOneVariableM;
    Button buttonLinearCongruenceInOneVariableRun;
    Button buttonLinearCongruenceInOneVariableRunExample1;
    Button buttonLinearCongruenceInOneVariableRunExample2;
    Button buttonLinearCongruenceInOneVariableRunExample3;
    TextView textViewLinearCongruenceInOneVariableLabelResult;
    TextView textViewLinearCongruenceInOneVariableLabelElasticResult;
    TextView textViewLinearCongruenceInOneVariableExpandResult;
    TextView textViewLinearCongruenceInOneVariableCopyResult;
    TextView textViewLinearCongruenceInOneVariableClearResult;
    EditText editTextLinearCongruenceInOneVariableResult;
    boolean isCompactInputView = false;


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
            textViewLinearCongruenceInOneVariableBackToAlgorithms = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableBackToAlgorithms);
            textViewLinearCongruenceInOneVariableTitle = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableTitle);
            textViewLinearCongruenceInOneVariableDocumentationFile = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableDocumentationFile);
            textViewLinearCongruenceInOneVariableLabelA = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableLabelA);
            textViewLinearCongruenceInOneVariableLabelElasticA = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableLabelElasticA);
            textViewLinearCongruenceInOneVariableCopyA = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableCopyA);
            textViewLinearCongruenceInOneVariablePasteA = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariablePasteA);
            textViewLinearCongruenceInOneVariableClearA = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableClearA);
            editTextLinearCongruenceInOneVariableA = inflater.findViewById(R.id.EditTextLinearCongruenceInOneVariableA);
            textViewLinearCongruenceInOneVariableLabelB = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableLabelB);
            textViewLinearCongruenceInOneVariableLabelElasticB = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableLabelElasticB);
            textViewLinearCongruenceInOneVariableCopyB = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableCopyB);
            textViewLinearCongruenceInOneVariablePasteB = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariablePasteB);
            textViewLinearCongruenceInOneVariableClearB = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableClearB);
            editTextLinearCongruenceInOneVariableB = inflater.findViewById(R.id.EditTextLinearCongruenceInOneVariableB);
            textViewLinearCongruenceInOneVariableLabelM = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableLabelM);
            textViewLinearCongruenceInOneVariableLabelElasticM = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableLabelElasticM);
            textViewLinearCongruenceInOneVariableCopyM = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableCopyM);
            textViewLinearCongruenceInOneVariablePasteM = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariablePasteM);
            textViewLinearCongruenceInOneVariableClearM = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableClearM);
            editTextLinearCongruenceInOneVariableM = inflater.findViewById(R.id.EditTextLinearCongruenceInOneVariableM);
            buttonLinearCongruenceInOneVariableRun = inflater.findViewById(R.id.ButtonLinearCongruenceInOneVariableRun);
            buttonLinearCongruenceInOneVariableRunExample1 = inflater.findViewById(R.id.ButtonLinearCongruenceInOneVariableRunExample1);
            buttonLinearCongruenceInOneVariableRunExample2 = inflater.findViewById(R.id.ButtonLinearCongruenceInOneVariableRunExample2);
            buttonLinearCongruenceInOneVariableRunExample3 = inflater.findViewById(R.id.ButtonLinearCongruenceInOneVariableRunExample3);
            textViewLinearCongruenceInOneVariableLabelResult = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableLabelResult);
            textViewLinearCongruenceInOneVariableLabelElasticResult = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableLabelElasticResult);
            textViewLinearCongruenceInOneVariableExpandResult = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableExpandResult);
            textViewLinearCongruenceInOneVariableCopyResult = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableCopyResult);
            textViewLinearCongruenceInOneVariableClearResult = inflater.findViewById(R.id.TextViewLinearCongruenceInOneVariableClearResult);
            editTextLinearCongruenceInOneVariableResult = inflater.findViewById(R.id.EditTextLinearCongruenceInOneVariableResult);

            // InputGroup filter integer only
            editTextLinearCongruenceInOneVariableA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextLinearCongruenceInOneVariableB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextLinearCongruenceInOneVariableM.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Events
            textViewLinearCongruenceInOneVariableBackToAlgorithms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tabFragmentAlgorithms != null) {
                        // Go back to the algorithms main menu
                        FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                        tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                    }
                }
            });
            textViewLinearCongruenceInOneVariableDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.LINEAR_CONGRUENCE_IN_ONE_VARIABLE_PDF).show(getParentFragmentManager(), "LINEAR_CONGRUENCE_IN_ONE_VARIABLE_PDF");
                }
            });
            textViewLinearCongruenceInOneVariableCopyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInOneVariableA);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariableCopyA);
                }
            });
            textViewLinearCongruenceInOneVariableCopyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInOneVariableB);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariableCopyB);
                }
            });
            textViewLinearCongruenceInOneVariableCopyM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInOneVariableM);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariableCopyM);
                }
            });
            textViewLinearCongruenceInOneVariableCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextLinearCongruenceInOneVariableResult);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariableCopyResult);
                }
            });
            textViewLinearCongruenceInOneVariablePasteA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInOneVariableA);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariablePasteA);
                }
            });
            textViewLinearCongruenceInOneVariablePasteB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInOneVariableB);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariablePasteB);
                }
            });
            textViewLinearCongruenceInOneVariablePasteM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.pasteEditText(requireContext(), editTextLinearCongruenceInOneVariableM);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariablePasteM);
                }
            });
            textViewLinearCongruenceInOneVariableClearA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInOneVariableA);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariableClearA);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            textViewLinearCongruenceInOneVariableClearB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInOneVariableB);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariableClearB);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            textViewLinearCongruenceInOneVariableClearM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInOneVariableM);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariableClearM);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            textViewLinearCongruenceInOneVariableClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextLinearCongruenceInOneVariableResult);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariableClearResult);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            editTextLinearCongruenceInOneVariableA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s)
                {
                    String linearCongruenceInOneVariableLabelA = "a" + UIHelper.getNrOfDigits(s.toString());
                    textViewLinearCongruenceInOneVariableLabelA.setText(linearCongruenceInOneVariableLabelA);
                    // Reset
                    resetResult(false);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            editTextLinearCongruenceInOneVariableB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String linearCongruenceInOneVariableLabelB = "b" + UIHelper.getNrOfDigits(s.toString());
                    textViewLinearCongruenceInOneVariableLabelB.setText(linearCongruenceInOneVariableLabelB);
                    // Reset
                    resetResult(false);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            editTextLinearCongruenceInOneVariableM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String linearCongruenceInOneVariableLabelM = "m" + UIHelper.getNrOfDigits(s.toString());
                    textViewLinearCongruenceInOneVariableLabelM.setText(linearCongruenceInOneVariableLabelM);
                    // Reset
                    resetResult(false);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            buttonLinearCongruenceInOneVariableRun.setOnClickListener(v -> onButtonRun(container, buttonLinearCongruenceInOneVariableRun, false));
            buttonLinearCongruenceInOneVariableRunExample1.setOnClickListener(v -> onButtonRunExample1(container));
            buttonLinearCongruenceInOneVariableRunExample2.setOnClickListener(v -> onButtonRunExample2(container));
            buttonLinearCongruenceInOneVariableRunExample3.setOnClickListener(v -> onButtonRunExample3(container));
            textViewLinearCongruenceInOneVariableExpandResult.setOnClickListener(v -> {
                PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewLinearCongruenceInOneVariableTitle.getText().toString(), editTextLinearCongruenceInOneVariableResult.getText());
                popupResult.show();
                resetAllAndSelectTheLastClipboardButtonClicked(textViewLinearCongruenceInOneVariableExpandResult);
            });
            editTextLinearCongruenceInOneVariableResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.toString().isEmpty()) {
                        textViewLinearCongruenceInOneVariableExpandResult.setVisibility(View.GONE);
                    } else {
                        textViewLinearCongruenceInOneVariableExpandResult.setVisibility(View.VISIBLE);
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
                this.editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_a));
                this.editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_b));
                this.editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_m));
                this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_1));
                resetResult(true);
                return true;
            }
            if (id == R.id.linear_congruence_in_one_variable_menu_example_2) {
                this.editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_a));
                this.editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_b));
                this.editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_m));
                this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_2));
                resetResult(true);
                return true;
            }
            if (id == R.id.linear_congruence_in_one_variable_menu_example_3) {
                this.editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_a));
                this.editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_b));
                this.editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_m));
                this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_3));
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
        this.refreshBiggerControls();
        this.refreshHideExampleButtons();
    }


    //region Display
    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.buttonLinearCongruenceInOneVariableRunExample1.getVisibility() == View.VISIBLE; // Just check one.
            boolean hideExampleButtons = UserSettings.getHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonLinearCongruenceInOneVariableRun.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_run_long));
                this.buttonLinearCongruenceInOneVariableRunExample1.setVisibility(View.GONE);
                this.buttonLinearCongruenceInOneVariableRunExample2.setVisibility(View.GONE);
                this.buttonLinearCongruenceInOneVariableRunExample3.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonLinearCongruenceInOneVariableRun.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_run_short));
                this.buttonLinearCongruenceInOneVariableRunExample1.setVisibility(View.VISIBLE);
                this.buttonLinearCongruenceInOneVariableRunExample2.setVisibility(View.VISIBLE);
                this.buttonLinearCongruenceInOneVariableRunExample3.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshBiggerControls() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariableCopyA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariablePasteA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariableClearA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariableCopyB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariablePasteB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariableClearB, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariableCopyM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariablePasteM, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariableClearM, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariableExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariableCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewLinearCongruenceInOneVariableClearResult, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelElasticA, biggerControls);
            // InputGroup
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInOneVariableA, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelB, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelElasticB, biggerControls);
            // InputGroup
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInOneVariableB, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelM, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelElasticM, biggerControls);
            // InputGroup
            ControlDisplay.setInputFontSize(editTextLinearCongruenceInOneVariableM, biggerControls);
            // Buttons
            ControlDisplay.setButtonFontSize(buttonLinearCongruenceInOneVariableRun, biggerControls);
            ControlDisplay.setButtonFontSize(buttonLinearCongruenceInOneVariableRunExample1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonLinearCongruenceInOneVariableRunExample2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonLinearCongruenceInOneVariableRunExample3, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelElasticResult, biggerControls);
            // Output
            ControlDisplay.setOutputFontSize(editTextLinearCongruenceInOneVariableResult, biggerControls);
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
                editTextLinearCongruenceInOneVariableResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextLinearCongruenceInOneVariableResult.setText(resultFromHtml);
            }
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void onButtonRun(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextLinearCongruenceInOneVariableA, textViewLinearCongruenceInOneVariableLabelA, textViewLinearCongruenceInOneVariableLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextLinearCongruenceInOneVariableB, textViewLinearCongruenceInOneVariableLabelB, textViewLinearCongruenceInOneVariableLabelElasticB, "b")) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanMin(requireActivity(), editTextLinearCongruenceInOneVariableM, textViewLinearCongruenceInOneVariableLabelM, textViewLinearCongruenceInOneVariableLabelElasticM, "m", BigInteger.ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextLinearCongruenceInOneVariableA.getText().toString());
            BigInteger b = new BigInteger(editTextLinearCongruenceInOneVariableB.getText().toString());
            BigInteger m = new BigInteger(editTextLinearCongruenceInOneVariableM.getText().toString());

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
            editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_a));
            editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_b));
            editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_m));
            this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            onButtonRun(container, buttonLinearCongruenceInOneVariableRunExample1, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample2(ViewGroup container) {
        try {
            editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_a));
            editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_b));
            editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_m));
            this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            onButtonRun(container, buttonLinearCongruenceInOneVariableRunExample2, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample3(ViewGroup container) {
        try {
            editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_a));
            editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_b));
            editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_m));
            this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            onButtonRun(container, buttonLinearCongruenceInOneVariableRunExample3, true);
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
        editTextLinearCongruenceInOneVariableA.clearFocus();
        editTextLinearCongruenceInOneVariableB.clearFocus();
        editTextLinearCongruenceInOneVariableM.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewLinearCongruenceInOneVariableCopyA.setSelected(false);
        textViewLinearCongruenceInOneVariablePasteA.setSelected(false);
        textViewLinearCongruenceInOneVariableClearA.setSelected(false);
        textViewLinearCongruenceInOneVariableCopyB.setSelected(false);
        textViewLinearCongruenceInOneVariablePasteB.setSelected(false);
        textViewLinearCongruenceInOneVariableClearB.setSelected(false);
        textViewLinearCongruenceInOneVariableCopyM.setSelected(false);
        textViewLinearCongruenceInOneVariablePasteM.setSelected(false);
        textViewLinearCongruenceInOneVariableClearM.setSelected(false);
        textViewLinearCongruenceInOneVariableExpandResult.setSelected(false);
        textViewLinearCongruenceInOneVariableCopyResult.setSelected(false);
        textViewLinearCongruenceInOneVariableClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void resetAllAndSelectTheLastButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonLinearCongruenceInOneVariableRun.setSelected(false);
        buttonLinearCongruenceInOneVariableRunExample1.setSelected(false);
        buttonLinearCongruenceInOneVariableRunExample2.setSelected(false);
        buttonLinearCongruenceInOneVariableRunExample3.setSelected(false);
        // Select he last button clicked.
        if (button != null) {
            button.setSelected(true);
        }
    }
    private void resetResult(boolean skipLabelResult) {
        // Reset the last clipboard clicked.
        resetAllAndSelectTheLastClipboardButtonClicked(null);
        // Reset the last button clicked.
        resetAllAndSelectTheLastButtonClicked(null);
        //
        if(!skipLabelResult) {
            textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextLinearCongruenceInOneVariableResult.setText("");
    }
    //endregion RESULT

}