package com.gegprifti.android.numbertheoryalgorithms.algorithms.binaryquadraticform;


import android.util.Log;

import androidx.annotation.NonNull;

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
    private final static BigInteger LIMIT_AXIS = new BigInteger("250");
    private boolean passedMinXLimitAxis = false;
    private boolean passedMaxXLimitAxis = false;
    private boolean passedMinYLimitAxis = false;
    private boolean passedMaxYLimitAxis = false;

    public BinaryQuadraticForm2(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public Grid calculate() throws InterruptedException {
        try {
            List<List<Cell>> corner = new ArrayList<>();
            List<List<Cell>> columnHeaders = new ArrayList<>();
            List<List<Cell>> rowHeaders = new ArrayList<>();
            List<List<Cell>> rows = new ArrayList<>();
            List<Integer> columnMaxLengths = new ArrayList<>();
            
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger c = algorithmParameters.getInput3();
            BigInteger d = algorithmParameters.getInput4();
            BigInteger e = algorithmParameters.getInput5();
            BigInteger f = algorithmParameters.getInput6();

            BigInteger maxX = f.abs();
            if (d.compareTo(ZERO) != 0) {
                maxX = f.abs().divide(d.abs()).add(ONE);
            }
            BigInteger minX = maxX.negate();
            if(minX.compareTo(LIMIT_AXIS.negate()) < 0){
                minX = LIMIT_AXIS.add(ONE).negate();
                passedMinXLimitAxis = true;
            }
            if(maxX.compareTo(LIMIT_AXIS) > 0){
                maxX = LIMIT_AXIS.add(ONE);
                passedMaxXLimitAxis = true;
            }

            BigInteger maxY = f.abs();
            if (e.compareTo(ZERO) != 0) {
                maxY = f.abs().divide(e.abs()).add(ONE);
            }
            BigInteger minY = maxY.negate();
            if(minY.compareTo(LIMIT_AXIS.negate()) < 0){
                minY = LIMIT_AXIS.add(ONE).negate();
                passedMinYLimitAxis = true;
            }
            if(maxY.compareTo(LIMIT_AXIS) > 0){
                maxY = LIMIT_AXIS.add(ONE);
                passedMaxYLimitAxis = true;
            }

            // Add the corner.
            List<Cell> cornerRow = new ArrayList<>();
            Cell cornerCell = new Cell(true, "f(x,y)");
            cornerRow.add(cornerCell);
            corner.add(cornerRow);
            // ┌────────┐
            // │ f(x,y) │
            // └────────┘

            // Add the length of the cornerCell value to begin with.
            int columnHeaderRowIndex = 0;
            addColumnMaxLength(columnMaxLengths, cornerCell, columnHeaderRowIndex);

            // Add column headers. List of header cells in the x axis.
            List<Cell> columnHeaderRow = new ArrayList<>();
            for (BigInteger x = minX; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();
                Cell columnHeaderCell = new Cell(true, x.toString());
                Cell cellInfinit = new Cell(true, "···");
                if (passedMinXLimitAxis && x.compareTo(minX) == 0) {
                    columnHeaderCell = cellInfinit;
                }
                if (passedMaxXLimitAxis && x.compareTo(maxX) == 0) {
                    columnHeaderCell =  cellInfinit;
                }
                columnHeaderRow.add(columnHeaderCell);
                // Add the length of the columnHeaderCell values to begin with.
                columnHeaderRowIndex++;
                addColumnMaxLength(columnMaxLengths, columnHeaderCell, columnHeaderRowIndex);
            }
            columnHeaders.add(columnHeaderRow);
            // ┌───────┬───────┬───────┐     ┌───────┐     ┌───────┬───────┬───────┐
            // │ x=-8  │ x=-7  │ x=-6  │ ... │  x=0  │ ... │  x=6  │  x=7  │  x=8  │
            // └───────┴───────┴───────┘     └───────┘     └───────┴───────┴───────┘

            // Calculate ax² + bxy + cy² + dx + ey = f values.
            for (BigInteger y = maxY; y.compareTo(minY) >= 0; y = y.subtract(ONE)) { // BigInteger y = minY; y.compareTo(maxY) <= 0; y = y.add(ONE)
                AlgorithmHelper.checkIfCanceled();
                List<Cell> row = new ArrayList<>();

                // Add row headers.
                List<Cell> rowHeaderRow = new ArrayList<>();
                Cell rowHeaderCell = new Cell(true, y.toString());
                Cell cellInfinit = new Cell(true, "···");
                if (passedMinXLimitAxis && y.compareTo(minY) == 0) {
                    rowHeaderCell = cellInfinit;
                }
                if (passedMaxXLimitAxis && y.compareTo(maxY) == 0) {
                    rowHeaderCell =  cellInfinit;
                }
                rowHeaderRow.add(rowHeaderCell);
                columnHeaderRowIndex = 0;
                addColumnMaxLength(columnMaxLengths, rowHeaderCell, columnHeaderRowIndex);
                rowHeaders.add(rowHeaderRow);

                // Add f values.
                for(BigInteger x = minX; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();

                    BigInteger axx = a.multiply(x.pow(2));
                    BigInteger bxy = b.multiply(x).multiply(y);
                    BigInteger cyy = c.multiply(y.pow(2));
                    BigInteger dx = d.multiply(x);
                    BigInteger ey = e.multiply(y);
                    BigInteger fCalculated = axx.add(bxy).add(cyy).add(dx).add(ey);

                    columnHeaderRowIndex++;
                    if (fCalculated.equals(f)) {
                        Cell cell = createSolutionCell(columnHeaders, minX, x, rowHeaders, minY, y, fCalculated);
                        row.add(cell);
                        addColumnMaxLength(columnMaxLengths, cell, columnHeaderRowIndex);
                    } else {
                        Cell cell = createNonSolutionCell(fCalculated, minX, x, maxX, minY, y, maxY);
                        row.add(cell);
                        addColumnMaxLength(columnMaxLengths, cell, columnHeaderRowIndex);
                    }
                }
                rows.add(row);
            }

            return new Grid(corner, columnHeaders, rowHeaders, rows, columnMaxLengths);
        } catch (InterruptedException ex) {
            // This specifically handles the cancellation.
            // Re-throw it so ProgressManager can handle it correctly.
            throw ex;
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
            return null;
        }
    }

    private void addColumnMaxLength(List<Integer> columnMaxLengths, Cell cell, int index) {
        String currentLengthAsString = cell.getValue1();
        int currentLength = (currentLengthAsString == null) ? 0 : currentLengthAsString.length();
        // Add a length to begin with.
        while (columnMaxLengths.size() <= index) {
            columnMaxLengths.add(0);
        }
        // Update the previous length if smaller than the current one.
        int previousLength = columnMaxLengths.get(index);
        if (currentLength > previousLength) {
            columnMaxLengths.set(index, currentLength);
        }
    }

    @NonNull
    private static Cell createSolutionCell(List<List<Cell>> columnHeaders, BigInteger minX, BigInteger x, List<List<Cell>> rowHeaders, BigInteger minY, BigInteger y, BigInteger fCalculated) {
        // Highlight column header x solution.
        List<Cell> columnHeaderRow = columnHeaders.get(0);
        Cell xSolutionCell = columnHeaderRow.get(minX.abs().add(x).intValue());
        xSolutionCell.setHeaderStyle(Cell.HeaderStyle.HIGHLIGHTED);
        // Highlight row header y solution.
        Cell ySolutionCell = rowHeaders.get(minY.abs().subtract(y).intValue()).get(0);
        ySolutionCell.setHeaderStyle(Cell.HeaderStyle.HIGHLIGHTED);
        //
        Cell cell = new Cell(false, fCalculated.toString(), false, Cell.ValueStyle.ORANGE);
        cell.setIsSelected(true);
        return cell;
    }

    @NonNull
    private Cell createNonSolutionCell(BigInteger fCalculated, BigInteger minX, BigInteger x, BigInteger maxX, BigInteger minY, BigInteger y, BigInteger maxY) {
        Cell cell = new Cell(false, fCalculated.toString(), false);

        if (x.compareTo(ZERO) >= 0 && y.compareTo(ZERO) >= 0) {
            cell.setQuadrant(1);
        } else if (x.compareTo(ZERO) < 0 && y.compareTo(ZERO) > 0) {
            cell.setQuadrant(2);
        } else if (x.compareTo(ZERO) < 0 && y.compareTo(ZERO) < 0) {
            cell.setQuadrant(3);
        } else if (x.compareTo(ZERO) > 0 && y.compareTo(ZERO) < 0) {
            cell.setQuadrant(4);
        }

        //
        if (passedMinXLimitAxis && x.compareTo(minX) == 0) {
            Cell cellInfinit = new Cell(false, "···", false);
            cellInfinit.setIsInfinit(true);
            return cellInfinit;
        }
        if (passedMaxXLimitAxis && x.compareTo(maxX) == 0) {
            Cell cellInfinit = new Cell(false, "···", false);
            cellInfinit.setIsInfinit(true);
            return cellInfinit;
        }
        if (passedMinYLimitAxis && y.compareTo(minY) == 0) {
            Cell cellInfinit = new Cell(false, "···", false);
            cellInfinit.setIsInfinit(true);
            return cellInfinit;
        }
        if (passedMaxYLimitAxis && y.compareTo(maxY) == 0) {
            Cell cellInfinit = new Cell(false, "···", false);
            cellInfinit.setIsInfinit(true);
            return cellInfinit;
        }

        return cell;
    }
}