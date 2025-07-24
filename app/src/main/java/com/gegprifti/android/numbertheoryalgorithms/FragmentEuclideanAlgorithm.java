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
import java.math.BigInteger;
import com.gegprifti.android.numbertheoryalgorithms.common.ClipboardButtonDisplay;
import com.gegprifti.android.numbertheoryalgorithms.common.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.common.PopupDocumentation;
import com.gegprifti.android.numbertheoryalgorithms.common.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.common.Helper;
import com.gegprifti.android.numbertheoryalgorithms.common.UserSettings;


public class FragmentEuclideanAlgorithm extends FragmentBase implements Callback {
    private final static String TAG = "FragmentEA";

    TextView textViewEuclideanBackToAlgorithms;
    TextView textViewEuclideanTitle;
    TextView textViewEuclideanDocumentationFile;
    TextView textViewEuclideanLabelA;
    TextView textViewEuclideanLabelElasticA;
    TextView textViewEuclideanCopyA;
    TextView textViewEuclideanPasteA;
    TextView textViewEuclideanClearA;
    EditText editTextEuclideanA;
    TextView textViewEuclideanLabelB;
    TextView textViewEuclideanLabelElasticB;
    TextView textViewEuclideanCopyB;
    TextView textViewEuclideanPasteB;
    TextView textViewEuclideanClearB;
    EditText editTextEuclideanB;
    Button buttonEuclideanRun;
    Button buttonEuclideanRunExample1;
    Button buttonEuclideanRunExample2;
    Button buttonEuclideanRunExample3;
    Button buttonEuclideanRunExample4;
    TextView textViewEuclideanLabelResult;
    TextView textViewEuclideanLabelElasticResult;
    TextView textViewEuclideanExpandResult;
    TextView textViewEuclideanCopyResult;
    TextView textViewEuclideanClearResult;
    EditText editTextEuclideanResult;

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
            inflater = layoutInflater.inflate(R.layout.fragment_euclidean_algorithm, container, false);

            textViewEuclideanBackToAlgorithms = inflater.findViewById(R.id.TextViewEuclideanBackToAlgorithms);
            textViewEuclideanTitle = inflater.findViewById(R.id.TextViewEuclideanTitle);
            textViewEuclideanDocumentationFile = inflater.findViewById(R.id.TextViewEuclideanDocumentationFile);
            textViewEuclideanLabelA = inflater.findViewById(R.id.TextViewEuclideanLabelA);
            textViewEuclideanLabelElasticA = inflater.findViewById(R.id.TextViewEuclideanLabelElasticA);
            textViewEuclideanCopyA = inflater.findViewById(R.id.TextViewEuclideanCopyA);
            textViewEuclideanPasteA = inflater.findViewById(R.id.TextViewEuclideanPasteA);
            textViewEuclideanClearA = inflater.findViewById(R.id.TextViewEuclideanClearA);
            editTextEuclideanA = inflater.findViewById(R.id.EditTextEuclideanA);
            textViewEuclideanLabelB = inflater.findViewById(R.id.TextViewEuclideanLabelB);
            textViewEuclideanLabelElasticB = inflater.findViewById(R.id.TextViewEuclideanLabelElasticB);
            textViewEuclideanCopyB = inflater.findViewById(R.id.TextViewEuclideanCopyB);
            textViewEuclideanPasteB = inflater.findViewById(R.id.TextViewEuclideanPasteB);
            textViewEuclideanClearB = inflater.findViewById(R.id.TextViewEuclideanClearB);
            editTextEuclideanB = inflater.findViewById(R.id.EditTextEuclideanB);
            buttonEuclideanRun = inflater.findViewById(R.id.ButtonEuclideanRun);
            buttonEuclideanRunExample1 = inflater.findViewById(R.id.ButtonEuclideanRunExample1);
            buttonEuclideanRunExample2 = inflater.findViewById(R.id.ButtonEuclideanRunExample2);
            buttonEuclideanRunExample3 = inflater.findViewById(R.id.ButtonEuclideanRunExample3);
            buttonEuclideanRunExample4 = inflater.findViewById(R.id.ButtonEuclideanRunExample4);
            textViewEuclideanLabelResult = inflater.findViewById(R.id.TextViewEuclideanLabelResult);
            textViewEuclideanLabelElasticResult = inflater.findViewById(R.id.TextViewEuclideanLabelElasticResult);
            textViewEuclideanExpandResult = inflater.findViewById(R.id.TextViewEuclideanExpandResult);
            textViewEuclideanCopyResult = inflater.findViewById(R.id.TextViewEuclideanCopyResult);
            textViewEuclideanClearResult = inflater.findViewById(R.id.TextViewEuclideanClearResult);
            editTextEuclideanResult = inflater.findViewById(R.id.EditTextEuclideanResult);

            // Input filter integer only
            editTextEuclideanA.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});
            editTextEuclideanB.setFilters(new InputFilter[]{Helper.inputFilterIntegerOnly});

            // Default UI status
            // Check if this is the first time the user is using this.
            if (UserSettings.getFirstTimeEA(requireContext())) {
                this.OnButtonRunExample1(container, false);
            }

            // Events
            textViewEuclideanBackToAlgorithms.setOnClickListener(view -> {
                if(fragmentTabAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms)fragmentTabAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    fragmentTabAlgorithms.SetFragment(fragmentAlgorithms);
                }
            });
            textViewEuclideanDocumentationFile.setOnClickListener(view -> {
                PopupDocumentation popupDocumentation = new PopupDocumentation(requireActivity(), requireContext(), PopupDocumentation.EUCLIDEAN_ALGORITHM_PDF);
                popupDocumentation.Show();
            });
            textViewEuclideanCopyA.setOnClickListener(v -> {
                Helper.CopyEditText(requireContext(), editTextEuclideanA);
                ResetAllAndSelectClipboardButtonClicked(textViewEuclideanCopyA);
            });
            textViewEuclideanCopyB.setOnClickListener(v -> {
                Helper.CopyEditText(requireContext(), editTextEuclideanB);
                ResetAllAndSelectClipboardButtonClicked(textViewEuclideanCopyB);
            });
            textViewEuclideanCopyResult.setOnClickListener(v -> {
                Helper.CopyEditText(requireContext(), editTextEuclideanResult);
                ResetAllAndSelectClipboardButtonClicked(textViewEuclideanCopyResult);
            });
            textViewEuclideanPasteA.setOnClickListener(v -> {
                Helper.PasteEditText(requireContext(), editTextEuclideanA);
                ResetAllAndSelectClipboardButtonClicked(textViewEuclideanPasteA);
            });
            textViewEuclideanPasteB.setOnClickListener(v -> {
                Helper.PasteEditText(requireContext(), editTextEuclideanB);
                ResetAllAndSelectClipboardButtonClicked(textViewEuclideanPasteB);
            });
            textViewEuclideanClearA.setOnClickListener(v -> {
                Helper.ClearEditText(requireContext(), editTextEuclideanA);
                ResetAllAndSelectClipboardButtonClicked(textViewEuclideanClearA);
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
            });
            textViewEuclideanClearB.setOnClickListener(v -> {
                Helper.ClearEditText(requireContext(), editTextEuclideanB);
                ResetAllAndSelectClipboardButtonClicked(textViewEuclideanClearB);
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
            });
            textViewEuclideanClearResult.setOnClickListener(v -> {
                Helper.ClearEditText(requireContext(), editTextEuclideanResult);
                ResetAllAndSelectClipboardButtonClicked(textViewEuclideanClearResult);
                // Reset the last button clicked.
                ResetAllAndSelectButtonClicked(null);
            });
            editTextEuclideanA.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String euclideanLabelA = "a" + Helper.GetNrOfDigits(s.toString());
                    textViewEuclideanLabelA.setText(euclideanLabelA);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            editTextEuclideanB.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    String euclideanLabelB = "b" + Helper.GetNrOfDigits(s.toString());
                    textViewEuclideanLabelB.setText(euclideanLabelB);
                    // Reset
                    ResultReset(false);
                    // Reset the last button clicked.
                    ResetAllAndSelectButtonClicked(null);
                }
            });
            buttonEuclideanRun.setOnClickListener(v -> OnButtonRun(container, buttonEuclideanRun, false, true));
            buttonEuclideanRunExample1.setOnClickListener(v -> OnButtonRunExample1(container, true));
            buttonEuclideanRunExample2.setOnClickListener(v -> OnButtonRunExample2(container, true));
            buttonEuclideanRunExample3.setOnClickListener(v -> OnButtonRunExample3(container, true));
            buttonEuclideanRunExample4.setOnClickListener(v -> OnButtonRunExample4(container, true));
            textViewEuclideanExpandResult.setOnClickListener(v -> {
                PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewEuclideanTitle.getText().toString(), editTextEuclideanResult.getText());
                popupResult.Show();
                ResetAllAndSelectClipboardButtonClicked(textViewEuclideanExpandResult);
            });
            editTextEuclideanResult.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }
                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null || s.toString().isEmpty()) {
                        textViewEuclideanExpandResult.setVisibility(View.GONE);
                    } else {
                        textViewEuclideanExpandResult.setVisibility(View.VISIBLE);
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
            menuInflater.inflate(R.menu.menu_fragment_euclidean_algorithm, menu);
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
            if (id == R.id.euclidean_algorithm_menu_example_1) {
                this.editTextEuclideanA.setText(requireContext().getText(R.string.euclidean_algorithm_example_1_a));
                this.editTextEuclideanB.setText(requireContext().getText(R.string.euclidean_algorithm_example_1_b));
                this.textViewEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_1));
                ResultReset(true);
                return true;
            }
            if (id == R.id.euclidean_algorithm_menu_example_2) {
                this.editTextEuclideanA.setText(requireContext().getText(R.string.euclidean_algorithm_example_2_a));
                this.editTextEuclideanB.setText(requireContext().getText(R.string.euclidean_algorithm_example_2_b));
                this.textViewEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_2));
                ResultReset(true);
                return true;
            }
            if (id == R.id.euclidean_algorithm_menu_example_3) {
                this.editTextEuclideanA.setText(requireContext().getText(R.string.euclidean_algorithm_example_3_a));
                this.editTextEuclideanB.setText(requireContext().getText(R.string.euclidean_algorithm_example_3_b));
                this.textViewEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_3));
                ResultReset(true);
                return true;
            }
            if (id == R.id.euclidean_algorithm_menu_example_4) {
                this.editTextEuclideanA.setText(requireContext().getText(R.string.euclidean_algorithm_example_4_a));
                this.editTextEuclideanB.setText(requireContext().getText(R.string.euclidean_algorithm_example_4_b));
                this.textViewEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_4));
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
            Typeface currentTypeface = editTextEuclideanResult.getTypeface();
            boolean showResultInMonospace = UserSettings.GetShowResultInMonospace(requireContext());
            if (currentTypeface != Typeface.MONOSPACE && showResultInMonospace) {
                editTextEuclideanResult.setTypeface(Typeface.MONOSPACE);
            } else if (currentTypeface == Typeface.MONOSPACE && !showResultInMonospace) {
                editTextEuclideanResult.setTypeface(Typeface.DEFAULT);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshHideExampleButtons() {
        try {
            boolean exampleButtonsAreVisible = this.buttonEuclideanRunExample1.getVisibility() == View.VISIBLE; // Just check one.
            boolean hideExampleButtons = UserSettings.GetHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonEuclideanRun.setText(requireContext().getText(R.string.euclidean_algorithm_run_long));
                this.buttonEuclideanRunExample1.setVisibility(View.GONE);
                this.buttonEuclideanRunExample2.setVisibility(View.GONE);
                this.buttonEuclideanRunExample3.setVisibility(View.GONE);
                this.buttonEuclideanRunExample4.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonEuclideanRun.setText(requireContext().getText(R.string.euclidean_algorithm_run_short));
                this.buttonEuclideanRunExample1.setVisibility(View.VISIBLE);
                this.buttonEuclideanRunExample2.setVisibility(View.VISIBLE);
                this.buttonEuclideanRunExample3.setVisibility(View.VISIBLE);
                this.buttonEuclideanRunExample4.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerClipboardButtons() {
        try {
            boolean smallerClipboardButtons = UserSettings.GetSmallerClipboardButtons(requireContext());

            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewEuclideanCopyA, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewEuclideanPasteA, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewEuclideanClearA, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewEuclideanCopyB, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewEuclideanPasteB, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewEuclideanClearB, smallerClipboardButtons);
            // Clipboard
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewEuclideanExpandResult, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewEuclideanCopyResult, smallerClipboardButtons);
            ClipboardButtonDisplay.SetClipboardButtonFontSize(textViewEuclideanClearResult, smallerClipboardButtons);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshSmallerControls() {
        try {
            boolean smallerControls = UserSettings.GetSmallerControls(requireContext());

            // Label
            ControlDisplay.SetInputLabelFontSize(textViewEuclideanLabelA, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewEuclideanLabelElasticA, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextEuclideanA, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewEuclideanLabelB, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewEuclideanLabelElasticB, smallerControls);
            // Input
            ControlDisplay.SetInputFontSize(editTextEuclideanB, smallerControls);
            // Buttons
            ControlDisplay.SetButtonFontSize(buttonEuclideanRun, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonEuclideanRunExample1, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonEuclideanRunExample2, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonEuclideanRunExample3, smallerControls);
            ControlDisplay.SetButtonFontSize(buttonEuclideanRunExample4, smallerControls);
            // Label
            ControlDisplay.SetInputLabelFontSize(textViewEuclideanLabelResult, smallerControls);
            ControlDisplay.SetInputLabelFontSize(textViewEuclideanLabelElasticResult, smallerControls);
            // Output
            ControlDisplay.SetOutputFontSize(editTextEuclideanResult, smallerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Display


    //region Callback
    /**
     *
     * @param algorithmName
     * @param result
     * @param algorithmStatus
     */
    @Override
    public void callbackResult(AlgorithmName algorithmName, Object result, AlgorithmStatus algorithmStatus) {
        // This is to prevent the error: Non-fatal Exception: java.lang.IllegalStateException: Fragment FragmentPrimesList{94d7331} (36e8cdd6-9d00-4c2a-bd07-ab5550e2c88b) not attached to a context.
        // java.lang.IllegalStateException: Fragment not attached to Activity -> https://stackoverflow.com/questions/28672883/java-lang-illegalstateexception-fragment-not-attached-to-activity
        Activity activity = getActivity();
        if (activity == null || !this.isAdded()) {
            return;
        }
        if (algorithmName == AlgorithmName.EUCLIDEAN_ALGORITHM) {
            if (algorithmStatus == AlgorithmStatus.CANCELED) {
                String resultCanceledText = requireContext().getResources().getString(R.string.canceled);
                editTextEuclideanResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextEuclideanResult.setText(resultFromHtml);
            }
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void OnButtonRun(ViewGroup container, Button button, boolean skipLabelResult, boolean displayProgressDialog) {
        try {
            // Check.
            if(Helper.checkInputMustBeNumber(requireContext(), editTextEuclideanA, textViewEuclideanLabelA, textViewEuclideanLabelElasticA, "a")) {
                return;
            }
            if(Helper.checkInputMustBeNumber(requireContext(), editTextEuclideanB, textViewEuclideanLabelB, textViewEuclideanLabelElasticB, "b")) {
                return;
            }

            // Get numbers
            BigInteger a = new BigInteger(editTextEuclideanA.getText().toString());
            BigInteger b = new BigInteger(editTextEuclideanB.getText().toString());

            // Reset result
            ResultReset(skipLabelResult);

            // Before action performing.
            BeforeActionPerforming(button);

            // Perform the euclidean algorithm
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.EUCLIDEAN_ALGORITHM, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(null);
            progressManager.startWork(container, algorithmParameters, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample1(ViewGroup container, boolean displayProgressDialog) {
        try {
            //
            editTextEuclideanA.setText(requireContext().getText(R.string.euclidean_algorithm_example_1_a));
            editTextEuclideanB.setText(requireContext().getText(R.string.euclidean_algorithm_example_1_b));
            this.textViewEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            OnButtonRun(container, buttonEuclideanRunExample1, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample2(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextEuclideanA.setText(requireContext().getText(R.string.euclidean_algorithm_example_2_a));
            editTextEuclideanB.setText(requireContext().getText(R.string.euclidean_algorithm_example_2_b));
            this.textViewEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            OnButtonRun(container, buttonEuclideanRunExample2, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample3(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextEuclideanA.setText(requireContext().getText(R.string.euclidean_algorithm_example_3_a));
            editTextEuclideanB.setText(requireContext().getText(R.string.euclidean_algorithm_example_3_b));
            this.textViewEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            OnButtonRun(container, buttonEuclideanRunExample3, true, displayProgressDialog);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void OnButtonRunExample4(ViewGroup container, boolean displayProgressDialog) {
        try {
            editTextEuclideanA.setText(requireContext().getText(R.string.euclidean_algorithm_example_4_a));
            editTextEuclideanB.setText(requireContext().getText(R.string.euclidean_algorithm_example_4_b));
            this.textViewEuclideanLabelResult.setText(requireContext().getText(R.string.result_example_4));
            //
            OnButtonRun(container, buttonEuclideanRunExample4, true, displayProgressDialog);
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
        editTextEuclideanA.clearFocus();
        editTextEuclideanB.clearFocus();
        // Select the last button clicked.
        ResetAllAndSelectButtonClicked(button);
    }
    private void ResetAllAndSelectClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewEuclideanCopyA.setSelected(false);
        textViewEuclideanPasteA.setSelected(false);
        textViewEuclideanClearA.setSelected(false);
        textViewEuclideanCopyB.setSelected(false);
        textViewEuclideanPasteB.setSelected(false);
        textViewEuclideanClearB.setSelected(false);
        textViewEuclideanExpandResult.setSelected(false);
        textViewEuclideanCopyResult.setSelected(false);
        textViewEuclideanClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void ResetAllAndSelectButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonEuclideanRun.setSelected(false);
        buttonEuclideanRunExample1.setSelected(false);
        buttonEuclideanRunExample2.setSelected(false);
        buttonEuclideanRunExample3.setSelected(false);
        buttonEuclideanRunExample4.setSelected(false);
        // Select the last button clicked.
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
            textViewEuclideanLabelResult.setText(requireContext().getText(R.string.result));
        }
        editTextEuclideanResult.setText("");
    }
    //endregion RESULT

}