package com.gegprifti.android.numbertheoryalgorithms.fragments.primeslist;


import android.util.Log;

import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.RowItem;
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


    public List<List<RowItem>> calculate() throws InterruptedException {
        try {
            List<List<RowItem>> rows = new ArrayList<>();
            List<RowItem> row;

            // Create the column headers
            row = new ArrayList<>();
            String columnLabel = columns + "k+";
            for(int c = -1; c < columns; c++) {
                RowItem rowItemHeader;
                if(c == -1) {
                    // This is the first header
                    rowItemHeader = new RowItem(true,"k",false);
                } else {
                    // This is column header
                    rowItemHeader = new RowItem(true,columnLabel + c,false);
                }
                row.add(rowItemHeader);
            }
            rows.add(row);
            // row if k = 6
            // 0,    1,    2,    3,    4,    5,    6
            // k,    6k+0, 6k+1, 6k+2, 6k+3, 6k+4, 6k+5

            // Create the numbers
            int nrOfRows = (int)Math.ceil((double) (numbers/columns)) + 1 ;
            for (int k = 0; k < nrOfRows; k++) {
                AlgorithmHelper.checkIfCanceled();

                // Start a new row
                row = new ArrayList<>();
                for(int c = -1; c < columns; c++) {
                    if(c == -1) {
                        // Create the row label
                        RowItem rowItem = new RowItem(true, Integer.toString(k), false);
                        row.add(rowItem);
                        // The first column. Represents the values of k.
                        // k
                        // 0
                        // 1
                        // 2
                        // ...
                    } else {
                        int number = (columns*k)+c;
                        boolean isPrime = BigInteger.valueOf((long)number).isProbablePrime(10);
                        RowItem rowItem = new RowItem(false, Integer.toString(number), isPrime, isPrime ? RowItem.ValueStyle.YELLOW : RowItem.ValueStyle.DEFAULT);
                        row.add(rowItem);
                    }
                }
                rows.add(row);
            }

            return rows;
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