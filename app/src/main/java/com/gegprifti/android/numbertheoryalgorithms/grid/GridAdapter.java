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
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;


public class GridAdapter extends BaseAdapter {
    private final static String TAG = GridAdapter.class.getSimpleName();

    private final Context context;
    private final LayoutInflater layoutInflater;
    private final List<List<Cell>> rows;
    private final LinearLayout.LayoutParams layoutParamsGlobal;
    private final int cellWidthGlobal;
    private final int cellHeightGlobal;
    private final List<Integer> cellWidths;
    private final List<Integer> cellHeights;
    private final boolean biggerResultDisplay;
    private final int marginLeft;
    private final int marginTop;
    private final int marginRight;
    private final int marginBottom;
    private int lastItemIndex = -1;

    public GridAdapter(Context context, LinearLayout staticColumnHeader, List<List<Cell>> rows, int cellWidthGlobal, List<Integer> cellWidths, int cellHeightGlobal, List<Integer> cellHeights, boolean biggerResultDisplay) {
        this.context = context;
        this.rows = rows;
        this.cellWidthGlobal = cellWidthGlobal;
        this.cellWidths = cellWidths;
        this.cellHeightGlobal = cellHeightGlobal;
        this.cellHeights = cellHeights;
        this.biggerResultDisplay = biggerResultDisplay;

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.marginLeft = 0; // this.smallerResultDisplay ? (int)UIHelper.ConvertDpToPixel(1F, layoutInflater.getContext()) : (int)UIHelper.ConvertDpToPixel(4F, layoutInflater.getContext());
        this.marginTop = 0;
        this.marginRight = this.biggerResultDisplay ? (int) UIHelper.convertDpToPixel(4F, layoutInflater.getContext()) : (int) UIHelper.convertDpToPixel(1F, layoutInflater.getContext());
        this.marginBottom = 0;

        if ((cellWidths == null || cellWidths.isEmpty()) && (cellHeights == null || cellHeights.isEmpty())) {
            this.layoutParamsGlobal = new LinearLayout.LayoutParams(cellWidthGlobal, cellHeightGlobal);
            this.layoutParamsGlobal.setMargins(this.marginLeft, this.marginTop, this.marginRight, this.marginBottom);
        } else {
            this.layoutParamsGlobal = null;
        }

        // Get the last item index in the first row.
        if (this.rows != null && !this.rows.isEmpty()) {
            List<Cell> firstCells = rows.get(0);
            if (firstCells != null && !firstCells.isEmpty()) {
                this.lastItemIndex = firstCells.size() - 1;
            }
        }

        // Write the static column header.
        int margin = this.biggerResultDisplay ? (int) UIHelper.convertDpToPixel(4F, layoutInflater.getContext()) : (int) UIHelper.convertDpToPixel(1F, layoutInflater.getContext());
        if (this.rows != null && !this.rows.isEmpty()) {
            List<Cell> firstCells = this.rows.get(0);
            if (firstCells != null && !firstCells.isEmpty()) {
                staticColumnHeader.removeAllViews();
                LinearLayout.LayoutParams layoutParamsStaticColumnHeader = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParamsStaticColumnHeader.setMargins(0, 0, 0, margin);
                staticColumnHeader.setLayoutParams(layoutParamsStaticColumnHeader);
                for (int i = 0; i < firstCells.size(); i++) {
                    Cell cell = firstCells.get(i);
                    if (cell.getIsHeader()) {
                        if (cell.getIsConfig()) {
                            cell.setValue1("●");
                        }
                        boolean isLastItem = this.lastItemIndex == i;
                        TextView textView = generateTextView(i, null, isLastItem);
                        staticColumnHeader.addView(textView);
                        refreshTextView(context, cell, textView);
                    }
                }
            }
        }

        // Remove the column header.
        if (this.rows != null && !this.rows.isEmpty()) {
            this.rows.remove(0);
        }
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
                List<TextView> textViewCells = new ArrayList<>();
                List<Cell> cells = getItem(position);
                for(int i = 0; i < cells.size(); i++) {
                    boolean isLastItem = this.lastItemIndex == i;
                    TextView textView = generateTextView(i, parent, isLastItem);
                    textViewCells.add(textView);
                    linearLayoutRow.addView(textView);
                }
                rowHolder.TextViewCells = textViewCells;
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
                refreshTextView(context, cell, textView);
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

    private void refreshTextView(Context context, Cell cell, TextView textView) {
        if (context == null || cell == null || textView == null) {
            return;
        }

        // Set the value
        textView.setText(cell.getValue());

        // TODO +++ Set the on click listener.
        //if () {

        //}


        if (!cell.getIsHeader() && cell.getValue() != null && !cell.getValue().isEmpty()) {
            textView.setOnClickListener(view -> UIHelper.copyTextIntoClipboard(context, cell.getValue()));
        } else {
            textView.setOnClickListener(null);
        }

        // Set header or value styles.
        if(cell.getIsHeader()) {
            switch (cell.getHeaderStyle()) {
                case DEFAULT:
                    textView.setBackgroundResource(R.drawable.cell_header_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorGeneralBg));
                    break;
                case OUTLINED:
                    textView.setBackgroundResource(R.drawable.cell_header_outlined_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorGeneralBg));
                    break;
                case HIGHLIGHTED:
                    textView.setBackgroundResource(R.drawable.cell_header_highlighted_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorGeneralBg));
                    break;
                case BLANK:
                    textView.setBackgroundResource(R.drawable.cell_blank_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorGeneralBg));
                    break;
            }
        } else {
            switch (cell.getValueStyle()) {
                case DEFAULT:
                    textView.setBackgroundResource(R.drawable.cell_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case YELLOW:
                    textView.setBackgroundResource(R.drawable.cell_yellow_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case YELLOW_STRESSED:
                    textView.setBackgroundResource(R.drawable.cell_yellow_stressed_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case ORANGE:
                    textView.setBackgroundResource(R.drawable.cell_orange_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case ORANGE_STRESSED:
                    textView.setBackgroundResource(R.drawable.cell_orange_stressed_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case GREEN:
                    textView.setBackgroundResource(R.drawable.cell_green_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case GREEN_STRESSED:
                    textView.setBackgroundResource(R.drawable.cell_green_stressed_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case BLUE:
                    textView.setBackgroundResource(R.drawable.cell_blue_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case BLUE_STRESSED:
                    textView.setBackgroundResource(R.drawable.cell_blue_stressed_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case WHITE:
                    textView.setBackgroundResource(R.drawable.cell_white_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case WHITE_STRESSED:
                    textView.setBackgroundResource(R.drawable.cell_white_stressed_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case BLACK:
                    textView.setBackgroundResource(R.drawable.cell_black_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorGeneralBg));
                    break;
            }
        }
    }

    private TextView generateTextView(int i, ViewGroup parent, boolean isLastItem) {
        int cellResource = this.biggerResultDisplay ? R.layout.cell_big : R.layout.cell_small;
        int cellId = this.biggerResultDisplay ? R.id.CellBig : R.id.CellSmall;
        View cellInflater = layoutInflater.inflate(cellResource, parent, false);
        TextView textView = (TextView) cellInflater.findViewById(cellId);

        if (this.layoutParamsGlobal == null) {
            LinearLayout.LayoutParams layoutParams = createLayoutParams(i, isLastItem);
            textView.setLayoutParams(layoutParams);
        } else {
            textView.setLayoutParams(layoutParamsGlobal);
        }

        return textView;
    }

    private LinearLayout.LayoutParams createLayoutParams(int i, boolean isLastItem) {
        int cellWidth = (cellWidths != null && !cellWidths.isEmpty()) ? cellWidths.get(i) : this.cellWidthGlobal;
        int cellHeight = (cellHeights != null && !cellHeights.isEmpty()) ? cellHeights.get(i) : this.cellHeightGlobal;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(cellWidth, cellHeight);
        layoutParams.setMargins(this.marginLeft, this.marginTop, isLastItem ? 0 : this.marginRight, this.marginBottom);
        return layoutParams;
    }
}