package com.gegprifti.android.numbertheoryalgorithms.grid;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Grid {
    private final List<List<Cell>> corner;
    private final List<List<Cell>> columnHeaders;
    private final List<List<Cell>> rowHeaders;
    private final List<List<Cell>> rows;

    /**
     *
     * @return  The list of left top corner cell.
     */
    public List<List<Cell>> getCorner() {
        return corner;
    }

    /**
     *
     * @return  The list of header cells in the x axis.
     */
    public List<List<Cell>> getColumnHeaders() {
        return columnHeaders;
    }

    /**
     *
     * @return  The list of header cells in the y axis.
     */
    public List<List<Cell>> getRowHeaders() {
        return rowHeaders;
    }

    /**
     *
     * @return  The list of rows.
     */
    public List<List<Cell>> getRows() {
        return rows;
    }

    /**
     * @param corner            The list of left top corner cell.
     * @param columnHeaders     The list of header cells in the x axis.
     * @param rowHeaders        The list of header cells in the y axis.
     * @param rows              The list of rows.
     */
    public Grid(List<List<Cell>> corner, List<List<Cell>> columnHeaders, List<List<Cell>> rowHeaders, List<List<Cell>> rows) {
        this.corner = corner;
        this.columnHeaders = columnHeaders;
        this.rowHeaders = rowHeaders;
        this.rows = rows;
    }

    // TODO remove
    /**
     * @deprecated Use corner, columnHeaders, rowHeaders ListView or RecycleView directly and then synchronize horizontal and vertical scrolls.<br>
     *
     * Write the static corner and column header cells into their respective static layout.
     *
     * @param cellUI
     * @param columnHeaders
     * @param staticColumnHeader
     */
    @Deprecated
    public static void setColumnHeaders(CellUI cellUI, List<List<Cell>> columnHeaders, LinearLayout staticColumnHeader) {
        staticColumnHeader.removeAllViews();
        LinearLayout.LayoutParams layoutParamsStaticColumnHeader = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsStaticColumnHeader.setMargins(0, 0, 0, cellUI.getMargins().getMargin());
        staticColumnHeader.setLayoutParams(layoutParamsStaticColumnHeader);
        List<Cell> columnHeaderRow = columnHeaders.get(0);
        int lastItemIndex = columnHeaderRow.size() - 1;
        for (int i = 0; i < columnHeaderRow.size(); i++) {
            Cell cell = columnHeaderRow.get(i);
            if (cell.getIsHeader()) {
                if (cell.isCorner()) {
                    cell.setValue1("●");
                }
                boolean isLastItem = lastItemIndex == i;
                TextView textView = cellUI.createTextView(i, null, isLastItem);
                staticColumnHeader.addView(textView);
                cellUI.refreshCell(cell, textView);
            }
        }
    }
}
