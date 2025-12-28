package com.gegprifti.android.numbertheoryalgorithms.algorithms.binaryquadraticform;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.getNP;
import android.util.Log;
import android.util.Pair;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Solution;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;


public class BinaryQuadraticForm extends Algorithm implements StringCalculator {
    private final static String TAG = BinaryQuadraticForm.class.getSimpleName();


    public BinaryQuadraticForm(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger c = algorithmParameters.getInput3();
            BigInteger d = algorithmParameters.getInput4();
            BigInteger e = algorithmParameters.getInput5();
            BigInteger f = algorithmParameters.getInput6();
            boolean includeTrivialSolutions = algorithmParameters.getIncludeTrivialSolutions();
            boolean includeOnlyPositiveSolutions = algorithmParameters.getIncludeOnlyPositiveSolutions();
            boolean includeOnlyNegativeSolutions = algorithmParameters.getIncludeOnlyNegativeSolutions();

            // Simple Quadratic Form
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Binary Quadratic Form</b></font><br>", COLOR));

            // Solve for x,y such as bxy+dx+ey=f where b,d,e,f,x,y ∊ ℤ and b ≠ 0
            result.append("Solve for <b>x</b>,<b>y</b> such as b<b>x</b><b>y</b>+d<b>x</b>+e<b>y</b>=f where b,d,e,f,<b>x</b>,<b>y</b> ∊ ℤ and b ≠ 0<br>");
            result.append(String.format(Locale.getDefault(), "%s<b>x</b><b>y</b>+%s<b>x</b>+%s<b>y</b>=%s<br>", getNP(b), getNP(d), getNP(e), getNP(f)));
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

            // InputGroup
            result.append(String.format("<font color='%s'>InputGroup</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "b = %s<br>", getNP(b)));
            result.append(String.format(Locale.getDefault(), "d = %s<br>", getNP(d)));
            result.append(String.format(Locale.getDefault(), "e = %s<br>", getNP(e)));
            result.append(String.format(Locale.getDefault(), "f = %s<br>", getNP(f)));
            result.append("<br>");

            // Multiply both sides with b then, b²xy+bdx+bey=bf
            result.append(String.format("<font color='%s'>Multiply both sides with b then, b²<b>x</b><b>y</b>+bd<b>x</b>+be<b>y</b>=bf</font><br>", COLOR));
            BigInteger bb = b.multiply(b);
            BigInteger bd = d.multiply(b);
            BigInteger be = e.multiply(b);
            BigInteger bf = f.multiply(b);
            result.append(String.format(Locale.getDefault(), "%s<b>x</b><b>y</b>+%s<b>x</b>+%s<b>y</b>=%s<br>", getNP(bb), getNP(bd), getNP(be), getNP(bf)));
            result.append("<br>");

            // Add de to both sides then, b²xy+bdx+bey+de=bf+de
            result.append(String.format("<font color='%s'>Add de to both sides then, b²<b>x</b><b>y</b>+bd<b>x</b>+be<b>y</b>+de=bf+de</font><br>", COLOR));
            BigInteger de = d.multiply(e);
            result.append(String.format(Locale.getDefault(), "%s<b>x</b><b>y</b>+%s<b>x</b>+%s<b>y</b>+%s=%s+%s<br>", getNP(bb), getNP(bd), getNP(be), getNP(de), getNP(bf), getNP(de)));
            result.append("<br>");

            // The LHS is of the form of (bx+e)(by+d)
            result.append(String.format("<font color='%s'>The LHS is of the form of (b<b>x</b>+e)(b<b>y</b>+d)</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "(%s<b>x</b>+%s)(%s<b>y</b>+%s)<br>", getNP(b), getNP(e), getNP(b), getNP(d)));
            result.append("<br>");

            // Let n=bf+de, then the RHS can be written as n=pq
            result.append(String.format("<font color='%s'>Let n=bf+de, then the RHS can be written as</font><br>", COLOR));
            BigInteger n = bf.add(de);
            result.append(String.format(Locale.getDefault(), "n=%s<br>", getNP(n)));
            result.append("<br>");

            // So we must solve (bx+e)(by+d)=n
            result.append(String.format("<font color='%s'>So we must solve (b<b>x</b>+e)(b<b>y</b>+d)=n</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "(%s<b>x</b>+%s)(%s<b>y</b>+%s)=%s<br>", getNP(b), getNP(e), getNP(b), getNP(d), getNP(n)));
            result.append("<br>");

            // Factoring n into pq pairs using Trial Division
            result.append(String.format("<font color='%s'>Factoring n into pq pairs using Trial Division</font><br>", COLOR));
            List<Pair<BigInteger, BigInteger>> pairFactors = AlgorithmHelper.getPairFactors(n, includeTrivialSolutions);
            int pairFactorsSize = pairFactors.size();
            // We expect pairFactorsSize to be 0, 4, 8, 12, 16, 20, 24,...
            if (pairFactorsSize >= 4) {
                if (pairFactorsSize % 4 == 0) {
                    for (int i = 0; i < pairFactorsSize; i += 4) {
                        AlgorithmHelper.checkIfCanceled();

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
                        result.append(String.format(Locale.getDefault(), "[%s, %s] [%s, %s] [%s, %s] [%s, %s]<br>", getNP(p0), getNP(q0), getNP(p1), getNP(q1), getNP(p2), getNP(q2), getNP(p3), getNP(q3)));
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
            List<Solution> solutions = AlgorithmHelper.calculateBQFSolutions(b,d,e,pairFactors, includeOnlyPositiveSolutions, includeOnlyNegativeSolutions);
            if (!solutions.isEmpty()) {
                for(int i = 0; i < solutions.size(); i++) {
                    AlgorithmHelper.checkIfCanceled();

                    Solution solution = solutions.get(i);
                    BigInteger p = solution.getP();
                    BigInteger q = solution.getQ();
                    BigInteger x = solution.getX();
                    BigInteger y = solution.getY();
                    result.append(String.format(Locale.getDefault(), "[%s, %s] %s [%s, %s]<br>", getNP(p), getNP(q), RIGHT_ARROW_COLORED, getNP(x), getNP(y)));
                }
            } else {
                result.append("No solutions were found");
                return result.toString();
            }
            result.append("<br>");

            // Solutions
            result.append(String.format("<font color='%s'>Solutions</font><br>", COLOR));
            for (int i = 0; i < solutions.size(); i++) {
                AlgorithmHelper.checkIfCanceled();

                Solution solution = solutions.get(i);
                BigInteger x = solution.getX();
                BigInteger y = solution.getY();
                if (i != solutions.size() - 1) {
                    result.append(String.format(Locale.getDefault(), "[%s, %s]<br>", getNP(x), getNP(y)));
                } else {
                    result.append(String.format(Locale.getDefault(), "[%s, %s]", getNP(x), getNP(y)));
                }
            }

            return result.toString();
        } catch (InterruptedException ex) {
            // This specifically handles the cancellation.
            // Re-throw it so ProgressManager can handle it correctly.
            throw ex;
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return ex.toString();
        }
    }
}