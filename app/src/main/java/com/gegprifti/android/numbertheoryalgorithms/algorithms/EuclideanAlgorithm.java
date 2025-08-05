package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.getNP;
import android.util.Log;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;
import java.util.Locale;


public class EuclideanAlgorithm extends Algorithm implements StringCalculator {
    private final static String TAG = EuclideanAlgorithm.class.getSimpleName();


    public EuclideanAlgorithm(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        StringBuilder output = new StringBuilder();
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();

            // Title
            output.append(String.format(Locale.getDefault(), "<font color='%s'><b>Euclidean Algorithm</b></font><br>", COLOR));
            output.append("Compute the GCD of a and b<br>");
            output.append(String.format(Locale.getDefault(), "GCD(%s, %s)<br>", getNP(a), getNP(b)));
            output.append("<br>");

            // InputGroup
            output.append(String.format("<font color='%s'>%s%sInput</font><br>", COLOR, BULLET, TAB));
            output.append(String.format("%sa = %s<br>", TAB, getNP(a)));
            output.append(String.format("%sb = %s<br>", TAB, getNP(b)));
            output.append("<br>");

            // Make sure a,b ∊ ℕ
            a = a.abs();
            b = b.abs();
            output.append(String.format("<font color='%s'>%s%sMake sure a,b ∊ ℕ" + "</font>" + "<br>", COLOR, BULLET, TAB));
            output.append(String.format("%sa = |a| = %s<br>", TAB, a));
            output.append(String.format("%sb = |b| = %s<br>", TAB, b));
            output.append("<br>");

            // if a = b, then GCD(a, b) = a
            if (a.equals(b)) {
                output.append(String.format("<font color='%s'>%s%sIf a = b, then GCD(a, b) = a, since a∣a and a∣b</font><br>", COLOR, BULLET, TAB));
                output.append(String.format("%sGCD(a, b) = %s", TAB, a));
                return output.toString();
            }

            // GCD(a, 0) = a, since a divides both a and 0
            if (!a.equals(BigInteger.ZERO) && b.equals(BigInteger.ZERO)) {
                output.append(String.format("<font color='%s'>%s%sGCD(a, 0) = a</font><br>", COLOR, BULLET, TAB));
                output.append(String.format("%sGCD(a, 0) = %s", TAB, a));
                return output.toString();
            }

            // GCD(0, 0) = 0
            if (a.equals(BigInteger.ZERO) && b.equals(BigInteger.ZERO)) {
                output.append(String.format("<font color='%s'>%s%sGCD(0, 0) = 0</font><br>", COLOR, BULLET, TAB));
                output.append(String.format("%sGCD(0, 0) = %s", TAB, BigInteger.ZERO));
                return output.toString();
            }

            // If b ∣ a then GCD(a, b) = b. If b ∤ a then apply the division algorithm
            if (AlgorithmHelper.doesDCompletelyDivideN(b, a)) {
                output.append(String.format("<font color='%s'>%s%sSince b ∣ a then GCD(a, b) = b</font><br>", COLOR, BULLET, TAB));
                output.append(String.format("%sGCD(a, b) = %s", TAB, b));
                return output.toString();
            }

            // Since b ∤ a, then apply the division algorithm (rₙ₋₂ = rₙ₋₁ qₙ₋₁ + rₙ) repeatedly
            output.append(String.format("<font color='%s'>%s%sSince b ∤ a, then apply r<sub><small><small>n-2</small></small></sub> = r<sub><small><small>n-1</small></small></sub> · q<sub><small><small>n-1</small></small></sub> + r<sub><small><small>n</small></small></sub> repeatedly</font><br>", COLOR, BULLET, TAB));
            output.append(String.format("%sSet the starting point where n = 1<br>", TAB));
            output.append(String.format("%sr<sub><small><small>n-2</small></small></sub> = a<br>", TAB));
            output.append(String.format("%sr<sub><small><small>n-1</small></small></sub> = b<br>", TAB));
            output.append(String.format("%sq<sub><small><small>n-1</small></small></sub> = quotient of r<sub><small><small>n-2</small></small></sub>/r<sub><small><small>n-1</small></small></sub><br>", TAB));
            output.append(String.format("%sr<sub><small><small>n</small></small></sub> = remainder of r<sub><small><small>n-2</small></small></sub>/r<sub><small><small>n-1</small></small></sub><br>", TAB));
            output.append("<br>");

            // loop rₙ₋₂ = rₙ₋₁ qₙ₋₁ + rₙ until rₙ = 0
            output.append(String.format("<font color='%s'>%s%sLoop until r<sub><small><small>n</small></small></sub> = 0</font><br>", COLOR, BULLET, TAB));
            // The first result
            BigInteger rn_2 = a; // rₙ₋₂ = rn_2
            BigInteger rn_1 = b; // rₙ₋₁ = rn_1
            BigInteger[] divisionResult = a.divideAndRemainder(b);
            BigInteger qn_1 = divisionResult[0]; // qₙ₋₁ = qn_1
            BigInteger rn = divisionResult[1]; // rₙ = rn
            output.append(String.format("%s%s = %s · %s + %s<br>", TAB, rn_2, rn_1, qn_1, rn));
            while (rn.compareTo(BigInteger.ZERO) > 0) {
                AlgorithmHelper.checkIfCanceled();
                rn_2 = rn_1;
                rn_1 = rn;
                divisionResult = rn_2.divideAndRemainder(rn_1);
                qn_1 = divisionResult[0];
                rn = divisionResult[1];
                output.append(String.format("%s%s = %s · %s + %s", TAB, rn_2, rn_1, qn_1, rn));
                if (rn.compareTo(BigInteger.ZERO) == 0) {
                    output.append(String.format("<font color='%s'> %s stop here</font>", COLOR, STOP));
                }
                output.append("<br>");
            }
            output.append("<br>");

            // Return the last rₙ before rₙ = 0. Hence return rₙ₋₁
            output.append(String.format("<font color='%s'>%s%sGCD of a and b is the last nonzero remainder</font><br>", COLOR, BULLET, TAB));
            output.append(String.format("%sGCD(a, b) = %s", TAB, rn_1));

            // Return
            return output.toString();
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