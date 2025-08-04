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

            // Events
            textViewTonelliShanksAlgorithmBackToAlgorithms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tabFragmentAlgorithms != null) {
                        // Go back to the algorithms main menu
                        FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                        tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                    }
                }
            });
            textViewTonelliShanksAlgorithmDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.TONELLI_SHANKS_ALGORITHM_PDF).show(getParentFragmentManager(), "TONELLI_SHANKS_ALGORITHM_PDF");
                }
            });
            buttonTonelliShanksAlgorithmRun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonRun(container, buttonTonelliShanksAlgorithmRun, false, true);
                }
            });
            buttonTonelliShanksAlgorithmRunExample1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonRunExample1(container, true);
                }
            });
            buttonTonelliShanksAlgorithmRunExample2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonRunExample2(container, true);
                }
            });
            buttonTonelliShanksAlgorithmRunExample3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonRunExample3(container, true);
                }
            });
            textViewTonelliShanksAlgorithmCopyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextTonelliShanksAlgorithmA);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewTonelliShanksAlgorithmCopyA);
                }
            });
            textViewTonelliShanksAlgorithmCopyP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextTonelliShanksAlgorithmP);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewTonelliShanksAlgorithmCopyP);
                }
            });
            textViewTonelliShanksAlgorithmCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextTonelliShanksAlgorithmResult);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewTonelliShanksAlgorithmCopyResult);
                }
            });
            textViewTonelliShanksAlgorithmPasteA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.pasteEditText(requireContext(), editTextTonelliShanksAlgorithmA);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewTonelliShanksAlgorithmPasteA);
                }
            });
            textViewTonelliShanksAlgorithmPasteP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.pasteEditText(requireContext(), editTextTonelliShanksAlgorithmP);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewTonelliShanksAlgorithmPasteP);
                }
            });
            textViewTonelliShanksAlgorithmClearA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextTonelliShanksAlgorithmA);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewTonelliShanksAlgorithmClearA);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            textViewTonelliShanksAlgorithmClearP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextTonelliShanksAlgorithmP);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewTonelliShanksAlgorithmClearP);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            textViewTonelliShanksAlgorithmClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextTonelliShanksAlgorithmResult);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewTonelliShanksAlgorithmClearResult);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
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
                    String tonelliShanksLabelA = "a" + UIHelper.getNrOfDigits(s.toString());
                    textViewTonelliShanksAlgorithmLabelA.setText(tonelliShanksLabelA);
                    // Reset
                    resetResult(false);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
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
                    String tonelliShanksLabelP = "p" + UIHelper.getNrOfDigits(s.toString());
                    textViewTonelliShanksAlgorithmLabelP.setText(tonelliShanksLabelP);
                    // Reset
                    resetResult(false);
                    // Reset the last button clicked.
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            textViewTonelliShanksAlgorithmExpandResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTonelliShanksAlgorithmTitle.getText().toString(), editTextTonelliShanksAlgorithmResult.getText());
                    popupResult.show();
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewTonelliShanksAlgorithmExpandResult);
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
                resetAllAndSelectTheLastButtonClicked(null);
                return true;
            }
            if (id == R.id.tonelli_shanks_menu_example_2) {
                editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_a));
                editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_p));
                this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_2));
                // ResultReset(true);
                // Reset the last button clicked.
                resetAllAndSelectTheLastButtonClicked(null);
                return true;
            }
            if (id == R.id.tonelli_shanks_menu_example_3) {
                editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_a));
                editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_p));
                this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_3));
                // ResultReset(true);
                // Reset the last button clicked.
                resetAllAndSelectTheLastButtonClicked(null);
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
            boolean exampleButtonsAreVisible = this.buttonTonelliShanksAlgorithmRunExample1.getVisibility() == View.VISIBLE; // Just check one.
            boolean hideExampleButtons = UserSettings.getHideExampleButtons(requireContext());
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


    private void refreshBiggerControls() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons
            ControlDisplay.setClipboardButtonFontSize(textViewTonelliShanksAlgorithmCopyA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewTonelliShanksAlgorithmPasteA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewTonelliShanksAlgorithmClearA, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewTonelliShanksAlgorithmCopyP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewTonelliShanksAlgorithmPasteP, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewTonelliShanksAlgorithmClearP, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewTonelliShanksAlgorithmExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewTonelliShanksAlgorithmCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewTonelliShanksAlgorithmClearResult, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewTonelliShanksAlgorithmLabelA, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewTonelliShanksAlgorithmLabelElasticA, biggerControls);
            // Input
            ControlDisplay.setInputFontSize(editTextTonelliShanksAlgorithmA, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewTonelliShanksAlgorithmLabelP, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewTonelliShanksAlgorithmLabelElasticP, biggerControls);
            // Input
            ControlDisplay.setInputFontSize(editTextTonelliShanksAlgorithmP, biggerControls);
            // Buttons
            ControlDisplay.setButtonFontSize(buttonTonelliShanksAlgorithmRun, biggerControls);
            ControlDisplay.setButtonFontSize(buttonTonelliShanksAlgorithmRunExample1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonTonelliShanksAlgorithmRunExample2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonTonelliShanksAlgorithmRunExample3, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(textViewTonelliShanksAlgorithmLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(textViewTonelliShanksAlgorithmLabelElasticResult, biggerControls);
            // Output
            ControlDisplay.setOutputFontSize(editTextTonelliShanksAlgorithmResult, biggerControls);
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
    private void onButtonRun(ViewGroup container, Button button, boolean skipLabelResult, boolean displayProgressDialog) {
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
            resetResult(skipLabelResult);

            // Before action performing.
            beforeActionPerforming(button);

            // Perform the Tonelli Shanks Algorithm
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.TONELLI_SHANKS_ALGORITHM, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(p);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample1(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_1_a));
            editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_1_p));
            this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            onButtonRun(container, buttonTonelliShanksAlgorithmRunExample1, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample2(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_a));
            editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_2_p));
            this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            onButtonRun(container, buttonTonelliShanksAlgorithmRunExample2, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample3(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextTonelliShanksAlgorithmA.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_a));
            editTextTonelliShanksAlgorithmP.setText(requireContext().getText(R.string.tonelli_shanks_algorithm_example_3_p));
            this.textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            onButtonRun(container, buttonTonelliShanksAlgorithmRunExample3, true, displayProgressDialog);
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
        editTextTonelliShanksAlgorithmA.clearFocus();
        editTextTonelliShanksAlgorithmP.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastClipboardButtonClicked(TextView textView) {
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
    private void resetAllAndSelectTheLastButtonClicked(Button button) {
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
    private void resetResult(boolean skipLabelResult) {
        // Reset the last clipboard clicked.
        resetAllAndSelectTheLastClipboardButtonClicked(null);
        // Reset the last button clicked.
        resetAllAndSelectTheLastButtonClicked(null);
        //
        if(!skipLabelResult) {
            textViewTonelliShanksAlgorithmLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextTonelliShanksAlgorithmResult.setText("");
    }
    //endregion RESULT

}