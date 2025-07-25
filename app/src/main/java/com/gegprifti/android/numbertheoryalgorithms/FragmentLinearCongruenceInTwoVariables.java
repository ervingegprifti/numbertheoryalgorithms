package com.gegprifti.android.numbertheoryalgorithms;


import android.app.Activity;
import android.graphics.Typeface;
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
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.gegprifti.android.numbertheoryalgorithms.common.ClipboardButtonDisplay;
import com.gegprifti.android.numbertheoryalgorithms.common.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.common.PopupDocumentation;
import com.gegprifti.android.numbertheoryalgorithms.common.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.common.Helper;
import com.gegprifti.android.numbertheoryalgorithms.common.UserSettings;
import java.math.BigInteger;


public class FragmentLinearCongruenceInTwoVariables extends FragmentBase implements Callback {
    private final static String TAG = FragmentLinearCongruenceInTwoVariables.class.getSimpleName();

    TextView textViewLinearCongruenceInTwoVariablesBackToAlgorithms;
    TextView textViewLinearCongruenceInTwoVariablesTitle;
    TextView textViewLinearCongruenceInTwoVariablesDocumentationFile;
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
    Button buttonLinearCongruenceInTwoVariablesRun;
    Button buttonLinearCongruenceInTwoVariablesRunExample1;
    Button buttonLinearCongruenceInTwoVariablesRunExample2;
    Button buttonLinearCongruenceInTwoVariablesRunExample3;
    TextView textViewLinearCongruenceInTwoVariablesLabelResult;
    TextView textViewLinearCongruenceInTwoVariablesLabelElasticResult;
    TextView textViewLinearCongruenceInTwoVariablesExpandResult;
    TextView textViewLinearCongruenceInTwoVariablesCopyResult;
    TextView textViewLinearCongruenceInTwoVariablesClearResult;
    EditText editTextLinearCongruenceInTwoVariablesResult;

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
            inflater = layoutInflater.inflate(R.layout.fragment_linear_congruence_in_two_variables, container, false);
            textViewLinearCongruenceInTwoVariablesBackToAlgorithms = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesBackToAlgorithms);
            textViewLinearCongruenceInTwoVariablesTitle = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesTitle);
            textViewLinearCongruenceInTwoVariablesDocumentationFile = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesDocumentationFile);
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
            buttonLinearCongruenceInTwoVariablesRun = inflater.findViewById(R.id.ButtonLinearCongruenceInTwoVariablesRun);
            buttonLinearCongruenceInTwoVariablesRunExample1 = inflater.findViewById(R.id.ButtonLinearCongruenceInTwoVariablesRunExample1);
            buttonLinearCongruenceInTwoVariablesRunExample2 = inflater.findViewById(R.id.ButtonLinearCongruenceInTwoVariablesRunExample2);
            buttonLinearCongruenceInTwoVariablesRunExample3 = inflater.findViewById(R.id.ButtonLinearCongruenceInTwoVariablesRunExample3);
            textViewLinearCongruenceInTwoVariablesLabelResult = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelResult);
            textViewLinearCongruenceInTwoVariablesLabelElasticResult = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesLabelElasticResult);
            textViewLinearCongruenceInTwoVariablesExpandResult = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesExpandResult);
            textViewLinearCongruenceInTwoVariablesCopyResult = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesCopyResult);
            textViewLinearCongruenceInTwoVariablesClearResult = inflater.findViewById(R.id.TextViewLinearCongruenceInTwoVariablesClearResult);
            editTextLinearCongruenceInTwoVariablesResult = inflater.findViewById(R.id.EditTextLinearCongruenceInTwoVariablesResult);

            // Input filter integer only
            editTextLinearCongruenceInTwoVariablesA.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});
            editTextLinearCongruenceInTwoVariablesB.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});
            editTextLinearCongruenceInTwoVariablesC.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});
            editTextLinearCongruenceInTwoVariablesM.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});

            // Default UI status
            // Check if this is the first time the user is using this.
            if (UserSettings.getFirstTimeLC2V(requireContext())) {
                this.OnButtonRunExample2(container, false);
            }

            // Events
            textViewLinearCongruenceInTwoVariablesBackToAlgorithms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(fragmentTabAlgorithms != null) {
                        // Go back to the algorithms main menu
                        FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                        fragmentTabAlgorithms.SetFragment(fragmentAlgorithms);
                    }
                }
            });
            textViewLinearCongruenceInTwoVariablesDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupDocumentation popupDocumentation = new PopupDocumentation(requireActivity(), requireContext(), PopupDocumentation.LINEAR_CONGRUENCE_IN_TWO_VARIABLES_PDF);
                    popupDocumentation.Show();
                }
            });
            textViewLinearCongruenceInTwoVariablesCopyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesA);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyA);
                }
            });
            textViewLinearCongruenceInTwoVariablesCopyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesB);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyB);
                }
            });
            textViewLinearCongruenceInTwoVariablesCopyC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesC);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyC);
                }
            });
            textViewLinearCongruenceInTwoVariablesCopyM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesM);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyM);
                }
            });
            textViewLinearCongruenceInTwoVariablesCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearCongruenceInTwoVariablesResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesCopyResult);
                }
            });
            textViewLinearCongruenceInTwoVariablesPasteA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesA);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteA);
                }
            });
            textViewLinearCongruenceInTwoVariablesPasteB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesB);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteB);
                }
            });
            textViewLinearCongruenceInTwoVariablesPasteC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesC);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteC);
                }
            });
            textViewLinearCongruenceInTwoVariablesPasteM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextLinearCongruenceInTwoVariablesM);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesPasteM);
                }
            });
            textViewLinearCongruenceInTwoVariablesClearA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesA);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearA);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewLinearCongruenceInTwoVariablesClearB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesB);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearB);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewLinearCongruenceInTwoVariablesClearC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesC);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearC);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewLinearCongruenceInTwoVariablesClearM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesM);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearM);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewLinearCongruenceInTwoVariablesClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearCongruenceInTwoVariablesResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesClearResult);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextLinearCongruenceInTwoVariablesA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s)
                {
                    String linearCongruenceInTwoVariablesLabelA = "a" + Helper.GetNrOfDigits(s.toString());
                    textViewLinearCongruenceInTwoVariablesLabelA.setText(linearCongruenceInTwoVariablesLabelA);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextLinearCongruenceInTwoVariablesB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String linearCongruenceInTwoVariablesLabelB = "b" + Helper.GetNrOfDigits(s.toString());
                    textViewLinearCongruenceInTwoVariablesLabelB.setText(linearCongruenceInTwoVariablesLabelB);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextLinearCongruenceInTwoVariablesC.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String linearCongruenceInTwoVariablesLabelB = "c" + Helper.GetNrOfDigits(s.toString());
                    textViewLinearCongruenceInTwoVariablesLabelC.setText(linearCongruenceInTwoVariablesLabelB);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextLinearCongruenceInTwoVariablesM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String linearCongruenceInTwoVariablesLabelM = "m" + Helper.GetNrOfDigits(s.toString());
                    textViewLinearCongruenceInTwoVariablesLabelM.setText(linearCongruenceInTwoVariablesLabelM);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            buttonLinearCongruenceInTwoVariablesRun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRun(container, buttonLinearCongruenceInTwoVariablesRun, false, true);
                }
            });
            buttonLinearCongruenceInTwoVariablesRunExample1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample1(container, true);
                }
            });
            buttonLinearCongruenceInTwoVariablesRunExample2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample2(container, true);
                }
            });
            buttonLinearCongruenceInTwoVariablesRunExample3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample3(container, true);
                }
            });
            textViewLinearCongruenceInTwoVariablesExpandResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewLinearCongruenceInTwoVariablesTitle.getText().toString(), editTextLinearCongruenceInTwoVariablesResult.getText());
                    popupResult.Show();
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearCongruenceInTwoVariablesExpandResult);
                }
            });
            editTextLinearCongruenceInTwoVariablesResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
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
                ResultReset(true);
                return true;
            }
            if (id == R.id.linear_congruence_in_two_variables_menu_example_2) {
                this.editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_a));
                this.editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_b));
                this.editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_c));
                this.editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_m));
                this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_2));
                ResultReset(true);
                return true;
            }
            if (id == R.id.linear_congruence_in_two_variables_menu_example_3) {
                this.editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_a));
                this.editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_b));
                this.editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_c));
                this.editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_m));
                this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_3));
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
            Typeface currentTypeface = editTextLinearCongruenceInTwoVariablesResult.getTypeface();
            boolean showResultInMonospace = UserSettings.GetShowResultInMonospace(requireContext());
            if (currentTypeface != Typeface.MONOSPACE && showResultInMonospace) {
                editTextLinearCongruenceInTwoVariablesResult.setTypeface(Typeface.MONOSPACE);
            } else if (currentTypeface == Typeface.MONOSPACE && !showResultInMonospace) {
                editTextLinearCongruenceInTwoVariablesResult.setTypeface(Typeface.DEFAULT);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.buttonLinearCongruenceInTwoVariablesRunExample1.getVisibility() == View.VISIBLE; // Just check one.
            boolean hideExampleButtons = UserSettings.GetHideExampleButtons(requireContext());
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
    private void refreshSmallerClipboardButtons() {
        try {
            boolean smallerClipboardButtons = UserSettings.GetSmallerClipboardButtons(requireContext());

            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyA, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteA, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearA, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyB, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteB, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearB, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyC, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteC, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearC, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyM, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesPasteM, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearM, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesExpandResult, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesCopyResult, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearCongruenceInTwoVariablesClearResult, smallerClipboardButtons);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerControls() {
        try {
            boolean smallerControls = UserSettings.GetSmallerControls(requireContext());

            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelA, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelElasticA, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextLinearCongruenceInTwoVariablesA, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelB, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelElasticB, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextLinearCongruenceInTwoVariablesB, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelC, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelElasticC, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextLinearCongruenceInTwoVariablesC, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelM, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelElasticM, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextLinearCongruenceInTwoVariablesM, smallerControls);
            // Buttons
            ControlDisplay.SetButtonFontSize(buttonLinearCongruenceInTwoVariablesRun, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonLinearCongruenceInTwoVariablesRunExample1, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonLinearCongruenceInTwoVariablesRunExample2, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonLinearCongruenceInTwoVariablesRunExample3, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelResult, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearCongruenceInTwoVariablesLabelElasticResult, smallerControls);
            // Output
            ControlDisplay.SetOutputFontSize(editTextLinearCongruenceInTwoVariablesResult, smallerControls);
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
        if (algorithmName == AlgorithmName.LINEAR_CONGRUENCE_IN_TWO_VARIABLES) {
            if (algorithmStatus == AlgorithmStatus.CANCELED) {
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
    private void OnButtonRun(ViewGroup container, Button button, boolean skipLabelResult, boolean displayProgressDialog) {
        try {
            // Check.
            if(Helper.checkInputMustBeNumber(requireContext(), editTextLinearCongruenceInTwoVariablesA, textViewLinearCongruenceInTwoVariablesLabelA, textViewLinearCongruenceInTwoVariablesLabelElasticA, "a")) {
                return;
            }
            if(Helper.checkInputMustBeNumber(requireContext(), editTextLinearCongruenceInTwoVariablesB, textViewLinearCongruenceInTwoVariablesLabelB, textViewLinearCongruenceInTwoVariablesLabelElasticB, "b")) {
                return;
            }
            if(Helper.checkInputMustBeNumber(requireContext(), editTextLinearCongruenceInTwoVariablesC, textViewLinearCongruenceInTwoVariablesLabelC, textViewLinearCongruenceInTwoVariablesLabelElasticC, "c")) {
                return;
            }
            if(Helper.checkInputMustBeGreaterThanMin(requireActivity(), editTextLinearCongruenceInTwoVariablesM, textViewLinearCongruenceInTwoVariablesLabelM, textViewLinearCongruenceInTwoVariablesLabelElasticM, "m", BigInteger.ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextLinearCongruenceInTwoVariablesA.getText().toString());
            BigInteger b = new BigInteger(editTextLinearCongruenceInTwoVariablesB.getText().toString());
            BigInteger c = new BigInteger(editTextLinearCongruenceInTwoVariablesC.getText().toString());
            BigInteger m = new BigInteger(editTextLinearCongruenceInTwoVariablesM.getText().toString());

            // Reset result
            ResultReset(skipLabelResult);

            // Before action performing.
            BeforeActionPerforming(button);

            boolean showResultInMonospace = UserSettings.GetShowResultInMonospace(requireContext());

            // Perform the linear congruence solver
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.LINEAR_CONGRUENCE_IN_TWO_VARIABLES, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(c);
            algorithmParameters.setInput4(m);
            algorithmParameters.setShowResultInMonospace(showResultInMonospace);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample1(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_a));
            editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_b));
            editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_c));
            editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_1_m));
            this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            OnButtonRun(container, buttonLinearCongruenceInTwoVariablesRunExample1, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample2(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_a));
            editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_b));
            editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_c));
            editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_2_m));
            this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            OnButtonRun(container, buttonLinearCongruenceInTwoVariablesRunExample2, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample3(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextLinearCongruenceInTwoVariablesA.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_a));
            editTextLinearCongruenceInTwoVariablesB.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_b));
            editTextLinearCongruenceInTwoVariablesC.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_c));
            editTextLinearCongruenceInTwoVariablesM.setText(requireContext().getText(R.string.linear_congruence_in_two_variables_example_3_m));
            this.textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            OnButtonRun(container, buttonLinearCongruenceInTwoVariablesRunExample3, true, displayProgressDialog);
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
        editTextLinearCongruenceInTwoVariablesA.clearFocus();
        editTextLinearCongruenceInTwoVariablesB.clearFocus();
        editTextLinearCongruenceInTwoVariablesC.clearFocus();
        editTextLinearCongruenceInTwoVariablesM.clearFocus();
        // Select the last button clicked.
        ResetAllAndSelectButtonClicked(button);
    }
    private void ResetAllAndSelectClipboardButtonClicked(TextView textView) {
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
        textViewLinearCongruenceInTwoVariablesExpandResult.setSelected(false);
        textViewLinearCongruenceInTwoVariablesCopyResult.setSelected(false);
        textViewLinearCongruenceInTwoVariablesClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void ResetAllAndSelectButtonClicked(Button button) {
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
    private void ResultReset(boolean skipLabelResult) {
        // Reset the last clipboard clicked.
        ResetAllAndSelectClipboardButtonClicked(null);
        // Reset the last button clicked.
        ResetAllAndSelectButtonClicked(null);
        //
        if(!skipLabelResult) {
            textViewLinearCongruenceInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextLinearCongruenceInTwoVariablesResult.setText("");
    }
    //endregion RESULT

}