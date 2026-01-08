package com.gegprifti.android.numbertheoryalgorithms.fragments.primeslist;


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
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gegprifti.android.numbertheoryalgorithms.fragments.DialogFragmentPdfViewer;
import com.gegprifti.android.numbertheoryalgorithms.fragments.FragmentAlgorithms;
import com.gegprifti.android.numbertheoryalgorithms.fragments.TabFragmentAlgorithms;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;
import com.gegprifti.android.numbertheoryalgorithms.grid.CellUI;
import com.gegprifti.android.numbertheoryalgorithms.grid.Grid;
import com.gegprifti.android.numbertheoryalgorithms.grid.GridAdapter;
import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.settings.ControlDisplay;
import com.gegprifti.android.numbertheoryalgorithms.popups.PopupResult;
import com.gegprifti.android.numbertheoryalgorithms.grid.Cell;
import com.gegprifti.android.numbertheoryalgorithms.settings.UserSettings;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.FragmentBase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FragmentPrimesList extends FragmentBase {
    private final static String TAG = FragmentPrimesList.class.getSimpleName();
    private final static int NUMBERS = 5000;
    private final static int MIN_COLUMNS = 1;
    private final static int COLUMNS_DEFAULT_VALUE = 6;
    private final static int MAX_COLUMNS = NUMBERS;
    // Navigation controls
    private TextView textViewTitle;
    // Compact input view controls
    private TextView textViewLabelCompactK;
    private EditText editTextCompactK;
    private TextView textViewMinusCompactK;
    private TextView textViewPlusCompactK;
    // Result controls
    private TextView textViewLabelResult;
    private TextView textViewLabelElasticResult;
    private TextView textViewExpandResult;
    private TextView textViewClearResult;
    // Result
    private LinearLayout resultLinearLayoutGridContainer;
    private LinearLayout resultLinearLayoutGrid;
    private LinearLayout resultLinearLayoutGridCorner;
    private LinearLayout resultLinearLayoutGridRowHeaders;
    private ListView resultListViewGridRowHeaders;
    private LinearLayout resultLinearLayoutGridColumnHeaders;
    private ListView resultListViewGridRows;

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
            // Result
            this.resultLinearLayoutGridContainer = inflater.findViewById(R.id.ResultLinearLayoutGridContainer);
            this.resultLinearLayoutGrid = inflater.findViewById(R.id.ResultLinearLayoutGrid);
            this.resultLinearLayoutGridCorner = inflater.findViewById(R.id.ResultLinearLayoutGridCorner);
            this.resultLinearLayoutGridRowHeaders = inflater.findViewById(R.id.ResultLinearLayoutGridRowHeaders);
            this.resultListViewGridRowHeaders = inflater.findViewById(R.id.ResultListViewGridRowHeaders);
            this.resultLinearLayoutGridColumnHeaders = inflater.findViewById(R.id.ResultLinearLayoutGridColumnHeaders);
            this.resultListViewGridRows = inflater.findViewById(R.id.ResultListViewGridRows);

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
            initDoubleTapDetector(textViewLabelCompactK);
            editTextCompactK.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }
                @Override
                public void afterTextChanged(Editable s) { }
            });

            // Compact input k button events
            textViewMinusCompactK.setOnClickListener(v -> {
                decreaseByOne(editTextCompactK, BigInteger.valueOf(MIN_COLUMNS));
                resetAllAndSelectTheLastButtonClicked(textViewMinusCompactK);
                run();
            });
            textViewPlusCompactK.setOnClickListener(v -> {
                increaseByOne(editTextCompactK, BigInteger.valueOf(MAX_COLUMNS));
                resetAllAndSelectTheLastButtonClicked(textViewPlusCompactK);
                run();
            });

            textViewExpandResult.setOnClickListener(v -> {
                PopupResult popupResult = new PopupResult(requireActivity(), requireContext(), textViewTitle.getText().toString(), resultLinearLayoutGrid);
                popupResult.show();
                resetAllAndSelectTheLastButtonClicked(textViewExpandResult);
            });
            textViewClearResult.setOnClickListener(view -> {
                this.resetResult();
                resetAllAndSelectTheLastButtonClicked(textViewClearResult);
            });

            resultListViewGridRows.setOnScrollListener(resultSyncScrollListener);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }

        return inflater;
    }
    //endregion CREATE


    AbsListView.OnScrollListener resultSyncScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // Not needed
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            View firstView = view.getChildAt(0);
            int topOffset = (firstView == null) ? 0 : firstView.getTop();
            resultListViewGridRowHeaders.setSelectionFromTop(firstVisibleItem, topOffset);
        }
    };

    @Override
    protected void fireOnDoubleTap(View view) {
        if (view == textViewLabelCompactK){
            // Toggle between enable disable
            editTextCompactK.setEnabled(!editTextCompactK.isEnabled());
            if(!editTextCompactK.isEnabled()){
                run();
            }
        }
    }


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
        run();
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
    //endregion Refresh UI


    //region BUTTON ACTIONS
    private void run() {
        try {
            // Check
            String columnsString = editTextCompactK.getText().toString();
            BigInteger columns;
            try {
                if(columnsString.isEmpty()) {
                    UIHelper.showCustomToastLight(requireContext(), "columns must not be empty");
                    return;
                } else {
                    columns =  new BigInteger(columnsString);
                }
            } catch (Exception ex) {
                UIHelper.showCustomToastLight(requireContext(), columnsString + " columns must be a number");
                return;
            }
            if (columns.compareTo(BigInteger.valueOf(MIN_COLUMNS)) < 0) {
                UIHelper.showCustomToastLight(requireContext(), "columns must be greater than or equal to " + MIN_COLUMNS);
                return;
            }
            if (columns.compareTo(BigInteger.valueOf(MAX_COLUMNS)) > 0) {
                UIHelper.showCustomToastLight(requireContext(), "columns must be less than or equal to " + MAX_COLUMNS);
                return;
            }

            // Reset result
            resetResult();
            // Before action performing.
            beforeActionPerforming();

            // Perform generate numbers
            PrimesListCalculator primesListCalculator = new PrimesListCalculator(columns.intValue(), NUMBERS);
            Grid grid = primesListCalculator.calculate();
            showResult(grid);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }

    private void showResult(Grid grid) {
        try {
            List<List<Cell>> corner = grid.getCorner();
            List<List<Cell>> columnHeaders = grid.getColumnHeaders();
            List<List<Cell>> rowHeaders = grid.getRowHeaders();
            List<List<Cell>> rows = grid.getRows();

            List<Cell> columnHeaderRow = columnHeaders.get(0);
            String maxText = getMaxText(rows, columnHeaderRow);

            // Get the value from shared preferences.
            boolean biggerResultDisplay = UserSettings.getBiggerResultDisplay(requireContext());

            // Start the pre-calculate
            // TextView textViewTemp = new TextView(requireContext());
            LayoutInflater layoutInflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TextView textViewTemp;
            int cellResource = biggerResultDisplay ? R.layout.cell_big : R.layout.cell_small;
            textViewTemp = (TextView) layoutInflater.inflate(cellResource, null);
            textViewTemp.setText(maxText);
            textViewTemp.measure(0, 0); //must call measure!
            int cellWidthDefault = textViewTemp.getMeasuredWidth();
            int cellHeightDefault = LinearLayout.LayoutParams.WRAP_CONTENT;

            // Populate cellWidths and cellHeights
            List<Integer> cellWidths = new ArrayList<>(Collections.nCopies(rows.get(0).size(), cellWidthDefault));
            List<Integer> cellHeights = new ArrayList<>(Collections.nCopies(rows.size(), cellHeightDefault));

            // Set the listview row space.
            float dividerDp = biggerResultDisplay ? 4f : 1f;
            int dividerPx = (int) UIHelper.convertDpToPixel(dividerDp, requireContext());
            resultListViewGridRows.setDividerHeight(dividerPx);
            resultListViewGridRowHeaders.setDividerHeight(dividerPx);

            // Set column headers.
            CellUI cellUI = new CellUI(requireContext(), cellWidths, cellHeights, biggerResultDisplay);
            Grid.setColumnHeaders(cellUI, corner, resultLinearLayoutGridCorner);
            Grid.setColumnHeaders(cellUI, columnHeaders, resultLinearLayoutGridColumnHeaders);

            // Manually set the row headers width as per the cellWidthDefault.
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) resultLinearLayoutGridRowHeaders.getLayoutParams();
            params.width = cellWidthDefault + dividerPx;
            resultLinearLayoutGridRowHeaders.setLayoutParams(params);

            // Create and set the adapter for grid rows.
            GridAdapter adapter = new GridAdapter(requireContext(), cellWidths, cellHeights, rows, biggerResultDisplay);
            setListViewAdapter(resultListViewGridRows, adapter);

            // Create and set the adapter for grid row headers.
            GridAdapter gridAdapterRowHeaders = new GridAdapter(requireContext(), cellWidths, cellHeights, rowHeaders, biggerResultDisplay);
            setListViewAdapter(resultListViewGridRowHeaders, gridAdapterRowHeaders);
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
    }


    @NonNull
    private String getMaxText(List<List<Cell>> rows, List<Cell> columnHeaderRow) {
        // Get the last lvItem value of the first row
        int maxTextLength = getMaxTextLength(rows, columnHeaderRow);
        maxTextLength = maxTextLength + 1; // Add 1 for easy reading.
        // Construct the maxText. if maxTextLength = 6 the maxText = "000000"
        StringBuilder maxText = new StringBuilder(maxTextLength);
        for(int i = 0; i < maxTextLength; i++) {
            maxText.append("0");
        }
        return maxText.toString();
    }


    private static int getMaxTextLength(List<List<Cell>> rows, List<Cell> columnHeaderRow) {
        String firstRowLastValue = columnHeaderRow.get(columnHeaderRow.size()-1).getValue();
        // Get the last row
        List<Cell> lastRow = rows.get(rows.size()-1);
        // Get the last lvItem value of the last row
        String lastRowLastValue = lastRow.get(lastRow.size()-1).getValue();
        // Pre-calculate the TextViewLVItemListItem width
        int maxOfLVItemsLength = lastRowLastValue.length();
        int maxOfHeadersLength = firstRowLastValue.length();
        return Math.max(maxOfHeadersLength, maxOfLVItemsLength);
    }


    private void setListViewAdapter(ListView listView, ListAdapter listAdapter) {
        listView.setAdapter(listAdapter);
        if (listAdapter == null) {
            textViewExpandResult.setVisibility(View.GONE);
        } else {
            textViewExpandResult.setVisibility(View.VISIBLE);
        }
    }
    //endregion BUTTON ACTIONS


    //region RESULT
    private void beforeActionPerforming() {
        UIHelper.hideSoftKeyBoard(requireActivity());
        editTextCompactK.clearFocus();
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
        resultLinearLayoutGridColumnHeaders.removeAllViews();
        setListViewAdapter(resultListViewGridRows, null);
    }
    //endregion RESULT
}