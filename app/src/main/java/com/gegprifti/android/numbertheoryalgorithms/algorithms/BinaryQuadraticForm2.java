package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import android.util.Log;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.GridCalculator;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.RowItem;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class BinaryQuadraticForm2 extends Algorithm implements GridCalculator {
    private final static String TAG = BinaryQuadraticForm2.class.getSimpleName();


    public BinaryQuadraticForm2(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public List<List<RowItem>> calculate() throws InterruptedException {
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger c = algorithmParameters.getInput3();
            BigInteger d = algorithmParameters.getInput4();
            BigInteger e = algorithmParameters.getInput5();
            BigInteger f = algorithmParameters.getInput6();

            List<List<RowItem>> rows = new ArrayList<>();

            BigInteger minX = ZERO;
            BigInteger maxX = f.abs();
            if (d.compareTo(ZERO) != 0) {
                maxX = f.divide(d).add(ONE);
            }
            BigInteger minY = ZERO;
            BigInteger maxY = f.abs();
            if (e.compareTo(ZERO) != 0) {
                maxY = f.divide(e).add(ONE);
            }

            // Create row header.
            List<RowItem> headerRow = new ArrayList<>();
            // Add the grid configuration button.
            headerRow.add(new RowItem(true, true));
            //
            for (BigInteger x = minX; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                headerRow.add(new RowItem(true, "x=" + x, false));
            }
            rows.add(headerRow);

            // Calculate ax² + bxy + cy² + dx + ey = f values.
            for (BigInteger y = minY; y.compareTo(maxY) <= 0; y = y.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                List<RowItem> row = new ArrayList<>();
                // Add column header.
                // boolean isYPrime = y.isProbablePrime(10);
                row.add(new RowItem(true, "y=" + y, false)); // isYPrime

                // Add f values.
                for(BigInteger x = minX; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();

                    BigInteger ax2 = a.multiply(x.pow(2));
                    BigInteger bxy = b.multiply(x).multiply(y);
                    BigInteger cy2 = c.multiply(y.pow(2));
                    BigInteger dx = d.multiply(x);
                    BigInteger ey = e.multiply(y);
                    BigInteger fCalculated = ax2.add(bxy).add(cy2).add(dx).add(ey);
                    // boolean isFPrime = fCalculated.isProbablePrime(10);

                    if (fCalculated.equals(f)) {
                        RowItem rowItemXSolution = headerRow.get(x.intValue() + 1);
                        rowItemXSolution.setHeaderStyle(RowItem.HeaderStyle.HIGHLIGHTED);
                        RowItem rowItemYSolution = row.get(0);
                        rowItemYSolution.setHeaderStyle(RowItem.HeaderStyle.HIGHLIGHTED);
                        RowItem rowItem = new RowItem(false, fCalculated.toString(), false, RowItem.ValueStyle.ORANGE); // isFPrime
                        rowItem.setIsSelected(true);
                        row.add(rowItem);
                    } else {
                        row.add(new RowItem(false, fCalculated.toString(), false)); // isFPrime
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