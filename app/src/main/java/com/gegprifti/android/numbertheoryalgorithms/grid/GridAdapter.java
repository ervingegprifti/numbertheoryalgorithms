package com.gegprifti.android.numbertheoryalgorithms.grid;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.gegprifti.android.numbertheoryalgorithms.R;


public class GridAdapter extends BaseAdapter {
    private final static String TAG = GridAdapter.class.getSimpleName();

    private final LayoutInflater layoutInflater;
    private final List<List<Cell>> rows;
    private int lastItemIndex = -1;

    private final CellUI cellUI;

    public GridAdapter(@NonNull Context context, @NonNull List<Integer> cellWidths, @NonNull List<Integer> cellHeights, @NonNull List<List<Cell>> rows, boolean biggerResultDisplay) {
        this.rows = Objects.requireNonNull(rows, "GridAdapter: rows must not be null");

        if (cellWidths.isEmpty()) {
            throw new IllegalArgumentException("CellUI: cellWidths must not be empty");
        }

        if (cellHeights.isEmpty()) {
            throw new IllegalArgumentException("CellUI: cellHeights must not be empty");
        }

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cellUI = new CellUI(context, cellWidths, cellHeights, biggerResultDisplay);

        // Get the last item index in the first row.
        List<Cell> columnHeaders = rows.get(0);
        this.lastItemIndex = columnHeaders.size() - 1;
    }

    static class RowHolder {
        private List<TextView> TextViewCells;
    }

    @Override
    public int getCount() {
        return rows.size();
    }

    @Override
    public List<Cell> getItem(int rowIndex)  {
        return rows.get(rowIndex);
    }

    @Override
    public long getItemId(int rowIndex) {
        return rowIndex;
    }

    @Override
    public View getView(int rowIndex, View convertView, ViewGroup parent) {
        try {
            // We define here.
            RowHolder rowHolder;
            if (convertView == null) {
                rowHolder = new RowHolder();
                View rowInflater = layoutInflater.inflate(R.layout.grid_row, parent, false);
                LinearLayout linearLayoutRow = (LinearLayout) rowInflater.findViewById(R.id.GridRow);
                List<TextView> textViews = new ArrayList<>();
                List<Cell> row = getItem(rowIndex);
                for(int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
                    boolean isLastItem = this.lastItemIndex == columnIndex;
                    TextView textView = cellUI.createTextView(columnIndex, rowIndex, parent, isLastItem);
                    textViews.add(textView);
                    linearLayoutRow.addView(textView);
                }
                rowHolder.TextViewCells = textViews;
                convertView = rowInflater;
                convertView.setTag(rowHolder);
            } else {
                rowHolder = (RowHolder) convertView.getTag();
            }

            // We set values here.
            List<Cell> row = getItem(rowIndex);
            for(int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
                final Cell cell = row.get(columnIndex);
                TextView textView = rowHolder.TextViewCells.get(columnIndex);
                cellUI.refreshCell(cell, textView);
            }

            return convertView;
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
        return  null;
    }

    public void refresh(){
        this.notifyDataSetChanged();
    }
}
