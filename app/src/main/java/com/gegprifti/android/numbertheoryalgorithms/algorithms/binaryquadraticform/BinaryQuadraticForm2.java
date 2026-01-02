package com.gegprifti.android.numbertheoryalgorithms.algorithms.binaryquadraticform;


import android.os.Debug;
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


public class BinaryQuadraticForm2 extends Algorithm implements GridCalculator {
    private final static String TAG = BinaryQuadraticForm2.class.getSimpleName();


    public BinaryQuadraticForm2(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public Grid calculate() throws InterruptedException {
        try {
            List<List<Cell>> rows = new ArrayList<>();
            List<Cell> columnHeaders = new ArrayList<>();
            List<Cell> rowHeaders = new ArrayList<>();

            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger c = algorithmParameters.getInput3();
            BigInteger d = algorithmParameters.getInput4();
            BigInteger e = algorithmParameters.getInput5();
            BigInteger f = algorithmParameters.getInput6();

            BigInteger minX = ZERO;
            BigInteger maxX = f.abs();
            if (d.compareTo(ZERO) != 0) {
                maxX = f.divide(d.abs()).add(ONE);
            }
            minX = maxX.negate();

            BigInteger minY = ZERO;
            BigInteger maxY = f.abs();
            if (e.compareTo(ZERO) != 0) {
                maxY = f.divide(e.abs()).add(ONE);
            }
            minY = maxY.negate();

            // Create column headers.
            // Add the grid configuration button.
            Cell columnHeaderOrigin =new Cell(true, true);
            columnHeaders.add(columnHeaderOrigin);
            for (BigInteger x = minX; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();
                Cell columnHeader = new Cell(true, "x=" + x, false);
                columnHeaders.add(columnHeader);
            }

            // Calculate ax² + bxy + cy² + dx + ey = f values.
            for (BigInteger y = maxY; y.compareTo(minY) >= 0; y = y.subtract(ONE)) { // BigInteger y = minY; y.compareTo(maxY) <= 0; y = y.add(ONE)
                AlgorithmHelper.checkIfCanceled();
                List<Cell> row = new ArrayList<>();

                // Add row header.
                Cell rowHeader = new Cell(true, "y=" + y, false);
                rowHeaders.add(rowHeader);
                row.add(rowHeader);

                // Add f values.
                for(BigInteger x = minX; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();

                    BigInteger axx = a.multiply(x.pow(2));
                    BigInteger bxy = b.multiply(x).multiply(y);
                    BigInteger cyy = c.multiply(y.pow(2));
                    BigInteger dx = d.multiply(x);
                    BigInteger ey = e.multiply(y);
                    BigInteger fCalculated = axx.add(bxy).add(cyy).add(dx).add(ey);

                    if (fCalculated.equals(f)) {
                        // Highlight row header x solution
                        Cell cellXSolution = columnHeaders.get(minX.abs().add(x.add(ONE)).intValue());
                        cellXSolution.setHeaderStyle(Cell.HeaderStyle.HIGHLIGHTED);
                        // Highlight
                        Cell cellYSolution = row.get(0);
                        cellYSolution.setHeaderStyle(Cell.HeaderStyle.HIGHLIGHTED);
                        //
                        Cell cell = new Cell(false, fCalculated.toString(), false, Cell.ValueStyle.ORANGE);
                        cell.setIsSelected(true);
                        row.add(cell);
                    } else {
                        Cell cell = new Cell(false, fCalculated.toString(), false);
                        if (x.compareTo(ZERO) > 0 && y.compareTo(ZERO) > 0) {
                            cell.setQuadrant(1);
                        } else if (x.compareTo(ZERO) < 0 && y.compareTo(ZERO) > 0) {
                            cell.setQuadrant(2);
                        } else if (x.compareTo(ZERO) < 0 && y.compareTo(ZERO) < 0) {
                            cell.setQuadrant(3);
                        } else if (x.compareTo(ZERO) > 0 && y.compareTo(ZERO) < 0) {
                            cell.setQuadrant(4);
                        }
                        row.add(cell);
                    }
                }
                rows.add(row);
            }

            Grid grid = new Grid(rows, columnHeaders, rowHeaders);
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
}