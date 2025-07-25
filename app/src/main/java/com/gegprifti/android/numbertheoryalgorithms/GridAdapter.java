package com.gegprifti.android.numbertheoryalgorithms;


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
import com.gegprifti.android.numbertheoryalgorithms.common.Helper;


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
    private final boolean smallerResultDisplay;
    private final int marginLeft;
    private final int marginTop;
    private final int marginRight;
    private final int marginBottom;
    private int lastItemIndex = -1;

    GridAdapter(Context context, LinearLayout staticColumnHeader, List<List<RowItem>> gridRows, int rowItemWidthGlobal, List<Integer> rowItemWidths, int rowItemHeightGlobal, List<Integer> rowItemHeights, boolean smallerResultDisplay) {
        this.context = context;
        this.gridRows = gridRows;
        this.rowItemWidthGlobal = rowItemWidthGlobal;
        this.rowItemWidths = rowItemWidths;
        this.rowItemHeightGlobal = rowItemHeightGlobal;
        this.rowItemHeights = rowItemHeights;
        this.smallerResultDisplay = smallerResultDisplay;

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.marginLeft = 0; // this.smallerResultDisplay ? (int)Helper.ConvertDpToPixel(1F, layoutInflater.getContext()) : (int)Helper.ConvertDpToPixel(4F, layoutInflater.getContext());
        this.marginTop = 0;
        this.marginRight = this.smallerResultDisplay ? (int) Helper.ConvertDpToPixel(1F, layoutInflater.getContext()) : (int)Helper.ConvertDpToPixel(4F, layoutInflater.getContext());
        this.marginBottom = 0;

        if ((rowItemWidths == null || rowItemWidths.size() == 0) && (rowItemHeights == null || rowItemHeights.size() == 0)) {
            this.layoutParamsGlobal = new LinearLayout.LayoutParams(rowItemWidthGlobal, rowItemHeightGlobal);
            this.layoutParamsGlobal.setMargins(this.marginLeft, this.marginTop, this.marginRight, this.marginBottom);
        } else {
            this.layoutParamsGlobal = null;
        }

        // Get the last item index in the first row.
        if (this.gridRows != null && this.gridRows.size() > 0) {
            List<RowItem> firstRowItems = gridRows.get(0);
            if (firstRowItems != null && firstRowItems.size() > 0) {
                this.lastItemIndex = firstRowItems.size() - 1;
            }
        }

        // Write the static column header.
        int margin = this.smallerResultDisplay ? (int)Helper.ConvertDpToPixel(1F, layoutInflater.getContext()) : (int)Helper.ConvertDpToPixel(4F, layoutInflater.getContext());
        if (this.gridRows != null && this.gridRows.size() > 0) {
            List<RowItem> firstRowItems = this.gridRows.get(0);
            if (firstRowItems != null && firstRowItems.size() > 0) {
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
                        TextView textView = GenerateTextView(i, null, isLastItem);
                        staticColumnHeader.addView(textView);
                        RefreshTextView(context, rowItem, textView);
                    }
                }
            }
        }

        // Remove the column header.
        if (this.gridRows != null && this.gridRows.size() > 0) {
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
                    TextView textView = GenerateTextView(i, parent, isLastItem);
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
                RefreshTextView(context, rowItem, textView);
            }

            return convertView;
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
        }
        return  null;
    }

    public void Refresh(){
        this.notifyDataSetChanged();
    }

    private void RefreshTextView (Context context, RowItem rowItem, TextView textView) {
        if (context == null || rowItem == null || textView == null) {
            return;
        }

        // Set the value
        textView.setText(rowItem.getValue());

        // TODO +++ Set the on click listener.
        //if () {

        //}


        if (!rowItem.getIsHeader() && rowItem.getValue() != null && !rowItem.getValue().isEmpty()) {
            textView.setOnClickListener(view -> Helper.CopyTextToClipboard(context, rowItem.getValue()));
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

    private TextView GenerateTextView(int i, ViewGroup parent, boolean isLastItem) {
        LinearLayout.LayoutParams layoutParams = null;
        if (this.layoutParamsGlobal == null) {
            int rowItemWidth = (rowItemWidths != null && rowItemWidths.size() > 0) ? rowItemWidths.get(i) : this.rowItemWidthGlobal;
            int rowItemHeight = (rowItemHeights != null && rowItemHeights.size() > 0) ? rowItemHeights.get(i) : this.rowItemHeightGlobal;
            layoutParams = new LinearLayout.LayoutParams(rowItemWidth, rowItemHeight);
            layoutParams.setMargins(this.marginLeft, this.marginTop, isLastItem ? 0 : this.marginRight, this.marginBottom);
        }
        int rowItemResource = this.smallerResultDisplay ? R.layout.row_item_small : R.layout.row_item_big;
        int rowItemId = this.smallerResultDisplay ? R.id.RowItemSmall : R.id.RowItemBig;
        View rowItemInflater = layoutInflater.inflate(rowItemResource, parent, false);
        TextView textView = (TextView) rowItemInflater.findViewById(rowItemId);
        if (layoutParams == null) {
            textView.setLayoutParams(layoutParamsGlobal);
        } else {
            textView.setLayoutParams(layoutParams);
        }
        return textView;
    }

}