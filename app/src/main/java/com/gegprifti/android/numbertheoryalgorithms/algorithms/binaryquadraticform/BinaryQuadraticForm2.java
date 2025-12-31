package com.gegprifti.android.numbertheoryalgorithms.algorithms.binaryquadraticform;


import android.util.Log;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
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
    public List<List<Cell>> calculate() throws InterruptedException {
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger c = algorithmParameters.getInput3();
            BigInteger d = algorithmParameters.getInput4();
            BigInteger e = algorithmParameters.getInput5();
            BigInteger f = algorithmParameters.getInput6();

            List<List<Cell>> rows = new ArrayList<>();

            BigInteger minX = ZERO;
            BigInteger maxX = f.abs();
            if (d.compareTo(ZERO) != 0) {
                maxX = f.divide(d).add(ONE);
            }
            minX = maxX.negate();

            BigInteger minY = ZERO;
            BigInteger maxY = f.abs();
            if (e.compareTo(ZERO) != 0) {
                maxY = f.divide(e).add(ONE);
            }
            minY = maxY.negate();

            // Create row header.
            List<Cell> headerRow = new ArrayList<>();
            // Add the grid configuration button.
            headerRow.add(new Cell(true, true));
            //
            for (BigInteger x = minX; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                headerRow.add(new Cell(true, "x=" + x, false));
            }
            rows.add(headerRow);

            // Calculate ax² + bxy + cy² + dx + ey = f values.
            for (BigInteger y = maxY; y.compareTo(minY) >= 0; y = y.subtract(ONE)) { // BigInteger y = minY; y.compareTo(maxY) <= 0; y = y.add(ONE)
                AlgorithmHelper.checkIfCanceled();

                List<Cell> row = new ArrayList<>();
                // Add column header.
                // boolean isYPrime = y.isProbablePrime(10);
                row.add(new Cell(true, "y=" + y, false)); // isYPrime

                // Add f values.
                for(BigInteger x = minX; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();

                    BigInteger axx = a.multiply(x.pow(2));
                    BigInteger bxy = b.multiply(x).multiply(y);
                    BigInteger cyy = c.multiply(y.pow(2));
                    BigInteger dx = d.multiply(x);
                    BigInteger ey = e.multiply(y);
                    BigInteger fCalculated = axx.add(bxy).add(cyy).add(dx).add(ey);
                    // boolean isFPrime = fCalculated.isProbablePrime(10);

                    if (fCalculated.equals(f)) {
                        // Highlight row header x solution
                        Cell cellXSolution = headerRow.get(minX.abs().add(x.add(ONE)).intValue());
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