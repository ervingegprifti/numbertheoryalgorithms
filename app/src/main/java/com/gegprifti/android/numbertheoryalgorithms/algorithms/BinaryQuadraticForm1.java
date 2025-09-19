package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.getNP;
import android.util.Log;
import android.util.Pair;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Solution;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.GridCalculator;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.RowItem;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class BinaryQuadraticForm1 extends Algorithm implements GridCalculator {
    private final static String TAG = BinaryQuadraticForm1.class.getSimpleName();


    public BinaryQuadraticForm1(AlgorithmParameters algorithmParameters) {
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

            BigInteger maxX = f;
            if (d.compareTo(ZERO) != 0) {
                maxX = f.divide(d).add(ONE);
            }

            // TODO This takes forever when e = 0. Look at it later.
            if (e.compareTo(ZERO) == 0) {
                return null;
            }

            // Create header row.
            List<RowItem> headerRow = new ArrayList<>();
            RowItem headerItem = new RowItem(true, "f", false);
            headerRow.add(headerItem);
            for (BigInteger x = ZERO; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                headerRow.add(new RowItem(true, "x=" + x, false));
            }
            headerRow.add(new RowItem(true, "solutions", false));
            rows.add(headerRow);

            // Calculate solutions from 0 up until f
            for(BigInteger i = ZERO; i.compareTo(f) <= 0; i = i.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                List<RowItem> rowItems = new ArrayList<>();
                // Add row header
                //RowItem item = new RowItem(true, i.toString(), false, i.equals(f) ? RowItem.HeaderDisplay.HIGHLIGHTED : RowItem.HeaderDisplay.DEFAULT);
                RowItem item = new RowItem(true, i.toString(), false);
                rowItems.add(item);
                // Add items
                for (BigInteger x = ZERO; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();

                    String resultXFixed = getBQFSolutionXFixed(b, d, e, i, x);
                    rowItems.add(new RowItem(false, resultXFixed, false, (resultXFixed == null || resultXFixed.isEmpty()) ? RowItem.ValueStyle.DEFAULT : RowItem.ValueStyle.YELLOW));
                }
                // Last column values.
                String resultSQFRun1 = runBQF1(b, d, e, i);
                if (resultSQFRun1 == null || resultSQFRun1.isEmpty()) {
                    rowItems.add(new RowItem(false, resultSQFRun1, false));
                } else {
                    RowItem rowItemSolution = rowItems.get(0);
                    rowItemSolution.setHeaderStyle(RowItem.HeaderStyle.HIGHLIGHTED);
                    rowItems.add(new RowItem(false, resultSQFRun1, false, RowItem.ValueStyle.YELLOW));
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


    private String runBQF1(BigInteger b, BigInteger d, BigInteger e, BigInteger f) throws InterruptedException {
        // Multiply both sides with b then, b²xy+bdx+bey=bf
        BigInteger bf = f.multiply(b);
        // Add de to both sides then, b²xy+bdx+bey+de=bf+de
        BigInteger de = d.multiply(e);
        // Let n=bf+de, then the RHS can be written as n=pq
        BigInteger n = bf.add(de);
        // So we must solve (bx+e)(by+d)=n

        // Factoring n into pq pairs
        List<Pair<BigInteger, BigInteger>> pairFactors = AlgorithmHelper.getPairFactors(n, true);

        int pairFactorsSize = pairFactors.size();
        // We expect pairFactorsSize to be 0, 4, 8, 12, 16, 20, 24,...
        if (pairFactorsSize == 0) {
            // No factors were found, hence no solutions. n is prime
            return "";
        }

        // Checking (bx+e)=p & (by+d)=q per each (p, q) pairs will give (x, y) solutions, if any
        StringBuilder sb = new StringBuilder();
        List<Solution> solutions = AlgorithmHelper.calculateBQFSolutions(b,d,e,pairFactors, true, false);
        if (!solutions.isEmpty()) {
            for(int i = 0; i < solutions.size(); i++) {
                AlgorithmHelper.checkIfCanceled();

                Solution solution = solutions.get(i);
                BigInteger x = solution.getX();
                BigInteger y = solution.getY();
                if (sb.toString().isEmpty()) {
                    sb.append(String.format(Locale.getDefault(), "[%s, %s]", getNP(x), getNP(y)));
                } else {
                    sb.append(String.format(Locale.getDefault(), " [%s, %s]", getNP(x), getNP(y)));
                }
            }
        } else {
            // No solutions were found.
            return "";
        }

        return sb.toString();
    }


    static String getBQFSolutionXFixed(BigInteger b, BigInteger d, BigInteger e, BigInteger f, BigInteger x) throws InterruptedException {
        BigInteger y = ZERO;
        BigInteger result = (b.multiply(x).multiply(y)).add((d.multiply(x))).add((e.multiply(y)));
        while (result.compareTo(f) < 0) {
            AlgorithmHelper.checkIfCanceled();

            y = y.add(ONE);
            result = (b.multiply(x).multiply(y)).add((d.multiply(x))).add((e.multiply(y)));
        }
        if (result.compareTo(f) == 0) {
            return "" + x + ", " + y + "";
        } else {
            return "";
        }
    }
}