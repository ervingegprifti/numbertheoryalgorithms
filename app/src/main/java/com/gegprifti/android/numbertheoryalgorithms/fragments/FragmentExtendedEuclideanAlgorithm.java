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
import java.math.BigInteger;
import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.gegprifti.android.numbertheoryalgorithms.fragments.tabs.TabFragmentAlgorithms;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.settings.ClipboardButtonDisplay;
import com.gegprifti.android.numbertheoryalgorithms.settings.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupDocumentation;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;


public class FragmentExtendedEuclideanAlgorithm extends FragmentBase implements Callback {
    private final static String TAG = FragmentExtendedEuclideanAlgorithm.class.getSimpleName();

    TextView textViewExtendedEuclideanBackToAlgorithms;
    TextView textViewExtendedEuclideanTitle;
    TextView textViewExtendedEuclideanDocumentationFile;
    TextView textViewExtendedEuclideanLabelA;
    TextView textViewExtendedEuclideanLabelElasticA;
    TextView textViewExtendedEuclideanCopyA;
    TextView textViewExtendedEuclideanPasteA;
    TextView textViewExtendedEuclideanClearA;
    EditText editTextExtendedEuclideanA;
    TextView textViewExtendedEuclideanLabelB;
    TextView textViewExtendedEuclideanLabelElasticB;
    TextView textViewExtendedEuclideanCopyB;
    TextView textViewExtendedEuclideanPasteB;
    TextView textViewExtendedEuclideanClearB;
    EditText editTextExtendedEuclideanB;
    Button buttonExtendedEuclideanRun;
    Button buttonExtendedEuclideanRunExample1;
    Button buttonExtendedEuclideanRunExample2;
    Button buttonExtendedEuclideanRunExample3;
    TextView textViewExtendedEuclideanLabelResult;
    TextView textViewExtendedEuclideanLabelElasticResult;
    TextView textViewExtendedEuclideanExpandResult;
    TextView textViewExtendedEuclideanCopyResult;
    TextView textViewExtendedEuclideanClearResult;
    EditText editTextExtendedEuclideanResult;


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
            inflater = layoutInflater.inflate(R.layout.fragment_extended_euclidean_algorithm, container, false);
            textViewExtendedEuclideanBackToAlgorithms = inflater.findViewById(R.id.TextViewExtendedEuclideanBackToAlgorithms);
            textViewExtendedEuclideanTitle = inflater.findViewById(R.id.TextViewExtendedEuclideanTitle);
            textViewExtendedEuclideanDocumentationFile = inflater.findViewById(R.id.TextViewExtendedEuclideanDocumentationFile);
            textViewExtendedEuclideanLabelA = inflater.findViewById(R.id.TextViewExtendedEuclideanLabelA);
            textViewExtendedEuclideanLabelElasticA = inflater.findViewById(R.id.TextViewExtendedEuclideanLabelElasticA);
            textViewExtendedEuclideanCopyA = inflater.findViewById(R.id.TextViewExtendedEuclideanCopyA);
            textViewExtendedEuclideanPasteA = inflater.findViewById(R.id.TextViewExtendedEuclideanPasteA);
            textViewExtendedEuclideanClearA = inflater.findViewById(R.id.TextViewExtendedEuclideanClearA);
            editTextExtendedEuclideanA = inflater.findViewById(R.id.EditTextExtendedEuclideanA);
            textViewExtendedEuclideanLabelB = inflater.findViewById(R.id.TextViewExtendedEuclideanLabelB);
            textViewExtendedEuclideanLabelElasticB = inflater.findViewById(R.id.TextViewExtendedEuclideanLabelElasticB);
            textViewExtendedEuclideanCopyB = inflater.findViewById(R.id.TextViewExtendedEuclideanCopyB);
            textViewExtendedEuclideanPasteB = inflater.findViewById(R.id.TextViewExtendedEuclideanPasteB);
            textViewExtendedEuclideanClearB = inflater.findViewById(R.id.TextViewExtendedEuclideanClearB);
            editTextExtendedEuclideanB = inflater.findViewById(R.id.EditTextExtendedEuclideanB);
            buttonExtendedEuclideanRun = inflater.findViewById(R.id.ButtonExtendedEuclideanRun);
            buttonExtendedEuclideanRunExample1 = inflater.findViewById(R.id.ButtonExtendedEuclideanRunExample1);
            buttonExtendedEuclideanRunExample2 = inflater.findViewById(R.id.ButtonExtendedEuclideanRunExample2);
            buttonExtendedEuclideanRunExample3 = inflater.findViewById(R.id.ButtonExtendedEuclideanRunExample3);
            textViewExtendedEuclideanLabelResult = inflater.findViewById(R.id.TextViewExtendedEuclideanLabelResult);
            textViewExtendedEuclideanLabelElasticResult = inflater.findViewById(R.id.TextViewExtendedEuclideanLabelElasticResult);
            textViewExtendedEuclideanExpandResult = inflater.findViewById(R.id.TextViewExtendedEuclideanExpandResult);
            textViewExtendedEuclideanCopyResult = inflater.findViewById(R.id.TextViewExtendedEuclideanCopyResult);
            textViewExtendedEuclideanClearResult = inflater.findViewById(R.id.TextViewExtendedEuclideanClearResult);
            editTextExtendedEuclideanResult = inflater.findViewById(R.id.EditTextExtendedEuclideanResult);

            // Input filter integer only
            editTextExtendedEuclideanA.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});
            editTextExtendedEuclideanB.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Default UI status
            // Check if this is the first time the user is using this.
            if (UserSettings.getFirstTimeEEA(requireContext())) {
                this.OnButtonRunExample2(container, false);
            }

            // Events
            textViewExtendedEuclideanBackToAlgorithms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tabFragmentAlgorithms != null) {
                        // Go back to the algorithms main menu
                        FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                        tabFragmentAlgorithms.SetFragment(fragmentAlgorithms);
                    }
                }
            });
            textViewExtendedEuclideanDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupDocumentation popupDocumentation = new PopupDocumentation(requireActivity(), requireContext(), PopupDocumentation.EXTENDED_EUCLIDEAN_ALGORITHM_PDF);
                    popupDocumentation.Show();
                }
            });
            textViewExtendedEuclideanCopyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.CopyEditText(requireContext(), editTextExtendedEuclideanA);
                    ResetAllAndSelectClipboardButtonClicked(textViewExtendedEuclideanCopyA);
                }
            });
            textViewExtendedEuclideanCopyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.CopyEditText(requireContext(), editTextExtendedEuclideanB);
                    ResetAllAndSelectClipboardButtonClicked(textViewExtendedEuclideanCopyB);
                }
            });
            textViewExtendedEuclideanCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.CopyEditText(requireContext(), editTextExtendedEuclideanResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewExtendedEuclideanCopyResult);
                }
            });
            textViewExtendedEuclideanPasteA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.PasteEditText(requireContext(), editTextExtendedEuclideanA);
                    ResetAllAndSelectClipboardButtonClicked(textViewExtendedEuclideanPasteA);
                }
            });
            textViewExtendedEuclideanPasteB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.PasteEditText(requireContext(), editTextExtendedEuclideanB);
                    ResetAllAndSelectClipboardButtonClicked(textViewExtendedEuclideanPasteB);
                }
            });
            textViewExtendedEuclideanClearA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.ClearEditText(requireContext(), editTextExtendedEuclideanA);
                    ResetAllAndSelectClipboardButtonClicked(textViewExtendedEuclideanClearA);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewExtendedEuclideanClearB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.ClearEditText(requireContext(), editTextExtendedEuclideanB);
                    ResetAllAndSelectClipboardButtonClicked(textViewExtendedEuclideanClearB);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            textViewExtendedEuclideanClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.ClearEditText(requireContext(), editTextExtendedEuclideanResult);
                    ResetAllAndSelectClipboardButtonClicked(textViewExtendedEuclideanClearResult);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextExtendedEuclideanA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String extendedEuclideanLabelA = "a" + UIHelper.GetNrOfDigits(s.toString());
                    textViewExtendedEuclideanLabelA.setText(extendedEuclideanLabelA);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextExtendedEuclideanB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String extendedEuclideanLabelB = "b" + UIHelper.GetNrOfDigits(s.toString());
                    textViewExtendedEuclideanLabelB.setText(extendedEuclideanLabelB);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            buttonExtendedEuclideanRun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRun(container, buttonExtendedEuclideanRun, false, true);
                }
            });
            buttonExtendedEuclideanRunExample1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample1(container, true);
                }
            });
            buttonExtendedEuclideanRunExample2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample2(container, true);
                }
            });
            buttonExtendedEuclideanRunExample3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnButtonRunExample3(container, true);
                }
            });
            textViewExtendedEuclideanExpandResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewExtendedEuclideanTitle.getText().toString(), editTextExtendedEuclideanResult.getText());
                    popupResult.Show();
                    ResetAllAndSelectClipboardButtonClicked(textViewExtendedEuclideanExpandResult);
                }
            });
            editTextExtendedEuclideanResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.toString().isEmpty()) {
                        textViewExtendedEuclideanExpandResult.setVisibility(View.GONE);
                    } else {
                        textViewExtendedEuclideanExpandResult.setVisibility(View.VISIBLE);
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
            menuInflater.inflate(R.menu.menu_fragment_extended_euclidean_algorithm, menu);
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
            if (id == R.id.extended_euclidean_algorithm_menu_example_1) {
                this.editTextExtendedEuclideanA.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_1_a));
                this.editTextExtendedEuclideanB.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_1_b));
                this.textViewExtendedEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_1));
                ResultReset(true);
                return true;
            }
            if (id == R.id.extended_euclidean_algorithm_menu_example_2) {
                this.editTextExtendedEuclideanA.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_2_a));
                this.editTextExtendedEuclideanB.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_2_b));
                this.textViewExtendedEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_2));
                ResultReset(true);
                return true;
            }
            if (id == R.id.extended_euclidean_algorithm_menu_example_3) {
                this.editTextExtendedEuclideanA.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_3_a));
                this.editTextExtendedEuclideanB.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_3_b));
                this.textViewExtendedEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_3));
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
    }


    //region Display
    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.buttonExtendedEuclideanRunExample1.getVisibility() == View.VISIBLE; // Just check one.
            boolean hideExampleButtons = UserSettings.GetHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonExtendedEuclideanRun.setText(requireContext().getText(R.string.extended_euclidean_algorithm_run_long));
                this.buttonExtendedEuclideanRunExample1.setVisibility(View.GONE);
                this.buttonExtendedEuclideanRunExample2.setVisibility(View.GONE);
                this.buttonExtendedEuclideanRunExample3.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonExtendedEuclideanRun.setText(requireContext().getText(R.string.extended_euclidean_algorithm_run_short));
                this.buttonExtendedEuclideanRunExample1.setVisibility(View.VISIBLE);
                this.buttonExtendedEuclideanRunExample2.setVisibility(View.VISIBLE);
                this.buttonExtendedEuclideanRunExample3.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerClipboardButtons() {
        try {
            boolean biggerClipboardButtons = UserSettings.GetBiggerClipboardButtons(requireContext());

            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewExtendedEuclideanCopyA, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewExtendedEuclideanPasteA, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewExtendedEuclideanClearA, biggerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewExtendedEuclideanCopyB, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewExtendedEuclideanPasteB, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewExtendedEuclideanClearB, biggerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewExtendedEuclideanExpandResult, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewExtendedEuclideanCopyResult, biggerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewExtendedEuclideanClearResult, biggerClipboardButtons);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerControls() {
        try {
            boolean biggerControls = UserSettings.GetBiggerControls(requireContext());

            // Label
            ControlDisplay.SetInputLabelFontSize(textViewExtendedEuclideanLabelA, biggerControls);
            ControlDisplay.SetInputLabelFontSize(textViewExtendedEuclideanLabelElasticA, biggerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextExtendedEuclideanA, biggerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewExtendedEuclideanLabelB, biggerControls);
            ControlDisplay.SetInputLabelFontSize(textViewExtendedEuclideanLabelElasticB, biggerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextExtendedEuclideanB, biggerControls);
            // Buttons
            ControlDisplay.SetButtonFontSize(buttonExtendedEuclideanRun, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonExtendedEuclideanRunExample1, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonExtendedEuclideanRunExample2, biggerControls);
            ControlDisplay.SetButtonFontSize(buttonExtendedEuclideanRunExample3, biggerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewExtendedEuclideanLabelResult, biggerControls);
            ControlDisplay.SetInputLabelFontSize(textViewExtendedEuclideanLabelElasticResult, biggerControls);
            // Output
            ControlDisplay.SetOutputFontSize(editTextExtendedEuclideanResult, biggerControls);
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
        if (algorithmName == AlgorithmName.EXTENDED_EUCLIDEAN_ALGORITHM) {
            if (progressStatus == ProgressStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextExtendedEuclideanResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextExtendedEuclideanResult.setText(resultFromHtml);
            }
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void OnButtonRun(ViewGroup container, Button button, boolean skipLabelResult, boolean displayProgressDialog) {
        try {
            // Check.
            if(UIHelper.checkInputMustBeGreaterThanMin(requireContext(), editTextExtendedEuclideanA, textViewExtendedEuclideanLabelA, textViewExtendedEuclideanLabelElasticA, "a", BigInteger.ZERO)) {
                return;
            }
            if(UIHelper.checkInputMustBeGreaterThanMin(requireContext(), editTextExtendedEuclideanB, textViewExtendedEuclideanLabelB, textViewExtendedEuclideanLabelElasticB, "b", BigInteger.ZERO)) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextExtendedEuclideanA.getText().toString());
            BigInteger b = new BigInteger(editTextExtendedEuclideanB.getText().toString());

            // Reset result
            ResultReset(skipLabelResult);

            // Before action performing.
            BeforeActionPerforming(button);

            // Perform the euclidean algorithm
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.EXTENDED_EUCLIDEAN_ALGORITHM, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample1(ViewGroup container, boolean displayProgressDialog) {
        try {
            //
            editTextExtendedEuclideanA.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_1_a));
            editTextExtendedEuclideanB.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_1_b));
            this.textViewExtendedEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            OnButtonRun(container, buttonExtendedEuclideanRunExample1, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample2(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextExtendedEuclideanA.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_2_a));
            editTextExtendedEuclideanB.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_2_b));
            this.textViewExtendedEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            OnButtonRun(container, buttonExtendedEuclideanRunExample2, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample3(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextExtendedEuclideanA.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_3_a));
            editTextExtendedEuclideanB.setText(requireContext().getText(R.string.extended_euclidean_algorithm_example_3_b));
            this.textViewExtendedEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            OnButtonRun(container, buttonExtendedEuclideanRunExample3, true, displayProgressDialog);
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
        editTextExtendedEuclideanA.clearFocus();
        editTextExtendedEuclideanB.clearFocus();
        // Select the last button clicked.
        ResetAllAndSelectButtonClicked(button);
    }
    private void ResetAllAndSelectClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewExtendedEuclideanCopyA.setSelected(false);
        textViewExtendedEuclideanPasteA.setSelected(false);
        textViewExtendedEuclideanClearA.setSelected(false);
        textViewExtendedEuclideanCopyB.setSelected(false);
        textViewExtendedEuclideanPasteB.setSelected(false);
        textViewExtendedEuclideanClearB.setSelected(false);
        textViewExtendedEuclideanExpandResult.setSelected(false);
        textViewExtendedEuclideanCopyResult.setSelected(false);
        textViewExtendedEuclideanClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void ResetAllAndSelectButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonExtendedEuclideanRun.setSelected(false);
        buttonExtendedEuclideanRunExample1.setSelected(false);
        buttonExtendedEuclideanRunExample2.setSelected(false);
        buttonExtendedEuclideanRunExample3.setSelected(false);
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
            textViewExtendedEuclideanLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextExtendedEuclideanResult.setText("");
    }
    //endregion RESULT

}