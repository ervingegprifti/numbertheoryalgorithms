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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gegprifti.android.numbertheoryalgorithms.common.ClipboardButtonDisplay;
import com.gegprifti.android.numbertheoryalgorithms.common.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.common.PopupDocumentation;
import com.gegprifti.android.numbertheoryalgorithms.common.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.common.Helper;
import com.gegprifti.android.numbertheoryalgorithms.common.UserSettings;
import java.math.BigInteger;


public class FragmentModFactors extends FragmentBase implements Callback {
    private final static String TAG = "FragmentMF";

    //static final BigInteger ZERO = BigInteger.ZERO;
    //static final BigInteger ONE = BigInteger.ONE;
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

    // Define the parent fragment
    private FragmentTabAlgorithms fragmentTabAlgorithms;
    //public FragmentTabAlgorithms getFragmentTabAlgorithms() { return fragmentTabAlgorithms; }
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

            // Input filter integer only
            editTextModFactorsN.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});
            editTextModFactorsA.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});

            // Default UI status
            // Check if this is the first time the user is using this.
            if (UserSettings.getFirstTimeMF(requireContext())) {
                this.OnButtonRunExample4(container, false);
            }

            // Events
            textViewModFactorsBackToAlgorithms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(fragmentTabAlgorithms != null) {
                        // Go back to the algorithms main menu
                        FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                        fragmentTabAlgorithms.SetFragment(fragmentAlgorithms);
                    }
                }
            });
            textViewModFactorsDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupDocumentation popupDocumentation = new PopupDocumentation(requireActivity(), requireContext(), PopupDocumentation.MOD_FACTORS_PDF);
                    popupDocumentation.Show();
                }
            });
            textViewModFactorsCopyN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextModFactorsN);
                    ResetAllAndSelectClipboardButtonClicked(textViewModFactorsCopyN);
                }
            });
            textViewModFactorsCopyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextModFactorsA);
                    ResetAllAndSelectClipboardButtonClicked(textViewModFactorsCopyA);
                }
            });
            textViewModFactorsCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.CopyEditText(requireContext(), editTextModFactorsResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewModFactorsCopyResult);
                }
            });
            textViewModFactorsPasteN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextModFactorsN);
                    ResetAllAndSelectClipboardButtonClicked(textViewModFactorsPasteN);
                }
            });
            textViewModFactorsPasteA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.PasteEditText(requireContext(), editTextModFactorsA);
                    ResetAllAndSelectClipboardButtonClicked(textViewModFactorsPasteA);
                }
            });
            textViewModFactorsClearN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextModFactorsN);
                    ResetAllAndSelectClipboardButtonClicked(textViewModFactorsClearN);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewModFactorsClearA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextModFactorsA);
                    ResetAllAndSelectClipboardButtonClicked(textViewModFactorsClearA);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewModFactorsClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Helper.ClearEditText(requireContext(), editTextModFactorsResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewModFactorsClearResult);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
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
                    String modFactorsLabelN = "n" + Helper.GetNrOfDigits(s.toString());
                    textViewModFactorsLabelN.setText(modFactorsLabelN);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
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
                    String modFactorsLabelA = "a" + Helper.GetNrOfDigits(s.toString());
                    textViewModFactorsLabelA.setText(modFactorsLabelA);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            this.buttonModFactorsRunExample1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample1(container, true);
                }
            });
            this.buttonModFactorsRunExample2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample2(container, true);
                }
            });
            this.buttonModFactorsRunExample3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample3(container, true);
                }
            });
            this.buttonModFactorsRunExample4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample4(container, true);
                }
            });
            this.buttonModFactorsRunExample5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample5(container, true);
                }
            });
            this.buttonModFactorsRunExample6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample6(container, true);
                }
            });
            buttonModFactorsRun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRun(container, buttonModFactorsRun, false, true);
                }
            });
            buttonModFactorsCountRun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonCountRun(container, true);
                }
            });
            textViewModFactorsExpandResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewModFactorsTitle.getText().toString(), editTextModFactorsResult.getText());
                    popupResult.Show();
                    ResetAllAndSelectClipboardButtonClicked(textViewModFactorsExpandResult);
                }
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
                ResultReset(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_2) {
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_2_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_2_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_2));
                ResultReset(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_3) {
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_3_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_3_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_3));
                ResultReset(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_4) {
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_4_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_4_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_4));
                ResultReset(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_5) {
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_5_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_5_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_5));
                ResultReset(true);
                return true;
            }
            if (id == R.id.mod_factors_menu_example_6) {
                this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_6_n));
                this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_6_a));
                this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_6));
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
            Typeface currentTypeface = editTextModFactorsResult.getTypeface();
            boolean showResultInMonospace = UserSettings.GetShowResultInMonospace(requireContext());
            if (currentTypeface != Typeface.MONOSPACE && showResultInMonospace) {
                editTextModFactorsResult.setTypeface(Typeface.MONOSPACE);
            } else if (currentTypeface == Typeface.MONOSPACE && !showResultInMonospace) {
                editTextModFactorsResult.setTypeface(Typeface.DEFAULT);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.linearLayoutModFactorsExamplesContainer.getVisibility() == View.VISIBLE;
            boolean hideExampleButtons = UserSettings.GetHideExampleButtons(requireContext());
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
    private void refreshSmallerClipboardButtons() {
        try {
            boolean smallerClipboardButtons = UserSettings.GetSmallerClipboardButtons(requireContext());

            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewModFactorsCopyN, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewModFactorsPasteN, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewModFactorsClearN, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewModFactorsCopyA, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewModFactorsPasteA, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewModFactorsClearA, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewModFactorsExpandResult, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewModFactorsCopyResult, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewModFactorsClearResult, smallerClipboardButtons);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerControls() {
        try {
            boolean smallerControls = UserSettings.GetSmallerControls(requireContext());

            // Label
            ControlDisplay.SetInputLabelFontSize(textViewModFactorsLabelN, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewModFactorsLabelElasticN, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextModFactorsN, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewModFactorsLabelA, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewModFactorsLabelElasticA, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextModFactorsA, smallerControls);
            // Buttons
            ControlDisplay.SetButtonFontSize(buttonModFactorsRunExample1, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonModFactorsRunExample2, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonModFactorsRunExample3, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonModFactorsRunExample4, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonModFactorsRunExample5, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonModFactorsRunExample6, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonModFactorsRun, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonModFactorsCountRun, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewModFactorsLabelResult, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewModFactorsLabelElasticResult, smallerControls);
            // Output
            ControlDisplay.SetOutputFontSize(editTextModFactorsResult, smallerControls);
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
        if (algorithmName == AlgorithmName.MOD_FACTORS) {
            if (algorithmStatus == AlgorithmStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextModFactorsResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String) result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextModFactorsResult.setText(resultFromHtml);
            }
        }

        if (algorithmName == AlgorithmName.MOD_FACTORS_COUNT) {
            if (algorithmStatus == AlgorithmStatus.CANCELED) {
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
    private void OnButtonRunExample1(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_1_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_1_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            OnButtonRun(container, buttonModFactorsRunExample1, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample2(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_2_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_2_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            OnButtonRun(container, buttonModFactorsRunExample2, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample3(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_3_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_3_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            OnButtonRun(container, buttonModFactorsRunExample3, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample4(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_4_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_4_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_4));
            //
            OnButtonRun(container, buttonModFactorsRunExample4, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample5(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_5_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_5_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_5));
            //
            OnButtonRun(container, buttonModFactorsRunExample5, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample6(ViewGroup container, boolean displayProgressDialog) {
        try {
            this.editTextModFactorsN.setText(requireContext().getText(R.string.mod_factors_example_6_n));
            this.editTextModFactorsA.setText(requireContext().getText(R.string.mod_factors_example_6_a));
            this.textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result_example_6));
            //
            OnButtonRun(container, buttonModFactorsRunExample6, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRun(ViewGroup container, Button button, boolean skipLabelResult, boolean displayProgressDialog) {
        try {
            // Check.
            if(Helper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextModFactorsN, textViewModFactorsLabelN, textViewModFactorsLabelElasticN, "n", BigInteger.ZERO)) {
                return;
            }
            if(Helper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), editTextModFactorsA, textViewModFactorsLabelA, textViewModFactorsLabelElasticA, "a", TWO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger n = new BigInteger(editTextModFactorsN.getText().toString());
            BigInteger a = new BigInteger(editTextModFactorsA.getText().toString());

            // Reset result
            ResultReset(skipLabelResult);

            // Before action performing.
            BeforeActionPerforming(button);

            // Perform the mod factors
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.MOD_FACTORS, this);
            algorithmParameters.setInput1(n);
            algorithmParameters.setInput2(a);
            ProgressDialog.StartWork(requireContext(), container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonCountRun(ViewGroup container, boolean displayProgressDialog) {
        try {
            // Check.
            if(Helper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextModFactorsN,  textViewModFactorsLabelN, textViewModFactorsLabelElasticN, "n", BigInteger.ZERO)) {
                return;
            }
            if(Helper.checkInputMustBeBetweenMinMaxInclusive(requireContext(), editTextModFactorsA,  textViewModFactorsLabelA, textViewModFactorsLabelElasticA, "a", TWO, INTEGER_MAX_VALUE)) {
                return;
            }

            // Get numbers
            BigInteger n = new BigInteger(editTextModFactorsN.getText().toString());
            BigInteger a = new BigInteger(editTextModFactorsA.getText().toString());

            // Reset result
            ResultReset(false);

            // Before action performing.
            BeforeActionPerforming(buttonModFactorsCountRun);

            // Perform the mod factors
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.MOD_FACTORS_COUNT, this);
            algorithmParameters.setInput1(n);
            algorithmParameters.setInput2(a);
            ProgressDialog.StartWork(requireContext(), container, algorithmParameters, displayProgressDialog);
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
        editTextModFactorsN.clearFocus();
        editTextModFactorsA.clearFocus();
        // Select the last button clicked.
        ResetAllAndSelectButtonClicked(button);
    }
    private void ResetAllAndSelectClipboardButtonClicked(TextView textView) {
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
    private void ResetAllAndSelectButtonClicked(Button button) {
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
    private void ResultReset(boolean skipLabelResult) {
        // Reset the last clipboard clicked.
        ResetAllAndSelectClipboardButtonClicked(null);
        // Reset the last button clicked.
        ResetAllAndSelectButtonClicked(null);
        //
        if(!skipLabelResult) {
            textViewModFactorsLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextModFactorsResult.setText("");
    }
    //endregion RESULT

}