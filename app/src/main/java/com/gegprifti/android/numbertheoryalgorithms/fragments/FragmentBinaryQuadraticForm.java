package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
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
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.GridAdapter;
import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.settings.ClipboardButtonDisplay;
import com.gegprifti.android.numbertheoryalgorithms.settings.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.RowItem;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class FragmentBinaryQuadraticForm extends FragmentBase implements Callback {
    private final static String TAG = FragmentBinaryQuadraticForm.class.getSimpleName();

    TextView textViewQuadraticFormBackToAlgorithms;
    TextView textViewQuadraticFormTitle;
    TextView textViewQuadraticFormDocumentationFile;
    TextView textViewQuadraticFormLabelExpression;
    TextView textViewQuadraticFormLabelElasticExpression;
    TextView textViewQuadraticFormCopyExpression;
    TextView textViewQuadraticFormPasteExpression;
    TextView textViewQuadraticFormClearExpression;
    EditText editTextQuadraticFormExpression;
    LinearLayout linearLayoutQuadraticFormExamplesContainer;
    Button buttonQuadraticFormRunExample1;
    Button buttonQuadraticFormRunExample2;
    Button buttonQuadraticFormRunExample3;
    Button buttonQuadraticFormRunExample4;
    Button buttonQuadraticFormRunExample5;
    Button buttonQuadraticFormRun;
    Button buttonQuadraticFormRun1;
    Button buttonQuadraticFormRun2;
    LinearLayout linearLayoutQuadraticFormFModMContainer;
    Button buttonQuadraticFormMMinus;
    Button buttonQuadraticFormM;
    Button buttonQuadraticFormMPlus;
    Button buttonQuadraticFormRMinus;
    Button buttonQuadraticFormR;
    Button buttonQuadraticFormRPlus;
    TextView textViewQuadraticFormLabelResult;
    TextView textViewQuadraticFormLabelElasticResult;
    TextView textViewQuadraticFormExpandResult;
    TextView textViewQuadraticFormCopyResult;
    TextView textViewQuadraticFormClearResult;
    LinearLayout linearLayoutQuadraticFormResultContainer;
    EditText editTextQuadraticFormResult;
    LinearLayout linearLayoutQuadraticFormResultGridContainer1;
    LinearLayout linearLayoutQuadraticFormStaticColumnHeader1;
    ListView listViewQuadraticFormResult1;
    LinearLayout linearLayoutQuadraticFormResultGridContainer2;
    LinearLayout linearLayoutQuadraticFormStaticColumnHeader2;
    ListView listViewQuadraticFormResult2;
    MenuItem menuItemQuadraticFormIncludeTrivialSolutions;
    MenuItem menuItemQuadraticFormIncludeOnlyPositiveSolutions;
    MenuItem menuItemQuadraticFormIncludeOnlyNegativeSolutions;

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
            this.textViewQuadraticFormBackToAlgorithms = inflater.findViewById(R.id.TextViewQuadraticFormBackToAlgorithms);
            this.textViewQuadraticFormTitle = inflater.findViewById(R.id.TextViewQuadraticFormTitle);
            this.textViewQuadraticFormDocumentationFile = inflater.findViewById(R.id.TextViewQuadraticFormDocumentationFile);
            this.textViewQuadraticFormLabelExpression = inflater.findViewById(R.id.TextViewQuadraticFormLabelExpression);
            this.textViewQuadraticFormLabelElasticExpression = inflater.findViewById(R.id.TextViewQuadraticFormLabelElasticExpression);
            this.textViewQuadraticFormCopyExpression = inflater.findViewById(R.id.TextViewQuadraticFormCopyExpression);
            this.textViewQuadraticFormPasteExpression = inflater.findViewById(R.id.TextViewQuadraticFormPasteExpression);
            this.textViewQuadraticFormClearExpression = inflater.findViewById(R.id.TextViewQuadraticFormClearExpression);
            this.editTextQuadraticFormExpression = inflater.findViewById(R.id.EditTextQuadraticFormExpression);
            this.buttonQuadraticFormM = inflater.findViewById(R.id.ButtonQuadraticFormM);
            this.buttonQuadraticFormR = inflater.findViewById(R.id.ButtonQuadraticFormR);
            this.linearLayoutQuadraticFormExamplesContainer = inflater.findViewById(R.id.LinearLayoutQuadraticFormExamplesContainer);
            this.buttonQuadraticFormRunExample1 = inflater.findViewById(R.id.ButtonQuadraticFormRunExample1);
            this.buttonQuadraticFormRunExample2 = inflater.findViewById(R.id.ButtonQuadraticFormRunExample2);
            this.buttonQuadraticFormRunExample3 = inflater.findViewById(R.id.ButtonQuadraticFormRunExample3);
            this.buttonQuadraticFormRunExample4 = inflater.findViewById(R.id.ButtonQuadraticFormRunExample4);
            this.buttonQuadraticFormRunExample5 = inflater.findViewById(R.id.ButtonQuadraticFormRunExample5);
            this.buttonQuadraticFormRun = inflater.findViewById(R.id.ButtonQuadraticFormRun);
            this.buttonQuadraticFormRun1 = inflater.findViewById(R.id.ButtonQuadraticFormRun1);
            this.buttonQuadraticFormRun2 = inflater.findViewById(R.id.ButtonQuadraticFormRun2);
            this.textViewQuadraticFormLabelResult = inflater.findViewById(R.id.TextViewQuadraticFormLabelResult);
            this.textViewQuadraticFormLabelElasticResult = inflater.findViewById(R.id.TextViewQuadraticFormLabelElasticResult);
            this.textViewQuadraticFormExpandResult = inflater.findViewById(R.id.TextViewQuadraticFormExpandResult);
            this.textViewQuadraticFormCopyResult = inflater.findViewById(R.id.TextViewQuadraticFormCopyResult);
            this.buttonQuadraticFormMMinus = inflater.findViewById(R.id.ButtonQuadraticFormMMinus);
            this.buttonQuadraticFormMPlus = inflater.findViewById(R.id.ButtonQuadraticFormMPlus);
            this.buttonQuadraticFormRMinus = inflater.findViewById(R.id.ButtonQuadraticFormRMinus);
            this.buttonQuadraticFormRPlus = inflater.findViewById(R.id.ButtonQuadraticFormRPlus);
            this.textViewQuadraticFormClearResult = inflater.findViewById(R.id.TextViewQuadraticFormClearResult);
            this.linearLayoutQuadraticFormResultContainer = inflater.findViewById(R.id.LinearLayoutQuadraticFormResultContainer);
            this.editTextQuadraticFormResult = inflater.findViewById(R.id.EditTextQuadraticFormResult);
            this.linearLayoutQuadraticFormResultGridContainer1 = inflater.findViewById(R.id.LinearLayoutQuadraticFormResultGridContainer1);
            this.linearLayoutQuadraticFormFModMContainer = inflater.findViewById(R.id.LinearLayoutQuadraticFormFModMContainer);
            this.linearLayoutQuadraticFormStaticColumnHeader1 = inflater.findViewById(R.id.LinearLayoutQuadraticFormStaticColumnHeader1);
            this.listViewQuadraticFormResult1 = inflater.findViewById(R.id.ListViewQuadraticFormResult1);
            this.linearLayoutQuadraticFormResultGridContainer2 = inflater.findViewById(R.id.LinearLayoutQuadraticFormResultGridContainer2);
            this.linearLayoutQuadraticFormStaticColumnHeader2 = inflater.findViewById(R.id.LinearLayoutQuadraticFormStaticColumnHeader2);
            this.listViewQuadraticFormResult2 = inflater.findViewById(R.id.ListViewQuadraticFormResult2);

            // Events
            this.textViewQuadraticFormBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });
            this.textViewQuadraticFormDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.QUADRATIC_FORM_PDF).show(getParentFragmentManager(), "QUADRATIC_FORM_PDF");
                }
            });
            this.textViewQuadraticFormCopyExpression.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextQuadraticFormExpression);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewQuadraticFormCopyExpression);
                }
            });
            this.textViewQuadraticFormPasteExpression.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.pasteToEditTextAsExpressionBDEF(requireContext(), editTextQuadraticFormExpression);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewQuadraticFormPasteExpression);
                }
            });
            this.textViewQuadraticFormClearExpression.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextQuadraticFormExpression);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewQuadraticFormClearExpression);
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            this.textViewQuadraticFormExpandResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (linearLayoutQuadraticFormResultContainer.getVisibility() == View.VISIBLE) {
                        PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewQuadraticFormTitle.getText().toString(), editTextQuadraticFormResult.getText());
                        popupResult.show();
                    } else if (linearLayoutQuadraticFormResultGridContainer1.getVisibility() == View.VISIBLE) {
                        PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewQuadraticFormTitle.getText().toString(), linearLayoutQuadraticFormStaticColumnHeader1, listViewQuadraticFormResult1);
                        popupResult.show();
                    } else if (linearLayoutQuadraticFormResultGridContainer2.getVisibility() == View.VISIBLE) {
                        PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewQuadraticFormTitle.getText().toString(), linearLayoutQuadraticFormStaticColumnHeader2, listViewQuadraticFormResult2);
                        popupResult.show();
                    }
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewQuadraticFormExpandResult);
                }
            });
            this.textViewQuadraticFormCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextQuadraticFormResult);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewQuadraticFormCopyResult);
                }
            });
            this.buttonQuadraticFormMMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mText = buttonQuadraticFormM.getText().toString();
                    if (!mText.isEmpty()) {
                        int m = Integer.parseInt(mText);
                        if (m <= 1) { // m value should not be 0 otherwise we get "modulus not positive" or "m.signum() <= 0" exceptions when doing m.mod(r)
                            buttonQuadraticFormM.setText("");
                        } else {
                            m = m - 1;
                            buttonQuadraticFormM.setText(String.valueOf(m));
                        }
                    }
                    calculateFModM();
                }
            });
            this.buttonQuadraticFormMPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mText = buttonQuadraticFormM.getText().toString();
                    if (mText.isEmpty()) {
                        buttonQuadraticFormM.setText("1"); // m value should not be 0 otherwise we get "modulus not positive" or "m.signum() <= 0" exceptions when doing m.mod(r)
                    } else {
                        int m = Integer.parseInt(mText);
                        m = m + 1;
                        buttonQuadraticFormM.setText(String.valueOf(m));
                    }
                    calculateFModM();
                }
            });
            this.buttonQuadraticFormRMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String rText = buttonQuadraticFormR.getText().toString();
                    if (!rText.isEmpty()) {
                        int r = Integer.parseInt(rText);
                        if (r <= 0) {
                            buttonQuadraticFormR.setText("");
                        } else {
                            r -= 1;
                            buttonQuadraticFormR.setText(String.valueOf(r));
                        }
                    }
                    calculateFModM();
                }
            });
            this.buttonQuadraticFormRPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String rText = buttonQuadraticFormR.getText().toString();
                    if (rText.isEmpty()) {
                        buttonQuadraticFormR.setText("0");
                    } else {
                        String mText = buttonQuadraticFormM.getText().toString();
                        if (mText.isEmpty()) {
                            buttonQuadraticFormR.setText("");
                        } else {
                            int m = Integer.parseInt(mText);
                            int r = Integer.parseInt(rText);
                            r += 1;
                            if (r < m) {
                                buttonQuadraticFormR.setText(String.valueOf(r));
                            }
                        }
                    }
                    calculateFModM();
                }
            });
            this.textViewQuadraticFormClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextQuadraticFormResult);
                    resetResult(false);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewQuadraticFormClearResult);
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            this.editTextQuadraticFormExpression.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            this.buttonQuadraticFormM.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    // Reset the last button clicked.
                    // ResetAllAndSelectResultButtonClicked(null);
                }
            });
            this.buttonQuadraticFormR.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    // Reset the last button clicked.
                    // ResetAllAndSelectResultButtonClicked(null);
                }
            });
            this.buttonQuadraticFormRunExample1.setOnClickListener(v -> onButtonRunExample1(container, true));
            this.buttonQuadraticFormRunExample2.setOnClickListener(v -> onButtonRunExample2(container, true));
            this.buttonQuadraticFormRunExample3.setOnClickListener(v -> onButtonRunExample3(container, true));
            this.buttonQuadraticFormRunExample4.setOnClickListener(v -> onButtonRunExample4(container, true));
            this.buttonQuadraticFormRunExample5.setOnClickListener(v -> onButtonRunExample5(container, true));
            this.buttonQuadraticFormRun.setOnClickListener(v -> onButtonRun(container, buttonQuadraticFormRun, false, true));
            this.buttonQuadraticFormRun1.setOnClickListener(v -> onButtonRun1(container, true));
            this.buttonQuadraticFormRun2.setOnClickListener(v -> onButtonRun2(container, true));
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        return inflater;
    }
    //endregion CREATE


    // region MENU
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        try {
            menuInflater.inflate(R.menu.menu_fragment_quadratic_form, menu);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
        this.menuItemQuadraticFormIncludeTrivialSolutions = menu.findItem(R.id.quadratic_form_menu_include_trivial_solutions);
        this.menuItemQuadraticFormIncludeOnlyPositiveSolutions = menu.findItem(R.id.quadratic_form_menu_include_only_positive_solutions);
        this.menuItemQuadraticFormIncludeOnlyNegativeSolutions = menu.findItem(R.id.quadratic_form_menu_include_only_negative_solutions);
        // Get the value from shared preferences.
        boolean includeTrivialSolutions = UserSettings.getQFIncludeTrivialSolutions(requireContext());
        menuItemQuadraticFormIncludeTrivialSolutions.setChecked(includeTrivialSolutions);
        boolean includeOnlyPositiveSolutions = UserSettings.getQFIncludeOnlyPositiveSolutions(requireContext());
        menuItemQuadraticFormIncludeOnlyPositiveSolutions.setChecked(includeOnlyPositiveSolutions);
        boolean includeOnlyNegativeSolutions = UserSettings.getQFIncludeOnlyNegativeSolutions(requireContext());
        menuItemQuadraticFormIncludeOnlyNegativeSolutions.setChecked(includeOnlyNegativeSolutions);
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
                boolean isChecked = !menuItemQuadraticFormIncludeTrivialSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setQFIncludeTrivialSolutions(requireContext(), isChecked);
                menuItemQuadraticFormIncludeTrivialSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_include_only_positive_solutions) {
                boolean isChecked = !menuItemQuadraticFormIncludeOnlyPositiveSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setQFIncludeOnlyPositiveSolutions(requireContext(), isChecked);
                menuItemQuadraticFormIncludeOnlyPositiveSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_include_only_negative_solutions) {
                boolean isChecked = !menuItemQuadraticFormIncludeOnlyNegativeSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setQFIncludeOnlyNegativeSolutions(requireContext(), isChecked);
                menuItemQuadraticFormIncludeOnlyNegativeSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_1) {
                this.editTextQuadraticFormExpression.setText(requireContext().getText(R.string.quadratic_form_example_1_expression));
                this.buttonQuadraticFormM.setText(requireContext().getText(R.string.quadratic_form_example_1_m));
                this.buttonQuadraticFormR.setText(requireContext().getText(R.string.quadratic_form_example_1_r));
                this.textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result_example_1));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_2) {
                this.editTextQuadraticFormExpression.setText(requireContext().getText(R.string.quadratic_form_example_2_expression));
                this.buttonQuadraticFormM.setText(requireContext().getText(R.string.quadratic_form_example_2_m));
                this.buttonQuadraticFormR.setText(requireContext().getText(R.string.quadratic_form_example_2_r));
                this.textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result_example_2));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_3) {
                this.editTextQuadraticFormExpression.setText(requireContext().getText(R.string.quadratic_form_example_3_expression));
                this.buttonQuadraticFormM.setText(requireContext().getText(R.string.quadratic_form_example_3_m));
                this.buttonQuadraticFormR.setText(requireContext().getText(R.string.quadratic_form_example_3_r));
                this.textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result_example_3));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_4) {
                this.editTextQuadraticFormExpression.setText(requireContext().getText(R.string.quadratic_form_example_4_expression));
                this.buttonQuadraticFormM.setText(requireContext().getText(R.string.quadratic_form_example_4_m));
                this.buttonQuadraticFormR.setText(requireContext().getText(R.string.quadratic_form_example_4_r));
                this.textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result_example_4));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_5) {
                this.editTextQuadraticFormExpression.setText(requireContext().getText(R.string.quadratic_form_example_5_expression));
                this.buttonQuadraticFormM.setText(requireContext().getText(R.string.quadratic_form_example_5_m));
                this.buttonQuadraticFormR.setText(requireContext().getText(R.string.quadratic_form_example_5_r));
                this.textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result_example_5));
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
        this.refreshSmallerClipboardButtons();
        this.refreshBiggerControls();
        this.refreshHideExampleButtons();
    }


    //region Display
    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.linearLayoutQuadraticFormExamplesContainer.getVisibility() == View.VISIBLE;
            boolean hideExampleButtons = UserSettings.getHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonQuadraticFormRun.setText(requireContext().getText(R.string.quadratic_form_run_short));
                this.linearLayoutQuadraticFormExamplesContainer.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonQuadraticFormRun.setText(requireContext().getText(R.string.quadratic_form_run_short));
                this.linearLayoutQuadraticFormExamplesContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerClipboardButtons() {
        try {
            boolean biggerClipboardButtons = UserSettings.getBiggerClipboardButtons(requireContext());

            // Clipboard
            ClipboardButtonDisplay.setClipboardButtonFontSize(textViewQuadraticFormCopyExpression, biggerClipboardButtons);
            ClipboardButtonDisplay.setClipboardButtonFontSize(textViewQuadraticFormPasteExpression, biggerClipboardButtons);
            ClipboardButtonDisplay.setClipboardButtonFontSize(textViewQuadraticFormClearExpression, biggerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.setClipboardButtonFontSize(textViewQuadraticFormExpandResult, biggerClipboardButtons);
            ClipboardButtonDisplay.setClipboardButtonFontSize(textViewQuadraticFormCopyResult, biggerClipboardButtons);
            ClipboardButtonDisplay.setClipboardButtonFontSize(textViewQuadraticFormClearResult, biggerClipboardButtons);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshBiggerControls() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());

            // Label
            ControlDisplay.setInputLabelFontSize(this.textViewQuadraticFormLabelExpression, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewQuadraticFormLabelElasticExpression, biggerControls);
            // Input
            ControlDisplay.setInputFontSize(this.editTextQuadraticFormExpression, biggerControls);
            // Buttons
            ControlDisplay.setButtonFontSize(buttonQuadraticFormRunExample1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormRunExample2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormRunExample3, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormRunExample4, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormRunExample5, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormRun, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormRun1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormRun2, biggerControls);
            // Buttons.
            ControlDisplay.setButtonFontSize(buttonQuadraticFormMMinus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormM, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormMPlus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormRMinus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormR, biggerControls);
            ControlDisplay.setButtonFontSize(buttonQuadraticFormRPlus, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(this.textViewQuadraticFormLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewQuadraticFormLabelElasticResult, biggerControls);
            // Output
            ControlDisplay.setOutputFontSize(this.editTextQuadraticFormResult, biggerControls);
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
        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextQuadraticFormResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextQuadraticFormResult.setText(resultFromHtml);
            }
        }

        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM_1) {
            if (progressStatus == ProgressStatus.CANCELED) {
                cancelBinaryQuadraticFormShowResult(listViewQuadraticFormResult1);
            } else {
                @SuppressWarnings("unchecked")
                List<List<RowItem>> sqfResultList1 = (List<List<RowItem>>)result;
                showResultBinaryQuadraticForm1(sqfResultList1);
            }
        }

        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM_2) {
            if (progressStatus == ProgressStatus.CANCELED) {
                cancelBinaryQuadraticFormShowResult(listViewQuadraticFormResult2);
            } else {
                @SuppressWarnings("unchecked")
                List<List<RowItem>> sqfResultList2 = (List<List<RowItem>>)result;
                showResultBinaryQuadraticForm2(sqfResultList2);
            }
        }
    }
    private void cancelBinaryQuadraticFormShowResult(ListView listView) {
        ArrayList<String> listItems=new ArrayList<>();
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(requireContext(), R.layout.row_item_canceled, R.id.RowItemCanceled, listItems);
        listView.setAdapter(adapter);
        listItems.add(requireContext().getResources().getString(R.string.canceled));
        adapter.notifyDataSetChanged();
    }
    private void showResultBinaryQuadraticForm1(List<List<RowItem>> rows) {
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
                listViewQuadraticFormResult1.setDividerHeight((int) UIHelper.convertDpToPixel(4F, requireContext()));
            } else {
                listViewQuadraticFormResult1.setDividerHeight((int) UIHelper.convertDpToPixel(1F, requireContext()));
            }

            // Create and set the adapter.
            GridAdapter adapter = new GridAdapter(requireContext(), linearLayoutQuadraticFormStaticColumnHeader1, rows, rowItemWidth, rowItemWidths, rowItemHeight, null, biggerResultDisplay);
            listViewQuadraticFormResult1.setAdapter(adapter);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void showResultBinaryQuadraticForm2(List<List<RowItem>> rows) {
        try {
            if(rows == null || rows.isEmpty()) {
                return;
            }

            // Get the values from shared preferences.
            boolean biggerResultDisplay = UserSettings.getBiggerResultDisplay(requireContext());
            boolean squareResultDisplay = UserSettings.getSquareResultDisplay(requireContext());

            BigInteger m = null;
            BigInteger r = null;
            String mText = this.buttonQuadraticFormM.getText().toString();
            if (!mText.isEmpty()) {
                m = new BigInteger(mText);
            }
            String rText = this.buttonQuadraticFormR.getText().toString();
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
                listViewQuadraticFormResult2.setDividerHeight((int) UIHelper.convertDpToPixel(4F, requireContext()));
            } else {
                listViewQuadraticFormResult2.setDividerHeight((int) UIHelper.convertDpToPixel(1F, requireContext()));
            }

            // Create and set the adapter.
            GridAdapter adapter = new GridAdapter(requireContext(), linearLayoutQuadraticFormStaticColumnHeader2, rows, rowItemWidth, null, rowItemHeight, null, biggerResultDisplay);
            FragmentBinaryQuadraticForm.showResultBinaryQuadraticFormFModM2(adapter, m, r);
            listViewQuadraticFormResult2.setAdapter(adapter);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void onButtonRunExample1(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextQuadraticFormExpression.setText(requireContext().getText(R.string.quadratic_form_example_1_expression));
            this.buttonQuadraticFormM.setText(requireContext().getText(R.string.quadratic_form_example_1_m));
            this.buttonQuadraticFormR.setText(requireContext().getText(R.string.quadratic_form_example_1_r));
            this.textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            onButtonRun(container, buttonQuadraticFormRunExample1, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample2(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextQuadraticFormExpression.setText(requireContext().getText(R.string.quadratic_form_example_2_expression));
            this.buttonQuadraticFormM.setText(requireContext().getText(R.string.quadratic_form_example_2_m));
            this.buttonQuadraticFormR.setText(requireContext().getText(R.string.quadratic_form_example_2_r));
            this.textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            onButtonRun(container, buttonQuadraticFormRunExample2, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample3(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextQuadraticFormExpression.setText(requireContext().getText(R.string.quadratic_form_example_3_expression));
            this.buttonQuadraticFormM.setText(requireContext().getText(R.string.quadratic_form_example_3_m));
            this.buttonQuadraticFormR.setText(requireContext().getText(R.string.quadratic_form_example_3_r));
            this.textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            onButtonRun(container, buttonQuadraticFormRunExample3, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample4(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextQuadraticFormExpression.setText(requireContext().getText(R.string.quadratic_form_example_4_expression));
            this.buttonQuadraticFormM.setText(requireContext().getText(R.string.quadratic_form_example_4_m));
            this.buttonQuadraticFormR.setText(requireContext().getText(R.string.quadratic_form_example_4_r));
            this.textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result_example_4));
            //
            onButtonRun(container, buttonQuadraticFormRunExample4, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample5(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextQuadraticFormExpression.setText(requireContext().getText(R.string.quadratic_form_example_5_expression));
            this.buttonQuadraticFormM.setText(requireContext().getText(R.string.quadratic_form_example_5_m));
            this.buttonQuadraticFormR.setText(requireContext().getText(R.string.quadratic_form_example_5_r));
            this.textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result_example_5));
            //
            onButtonRun(container, buttonQuadraticFormRunExample5, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRun(ViewGroup container, Button button, boolean skipLabelResult, boolean displayProgressDialog) {
        try {
            resetResult(skipLabelResult);

            // Check.
            List<BigInteger> expression = UIHelper.checkInputAndGetQuadraticFormExpression(requireContext(), this.editTextQuadraticFormExpression, textViewQuadraticFormLabelExpression, textViewQuadraticFormLabelElasticExpression, "bxy+dx+ey=f");
            if (expression == null) {
                return;
            }
            String errorMessage = "";
            if (expression.size() == 6) {
                // The a, b, c, d, e, f Quadratic Form is not supported here yet.
                errorMessage = "The <b>a, b, c, d, e, f</b> Quadratic Form is not supported here yet.";
            }
            if (!errorMessage.isEmpty()) {
                UIHelper.displayError(this.editTextQuadraticFormExpression, textViewQuadraticFormLabelExpression, textViewQuadraticFormLabelElasticExpression);
                // Notify before return.
                UIHelper.displayTheErrorMessage(requireContext(), errorMessage);
                return;
            }

            BigInteger b = expression.get(0);
            BigInteger d = expression.get(1);
            BigInteger e = expression.get(2);
            BigInteger f = expression.get(3);

            // Check b ≠ 0
            if (b.compareTo(BigInteger.ZERO) == 0) {
                UIHelper.showCustomToastLight(requireContext(), "The value of b must be other than 0");
                return;
            }

            setResultVisibilityFromButtonRun(skipLabelResult);

            // Before action performing.
            beforeActionPerforming(button);

            boolean includeTrivialSolutions = UserSettings.getQFIncludeTrivialSolutions(requireContext());
            boolean includeOnlyPositiveSolutions = UserSettings.getQFIncludeOnlyPositiveSolutions(requireContext());
            boolean includeOnlyNegativeSolutions = UserSettings.getQFIncludeOnlyNegativeSolutions(requireContext());

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM, this);
            algorithmParameters.setInput1(b);
            algorithmParameters.setInput2(d);
            algorithmParameters.setInput3(e);
            algorithmParameters.setInput4(f);
            algorithmParameters.setIncludeTrivialSolutions(includeTrivialSolutions);
            algorithmParameters.setIncludeOnlyPositiveSolutions(includeOnlyPositiveSolutions);
            algorithmParameters.setIncludeOnlyNegativeSolutions(includeOnlyNegativeSolutions);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRun1(ViewGroup container, boolean displayProgressDialog) {
        try {
            resetResult(false);

            // Check.
            List<BigInteger> expression = UIHelper.checkInputAndGetQuadraticFormExpression(requireContext(), this.editTextQuadraticFormExpression, textViewQuadraticFormLabelExpression, textViewQuadraticFormLabelElasticExpression, "bxy+dx+ey=f");
            if (expression == null) {
                return;
            }
            String errorMessage = "";
            if (expression.size() == 6) {
                // The a, b, c, d, e, f Quadratic Form is not supported here yet.
                errorMessage = "The <b>a, b, c, d, e, f</b> Quadratic Form is not supported here yet.";
            }
            if (!errorMessage.isEmpty()) {
                UIHelper.displayError(this.editTextQuadraticFormExpression, textViewQuadraticFormLabelExpression, textViewQuadraticFormLabelElasticExpression);
                // Notify before return.
                UIHelper.displayTheErrorMessage(requireContext(), errorMessage);
                return;
            }

            BigInteger b = expression.get(0);
            BigInteger d = expression.get(1);
            BigInteger e = expression.get(2);
            BigInteger f = expression.get(3);

            // Check b ≠ 0
            if (b.compareTo(BigInteger.ZERO) == 0) {
                UIHelper.showCustomToastLight(requireContext(), "The value of b must be other than 0");
                return;
            }

            setResultVisibilityFromButtonRun1();

            // Before action performing.
            beforeActionPerforming(buttonQuadraticFormRun1);

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM_1, this);
            algorithmParameters.setInput1(b);
            algorithmParameters.setInput2(d);
            algorithmParameters.setInput3(e);
            algorithmParameters.setInput4(f);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRun2(ViewGroup container, boolean displayProgressDialog) {
        try {
            resetResult(false);

            // Check.
            List<BigInteger> expression = UIHelper.checkInputAndGetQuadraticFormExpression(requireContext(), this.editTextQuadraticFormExpression, textViewQuadraticFormLabelExpression, textViewQuadraticFormLabelElasticExpression, "bxy+dx+ey=f");
            if (expression == null) {
                return;
            }

            BigInteger a = null;
            BigInteger b = null;
            BigInteger c = null;
            BigInteger d = null;
            BigInteger e = null;
            BigInteger f = null;
            if (expression.size() == 6) {
                a = expression.get(0);
                b = expression.get(1);
                c = expression.get(2);
                d = expression.get(3);
                e = expression.get(4);
                f = expression.get(5);
            } else if (expression.size() == 4) {
                a = BigInteger.ZERO;
                b = expression.get(0);
                c = BigInteger.ZERO;
                d = expression.get(1);
                e = expression.get(2);
                f = expression.get(3);
            }

            setResultVisibilityFromButtonRun2();

            // Before action performing.
            beforeActionPerforming(buttonQuadraticFormRun2);

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM_2, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(c);
            algorithmParameters.setInput4(d);
            algorithmParameters.setInput5(e);
            algorithmParameters.setInput6(f);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void calculateFModM() {
        if (this.listViewQuadraticFormResult2.getAdapter() != null) {
            GridAdapter gridAdapter = (GridAdapter) this.listViewQuadraticFormResult2.getAdapter();

            BigInteger m = null;
            BigInteger r = null;

            String mText = this.buttonQuadraticFormM.getText().toString();
            String rText = this.buttonQuadraticFormR.getText().toString();

            if (!mText.isEmpty()) {
                m = new BigInteger(mText);
            }
            if (!rText.isEmpty()) {
                r = new BigInteger(rText);
            }

            showResultBinaryQuadraticFormFModM2(gridAdapter, m, r);
        }
    }
    public static void showResultBinaryQuadraticFormFModM2(GridAdapter gridAdapter, BigInteger m, BigInteger r) {
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
        editTextQuadraticFormExpression.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewQuadraticFormCopyExpression.setSelected(false);
        textViewQuadraticFormPasteExpression.setSelected(false);
        textViewQuadraticFormClearExpression.setSelected(false);
        textViewQuadraticFormExpandResult.setSelected(false);
        textViewQuadraticFormCopyResult.setSelected(false);
        textViewQuadraticFormClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void resetAllAndSelectTheLastButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonQuadraticFormRunExample1.setSelected(false);
        buttonQuadraticFormRunExample2.setSelected(false);
        buttonQuadraticFormRunExample3.setSelected(false);
        buttonQuadraticFormRunExample4.setSelected(false);
        buttonQuadraticFormRunExample5.setSelected(false);
        buttonQuadraticFormRun.setSelected(false);
        buttonQuadraticFormRun1.setSelected(false);
        buttonQuadraticFormRun2.setSelected(false);
        // Select the last button clicked.
        if (button != null) {
            button.setSelected(true);
            textViewQuadraticFormExpandResult.setVisibility(View.VISIBLE);
        } else {
            textViewQuadraticFormExpandResult.setVisibility(View.GONE);
        }
    }
    private void resetResult(boolean skipLabelResult) {
        // Reset the last clipboard clicked.
        resetAllAndSelectTheLastClipboardButtonClicked(null);
        // Reset the last button clicked.
        resetAllAndSelectTheLastButtonClicked(null);
        //
        if(!skipLabelResult) {
            textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result));
        }
        this.editTextQuadraticFormResult.setText("");
        this.linearLayoutQuadraticFormStaticColumnHeader1.removeAllViews();
        this.linearLayoutQuadraticFormStaticColumnHeader2.removeAllViews();
        this.listViewQuadraticFormResult1.setAdapter(null);
        this.listViewQuadraticFormResult2.setAdapter(null);
    }
    private void setResultVisibilityFromButtonRun(boolean skipLabelResult) {
        if(!skipLabelResult) {
            textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.result));
        }
        this.linearLayoutQuadraticFormFModMContainer.setVisibility(View.GONE);
        this.textViewQuadraticFormCopyResult.setVisibility(View.VISIBLE);
        this.textViewQuadraticFormClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutQuadraticFormResultContainer.setVisibility(View.VISIBLE);
        this.linearLayoutQuadraticFormResultGridContainer1.setVisibility(View.GONE);
        this.linearLayoutQuadraticFormResultGridContainer2.setVisibility(View.GONE);
    }
    private void setResultVisibilityFromButtonRun1() {
        textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.representation));
        this.linearLayoutQuadraticFormFModMContainer.setVisibility(View.GONE);
        this.textViewQuadraticFormCopyResult.setVisibility(View.GONE);
        this.textViewQuadraticFormClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutQuadraticFormResultContainer.setVisibility(View.GONE);
        this.linearLayoutQuadraticFormResultGridContainer1.setVisibility(View.VISIBLE);
        this.linearLayoutQuadraticFormResultGridContainer2.setVisibility(View.GONE);
    }
    private void setResultVisibilityFromButtonRun2() {
        textViewQuadraticFormLabelResult.setText(requireContext().getText(R.string.quadratic_form_result_representation_f_mod_m_r));
        this.linearLayoutQuadraticFormFModMContainer.setVisibility(View.VISIBLE);
        this.textViewQuadraticFormCopyResult.setVisibility(View.GONE);
        this.textViewQuadraticFormClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutQuadraticFormResultContainer.setVisibility(View.GONE);
        this.linearLayoutQuadraticFormResultGridContainer1.setVisibility(View.GONE);
        this.linearLayoutQuadraticFormResultGridContainer2.setVisibility(View.VISIBLE);
    }
    //endregion RESULT

}