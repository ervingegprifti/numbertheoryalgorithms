package com.gegprifti.android.numbertheoryalgorithms.algorithms.binaryquadraticform;


import android.util.Log;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.grid.Grid;
import com.gegprifti.android.numbertheoryalgorithms.grid.GridCalculator;
import com.gegprifti.android.numbertheoryalgorithms.grid.Cell;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class BinaryQuadraticForm1 extends Algorithm implements GridCalculator {
    private final static String TAG = BinaryQuadraticForm1.class.getSimpleName();


    public BinaryQuadraticForm1(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public Grid calculate() throws InterruptedException {
        try {
            List<List<Cell>> rows = new ArrayList<>();
            List<Cell> columnHeaders = new ArrayList<>();
            List<List<Cell>> rowHeaders = new ArrayList<>();

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

            // Create column headers.
            Cell columnHeaderOrigin = new Cell(true, "f", false);
            columnHeaders.add(columnHeaderOrigin);
            // ┌───────┐
            // │   f   │
            // └───────┘
            for (BigInteger x = ZERO; x.compareTo(xMax) <= 0; x = x.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();
                Cell columnHeader = new Cell(true, "x=" + x, false);
                columnHeaders.add(columnHeader);
            }
            Cell columnHeaderSolutions = new Cell(true, "solutions", false);
            columnHeaders.add(columnHeaderSolutions);
            // ┌───────┬───────┬───────┬───────┬───────┐      ┌─────────────┐
            // │   f   │  x=0  │  x=1  │  x=2  │  x=3  │ ...  │  solutions  │
            // └───────┴───────┴───────┴───────┴───────┘      └─────────────┘

            // Calculate solutions from 0 up until f
            for(BigInteger i = ZERO; i.compareTo(f.add(ONE)) <= 0; i = i.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();
                List<Cell> row = new ArrayList<>();

                // Add row header.
                List<Cell> rowHeader = new ArrayList<>();
                Cell cellRowHeader = new Cell(true, i.toString(), false);
                rowHeader.add(cellRowHeader);
                rowHeaders.add(rowHeader);

                row.add(cellRowHeader);

                List<String> solutionsPerRow = new ArrayList<>();

                // Add columns.
                for (BigInteger x = ZERO; x.compareTo(xMax) <= 0; x = x.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();
                    String resultFromFixedX = getResultFromFixedX(a, b, c, d, e, i, x);
                    if (resultFromFixedX.isEmpty()) {
                        Cell column = new Cell(false, resultFromFixedX, false, Cell.ValueStyle.DEFAULT);
                        row.add(column);
                    } else {
                        Cell column = new Cell(false, resultFromFixedX, false, Cell.ValueStyle.YELLOW);
                        row.add(column);
                        solutionsPerRow.add("[" + resultFromFixedX + "]");
                    }
                }

                // Add last column.
                if (solutionsPerRow.isEmpty()) {
                    Cell lastColumn = new Cell(false, "", false);
                    row.add(lastColumn);
                } else {
                    Cell columnHeaderEdit = row.get(0);
                    columnHeaderEdit.setHeaderStyle(Cell.HeaderStyle.HIGHLIGHTED);
                    String solutions = String.join(" ", solutionsPerRow);
                    Cell lastColumn = new Cell(false, solutions, false, Cell.ValueStyle.YELLOW);
                    row.add(lastColumn);
                }

                rows.add(row);
            }

            Grid grid = new Grid(rows, null, columnHeaders, rowHeaders);
            return grid;
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