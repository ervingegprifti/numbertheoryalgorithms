package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.getNP;
import android.util.Log;

import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;
import java.util.Locale;


public class ExtendedEuclideanAlgorithm extends Algorithm implements StringCalculator {
    private final static String TAG = ExtendedEuclideanAlgorithm.class.getSimpleName();

    public ExtendedEuclideanAlgorithm(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        StringBuilder output = new StringBuilder();
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();

            // Extended Euclidean Algorithm
            output.append(String.format(Locale.getDefault(), "<font color='%s'><b>Extended Euclidean Algorithm</b></font><br>", COLOR));

            // Solve for x,y such as ax+by=GCD(a,b) where a,b ∊ ℕ, x,y ∊ ℤ
            output.append("Solve for <b>x</b>,<b>y</b> such as a<b>x</b>+b<b>y</b>=GCD(a,b) where a,b ∊ ℕ, <b>x</b>,<b>y</b> ∊ ℤ<br>");
            output.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b> = GCD(%s, %s)<br>", getNP(a), getNP(b), getNP(a), getNP(b)));
            output.append("<br>");

            // Input
            output.append(String.format(Locale.getDefault(), "<font color='%s'>Input</font><br>", COLOR));
            output.append(String.format(Locale.getDefault(), "a = %s<br>", getNP(a)));
            output.append(String.format(Locale.getDefault(), "b = %s<br>", getNP(b)));
            output.append(String.format(Locale.getDefault(), "GCD(a,b) = %s<br>", a.gcd(b)));
            output.append("<br>");

            // Apply the division algorithm (rₙ₋₂ = rₙ₋₁ qₙ₋₁ + rₙ) repeatedly
            output.append(String.format(Locale.getDefault(), "<font color='%s'>Apply r<sub><small><small>n-2</small></small></sub> = r<sub><small><small>n-1</small></small></sub> · q<sub><small><small>n-1</small></small></sub> + r<sub><small><small>n</small></small></sub> repeatedly</font><br>", COLOR));
            output.append("Set the starting point<br>");
            output.append("r<sub><small><small>n-2</small></small></sub> = a<br>");
            output.append("r<sub><small><small>n-1</small></small></sub> = b<br>");
            output.append("q<sub><small><small>n-1</small></small></sub> = quotient of r<sub><small><small>n-2</small></small></sub>/r<sub><small><small>n-1</small></small></sub><br>");
            output.append("r<sub><small><small>n</small></small></sub> = remainder of r<sub><small><small>n-2</small></small></sub>/r<sub><small><small>n-1</small></small></sub><br>");
            output.append("x<sub><small><small>n-2</small></small></sub> = 1<br>");
            output.append("x<sub><small><small>n-1</small></small></sub> = 0<br>");
            output.append("x<sub><small><small>n-1</small></small></sub> = x<sub><small><small>n-2</small></small></sub> - x<sub><small><small>n-1</small></small></sub> · q<sub><small><small>n-1</small></small></sub><br>");
            output.append("y<sub><small><small>n-2</small></small></sub> = 1<br>");
            output.append("y<sub><small><small>n-1</small></small></sub> = 0<br>");
            output.append("y<sub><small><small>n-1</small></small></sub> = y<sub><small><small>n-2</small></small></sub> - y<sub><small><small>n-1</small></small></sub> · q<sub><small><small>n-1</small></small></sub><br>");
            output.append("<br>");

            // Loop rₙ₋₂ = rₙ₋₁ qₙ₋₁ + rₙ until rₙ = 0
            output.append(String.format(Locale.getDefault(), "<font color='%s'>Loop until r<sub><small><small>n</small></small></sub> = 0</font><br>", COLOR));
            // The first result
            BigInteger rn_2 = a; // rₙ₋₂ = rn_2
            BigInteger rn_1 = b; // rₙ₋₁ = rn_1
            BigInteger[] divisionResult = a.divideAndRemainder(b);
            BigInteger qn_1 = divisionResult[0]; // qₙ₋₁ = qn_1
            BigInteger rn = divisionResult[1]; // rₙ = rn
            output.append(String.format(Locale.getDefault(), "%s = %s · %s + %s<br>", rn_2, rn_1, qn_1, rn));
            BigInteger xn_2 = ONE;
            BigInteger xn_1 = ZERO;
            BigInteger xn_temp = xn_1;
            xn_1 = xn_2.subtract(xn_1.multiply(qn_1));
            output.append(String.format(Locale.getDefault(),"<b>x</b> = (%+d) - (%+d)·(%+d) =%s<br>", xn_2, xn_1, qn_1, xn_1));
            xn_2 = xn_temp;
            BigInteger yn_2 = ZERO;
            BigInteger yn_1 = ONE;
            BigInteger yn_temp = yn_1;
            yn_1 = yn_2.subtract(yn_1.multiply(qn_1));
            output.append(String.format(Locale.getDefault(), "<b>y</b> = (%+d) - (%+d)·(%+d) =%s<br>", yn_2, yn_1, qn_1, yn_1));
            yn_2 = yn_temp;
            output.append("<br>");

            while (rn.compareTo(BigInteger.ZERO) > 0) {
                AlgorithmHelper.checkIfCanceled();

                rn_2 = rn_1;
                rn_1 = rn;
                divisionResult = rn_2.divideAndRemainder(rn_1);
                qn_1 = divisionResult[0];
                rn = divisionResult[1];
                output.append(String.format(Locale.getDefault(), "%s = %s · %s + %s", rn_2, rn_1, qn_1, rn));
                if (rn.compareTo(BigInteger.ZERO) == 0) {
                    output.append(String.format(Locale.getDefault(), "<font color='%s'> %s stop here</font>", COLOR, STOP));
                }
                output.append("<br>");
                xn_temp = xn_1;
                xn_1 = xn_2.subtract(xn_1.multiply(qn_1));
                output.append(String.format(Locale.getDefault(),"<b>x</b> = (%+d) - (%+d)·(%+d) =%s<br>", xn_2, xn_1, qn_1, xn_1));
                xn_2 = xn_temp;
                yn_temp = yn_1;
                yn_1 = yn_2.subtract(yn_1.multiply(qn_1));
                output.append(String.format(Locale.getDefault(), "<b>y</b> = (%+d) - (%+d)·(%+d) =%s<br>", yn_2, yn_1, qn_1, yn_1));
                yn_2 = yn_temp;
                output.append("<br>");
            }

            // Return the last rₙ before rₙ = 0. Hence return rₙ₋₁
            output.append(String.format(Locale.getDefault(), "<font color='%s'>Output</font><br>", COLOR));
            // output.append(String.format(Locale.getDefault(), "GCD(a, b) = %s<br>", rn_1));
            output.append(String.format(Locale.getDefault(), "<b>x</b> = %s<br>", xn_2));
            output.append(String.format(Locale.getDefault(), "<b>y</b> = %s", yn_2));

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