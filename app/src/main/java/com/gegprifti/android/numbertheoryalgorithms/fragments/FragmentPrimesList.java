package com.gegprifti.android.numbertheoryalgorithms.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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


public class FragmentPrimesList extends FragmentBase implements Callback {
    private final static String TAG = FragmentPrimesList.class.getSimpleName();
    public final static int COLUMNS_DEFAULT_VALUE = 6;
    // Navigation controls
    private TextView textViewTitle;
    // Compact input view controls
    TextView textViewLabelCompactK;
    EditText editTextCompactK;
    TextView textViewMinusCompactK;
    TextView textViewPlusCompactK;
    // Result controls
    private TextView textViewLabelResult;
    private TextView textViewLabelElasticResult;
    private TextView textViewExpandResult;
    private TextView textViewClearResult;
    private LinearLayout linearLayoutStaticColumnHeader;
    private ListView listViewResult;
    private ListAdapter listAdapter;

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
            inflater = layoutInflater.inflate(R.layout.fragment_primes_list, container, false);
            // Navigation controls
            TextView textViewBackToAlgorithms = inflater.findViewById(R.id.TextViewBackToAlgorithms);
            this.textViewTitle = inflater.findViewById(R.id.TextViewTitle);
            // Compact input view
            textViewLabelCompactK = inflater.findViewById(R.id.TextViewLabelCompactK);
            editTextCompactK = inflater.findViewById(R.id.EditTextCompactK);
            textViewMinusCompactK = inflater.findViewById(R.id.TextViewMinusCompactK);
            textViewPlusCompactK = inflater.findViewById(R.id.TextViewPlusCompactK);
            // Result controls
            this.textViewLabelResult = inflater.findViewById(R.id.TextViewLabelResult);
            this.textViewLabelElasticResult = inflater.findViewById(R.id.TextViewLabelElasticResult);
            this.textViewExpandResult = inflater.findViewById(R.id.TextViewExpandResult);
            this.textViewClearResult = inflater.findViewById(R.id.TextViewClearResult);
            this.linearLayoutStaticColumnHeader = inflater.findViewById(R.id.LinearLayoutStaticColumnHeader);
            this.listViewResult = inflater.findViewById(R.id.ListViewResult);

            // Constrain compact input
            editTextCompactK.setFilters(new InputFilter[]{UIHelper.inputFilterIntegerOnly});

            // Initial values
            if (editTextCompactK.getText() == null || editTextCompactK.getText().length() == 0) {
                editTextCompactK.setText(String.valueOf(COLUMNS_DEFAULT_VALUE));
            }

            // Navigation vents
            textViewBackToAlgorithms.setOnClickListener(view -> {
                if(tabFragmentAlgorithms != null) {
                    // Go back to the algorithms main menu
                    FragmentAlgorithms fragmentAlgorithms = (FragmentAlgorithms) tabFragmentAlgorithms.getSectionsPagerAdapter().getItemByName("FragmentAlgorithms");
                    tabFragmentAlgorithms.setFragment(fragmentAlgorithms);
                }
            });

            // Compact input events
            editTextCompactK.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) {
                    // TODO +++
                }
            });

            // Compact input k button events
            textViewMinusCompactK.setOnClickListener(v -> {
                decreaseByOne(editTextCompactK);
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactK);
            });
            textViewPlusCompactK.setOnClickListener(v -> {
                increaseByOne(editTextCompactK);
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactK);
            });

            textViewExpandResult.setOnClickListener(v -> {
                PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), linearLayoutStaticColumnHeader, listViewResult);
                popupResult.show();
                resetAllAndSelectTheLastButtonClicked(textViewExpandResult);
            });
            textViewClearResult.setOnClickListener(view -> {
                this.resetResult();
                resetAllAndSelectTheLastButtonClicked(textViewClearResult);
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
        refreshControlsDisplay();
        refreshResultDisplay();

        // TODO +++ Remove later
        run(null, null);
    }


    //region Refresh UI
    private void refreshControlsDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerControls(requireContext());
            // Compact input controls
            ControlDisplay.setInputLabelFontSize(textViewLabelCompactK, biggerControls);
            ControlDisplay.setInputFontSize(editTextCompactK, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewMinusCompactK, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewPlusCompactK, biggerControls);
            // Output controls
            ControlDisplay.setInputLabelFontSize(this.textViewLabelResult, biggerControls);
            ControlDisplay.setInputLabelFontSize(this.textViewLabelElasticResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewExpandResult, biggerControls);
            ControlDisplay.setClipboardButtonFontSize(textViewClearResult, biggerControls);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void refreshResultDisplay() {
        try {
            boolean biggerControls = UserSettings.getBiggerResultDisplay(requireContext());
            // TODO +++
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion Refresh UI


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
        setListViewAdapter(listView, adapter);
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
            setListViewAdapter(listViewResult, adapter);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    private void setListViewAdapter(ListView listView, ListAdapter listAdapter) {
        listView.setAdapter(listAdapter);
        if (listAdapter == null) {
            textViewExpandResult.setVisibility(View.GONE);
        } else {
            textViewExpandResult.setVisibility(View.VISIBLE);
        }
        this.listAdapter = listAdapter;
    }
    //endregion Callback


    //region BUTTON ACTIONS
    private void run(ViewGroup container, Button button) {
        try {
            // Check
            String columnsString = editTextCompactK.getText().toString();
            BigInteger columns;
            try {
                if(columnsString.isEmpty()) {
                    UIHelper.showCustomToastLight(requireContext(), "columns must not be empty");
                    return;
                } else {
                    columns = new BigInteger(columnsString);
                }
            } catch (Exception ex) {
                UIHelper.showCustomToastLight(requireContext(), columnsString + " columns must be a number");
                return;
            }
            if (columns.compareTo(BigInteger.ZERO) <= 0) {
                UIHelper.showCustomToastLight(requireContext(), "columns must be greater than 0");
                return;
            }

            // TODO +++ increase decrease as we scroll up or down. Hardcoded for the moment.
            BigInteger numbers = new BigInteger("24");

            // Reset result
            resetResult();
            // Before action performing.
            beforeActionPerforming(button);

            // Perform generate numbers
            AlgorithmParameters algorithmParameters = new AlgorithmParameters(AlgorithmName.PRIMES_LIST, this);
            algorithmParameters.setInput1(columns);
            algorithmParameters.setInput2(numbers);
            progressManager.startWork(container, algorithmParameters);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }
    //endregion BUTTON ACTIONS


    //region RESULT
    private void beforeActionPerforming(Button button) {
        UIHelper.hideSoftKeyBoard(requireActivity());
        editTextCompactK.clearFocus();
        resetAllAndSelectTheLastButtonClicked(button);
    }
    private void resetAllAndSelectTheLastButtonClicked() {
        resetAllAndSelectTheLastButtonClicked(null);
    }
    private void resetAllAndSelectTheLastButtonClicked(TextView textView) {
        // Input compact controls
        textViewMinusCompactK.setSelected(false);
        textViewPlusCompactK.setSelected(false);
        // Output controls
        textViewExpandResult.setSelected(false);
        textViewClearResult.setSelected(false);
        // Select the last button clicked.
        if (textView != null) {
            UIHelper.vibrateOnButtonTap(requireContext());
            textView.setSelected(true);
        }
    }
    private void resetResult() {
        resetAllAndSelectTheLastButtonClicked();
        linearLayoutStaticColumnHeader.removeAllViews();
        setListViewAdapter(listViewResult, null);
    }
    //endregion RESULT
}