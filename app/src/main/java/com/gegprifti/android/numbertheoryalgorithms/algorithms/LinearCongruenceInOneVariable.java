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


/**
 *
 * @see <a href="https://math.stackexchange.com/questions/37806/extended-euclidean-algorithm-with-negative-numbers">Extended Euclidean algorithm with negative numbers</a>
 */
public class LinearCongruenceInOneVariable extends Algorithm implements StringCalculator {
    private final static String TAG = LinearCongruenceInOneVariable.class.getSimpleName();


    public LinearCongruenceInOneVariable(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger m = algorithmParameters.getInput3();

            // Linear Congruence In One Variable
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Linear Congruence In One Variable</b></font><br>", COLOR));

            // Solve for x, the linear congruence ax ≡ b (mod m) where a,b,x ∊ ℤ, m ∊ ℕ
            result.append("Solve for <b>x</b>, the linear congruence a<b>x</b> ≡ b (mod m) where a,b,<b>x</b> ∊ ℤ, m ∊ ℕ<br>");
            result.append(String.format(Locale.getDefault(), "%s<b>x</b> ≡ %s (mod %s)<br>", getNP(a), getNP(b), getNP(m)));
            result.append("<br>");

            // InputGroup
            result.append(String.format(Locale.getDefault(), "<font color='%s'>InputGroup </font><br>", COLOR));
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

            BigInteger[] EE = AlgorithmHelper.calculateEEA(a.abs(), m.abs());
            BigInteger xee = getSign(a).multiply(EE[1]);
            //BigInteger yee = getSign(m).multiply(EE[2]);

            // Use Extended Euclidean Algorithm to find xₑₑ from |a|x + |m|y = GCD(|a|, |m|) = g
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Use Extended Euclidean Algorithm to find xₑₑ from |a|<b>x</b> + |m|<b>y</b> = GCD(|a|, |m|) = g</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "xₑₑ = sign(a)·xₑₑ = %s·%s = %s <br>", getNP(getSign(a)), getNP(EE[1]), xee));
            //result.append(String.format(Locale.getDefault(), TAB + "yₑₑ = sign(m)·yₑₑ = %s·%s = %s <br>", NP(getSign(m)), NP(EE[2]), yee));
            result.append("<br>");

            // The first initial solution is x₀ = xₑₑ(b/g) mod m
            BigInteger x0 = (xee.multiply(b.divide(g))).mod(m);
            result.append(String.format(Locale.getDefault(), "<font color='%s'>The first initial solution is x₀ = xₑₑ(b/g) (mod m) </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "x₀ = %s·(%s/%s) mod %s = %s <br>", getNP(xee), getNP(b), getNP(g), getNP(m), getNP(x0)));
            result.append("<br>");

            // All initial solutions n = {0, ..., g-1}, xₙ = n(m/g) + x₀ mod m
            result.append(String.format(Locale.getDefault(), "<font color='%s'>All initial solutions for n = {0, ..., g-1} are xₙ = n(m/g) + x₀ (mod m) </font><br>", COLOR));
            List<BigInteger> initialXSolutions = new ArrayList<>();
            for (BigInteger n = ZERO; n.compareTo(g) < 0; n = n.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                BigInteger xn = n.multiply(m.divide(g)).add(x0).mod(m);
                initialXSolutions.add(xn);
                result.append(String.format(Locale.getDefault(), "x<sub><small><small>%s</small></small></sub> = %s·(%s/%s) + %s (mod %s) = %s <br>", getNP(n), getNP(n), getNP(m), getNP(g), getNP(x0), getNP(m), getNP(xn)));
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
                AlgorithmHelper.checkIfCanceled();

                if (n > 0) {
                    result.append("<br><br>");
                }
                BigInteger xn = initialXSolutions.get(n);
                result.append(String.format(Locale.getDefault(), "x<sub><small><small>%s</small></small></sub> = %s <br>", AlgorithmHelper.getNP(n), getNP(xn)));
                result.append(String.format(Locale.getDefault(), "%s(%s<b>r</b> + %s) ≡ %s (mod %s) <br>", a, m, xn, b, m));
                result.append("⋮<br>");
                Tabular tabular = new Tabular();
                for (BigInteger r = BigInteger.valueOf(-3); r.compareTo(BigInteger.valueOf(3)) < 0; r = r.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();

                    BigInteger mMrPxn = m.multiply(r).add(xn);
                    BigInteger aMx = a.multiply(mMrPxn);
                    BigInteger aMxMODm = aMx.mod(m);

                    List<String> row = new ArrayList<>();
                    row.add(String.format(Locale.getDefault(), "<b>r</b> = %s", getNP(r)));
                    row.add(NBSP + RIGHT_ARROW_COLORED + NBSP);
                    row.add(String.format(Locale.getDefault(), "<b>x</b> = %s·%s + %s", getNP(m), getNP(r), getNP(xn)));
                    row.add(String.format(Locale.getDefault(), " = %s", getNP(mMrPxn)));
                    row.add(NBSP + RIGHT_ARROW_COLORED + NBSP);
                    row.add(String.format(Locale.getDefault(), "%s·%s", getNP(a) , getNP(mMrPxn)));
                    row.add(String.format(Locale.getDefault(), " = %s", getNP(aMx)));
                    row.add(String.format(Locale.getDefault(), " = %s (mod %s)", getNP(aMxMODm), getNP(m))); // Calculated b


                    tabular.addRow(row);

                }

                result.append(tabular.render());
                result.append("⋮");
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