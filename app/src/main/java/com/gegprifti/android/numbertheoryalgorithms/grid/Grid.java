package com.gegprifti.android.numbertheoryalgorithms.grid;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Grid {
    private final List<List<Cell>> rows;
    private final List<Cell> columnHeaderOrigin;
    private final List<Cell> columnHeaders;
    private final List<List<Cell>> rowHeaders;

    /**
     *
     * @return  The list of rows.
     */
    public List<List<Cell>> getRows() {
        return rows;
    }

    /**
     *
     * @return  List of header cells in the x axis.
     */
    public List<Cell> getColumnHeaders() {
        return columnHeaders;
    }

    /**
     *
     * @return  List of header origin cell in the x axis.
     */
    public List<Cell> getColumnHeaderOrigin() {
        return columnHeaderOrigin;
    }

    /**
     *
     * @return  List of header cells in the y axis.
     */
    public List<List<Cell>> getRowHeaders() {
        return rowHeaders;
    }

    /**
     *
     * @param rows              The list of rows.
     * @param columnHeaders     List of header cells in the x axis.
     * @param rowHeaders        List of header cells in the y axis.
     */
    public Grid(List<List<Cell>> rows, List<Cell> columnHeaderOrigin, List<Cell> columnHeaders, List<List<Cell>> rowHeaders) {
        this.rows = rows;
        this.columnHeaderOrigin = columnHeaderOrigin;
        this.columnHeaders = columnHeaders;
        this.rowHeaders = rowHeaders;
    }

    /**
     * Write the static column headers.
     * @param cellUI
     * @param columnHeaders
     * @param staticColumnHeader
     */
    public static void setColumnHeaders(CellUI cellUI, List<Cell> columnHeaders, LinearLayout staticColumnHeader) {
        staticColumnHeader.removeAllViews();
        LinearLayout.LayoutParams layoutParamsStaticColumnHeader = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsStaticColumnHeader.setMargins(0, 0, 0, cellUI.getMargins().getMargin());
        staticColumnHeader.setLayoutParams(layoutParamsStaticColumnHeader);
        int lastItemIndex = columnHeaders.size() - 1;
        for (int i = 0; i < columnHeaders.size(); i++) {
            Cell cell = columnHeaders.get(i);
            if (cell.getIsHeader()) {
                if (cell.getIsConfig()) {
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
