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
 * x + y ➡ xPy<br>
 * x - y ➡ xSy<br>
 * x * y ➡ xMy<br>
 * x / y ➡ xDy<br>
 *
 * @see <a href="https://math.stackexchange.com/questions/37806/extended-euclidean-algorithm-with-negative-numbers">Extended Euclidean algorithm with negative numbers</a>
 */
public class LinearCongruenceInTwoVariables extends Algorithm implements StringCalculator {
    private final static String TAG = LinearCongruenceInTwoVariables.class.getSimpleName();


    public LinearCongruenceInTwoVariables(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();
            BigInteger c = algorithmParameters.getInput3();
            BigInteger m = algorithmParameters.getInput4();

            // Solving linear Congruence ax+by ≡ c (mod m)
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Linear Congruence In Two Variables</b></font><br>", COLOR));
            result.append("Solve for <b>x</b>,<b>y</b> a<b>x</b>+b<b>y</b> ≡ c (mod m) where a,b,c,<b>x</b>,<b>y</b> ∊ ℤ, m ∊ ℕ<br>");
            result.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b> ≡ %s (mod %s)<br>", getNP(a), getNP(b), getNP(c), getNP(m)));
            result.append("<br>");

            // Input
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Input</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "a = %s <br>", getNP(a)));
            result.append(String.format(Locale.getDefault(), "b = %s <br>", getNP(b)));
            result.append(String.format(Locale.getDefault(), "c = %s <br>", getNP(c)));
            result.append(String.format(Locale.getDefault(), "m = %s <br>", getNP(m)));
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
            result.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b>+%s<b>z</b>=%s<br>", getNP(a), getNP(b), getNP(m), getNP(c)));
            result.append("<br>");

            // Let h=GCD(a,b), d=a/h, e=b/h
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Let h=GCD(a,b), d=a/h, e=b/h</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "h = GCD(a, b) = GCD(%s, %s) = %s <br>", getNP(a), getNP(b), getNP(h)));
            result.append(String.format(Locale.getDefault(), "d = a/h = %s/%s = %s <br>", getNP(a), getNP(h), getNP(d)));
            result.append(String.format(Locale.getDefault(), "e = b/h = %s/%s = %s <br>", getNP(b), getNP(h), getNP(e)));
            result.append("<br>");

            // Factoring out ax+by we get h(dx+ey)+mz=c
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Factoring out a<b>x</b>+b<b>y</b> we get h(d<b>x</b>+e<b>y</b>)+m<b>z</b>=c</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s(%s<b>x</b>+%s<b>y</b>)+%s<b>z</b>=%s<br>", getNP(h), getNP(d), getNP(e), getNP(m), getNP(c)));
            result.append("<br>");

            // Note that GCD(d,e) is always 1, since d = a/h and e = b/h
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Note that GCD(d,e) is always 1, since d = a/h and e = b/h</font><br>", COLOR));
            result.append("<br>");

            // Let dx+ey=w
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Let d<b>x</b>+e<b>y</b>=<b>w</b></font> <br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b>=<b>w</b> <br>", getNP(d), getNP(e)));
            result.append("<br>");

            // Rewriting we must solve hw+mz=c
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Rewriting we must solve h<b>w</b>+m<b>z</b>=c</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s<b>w</b>+%s<b>z</b>=%s<br>", getNP(h), getNP(m), getNP(c)));
            result.append("<br>");

            BigInteger i = h.gcd(m).gcd(c);

            // Simplify hw+mz=c by dividing both sides with i=GCD(h,m,c) to get jw+nz=f
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Simplify h<b>w</b>+m<b>z</b>=c by dividing both sides with i=GCD(h,m,c) to get j<b>w</b>+n<b>z</b>=f </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "i = GCD(h,m,c) = GCD(%s, %s, %s) = %s <br>", h, m, c, i));

            // Simplified
            BigInteger j = h.divide(i);
            BigInteger n = m.divide(i);
            BigInteger f = c.divide(i);

            result.append(String.format(Locale.getDefault(), "j = h/i = %s/%s = %s <br>", getNP(h), i, getNP(j)));
            result.append(String.format(Locale.getDefault(), "n = m/i = %s/%s = %s <br>", getNP(m), i, getNP(n)));
            result.append(String.format(Locale.getDefault(), "f = c/i = %s/%s = %s <br>", getNP(c), i, getNP(f)));
            result.append(String.format(Locale.getDefault(), "%s<b>w</b>+%s<b>z</b>=%s<br>", getNP(j), getNP(n), getNP(f)));
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

            BigInteger[] eeaForWandZ = AlgorithmHelper.calculateEEA(j.abs(), n.abs());
            BigInteger wee = getSign(j).multiply(eeaForWandZ[1]);
            BigInteger zee = getSign(n).multiply(eeaForWandZ[2]);

            // Use Extended Euclidean Algorithm to find wₑₑ and zₑₑ from |j|w + |n|z = GCD(|j|, |n|) = k
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Use Extended Euclidean Algorithm to find wₑₑ and zₑₑ from |j|<b>w</b> + |n|<b>z</b> = GCD(|j|, |n|) = k </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "wₑₑ = sign(j)·wₑₑ = %s·%s = %s <br>", getNP(getSign(j)), getNP(eeaForWandZ[1]), wee));
            result.append(String.format(Locale.getDefault(), "zₑₑ = sign(n)·zₑₑ = %s·%s = %s <br>", getNP(getSign(n)), getNP(eeaForWandZ[2]), zee));
            result.append("<br>");

            BigInteger w0 = (wee.multiply(f.divide(k)));
            BigInteger z0 = (zee.multiply(f.divide(k)));

            // A particular first initial solution is w₀ = wₑₑ(f/k) and z₀ = zₑₑ(f/k)
            result.append(String.format(Locale.getDefault(), "<font color='%s'>A particular first initial solution is w₀ = wₑₑ(f/k) and z₀ = zₑₑ(f/k) </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "w₀ = %s·(%s/%s) = %s <br>", getNP(wee), getNP(f), getNP(k), getNP(w0)));
            result.append(String.format(Locale.getDefault(), "z₀ = %s·(%s/%s) = %s <br>", getNP(zee), getNP(f), getNP(k), getNP(z0)));
            result.append("<br>");

            BigInteger p = n.divide(k);
            BigInteger q = j.divide(k);

            // For r ∊ ℤ, the general solution to jw+nz=f is w = w₀ + (n/k)r and z = z₀ - (j/k)r
            result.append(String.format(Locale.getDefault(), "<font color='%s'>For r ∊ ℤ, the general solution to j<b>w</b>+n<b>z</b>=f is</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<b>w</b> = %s + (%s/%s)r<br>", getNP(w0), getNP(n), getNP(k)));
            result.append(String.format(Locale.getDefault(), "<b>z</b> = %s - (%s/%s)r<br>", getNP(z0), getNP(j), getNP(k)));
            result.append("<br>");

            // Let p=(n/k) and q=(j/k), hence the general solution to jw+nz=f is w = w₀ + (n/k)r = w₀ + pr and z = z₀ - (j/k)r = z₀ - qr
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Let p=(n/k) and q=(j/k), hence the general solution to j<b>w</b>+n<b>z</b>=f is</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>w</b> = w₀ + (n/k)r = w₀ + p<b>r</b> </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>z</b> = z₀ - (j/k)r = z₀ - q<b>r</b> </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<b>w</b> = %s + %s<b>r</b> <br>", getNP(w0), getNP(p)));
            result.append(String.format(Locale.getDefault(), "<b>z</b> = %s - %s<b>r</b> <br>", getNP(z0), getNP(q)));
            result.append("<br>");

            // Substituting for w, then we have  dx+ey = w₀+pr
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Substituting for <b>w</b>, then d<b>x</b>+e<b>y</b> = w₀+p<b>r</b></font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s<b>x</b>+%s<b>y</b> = %s+%s<b>r</b> <br>", getNP(d), getNP(e), getNP(w0), getNP(p)));
            result.append("<br>");

            // Since GCD(d,e) is always 1, then we find x₀ and y₀ by solving dx+ey=1
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Since GCD(d,e) is always 1, then we find x₀ and y₀ by solving d<b>x</b>+<b>e</b>y=1</font><br>", COLOR));
            result.append("<br>");

            BigInteger[] eeaForXandY = AlgorithmHelper.calculateEEA(d.abs(), e.abs());
            BigInteger xee = getSign(d).multiply(eeaForXandY[1]);
            BigInteger yee = getSign(e).multiply(eeaForXandY[2]);

            // Use Extended Euclidean Algorithm to find xₑₑ and yₑₑ from |d|x + |e|y = GCD(|d|, |e|) = 1
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Use Extended Euclidean Algorithm to find xₑₑ and yₑₑ from |d|<b>x</b> + |e|<b>y</b> = GCD(|d|, |e|) = 1</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "xₑₑ = sign(d)·xₑₑ = %s·%s = %s <br>", getNP(getSign(d)), getNP(eeaForXandY[1]), xee));
            result.append(String.format(Locale.getDefault(), "yₑₑ = sign(e)·yₑₑ = %s·%s = %s <br>", getNP(getSign(e)), getNP(eeaForXandY[2]), yee));
            result.append("<br>");

            // Hence dxₑₑ+eyₑₑ = 1
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Hence dxₑₑ+eyₑₑ = 1</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "dxₑₑ+eyₑₑ = %s·%s+%s·%s = 1 <br>", getNP(d), getNP(xee), getNP(e), getNP(yee)));
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
            result.append(String.format(Locale.getDefault(), "x₀ = %s·%s+%s·%s<b>r</b> = %s + %s<b>r</b><br>", getNP(xee), getNP(w0), getNP(xee), getNP(p), getNP(xeew0), getNP(xeep)));
            result.append(String.format(Locale.getDefault(), "y₀ = %s·%s+%s·%s<b>r</b> = %s + %s<b>r</b><br>", getNP(yee), getNP(w0), getNP(yee), getNP(p), getNP(yeew0), getNP(yeep)));
            result.append("<br>");

            // The general x,y solution is x = x₀ + (e/1)t = xₑₑw₀+xₑₑpr + et and y = y₀ - (d/1)t = yₑₑw₀+yₑₑpr - dt
            result.append(String.format(Locale.getDefault(), "<font color='%s'>The general <b>x</b>,<b>y</b> solution is</font><br>", COLOR));
            result.append("<b>x</b> = x₀ + (e/1)t = xₑₑw₀+xₑₑp<b>r</b> + e<b>t</b> <br>");
            result.append("<b>y</b> = y₀ - (d/1)t = yₑₑw₀+yₑₑp<b>r</b> - d<b>t</b> <br>");
            result.append("<br>");
            result.append(String.format(Locale.getDefault(), "<b>x</b> = %s+%s<b>r</b> + %s<b>t</b> <br>", getNP(xeew0), getNP(xeep), getNP(e)));
            result.append(String.format(Locale.getDefault(), "<b>y</b> = %s+%s<b>r</b> - %s<b>t</b> <br>", getNP(yeew0), getNP(yeep), getNP(d)));
            result.append("<br>");

            Tabular tabular = new Tabular();

            // We negated m previously. Set m to |m| again.
            m = m.abs();

            // The congruence ax+by ≡ c (mod m) can be written as a(xₑₑw₀ + xₑₑpr + et) + b(yₑₑw₀ + yₑₑpr - dt) ≡ c (mod m)
            result.append(String.format(Locale.getDefault(), "<font color='%s'>The congruence a<b>x</b>+b<b>y</b> ≡ c (mod m) can be written as </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>a(xₑₑw₀ + xₑₑp<b>r</b> + e<b>t</b>) + b(yₑₑw₀ + yₑₑp<b>r</b> - d<b>t</b>) ≡ c (mod m)</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "%s(%s + %s<b>r</b> + %s<b>t</b>) + %s(%s + %s<b>r</b> - %s<b>t</b>) ≡ %s (mod %s) <br>", getNP(a), getNP(xeew0), getNP(xeep), getNP(e), getNP(b), getNP(yeew0), getNP(yeep), getNP(d), getNP(c), getNP(m)));
            result.append("<br>");

            // Check correctness for r = {-3, ..., 3} and t = {-3, ..., 3} in a(xₑₑw₀+xₑₑpr + et) + b(yₑₑw₀+yₑₑpr - dt) ≡ c (mod m)
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Check correctness for <b>r</b> = {-3, ..., 3} and <b>t</b> = {-3, ..., 3} in</font><br>", COLOR));
            result.append("⋮<br>");
            BigInteger rMin = BigInteger.valueOf(-3);
            BigInteger rMax = BigInteger.valueOf(3);
            BigInteger tMin = BigInteger.valueOf(-3);
            BigInteger tMax = BigInteger.valueOf(3);
            for(BigInteger r = rMin; r.compareTo(rMax) <= 0; r = r.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();
                for(BigInteger t = tMin; t.compareTo(tMax) <= 0; t = t.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();
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
                    row.add(String.format(Locale.getDefault(), "%s(%s", getNP(a), getNP(xeew0)));
                    row.add(" + ");
                    row.add(String.format(Locale.getDefault(), "%s", getNP(xeepr)));
                    row.add(" + ");
                    row.add(String.format(Locale.getDefault(), "%s", getNP(et)));
                    row.add(") + ");
                    row.add(String.format(Locale.getDefault(), "%s(%s", getNP(b), getNP(yeew0)));
                    row.add(" + ");
                    row.add(String.format(Locale.getDefault(), "%s", getNP(yeepr)));
                    row.add(" - ");
                    row.add(String.format(Locale.getDefault(), "%s", getNP(dt)));
                    row.add(") = ");
                    row.add(String.format(Locale.getDefault(), "%s", axNewPLUSbyNew));
                    row.add(" ≡ ");
                    row.add(String.format(Locale.getDefault(), "%s", axNewPLUSbyNewMODm)); // Calculated c
                    row.add(String.format(Locale.getDefault(), " (mod %s)", m));
                    tabular.addRow(row);
                }
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