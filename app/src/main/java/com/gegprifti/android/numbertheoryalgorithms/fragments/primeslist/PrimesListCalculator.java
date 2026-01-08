package com.gegprifti.android.numbertheoryalgorithms.fragments.primeslist;


import android.util.Log;

import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.grid.Cell;
import com.gegprifti.android.numbertheoryalgorithms.grid.Grid;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class PrimesListCalculator {
    private final static String TAG = PrimesListCalculator.class.getSimpleName();
    private final int columns;
    private final int numbers;

    public PrimesListCalculator(int columns, int numbers) {
        this.columns = columns;
        this.numbers = numbers;
    }


    public Grid calculate() throws InterruptedException {
        try {
            List<List<Cell>> corner = new ArrayList<>(); // TODO
            List<List<Cell>> columnHeaders = new ArrayList<>();
            List<List<Cell>> rowHeaders = new ArrayList<>();
            List<List<Cell>> rows = new ArrayList<>();

            // Add the corner.
            List<Cell> cornerRow = new ArrayList<>();
            Cell cornerCell = new Cell(true,"k",false);
            cornerRow.add(cornerCell);
            corner.add(cornerRow);
            // ┌───────┐
            // │   k   │
            // └───────┘

            // Create column headers. List of header cells in the x axis.
            List<Cell> columnHeaderRow = new ArrayList<>();
            for(int c = 0; c < columns; c++) {
                Cell columnHeaderCell = new Cell(true,columns + "k+" + c,false);
                columnHeaderRow.add(columnHeaderCell);
            }
            columnHeaders.add(columnHeaderRow);
            // column if k = 6
            // 0,    1,    2,    3,    4,    5,
            // 6k+0, 6k+1, 6k+2, 6k+3, 6k+4, 6k+5

            // Create the numbers
            int nrOfRows = (int)Math.ceil((double) (numbers/columns)) + 1 ;
            for (int k = 0; k < nrOfRows; k++) {
                AlgorithmHelper.checkIfCanceled();

                // Start a new row
                List<Cell> row = new ArrayList<>();
                List<Cell> rowHeaderRow = new ArrayList<>();
                for(int c = -1; c < columns; c++) {
                    if(c == -1) {
                        // Create the row header
                        Cell rowHeaderCell = new Cell(true, Integer.toString(k), false);
                        rowHeaderRow.add(rowHeaderCell);
                        rowHeaders.add(rowHeaderRow);
                        // The first row cell. Represents the values of k.
                        // 0
                        // 1
                        // 2
                        // ...
                    } else {
                        int number = (columns*k)+c;
                        boolean isPrime = BigInteger.valueOf((long)number).isProbablePrime(10);
                        Cell cell = new Cell(false, Integer.toString(number), isPrime, isPrime ? Cell.ValueStyle.YELLOW : Cell.ValueStyle.DEFAULT);
                        row.add(cell);
                    }
                }
                rows.add(row);
            }

            return new Grid(corner, columnHeaders, rowHeaders, rows, null);
        } catch (InterruptedException ex) {
            // This specifically handles the cancellation.
            // Re-throw it so ProgressManager can handle it correctly.
            throw ex;
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return null;
        }
    }
}