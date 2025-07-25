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
import com.gegprifti.android.numbertheoryalgorithms.fragments.tabs.TabFragmentAlgorithms;
import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.settings.ClipboardButtonDisplay;
import com.gegprifti.android.numbertheoryalgorithms.settings.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupDocumentation;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;
import java.math.BigInteger;


/**
 * @see <a href="http://www.math.vt.edu/people/brown/doc/sqrts.pdf">sqrt</a>
 */
public class FragmentTonelliShanksAlgorithm extends FragmentBase implements Callback {
    // quadratic residue modulo
    private final static String TAG = FragmentTonelliShanksAlgorithm.class.getSimpleName();

    TextView textViewTonelliShanksAlgorithmBackToAlgorithms;
    TextView textViewTonelliShanksAlgorithmTitle;
    TextView textViewTonelliShanksAlgorithmDocumentationFile;
    TextView textViewTonelliShanksAlgorithmLabelA;
    TextView textViewTonelliShanksAlgorithmLabelElasticA;
    TextView textViewTonelliShanksAlgorithmCopyA;
    TextView textViewTonelliShanksAlgorithmPasteA;
    TextView textViewTonelliShanksAlgorithmClearA;
    EditText editTextTonelliShanksAlgorithmA;
    TextView textViewTonelliShanksAlgorithmLabelP;
    TextView textViewTonelliShanksAlgorithmLabelElasticP;
    TextView textViewTonelliShanksAlgorithmCopyP;
    TextView textViewTonelliShanksAlgorithmPasteP;
    TextView textViewTonelliShanksAlgorithmClearP;
    EditText editTextTonelliShanksAlgorithmP;
    Button buttonTonelliShanksAlgorithmRun;
    Button buttonTonelliShanksAlgorithmRunExample1;
    Button buttonTonelliShanksAlgorithmRunExample2;
    Button buttonTonelliShanksAlgorithmRunExample3;
    TextView textViewTonelliShanksAlgorithmLabelResult;
    TextView textViewTonelliShanksAlgorithmLabelElasticResult;
    TextView textViewTonelliShanksAlgorithmExpandResult;
    TextView textViewTonelliShanksAlgorithmCopyResult;
    TextView textViewTonelliShanksAlgorithmClearResult;
    EditText editTextTonelliShanksAlgorithmResult;

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
            textViewTonelliShanksAlgorithmBackToAlgorithms = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmBackToAlgorithms);
            textViewTonelliShanksAlgorithmTitle = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmTitle);
            textViewTonelliShanksAlgorithmDocumentationFile = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmDocumentationFile);
            textViewTonelliShanksAlgorithmLabelA = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmLabelA);
            textViewTonelliShanksAlgorithmLabelElasticA = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmLabelElasticA);
            textViewTonelliShanksAlgorithmCopyA = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmCopyA);
            textViewTonelliShanksAlgorithmPasteA = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmPasteA);
            textViewTonelliShanksAlgorithmClearA = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmClearA);
            editTextTonelliShanksAlgorithmA = inflater.findViewById(R.id.EditTextTonelliShanksAlgorithmA);
            textViewTonelliShanksAlgorithmLabelP = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmLabelP);
            textViewTonelliShanksAlgorithmLabelElasticP = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmLabelElasticP);
            textViewTonelliShanksAlgorithmCopyP = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmCopyP);
            textViewTonelliShanksAlgorithmPasteP = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmPasteP);
            textViewTonelliShanksAlgorithmClearP = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmClearP);
            editTextTonelliShanksAlgorithmP = inflater.findViewById(R.id.EditTextTonelliShanksAlgorithmP);
            buttonTonelliShanksAlgorithmRun = inflater.findViewById(R.id.ButtonTonelliShanksAlgorithmRun);
            buttonTonelliShanksAlgorithmRunExample1 = inflater.findViewById(R.id.ButtonTonelliShanksAlgorithmRunExample1);
            buttonTonelliShanksAlgorithmRunExample2 = inflater.findViewById(R.id.ButtonTonelliShanksAlgorithmRunExample2);
            buttonTonelliShanksAlgorithmRunExample3 = inflater.findViewById(R.id.ButtonTonelliShanksAlgorithmRunExample3);
            textViewTonelliShanksAlgorithmLabelResult = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmLabelResult);
            textViewTonelliShanksAlgorithmLabelElasticResult = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmLabelElasticResult);
            textViewTonelliShanksAlgorithmExpandResult = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmExpandResult);
            textViewTonelliShanksAlgorithmCopyResult = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmCopyResult);
            textViewTonelliShanksAlgorithmClearResult = inflater.findViewById(R.id.TextViewTonelliShanksAlgorithmClearResult);
            editTextTonelliShanksAlgorithmResult = inflater.findViewById(R.id.EditTextTonelliShanksAlgorithmResult);

            // Input filter integer only
            editTextTonelliShanksAlgorithmA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextTonelliShanksAlgorithmP.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // editTextTonelliShanksResult.setEnabled(false);
            // Make the link text in the textView clickable
            // editTextTonelliShanksResult.setMovementMethod(LinkMovementMethod.getInstance()); // This blocks the text selection

            // Default UI status
            // Check if this is the first time the user is using this.
            if (UserSettings.getFirstTimeTSH(requireContext())) {
                this.OnButtonRunExample3(container, false);
            }

            // Events
            textViewTonelliShanksAlgorithmBackToAlgorithms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tabFragmentAlgorithms != null) {
                        // Go back to the algorithms main menu
                        FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                        tabFragmentAlgorithms.SetFragment(fragmentAlgorithms);
                    }
                }
            });
            textViewTonelliShanksAlgorithmDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupDocumentation popupDocumentation = new PopupDocumentation(requireActivity(), requireContext(), PopupDocumentation.TONELLI_SHANKS_ALGORITHM_PDF);
                    popupDocumentation.Show();
                }
            });
            buttonTonelliShanksAlgorithmRun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRun(container, buttonTonelliShanksAlgorithmRun, false, true);
                }
            });
            buttonTonelliShanksAlgorithmRunExample1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample1(container, true);
                }
            });
            buttonTonelliShanksAlgorithmRunExample2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample2(container, true);
                }
            });
            buttonTonelliShanksAlgorithmRunExample3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample3(container, true);
                }
            });
            textViewTonelliShanksAlgorithmCopyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.CopyEditText(requireContext(), editTextTonelliShanksAlgorithmA);
                    ResetAllAndSelectClipboardButtonClicked(textViewTonelliShanksAlgorithmCopyA);
                }
            });
            textViewTonelliShanksAlgorithmCopyP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.CopyEditText(requireContext(), editTextTonelliShanksAlgorithmP);
                    ResetAllAndSelectClipboardButtonClicked(textViewTonelliShanksAlgorithmCopyP);
                }
            });
            textViewTonelliShanksAlgorithmCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.CopyEditText(requireContext(), editTextTonelliShanksAlgorithmResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewTonelliShanksAlgorithmCopyResult);
                }
            });
            textViewTonelliShanksAlgorithmPasteA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.PasteEditText(requireContext(), editTextTonelliShanksAlgorithmA);
                    ResetAllAndSelectClipboardButtonClicked(textViewTonelliShanksAlgorithmPasteA);
                }
            });
            textViewTonelliShanksAlgorithmPasteP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.PasteEditText(requireContext(), editTextTonelliShanksAlgorithmP);
                    ResetAllAndSelectClipboardButtonClicked(textViewTonelliShanksAlgorithmPasteP);
                }
            });
            textViewTonelliShanksAlgorithmClearA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.ClearEditText(requireContext(), editTextTonelliShanksAlgorithmA);
                    ResetAllAndSelectClipboardButtonClicked(textViewTonelliShanksAlgorithmClearA);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewTonelliShanksAlgorithmClearP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.ClearEditText(requireContext(), editTextTonelliShanksAlgorithmP);
                    ResetAllAndSelectClipboardButtonClicked(textViewTonelliShanksAlgorithmClearP);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewTonelliShanksAlgorithmClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.ClearEditText(requireContext(), editTextTonelliShanksAlgorithmResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewTonelliShanksAlgorithmClearResult);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextTonelliShanksAlgorithmA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String tonelliShanksLabelA = "a" + UIHelper.GetNrOfDigits(s.toString());
                    textViewTonelliShanksAlgorithmLabelA.setText(tonelliShanksLabelA);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextTonelliShanksAlgorithmP.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String tonelliShanksLabelP = "p" + UIHelper.GetNrOfDigits(s.toString());
                    textViewTonelliShanksAlgorithmLabelP.setText(tonelliShanksLabelP);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewTonelliShanksAlgorithmExpandResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTonelliShanksAlgorithmTitle.getText().toString(), editTextTonelliShanksAlgorithmResult.getText());
                    popupResult.Show();
                    ResetAllAndSelectClipboardButtonClicked(textViewTonelliShanksAlgorithmExpandResult);
                }
            });
            editTextTonelliShanksAlgorithmResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.toString().isEmpty()) {
                        textViewTonelliShanksAlgorithmExpandResult.setVisibility(View.GONE);
                    } else {
                        textViewTonelliShanksAlgorithmExpandResult.setVisibility(View.VISIBLE);
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
                editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_1_a));
                editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_1_p));
                this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_1));
                // ResultReset(true);
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
                return true;
            }
            if (id == R.id.tonelli_shanks_menu_example_2) {
                editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_a));
                editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_p));
                this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_2));
                // ResultReset(true);
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
                return true;
            }
            if (id == R.id.tonelli_shanks_menu_example_3) {
                editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_a));
                editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_p));
                this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_3));
                // ResultReset(true);
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
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
    }


    //region Display
    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.buttonTonelliShanksAlgorithmRunExample1.getVisibility() == View.VISIBLE; // Just check one.
            boolean hideExampleButtons = UserSettings.GetHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonTonelliShanksAlgorithmRun.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_run_long));
                this.buttonTonelliShanksAlgorithmRunExample1.setVisibility(View.GONE);
                this.buttonTonelliShanksAlgorithmRunExample2.setVisibility(View.GONE);
                this.buttonTonelliShanksAlgorithmRunExample3.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonTonelliShanksAlgorithmRun.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_run_short));
                this.buttonTonelliShanksAlgorithmRunExample1.setVisibility(View.VISIBLE);
                this.buttonTonelliShanksAlgorithmRunExample2.setVisibility(View.VISIBLE);
                this.buttonTonelliShanksAlgorithmRunExample3.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerClipboardButtons() {
        try {
            boolean biggerClipboardButtons = UserSettings.GetBiggerClipboardButtons(requireContext());

            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewTonelliShanksAlgorithmCopyA, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewTonelliShanksAlgorithmPasteA, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewTonelliShanksAlgorithmClearA, biggerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewTonelliShanksAlgorithmCopyP, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewTonelliShanksAlgorithmPasteP, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewTonelliShanksAlgorithmClearP, biggerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewTonelliShanksAlgorithmExpandResult, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewTonelliShanksAlgorithmCopyResult, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewTonelliShanksAlgorithmClearResult, biggerClipboardButtons);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerControls() {
        try {
            boolean biggerControls = UserSettings.GetBiggerControls(requireContext());

            // Label
            ControlDisplay.SetInputLabelFontSize(textViewTonelliShanksAlgorithmLabelA, biggerControls);
            ControlDisplay.SetInputLabelFontSize(textViewTonelliShanksAlgorithmLabelElasticA, biggerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextTonelliShanksAlgorithmA, biggerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewTonelliShanksAlgorithmLabelP, biggerControls);
            ControlDisplay.SetInputLabelFontSize(textViewTonelliShanksAlgorithmLabelElasticP, biggerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextTonelliShanksAlgorithmP, biggerControls);
            // Buttons
            ControlDisplay.SetButtonFontSize(buttonTonelliShanksAlgorithmRun, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonTonelliShanksAlgorithmRunExample1, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonTonelliShanksAlgorithmRunExample2, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonTonelliShanksAlgorithmRunExample3, biggerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewTonelliShanksAlgorithmLabelResult, biggerControls);
            ControlDisplay.SetInputLabelFontSize(textViewTonelliShanksAlgorithmLabelElasticResult, biggerControls);
            // Output
            ControlDisplay.SetOutputFontSize(editTextTonelliShanksAlgorithmResult, biggerControls);
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
                editTextTonelliShanksAlgorithmResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextTonelliShanksAlgorithmResult.setText(resultFromHtml);
            }
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void OnButtonRun(ViewGroup container, Button button, boolean skipLabelResult, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeNumber(requireContext(), editTextTonelliShanksAlgorithmA, textViewTonelliShanksAlgorithmLabelA, textViewTonelliShanksAlgorithmLabelElasticA, "a")) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanOrEqualToMin(requireContext(), editTextTonelliShanksAlgorithmP, textViewTonelliShanksAlgorithmLabelP, textViewTonelliShanksAlgorithmLabelElasticP, "p", BigInteger.valueOf(2L))) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextTonelliShanksAlgorithmA.getText().toString());
            BigInteger p = new BigInteger(editTextTonelliShanksAlgorithmP.getText().toString());

            // Reset result
            ResultReset(skipLabelResult);

            // Before action performing.
            BeforeActionPerforming(button);

            // Perform the Tonelli Shanks Algorithm
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.TONELLI_SHANKS_ALGORITHM, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(p);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample1(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_1_a));
            editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_1_p));
            this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            OnButtonRun(container, buttonTonelliShanksAlgorithmRunExample1, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample2(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_a));
            editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_p));
            this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            OnButtonRun(container, buttonTonelliShanksAlgorithmRunExample2, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample3(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_a));
            editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_p));
            this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            OnButtonRun(container, buttonTonelliShanksAlgorithmRunExample3, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion BUTTON ACTIONS


    //region RESULT
    private void BeforeActionPerforming(Button button) {
        // Hide the keyboard.
        UIHelper.HideSoftKeyBoard(requireActivity());
        // Clear the focus.
        editTextTonelliShanksAlgorithmA.clearFocus();
        editTextTonelliShanksAlgorithmP.clearFocus();
        // Select the last button clicked.
        ResetAllAndSelectButtonClicked(button);
    }
    private void ResetAllAndSelectClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewTonelliShanksAlgorithmCopyA.setSelected(false);
        textViewTonelliShanksAlgorithmPasteA.setSelected(false);
        textViewTonelliShanksAlgorithmClearA.setSelected(false);
        textViewTonelliShanksAlgorithmCopyP.setSelected(false);
        textViewTonelliShanksAlgorithmPasteP.setSelected(false);
        textViewTonelliShanksAlgorithmClearP.setSelected(false);
        textViewTonelliShanksAlgorithmExpandResult.setSelected(false);
        textViewTonelliShanksAlgorithmCopyResult.setSelected(false);
        textViewTonelliShanksAlgorithmClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void ResetAllAndSelectButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonTonelliShanksAlgorithmRun.setSelected(false);
        buttonTonelliShanksAlgorithmRunExample1.setSelected(false);
        buttonTonelliShanksAlgorithmRunExample2.setSelected(false);
        buttonTonelliShanksAlgorithmRunExample3.setSelected(false);
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
            textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextTonelliShanksAlgorithmResult.setText("");
    }
    //endregion RESULT

}