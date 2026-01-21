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
    private final int a;
    private final int numbers;

    public PrimesListCalculator(int a, int numbers) {
        this.a = a;
        this.numbers = numbers;
    }


    public Grid calculate() throws InterruptedException {
        try {
            List<List<Cell>> corner = new ArrayList<>();
            List<List<Cell>> columnHeaders = new ArrayList<>();
            List<List<Cell>> rowHeaders = new ArrayList<>();
            List<List<Cell>> rows = new ArrayList<>();

            // Add the corner.
            List<Cell> cornerRow = new ArrayList<>();
            Cell cornerCell = new Cell(true,"x",false);
            cornerRow.add(cornerCell);
            corner.add(cornerRow);
            // ┌───────┐
            // │   x   │
            // └───────┘

            // Create column headers. List of header cells in the x axis.
            List<Cell> columnHeaderRow = new ArrayList<>();
            for(int r = 0; r < a; r++) {
                Cell columnHeaderCell = new Cell(true, a + "x" + "+" + r,false);
                columnHeaderRow.add(columnHeaderCell);
            }
            columnHeaders.add(columnHeaderRow);
            // column if a = 6
            // 0,    1,    2,    3,    4,    5,
            // 6x+0, 6x+1, 6x+2, 6x+3, 6x+4, 6x+5

            // Create the numbers
            int nrOfRows = (int)Math.ceil((double) (numbers/ a)) + 1 ;
            for (int x = 0; x < nrOfRows; x++) {
                AlgorithmHelper.checkIfCanceled();

                // Start a new row
                List<Cell> row = new ArrayList<>();
                List<Cell> rowHeaderRow = new ArrayList<>();
                for(int r = -1; r < a; r++) {
                    if(r == -1) {
                        // Create the row header
                        Cell rowHeaderCell = new Cell(true, Integer.toString(x), false);
                        rowHeaderRow.add(rowHeaderCell);
                        rowHeaders.add(rowHeaderRow);
                        // The first row cell. Represents the values of x.
                        // 0
                        // 1
                        // 2
                        // ...
                    } else {
                        int number = (a*x)+r;
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
            Log.e(TAG, "", ex);
            return null;
        }
    }
}