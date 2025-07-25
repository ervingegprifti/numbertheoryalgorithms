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















































    //region LINEAR DIOPHANTINE EQUATION IN TWO VARIABLES
    static String LinearDiophantineEquation(ProgressDialog.Run run, AlgorithmParameters algorithmParameters) {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger c = algorithmParameters.getInput3();
            boolean showResultInMonospace = algorithmParameters.getShowResultInMonospace();

            // Linear Diophantine Equation In Two Variables
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Linear Diophantine Equation In Two Variables</b></font><br>", COLOR));

            // Solve for x,y such as ax+by=c where a,b,c,x,y ∊ ℤ with a,b ≠ 0
            result.append("Solve for <b>x</b>,<b>y</b> such as a<b>x</b>+b<b>y</b>=c where a,b,c,<b>x</b>,<b>y</b> ∊ ℤ with a,b ≠ 0<br>");
            result.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b>=%s <br>", NP(a), NP(b), NP(c)));
            result.append("<br>");

            // Input
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Input</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "a = %s <br>", a));
            result.append(String.format(Locale.getDefault(), "b = %s <br>", b));
            result.append(String.format(Locale.getDefault(), "c = %s <br>", c));
            result.append("<br>");

            // Check a,b ≠ 0
            if (a.compareTo(ZERO) == 0) {
                result.append(String.format(Locale.getDefault(), "<font color='%s'>Check a ≠ 0</font><br>", COLOR));
                result.append("a should not be 0. <br>");
                result.append("Stop.");
            }
            if (b.compareTo(ZERO) == 0) {
                result.append(String.format(Locale.getDefault(), "<font color='%s'>Check b ≠ 0</font><br>", COLOR));
                result.append("b should not be 0. <br>");
                result.append("Stop.");
            }

            BigInteger g = a.gcd(b);

            // Check solubility
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Check solubility</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "g = GCD(a, b) = GCD(%s, %s) = %s <br>", a, b, g));
            if (c.mod(g).compareTo(BigInteger.ZERO) > 0) {
                result.append("Not soluble as g ∤ c, hence there is no integer solution<br>");
                result.append("Stop.");
                return  result.toString();
            } else {
                result.append("Soluble as g ∣ c, hence there are infinitely many integer solutions<br>");
                result.append("Continue...<br>");
                result.append("<br>");
            }

            BigInteger[] EE = AlgorithmHelper.EEA(a.abs(), b.abs());
            BigInteger xee = getSign(a).multiply(EE[1]);
            BigInteger yee = getSign(b).multiply(EE[2]);

            // Use Extended Euclidean Algorithm to find xₑₑ and yₑₑ from |a|x + |b|y = GCD(|a|, |b|) = g
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Use Extended Euclidean Algorithm to find xₑₑ and yₑₑ from |a|<b>x</b> + |b|<b>y</b> = GCD(|a|, |b|) = g </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "xₑₑ = sign(a)·xₑₑ = %s·%s = %s <br>", NP(getSign(a)), NP(EE[1]), xee));
            result.append(String.format(Locale.getDefault(), "yₑₑ = sign(b)·yₑₑ = %s·%s = %s <br>", NP(getSign(b)), NP(EE[2]), yee));
            result.append("<br>");

            BigInteger x0 = (xee.multiply(c.divide(g)));
            BigInteger y0 = (yee.multiply(c.divide(g)));

            // A particular first initial solution is x₀ = xₑₑ(c/g) and y₀ = yₑₑ(c/g)
            result.append(String.format(Locale.getDefault(), "<font color='%s'>A particular first initial solution is x₀ = xₑₑ(c/g) and y₀ = yₑₑ(c/g) </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "x₀ = %s·(%s/%s) = %s <br>", NP(xee), NP(c), NP(g), NP(x0)));
            result.append(String.format(Locale.getDefault(), "y₀ = %s·(%s/%s) = %s <br>", NP(yee), NP(c), NP(g), NP(y0)));
            result.append("<br>");

            // For r ∊ ℤ, The general x,y solution is
            result.append(String.format(Locale.getDefault(), "<font color='%s'>For <b>r</b> ∊ ℤ, the general <b>x</b>,<b>y</b> solution is </font><br>", COLOR));
            result.append("<b>x</b> = x₀ + (b/g)<b>r</b>  <br>");
            result.append("<b>y</b> = y₀ - (a/g)<b>r</b>  <br>");
            result.append("<br>");
            result.append(String.format(Locale.getDefault(), "<b>x</b> = %s + (%s/%s)<b>r</b>  <br>", NP(x0), NP(b), NP(g)));
            result.append(String.format(Locale.getDefault(), "<b>y</b> = %s - (%s/%s)<b>r</b>  <br>", NP(y0), NP(a), NP(g)));
            result.append("<br>");
            BigInteger bDg = b.divide(g);
            BigInteger aDg = a.divide(g);
            result.append(String.format(Locale.getDefault(), "<b>x</b> = %s + %s<b>r</b>  <br>", NP(x0), NP(bDg)));
            result.append(String.format(Locale.getDefault(), "<b>y</b> = %s - %s<b>r</b>  <br>", NP(y0), NP(aDg)));
            result.append("<br>");

            // The Linear Diophantine Equation ax+by=c can be written as
            result.append(String.format(Locale.getDefault(), "<font color='%s'>The Linear Diophantine Equation a<b>x</b>+b<b>y</b>=c can be written as </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s(%s + %s<b>r</b>)+%s(%s - %s<b>r</b>)=c  <br>", NP(a), NP(x0), NP(bDg), NP(b), NP(y0), NP(aDg)));
            result.append("<br>");

            // Check correctness for r = {-3, ..., 3}
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Check correctness for <b>r</b> = {-3, ..., 3} </font><br>", COLOR));
            result.append("⋮<br>");
            Tabular tabular = new Tabular();
            for (BigInteger r = BigInteger.valueOf(-3); r.compareTo(BigInteger.valueOf(3)) < 0; r = r.add(ONE)) {

                BigInteger bDgMr = (bDg).multiply(r);
                BigInteger aDgMr = (aDg).multiply(r);

                BigInteger x = x0.add(bDgMr);
                BigInteger y = y0.subtract(aDgMr);

                BigInteger ax = a.multiply(x);
                BigInteger by = b.multiply(y);
                BigInteger cCalculated = ax.add(by);

                List<String> row = new ArrayList<>();
                row.add(String.format(Locale.getDefault(), "<b>r</b> = %s", NP(r)));
                row.add(NBSP + RIGHT_ARROW_COLORED + NBSP);
                row.add(String.format(Locale.getDefault(), "<b>x</b> = %s + %s·%s", NP(x0), NP(bDg), NP(r)));
                row.add(" = ");
                row.add(String.format(Locale.getDefault(), "%s+%s", NP(x0), NP(bDgMr)));
                row.add(String.format(Locale.getDefault(), " = %s", NP(x)));
                row.add(" and ");
                row.add(String.format(Locale.getDefault(), "<b>y</b> = %s - %s·%s", NP(y0), NP(aDg), NP(r)));
                row.add(" = ");
                row.add(String.format(Locale.getDefault(), "%s-%s", NP(y0), NP(aDgMr)));
                row.add(String.format(Locale.getDefault(), " = %s", NP(y)));
                row.add(NBSP + RIGHT_ARROW_COLORED + NBSP);
                row.add(String.format(Locale.getDefault(),"%s·%s", NP(a), NP(x)));
                row.add(" + ");
                row.add(String.format(Locale.getDefault(),"%s·%s", NP(b), NP(y)));
                row.add(String.format(Locale.getDefault()," = %s", NP(cCalculated)));

                if (showResultInMonospace) {
                    tabular.addRow(row);
                } else {
                    for (String cell : row) {
                        result.append(cell);
                    }
                    result.append("<br>");
                }
            }
            if (showResultInMonospace) {
                result.append(tabular.render());
            }
            result.append("⋮");

            // Just for the sake of canceling, but this has no real effect here
            // Check if the run is canceled
            if (run.isCancelled()) { return null; }

            return result.toString();

        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return ex.toString();
        }
    }
    //endregion LINEAR DIOPHANTINE EQUATION IN TWO VARIABLES


    //region LINEAR CONGRUENCE In One Variable

    /**
     *
     * @param run
     * @param algorithmParameters
     * @return
     *
     * @see <a href="https://math.stackexchange.com/questions/37806/extended-euclidean-algorithm-with-negative-numbers">Extended Euclidean algorithm with negative numbers</a>
     */
    static String LinearCongruenceInOneVariable(ProgressDialog.Run run, AlgorithmParameters algorithmParameters) {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger m = algorithmParameters.getInput3();
            boolean showResultInMonospace = algorithmParameters.getShowResultInMonospace();

            // Linear Congruence In One Variable
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Linear Congruence In One Variable</b></font><br>", COLOR));

            // Solve for x, the linear congruence ax ≡ b (mod m) where a,b,x ∊ ℤ, m ∊ ℕ
            result.append("Solve for <b>x</b>, the linear congruence a<b>x</b> ≡ b (mod m) where a,b,<b>x</b> ∊ ℤ, m ∊ ℕ<br>");
            result.append(String.format(Locale.getDefault(), "%s<b>x</b> ≡ %s (mod %s)<br>", NP(a), NP(b), NP(m)));
            result.append("<br>");

            // Input
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Input </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "a = %s <br>", a));
            result.append(String.format(Locale.getDefault(), "b = %s <br>", b));
            result.append(String.format(Locale.getDefault(), "m = %s <br>", m));
            result.append("<br>");

            BigInteger g = a.gcd(m);

            // Check solubility
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Check solubility </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "g = GCD(a, m) = %s <br>", g));
            if (b.mod(g).compareTo(BigInteger.ZERO) > 0) {
                result.append("Not soluble as g ∤ b<br>");
                result.append("Stop.");
                return  result.toString();
            } else {
                if (g.compareTo(ONE) == 0) {
                    result.append("Soluble as g ∣ b, hence there is a unique solution modulo m<br>");
                    result.append("Continue...<br>");
                    result.append("<br>");
                } else {
                    result.append("Soluble as g ∣ b, hence there are g incongruent solutions modulo m<br>");
                    result.append("Continue...<br>");
                    result.append("<br>");
                }
            }

            BigInteger[] EE = AlgorithmHelper.EEA(a.abs(), m.abs());
            BigInteger xee = getSign(a).multiply(EE[1]);
            //BigInteger yee = getSign(m).multiply(EE[2]);

            // Use Extended Euclidean Algorithm to find xₑₑ from |a|x + |m|y = GCD(|a|, |m|) = g
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Use Extended Euclidean Algorithm to find xₑₑ from |a|<b>x</b> + |m|<b>y</b> = GCD(|a|, |m|) = g</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "xₑₑ = sign(a)·xₑₑ = %s·%s = %s <br>", NP(getSign(a)), NP(EE[1]), xee));
            //result.append(String.format(Locale.getDefault(), TAB + "yₑₑ = sign(m)·yₑₑ = %s·%s = %s <br>", NP(getSign(m)), NP(EE[2]), yee));
            result.append("<br>");

            // The first initial solution is x₀ = xₑₑ(b/g) mod m
            BigInteger x0 = (xee.multiply(b.divide(g))).mod(m);
            result.append(String.format(Locale.getDefault(), "<font color='%s'>The first initial solution is x₀ = xₑₑ(b/g) (mod m) </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "x₀ = %s·(%s/%s) mod %s = %s <br>", NP(xee), NP(b), NP(g), NP(m), NP(x0)));
            result.append("<br>");

            // All initial solutions n = {0, ..., g-1}, xₙ = n(m/g) + x₀ mod m
            result.append(String.format(Locale.getDefault(), "<font color='%s'>All initial solutions for n = {0, ..., g-1} are xₙ = n(m/g) + x₀ (mod m) </font><br>", COLOR));
            List<BigInteger> initialXSolutions = new ArrayList<>();
            for (BigInteger n = ZERO; n.compareTo(g) < 0; n = n.add(ONE)) {
                BigInteger xn = n.multiply(m.divide(g)).add(x0).mod(m);
                initialXSolutions.add(xn);
                result.append(String.format(Locale.getDefault(), "x<sub><small><small>%s</small></small></sub> = %s·(%s/%s) + %s (mod %s) = %s <br>", NP(n), NP(n), NP(m), NP(g), NP(x0), NP(m), NP(xn)));
            }
            result.append("<br>");

            // For r ∊ ℤ, the general x solution is
            result.append(String.format(Locale.getDefault(), "<font color='%s'>For <b>r</b> ∊ ℤ, the general <b>x</b> solution is </font><br>", COLOR));
            result.append("<b>x</b> = m<b>r</b> + xₙ <br>"); // x = mr + xₙ
            result.append(String.format(Locale.getDefault(), "<b>x</b> = %s<b>r</b> + xₙ <br>", m));
            result.append("<br>");

            // The congruence ax ≡ b (mod m) can be written as
            result.append(String.format(Locale.getDefault(), "<font color='%s'>The congruence a<b>x</b> ≡ b (mod m) can be written as </font><br>", COLOR));
            result.append("a(m<b>r</b> + xₙ) ≡ b (mod m) <br>"); // a(mr + xₙ) ≡ b (mod m)
            result.append(String.format(Locale.getDefault(), "%s(%s<b>r</b> + xₙ) ≡ %s (mod %s) <br>", a, m, b, m));
            result.append("<br>");

            // Check correctness for r = {-3, ..., 3}
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Check correctness for <b>r</b> = {-3, ..., 3} </font><br>", COLOR));
            for (int n = 0; n < initialXSolutions.size(); n++) {
                if (n > 0) {
                    result.append("<br><br>");
                }
                BigInteger xn = initialXSolutions.get(n);
                result.append(String.format(Locale.getDefault(), "x<sub><small><small>%s</small></small></sub> = %s <br>", NP(n), NP(xn)));
                result.append(String.format(Locale.getDefault(), "%s(%s<b>r</b> + %s) ≡ %s (mod %s) <br>", a, m, xn, b, m));
                result.append("⋮<br>");
                Tabular tabular = new Tabular();
                for (BigInteger r = BigInteger.valueOf(-3); r.compareTo(BigInteger.valueOf(3)) < 0; r = r.add(ONE)) {
                    BigInteger mMrPxn = m.multiply(r).add(xn);
                    BigInteger aMx = a.multiply(mMrPxn);
                    BigInteger aMxMODm = aMx.mod(m);

                    List<String> row = new ArrayList<>();
                    row.add(String.format(Locale.getDefault(), "<b>r</b> = %s", NP(r)));
                    row.add(NBSP + RIGHT_ARROW_COLORED + NBSP);
                    row.add(String.format(Locale.getDefault(), "<b>x</b> = %s·%s + %s", NP(m), NP(r), NP(xn)));
                    row.add(String.format(Locale.getDefault(), " = %s", NP(mMrPxn)));
                    row.add(NBSP + RIGHT_ARROW_COLORED + NBSP);
                    row.add(String.format(Locale.getDefault(), "%s·%s", NP(a) , NP(mMrPxn)));
                    row.add(String.format(Locale.getDefault(), " = %s", NP(aMx)));
                    row.add(String.format(Locale.getDefault(), " = %s (mod %s)", NP(aMxMODm), NP(m))); // Calculated b

                    if (showResultInMonospace) {
                        tabular.addRow(row);
                    } else {
                        for (String cell : row) {
                            result.append(cell);
                        }
                        result.append("<br>");
                    }
                }
                if (showResultInMonospace) {
                    result.append(tabular.render());
                }
                result.append("⋮");
            }

            // Just for the sake of canceling, but this has no real effect here
            // Check if the run is canceled
            if (run.isCancelled()) { return null; }

            return result.toString();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return ex.toString();
        }
    }
    //endregion LINEAR CONGRUENCE IN ONE VARIABLE


    //region LINEAR CONGRUENCE IN TWO VARIABLES

    /**
     *
     * @param run
     * @param algorithmParameters
     * @return
     *
     * @see <a href="https://math.stackexchange.com/questions/37806/extended-euclidean-algorithm-with-negative-numbers">Extended Euclidean algorithm with negative numbers</a>
     */
    static String LinearCongruenceInTwoVariables(ProgressDialog.Run run, AlgorithmParameters algorithmParameters) {
        // x + y ➡ xPy
        // x - y ➡ xSy
        // x * y ➡ xMy
        // x / y ➡ xDy

        StringBuilder result = new StringBuilder();
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger c = algorithmParameters.getInput3();
            BigInteger m = algorithmParameters.getInput4();
            boolean showResultInMonospace = algorithmParameters.getShowResultInMonospace();

            // Solving linear Congruence ax+by ≡ c (mod m)
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Linear Congruence In Two Variables</b></font><br>", COLOR));
            result.append("Solve for <b>x</b>,<b>y</b> a<b>x</b>+b<b>y</b> ≡ c (mod m) where a,b,c,<b>x</b>,<b>y</b> ∊ ℤ, m ∊ ℕ<br>");
            result.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b> ≡ %s (mod %s)<br>", NP(a), NP(b), NP(c), NP(m)));
            result.append("<br>");

            // Input
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Input</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "a = %s <br>", NP(a)));
            result.append(String.format(Locale.getDefault(), "b = %s <br>", NP(b)));
            result.append(String.format(Locale.getDefault(), "c = %s <br>", NP(c)));
            result.append(String.format(Locale.getDefault(), "m = %s <br>", NP(m)));
            result.append("<br>");

            // g = GCD(a, b, m)
            BigInteger g = a.gcd(b).gcd(m);

            // Check solubility
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Check solubility</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "g = GCD(a, b, m) = %s <br>", g));
            if (c.mod(g).compareTo(BigInteger.ZERO) > 0) {
                result.append(TAB + "Not soluble as g ∤ c<br>");
                result.append(TAB + "Stop.");
                return  result.toString();
            } else {
                // result.append(TAB + "Soluble as g ∣ c, hence there are gm²⁻¹ incongruent solutions modulo m<br>");
                result.append(TAB + "Soluble as g ∣ c<br>");
                result.append(TAB + "Continue...<br>");
                result.append("<br>");
            }

            BigInteger h = a.gcd(b);
            BigInteger d = a.divide(h);
            BigInteger e = b.divide(h);

            m = m.negate(); // Experimental

            // The Congruence ax+by ≡ c (mod m) ⬌ ax+by=mz+c ⬌ ax+by+mz=c
            result.append(String.format(Locale.getDefault(), "<font color='%s'>The Congruence a<b>x</b>+b<b>y</b> ≡ c (mod m) %s a<b>x</b>+b<b>y</b>=m<b>z</b>+c %s a<b>x</b>+b<b>y</b>+m<b>z</b>=c</font><br>", COLOR, LEFT_RIGHT_ARROW, LEFT_RIGHT_ARROW));
            result.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b>+%s<b>z</b>=%s<br>", NP(a), NP(b), NP(m), NP(c)));
            result.append("<br>");

            // Let h=GCD(a,b), d=a/h, e=b/h
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Let h=GCD(a,b), d=a/h, e=b/h</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "h = GCD(a, b) = GCD(%s, %s) = %s <br>", NP(a), NP(b), NP(h)));
            result.append(String.format(Locale.getDefault(), "d = a/h = %s/%s = %s <br>", NP(a), NP(h), NP(d)));
            result.append(String.format(Locale.getDefault(), "e = b/h = %s/%s = %s <br>", NP(b), NP(h), NP(e)));
            result.append("<br>");

            // Factoring out ax+by we get h(dx+ey)+mz=c
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Factoring out a<b>x</b>+b<b>y</b> we get h(d<b>x</b>+e<b>y</b>)+m<b>z</b>=c</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s(%s<b>x</b>+%s<b>y</b>)+%s<b>z</b>=%s<br>", NP(h), NP(d), NP(e), NP(m), NP(c)));
            result.append("<br>");

            // Note that GCD(d,e) is always 1, since d = a/h and e = b/h
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Note that GCD(d,e) is always 1, since d = a/h and e = b/h</font><br>", COLOR));
            result.append("<br>");

            // Let dx+ey=w
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Let d<b>x</b>+e<b>y</b>=<b>w</b></font> <br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b>=<b>w</b> <br>", NP(d), NP(e)));
            result.append("<br>");

            // Rewriting we must solve hw+mz=c
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Rewriting we must solve h<b>w</b>+m<b>z</b>=c</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s<b>w</b>+%s<b>z</b>=%s<br>", NP(h), NP(m), NP(c)));
            result.append("<br>");

            BigInteger i = h.gcd(m).gcd(c);

            // Simplify hw+mz=c by dividing both sides with i=GCD(h,m,c) to get jw+nz=f
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Simplify h<b>w</b>+m<b>z</b>=c by dividing both sides with i=GCD(h,m,c) to get j<b>w</b>+n<b>z</b>=f </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "i = GCD(h,m,c) = GCD(%s, %s, %s) = %s <br>", h, m, c, i));

            // Simplified
            BigInteger j = h.divide(i);
            BigInteger n = m.divide(i);
            BigInteger f = c.divide(i);

            result.append(String.format(Locale.getDefault(), "j = h/i = %s/%s = %s <br>", NP(h), i, NP(j)));
            result.append(String.format(Locale.getDefault(), "n = m/i = %s/%s = %s <br>", NP(m), i, NP(n)));
            result.append(String.format(Locale.getDefault(), "f = c/i = %s/%s = %s <br>", NP(c), i, NP(f)));
            result.append(String.format(Locale.getDefault(), "%s<b>w</b>+%s<b>z</b>=%s<br>", NP(j), NP(n), NP(f)));
            result.append("<br>");

            BigInteger k = j.gcd(n);

            // Check jw+nz=f solubility
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Check j<b>w</b>+n<b>z</b>=f solubility</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "k = GCD(j, n) = GCD(%s, %s) = %s <br>", j, n, k));
            if (f.mod(k).compareTo(BigInteger.ZERO) > 0) {
                result.append("Not soluble as k ∤ f, hence there is no integer solution <br>");
                result.append("Stop.");
                return  result.toString();
            } else {
                result.append("Soluble as k ∣ f, hence there are infinitely many integer solutions <br>");
                result.append("Continue...<br>");
                result.append("<br>");
            }

            BigInteger[] eeaForWandZ = AlgorithmHelper.EEA(j.abs(), n.abs());
            BigInteger wee = getSign(j).multiply(eeaForWandZ[1]);
            BigInteger zee = getSign(n).multiply(eeaForWandZ[2]);

            // Use Extended Euclidean Algorithm to find wₑₑ and zₑₑ from |j|w + |n|z = GCD(|j|, |n|) = k
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Use Extended Euclidean Algorithm to find wₑₑ and zₑₑ from |j|<b>w</b> + |n|<b>z</b> = GCD(|j|, |n|) = k </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "wₑₑ = sign(j)·wₑₑ = %s·%s = %s <br>", NP(getSign(j)), NP(eeaForWandZ[1]), wee));
            result.append(String.format(Locale.getDefault(), "zₑₑ = sign(n)·zₑₑ = %s·%s = %s <br>", NP(getSign(n)), NP(eeaForWandZ[2]), zee));
            result.append("<br>");

            BigInteger w0 = (wee.multiply(f.divide(k)));
            BigInteger z0 = (zee.multiply(f.divide(k)));

            // A particular first initial solution is w₀ = wₑₑ(f/k) and z₀ = zₑₑ(f/k)
            result.append(String.format(Locale.getDefault(), "<font color='%s'>A particular first initial solution is w₀ = wₑₑ(f/k) and z₀ = zₑₑ(f/k) </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "w₀ = %s·(%s/%s) = %s <br>", NP(wee), NP(f), NP(k), NP(w0)));
            result.append(String.format(Locale.getDefault(), "z₀ = %s·(%s/%s) = %s <br>", NP(zee), NP(f), NP(k), NP(z0)));
            result.append("<br>");

            BigInteger p = n.divide(k);
            BigInteger q = j.divide(k);

            // For r ∊ ℤ, the general solution to jw+nz=f is w = w₀ + (n/k)r and z = z₀ - (j/k)r
            result.append(String.format(Locale.getDefault(), "<font color='%s'>For r ∊ ℤ, the general solution to j<b>w</b>+n<b>z</b>=f is</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<b>w</b> = %s + (%s/%s)r<br>", NP(w0), NP(n), NP(k)));
            result.append(String.format(Locale.getDefault(), "<b>z</b> = %s - (%s/%s)r<br>", NP(z0), NP(j), NP(k)));
            result.append("<br>");

            // Let p=(n/k) and q=(j/k), hence the general solution to jw+nz=f is w = w₀ + (n/k)r = w₀ + pr and z = z₀ - (j/k)r = z₀ - qr
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Let p=(n/k) and q=(j/k), hence the general solution to j<b>w</b>+n<b>z</b>=f is</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>w</b> = w₀ + (n/k)r = w₀ + p<b>r</b> </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>z</b> = z₀ - (j/k)r = z₀ - q<b>r</b> </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<b>w</b> = %s + %s<b>r</b> <br>", NP(w0), NP(p)));
            result.append(String.format(Locale.getDefault(), "<b>z</b> = %s - %s<b>r</b> <br>", NP(z0), NP(q)));
            result.append("<br>");

            // Substituting for w, then we have  dx+ey = w₀+pr
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Substituting for <b>w</b>, then d<b>x</b>+e<b>y</b> = w₀+p<b>r</b></font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b> = %s+%s<b>r</b> <br>", NP(d), NP(e), NP(w0), NP(p)));
            result.append("<br>");

            // Since GCD(d,e) is always 1, then we find x₀ and y₀ by solving dx+ey=1
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Since GCD(d,e) is always 1, then we find x₀ and y₀ by solving d<b>x</b>+<b>e</b>y=1</font><br>", COLOR));
            result.append("<br>");

            BigInteger[] eeaForXandY = AlgorithmHelper.EEA(d.abs(), e.abs());
            BigInteger xee = getSign(d).multiply(eeaForXandY[1]);
            BigInteger yee = getSign(e).multiply(eeaForXandY[2]);

            // Use Extended Euclidean Algorithm to find xₑₑ and yₑₑ from |d|x + |e|y = GCD(|d|, |e|) = 1
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Use Extended Euclidean Algorithm to find xₑₑ and yₑₑ from |d|<b>x</b> + |e|<b>y</b> = GCD(|d|, |e|) = 1</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "xₑₑ = sign(d)·xₑₑ = %s·%s = %s <br>", NP(getSign(d)), NP(eeaForXandY[1]), xee));
            result.append(String.format(Locale.getDefault(), "yₑₑ = sign(e)·yₑₑ = %s·%s = %s <br>", NP(getSign(e)), NP(eeaForXandY[2]), yee));
            result.append("<br>");

            // Hence dxₑₑ+eyₑₑ = 1
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Hence dxₑₑ+eyₑₑ = 1</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "dxₑₑ+eyₑₑ = %s·%s+%s·%s = 1 <br>", NP(d), NP(xee), NP(e), NP(yee)));
            result.append("<br>");

            // Multiplying both sides with w₀+pr = w we have dxₑₑ(w₀+pr)+eyₑₑ(w₀+pr) = w
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Multiplying both sides with w₀+p<b>r</b> we have</font><br>", COLOR));
            // dxₑₑ(w₀+pr)+eyₑₑ(w₀+pr) = w
            result.append(String.format(Locale.getDefault(), "<font color='%s'>dxₑₑ(w₀+p<b>r</b>)+eyₑₑ(w₀+p<b>r</b>) = w₀+p<b>r</b></font><br>", COLOR));
            result.append("<br>");

            BigInteger xeew0 = xee.multiply(w0);
            BigInteger xeep = xee.multiply(p);

            BigInteger yeew0 = yee.multiply(w0);
            BigInteger yeep = yee.multiply(p);

            // Hence x₀ and y₀ are x₀ = xₑₑ(w₀+pr) = xₑₑw₀+xₑₑpr and y₀ = yₑₑ(w₀+pr) = yₑₑw₀+yₑₑpr
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Hence x₀ and y₀ are</font><br>", COLOR));
            result.append("x₀ = xₑₑ(w₀+p<b>r</b>) = xₑₑw₀+xₑₑp<b>r</b> <br>");
            result.append("y₀ = yₑₑ(w₀+p<b>r</b>) = yₑₑw₀+yₑₑp<b>r</b> <br>");
            result.append("<br>");
            result.append(String.format(Locale.getDefault(), "x₀ = %s·%s+%s·%s<b>r</b> = %s + %s<b>r</b><br>", NP(xee), NP(w0), NP(xee), NP(p), NP(xeew0), NP(xeep)));
            result.append(String.format(Locale.getDefault(), "y₀ = %s·%s+%s·%s<b>r</b> = %s + %s<b>r</b><br>", NP(yee), NP(w0), NP(yee), NP(p), NP(yeew0), NP(yeep)));
            result.append("<br>");

            // The general x,y solution is x = x₀ + (e/1)t = xₑₑw₀+xₑₑpr + et and y = y₀ - (d/1)t = yₑₑw₀+yₑₑpr - dt
            result.append(String.format(Locale.getDefault(), "<font color='%s'>The general <b>x</b>,<b>y</b> solution is</font><br>", COLOR));
            result.append("<b>x</b> = x₀ + (e/1)t = xₑₑw₀+xₑₑp<b>r</b> + e<b>t</b> <br>");
            result.append("<b>y</b> = y₀ - (d/1)t = yₑₑw₀+yₑₑp<b>r</b> - d<b>t</b> <br>");
            result.append("<br>");
            result.append(String.format(Locale.getDefault(), "<b>x</b> = %s+%s<b>r</b> + %s<b>t</b> <br>", NP(xeew0), NP(xeep), NP(e)));
            result.append(String.format(Locale.getDefault(), "<b>y</b> = %s+%s<b>r</b> - %s<b>t</b> <br>", NP(yeew0), NP(yeep), NP(d)));
            result.append("<br>");

            Tabular tabular = new Tabular();

            // We negated m previously. Set m to |m| again.
            m = m.abs();

            // The congruence ax+by ≡ c (mod m) can be written as a(xₑₑw₀ + xₑₑpr + et) + b(yₑₑw₀ + yₑₑpr - dt) ≡ c (mod m)
            result.append(String.format(Locale.getDefault(), "<font color='%s'>The congruence a<b>x</b>+b<b>y</b> ≡ c (mod m) can be written as </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>a(xₑₑw₀ + xₑₑp<b>r</b> + e<b>t</b>) + b(yₑₑw₀ + yₑₑp<b>r</b> - d<b>t</b>) ≡ c (mod m)</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s(%s + %s<b>r</b> + %s<b>t</b>) + %s(%s + %s<b>r</b> - %s<b>t</b>) ≡ %s (mod %s) <br>", NP(a), NP(xeew0), NP(xeep), NP(e), NP(b), NP(yeew0), NP(yeep), NP(d), NP(c), NP(m)));
            result.append("<br>");

            // Check correctness for r = {-3, ..., 3} and t = {-3, ..., 3} in a(xₑₑw₀+xₑₑpr + et) + b(yₑₑw₀+yₑₑpr - dt) ≡ c (mod m)
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Check correctness for <b>r</b> = {-3, ..., 3} and <b>t</b> = {-3, ..., 3} in</font><br>", COLOR));
            result.append("⋮<br>");
            BigInteger rMin = BigInteger.valueOf(-3);
            BigInteger rMax = BigInteger.valueOf(3);
            BigInteger tMin = BigInteger.valueOf(-3);
            BigInteger tMax = BigInteger.valueOf(3);
            for(BigInteger r = rMin; r.compareTo(rMax) <= 0; r = r.add(ONE)) {
                for(BigInteger t = tMin; t.compareTo(tMax) <= 0; t = t.add(ONE)) {
                    BigInteger xeepr = xeep.multiply(r);
                    BigInteger et = e.multiply(t);
                    BigInteger axNew = a.multiply(xeew0.add(xeepr).add(et));
                    BigInteger yeepr = yeep.multiply(r);
                    BigInteger dt = d.multiply(t);
                    BigInteger byNew = b.multiply(yeew0.add(yeepr).subtract(dt));
                    BigInteger axNewPLUSbyNew = axNew.add(byNew);
                    BigInteger axNewPLUSbyNewMODm = axNewPLUSbyNew.mod(m);

                    List<String> row = new ArrayList<>();
                    row.add(String.format(Locale.getDefault(), "<b>r</b> = %s,", r)); // r.compareTo(ZERO) < 0 ? r : NBSP + r // r.compareTo(ZERO) < 0 ? r : "+" + r
                    row.add(" ");
                    row.add(String.format(Locale.getDefault(), "<b>t</b> = %s", t)); // t.compareTo(ZERO) < 0 ? t : NBSP + t // t.compareTo(ZERO) < 0 ? t : "+" + t
                    row.add(NBSP + RIGHT_ARROW_COLORED + NBSP);
                    row.add(String.format(Locale.getDefault(), "%s(%s", NP(a), NP(xeew0)));
                    row.add(" + ");
                    row.add(String.format(Locale.getDefault(), "%s", NP(xeepr)));
                    row.add(" + ");
                    row.add(String.format(Locale.getDefault(), "%s", NP(et)));
                    row.add(") + ");
                    row.add(String.format(Locale.getDefault(), "%s(%s", NP(b), NP(yeew0)));
                    row.add(" + ");
                    row.add(String.format(Locale.getDefault(), "%s", NP(yeepr)));
                    row.add(" - ");
                    row.add(String.format(Locale.getDefault(), "%s", NP(dt)));
                    row.add(") = ");
                    row.add(String.format(Locale.getDefault(), "%s", axNewPLUSbyNew));
                    row.add(" ≡ ");
                    row.add(String.format(Locale.getDefault(), "%s", axNewPLUSbyNewMODm)); // Calculated c
                    row.add(String.format(Locale.getDefault(), " (mod %s)", m));

                    if (showResultInMonospace) {
                        tabular.addRow(row);
                    } else {
                        for (String cell : row) {
                            result.append(cell);
                        }
                        result.append("<br>");
                    }
                }
            }

            if (showResultInMonospace) {
                result.append(tabular.render());
            }
            result.append("⋮");

            // Just for the sake of canceling, but this has no real effect here
            // Check if the run is canceled
            if (run.isCancelled()) { return null; }

            return result.toString();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return ex.toString();
        }
    }
    //endregion LINEAR CONGRUENCE IN TWO VARIABLES















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

