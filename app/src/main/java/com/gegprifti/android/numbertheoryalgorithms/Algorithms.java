package com.gegprifti.android.numbertheoryalgorithms;


import android.util.Log;
import android.util.Pair;

import com.gegprifti.android.numbertheoryalgorithms.algorithms.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.common.Tabular;
import static com.gegprifti.android.numbertheoryalgorithms.common.Helper.NP;
import static com.gegprifti.android.numbertheoryalgorithms.common.Helper.getSign;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


class Algorithms {
    private final static String TAG = Algorithms.class.getSimpleName();

    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private static final BigInteger THREE = BigInteger.valueOf(3L);
    private static final BigInteger FOUR = BigInteger.valueOf(4L);


    private static final String COLOR = "#8C5900";
    // https://www.htmlsymbols.xyz/unicode
    private static final String STOP = "■";
    private static final String STOP_SPACED = " ■ ";
    private static final String STOP_COLORED = "<font color='#8C5900'>■</font>";
    private static final String RIGHT_ARROW = "➡";
    private static final String RIGHT_ARROW_SPACED = " ➡ ";
    private static final String RIGHT_ARROW_COLORED = "<font color='#8C5900'>➡</font>";
    private static final String LEFT_RIGHT_ARROW = "<b>⬌</b>";
    private static final String LEFT_RIGHT_ARROW_SPACED = " <b>⬌</b> ";
    private static final String LEFT_RIGHT_ARROW_COLORED = "<font color='#8C5900'><b>⬌</b></font>";
    private static final String TAB = ""; // "\t" // TODO Useless, remove it later.
    private static final String BULLET = ""; // "•"; // TODO Useless, remove it later.
    private static final String SUP2 = "<sup><small><small>2</small></small></sup>";
    private static final String NBSP = "&nbsp;";





    //region QUADRATIC FORM
    static String QuadraticFormRun(ProgressDialog.Run run, AlgorithmParameters algPrm) {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger b = algPrm.getInput1();
            BigInteger d = algPrm.getInput2();
            BigInteger e = algPrm.getInput3();
            BigInteger f = algPrm.getInput4();
            boolean includeTrivialSolutions = algPrm.getIncludeTrivialSolutions();
            boolean includeOnlyPositiveSolutions = algPrm.getIncludeOnlyPositiveSolutions();
            boolean includeOnlyNegativeSolutions = algPrm.getIncludeOnlyNegativeSolutions();

            // Simple Quadratic Form
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Simple Quadratic Form</b></font><br>", COLOR));

            // Solve for x,y such as bxy+dx+ey=f where b,d,e,f,x,y ∊ ℤ and b ≠ 0
            result.append("Solve for <b>x</b>,<b>y</b> such as b<b>x</b><b>y</b>+d<b>x</b>+e<b>y</b>=f where b,d,e,f,<b>x</b>,<b>y</b> ∊ ℤ and b ≠ 0<br>");
            result.append(String.format(Locale.getDefault(), "%s<b>x</b><b>y</b>+%s<b>x</b>+%s<b>y</b>=%s<br>", NP(b), NP(d), NP(e), NP(f)));
            result.append("<br>");

            if (includeTrivialSolutions) {
                result.append("Trivial solutions included<br>" );
            } else {
                result.append("Trivial solutions not included<br>");
            }
            if (includeOnlyPositiveSolutions && !includeOnlyNegativeSolutions) {
                // Include only positive solutions.
                result.append("Only positive solutions included<br>");
            } else if (!includeOnlyPositiveSolutions && includeOnlyNegativeSolutions) {
                // Include only negative solutions.
                result.append("Only negative solutions included<br>");
            } else {
                // Include positive and negative solutions.
                result.append("Positive and negative solutions included<br>");
            }
            result.append("<br>");

            // Input
            result.append(String.format("<font color='%s'>Input</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "b = %s<br>", NP(b)));
            result.append(String.format(Locale.getDefault(), "d = %s<br>", NP(d)));
            result.append(String.format(Locale.getDefault(), "e = %s<br>", NP(e)));
            result.append(String.format(Locale.getDefault(), "f = %s<br>", NP(f)));
            result.append("<br>");

            // Multiply both sides with b then, b²xy+bdx+bey=bf
            result.append(String.format("<font color='%s'>Multiply both sides with b then, b²<b>x</b><b>y</b>+bd<b>x</b>+be<b>y</b>=bf</font><br>", COLOR));
            BigInteger bb = b.multiply(b);
            BigInteger bd = d.multiply(b);
            BigInteger be = e.multiply(b);
            BigInteger bf = f.multiply(b);
            result.append(String.format(Locale.getDefault(), "%s<b>x</b><b>y</b>+%s<b>x</b>+%s<b>y</b>=%s<br>", NP(bb), NP(bd), NP(be), NP(bf)));
            result.append("<br>");

            // Add de to both sides then, b²xy+bdx+bey+de=bf+de
            result.append(String.format("<font color='%s'>Add de to both sides then, b²<b>x</b><b>y</b>+bd<b>x</b>+be<b>y</b>+de=bf+de</font><br>", COLOR));
            BigInteger de = d.multiply(e);
            result.append(String.format(Locale.getDefault(), "%s<b>x</b><b>y</b>+%s<b>x</b>+%s<b>y</b>+%s=%s+%s<br>", NP(bb), NP(bd), NP(be), NP(de), NP(bf), NP(de)));
            result.append("<br>");

            // The LHS is of the form of (bx+e)(by+d)
            result.append(String.format("<font color='%s'>The LHS is of the form of (b<b>x</b>+e)(b<b>y</b>+d)</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "(%s<b>x</b>+%s)(%s<b>y</b>+%s)<br>", NP(b), NP(e), NP(b), NP(d)));
            result.append("<br>");

            // Let n=bf+de, then the RHS can be written as n=pq
            result.append(String.format("<font color='%s'>Let n=bf+de, then the RHS can be written as</font><br>", COLOR));
            BigInteger n = bf.add(de);
            result.append(String.format(Locale.getDefault(), "n=%s<br>", NP(n)));
            result.append("<br>");

            // So we must solve (bx+e)(by+d)=n
            result.append(String.format("<font color='%s'>So we must solve (b<b>x</b>+e)(b<b>y</b>+d)=n</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "(%s<b>x</b>+%s)(%s<b>y</b>+%s)=%s<br>", NP(b), NP(e), NP(b), NP(d), NP(n)));
            result.append("<br>");

            // Factoring n into pq pairs using Trial Division
            result.append(String.format("<font color='%s'>Factoring n into pq pairs using Trial Division</font><br>", COLOR));
            List<Pair<BigInteger, BigInteger>> pairFactors = BigMath.PairFactors(run, n, includeTrivialSolutions);
            if (pairFactors == null) {
                return null;
            }
            int pairFactorsSize = pairFactors.size();
            // We expect pairFactorsSize to be 0, 4, 8, 12, 16, 20, 24,...
            if (pairFactorsSize >= 4) {
                if (pairFactorsSize % 4 == 0) {
                    for (int i = 0; i < pairFactorsSize; i += 4) {
                        // Check if the run is canceled
                        if (run.isCancelled()) { return null; }

                        Pair<BigInteger, BigInteger> pairFactor0 = pairFactors.get(i);
                        Pair<BigInteger, BigInteger> pairFactor1 = pairFactors.get(i+1);
                        Pair<BigInteger, BigInteger> pairFactor2 = pairFactors.get(i+2);
                        Pair<BigInteger, BigInteger> pairFactor3 = pairFactors.get(i+3);
                        BigInteger p0 = pairFactor0.first;
                        BigInteger q0 = pairFactor0.second;
                        BigInteger p1 = pairFactor1.first;
                        BigInteger q1 = pairFactor1.second;
                        BigInteger p2 = pairFactor2.first;
                        BigInteger q2 = pairFactor2.second;
                        BigInteger p3 = pairFactor3.first;
                        BigInteger q3 = pairFactor3.second;
                        result.append(String.format(Locale.getDefault(), "[%s, %s] [%s, %s] [%s, %s] [%s, %s]<br>", NP(p0), NP(q0), NP(p1), NP(q1), NP(p2), NP(q2), NP(p3), NP(q3)));
                    }
                }
                result.append("<br>");
            } else {
                // No factors were found, hence no solutions. n is prime
                result.append("No factors were found, hence no solutions. n is prime");
                return result.toString();
            }

            // Checking (bx+e)=p and (by+d)=q per each (p,q) pairs will give (x,y) solutions if any
            result.append(String.format("<font color='%s'>Checking (b<b>x</b>+e)=p and (b<b>y</b>+d)=q per each (p,q) pairs will give (<b>x</b>,<b>y</b>) solutions if any</font><br>", COLOR));
            List<Solution> solutions = SQFSolutions(run,b,d,e,pairFactors, includeOnlyPositiveSolutions, includeOnlyNegativeSolutions);
            if (solutions == null) {
                return null;
            }
            if (solutions.size() > 0) {
                for(int i = 0; i < solutions.size(); i++) {
                    // Check if the run is canceled
                    if (run.isCancelled()) { return null; }

                    Solution solution = solutions.get(i);
                    BigInteger p = solution.getP();
                    BigInteger q = solution.getQ();
                    BigInteger x = solution.getX();
                    BigInteger y = solution.getY();
                    result.append(String.format(Locale.getDefault(), "[%s, %s] %s [%s, %s]<br>", NP(p), NP(q), RIGHT_ARROW_COLORED, NP(x), NP(y)));
                }
            } else {
                result.append("No solutions were found");
                return result.toString();
            }
            result.append("<br>");

            // Solutions
            result.append(String.format("<font color='%s'>Solutions</font><br>", COLOR));
            for (int i = 0; i < solutions.size(); i++) {
                // Check if the run is canceled
                if (run.isCancelled()) { return null; }

                Solution solution = solutions.get(i);
                BigInteger x = solution.getX();
                BigInteger y = solution.getY();
                if (i != solutions.size() - 1) {
                    result.append(String.format(Locale.getDefault(), "[%s, %s]<br>", NP(x), NP(y)));
                } else {
                    result.append(String.format(Locale.getDefault(), "[%s, %s]", NP(x), NP(y)));
                }
            }

            return result.toString();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return ex.toString();
        }
    }
    static List<List<RowItem>> QuadraticFormRun1(ProgressDialog.Run run, AlgorithmParameters algPrm) {
        try {
            BigInteger b = algPrm.getInput1();
            BigInteger d = algPrm.getInput2();
            BigInteger e = algPrm.getInput3();
            BigInteger f = algPrm.getInput4();

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
                // Check if the run is canceled
                if (run.isCancelled()) { return null; }

                headerRow.add(new RowItem(true, "x=" + x, false));
            }
            headerRow.add(new RowItem(true, "solutions", false));
            rows.add(headerRow);

            // Calculate solutions from 0 up until f
            for(BigInteger i = ZERO; i.compareTo(f) <= 0; i = i.add(ONE)) {
                // Check if the run is canceled
                if (run.isCancelled()) { return null; }

                List<RowItem> rowItems = new ArrayList<>();
                // Add row header
                //RowItem item = new RowItem(true, i.toString(), false, i.equals(f) ? RowItem.HeaderDisplay.HIGHLIGHTED : RowItem.HeaderDisplay.DEFAULT);
                RowItem item = new RowItem(true, i.toString(), false);
                rowItems.add(item);
                // Add items
                for (BigInteger x = ZERO; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                    // Check if the run is canceled
                    if (run.isCancelled()) { return null; }

                    String resultXFixed = SQFSolutionXFixed(run, b, d, e, i, x);
                    rowItems.add(new RowItem(false, resultXFixed, false, (resultXFixed == null || resultXFixed.isEmpty()) ? RowItem.ValueStyle.DEFAULT : RowItem.ValueStyle.YELLOW));
                }
                // Last column values.
                String resultSQFRun1 = SQFRun1(run, b, d, e, i);
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
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return null;
        }
    }
    static List<List<RowItem>> QuadraticFormRun2(ProgressDialog.Run run, AlgorithmParameters algPrm) {
        try {
            BigInteger a = algPrm.getInput1();
            BigInteger b = algPrm.getInput2();
            BigInteger c = algPrm.getInput3();
            BigInteger d = algPrm.getInput4();
            BigInteger e = algPrm.getInput5();
            BigInteger f = algPrm.getInput6();

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
                // Check if the run is canceled
                if (run.isCancelled()) { return null; }

                headerRow.add(new RowItem(true, "x=" + x, false));
            }
            rows.add(headerRow);

            // Calculate ax² + bxy + cy² + dx + ey = f values.
            for (BigInteger y = minY; y.compareTo(maxY) <= 0; y = y.add(ONE)) {
                // Check if the run is canceled
                if (run.isCancelled()) { return null; }

                List<RowItem> row = new ArrayList<>();
                // Add column header.
                // boolean isYPrime = y.isProbablePrime(10);
                row.add(new RowItem(true, "y=" + y, false)); // isYPrime

                // Add f values.
                for(BigInteger x = minX; x.compareTo(maxX) <= 0; x = x.add(ONE)) {
                    // Check if the run is canceled
                    if (run.isCancelled()) { return null; }

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
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return null;
        }
    }
    static List<Solution> SQFSolutions (ProgressDialog.Run run, BigInteger b, BigInteger d, BigInteger e, List<Pair<BigInteger, BigInteger>> pairFactors, boolean includeOnlyPositiveSolutions, boolean includeOnlyNegativeSolutions) {
        List<Solution> solutions = new ArrayList<>();
        for (int i = 0; i < pairFactors.size(); i++) {
            // Check if the run is canceled
            if (run.isCancelled()) { return null; }

            Pair<BigInteger, BigInteger> pairFactor = pairFactors.get(i);
            BigInteger p = pairFactor.first;
            BigInteger q = pairFactor.second;
            boolean solForX = false;
            boolean solForY = false;
            BigInteger[] solX = p.subtract(e).divideAndRemainder(b);
            BigInteger[] solY = q.subtract(d).divideAndRemainder(b);
            BigInteger x = solX[0];
            BigInteger y = solY[0];
            if(solX[1].compareTo(ZERO) == 0) {
                solForX = true;
            }
            if(solY[1].compareTo(ZERO) == 0) {
                solForY = true;
            }
            if (solForX && solForY) {
                if (includeOnlyPositiveSolutions && !includeOnlyNegativeSolutions) {
                    // Include only positive solutions.
                    if (x.compareTo(ZERO) >= 0 && y.compareTo(ZERO) >= 0) {
                        Solution solution = new Solution(p, q, x, y);
                        solutions.add(solution);
                    }
                } else if (!includeOnlyPositiveSolutions && includeOnlyNegativeSolutions) {
                    // Include only negative solutions.
                    if (x.compareTo(ZERO) < 0 && y.compareTo(ZERO) < 0) {
                        Solution solution = new Solution(p, q, x, y);
                        solutions.add(solution);
                    }
                } else {
                    // Include positive and negative solutions.
                    Solution solution = new Solution(p, q, x, y);
                    solutions.add(solution);
                }
            }
        }
        return solutions;
    }
    static String SQFRun1(ProgressDialog.Run run, BigInteger b, BigInteger d, BigInteger e, BigInteger f) {
        // Multiply both sides with b then, b²xy+bdx+bey=bf
        BigInteger bf = f.multiply(b);
        // Add de to both sides then, b²xy+bdx+bey+de=bf+de
        BigInteger de = d.multiply(e);
        // Let n=bf+de, then the RHS can be written as n=pq
        BigInteger n = bf.add(de);
        // So we must solve (bx+e)(by+d)=n

        // Factoring n into pq pairs
        List<Pair<BigInteger, BigInteger>> pairFactors = BigMath.PairFactors(run, n, true);
        if (pairFactors == null) {
            return "";
        }

        int pairFactorsSize = pairFactors.size();
        // We expect pairFactorsSize to be 0, 4, 8, 12, 16, 20, 24,...
        if (pairFactorsSize == 0) {
            // No factors were found, hence no solutions. n is prime
            return "";
        }

        // Checking (bx+e)=p & (by+d)=q per each (p, q) pairs will give (x, y) solutions, if any
        StringBuilder sb = new StringBuilder();
        List<Solution> solutions = SQFSolutions(run,b,d,e,pairFactors, true, false);
        if (solutions == null) {
            return "";
        }
        if (solutions.size() > 0) {
            for(int i = 0; i < solutions.size(); i++) {
                // Check if the run is canceled
                if (run.isCancelled()) { return null; }

                Solution solution = solutions.get(i);
                BigInteger x = solution.getX();
                BigInteger y = solution.getY();
                if (sb.toString().isEmpty()) {
                    sb.append(String.format(Locale.getDefault(), "[%s, %s]", NP(x), NP(y)));
                } else {
                    sb.append(String.format(Locale.getDefault(), " [%s, %s]", NP(x), NP(y)));
                }
            }
        } else {
            // No solutions were found.
            return "";
        }

        return sb.toString();
    }
    static String SQFSolutionXFixed (ProgressDialog.Run run, BigInteger b, BigInteger d, BigInteger e, BigInteger f, BigInteger x) {
            BigInteger y = ZERO;
            BigInteger result = (b.multiply(x).multiply(y)).add((d.multiply(x))).add((e.multiply(y)));
            while (result.compareTo(f) < 0) {
                // Check if the run is canceled
                if (run.isCancelled()) { return null; }

                y = y.add(ONE);
                result = (b.multiply(x).multiply(y)).add((d.multiply(x))).add((e.multiply(y)));
            }
            if (result.compareTo(f) == 0) {
                return "" + x + ", " + y + "";
            } else {
                return "";
            }
    }
    //endregion QUADRATIC FORM






























































































    //region PRIMES LIST
    static List<List<RowItem>> PrimesList(ProgressDialog.Run run, AlgorithmParameters algorithmParameters) {
        try {
            int maxOfNumbers = algorithmParameters.getInput1().intValue();
            int nrOfColumns = algorithmParameters.getInput2().intValue();

            List<List<RowItem>> rows = new ArrayList<>();
            List<RowItem> row;

            // Create the column headers
            row = new ArrayList<>();
            String columnLabel = nrOfColumns + "k+";
            for(int c = -1; c < nrOfColumns; c++) {
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
            int nrOfRows = (int)Math.ceil((double) (maxOfNumbers/nrOfColumns)) + 1 ;
            for (int k = 0; k < nrOfRows; k++) {
                // Check if the run is canceled
                if (run.isCancelled()) {
                    return null;
                }
                // Start a new row
                row = new ArrayList<>();
                for(int c = -1; c < nrOfColumns; c++) {
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
                        int number = (nrOfColumns*k)+c;
                        boolean isPrime = BigInteger.valueOf((long)number).isProbablePrime(10);
                        RowItem rowItem = new RowItem(false, Integer.toString(number), isPrime, isPrime ? RowItem.ValueStyle.YELLOW : RowItem.ValueStyle.DEFAULT);
                        row.add(rowItem);
                    }
                }
                rows.add(row);
            }

            return rows;
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return null;
        }
    }
    //endregion PRIMES LIST

}

