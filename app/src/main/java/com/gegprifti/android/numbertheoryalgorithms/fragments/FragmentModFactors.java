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


public class FragmentModFactors extends FragmentBase implements Callback {
    private final static String TAG = FragmentModFactors.class.getSimpleName();

    static final BigInteger TWO = BigInteger.valueOf(2L);
    BigInteger INTEGER_MAX_VALUE = new BigInteger(Integer.toString(Integer.MAX_VALUE));

    TextView textViewModFactorsBackToAlgorithms;
    TextView textViewModFactorsTitle;
    TextView textViewModFactorsDocumentationFile;
    TextView textViewModFactorsLabelN;
    TextView textViewModFactorsLabelElasticN;
    TextView textViewModFactorsCopyN;
    TextView textViewModFactorsPasteN;
    TextView textViewModFactorsClearN;
    EditText editTextModFactorsN;
    TextView textViewModFactorsLabelA;
    TextView textViewModFactorsLabelElasticA;
    TextView textViewModFactorsCopyA;
    TextView textViewModFactorsPasteA;
    TextView textViewModFactorsClearA;
    EditText editTextModFactorsA;
    LinearLayout linearLayoutModFactorsExamplesContainer;
    Button buttonModFactorsRunExample1;
    Button buttonModFactorsRunExample2;
    Button buttonModFactorsRunExample3;
    Button buttonModFactorsRunExample4;
    Button buttonModFactorsRunExample5;
    Button buttonModFactorsRunExample6;
    Button buttonModFactorsRun;
    Button buttonModFactorsCountRun;
    TextView textViewModFactorsLabelResult;
    TextView textViewModFactorsLabelElasticResult;
    TextView textViewModFactorsExpandResult;
    TextView textViewModFactorsCopyResult;
    TextView textViewModFactorsClearResult;
    EditText editTextModFactorsResult;
    boolean isCompactInputView = false;


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
            textViewModFactorsBackToAlgorithms = inflater.findViewById(R.id.TextViewModFactorsBackToAlgorithms);
            textViewModFactorsTitle = inflater.findViewById(R.id.TextViewModFactorsTitle);
            textViewModFactorsDocumentationFile = inflater.findViewById(R.id.TextViewModFactorsDocumentationFile);
            textViewModFactorsLabelN = inflater.findViewById(R.id.TextViewModFactorsLabelN);
            textViewModFactorsLabelElasticN = inflater.findViewById(R.id.TextViewModFactorsLabelElasticN);
            textViewModFactorsCopyN = inflater.findViewById(R.id.TextViewModFactorsCopyN);
            textViewModFactorsPasteN = inflater.findViewById(R.id.TextViewModFactorsPasteN);
            textViewModFactorsClearN = inflater.findViewById(R.id.TextViewModFactorsClearN);
            editTextModFactorsN = inflater.findViewById(R.id.EditTextModFactorsN);
            textViewModFactorsLabelA = inflater.findViewById(R.id.TextViewModFactorsLabelA);
            textViewModFactorsLabelElasticA = inflater.findViewById(R.id.TextViewModFactorsLabelElasticA);
            textViewModFactorsCopyA = inflater.findViewById(R.id.TextViewModFactorsCopyA);
            textViewModFactorsPasteA = inflater.findViewById(R.id.TextViewModFactorsPasteA);
            textViewModFactorsClearA = inflater.findViewById(R.id.TextViewModFactorsClearA);
            editTextModFactorsA = inflater.findViewById(R.id.EditTextModFactorsA);
            this.linearLayoutModFactorsExamplesContainer = inflater.findViewById(R.id.LinearLayoutModFactorsExamplesContainer);
            this.buttonModFactorsRunExample1 = inflater.findViewById(R.id.ButtonModFactorsRunExample1);
            this.buttonModFactorsRunExample2 = inflater.findViewById(R.id.ButtonModFactorsRunExample2);
            this.buttonModFactorsRunExample3 = inflater.findViewById(R.id.ButtonModFactorsRunExample3);
            this.buttonModFactorsRunExample4 = inflater.findViewById(R.id.ButtonModFactorsRunExample4);
            this.buttonModFactorsRunExample5 = inflater.findViewById(R.id.ButtonModFactorsRunExample5);
            this.buttonModFactorsRunExample6 = inflater.findViewById(R.id.ButtonModFactorsRunExample6);
            buttonModFactorsRun = inflater.findViewById(R.id.ButtonModFactorsRun);
            buttonModFactorsCountRun = inflater.findViewById(R.id.ButtonModFactorsCountRun);
            textViewModFactorsLabelResult = inflater.findViewById(R.id.TextViewModFactorsLabelResult);
            textViewModFactorsLabelElasticResult = inflater.findViewById(R.id.TextViewModFactorsLabelElasticResult);
            textViewModFactorsExpandResult = inflater.findViewById(R.id.TextViewModFactorsExpandResult);
            textViewModFactorsCopyResult = inflater.findViewById(R.id.TextViewModFactorsCopyResult);
            textViewModFactorsClearResult = inflater.findViewById(R.id.TextViewModFactorsClearResult);
            editTextModFactorsResult = inflater.findViewById(R.id.EditTextModFactorsResult);

            // InputGroup filter integer only
            editTextModFactorsN.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextModFactorsA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Events
            textViewModFactorsBackToAlgorithms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tabFragmentAlgorithms != null) {
                        // Go back to the algorithms main menu
                        FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                        tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                    }
                }
            });
            textViewModFactorsDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.MOD_FACTORS_PDF).show(getParentFragmentManager(), "MOD_FACTORS_PDF");
                }
            });
            textViewModFactorsCopyN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextModFactorsN);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewModFactorsCopyN);
                }
            });
            textViewModFactorsCopyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextModFactorsA);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewModFactorsCopyA);
                }
            });
            textViewModFactorsCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextModFactorsResult);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewModFactorsCopyResult);
                }
            });
            textViewModFactorsPasteN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.pasteEditText(requireContext(), editTextModFactorsN);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewModFactorsPasteN);
                }
            });
            textViewModFactorsPasteA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.pasteEditText(requireContext(), editTextModFactorsA);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewModFactorsPasteA);
                }
            });
            textViewModFactorsClearN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextModFactorsN);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewModFactorsClearN);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            textViewModFactorsClearA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextModFactorsA);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewModFactorsClearA);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            textViewModFactorsClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextModFactorsResult);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewModFactorsClearResult);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            editTextModFactorsN.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String modFactorsLabelN = "n" + UIHelper.getNrOfDigits(s.toString());
                    textViewModFactorsLabelN.setText(modFactorsLabelN);
                    // Reset
                    resetResult(false);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            editTextModFactorsA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String modFactorsLabelA = "a" + UIHelper.getNrOfDigits(s.toString());
                    textViewModFactorsLabelA.setText(modFactorsLabelA);
                    // Reset
                    resetResult(false);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            this.buttonModFactorsRunExample1.setOnClickListener(v -> onButtonRunExample1(container));
            this.buttonModFactorsRunExample2.setOnClickListener(v -> onButtonRunExample2(container));
            this.buttonModFactorsRunExample3.setOnClickListener(v -> onButtonRunExample3(container));
            this.buttonModFactorsRunExample4.setOnClickListener(v -> onButtonRunExample4(container));
            this.buttonModFactorsRunExample5.setOnClickListener(v -> onButtonRunExample5(container));
            this.buttonModFactorsRunExample6.setOnClickListener(v -> onButtonRunExample6(container));
            buttonModFactorsRun.setOnClickListener(v -> onButtonRun(container, buttonModFactorsRun, false));
            buttonModFactorsCountRun.setOnClickListener(v -> onButtonCountRun(container));
            textViewModFactorsExpandResult.setOnClickListener(v -> {
                PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewModFactorsTitle.getText().toString(), editTextModFactorsResult.getText());
                popupResult.show();
                resetAllAndSelectTheLastClipboardButtonClicked(textViewModFactorsExpandResult);
            });
            editTextModFactorsResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.toString().isEmpty()) {
                        textViewModFactorsExpandResult.setVisibility(View.GONE);
                    } else {
                        textViewModFactorsExpandResult.setVisibility(View.VISIBLE);
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
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_1_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_1_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_1));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_2) {
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_2_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_2_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_2));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_3) {
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_3_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_3_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_3));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_4) {
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_4_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_4_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_4));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_5) {
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_5_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_5_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_5));
                resetResult(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_6) {
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_6_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_6_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_6));
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
            boolean exampleButtonsAreVisible = this.linearLayoutModFactorsExamplesContainer.getVisibility() == View.VISIBLE;
            boolean hideExampleButtons = UserSettings.getHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonModFactorsRun.setText(requireContext().getText(R.string.mod_factors_run_long));
                this.linearLayoutModFactorsExamplesContainer.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonModFactorsRun.setText(requireContext().getText(R.string.mod_factors_run_short));
                this.linearLayoutModFactorsExamplesContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshBiggerControls() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons
            ControlDisplay.setClipboardButtonFontSize(textViewModFactorsCopyN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewModFactorsPasteN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewModFactorsClearN, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewModFactorsCopyA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewModFactorsPasteA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewModFactorsClearA, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewModFactorsExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewModFactorsCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewModFactorsClearResult, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewModFactorsLabelN, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewModFactorsLabelElasticN, biggerControls);
            // InputGroup
            ControlDisplay.setInputFontSize(editTextModFactorsN, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewModFactorsLabelA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewModFactorsLabelElasticA, biggerControls);
            // InputGroup
            ControlDisplay.setInputFontSize(editTextModFactorsA, biggerControls);
            // Buttons
            ControlDisplay.setButtonFontSize(buttonModFactorsRunExample1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonModFactorsRunExample2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonModFactorsRunExample3, biggerControls);
            ControlDisplay.setButtonFontSize(buttonModFactorsRunExample4, biggerControls);
            ControlDisplay.setButtonFontSize(buttonModFactorsRunExample5, biggerControls);
            ControlDisplay.setButtonFontSize(buttonModFactorsRunExample6, biggerControls);
            ControlDisplay.setButtonFontSize(buttonModFactorsRun, biggerControls);
            ControlDisplay.setButtonFontSize(buttonModFactorsCountRun, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewModFactorsLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewModFactorsLabelElasticResult, biggerControls);
            // Output
            ControlDisplay.setOutputFontSize(editTextModFactorsResult, biggerControls);
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
                editTextModFactorsResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String) result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextModFactorsResult.setText(resultFromHtml);
            }
        }

        if (algorithmName == AlgorithmName.MOD_FACTORS_COUNT) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextModFactorsResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextModFactorsResult.setText(resultFromHtml);
            }
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void onButtonRunExample1(ViewGroup container) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_1_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_1_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            onButtonRun(container, buttonModFactorsRunExample1, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample2(ViewGroup container) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_2_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_2_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            onButtonRun(container, buttonModFactorsRunExample2, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample3(ViewGroup container) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_3_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_3_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            onButtonRun(container, buttonModFactorsRunExample3, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample4(ViewGroup container) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_4_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_4_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_4));
            //
            onButtonRun(container, buttonModFactorsRunExample4, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample5(ViewGroup container) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_5_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_5_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_5));
            //
            onButtonRun(container, buttonModFactorsRunExample5, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample6(ViewGroup container) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_6_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_6_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_6));
            //
            onButtonRun(container, buttonModFactorsRunExample6, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRun(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextModFactorsN, textViewModFactorsLabelN, textViewModFactorsLabelElasticN, "n", BigInteger.ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), editTextModFactorsA, textViewModFactorsLabelA, textViewModFactorsLabelElasticA, "a", TWO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger n = new BigInteger(editTextModFactorsN.getText().toString());
            BigInteger a = new BigInteger(editTextModFactorsA.getText().toString());

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
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextModFactorsN,  textViewModFactorsLabelN, textViewModFactorsLabelElasticN, "n", BigInteger.ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), editTextModFactorsA,  textViewModFactorsLabelA, textViewModFactorsLabelElasticA, "a", TWO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger n = new BigInteger(editTextModFactorsN.getText().toString());
            BigInteger a = new BigInteger(editTextModFactorsA.getText().toString());

            // Reset result
            resetResult(false);

            // Before action performing.
            beforeActionPerforming(buttonModFactorsCountRun);

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
        editTextModFactorsN.clearFocus();
        editTextModFactorsA.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewModFactorsCopyN.setSelected(false);
        textViewModFactorsPasteN.setSelected(false);
        textViewModFactorsClearN.setSelected(false);
        textViewModFactorsCopyA.setSelected(false);
        textViewModFactorsPasteA.setSelected(false);
        textViewModFactorsClearA.setSelected(false);
        textViewModFactorsExpandResult.setSelected(false);
        textViewModFactorsCopyResult.setSelected(false);
        textViewModFactorsClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void resetAllAndSelectTheLastButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonModFactorsRunExample1.setSelected(false);
        buttonModFactorsRunExample2.setSelected(false);
        buttonModFactorsRunExample3.setSelected(false);
        buttonModFactorsRunExample4.setSelected(false);
        buttonModFactorsRunExample5.setSelected(false);
        buttonModFactorsRunExample6.setSelected(false);
        buttonModFactorsRun.setSelected(false);
        buttonModFactorsCountRun.setSelected(false);
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
            textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextModFactorsResult.setText("");
    }
    //endregion RESULT

}