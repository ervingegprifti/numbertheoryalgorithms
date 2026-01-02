package com.gegprifti.android.numbertheoryalgorithms.grid;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.gegprifti.android.numbertheoryalgorithms.R;


public class GridAdapter extends BaseAdapter {
    private final static String TAG = GridAdapter.class.getSimpleName();

    private final Context context;
    private final LayoutInflater layoutInflater;
    private final List<List<Cell>> rows;
    private int lastItemIndex = -1;

    private final CellUI cellUI;

    public GridAdapter(Context context, List<List<Cell>> rows, int cellWidthDefault, List<Integer> cellWidths, int cellHeightDefault, List<Integer> cellHeights, boolean biggerResultDisplay) {
        this.context = context;
        this.rows = rows;

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        cellUI = new CellUI(context, cellWidthDefault, cellWidths, cellHeightDefault, cellHeights, biggerResultDisplay);

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
    public List<Cell> getItem(int position)  {
        return rows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            // We define here.
            RowHolder rowHolder;
            if (convertView == null) {
                rowHolder = new RowHolder();
                View rowInflater = layoutInflater.inflate(R.layout.grid_row, parent, false);
                LinearLayout linearLayoutRow = (LinearLayout) rowInflater.findViewById(R.id.GridRow);
                List<TextView> textViews = new ArrayList<>();
                List<Cell> cells = getItem(position);
                for(int i = 0; i < cells.size(); i++) {
                    boolean isLastItem = this.lastItemIndex == i;
                    TextView textView = cellUI.createTextView(i, parent, isLastItem);
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
            List<Cell> cells = getItem(position);
            for(int i = 0; i < cells.size(); i++) {
                final Cell cell = cells.get(i);
                TextView textView = rowHolder.TextViewCells.get(i);
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
