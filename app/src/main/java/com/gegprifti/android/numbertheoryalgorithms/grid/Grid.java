package com.gegprifti.android.numbertheoryalgorithms.grid;

import java.util.List;

public class Grid {
    private final List<List<Cell>> rows;
    private final List<Cell> columnHeaders;
    private final List<Cell> rowHeaders;

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
     * @return  List of header cells in the y axis.
     */
    public List<Cell> getRowHeaders() {
        return rowHeaders;
    }

    /**
     *
     * @param rows              The list of rows.
     * @param columnHeaders     List of header cells in the x axis.
     * @param rowHeaders        List of header cells in the y axis.
     */
    public Grid(List<List<Cell>> rows, List<Cell> columnHeaders, List<Cell> rowHeaders) {
        this.rows = rows;
        this.columnHeaders = columnHeaders;
        this.rowHeaders = rowHeaders;
    }
}
