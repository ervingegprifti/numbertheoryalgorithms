package com.gegprifti.android.numbertheoryalgorithms.algorithms.binaryquadraticform;


import android.util.Log;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.GridCalculator;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.RowItem;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class BinaryQuadraticForm1 extends Algorithm implements GridCalculator {
    private final static String TAG = BinaryQuadraticForm1.class.getSimpleName();


    public BinaryQuadraticForm1(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public List<List<RowItem>> calculate() throws InterruptedException {
        try {
            List<List<RowItem>> rows = new ArrayList<>();

            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger c = algorithmParameters.getInput3();
            BigInteger d = algorithmParameters.getInput4();
            BigInteger e = algorithmParameters.getInput5();
            BigInteger f = algorithmParameters.getInput6();

            BigInteger xMax = f.add(ONE);
            if (d.compareTo(ZERO) != 0) {
                xMax = f.divide(d).add(ONE);
            }

            // Create row headers.
            List<RowItem> rowHeaders = new ArrayList<>();
            RowItem rowHeaderOrigin = new RowItem(true, "f", false);
            rowHeaders.add(rowHeaderOrigin);
            // ┌───────┐
            // │   f   │
            // └───────┘
            for (BigInteger x = ZERO; x.compareTo(xMax) <= 0; x = x.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();
                RowItem rowHeader = new RowItem(true, "x=" + x, false);
                rowHeaders.add(rowHeader);
            }
            RowItem rowHeaderSolutions = new RowItem(true, "solutions", false);
            rowHeaders.add(rowHeaderSolutions);
            rows.add(rowHeaders);
            // ┌───────┬───────┬───────┬───────┬───────┐      ┌─────────────┐
            // │   f   │  x=0  │  x=1  │  x=2  │  x=3  │ ...  │  solutions  │
            // └───────┴───────┴───────┴───────┴───────┘      └─────────────┘

            // Calculate solutions from 0 up until f
            for(BigInteger i = ZERO; i.compareTo(f.add(ONE)) <= 0; i = i.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();
                List<RowItem> rowItems = new ArrayList<>();

                // Add column header.
                RowItem columnHeader = new RowItem(true, i.toString(), false);
                rowItems.add(columnHeader);

                List<String> solutionsPerRow = new ArrayList<>();

                // Add columns.
                for (BigInteger x = ZERO; x.compareTo(xMax) <= 0; x = x.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();
                    String resultFromFixedX = getResultFromFixedX(a, b, c, d, e, i, x);
                    if (resultFromFixedX.isEmpty()) {
                        RowItem column = new RowItem(false, resultFromFixedX, false, RowItem.ValueStyle.DEFAULT);
                        rowItems.add(column);
                    } else {
                        RowItem column = new RowItem(false, resultFromFixedX, false, RowItem.ValueStyle.YELLOW);
                        rowItems.add(column);
                        solutionsPerRow.add("[" + resultFromFixedX + "]");
                    }
                }

                // Add last column.
                if (solutionsPerRow.isEmpty()) {
                    RowItem lastColumn = new RowItem(false, "", false);
                    rowItems.add(lastColumn);
                } else {
                    RowItem columnHeaderEdit = rowItems.get(0);
                    columnHeaderEdit.setHeaderStyle(RowItem.HeaderStyle.HIGHLIGHTED);
                    String solutions = String.join(" ", solutionsPerRow);
                    RowItem lastColumn = new RowItem(false, solutions, false, RowItem.ValueStyle.YELLOW);
                    rowItems.add(lastColumn);
                }

                rows.add(rowItems);
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


    private String getResultFromFixedX(BigInteger a, BigInteger b, BigInteger c, BigInteger d, BigInteger e, BigInteger f, BigInteger x) throws InterruptedException {
        BigInteger yMax = f.add(ONE);
        if (e.compareTo(BigInteger.ZERO) != 0) {
            yMax = f.divide(e).add(ONE);
        }

        for (BigInteger y = ZERO; y.compareTo(yMax) <= 0; y = y.add(ONE)) {
            AlgorithmHelper.checkIfCanceled();
            BigInteger result = getResult(a, b, c, d, e, x, y);
            if (result.compareTo(f) == 0) {
                return x + ", " + y;
            }
            if (result.compareTo(f) > 0) {
                break;
            }
        }
        return "";
    }


    private BigInteger getResult(BigInteger a, BigInteger b, BigInteger c, BigInteger d, BigInteger e, BigInteger x, BigInteger y) {
        BigInteger axx = a.multiply(x.pow(2));
        BigInteger bxy = b.multiply(x).multiply(y);
        BigInteger cyy = c.multiply(y.pow(2));
        BigInteger dx = d.multiply(x);
        BigInteger ey = e.multiply(y);
        BigInteger result = axx.add(bxy).add(cyy).add(dx).add(ey);
        return result;
    }
}