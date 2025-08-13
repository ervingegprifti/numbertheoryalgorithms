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
    private TextView textViewTitle;
    private TextView textViewLabelColumns;
    private TextView textViewLabelNumbers;
    private Button buttonColumnsMinus;
    private Button buttonColumns;
    private Button buttonColumnsPlus;
    private Button buttonNumbersMinus;
    private Button buttonRun;
    private Button buttonNumbersPlus;
    private TextView textViewLabelResult;
    private TextView textViewLabelElasticResult;
    private TextView textViewExpandResult;
    private TextView textViewClearResult;
    private LinearLayout linearLayoutStaticColumnHeader;
    private ListView listViewResult;


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

            TextView textViewBackToAlgorithms = inflater.findViewById(R.id.TextViewBackToAlgorithms);
            this.textViewTitle = inflater.findViewById(R.id.TextViewTitle);
            this.textViewLabelColumns = inflater.findViewById(R.id.TextViewLabelColumns);
            this.textViewLabelNumbers = inflater.findViewById(R.id.TextViewLabelNumbers);
            this.buttonColumnsMinus = inflater.findViewById(R.id.ButtonColumnsMinus);
            this.buttonColumns = inflater.findViewById(R.id.ButtonColumns);
            this.buttonColumnsPlus = inflater.findViewById(R.id.ButtonColumnsPlus);
            this.buttonNumbersMinus = inflater.findViewById(R.id.ButtonNumbersMinus);
            this.buttonRun = inflater.findViewById(R.id.ButtonRun);
            this.buttonNumbersPlus = inflater.findViewById(R.id.ButtonNumbersPlus);
            this.textViewLabelResult = inflater.findViewById(R.id.TextViewLabelResult);
            this.textViewLabelElasticResult = inflater.findViewById(R.id.TextViewLabelElasticResult);
            this.textViewExpandResult = inflater.findViewById(R.id.TextViewExpandResult);
            this.textViewClearResult = inflater.findViewById(R.id.TextViewClearResult);
            this.linearLayoutStaticColumnHeader = inflater.findViewById(R.id.LinearLayoutStaticColumnHeader);
            this.listViewResult = inflater.findViewById(R.id.ListViewResult);

            // Events
            textViewBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });
            buttonColumnsMinus.setOnClickListener(view -> {
                String textValue = buttonColumns.getText().toString();
                if (textValue.isEmpty()) {
                    buttonColumns.setText("1");
                } else {
                    int value = Integer.parseInt(textValue);
                    if (value <= 1) {
                        buttonColumns.setText("1");
                    } else {
                        value -= 1;
                        buttonColumns.setText(String.valueOf(value));
                    }
                }
            });
            this.buttonColumns.addTextChangedListener(new TextWatcher() {
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
            buttonColumnsPlus.setOnClickListener(view -> {
                String textValue = buttonColumns.getText().toString();
                if (textValue.isEmpty()) {
                    buttonColumns.setText("1");
                } else {
                    int value = Integer.parseInt(textValue);
                    if (value < Integer.MAX_VALUE) {
                        value += 1;
                        buttonColumns.setText(String.valueOf(value));
                    } else {
                        buttonColumns.setText(String.valueOf(Integer.MAX_VALUE));
                    }
                }
            });
            buttonNumbersMinus.setOnClickListener(view -> {
                String textValue = buttonRun.getText().toString();
                if (textValue.isEmpty()) {
                    buttonRun.setText(String.valueOf(numbersDefault));
                } else {
                    int value = Integer.parseInt(textValue);
                    if (value <= numbersDefault) {
                        buttonRun.setText(String.valueOf(numbersDefault));
                    } else {
                        value -= numbersDefault;
                        buttonRun.setText(String.valueOf(value));
                    }
                }
            });
            this.buttonRun.setOnClickListener(view -> onButtonRun(container, buttonRun));
            this.buttonRun.addTextChangedListener(new TextWatcher() {
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
            buttonNumbersPlus.setOnClickListener(view -> {
                String textValue = buttonRun.getText().toString();
                if (textValue.isEmpty()) {
                    buttonRun.setText(String.valueOf(numbersDefault));
                } else {
                    int value = Integer.parseInt(textValue);
                    if (value < Integer.MAX_VALUE - numbersDefault) {
                        value += numbersDefault;
                        buttonRun.setText(String.valueOf(value));
                    } else {
                        buttonRun.setText(String.valueOf(Integer.MAX_VALUE));
                    }
                }
            });
            textViewExpandResult.setOnClickListener(v -> {
                PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), linearLayoutStaticColumnHeader, listViewResult);
                popupResult.show();
                resetAllAndSelectTheLastClipboardButtonClicked(textViewExpandResult);
            });
            textViewClearResult.setOnClickListener(view -> {
                this.resetResult();
                resetAllAndSelectTheLastClipboardButtonClicked(textViewClearResult);
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
        try {
            menuInflater.inflate(R.menu.menu_fragment_primes_list, menu);
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
            if (id == R.id.menu_documentation) {
                DialogFragmentPdfViewer.newInstance(DialogFragmentPdfViewer.PRIMES_LIST_PDF).show(getParentFragmentManager(), "PRIMES_LIST_PDF");
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
    }


    //region Callback
    public void callbackResult(AlgorithmName algorithmName, Object result, ProgressStatus progressStatus) {
        Activity activity = getActivity();
        if (activity == null || !this.isAdded()) {
            return;
        }
        if (algorithmName == AlgorithmName.PRIMES_LIST) {
            if (progressStatus == ProgressStatus.CANCELED) {
                cancelShowResult(listViewResult);
            } else {
                @SuppressWarnings("unchecked")
                List<List<RowItem>> plResultList = (List<List<RowItem>>)result;
                showResult(plResultList);
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
    private void showResult(List<List<RowItem>> rows) {
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
                listViewResult.setDividerHeight((int) UIHelper.convertDpToPixel(4F, requireContext()));
            } else {
                listViewResult.setDividerHeight((int) UIHelper.convertDpToPixel(1F, requireContext()));
            }

            // Create and set the adapter.
            GridAdapter adapter = new GridAdapter(requireContext(), linearLayoutStaticColumnHeader, rows, rowItemWidth, null, rowItemHeight, null, biggerResultDisplay);
            listViewResult.setAdapter(adapter);
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
            ControlDisplay.setClipboardButtonFontSize(textViewExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult, biggerControls);
            // Labels.
            ControlDisplay.setInputLabelFontSize(this.textViewLabelColumns, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewLabelNumbers, biggerControls);
            // Buttons.
            ControlDisplay.setButtonFontSize(this.buttonColumnsMinus, biggerControls);
            ControlDisplay.setButtonFontSize(this.buttonColumns, biggerControls);
            ControlDisplay.setButtonFontSize(this.buttonColumnsPlus, biggerControls);
            ControlDisplay.setButtonFontSize(this.buttonNumbersMinus, biggerControls);
            ControlDisplay.setButtonFontSize(this.buttonRun, biggerControls);
            ControlDisplay.setButtonFontSize(this.buttonNumbersPlus, biggerControls);
            // Labels.
            ControlDisplay.setInputLabelFontSize(this.textViewLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewLabelElasticResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Display


    //region BUTTON ACTIONS
    private void onButtonRun(ViewGroup container, Button button) {
        try {
            // Check
            String numbersString = buttonRun.getText().toString();
            BigInteger numbers;
            try {
                numbers = new BigInteger(numbersString);
            } catch (Exception ex) {
                UIHelper.showCustomToastLight(requireContext(), numbersString + " not a number");
                return;
            }

            // Check
            String columnsString = buttonColumns.getText().toString();
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
        UIHelper.hideSoftKeyBoard(requireActivity());
        buttonColumns.clearFocus();
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastClipboardButtonClicked(TextView textView) {
        textViewExpandResult.setSelected(false);
        textViewClearResult.setSelected(false);
        // Select he last clipboard clicked.
        if (textView != null) {
            textView.setSelected(true);
        }
    }
    private void resetAllAndSelectTheLastButtonClicked(Button button) {
        buttonColumnsMinus.setSelected(false);
        buttonColumnsPlus.setSelected(false);
        buttonNumbersMinus.setSelected(false);
        buttonRun.setSelected(false);
        buttonNumbersPlus.setSelected(false);
        // Select he last button clicked.
        if (button != null) {
            button.setSelected(true);
            textViewExpandResult.setVisibility(View.VISIBLE);
        } else {
            textViewExpandResult.setVisibility(View.GONE);
        }
    }
    private void resetResult() {
        resetAllAndSelectTheLastClipboardButtonClicked(null);
        resetAllAndSelectTheLastButtonClicked(null);
        linearLayoutStaticColumnHeader.removeAllViews();
        listViewResult.setAdapter(null);
    }
    //endregion RESULT
}