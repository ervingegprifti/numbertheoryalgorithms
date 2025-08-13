package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.Editable;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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


public class FragmentPrimesList extends FragmentBase implements Callback {
    private final static String TAG = FragmentPrimesList.class.getSimpleName();

    private TextView textViewPrimesListTitle;
    private TextView textViewPrimesListLabelColumns;
    private TextView textViewPrimesListLabelNumbers;
    private Button buttonPrimesListColumnsMinus;
    private Button buttonPrimesListColumns;
    private Button buttonPrimesListColumnsPlus;
    private Button buttonPrimesListNumbersMinus;
    private Button buttonPrimesListRun;
    private Button buttonPrimesListNumbersPlus;
    private TextView textViewPrimesListLabelResult;
    private TextView textViewPrimesListLabelElasticResult;
    private TextView textViewPrimesListExpandResult;
    private TextView textViewPrimesListClearResult;
    private LinearLayout linearLayoutPrimesListStaticColumnHeader;
    private ListView listViewPrimesListResult;
    boolean isCompactInputView = false;


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
            // Example default values
            int numbersDefault = 1000;
            int columnsDefault = 6;

            inflater = layoutInflater.inflate(R.layout.fragment_primes_list, container, false);

            TextView textViewPrimesListBackToAlgorithms = inflater.findViewById(R.id.TextViewPrimesListBackToAlgorithms);
            this.textViewPrimesListTitle = inflater.findViewById(R.id.TextViewPrimesListTitle);
            TextView textViewPrimesListDocumentationFile = inflater.findViewById(R.id.TextViewPrimesListDocumentationFile);
            this.textViewPrimesListLabelColumns = inflater.findViewById(R.id.TextViewPrimesListLabelColumns);
            this.textViewPrimesListLabelNumbers = inflater.findViewById(R.id.TextViewPrimesListLabelNumbers);
            this.buttonPrimesListColumnsMinus = inflater.findViewById(R.id.ButtonPrimesListColumnsMinus);
            this.buttonPrimesListColumns = inflater.findViewById(R.id.ButtonPrimesListColumns);
            this.buttonPrimesListColumnsPlus = inflater.findViewById(R.id.ButtonPrimesListColumnsPlus);
            this.buttonPrimesListNumbersMinus = inflater.findViewById(R.id.ButtonPrimesListNumbersMinus);
            this.buttonPrimesListRun = inflater.findViewById(R.id.ButtonPrimesListRun);
            this.buttonPrimesListNumbersPlus = inflater.findViewById(R.id.ButtonPrimesListNumbersPlus);
            this.textViewPrimesListLabelResult = inflater.findViewById(R.id.TextViewPrimesListLabelResult);
            this.textViewPrimesListLabelElasticResult = inflater.findViewById(R.id.TextViewPrimesListLabelElasticResult);
            this.textViewPrimesListExpandResult = inflater.findViewById(R.id.TextViewPrimesListExpandResult);
            this.textViewPrimesListClearResult = inflater.findViewById(R.id.TextViewPrimesListClearResult);
            this.linearLayoutPrimesListStaticColumnHeader = inflater.findViewById(R.id.LinearLayoutPrimesListStaticColumnHeader);
            this.listViewPrimesListResult = inflater.findViewById(R.id.ListViewPrimesListResult);

            // Events
            textViewPrimesListBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });
            textViewPrimesListDocumentationFile.setOnClickListener(view -> DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.PRIMES_LIST_PDF).show(getParentFragmentManager(), "PRIMES_LIST_PDF"));
            buttonPrimesListColumnsMinus.setOnClickListener(view -> {
                String textValue = buttonPrimesListColumns.getText().toString();
                if (textValue.isEmpty()) {
                    buttonPrimesListColumns.setText("1");
                } else {
                    int value = Integer.parseInt(textValue);
                    if (value <= 1) {
                        buttonPrimesListColumns.setText("1");
                    } else {
                        value -= 1;
                        buttonPrimesListColumns.setText(String.valueOf(value));
                    }
                }
            });
            this.buttonPrimesListColumns.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
                @Override
                public void afterTextChanged(Editable editable) {
                    resetResult();
                    resetAllAndSelectTheLastClipboardButtonClicked(null);
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            buttonPrimesListColumnsPlus.setOnClickListener(view -> {
                String textValue = buttonPrimesListColumns.getText().toString();
                if (textValue.isEmpty()) {
                    buttonPrimesListColumns.setText("1");
                } else {
                    int value = Integer.parseInt(textValue);
                    if (value < Integer.MAX_VALUE) {
                        value += 1;
                        buttonPrimesListColumns.setText(String.valueOf(value));
                    } else {
                        buttonPrimesListColumns.setText(String.valueOf(Integer.MAX_VALUE));
                    }
                }
            });
            buttonPrimesListNumbersMinus.setOnClickListener(view -> {
                String textValue = buttonPrimesListRun.getText().toString();
                if (textValue.isEmpty()) {
                    buttonPrimesListRun.setText(String.valueOf(numbersDefault));
                } else {
                    int value = Integer.parseInt(textValue);
                    if (value <= numbersDefault) {
                        buttonPrimesListRun.setText(String.valueOf(numbersDefault));
                    } else {
                        value -= numbersDefault;
                        buttonPrimesListRun.setText(String.valueOf(value));
                    }
                }
            });
            this.buttonPrimesListRun.setOnClickListener(view -> onButtonRun(container, buttonPrimesListRun));
            this.buttonPrimesListRun.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    resetResult();
                    resetAllAndSelectTheLastClipboardButtonClicked(null);
                    resetAllAndSelectTheLastButtonClicked(null);
                }
            });
            buttonPrimesListNumbersPlus.setOnClickListener(view -> {
                String textValue = buttonPrimesListRun.getText().toString();
                if (textValue.isEmpty()) {
                    buttonPrimesListRun.setText(String.valueOf(numbersDefault));
                } else {
                    int value = Integer.parseInt(textValue);
                    if (value < Integer.MAX_VALUE - numbersDefault) {
                        value += numbersDefault;
                        buttonPrimesListRun.setText(String.valueOf(value));
                    } else {
                        buttonPrimesListRun.setText(String.valueOf(Integer.MAX_VALUE));
                    }
                }
            });
            textViewPrimesListExpandResult.setOnClickListener(v -> {
                PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewPrimesListTitle.getText().toString(), linearLayoutPrimesListStaticColumnHeader, listViewPrimesListResult);
                popupResult.show();
                resetAllAndSelectTheLastClipboardButtonClicked(textViewPrimesListExpandResult);
            });
            textViewPrimesListClearResult.setOnClickListener(view -> {
                this.resetResult();
                resetAllAndSelectTheLastClipboardButtonClicked(textViewPrimesListClearResult);
                resetAllAndSelectTheLastButtonClicked(null);
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
        //try {
        //    menuInflater.inflate(R.menu.menu_to_inflate_here, menu);
        //} catch (Exception ex) {
        //    Log.e(TAG, "" + ex);
        //}
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle menu item clicks here based on their ID.

        // If the menu item was not handled by this fragment, return false
        // so that the hosting Activity or other MenuProviders can handle it.
        return false;
    }
    //endregion MENU


    @Override
    public void onResume() {
        super.onResume();
        this.refreshBiggerControls();
    }


    //region Callback
    public void callbackResult(AlgorithmName algorithmName, Object result, ProgressStatus progressStatus) {
        // This is to prevent the error: Non-fatal Exception: java.lang.IllegalStateException: Fragment FragmentPrimesList{94d7331} (36e8cdd6-9d00-4c2a-bd07-ab5550e2c88b) not attached to a context.
        // java.lang.IllegalStateException: Fragment not attached to Activity -> https://stackoverflow.com/questions/28672883/java-lang-illegalstateexception-fragment-not-attached-to-activity
        Activity activity = getActivity();
        if (activity == null || !this.isAdded()) {
            return;
        }
        if (algorithmName == AlgorithmName.PRIMES_LIST) {
            if (progressStatus == ProgressStatus.CANCELED) {
                cancelPrimesListShowResult(listViewPrimesListResult);
            } else {
                @SuppressWarnings("unchecked")
                List<List<RowItem>> plResultList = (List<List<RowItem>>)result;
                showResultPrimesList(plResultList);
            }
        }
    }
    private void cancelPrimesListShowResult(ListView listView) {
        ArrayList<String> listItems=new ArrayList<>();
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(requireContext(), R.layout.row_item_canceled, R.id.RowItemCanceled, listItems);
        listView.setAdapter(adapter);
        listItems.add(requireContext().getResources().getString(R.string.canceled));
        adapter.notifyDataSetChanged();
    }
    private void showResultPrimesList(List<List<RowItem>> rows) {
        try {
            if(rows == null || rows.isEmpty()) {
                return;
            }
            // Get the first row (the headers row)
            List<RowItem> firstRow = rows.get(0);
            // Get the last lvItem value of the first row
            String firstRowLastValue = firstRow.get(firstRow.size()-1).getValue();
            // Get the last row
            List<RowItem> lastRow = rows.get(rows.size()-1);
            // Get the last lvItem value of the last row
            String lastRowLastValue = lastRow.get(lastRow.size()-1).getValue();
            // Pre-calculate the TextViewLVItemListItem width
            int maxOfLVItemsLength = lastRowLastValue.length();
            int maxOfHeadersLength = firstRowLastValue.length();
            int maxTextLength = Math.max(maxOfHeadersLength, maxOfLVItemsLength);
            maxTextLength = maxTextLength + 1; // Add 1 for easy reading.
            // Construct the maxText. if maxTextLength = 6 the maxText = "000000"
            StringBuilder maxText = new StringBuilder(maxTextLength);
            for(int i = 0; i < maxTextLength; i++) {
                maxText.append("0");
            }

            // Get the value from shared preferences.
            boolean biggerResultDisplay = UserSettings.getBiggerResultDisplay(requireContext());

            // Start the pre-calculate
            // TextView textViewTemp = new TextView(requireContext());
            LayoutInflater layoutInflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TextView textViewTemp;
            int rowItemResource = biggerResultDisplay ? R.layout.row_item_big : R.layout.row_item_small;
            textViewTemp = (TextView) layoutInflater.inflate(rowItemResource, null);
            textViewTemp.setText(maxText.toString());
            textViewTemp.measure(0, 0); //must call measure!
            int rowItemWidth = textViewTemp.getMeasuredWidth();
            int rowItemHeight = LinearLayout.LayoutParams.WRAP_CONTENT;

            // Set the listview row space.
            if(biggerResultDisplay) {
                listViewPrimesListResult.setDividerHeight((int) UIHelper.convertDpToPixel(4F, requireContext()));
            } else {
                listViewPrimesListResult.setDividerHeight((int) UIHelper.convertDpToPixel(1F, requireContext()));
            }

            // Create and set the adapter.
            GridAdapter adapter = new GridAdapter(requireContext(), linearLayoutPrimesListStaticColumnHeader, rows, rowItemWidth, null, rowItemHeight, null, biggerResultDisplay);
            listViewPrimesListResult.setAdapter(adapter);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Callback


    //region Display
    private void refreshBiggerControls() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Clipboard input buttons

            // Clipboard output buttons
            ControlDisplay.setClipboardButtonFontSize(textViewPrimesListExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPrimesListClearResult, biggerControls);
            // Labels.
            ControlDisplay.setInputLabelFontSize(this.textViewPrimesListLabelColumns, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewPrimesListLabelNumbers, biggerControls);
            // Buttons.
            ControlDisplay.setButtonFontSize(this.buttonPrimesListColumnsMinus, biggerControls);
            ControlDisplay.setButtonFontSize(this.buttonPrimesListColumns, biggerControls);
            ControlDisplay.setButtonFontSize(this.buttonPrimesListColumnsPlus, biggerControls);
            ControlDisplay.setButtonFontSize(this.buttonPrimesListNumbersMinus, biggerControls);
            ControlDisplay.setButtonFontSize(this.buttonPrimesListRun, biggerControls);
            ControlDisplay.setButtonFontSize(this.buttonPrimesListNumbersPlus, biggerControls);
            // Labels.
            ControlDisplay.setInputLabelFontSize(this.textViewPrimesListLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewPrimesListLabelElasticResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Display


    //region BUTTON ACTIONS
    private void onButtonRun(ViewGroup container, Button button) {
        try {
            // Check
            String numbersString = buttonPrimesListRun.getText().toString();
            BigInteger numbers;
            try {
                numbers = new BigInteger(numbersString);
            } catch (Exception ex) {
                UIHelper.showCustomToastLight(requireContext(), numbersString + " not a number");
                return;
            }

            // Check
            String columnsString = buttonPrimesListColumns.getText().toString();
            BigInteger columns;
            try {
                if(columnsString.isEmpty()) {
                    UIHelper.showCustomToastLight(requireContext(), "nr of columns must not be empty");
                    return;
                } else {
                    columns = new BigInteger(columnsString);
                }
            } catch (Exception ex) {
                UIHelper.showCustomToastLight(requireContext(), columnsString + " not a number");
                return;
            }
            if (columns.compareTo(BigInteger.ZERO) <= 0) {
                UIHelper.showCustomToastLight(requireContext(), "nr of columns must be greater than 0");
                return;
            }

            // Reset result
            resetResult();
            // Before action performing.
            beforeActionPerforming(button);

            // Perform generate numbers
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.PRIMES_LIST, this);
            algorithmParameters.setInput1(numbers);
            algorithmParameters.setInput2(columns);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //end region BUTTON ACTIONS


    //region RESULT
    private void beforeActionPerforming(Button button) {
        // Hide the keyboard.
        UIHelper.hideSoftKeyBoard(requireActivity());
        // Clear the focus.
        buttonPrimesListColumns.clearFocus();
        // Select the last button clicked.
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastClipboardButtonClicked(TextView textView) {
        // Reset the last clipboard clicked.
        textViewPrimesListExpandResult.setSelected(false);
        textViewPrimesListClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void resetAllAndSelectTheLastButtonClicked(Button button) {
        // Reset the last button clicked.
        buttonPrimesListColumnsMinus.setSelected(false);
        buttonPrimesListColumnsPlus.setSelected(false);
        buttonPrimesListNumbersMinus.setSelected(false);
        buttonPrimesListRun.setSelected(false);
        buttonPrimesListNumbersPlus.setSelected(false);
        // Select he last button clicked.
        if (button != null) {
            button.setSelected(true);
            textViewPrimesListExpandResult.setVisibility(View.VISIBLE);
        } else {
            textViewPrimesListExpandResult.setVisibility(View.GONE);
        }
    }
    private void resetResult() {
        // Reset the last clipboard clicked.
        resetAllAndSelectTheLastClipboardButtonClicked(null);
        // Reset the last button clicked.
        resetAllAndSelectTheLastButtonClicked(null);
        //
        linearLayoutPrimesListStaticColumnHeader.removeAllViews();
        listViewPrimesListResult.setAdapter(null);
    }
    //endregion RESULT

}