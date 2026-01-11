package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.formatSigned;
import android.util.Log;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;
import java.util.Locale;


/**
 *
 * @see <a href="http://www.math.vt.edu/people/brown/doc/sqrts.pdf">sqrts</a>
 */
public class TonelliShanksAlgorithm extends Algorithm implements StringCalculator {
    private final static String TAG = TonelliShanksAlgorithm.class.getSimpleName();

    public TonelliShanksAlgorithm(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger a = algorithmParameters.getInput1();
            BigInteger p = algorithmParameters.getInput2();

            // Tonelli-Shanks Algorithm
            result.append(String.format(Locale.getDefault(),"<font color='%s'><b>Tonelli-Shanks Algorithm</b></font><br>", COLOR));

            // Solve for x, quadratic residue modulo x² ≡ a (mod p), where p ∊ PRIMES
            result.append("Solve for <b>x</b>, quadratic residue modulo <b>x</b>² ≡ a (mod p), where p ∊ PRIMES<br>");
            result.append(String.format(Locale.getDefault(), "<b>x</b>² ≡ %s (mod %s)<br>", formatSigned(a), formatSigned(p)));
            result.append("<br>");

            //
            result.append(String.format(Locale.getDefault(),"<font color='%s'>InputGroup </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(),"%sa = %s<br>", TAB, formatSigned(a)));
            result.append(String.format(Locale.getDefault(),"%sp = %s<br>", TAB, formatSigned(p)));
            result.append("<br>");

            // p must be prime. Check if p is prime.
            // The higher the certainty number you pass, the more certain you can be,
            // i.e. 100 means it's prime with probability 1 - (1/2)^100, which is extremely close to 1.
            // For reference: if certainty is 10, then 1 - 1/2^10 is approximately 99.9%
            if ((p.isProbablePrime(10))) {
                result.append(String.format(Locale.getDefault(),"<font color='%s'>If p is prime, then continue... </font><br>", COLOR));
                result.append("p is probable prime <br>");
                // result.append(String.format(LOCALE,"<font color='%s'>Continue... </font><br>", COLOR));
                result.append("<br>");
            } else {
                result.append(String.format(Locale.getDefault(),"<font color='%s'>If p is not prime, then stop.</font><br>", COLOR));
                result.append("p is not prime");
                // result.append(String.format(LOCALE,"<font color='%s'>Stop. </font>", COLOR));
                return result.toString();
            }

            // Check if GCD(a, p) = 1
            //BigInteger gcd_a_p = a.gcd(p);
            if (a.gcd(p).equals(ONE)) {
                result.append(String.format(Locale.getDefault(),"<font color='%s'>If p is relatively prime to a, then continue... </font><br>", COLOR));
                result.append("GCD(a, p) = 1 <br>");
                // result.append(String.format(LOCALE,"<font color='%s'>%sContinue...</font><br>", COLOR, TAB));
                result.append("<br>");
            } else {
                result.append(String.format(Locale.getDefault(),"<font color='%s'>If p is not relatively prime to a, then stop.</font><br>", COLOR));
                result.append(String.format(Locale.getDefault(),"GCD(a, p) = %s ≠ 1", a.gcd(p)));
                //result.append(String.format(LOCALE,"<font color='%s'>%sStop.</font>", COLOR, TAB));
                return result.toString();
            }

            // Calculate a⁽ᵖ⁻¹⁾ᐟ² (mod p)
            // Use Euler's Criterion to check if there is a solution or not.
            // If a⁽ᵖ⁻¹⁾ᐟ² (mod p) ≡ -1, then by Euler's Criterion, a has no square root (mod p)
            // result.append(String.format(LOCALE,"<font color='%s'>Check by Euler's Criterion, if a has square root (mod p)</font><br>", COLOR));
            BigInteger exp = p.subtract(ONE).divide(TWO); // Calculate (p-1)/2
            BigInteger mod = a.modPow(exp, p); // Calculate a⁽ᵖ⁻¹⁾ᐟ² (mod p)
            if(mod.equals(p.subtract(ONE))) {
                result.append(String.format(Locale.getDefault(), "<font color='%s'>If a<sup><small><small>(p-1)/2</small></small></sup> ≡ -1 (mod p), then by Euler's Criterion a has no square root (mod p)</font><br>", COLOR));
                result.append(String.format(Locale.getDefault(), "%s<sup><small><small>(%s-1)/2</small></small></sup> = %s<sup><small><small>%s</small></small></sup> = %s ≡ -1 (mod %s)<br>", a, p, a, exp, mod, p));
                result.append(String.format(Locale.getDefault(),"<font color='%s'>There is no sqrt solution. </font>", COLOR));
                return result.toString();
            } else {
                result.append(String.format(Locale.getDefault(), "<font color='%s'>If a<sup><small><small>(p-1)/2</small></small></sup> ≡ 1 (mod p), then continue..., as by Euler's Criterion a has square root (mod p)</font><br>", COLOR));
                result.append(String.format(Locale.getDefault(), "%s<sup><small><small>(%s-1)/2</small></small></sup> = %s<sup><small><small>%s</small></small></sup> ≡ %s (mod %s)<br>", a, p, a, exp, mod, p));
                //result.append(String.format(LOCALE,"<font color='%s'>%sContinue...</font><br>", COLOR, TAB));
                result.append("<br>");
            }

            // Check if p ≡ 3 (mod 4) then return x = a⁽ᵖ⁺¹⁾ᐟ⁴ (mod p)
            // result.append(String.format(LOCALE,"<font color='%s'>Check if p ≡ 3 (mod 4)</font><br>", COLOR));
            BigInteger pmod4 = p.mod(FOUR);
            if (pmod4.equals(THREE)) {
                BigInteger x = a.modPow(p.add(ONE).divide(FOUR), p);
                result.append(String.format(Locale.getDefault(),"<font color='%s'>IF p ≡ 3 (mod 4), then return <b>x</b> = a<sup><small><small>(p+1)/4</small></small></sup> (mod p)</font><br>", COLOR));
                result.append(String.format(Locale.getDefault(),"%s ≡ %s (mod 4)<br>", p, pmod4));
                result.append(String.format(Locale.getDefault(),"<b>x</b> = %s<sup><small><small>(%s+1)/4</small></small></sup> (mod %s) = %s<br>", a, p, p, x));
                result.append("<br>");
                result.append(getTonelliShanksResult(a, p, x));
                return result.toString();
            } else {
                result.append(String.format(Locale.getDefault(),"<font color='%s'>IF p ≢ 3 (mod 4), then continue...</font><br>", COLOR));
                result.append(String.format(Locale.getDefault(),"%s ≡ %s (mod 4)<br>", p, pmod4));
                // result.append(String.format(LOCALE,"<font color='%s'>Continue... </font><br>", COLOR));
                result.append("<br>");
            }

            // Calculate s,e where p-1=s2ᵉ with a odd and e positive.
            result.append(String.format(Locale.getDefault(),"<font color='%s'>Calculate s,e where p-1 = s·2<sup><small><small>e</small></small></sup> with s odd and e positive </font><br>", COLOR));
            BigInteger s = p.subtract(ONE);
            BigInteger e = ZERO;
            result.append(String.format(Locale.getDefault(), "%s-1 = %s = %s·2<sup><small><small>%s</small></small></sup>" + "<br>", p, p.subtract(ONE), s, e)) ;
            while (s.mod(TWO).equals(ZERO)) {
                AlgorithmHelper.checkIfCanceled();

                s = s.divide(TWO);
                e = e.add(ONE);
                result.append(String.format(Locale.getDefault(), "%s-1 = %s = %s·2<sup><small>%s</small></sup>", p, p.subtract(ONE), s, e));
                if (s.mod(TWO).equals(ZERO)) {
                    result.append("<br>");
                } else {
                    result.append(String.format(Locale.getDefault(), "<font color='%s'> %s s = %s stop here, s is odd</font>" + "<br>", COLOR, STOP, s));
                }
            }
            result.append(String.format(Locale.getDefault(), "s = %s<br>", s));
            result.append(String.format(Locale.getDefault(), "e = %s<br>", e));
            result.append("<br>");

            // Find a number n such that n⁽ᵖ⁻¹⁾ᐟ² ≡ -1 (mod p). Some number that does not have a square root (mod p)
            result.append(String.format(Locale.getDefault(),"<font color='%s'>Find a number n such that n<sup><small><small>(p-1)/2</small></small></sup> ≡ -1 (mod p). Some number that does not have a square root (mod p) </font><br>", COLOR));
            BigInteger n = TWO; // Start from 2
            while (n.modPow(p.subtract(ONE).divide(TWO), p).equals(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                if (n.equals(TWO)) {
                    result.append(String.format(Locale.getDefault(), "%s<sup><small><small>%s</small></small></sup> ≡ %s (mod %s)<font color='%s'> %s start from n=2</font><br>", n, exp, n.modPow(p.subtract(ONE).divide(TWO), p), p, COLOR, STOP)) ;
                } else {
                    result.append(String.format(Locale.getDefault(), "%s<sup><small><small>%s</small></small></sup> ≡ %s (mod %s)<br>", n, exp, n.modPow(p.subtract(ONE).divide(TWO), p), p)) ;
                }
                n = n.add(ONE);
            }
            result.append(String.format(Locale.getDefault(), "%s<sup><small><small>%s</small></small></sup> ≡ %s (mod %s)<font color='%s'> %s stop here</font><br>", n, exp, n.modPow(p.subtract(ONE).divide(TWO), p).subtract(p), p, COLOR, STOP)) ;
            result.append(String.format(Locale.getDefault(), "n = %s<br>", n));
            result.append("<br>");

            // Initialize
            result.append(String.format(Locale.getDefault(),"<font color='%s'>Initialize </font><br>", COLOR));
            BigInteger x = a.modPow(s.add(ONE).divide(TWO), p); // First guess at the square root
            BigInteger b = a.modPow(s, p); // First guess at the fudge factor
            BigInteger g = n.modPow(s, p); // Powers of g will update both x and b
            BigInteger r = e; // Exponent will decrease with each update of the algorithm
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>x</b> = a<sup><small><small>(s+1)/2</small></small></sup> (mod p), First guess at the square root</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>b = a<sup><small><small>s</small></small></sup> (mod p), First guess at the fudge factor</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>g = n<sup><small><small>s</small></small></sup> (mod p), Powers of g will update both <b>x</b> and b</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>r = e, Exponent will decrease with each update of the algorithm</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Note that <b>x</b>² ≡ ba (mod p) </font><br>", COLOR)); // Note that x² ≡ ba (mod p)
            result.append(String.format(Locale.getDefault(), "<b>x</b> = %s<sup><small><small>(%s+1)/2</small></small></sup> (mod %s) = %s<sup><small><small>%s</small></small></sup> (mod %s) = %s<br>", a, s, p, a, s.add(ONE).divide(TWO), p, x)) ;
            result.append(String.format(Locale.getDefault(), "b = %s<sup><small><small>%s</small></small></sup> (mod %s) = %s<br>", a, s, p, b)) ;
            result.append(String.format(Locale.getDefault(), "g = %s<sup><small><small>%s</small></small></sup> (mod %s) = %s<br>", n, s, p, g)) ;
            result.append(String.format(Locale.getDefault(), "r = %s<br>", r)) ;
            result.append("<br>");

            // Find m such that, 0 ≤ m ≤ r-1 and b^{2^{m}} (mod p) = 1
            Ord ord = calculateOrd(b, p, r);
            BigInteger m = ord.getM(); // Get the calculated m
            result.append(ord.getResult()); // Get the result logs
            result.append("<br>");

            // Check m ≠ -1
            if(m.equals(ONE.negate())) {
                result.append(String.format(Locale.getDefault(), "<font color='%s'>Something went wrong calculating m </font>", COLOR));
                return result.toString();
            }

            // Check if m = 0
            // result.append(String.format(LOCALE,"<font color='%s'>Check if m = 0</font><br>", COLOR));
            if(m.equals(ZERO)) {
                result.append(String.format(Locale.getDefault(), "<font color='%s'>IF m = 0, then return <b>x</b> </font><br>", COLOR));
                result.append(String.format(Locale.getDefault(), "m = %s<br>", m));
                result.append("<br>");
                result.append(getTonelliShanksResult(a, p, x));
                return result.toString();
            } else {
                result.append(String.format(Locale.getDefault(), "<font color='%s'>IF m > 0, update variables... </font><br>", COLOR));
                result.append(String.format(Locale.getDefault(), "m = %s<br>", m));
                result.append("<br>");
            }

            //
            while (m.compareTo(ZERO) > 0) {
                AlgorithmHelper.checkIfCanceled();

                result.append(String.format(Locale.getDefault(),"<font color='%s'>Update variables, repeat until m = 0 </font><br>", COLOR));
                // x
                result.append("<b>x</b> = xg<sup><small><small>2<sup><small><small>r-m-1</small></small></sup></small></small></sup> (mod p) = ");
                result.append(String.format(Locale.getDefault(), " %s·%s<sup><small><small>2<sup><small><small>%s-%s-1</small></small></sup></small></small></sup> (mod %s) = ", x, g, r, m, p)) ;
                x = (x.multiply(g.modPow(calculateTwoExp(r.subtract(m).subtract(BigInteger.ONE)), p))).mod(p);
                result.append(String.format(Locale.getDefault(), "%s<br>", x)) ;
                // b
                result.append("b = bg<sup><small><small>2<sup><small><small>r-m</small></small></sup></small></small></sup> (mod p) = ");
                result.append(String.format(Locale.getDefault(), " %s·%s<sup><small><small>2<sup><small><small>%s-%s</small></small></sup></small></small></sup> (mod %s) = ", b, g, r, m, p)) ;
                b = (b.multiply(g.modPow(calculateTwoExp(r.subtract(m)), p))).mod(p);
                result.append(String.format(Locale.getDefault(), "%s<br>", b)) ;
                // g
                result.append("g = g<sup><small><small>2<sup><small><small>r-m</small></small></sup></small></small></sup> (mod p) = ");
                result.append(String.format(Locale.getDefault(), " %s<sup><small><small>2<sup><small><small>%s-%s</small></small></sup></small></small></sup> (mod %s) = ", g, r, m, p)) ;
                g = g.modPow(calculateTwoExp(r.subtract(m)), p);
                result.append(String.format(Locale.getDefault(), "%s<br>", g)) ;
                // r
                r = m;
                result.append(String.format(Locale.getDefault(), "r = m = %s<br>", m));
                //
                result.append("<br>");
                //
                ord = calculateOrd(b, p, r);
                m = ord.getM(); // Get the calculated m
                result.append(ord.getResult()); // Get the result logs
                //result.append(String.format(LOCALE, "%sm = %s<br>", TAB, m));
                result.append("<br>");
            }

            // Check if m = 0
            // result.append(String.format(LOCALE,"<font color='%s'>Check if m = 0 </font><br>", COLOR));
            if(m.equals(ZERO)) {
                result.append(String.format(Locale.getDefault(), "<font color='%s'>IF m = 0, then return <b>x</b></font><br>", COLOR));
                result.append(String.format(Locale.getDefault(), "m = %s<br>", m));
                result.append("<br>");
                result.append(getTonelliShanksResult(a, p, x));
                return result.toString();
            }

            //
            result.append(getTonelliShanksResult(a, p, x));

            // Return
            return  result.toString();
        } catch (InterruptedException ex) {
            // This specifically handles the cancellation.
            // Re-throw it so ProgressManager can handle it correctly.
            throw ex;
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return ex.toString();
        }
    }


    private static String getTonelliShanksResult(BigInteger a, BigInteger p, BigInteger x) {
        StringBuilder result = new StringBuilder();
        try {
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Output </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<b>x</b> = %s<br>", x)) ;
            result.append(String.format(Locale.getDefault(), "<b>x</b><sup><small><small>2</small></small></sup> = %s<sup><small><small>2</small></small></sup> = %s ≡ %s (mod %s)<br>", x, x.pow(2), x.pow(2).mod(p), p));
            result.append(String.format(Locale.getDefault(), "a = %s ≡ %s (mod %s)<br>", a, a.mod(p), p)) ;
            result.append("<br>");
            //
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Check correctness </font><br>", COLOR));
            if (x.pow(2).mod(p).equals(a.mod(p))) {
                result.append(String.format(Locale.getDefault(), "%s<sup><small><small>2</small></small></sup> ≡ %s (mod %s)<br>", x, a, p)) ;
                result.append(String.format(Locale.getDefault(), "<font color='%s'>Correct.</font>", COLOR));
            } else {
                result.append(String.format(Locale.getDefault(), "%s<sup><small><small>2</small></small></sup> ≢ %s (mod %s)<br>", x, a, p)) ;
                result.append(String.format(Locale.getDefault(), "<font color='%s'>Something went wrong.</font>", COLOR));
            }

            return result.toString();
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return ex.toString();
        }
    }


    private static BigInteger calculateTwoExp(BigInteger e) {
        BigInteger a = ONE;
        while (e.compareTo(ZERO) > 0) {
            a = a.multiply(TWO);
            e = e.subtract(ONE);
        }
        // Return 2ᵉ
        return a;
    }


    private static class Ord {

        BigInteger m;
        String result;
        void setM(BigInteger m) {
            this.m = m;
        }
        public void setResult(String result) {
            this.result = result;
        }
        BigInteger getM() {
            return m;
        }
        public String getResult() {
            return result;
        }

    }


    private static Ord calculateOrd(BigInteger b, BigInteger p, BigInteger r) {
        // Return the value and the result log
        Ord ord = new Ord();
        try {
            // Find m such that, 0 ≤ m ≤ r-1 and b^{2^{m}} (mod p) = 1
            StringBuilder result = new StringBuilder();
            result.append(String.format(Locale.getDefault(),"<font color='%s'>%s%sFind m such that, 0 ≤ m ≤ r-1 and b<sup><small><small>2<sup><small><small>m</small></small></sup></small></small></sup> (mod p) = 1</font><br>", COLOR, BULLET, TAB));
            //
            for(BigInteger m = ZERO; m.compareTo(r.subtract(ONE)) <= 0; m = m.add(ONE)) {
                BigInteger mod = b.modPow(calculateTwoExp(m), p);
                result.append(String.format(Locale.getDefault(), "%sm = %s %s %s<sup><small><small>2<sup><small><small>%s</small></small></sup></small></small></sup> (mod %s) = %s", TAB, m, RIGHT_ARROW_COLORED, b, m, p, mod));
                if(mod.equals(ONE)) {
                    result.append(String.format(Locale.getDefault(), "<font color='%s'> %s stop here</font><br>", COLOR, STOP));
                    ord.setM(m);
                    ord.setResult(result.toString());
                    return ord;
                } else {
                    result.append("<br>");
                }
            }
            ord.setM(ONE.negate());
            ord.setResult(result.toString());
            return ord;
        } catch (Exception ex) {
            Log.e(TAG, "" + ex);
            return null;
        }
    }
}