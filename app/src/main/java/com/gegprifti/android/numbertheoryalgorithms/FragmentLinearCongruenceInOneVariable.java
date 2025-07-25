package com.gegprifti.android.numbertheoryalgorithms;


import android.app.Activity;
import android.graphics.Typeface;
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
import com.gegprifti.android.numbertheoryalgorithms.common.ClipboardButtonDisplay;
import com.gegprifti.android.numbertheoryalgorithms.common.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.common.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.common.Helper;
import com.gegprifti.android.numbertheoryalgorithms.common.PopupDocumentation;
import com.gegprifti.android.numbertheoryalgorithms.common.UserSettings;
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

    // Define the parent fragment
    private FragmentTabAlgorithms fragmentTabAlgorithms;
    // public FragmentTabAlgorithms getFragmentTabAlgorithms() { return fragmentTabAlgorithms; }
    public void setFragmentTabAlgorithms(FragmentTabAlgorithms fragmentTabAlgorithms) { this.fragmentTabAlgorithms = fragmentTabAlgorithms; }

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

            // Input filter integer only
            editTextLinearCongruenceInOneVariableA.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});
            editTextLinearCongruenceInOneVariableB.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});
            editTextLinearCongruenceInOneVariableM.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});

            // Default UI status
            // Check if this is the first time the user is using this.
            if (UserSettings.getFirstTimeLC1V(requireContext())) {
                this.OnButtonRunExample3(container, false);
            }

            // Events
            textViewLinearCongruenceInOneVariableBackToAlgorithms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(fragmentTabAlgorithms != null) {
                        // Go back to the algorithms main menu
                        FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                        fragmentTabAlgorithms.SetFragment(fragmentAlgorithms);
                    }
                }
            });
            textViewLinearCongruenceInOneVariableDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupDocumentation popupDocumentation = new PopupDocumentation(requireActivity(), requireContext(), PopupDocumentation.LINEAR_CONGRUENCE_IN_ONE_VARIABLE_PDF);
                    popupDocumentation.Show();
                }
            });
            textViewLinearCongruenceInOneVariableCopyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearCongruenceInOneVariableA);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariableCopyA);
                }
            });
            textViewLinearCongruenceInOneVariableCopyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearCongruenceInOneVariableB);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariableCopyB);
                }
            });
            textViewLinearCongruenceInOneVariableCopyM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearCongruenceInOneVariableM);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariableCopyM);
                }
            });
            textViewLinearCongruenceInOneVariableCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearCongruenceInOneVariableResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariableCopyResult);
                }
            });
            textViewLinearCongruenceInOneVariablePasteA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextLinearCongruenceInOneVariableA);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariablePasteA);
                }
            });
            textViewLinearCongruenceInOneVariablePasteB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextLinearCongruenceInOneVariableB);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariablePasteB);
                }
            });
            textViewLinearCongruenceInOneVariablePasteM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextLinearCongruenceInOneVariableM);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariablePasteM);
                }
            });
            textViewLinearCongruenceInOneVariableClearA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearCongruenceInOneVariableA);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariableClearA);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewLinearCongruenceInOneVariableClearB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearCongruenceInOneVariableB);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariableClearB);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewLinearCongruenceInOneVariableClearM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearCongruenceInOneVariableM);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariableClearM);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewLinearCongruenceInOneVariableClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearCongruenceInOneVariableResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariableClearResult);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
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
                    String linearCongruenceInOneVariableLabelA = "a" + Helper.GetNrOfDigits(s.toString());
                    textViewLinearCongruenceInOneVariableLabelA.setText(linearCongruenceInOneVariableLabelA);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
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
                    String linearCongruenceInOneVariableLabelB = "b" + Helper.GetNrOfDigits(s.toString());
                    textViewLinearCongruenceInOneVariableLabelB.setText(linearCongruenceInOneVariableLabelB);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
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
                    String linearCongruenceInOneVariableLabelM = "m" + Helper.GetNrOfDigits(s.toString());
                    textViewLinearCongruenceInOneVariableLabelM.setText(linearCongruenceInOneVariableLabelM);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            buttonLinearCongruenceInOneVariableRun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRun(container, buttonLinearCongruenceInOneVariableRun, false, true);
                }
            });
            buttonLinearCongruenceInOneVariableRunExample1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample1(container, true);
                }
            });
            buttonLinearCongruenceInOneVariableRunExample2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample2(container, true);
                }
            });
            buttonLinearCongruenceInOneVariableRunExample3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample3(container, true);
                }
            });
            textViewLinearCongruenceInOneVariableExpandResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewLinearCongruenceInOneVariableTitle.getText().toString(), editTextLinearCongruenceInOneVariableResult.getText());
                    popupResult.Show();
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInOneVariableExpandResult);
                }
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
                ResultReset(true);
                return true;
            }
            if (id == R.id.linear_congruence_in_one_variable_menu_example_2) {
                this.editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_a));
                this.editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_b));
                this.editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_m));
                this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_2));
                ResultReset(true);
                return true;
            }
            if (id == R.id.linear_congruence_in_one_variable_menu_example_3) {
                this.editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_a));
                this.editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_b));
                this.editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_m));
                this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_3));
                ResultReset(true);
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
        this.refreshSmallerClipboardButtons();
        this.refreshSmallerControls();
        this.refreshHideExampleButtons();
        this.refreshShowResultInMonospace();
    }


    //region Display
    private void refreshShowResultInMonospace() {
        try {
            Typeface currentTypeface = editTextLinearCongruenceInOneVariableResult.getTypeface();
            boolean showResultInMonospace = UserSettings.GetShowResultInMonospace(requireContext());
            if (currentTypeface != Typeface.MONOSPACE && showResultInMonospace) {
                editTextLinearCongruenceInOneVariableResult.setTypeface(Typeface.MONOSPACE);
            } else if (currentTypeface == Typeface.MONOSPACE && !showResultInMonospace) {
                editTextLinearCongruenceInOneVariableResult.setTypeface(Typeface.DEFAULT);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.buttonLinearCongruenceInOneVariableRunExample1.getVisibility() == View.VISIBLE; // Just check one.
            boolean hideExampleButtons = UserSettings.GetHideExampleButtons(requireContext());
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
    private void refreshSmallerClipboardButtons() {
        try {
            boolean smallerClipboardButtons = UserSettings.GetSmallerClipboardButtons(requireContext());

            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariableCopyA, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariablePasteA, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariableClearA, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariableCopyB, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariablePasteB, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariableClearB, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariableCopyM, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariablePasteM, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariableClearM, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariableExpandResult, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariableCopyResult, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInOneVariableClearResult, smallerClipboardButtons);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerControls() {
        try {
            boolean smallerControls = UserSettings.GetSmallerControls(requireContext());

            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelA, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelElasticA, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextLinearCongruenceInOneVariableA, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelB, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelElasticB, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextLinearCongruenceInOneVariableB, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelM, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelElasticM, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextLinearCongruenceInOneVariableM, smallerControls);
            // Buttons
            ControlDisplay.SetButtonFontSize(buttonLinearCongruenceInOneVariableRun, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonLinearCongruenceInOneVariableRunExample1, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonLinearCongruenceInOneVariableRunExample2, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonLinearCongruenceInOneVariableRunExample3, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelResult, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInOneVariableLabelElasticResult, smallerControls);
            // Output
            ControlDisplay.SetOutputFontSize(editTextLinearCongruenceInOneVariableResult, smallerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Display


    //region Callback
    @Override
    public void callbackResult(AlgorithmName algorithmName, Object result, AlgorithmStatus algorithmStatus) {
        // This is to prevent the error: Non-fatal Exception: java.lang.IllegalStateException: Fragment FragmentPrimesList{94d7331} (36e8cdd6-9d00-4c2a-bd07-ab5550e2c88b) not attached to a context.
        // java.lang.IllegalStateException: Fragment not attached to Activity -> https://stackoverflow.com/questions/28672883/java-lang-illegalstateexception-fragment-not-attached-to-activity
        Activity activity = getActivity();
        if (activity == null || !this.isAdded()) {
            return;
        }
        if (algorithmName == AlgorithmName.LINEAR_CONGRUENCE_IN_ONE_VARIABLE) {
            if (algorithmStatus == AlgorithmStatus.CANCELED) {
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
    private void OnButtonRun(ViewGroup container, Button button, boolean skipLabelResult, boolean displayProgressDialog) {
        try {
            // Check.
            if(Helper.checkInputMustBeNumber(requireContext(), editTextLinearCongruenceInOneVariableA, textViewLinearCongruenceInOneVariableLabelA, textViewLinearCongruenceInOneVariableLabelElasticA, "a")) {
                return;
            }
            if(Helper.checkInputMustBeNumber(requireContext(), editTextLinearCongruenceInOneVariableB, textViewLinearCongruenceInOneVariableLabelB, textViewLinearCongruenceInOneVariableLabelElasticB, "b")) {
                return;
            }
            if(Helper.checkInputMustBeGreaterThanMin(requireActivity(), editTextLinearCongruenceInOneVariableM, textViewLinearCongruenceInOneVariableLabelM, textViewLinearCongruenceInOneVariableLabelElasticM, "m", BigInteger.ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextLinearCongruenceInOneVariableA.getText().toString());
            BigInteger b = new BigInteger(editTextLinearCongruenceInOneVariableB.getText().toString());
            BigInteger m = new BigInteger(editTextLinearCongruenceInOneVariableM.getText().toString());

            // Reset result
            ResultReset(skipLabelResult);

            // Before action performing.
            BeforeActionPerforming(button);

            boolean showResultInMonospace = UserSettings.GetShowResultInMonospace(requireContext());

            // Perform the linear congruence solver
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.LINEAR_CONGRUENCE_IN_ONE_VARIABLE, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(m);
            algorithmParameters.setShowResultInMonospace(showResultInMonospace);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample1(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_a));
            editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_b));
            editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_1_m));
            this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            OnButtonRun(container, buttonLinearCongruenceInOneVariableRunExample1, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample2(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_a));
            editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_b));
            editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_2_m));
            this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            OnButtonRun(container, buttonLinearCongruenceInOneVariableRunExample2, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample3(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextLinearCongruenceInOneVariableA.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_a));
            editTextLinearCongruenceInOneVariableB.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_b));
            editTextLinearCongruenceInOneVariableM.setText(requireContext().getText(R.string.linear_congruence_in_one_variable_example_3_m));
            this.textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            OnButtonRun(container, buttonLinearCongruenceInOneVariableRunExample3, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion BUTTON ACTIONS


    //region RESULT
    private void BeforeActionPerforming(Button button) {
        // Hide the keyboard.
        Helper.HideSoftKeyBoard(requireActivity());
        // Clear the focus.
        editTextLinearCongruenceInOneVariableA.clearFocus();
        editTextLinearCongruenceInOneVariableB.clearFocus();
        editTextLinearCongruenceInOneVariableM.clearFocus();
        // Select the last button clicked.
        ResetAllAndSelectButtonClicked(button);
    }
    private void ResetAllAndSelectClipboardButtonClicked(TextView textView) {
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
    private void ResetAllAndSelectButtonClicked(Button button) {
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
    private void ResultReset(boolean skipLabelResult) {
        // Reset the last clipboard clicked.
        ResetAllAndSelectClipboardButtonClicked(null);
        // Reset the last button clicked.
        ResetAllAndSelectButtonClicked(null);
        //
        if(!skipLabelResult) {
            textViewLinearCongruenceInOneVariableLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextLinearCongruenceInOneVariableResult.setText("");
    }
    //endregion RESULT

}