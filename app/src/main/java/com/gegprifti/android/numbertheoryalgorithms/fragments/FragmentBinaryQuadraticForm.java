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
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.InputGroup;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.GridAdapter;
import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
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

    TextView textViewBackToAlgorithms;
    TextView textViewTitle;
    TextView textViewDocumentationFile;
    TextView textViewLabelExpression;
    TextView textViewLabelElasticExpression;
    TextView textViewCopyExpression;
    TextView textViewPasteExpression;
    TextView textViewClearExpression;
    EditText editTextExpression;
    LinearLayout linearLayoutExamplesContainer;
    Button buttonRunExample1;
    Button buttonRunExample2;
    Button buttonRunExample3;
    Button buttonRunExample4;
    Button buttonRunExample5;
    Button buttonRun;
    Button buttonRun1;
    Button buttonRun2;
    LinearLayout linearLayoutFModMContainer;
    Button buttonMMinus;
    Button buttonM;
    Button buttonMPlus;
    Button buttonRMinus;
    Button buttonR;
    Button buttonRPlus;
    TextView textViewLabelResult;
    TextView textViewLabelElasticResult;
    TextView textViewExpandResult;
    TextView textViewCopyResult;
    TextView textViewClearResult;
    LinearLayout linearLayoutResultContainer;
    EditText editTextResult;
    LinearLayout linearLayoutResultGridContainer1;
    LinearLayout linearLayoutStaticColumnHeader1;
    ListView listViewResult1;
    LinearLayout linearLayoutResultGridContainer2;
    LinearLayout linearLayoutStaticColumnHeader2;
    ListView listViewResult2;
    MenuItem menuItemIncludeTrivialSolutions;
    MenuItem menuItemIncludeOnlyPositiveSolutions;
    MenuItem menuItemIncludeOnlyNegativeSolutions;
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
            inflater = layoutInflater.inflate(R.layout.fragment_binary_quadratic_form, container, false);
            this.textViewBackToAlgorithms = inflater.findViewById(R.id.TextViewBackToAlgorithms);
            this.textViewTitle = inflater.findViewById(R.id.TextViewTitle);
            this.textViewDocumentationFile = inflater.findViewById(R.id.TextViewDocumentationFile);
            this.textViewLabelExpression = inflater.findViewById(R.id.TextViewLabelExpression);
            this.textViewLabelElasticExpression = inflater.findViewById(R.id.TextViewLabelElasticExpression);
            this.textViewCopyExpression = inflater.findViewById(R.id.TextViewCopyExpression);
            this.textViewPasteExpression = inflater.findViewById(R.id.TextViewPasteExpression);
            this.textViewClearExpression = inflater.findViewById(R.id.TextViewClearExpression);
            this.editTextExpression = inflater.findViewById(R.id.EditTextExpression);
            this.buttonM = inflater.findViewById(R.id.ButtonM);
            this.buttonR = inflater.findViewById(R.id.ButtonR);
            this.linearLayoutExamplesContainer = inflater.findViewById(R.id.LinearLayoutExamplesContainer);
            this.buttonRunExample1 = inflater.findViewById(R.id.ButtonRunExample1);
            this.buttonRunExample2 = inflater.findViewById(R.id.ButtonRunExample2);
            this.buttonRunExample3 = inflater.findViewById(R.id.ButtonRunExample3);
            this.buttonRunExample4 = inflater.findViewById(R.id.ButtonRunExample4);
            this.buttonRunExample5 = inflater.findViewById(R.id.ButtonRunExample5);
            this.buttonRun = inflater.findViewById(R.id.ButtonRun);
            this.buttonRun1 = inflater.findViewById(R.id.ButtonRun1);
            this.buttonRun2 = inflater.findViewById(R.id.ButtonRun2);
            this.textViewLabelResult = inflater.findViewById(R.id.TextViewLabelResult);
            this.textViewLabelElasticResult = inflater.findViewById(R.id.TextViewLabelElasticResult);
            this.textViewExpandResult = inflater.findViewById(R.id.TextViewExpandResult);
            this.textViewCopyResult = inflater.findViewById(R.id.TextViewCopyResult);
            this.buttonMMinus = inflater.findViewById(R.id.ButtonMMinus);
            this.buttonMPlus = inflater.findViewById(R.id.ButtonMPlus);
            this.buttonRMinus = inflater.findViewById(R.id.ButtonRMinus);
            this.buttonRPlus = inflater.findViewById(R.id.ButtonRPlus);
            this.textViewClearResult = inflater.findViewById(R.id.TextViewClearResult);
            this.linearLayoutResultContainer = inflater.findViewById(R.id.LinearLayoutResultContainer);
            this.editTextResult = inflater.findViewById(R.id.EditTextResult);
            this.linearLayoutResultGridContainer1 = inflater.findViewById(R.id.LinearLayoutResultGridContainer1);
            this.linearLayoutFModMContainer = inflater.findViewById(R.id.LinearLayoutFModMContainer);
            this.linearLayoutStaticColumnHeader1 = inflater.findViewById(R.id.LinearLayoutStaticColumnHeader1);
            this.listViewResult1 = inflater.findViewById(R.id.ListViewResult1);
            this.linearLayoutResultGridContainer2 = inflater.findViewById(R.id.LinearLayoutResultGridContainer2);
            this.linearLayoutStaticColumnHeader2 = inflater.findViewById(R.id.LinearLayoutStaticColumnHeader2);
            this.listViewResult2 = inflater.findViewById(R.id.ListViewResult2);

            // Events
            this.textViewBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });
            this.textViewDocumentationFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.QUADRATIC_FORM_PDF).show(getParentFragmentManager(), "QUADRATIC_FORM_PDF");
                }
            });
            this.textViewCopyExpression.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextExpression);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewCopyExpression);
                }
            });
            this.textViewPasteExpression.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.pasteToEditTextAsExpressionBDEF(requireContext(), editTextExpression);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewPasteExpression);
                }
            });
            this.textViewClearExpression.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextExpression);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewClearExpression);
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            this.textViewExpandResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (linearLayoutResultContainer.getVisibility() == View.VISIBLE) {
                        PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), editTextResult.getText());
                        popupResult.show();
                    } else if (linearLayoutResultGridContainer1.getVisibility() == View.VISIBLE) {
                        PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), linearLayoutStaticColumnHeader1, listViewResult1);
                        popupResult.show();
                    } else if (linearLayoutResultGridContainer2.getVisibility() == View.VISIBLE) {
                        PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), linearLayoutStaticColumnHeader2, listViewResult2);
                        popupResult.show();
                    }
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewExpandResult);
                }
            });
            this.textViewCopyResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.copyEditText(requireContext(), editTextResult);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewCopyResult);
                }
            });
            this.buttonMMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mText = buttonM.getText().toString();
                    if (!mText.isEmpty()) {
                        int m = Integer.parseInt(mText);
                        if (m <= 1) { // m value should not be 0 otherwise we get "modulus not positive" or "m.signum() <= 0" exceptions when doing m.mod(r)
                            buttonM.setText("");
                        } else {
                            m = m - 1;
                            buttonM.setText(String.valueOf(m));
                        }
                    }
                    calculateFModM();
                }
            });
            this.buttonMPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mText = buttonM.getText().toString();
                    if (mText.isEmpty()) {
                        buttonM.setText("1"); // m value should not be 0 otherwise we get "modulus not positive" or "m.signum() <= 0" exceptions when doing m.mod(r)
                    } else {
                        int m = Integer.parseInt(mText);
                        m = m + 1;
                        buttonM.setText(String.valueOf(m));
                    }
                    calculateFModM();
                }
            });
            this.buttonRMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String rText = buttonR.getText().toString();
                    if (!rText.isEmpty()) {
                        int r = Integer.parseInt(rText);
                        if (r <= 0) {
                            buttonR.setText("");
                        } else {
                            r -= 1;
                            buttonR.setText(String.valueOf(r));
                        }
                    }
                    calculateFModM();
                }
            });
            this.buttonRPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String rText = buttonR.getText().toString();
                    if (rText.isEmpty()) {
                        buttonR.setText("0");
                    } else {
                        String mText = buttonM.getText().toString();
                        if (mText.isEmpty()) {
                            buttonR.setText("");
                        } else {
                            int m = Integer.parseInt(mText);
                            int r = Integer.parseInt(rText);
                            r += 1;
                            if (r < m) {
                                buttonR.setText(String.valueOf(r));
                            }
                        }
                    }
                    calculateFModM();
                }
            });
            this.textViewClearResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.clearEditText(requireContext(), editTextResult);
                    resetResult(false);
                    resetAllAndSelectTheLastClipboardButtonClicked(textViewClearResult);
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            this.editTextExpression.addTextChangedListener(new TextWatcher() {
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
            this.buttonM.addTextChangedListener(new TextWatcher() {
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
            this.buttonR.addTextChangedListener(new TextWatcher() {
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
            this.buttonRunExample1.setOnClickListener(v -> onButtonRunExample1(container));
            this.buttonRunExample2.setOnClickListener(v -> onButtonRunExample2(container));
            this.buttonRunExample3.setOnClickListener(v -> onButtonRunExample3(container));
            this.buttonRunExample4.setOnClickListener(v -> onButtonRunExample4(container));
            this.buttonRunExample5.setOnClickListener(v -> onButtonRunExample5(container));
            this.buttonRun.setOnClickListener(v -> onButtonRun(container, buttonRun, false));
            this.buttonRun1.setOnClickListener(v -> onButtonRun1(container));
            this.buttonRun2.setOnClickListener(v -> onButtonRun2(container));
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
        this.menuItemIncludeTrivialSolutions = menu.findItem(R.id.quadratic_form_menu_include_trivial_solutions);
        this.menuItemIncludeOnlyPositiveSolutions = menu.findItem(R.id.quadratic_form_menu_include_only_positive_solutions);
        this.menuItemIncludeOnlyNegativeSolutions = menu.findItem(R.id.quadratic_form_menu_include_only_negative_solutions);
        // Get the value from shared preferences.
        boolean includeTrivialSolutions = UserSettings.getBQFIncludeTrivialSolutions(requireContext());
        menuItemIncludeTrivialSolutions.setChecked(includeTrivialSolutions);
        boolean includeOnlyPositiveSolutions = UserSettings.getBQFIncludeOnlyPositiveSolutions(requireContext());
        menuItemIncludeOnlyPositiveSolutions.setChecked(includeOnlyPositiveSolutions);
        boolean includeOnlyNegativeSolutions = UserSettings.getBQFIncludeOnlyNegativeSolutions(requireContext());
        menuItemIncludeOnlyNegativeSolutions.setChecked(includeOnlyNegativeSolutions);
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
                boolean isChecked = !menuItemIncludeTrivialSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setBQFIncludeTrivialSolutions(requireContext(), isChecked);
                menuItemIncludeTrivialSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_include_only_positive_solutions) {
                boolean isChecked = !menuItemIncludeOnlyPositiveSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setBQFIncludeOnlyPositiveSolutions(requireContext(), isChecked);
                menuItemIncludeOnlyPositiveSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_include_only_negative_solutions) {
                boolean isChecked = !menuItemIncludeOnlyNegativeSolutions.isChecked();
                // Store the value to shared preferences.
                UserSettings.setBQFIncludeOnlyNegativeSolutions(requireContext(), isChecked);
                menuItemIncludeOnlyNegativeSolutions.setChecked(isChecked);
                resetResult(false);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_1) {
                this.editTextExpression.setText(requireContext().getText(R.string.quadratic_form_example_1_expression));
                this.buttonM.setText(requireContext().getText(R.string.quadratic_form_example_1_m));
                this.buttonR.setText(requireContext().getText(R.string.quadratic_form_example_1_r));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_2) {
                this.editTextExpression.setText(requireContext().getText(R.string.quadratic_form_example_2_expression));
                this.buttonM.setText(requireContext().getText(R.string.quadratic_form_example_2_m));
                this.buttonR.setText(requireContext().getText(R.string.quadratic_form_example_2_r));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_3) {
                this.editTextExpression.setText(requireContext().getText(R.string.quadratic_form_example_3_expression));
                this.buttonM.setText(requireContext().getText(R.string.quadratic_form_example_3_m));
                this.buttonR.setText(requireContext().getText(R.string.quadratic_form_example_3_r));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_4) {
                this.editTextExpression.setText(requireContext().getText(R.string.quadratic_form_example_4_expression));
                this.buttonM.setText(requireContext().getText(R.string.quadratic_form_example_4_m));
                this.buttonR.setText(requireContext().getText(R.string.quadratic_form_example_4_r));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_4));
                resetResult(true);
                return true;
            }
            if (id == R.id.quadratic_form_menu_example_5) {
                this.editTextExpression.setText(requireContext().getText(R.string.quadratic_form_example_5_expression));
                this.buttonM.setText(requireContext().getText(R.string.quadratic_form_example_5_m));
                this.buttonR.setText(requireContext().getText(R.string.quadratic_form_example_5_r));
                this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_5));
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
            boolean exampleButtonsAreVisible = this.linearLayoutExamplesContainer.getVisibility() == View.VISIBLE;
            boolean hideExampleButtons = UserSettings.getHideExampleButtons(requireContext());
            if (exampleButtonsAreVisible && hideExampleButtons) {
                this.buttonRun.setText(requireContext().getText(R.string.quadratic_form_run_short));
                this.linearLayoutExamplesContainer.setVisibility(View.GONE);
            } else if (!exampleButtonsAreVisible && !hideExampleButtons) {
                this.buttonRun.setText(requireContext().getText(R.string.quadratic_form_run_short));
                this.linearLayoutExamplesContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    private void refreshBiggerControls() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons
            ControlDisplay.setClipboardButtonFontSize(textViewCopyExpression, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPasteExpression, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearExpression, biggerControls);
            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewCopyResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(this.textViewLabelExpression, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewLabelElasticExpression, biggerControls);
            // InputGroup
            ControlDisplay.setInputFontSize(this.editTextExpression, biggerControls);
            // Buttons
            ControlDisplay.setButtonFontSize(buttonRunExample1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample2, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample3, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample4, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRunExample5, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRun, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRun1, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRun2, biggerControls);
            // Buttons.
            ControlDisplay.setButtonFontSize(buttonMMinus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonM, biggerControls);
            ControlDisplay.setButtonFontSize(buttonMPlus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRMinus, biggerControls);
            ControlDisplay.setButtonFontSize(buttonR, biggerControls);
            ControlDisplay.setButtonFontSize(buttonRPlus, biggerControls);
            // Label
            ControlDisplay.setInputLabelFontSize(this.textViewLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewLabelElasticResult, biggerControls);
            // Output
            ControlDisplay.setOutputFontSize(this.editTextResult, biggerControls);
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
                editTextResult.setText(resultCanceledText);
            } else {
                String resultAsString = (String)result;
                CharSequence resultFromHtml = Html.fromHtml(resultAsString);
                editTextResult.setText(resultFromHtml);
            }
        }

        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM_1) {
            if (progressStatus == ProgressStatus.CANCELED) {
                cancelShowResult(listViewResult1);
            } else {
                @SuppressWarnings("unchecked")
                List<List<RowItem>> sqfResultList1 = (List<List<RowItem>>)result;
                showResult1(sqfResultList1);
            }
        }

        if (algorithmName == AlgorithmName.BINARY_QUADRATIC_FORM_2) {
            if (progressStatus == ProgressStatus.CANCELED) {
                cancelShowResult(listViewResult2);
            } else {
                @SuppressWarnings("unchecked")
                List<List<RowItem>> sqfResultList2 = (List<List<RowItem>>)result;
                showResult2(sqfResultList2);
            }
        }
    }
    private void cancelShowResult(ListView listView) {
        ArrayList<String> listItems=new ArrayList<>();
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(requireContext(), R.layout.row_item_canceled, R.id.RowItemCanceled, listItems);
        listView.setAdapter(adapter);
        listItems.add(requireContext().getResources().getString(R.string.canceled));
        adapter.notifyDataSetChanged();
    }
    private void showResult1(List<List<RowItem>> rows) {
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
                listViewResult1.setDividerHeight((int) UIHelper.convertDpToPixel(4F, requireContext()));
            } else {
                listViewResult1.setDividerHeight((int) UIHelper.convertDpToPixel(1F, requireContext()));
            }

            // Create and set the adapter.
            GridAdapter adapter = new GridAdapter(requireContext(), linearLayoutStaticColumnHeader1, rows, rowItemWidth, rowItemWidths, rowItemHeight, null, biggerResultDisplay);
            listViewResult1.setAdapter(adapter);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void showResult2(List<List<RowItem>> rows) {
        try {
            if(rows == null || rows.isEmpty()) {
                return;
            }

            // Get the values from shared preferences.
            boolean biggerResultDisplay = UserSettings.getBiggerResultDisplay(requireContext());
            boolean squareResultDisplay = UserSettings.getSquareResultDisplay(requireContext());

            BigInteger m = null;
            BigInteger r = null;
            String mText = this.buttonM.getText().toString();
            if (!mText.isEmpty()) {
                m = new BigInteger(mText);
            }
            String rText = this.buttonR.getText().toString();
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
                listViewResult2.setDividerHeight((int) UIHelper.convertDpToPixel(4F, requireContext()));
            } else {
                listViewResult2.setDividerHeight((int) UIHelper.convertDpToPixel(1F, requireContext()));
            }

            // Create and set the adapter.
            GridAdapter adapter = new GridAdapter(requireContext(), linearLayoutStaticColumnHeader2, rows, rowItemWidth, null, rowItemHeight, null, biggerResultDisplay);
            showResultFModM2(adapter, m, r);
            listViewResult2.setAdapter(adapter);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void onButtonRunExample1(ViewGroup container) {
        try {
            this.editTextExpression.setText(requireContext().getText(R.string.quadratic_form_example_1_expression));
            this.buttonM.setText(requireContext().getText(R.string.quadratic_form_example_1_m));
            this.buttonR.setText(requireContext().getText(R.string.quadratic_form_example_1_r));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_1));
            //
            onButtonRun(container, buttonRunExample1, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample2(ViewGroup container) {
        try {
            this.editTextExpression.setText(requireContext().getText(R.string.quadratic_form_example_2_expression));
            this.buttonM.setText(requireContext().getText(R.string.quadratic_form_example_2_m));
            this.buttonR.setText(requireContext().getText(R.string.quadratic_form_example_2_r));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_2));
            //
            onButtonRun(container, buttonRunExample2, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample3(ViewGroup container) {
        try {
            this.editTextExpression.setText(requireContext().getText(R.string.quadratic_form_example_3_expression));
            this.buttonM.setText(requireContext().getText(R.string.quadratic_form_example_3_m));
            this.buttonR.setText(requireContext().getText(R.string.quadratic_form_example_3_r));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_3));
            //
            onButtonRun(container, buttonRunExample3, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample4(ViewGroup container) {
        try {
            this.editTextExpression.setText(requireContext().getText(R.string.quadratic_form_example_4_expression));
            this.buttonM.setText(requireContext().getText(R.string.quadratic_form_example_4_m));
            this.buttonR.setText(requireContext().getText(R.string.quadratic_form_example_4_r));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_4));
            //
            onButtonRun(container, buttonRunExample4, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRunExample5(ViewGroup container) {
        try {
            this.editTextExpression.setText(requireContext().getText(R.string.quadratic_form_example_5_expression));
            this.buttonM.setText(requireContext().getText(R.string.quadratic_form_example_5_m));
            this.buttonR.setText(requireContext().getText(R.string.quadratic_form_example_5_r));
            this.textViewLabelResult.setText(requireContext().getText(R.string.result_example_5));
            //
            onButtonRun(container, buttonRunExample5, true);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private InputGroup getInputGroupExpression() {
        return new InputGroup.Builder()
                .setIsCompactInputView(isCompactInputView)
                .setLabel(textViewLabelExpression, "bxy+dx+ey=f", textViewLabelElasticExpression)
                .setInput(editTextExpression)
                .setCompactControls(null, null) // TODO +++ remove null when implemented.
                .build();
    }
    private void onButtonRun(ViewGroup container, Button button, boolean skipLabelResult) {
        try {
            resetResult(skipLabelResult);

            // Check.
            InputGroup inputGroupExpression = getInputGroupExpression();
            List<BigInteger> expression = UIHelper.checkInputAndGetQuadraticFormExpression(requireContext(), inputGroupExpression);
            if (expression == null) {
                return;
            }
            String errorMessage = "";
            if (expression.size() == 6) {
                // The a, b, c, d, e, f Quadratic Form is not supported here yet.
                errorMessage = "The <b>a, b, c, d, e, f</b> Quadratic Form is not supported here yet.";
            }
            if (!errorMessage.isEmpty()) {
                UIHelper.displayError(inputGroupExpression);
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

            boolean includeTrivialSolutions = UserSettings.getBQFIncludeTrivialSolutions(requireContext());
            boolean includeOnlyPositiveSolutions = UserSettings.getBQFIncludeOnlyPositiveSolutions(requireContext());
            boolean includeOnlyNegativeSolutions = UserSettings.getBQFIncludeOnlyNegativeSolutions(requireContext());

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM, this);
            algorithmParameters.setInput1(b);
            algorithmParameters.setInput2(d);
            algorithmParameters.setInput3(e);
            algorithmParameters.setInput4(f);
            algorithmParameters.setIncludeTrivialSolutions(includeTrivialSolutions);
            algorithmParameters.setIncludeOnlyPositiveSolutions(includeOnlyPositiveSolutions);
            algorithmParameters.setIncludeOnlyNegativeSolutions(includeOnlyNegativeSolutions);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRun1(ViewGroup container) {
        try {
            resetResult(false);

            // Check.
            InputGroup inputGroupExpression = getInputGroupExpression();
            List<BigInteger> expression = UIHelper.checkInputAndGetQuadraticFormExpression(requireContext(), inputGroupExpression);
            if (expression == null) {
                return;
            }
            String errorMessage = "";
            if (expression.size() == 6) {
                // The a, b, c, d, e, f Quadratic Form is not supported here yet.
                errorMessage = "The <b>a, b, c, d, e, f</b> Quadratic Form is not supported here yet.";
            }
            if (!errorMessage.isEmpty()) {
                UIHelper.displayError(inputGroupExpression);
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
            beforeActionPerforming(buttonRun1);

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM_1, this);
            algorithmParameters.setInput1(b);
            algorithmParameters.setInput2(d);
            algorithmParameters.setInput3(e);
            algorithmParameters.setInput4(f);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void onButtonRun2(ViewGroup container) {
        try {
            resetResult(false);

            // Check.
            InputGroup inputGroupExpression = getInputGroupExpression();
            List<BigInteger> expression = UIHelper.checkInputAndGetQuadraticFormExpression(requireContext(), inputGroupExpression);
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
            beforeActionPerforming(buttonRun2);

            // Perform the quadratic form
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.BINARY_QUADRATIC_FORM_2, this);
            algorithmParameters.setInput1(a);
            algorithmParameters.setInput2(b);
            algorithmParameters.setInput3(c);
            algorithmParameters.setInput4(d);
            algorithmParameters.setInput5(e);
            algorithmParameters.setInput6(f);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void calculateFModM() {
        if (this.listViewResult2.getAdapter() != null) {
            GridAdapter gridAdapter = (GridAdapter) this.listViewResult2.getAdapter();

            BigInteger m = null;
            BigInteger r = null;

            String mText = this.buttonM.getText().toString();
            String rText = this.buttonR.getText().toString();

            if (!mText.isEmpty()) {
                m = new BigInteger(mText);
            }
            if (!rText.isEmpty()) {
                r = new BigInteger(rText);
            }

            showResultFModM2(gridAdapter, m, r);
        }
    }
    public static void showResultFModM2(GridAdapter gridAdapter, BigInteger m, BigInteger r) {
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
        editTextExpression.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewCopyExpression.setSelected(false);
        textViewPasteExpression.setSelected(false);
        textViewClearExpression.setSelected(false);
        textViewExpandResult.setSelected(false);
        textViewCopyResult.setSelected(false);
        textViewClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void resetAllAndSelectTheLastButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonRunExample1.setSelected(false);
        buttonRunExample2.setSelected(false);
        buttonRunExample3.setSelected(false);
        buttonRunExample4.setSelected(false);
        buttonRunExample5.setSelected(false);
        buttonRun.setSelected(false);
        buttonRun1.setSelected(false);
        buttonRun2.setSelected(false);
        // Select the last button clicked.
        if (button != null) {
            button.setSelected(true);
            textViewExpandResult.setVisibility(View.VISIBLE);
        } else {
            textViewExpandResult.setVisibility(View.GONE);
        }
    }
    private void resetResult(boolean skipLabelResult) {
        // Reset the last clipboard clicked.
        resetAllAndSelectTheLastClipboardButtonClicked(null);
        // Reset the last button clicked.
        resetAllAndSelectTheLastButtonClicked(null);
        //
        if(!skipLabelResult) {
            textViewLabelResult.setText(requireContext().getText(R.string.result));
        }
        this.editTextResult.setText("");
        this.linearLayoutStaticColumnHeader1.removeAllViews();
        this.linearLayoutStaticColumnHeader2.removeAllViews();
        this.listViewResult1.setAdapter(null);
        this.listViewResult2.setAdapter(null);
    }
    private void setResultVisibilityFromButtonRun(boolean skipLabelResult) {
        if(!skipLabelResult) {
            textViewLabelResult.setText(requireContext().getText(R.string.result));
        }
        this.linearLayoutFModMContainer.setVisibility(View.GONE);
        this.textViewCopyResult.setVisibility(View.VISIBLE);
        this.textViewClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutResultContainer.setVisibility(View.VISIBLE);
        this.linearLayoutResultGridContainer1.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer2.setVisibility(View.GONE);
    }
    private void setResultVisibilityFromButtonRun1() {
        textViewLabelResult.setText(requireContext().getText(R.string.representation));
        this.linearLayoutFModMContainer.setVisibility(View.GONE);
        this.textViewCopyResult.setVisibility(View.GONE);
        this.textViewClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutResultContainer.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer1.setVisibility(View.VISIBLE);
        this.linearLayoutResultGridContainer2.setVisibility(View.GONE);
    }
    private void setResultVisibilityFromButtonRun2() {
        textViewLabelResult.setText(requireContext().getText(R.string.quadratic_form_result_representation_f_mod_m_r));
        this.linearLayoutFModMContainer.setVisibility(View.VISIBLE);
        this.textViewCopyResult.setVisibility(View.GONE);
        this.textViewClearResult.setVisibility(View.VISIBLE);
        this.linearLayoutResultContainer.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer1.setVisibility(View.GONE);
        this.linearLayoutResultGridContainer2.setVisibility(View.VISIBLE);
    }
    //endregion RESULT

}