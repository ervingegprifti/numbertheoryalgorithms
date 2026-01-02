package com.gegprifti.android.numbertheoryalgorithms.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;

import java.util.List;

public class CellUI {
    private final Context context;
    private final boolean biggerResultDisplay;
    private final int cellWidthDefault;
    private final List<Integer> cellWidths;
    private final int cellHeightDefault;
    private final List<Integer> cellHeights;
    private final LayoutInflater layoutInflater;
    private final Margins margins;
    private final LinearLayout.LayoutParams layoutParamsDefault;

    public Margins getMargins() {
        return margins;
    }

    public CellUI(Context context, int cellWidthDefault, List<Integer> cellWidths, int cellHeightDefault, List<Integer> cellHeights, boolean biggerResultDisplay) {
        this.context = context;
        this.biggerResultDisplay = biggerResultDisplay;
        this.cellWidthDefault = cellWidthDefault;
        this.cellWidths = cellWidths;
        this.cellHeightDefault = cellHeightDefault;
        this.cellHeights = cellHeights;

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        margins = new Margins(context, biggerResultDisplay);
        if ((cellWidths == null || cellWidths.isEmpty()) && (cellHeights == null || cellHeights.isEmpty())) {
            layoutParamsDefault = new LinearLayout.LayoutParams(cellWidthDefault, cellHeightDefault);
            layoutParamsDefault.setMargins(margins.getLeft(), margins.getTop(), margins.getRight(), margins.getBottom());
        } else {
            layoutParamsDefault = null;
        }
    }


    public void refreshCell(Cell cell, TextView textView) {
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
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorGeneralBg));
                    break;
                case OUTLINED:
                    textView.setBackgroundResource(R.drawable.cell_header_outlined_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorGeneralBg));
                    break;
                case HIGHLIGHTED:
                    textView.setBackgroundResource(R.drawable.cell_header_highlighted_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorGeneralBg));
                    break;
                case BLANK:
                    textView.setBackgroundResource(R.drawable.cell_blank_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorGeneralBg));
                    break;
            }
        } else {
            switch (cell.getValueStyle()) {
                case DEFAULT:
                    textView.setBackgroundResource(R.drawable.cell_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case YELLOW:
                    textView.setBackgroundResource(R.drawable.cell_yellow_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case YELLOW_STRESSED:
                    textView.setBackgroundResource(R.drawable.cell_yellow_stressed_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case ORANGE:
                    textView.setBackgroundResource(R.drawable.cell_orange_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case ORANGE_STRESSED:
                    textView.setBackgroundResource(R.drawable.cell_orange_stressed_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case GREEN:
                    textView.setBackgroundResource(R.drawable.cell_green_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case GREEN_STRESSED:
                    textView.setBackgroundResource(R.drawable.cell_green_stressed_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case BLUE:
                    textView.setBackgroundResource(R.drawable.cell_blue_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case BLUE_STRESSED:
                    textView.setBackgroundResource(R.drawable.cell_blue_stressed_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case WHITE:
                    textView.setBackgroundResource(R.drawable.cell_white_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case WHITE_STRESSED:
                    textView.setBackgroundResource(R.drawable.cell_white_stressed_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                    break;
                case BLACK:
                    textView.setBackgroundResource(R.drawable.cell_black_bg);
                    textView.setTextColor(ContextCompat.getColor(context, R.color.colorGeneralBg));
                    break;
            }
        }
    }


    public TextView createTextView(int i, ViewGroup parent, boolean isLastItem) {
        int cellResource = this.biggerResultDisplay ? R.layout.cell_big : R.layout.cell_small;
        int cellId = this.biggerResultDisplay ? R.id.CellBig : R.id.CellSmall;
        View cellInflater = layoutInflater.inflate(cellResource, parent, false);
        TextView textView = cellInflater.findViewById(cellId);

        if (this.layoutParamsDefault == null) {
            LinearLayout.LayoutParams layoutParams = createLayoutParams(i, isLastItem);
            textView.setLayoutParams(layoutParams);
        } else {
            textView.setLayoutParams(layoutParamsDefault);
        }

        return textView;
    }


    public LinearLayout.LayoutParams createLayoutParams(int i, boolean isLastItem) {
        int cellWidth = CellUI.getCellWidth(i, cellWidths, cellWidthDefault);
        int cellHeight = CellUI.getCellHeight(i, cellHeights, cellHeightDefault);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(cellWidth, cellHeight);
        layoutParams.setMargins(margins.getLeft(), margins.getTop(), isLastItem ? 0 : margins.getRight(), margins.getBottom());
        return layoutParams;
    }


    private static int getCellWidth(int i, List<Integer> cellWidths, int cellWidthDefault) {
        int cellWidth = (cellWidths != null && !cellWidths.isEmpty()) ? cellWidths.get(i) : cellWidthDefault;
        return cellWidth;
    }


    private static int getCellHeight(int i, List<Integer> cellHeights, int cellHeightDefault) {
        int cellHeight = (cellHeights != null && !cellHeights.isEmpty()) ? cellHeights.get(i) : cellHeightDefault;
        return cellHeight;
    }
}
