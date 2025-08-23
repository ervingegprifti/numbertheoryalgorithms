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

            List<List<RowItem>> rows = new ArrayList<>();

            BigInteger maxX = d;
            if (b.compareTo(ZERO) != 0) {
                maxX = d.divide(b).add(ONE);
            }

            // TODO This takes forever when c = 0. Look at it later.
            if (c.compareTo(ZERO) == 0) {
                return null;
            }

            // Create header row.
            List<RowItem> headerRow = new ArrayList<>();
            RowItem headerItem = new RowItem(true, "d", false);
            headerRow.add(headerItem);
            for (BigInteger x = ZERO; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                headerRow.add(new RowItem(true, "x=" + x, false));
            }
            headerRow.add(new RowItem(true, "solutions", false));
            rows.add(headerRow);

            // Calculate solutions from 0 up until d
            for(BigInteger i = ZERO; i.compareTo(d) <= 0; i = i.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                List<RowItem> rowItems = new ArrayList<>();
                // Add row header
                //RowItem item = new RowItem(true, i.toString(), false, i.equals(f) ? RowItem.HeaderDisplay.HIGHLIGHTED : RowItem.HeaderDisplay.DEFAULT);
                RowItem item = new RowItem(true, i.toString(), false);
                rowItems.add(item);
                // Add items
                for (BigInteger x = ZERO; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();

                    String resultXFixed = getBQFSolutionXFixed(a, b, c, i, x);
                    rowItems.add(new RowItem(false, resultXFixed, false, (resultXFixed == null || resultXFixed.isEmpty()) ? RowItem.ValueStyle.DEFAULT : RowItem.ValueStyle.YELLOW));
                }
                // Last column values.
                String resultSQFRun1 = runBQF1(a, b, c, i);
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


    private String runBQF1(BigInteger a, BigInteger b, BigInteger c, BigInteger d) throws InterruptedException {
        // Given axy+bx+cy=d, multiply both sides with a then we have, a²xy+abx+acy=ad
        BigInteger ad = d.multiply(a);
        // Add bc to both sides then, a²xy+adx+acy+bc=ad+bc
        BigInteger bc = b.multiply(c);
        // Let n=ad+bc, then the RHS can be written as n=pq
        BigInteger n = ad.add(bc);
        // So we must solve (ax+c)(ay+b)=n

        // Factoring n into pq pairs
        List<Pair<BigInteger, BigInteger>> pairFactors = AlgorithmHelper.getPairFactors(n, true);

        int pairFactorsSize = pairFactors.size();
        // We expect pairFactorsSize to be 0, 4, 8, 12, 16, 20, 24,...
        if (pairFactorsSize == 0) {
            // No factors were found, hence no solutions. n is prime
            return "";
        }

        // Checking (ax+c)=p & (ay+b)=q per each (p, q) pairs will give (x, y) solutions, if any
        StringBuilder sb = new StringBuilder();
        List<Solution> solutions = AlgorithmHelper.calculateBQFSolutions(a,b,c,pairFactors, true, false);
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


    static String getBQFSolutionXFixed(BigInteger a, BigInteger b, BigInteger c, BigInteger d, BigInteger x) throws InterruptedException {
        BigInteger y = ZERO;
        BigInteger result = (a.multiply(x).multiply(y)).add((b.multiply(x))).add((c.multiply(y)));
        while (result.compareTo(d) < 0) {
            AlgorithmHelper.checkIfCanceled();

            y = y.add(ONE);
            result = (a.multiply(x).multiply(y)).add((b.multiply(x))).add((c.multiply(y)));
        }
        if (result.compareTo(d) == 0) {
            return "" + x + ", " + y + "";
        } else {
            return "";
        }
    }
}