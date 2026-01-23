package com.gegprifti.android.numbertheoryalgorithms.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.gegprifti.android.numbertheoryalgorithms.R;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.UIHelper;

import java.util.List;
import java.util.Objects;

public class CellUI {
    private final Context context;
    private final boolean biggerResultDisplay;
    private final List<Integer> cellWidths;
    private final List<Integer> cellHeights;
    private final LayoutInflater layoutInflater;
    private final Margins margins;

    public Margins getMargins() {
        return margins;
    }

    public CellUI(@NonNull Context context, @NonNull List<Integer> cellWidths,  @NonNull List<Integer> cellHeights, boolean biggerResultDisplay) {
        this.context = Objects.requireNonNull(context, "CellUI: context must not be null");
        this.cellWidths = Objects.requireNonNull(cellWidths, "CellUI: cellWidths must not be null");
        this.cellHeights = Objects.requireNonNull(cellHeights, "CellUI: cellHeights must not be null");
        this.biggerResultDisplay = biggerResultDisplay;

        if (cellWidths.isEmpty()) {
            throw new IllegalArgumentException("CellUI: cellWidths must not be empty");
        }

        if (cellHeights.isEmpty()) {
            throw new IllegalArgumentException("CellUI: cellHeights must not be empty");
        }

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        margins = new Margins(context, biggerResultDisplay);

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
            textView.setOnClickListener(view -> UIHelper.copyTextIntoClipboardWithNotification(context, cell.getValue(), true));
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


    public TextView createTextView(int columnIndex, int rowIndex, ViewGroup parent, boolean isLastItem) {
        int cellResource = this.biggerResultDisplay ? R.layout.cell_big : R.layout.cell_small;
        int cellId = this.biggerResultDisplay ? R.id.CellBig : R.id.CellSmall;
        View cellInflater = layoutInflater.inflate(cellResource, parent, false);
        TextView textView = cellInflater.findViewById(cellId);
        LinearLayout.LayoutParams layoutParams = createLayoutParams(columnIndex, rowIndex, isLastItem);
        textView.setLayoutParams(layoutParams);
        return textView;
    }


    private LinearLayout.LayoutParams createLayoutParams(int columnIndex, int rowIndex, boolean isLastItem) {
        int cellWidth = cellWidths.get(columnIndex);
        int cellHeight = cellHeights.get(rowIndex);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(cellWidth, cellHeight);
        layoutParams.setMargins(margins.getLeft(), margins.getTop(), isLastItem ? 0 : margins.getRight(), margins.getBottom());
        return layoutParams;
    }
}
