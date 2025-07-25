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


public class FragmentLinearDiophantineEquationInTwoVariables extends FragmentBase implements Callback {
    private final static String TAG = FragmentLinearDiophantineEquationInTwoVariables.class.getSimpleName();

    TextView textViewLinearDiophantineEquationInTwoVariablesBackToAlgorithms;
    TextView textViewLinearDiophantineEquationInTwoVariablesTitle;
    TextView textViewLinearDiophantineEquationInTwoVariablesDocumentationFile;
    TextView textViewLinearDiophantineEquationInTwoVariablesLabelA;
    TextView textViewLinearDiophantineEquationInTwoVariablesLabelElasticA;
    TextView textViewLinearDiophantineEquationInTwoVariablesCopyA;
    TextView textViewLinearDiophantineEquationInTwoVariablesPasteA;
    TextView textViewLinearDiophantineEquationInTwoVariablesClearA;
    EditText editTextLinearDiophantineEquationInTwoVariablesA;
    TextView textViewLinearDiophantineEquationInTwoVariablesLabelB;
    TextView textViewLinearDiophantineEquationInTwoVariablesLabelElasticB;
    TextView textViewLinearDiophantineEquationInTwoVariablesCopyB;
    TextView textViewLinearDiophantineEquationInTwoVariablesPasteB;
    TextView textViewLinearDiophantineEquationInTwoVariablesClearB;
    EditText editTextLinearDiophantineEquationInTwoVariablesB;
    TextView textViewLinearDiophantineEquationInTwoVariablesLabelC;
    TextView textViewLinearDiophantineEquationInTwoVariablesLabelElasticC;
    TextView textViewLinearDiophantineEquationInTwoVariablesCopyC;
    TextView textViewLinearDiophantineEquationInTwoVariablesPasteC;
    TextView textViewLinearDiophantineEquationInTwoVariablesClearC;
    EditText editTextLinearDiophantineEquationInTwoVariablesC;
    Button buttonLinearDiophantineEquationInTwoVariablesRun;
    Button buttonLinearDiophantineEquationInTwoVariablesRunExample1;
    Button buttonLinearDiophantineEquationInTwoVariablesRunExample2;
    Button buttonLinearDiophantineEquationInTwoVariablesRunExample3;
    TextView textViewLinearDiophantineEquationInTwoVariablesLabelResult;
    TextView textViewLinearDiophantineEquationInTwoVariablesLabelElasticResult;
    TextView textViewLinearDiophantineEquationInTwoVariablesExpandResult;
    TextView textViewLinearDiophantineEquationInTwoVariablesCopyResult;
    TextView textViewLinearDiophantineEquationInTwoVariablesClearResult;
    EditText editTextLinearDiophantineEquationInTwoVariablesResult;

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
            inflater = layoutInflater.inflate(R.layout.fragment_linear_diophantine_equation_in_two_variables, container, false);
            textViewLinearDiophantineEquationInTwoVariablesBackToAlgorithms = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesBackToAlgorithms);
            textViewLinearDiophantineEquationInTwoVariablesTitle = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesTitle);
            textViewLinearDiophantineEquationInTwoVariablesDocumentationFile = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesDocumentationFile);
            textViewLinearDiophantineEquationInTwoVariablesLabelA = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesLabelA);
            textViewLinearDiophantineEquationInTwoVariablesLabelElasticA = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesLabelElasticA);
            textViewLinearDiophantineEquationInTwoVariablesCopyA = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesCopyA);
            textViewLinearDiophantineEquationInTwoVariablesPasteA = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesPasteA);
            textViewLinearDiophantineEquationInTwoVariablesClearA = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesClearA);
            editTextLinearDiophantineEquationInTwoVariablesA = inflater.findViewById(R.id.EditTextLinearDiophantineEquationInTwoVariablesA);
            textViewLinearDiophantineEquationInTwoVariablesLabelB = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesLabelB);
            textViewLinearDiophantineEquationInTwoVariablesLabelElasticB = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesLabelElasticB);
            textViewLinearDiophantineEquationInTwoVariablesCopyB = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesCopyB);
            textViewLinearDiophantineEquationInTwoVariablesPasteB = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesPasteB);
            textViewLinearDiophantineEquationInTwoVariablesClearB = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesClearB);
            editTextLinearDiophantineEquationInTwoVariablesB = inflater.findViewById(R.id.EditTextLinearDiophantineEquationInTwoVariablesB);
            textViewLinearDiophantineEquationInTwoVariablesLabelC = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesLabelC);
            textViewLinearDiophantineEquationInTwoVariablesLabelElasticC = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesLabelElasticC);
            textViewLinearDiophantineEquationInTwoVariablesCopyC = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesCopyC);
            textViewLinearDiophantineEquationInTwoVariablesPasteC = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesPasteC);
            textViewLinearDiophantineEquationInTwoVariablesClearC = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesClearC);
            editTextLinearDiophantineEquationInTwoVariablesC = inflater.findViewById(R.id.EditTextLinearDiophantineEquationInTwoVariablesC);
            buttonLinearDiophantineEquationInTwoVariablesRun = inflater.findViewById(R.id.ButtonLinearDiophantineEquationInTwoVariablesRun);
            buttonLinearDiophantineEquationInTwoVariablesRunExample1 = inflater.findViewById(R.id.ButtonLinearDiophantineEquationInTwoVariablesRunExample1);
            buttonLinearDiophantineEquationInTwoVariablesRunExample2 = inflater.findViewById(R.id.ButtonLinearDiophantineEquationInTwoVariablesRunExample2);
            buttonLinearDiophantineEquationInTwoVariablesRunExample3 = inflater.findViewById(R.id.ButtonLinearDiophantineEquationInTwoVariablesRunExample3);
            textViewLinearDiophantineEquationInTwoVariablesLabelResult = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesLabelResult);
            textViewLinearDiophantineEquationInTwoVariablesLabelElasticResult = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesLabelElasticResult);
            textViewLinearDiophantineEquationInTwoVariablesExpandResult = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesExpandResult);
            textViewLinearDiophantineEquationInTwoVariablesCopyResult = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesCopyResult);
            textViewLinearDiophantineEquationInTwoVariablesClearResult = inflater.findViewById(R.id.TextViewLinearDiophantineEquationInTwoVariablesClearResult);
            editTextLinearDiophantineEquationInTwoVariablesResult = inflater.findViewById(R.id.EditTextLinearDiophantineEquationInTwoVariablesResult);

            // Input filter integer only
            editTextLinearDiophantineEquationInTwoVariablesA.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});
            editTextLinearDiophantineEquationInTwoVariablesB.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});
            editTextLinearDiophantineEquationInTwoVariablesC.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});

            // Default UI status
            // Check if this is the first time the user is using this.
            if (UserSettings.getFirstTimeLDE2V(requireContext())) {
                this.OnButtonRunExample2(container, false);
            }

            // Events
            textViewLinearDiophantineEquationInTwoVariablesBackToAlgorithms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(fragmentTabAlgorithms != null) {
                        // Go back to the algorithms main menu
                        FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                        fragmentTabAlgorithms.SetFragment(fragmentAlgorithms);
                    }
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupDocumentation popupDocumentation = new PopupDocumentation(requireActivity(), requireContext(), PopupDocumentation.LINEAR_DIOPHANTINE_EQUATION_IN_TWO_VARIABLES_PDF);
                    popupDocumentation.Show();
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesCopyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesA);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesCopyA);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesCopyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesB);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesCopyB);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesCopyC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesC);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesCopyC);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesCopyResult);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesPasteA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesA);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesPasteA);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesPasteB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesB);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesPasteB);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesPasteC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesC);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesPasteC);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesClearA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesA);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesClearA);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesClearB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesB);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesClearB);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesClearC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesC);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesClearC);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextLinearDiophantineEquationInTwoVariablesResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesClearResult);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextLinearDiophantineEquationInTwoVariablesA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s)
                {
                    String linearDiophantineEquationLabelA = "a" + Helper.GetNrOfDigits(s.toString());
                    textViewLinearDiophantineEquationInTwoVariablesLabelA.setText(linearDiophantineEquationLabelA);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextLinearDiophantineEquationInTwoVariablesB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String linearDiophantineEquationLabelB = "b" + Helper.GetNrOfDigits(s.toString());
                    textViewLinearDiophantineEquationInTwoVariablesLabelB.setText(linearDiophantineEquationLabelB);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextLinearDiophantineEquationInTwoVariablesC.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String linearDiophantineEquationLabelC = "c" + Helper.GetNrOfDigits(s.toString());
                    textViewLinearDiophantineEquationInTwoVariablesLabelC.setText(linearDiophantineEquationLabelC);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            buttonLinearDiophantineEquationInTwoVariablesRun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRun(container, buttonLinearDiophantineEquationInTwoVariablesRun, false, true);
                }
            });
            buttonLinearDiophantineEquationInTwoVariablesRunExample1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample1(container, true);
                }
            });
            buttonLinearDiophantineEquationInTwoVariablesRunExample2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample2(container, true);
                }
            });
            buttonLinearDiophantineEquationInTwoVariablesRunExample3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample3(container, true);
                }
            });
            textViewLinearDiophantineEquationInTwoVariablesExpandResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewLinearDiophantineEquationInTwoVariablesTitle.getText().toString(), editTextLinearDiophantineEquationInTwoVariablesResult.getText());
                    popupResult.Show();
                    ResetAllAndSelectClipboardButtonClicked(textViewLinearDiophantineEquationInTwoVariablesExpandResult);
                }
            });
            editTextLinearDiophantineEquationInTwoVariablesResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.toString().isEmpty()) {
                        textViewLinearDiophantineEquationInTwoVariablesExpandResult.setVisibility(View.GONE);
                    } else {
                        textViewLinearDiophantineEquationInTwoVariablesExpandResult.setVisibility(View.VISIBLE);
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
            menuInflater.inflate(R.menu.menu_fragment_linear_diophantine_equation_in_two_variables, menu);
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
            if (id == R.id.linear_diophantine_equation_menu_example_1) {
                this.editTextLinearDiophantineEquationInTwoVariablesA.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_1_a));
                this.editTextLinearDiophantineEquationInTwoVariablesB.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_1_b));
                this.editTextLinearDiophantineEquationInTwoVariablesC.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_1_c));
                this.textViewLinearDiophantineEquationInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_1));
                ResultReset(true);
                return true;
            }
            if (id == R.id.linear_diophantine_equation_menu_example_2) {
                this.editTextLinearDiophantineEquationInTwoVariablesA.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_2_a));
                this.editTextLinearDiophantineEquationInTwoVariablesB.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_2_b));
                this.editTextLinearDiophantineEquationInTwoVariablesC.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_2_c));
                this.textViewLinearDiophantineEquationInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_2));
                ResultReset(true);
                return true;
            }
            if (id == R.id.linear_diophantine_equation_menu_example_3) {
                this.editTextLinearDiophantineEquationInTwoVariablesA.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_3_a));
                this.editTextLinearDiophantineEquationInTwoVariablesB.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_3_b));
                this.editTextLinearDiophantineEquationInTwoVariablesC.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_3_c));
                this.textViewLinearDiophantineEquationInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_3));
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
            Typeface currentTypeface = editTextLinearDiophantineEquationInTwoVariablesResult.getTypeface();
            boolean showResultInMonospace = UserSettings.GetShowResultInMonospace(requireContext());
            if (currentTypeface != Typeface.MONOSPACE && showResultInMonospace) {
                editTextLinearDiophantineEquationInTwoVariablesResult.setTypeface(Typeface.MONOSPACE);
            } else if (currentTypeface == Typeface.MONOSPACE && !showResultInMonospace) {
                editTextLinearDiophantineEquationInTwoVariablesResult.setTypeface(Typeface.DEFAULT);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.buttonLinearDiophantineEquationInTwoVariablesRunExample1.getVisibility() == View.VISIBLE; // Just check one.
            boolean hideExampleButtons = UserSettings.GetHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonLinearDiophantineEquationInTwoVariablesRun.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_run_long));
                this.buttonLinearDiophantineEquationInTwoVariablesRunExample1.setVisibility(View.GONE);
                this.buttonLinearDiophantineEquationInTwoVariablesRunExample2.setVisibility(View.GONE);
                this.buttonLinearDiophantineEquationInTwoVariablesRunExample3.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonLinearDiophantineEquationInTwoVariablesRun.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_run_short));
                this.buttonLinearDiophantineEquationInTwoVariablesRunExample1.setVisibility(View.VISIBLE);
                this.buttonLinearDiophantineEquationInTwoVariablesRunExample2.setVisibility(View.VISIBLE);
                this.buttonLinearDiophantineEquationInTwoVariablesRunExample3.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerClipboardButtons() {
        try {
            boolean smallerClipboardButtons = UserSettings.GetSmallerClipboardButtons(requireContext());

            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesCopyA, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesPasteA, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesClearA, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesCopyB, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesPasteB, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesClearB, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesCopyC, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesPasteC, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesClearC, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesExpandResult, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesCopyResult, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewLinearDiophantineEquationInTwoVariablesClearResult, smallerClipboardButtons);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerControls() {
        try {
            boolean smallerControls = UserSettings.GetSmallerControls(requireContext());

            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearDiophantineEquationInTwoVariablesLabelA, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearDiophantineEquationInTwoVariablesLabelElasticA, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextLinearDiophantineEquationInTwoVariablesA, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearDiophantineEquationInTwoVariablesLabelB, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearDiophantineEquationInTwoVariablesLabelElasticB, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextLinearDiophantineEquationInTwoVariablesB, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearDiophantineEquationInTwoVariablesLabelC, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearDiophantineEquationInTwoVariablesLabelElasticC, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextLinearDiophantineEquationInTwoVariablesC, smallerControls);
            // Buttons
            ControlDisplay.SetButtonFontSize(buttonLinearDiophantineEquationInTwoVariablesRun, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonLinearDiophantineEquationInTwoVariablesRunExample1, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonLinearDiophantineEquationInTwoVariablesRunExample2, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonLinearDiophantineEquationInTwoVariablesRunExample3, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewLinearDiophantineEquationInTwoVariablesLabelResult, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewLinearDiophantineEquationInTwoVariablesLabelElasticResult, smallerControls);
            // Output
            ControlDisplay.SetOutputFontSize(editTextLinearDiophantineEquationInTwoVariablesResult, smallerControls);
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
        if (algorithmName == AlgorithmName.LINEAR_DIOPHANTINE_EQUATION_IN_TWO_VARIABLES) {
            if (algorithmStatus == AlgorithmStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextLinearDiophantineEquationInTwoVariablesResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextLinearDiophantineEquationInTwoVariablesResult.setText(resultFromHtml);
            }
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void OnButtonRun(ViewGroup container, Button button, boolean skipLabelResult, boolean displayProgressDialog) {
        try {
            // Check.
            if(Helper.checkInputMustBeOtherThanZero(requireContext(), editTextLinearDiophantineEquationInTwoVariablesA, textViewLinearDiophantineEquationInTwoVariablesLabelA, textViewLinearDiophantineEquationInTwoVariablesLabelElasticA, "a")) {
                return;
            }
            if(Helper.checkInputMustBeOtherThanZero(requireContext(), editTextLinearDiophantineEquationInTwoVariablesB, textViewLinearDiophantineEquationInTwoVariablesLabelB, textViewLinearDiophantineEquationInTwoVariablesLabelElasticB, "b")) {
                return;
            }
            if(Helper.checkInputMustBeNumber(requireContext(), editTextLinearDiophantineEquationInTwoVariablesC, textViewLinearDiophantineEquationInTwoVariablesLabelC, textViewLinearDiophantineEquationInTwoVariablesLabelElasticC, "c")) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextLinearDiophantineEquationInTwoVariablesA.getText().toString());
            BigInteger b = new BigInteger(editTextLinearDiophantineEquationInTwoVariablesB.getText().toString());
            BigInteger c = new BigInteger(editTextLinearDiophantineEquationInTwoVariablesC.getText().toString());

            // Reset result
            ResultReset(skipLabelResult);

            // Before action performing.
            BeforeActionPerforming(button);

            boolean showResultInMonospace = UserSettings.GetShowResultInMonospace(requireContext());

            // Perform the linear diophantine equation solver
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.LINEAR_DIOPHANTINE_EQUATION_IN_TWO_VARIABLES, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(c);
            algorithmParameters.setShowResultInMonospace(showResultInMonospace);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample1(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextLinearDiophantineEquationInTwoVariablesA.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_1_a));
            editTextLinearDiophantineEquationInTwoVariablesB.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_1_b));
            editTextLinearDiophantineEquationInTwoVariablesC.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_1_c));
            this.textViewLinearDiophantineEquationInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            OnButtonRun(container, buttonLinearDiophantineEquationInTwoVariablesRunExample1, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample2(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextLinearDiophantineEquationInTwoVariablesA.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_2_a));
            editTextLinearDiophantineEquationInTwoVariablesB.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_2_b));
            editTextLinearDiophantineEquationInTwoVariablesC.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_2_c));
            this.textViewLinearDiophantineEquationInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            OnButtonRun(container, buttonLinearDiophantineEquationInTwoVariablesRunExample2, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample3(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextLinearDiophantineEquationInTwoVariablesA.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_3_a));
            editTextLinearDiophantineEquationInTwoVariablesB.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_3_b));
            editTextLinearDiophantineEquationInTwoVariablesC.setText(requireContext().getText(R.string.linear_diophantine_equation_in_two_variables_example_3_c));
            this.textViewLinearDiophantineEquationInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            OnButtonRun(container, buttonLinearDiophantineEquationInTwoVariablesRunExample3, true, displayProgressDialog);
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
        editTextLinearDiophantineEquationInTwoVariablesA.clearFocus();
        editTextLinearDiophantineEquationInTwoVariablesB.clearFocus();
        editTextLinearDiophantineEquationInTwoVariablesC.clearFocus();
        // Select the last button clicked.
        ResetAllAndSelectButtonClicked(button);
    }
    private void ResetAllAndSelectClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewLinearDiophantineEquationInTwoVariablesCopyA.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesPasteA.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesClearA.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesCopyB.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesPasteB.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesClearB.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesCopyC.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesPasteC.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesClearC.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesExpandResult.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesCopyResult.setSelected(false);
        textViewLinearDiophantineEquationInTwoVariablesClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void ResetAllAndSelectButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonLinearDiophantineEquationInTwoVariablesRun.setSelected(false);
        buttonLinearDiophantineEquationInTwoVariablesRunExample1.setSelected(false);
        buttonLinearDiophantineEquationInTwoVariablesRunExample2.setSelected(false);
        buttonLinearDiophantineEquationInTwoVariablesRunExample3.setSelected(false);
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
            textViewLinearDiophantineEquationInTwoVariablesLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextLinearDiophantineEquationInTwoVariablesResult.setText("");
    }
    //endregion RESULT

}