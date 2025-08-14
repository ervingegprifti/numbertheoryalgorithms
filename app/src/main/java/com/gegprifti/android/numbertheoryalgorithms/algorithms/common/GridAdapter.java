package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


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
    private final List<List<RowItem>> gridRows;
    private final LinearLayout.LayoutParams layoutParamsGlobal;
    private final int rowItemWidthGlobal;
    private final int rowItemHeightGlobal;
    private final List<Integer> rowItemWidths;
    private final List<Integer> rowItemHeights;
    private final boolean biggerResultDisplay;
    private final int marginLeft;
    private final int marginTop;
    private final int marginRight;
    private final int marginBottom;
    private int lastItemIndex = -1;

    public GridAdapter(Context context, LinearLayout staticColumnHeader, List<List<RowItem>> gridRows, int rowItemWidthGlobal, List<Integer> rowItemWidths, int rowItemHeightGlobal, List<Integer> rowItemHeights, boolean biggerResultDisplay) {
        this.context = context;
        this.gridRows = gridRows;
        this.rowItemWidthGlobal = rowItemWidthGlobal;
        this.rowItemWidths = rowItemWidths;
        this.rowItemHeightGlobal = rowItemHeightGlobal;
        this.rowItemHeights = rowItemHeights;
        this.biggerResultDisplay = biggerResultDisplay;

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.marginLeft = 0; // this.smallerResultDisplay ? (int)UIHelper.ConvertDpToPixel(1F, layoutInflater.getContext()) : (int)UIHelper.ConvertDpToPixel(4F, layoutInflater.getContext());
        this.marginTop = 0;
        this.marginRight = this.biggerResultDisplay ? (int) UIHelper.convertDpToPixel(4F, layoutInflater.getContext()) : (int) UIHelper.convertDpToPixel(1F, layoutInflater.getContext());
        this.marginBottom = 0;

        if ((rowItemWidths == null || rowItemWidths.isEmpty()) && (rowItemHeights == null || rowItemHeights.isEmpty())) {
            this.layoutParamsGlobal = new LinearLayout.LayoutParams(rowItemWidthGlobal, rowItemHeightGlobal);
            this.layoutParamsGlobal.setMargins(this.marginLeft, this.marginTop, this.marginRight, this.marginBottom);
        } else {
            this.layoutParamsGlobal = null;
        }

        // Get the last item index in the first row.
        if (this.gridRows != null && !this.gridRows.isEmpty()) {
            List<RowItem> firstRowItems = gridRows.get(0);
            if (firstRowItems != null && !firstRowItems.isEmpty()) {
                this.lastItemIndex = firstRowItems.size() - 1;
            }
        }

        // Write the static column header.
        int margin = this.biggerResultDisplay ? (int) UIHelper.convertDpToPixel(4F, layoutInflater.getContext()) : (int) UIHelper.convertDpToPixel(1F, layoutInflater.getContext());
        if (this.gridRows != null && !this.gridRows.isEmpty()) {
            List<RowItem> firstRowItems = this.gridRows.get(0);
            if (firstRowItems != null && !firstRowItems.isEmpty()) {
                staticColumnHeader.removeAllViews();
                LinearLayout.LayoutParams layoutParamsStaticColumnHeader = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParamsStaticColumnHeader.setMargins(0, 0, 0, margin);
                staticColumnHeader.setLayoutParams(layoutParamsStaticColumnHeader);
                for (int i = 0; i < firstRowItems.size(); i++) {
                    RowItem rowItem = firstRowItems.get(i);
                    if (rowItem.getIsHeader()) {
                        if (rowItem.getIsConfig()) {
                            rowItem.setValue1("●");
                        }
                        boolean isLastItem = this.lastItemIndex == i;
                        TextView textView = generateTextView(i, null, isLastItem);
                        staticColumnHeader.addView(textView);
                        refreshTextView(context, rowItem, textView);
                    }
                }
            }
        }

        // Remove the column header.
        if (this.gridRows != null && !this.gridRows.isEmpty()) {
            this.gridRows.remove(0);
        }
    }

    static class RowHolder {
        private List<TextView> TextViewRowItems;
    }

    @Override
    public int getCount() {
        return gridRows.size();
    }

    @Override
    public List<RowItem> getItem(int position)  {
        return gridRows.get(position);
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
                List<TextView> textViewRowItems = new ArrayList<>();
                List<RowItem> rowItems = getItem(position);
                for(int i = 0; i < rowItems.size(); i++) {
                    boolean isLastItem = this.lastItemIndex == i;
                    TextView textView = generateTextView(i, parent, isLastItem);
                    textViewRowItems.add(textView);
                    linearLayoutRow.addView(textView);
                }
                rowHolder.TextViewRowItems = textViewRowItems;
                convertView = rowInflater;
                convertView.setTag(rowHolder);
            } else {
                rowHolder = (RowHolder) convertView.getTag();
            }

            // We set values here.
            List<RowItem> rowItems = getItem(position);
            for(int i = 0; i < rowItems.size(); i++) {
                final RowItem rowItem = rowItems.get(i);
                TextView textView = rowHolder.TextViewRowItems.get(i);
                refreshTextView(context, rowItem, textView);
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

    private void refreshTextView(Context context, RowItem rowItem, TextView textView) {
        if (context == null || rowItem == null || textView == null) {
            return;
        }

        // Set the value
        textView.setText(rowItem.getValue());

        // TODO +++ Set the on click listener.
        //if () {

        //}


        if (!rowItem.getIsHeader() && rowItem.getValue() != null && !rowItem.getValue().isEmpty()) {
            textView.setOnClickListener(view -> UIHelper.copyTextIntoClipboard(context, rowItem.getValue()));
        } else {
            textView.setOnClickListener(null);
        }

        // Set header or value styles.
        if(rowItem.getIsHeader()) {
            switch (rowItem.getHeaderStyle()) {
                case DEFAULT:
                    textView.setBackgroundResource(R.drawable.row_item_header_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorGeneralBg));
                    break;
                case OUTLINED:
                    textView.setBackgroundResource(R.drawable.row_item_header_outlined_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorGeneralBg));
                    break;
                case HIGHLIGHTED:
                    textView.setBackgroundResource(R.drawable.row_item_header_highlighted_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorGeneralBg));
                    break;
                case BLANK:
                    textView.setBackgroundResource(R.drawable.row_item_blank_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorGeneralBg));
                    break;
            }
        } else {
            switch (rowItem.getValueStyle()) {
                case DEFAULT:
                    textView.setBackgroundResource(R.drawable.row_item_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case YELLOW:
                    textView.setBackgroundResource(R.drawable.row_item_yellow_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case YELLOW_STRESSED:
                    textView.setBackgroundResource(R.drawable.row_item_yellow_stressed_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case ORANGE:
                    textView.setBackgroundResource(R.drawable.row_item_orange_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case ORANGE_STRESSED:
                    textView.setBackgroundResource(R.drawable.row_item_orange_stressed_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case GREEN:
                    textView.setBackgroundResource(R.drawable.row_item_green_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case GREEN_STRESSED:
                    textView.setBackgroundResource(R.drawable.row_item_green_stressed_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case BLUE:
                    textView.setBackgroundResource(R.drawable.row_item_blue_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case BLUE_STRESSED:
                    textView.setBackgroundResource(R.drawable.row_item_blue_stressed_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case WHITE:
                    textView.setBackgroundResource(R.drawable.row_item_white_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case WHITE_STRESSED:
                    textView.setBackgroundResource(R.drawable.row_item_white_stressed_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case BLACK:
                    textView.setBackgroundResource(R.drawable.row_item_black_bg);
                    textView.setTextColor(context.getResources().getColor(R.color.colorGeneralBg));
                    break;
            }
        }
    }

    private TextView generateTextView(int i, ViewGroup parent, boolean isLastItem) {
        int rowItemResource = this.biggerResultDisplay ? R.layout.row_item_big : R.layout.row_item_small;
        int rowItemId = this.biggerResultDisplay ? R.id.RowItemBig : R.id.RowItemSmall;
        View rowItemInflater = layoutInflater.inflate(rowItemResource, parent, false);
        TextView textView = (TextView) rowItemInflater.findViewById(rowItemId);

        if (this.layoutParamsGlobal == null) {
            LinearLayout.LayoutParams layoutParams = createLayoutParams(i, isLastItem);
            textView.setLayoutParams(layoutParams);
        } else {
            textView.setLayoutParams(layoutParamsGlobal);
        }

        return textView;
    }

    private LinearLayout.LayoutParams createLayoutParams(int i, boolean isLastItem) {
        int rowItemWidth = (rowItemWidths != null && !rowItemWidths.isEmpty()) ? rowItemWidths.get(i) : this.rowItemWidthGlobal;
        int rowItemHeight = (rowItemHeights != null && !rowItemHeights.isEmpty()) ? rowItemHeights.get(i) : this.rowItemHeightGlobal;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(rowItemWidth, rowItemHeight);
        layoutParams.setMargins(this.marginLeft, this.marginTop, isLastItem ? 0 : this.marginRight, this.marginBottom);
        return layoutParams;
    }
}