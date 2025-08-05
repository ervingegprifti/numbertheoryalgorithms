package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.getNP;
import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.getSign;
import android.util.Log;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Tabular;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LinearDiophantineEquation extends Algorithm implements StringCalculator {
    private final static String TAG = LinearDiophantineEquation.class.getSimpleName();

    public LinearDiophantineEquation(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }
    @Override
    public String calculate() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger c = algorithmParameters.getInput3();

            // Linear Diophantine Equation In Two Variables
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Linear Diophantine Equation In Two Variables</b></font><br>", COLOR));

            // Solve for x,y such as ax+by=c where a,b,c,x,y ∊ ℤ with a,b ≠ 0
            result.append("Solve for <b>x</b>,<b>y</b> such as a<b>x</b>+b<b>y</b>=c where a,b,c,<b>x</b>,<b>y</b> ∊ ℤ with a,b ≠ 0<br>");
            result.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b>=%s <br>", getNP(a), getNP(b), getNP(c)));
            result.append("<br>");

            // InputGroup
            result.append(String.format(Locale.getDefault(), "<font color='%s'>InputGroup</font><br>", COLOR));
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

            BigInteger[] EE = AlgorithmHelper.calculateEEA(a.abs(), b.abs());
            BigInteger xee = getSign(a).multiply(EE[1]);
            BigInteger yee = getSign(b).multiply(EE[2]);

            // Use Extended Euclidean Algorithm to find xₑₑ and yₑₑ from |a|x + |b|y = GCD(|a|, |b|) = g
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Use Extended Euclidean Algorithm to find xₑₑ and yₑₑ from |a|<b>x</b> + |b|<b>y</b> = GCD(|a|, |b|) = g </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "xₑₑ = sign(a)·xₑₑ = %s·%s = %s <br>", getNP(getSign(a)), getNP(EE[1]), xee));
            result.append(String.format(Locale.getDefault(), "yₑₑ = sign(b)·yₑₑ = %s·%s = %s <br>", getNP(getSign(b)), getNP(EE[2]), yee));
            result.append("<br>");

            BigInteger x0 = (xee.multiply(c.divide(g)));
            BigInteger y0 = (yee.multiply(c.divide(g)));

            // A particular first initial solution is x₀ = xₑₑ(c/g) and y₀ = yₑₑ(c/g)
            result.append(String.format(Locale.getDefault(), "<font color='%s'>A particular first initial solution is x₀ = xₑₑ(c/g) and y₀ = yₑₑ(c/g) </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "x₀ = %s·(%s/%s) = %s <br>", getNP(xee), getNP(c), getNP(g), getNP(x0)));
            result.append(String.format(Locale.getDefault(), "y₀ = %s·(%s/%s) = %s <br>", getNP(yee), getNP(c), getNP(g), getNP(y0)));
            result.append("<br>");

            // For r ∊ ℤ, The general x,y solution is
            result.append(String.format(Locale.getDefault(), "<font color='%s'>For <b>r</b> ∊ ℤ, the general <b>x</b>,<b>y</b> solution is </font><br>", COLOR));
            result.append("<b>x</b> = x₀ + (b/g)<b>r</b>  <br>");
            result.append("<b>y</b> = y₀ - (a/g)<b>r</b>  <br>");
            result.append("<br>");
            result.append(String.format(Locale.getDefault(), "<b>x</b> = %s + (%s/%s)<b>r</b>  <br>", getNP(x0), getNP(b), getNP(g)));
            result.append(String.format(Locale.getDefault(), "<b>y</b> = %s - (%s/%s)<b>r</b>  <br>", getNP(y0), getNP(a), getNP(g)));
            result.append("<br>");
            BigInteger bDg = b.divide(g);
            BigInteger aDg = a.divide(g);
            result.append(String.format(Locale.getDefault(), "<b>x</b> = %s + %s<b>r</b>  <br>", getNP(x0), getNP(bDg)));
            result.append(String.format(Locale.getDefault(), "<b>y</b> = %s - %s<b>r</b>  <br>", getNP(y0), getNP(aDg)));
            result.append("<br>");

            // The Linear Diophantine Equation ax+by=c can be written as
            result.append(String.format(Locale.getDefault(), "<font color='%s'>The Linear Diophantine Equation a<b>x</b>+b<b>y</b>=c can be written as </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s(%s + %s<b>r</b>)+%s(%s - %s<b>r</b>)=c  <br>", getNP(a), getNP(x0), getNP(bDg), getNP(b), getNP(y0), getNP(aDg)));
            result.append("<br>");

            // Check correctness for r = {-3, ..., 3}
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Check correctness for <b>r</b> = {-3, ..., 3} </font><br>", COLOR));
            result.append("⋮<br>");
            Tabular tabular = new Tabular();
            for (BigInteger r = BigInteger.valueOf(-3); r.compareTo(BigInteger.valueOf(3)) < 0; r = r.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                BigInteger bDgMr = (bDg).multiply(r);
                BigInteger aDgMr = (aDg).multiply(r);

                BigInteger x = x0.add(bDgMr);
                BigInteger y = y0.subtract(aDgMr);

                BigInteger ax = a.multiply(x);
                BigInteger by = b.multiply(y);
                BigInteger cCalculated = ax.add(by);

                List<String> row = new ArrayList<>();
                row.add(String.format(Locale.getDefault(), "<b>r</b> = %s", getNP(r)));
                row.add(NBSP + RIGHT_ARROW_COLORED + NBSP);
                row.add(String.format(Locale.getDefault(), "<b>x</b> = %s + %s·%s", getNP(x0), getNP(bDg), getNP(r)));
                row.add(" = ");
                row.add(String.format(Locale.getDefault(), "%s+%s", getNP(x0), getNP(bDgMr)));
                row.add(String.format(Locale.getDefault(), " = %s", getNP(x)));
                row.add(" and ");
                row.add(String.format(Locale.getDefault(), "<b>y</b> = %s - %s·%s", getNP(y0), getNP(aDg), getNP(r)));
                row.add(" = ");
                row.add(String.format(Locale.getDefault(), "%s-%s", getNP(y0), getNP(aDgMr)));
                row.add(String.format(Locale.getDefault(), " = %s", getNP(y)));
                row.add(NBSP + RIGHT_ARROW_COLORED + NBSP);
                row.add(String.format(Locale.getDefault(),"%s·%s", getNP(a), getNP(x)));
                row.add(" + ");
                row.add(String.format(Locale.getDefault(),"%s·%s", getNP(b), getNP(y)));
                row.add(String.format(Locale.getDefault()," = %s", getNP(cCalculated)));
                tabular.addRow(row);
            }
            result.append(tabular.render());
            result.append("⋮");
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