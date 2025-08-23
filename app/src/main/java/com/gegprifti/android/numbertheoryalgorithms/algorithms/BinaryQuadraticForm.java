package com.gegprifti.android.numbertheoryalgorithms.algorithms;


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
            boolean includeTrivialSolutions = algorithmParameters.getIncludeTrivialSolutions();
            boolean includeOnlyPositiveSolutions = algorithmParameters.getIncludeOnlyPositiveSolutions();
            boolean includeOnlyNegativeSolutions = algorithmParameters.getIncludeOnlyNegativeSolutions();

            // Simple Quadratic Form
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Binary Quadratic Form</b></font><br>", COLOR));

            // Solve for x,y such as axy+bx+cy=d where a,b,c,d,x,y ∊ ℤ and a ≠ 0
            result.append("Solve for <b>x</b>,<b>y</b> such as a<b>x</b><b>y</b>+b<b>x</b>+c<b>y</b>=d where a,b,c,d,<b>x</b>,<b>y</b> ∊ ℤ and a ≠ 0<br>");
            result.append(String.format(Locale.getDefault(), "%s<b>x</b><b>y</b>+%s<b>x</b>+%s<b>y</b>=%s<br>", getNP(a), getNP(b), getNP(c), getNP(d)));
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
            result.append(String.format(Locale.getDefault(), "a = %s<br>", getNP(a)));
            result.append(String.format(Locale.getDefault(), "b = %s<br>", getNP(b)));
            result.append(String.format(Locale.getDefault(), "c = %s<br>", getNP(c)));
            result.append(String.format(Locale.getDefault(), "d = %s<br>", getNP(d)));
            result.append("<br>");

            // Multiply both sides with a then, a²xy+abx+acy=ad
            result.append(String.format("<font color='%s'>Multiply both sides with a then, a²<b>x</b><b>y</b>+ab<b>x</b>+ac<b>y</b>=ad</font><br>", COLOR));
            BigInteger aa = a.multiply(a);
            BigInteger ab = a.multiply(b);
            BigInteger ac = a.multiply(c);
            BigInteger ad = a.multiply(d);
            result.append(String.format(Locale.getDefault(), "%s<b>x</b><b>y</b>+%s<b>x</b>+%s<b>y</b>=%s<br>", getNP(aa), getNP(ab), getNP(ac), getNP(ad)));
            result.append("<br>");

            // Add bc to both sides then, a²xy+abx+acy+bc=ad+bc
            result.append(String.format("<font color='%s'>Add bc to both sides then, a²<b>x</b><b>y</b>+ab<b>x</b>+ac<b>y</b>+bc=ad+bc</font><br>", COLOR));
            BigInteger bc = b.multiply(c);
            result.append(String.format(Locale.getDefault(), "%s<b>x</b><b>y</b>+%s<b>x</b>+%s<b>y</b>+%s=%s+%s<br>", getNP(aa), getNP(ab), getNP(ac), getNP(bc), getNP(ad), getNP(bc)));
            result.append("<br>");

            // The LHS is of the form of (ax+c)(ay+b)
            result.append(String.format("<font color='%s'>The LHS is of the form of (a<b>x</b>+c)(a<b>y</b>+b)</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "(%s<b>x</b>+%s)(%s<b>y</b>+%s)<br>", getNP(a), getNP(c), getNP(a), getNP(b)));
            result.append("<br>");

            // Let n=ad+bc, then the RHS can be written as n=pq
            result.append(String.format("<font color='%s'>Let n=ad+bc, then the RHS can be written as</font><br>", COLOR));
            BigInteger n = ad.add(bc);
            result.append(String.format(Locale.getDefault(), "n=%s<br>", getNP(n)));
            result.append("<br>");

            // So we must solve (ax+c)(ay+b)=n
            result.append(String.format("<font color='%s'>So we must solve (a<b>x</b>+c)(a<b>y</b>+b)=n</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "(%s<b>x</b>+%s)(%s<b>y</b>+%s)=%s<br>", getNP(a), getNP(c), getNP(a), getNP(b), getNP(n)));
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

            // Checking (ax+c)=p and (ay+b)=q per each (p,q) pairs will give (x,y) solutions if any
            result.append(String.format("<font color='%s'>Checking (a<b>x</b>+c)=p and (a<b>y</b>+b)=q per each (p,q) pairs will give (<b>x</b>,<b>y</b>) solutions if any</font><br>", COLOR));
            List<Solution> solutions = AlgorithmHelper.calculateBQFSolutions(a,b,c,pairFactors, includeOnlyPositiveSolutions, includeOnlyNegativeSolutions);
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