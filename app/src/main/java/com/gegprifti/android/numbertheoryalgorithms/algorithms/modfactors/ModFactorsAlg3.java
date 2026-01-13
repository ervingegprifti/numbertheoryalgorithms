package com.gegprifti.android.numbertheoryalgorithms.algorithms.modfactors;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.formatSigned;

import android.util.Log;

import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;

import java.math.BigInteger;
import java.util.Locale;


public class ModFactorsAlg3 extends Algorithm implements StringCalculator {
    private final static String TAG = ModFactorsAlg3.class.getSimpleName();

    public ModFactorsAlg3(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger n = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();

            // Mod factors
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Mod factors: Alg 3</b></font><br>", COLOR));

            // Find n ≡ de (mod b) where
            result.append("Find n ≡ de (mod b) where <br>");
            result.append("(b<b>x</b> + e)(b<b>y</b> + d) = n <br>");
            result.append("b(b<b>x</b><b>y</b> + d<b>x</b> + e<b>y</b>) + de = n <br>");
            result.append("b<b>x</b><b>y</b> + d<b>x</b> + e<b>y</b> = (n-de)/b <br>");
            result.append("<br>");

            // Inputs
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Inputs</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "n = %s<br>", formatSigned(n)));
            result.append(String.format(Locale.getDefault(), "b = %s<br>", formatSigned(b)));
            result.append("<br>");

            // n (mod b) = r
            result.append(String.format(Locale.getDefault(), "<font color='%s'>n (mod b) = r</font><br>", COLOR));
            BigInteger r = n.mod(b);
            result.append(String.format(Locale.getDefault(), "n (mod b) = %s<br>", r));
            result.append("<br>");

            // gcd(n, b)
            result.append(String.format(Locale.getDefault(), "<font color='%s'>gcd(n, b)</font><br>", COLOR));
            BigInteger gcdnb = n.gcd(b);
            result.append(String.format(Locale.getDefault(), "gcd(%s, %s) = %s<br>", n, b, gcdnb));
            result.append("<br>");

            if (gcdnb.compareTo(ONE) == 0) {
                alg2CoprimeCase (n, b, r, result);
            } else {
                alg2NonCoprimeCase(n, b, r, result);
            }

            // Return
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


    /**
     * Used if gcd(n, b) = 1
     * @param b
     * @param r
     * @param result
     * @return
     * @throws InterruptedException
     */
    private void alg2CoprimeCase (BigInteger n, BigInteger b, BigInteger r, StringBuilder result) throws InterruptedException {
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Coprime case since gcd(n, b) = 1</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Possible de (mod b) factors (invertible d)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>For each d = {0, ..., b-1}</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Check if gcd(d, b) = 1</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Since gcd(d, b) = 1 then d has a multiplicative inverse modulo b</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>So there exists an integer d⁻¹ such that: d·d⁻¹ ≡ 1 (mod b)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Compute d⁻¹ (mod b)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Compute r·d⁻¹ (mod b) = e</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Compute (d·e) (mod b) = rem</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Check if rem = r</font><br>", COLOR));

        int counter = 0;

        // Track execution time start
        long start = System.nanoTime();

        // d = {0, ..., b-1}
        for (BigInteger d = ZERO; d.compareTo(b) < 0; d = d.add(ONE)) {
            AlgorithmHelper.checkIfCanceled();

            // Compute g = gcd(d, b)
            BigInteger g = d.gcd(b);
            // Check if gcd(d, b) = 1
            if (g.compareTo(ONE) == 0) {
                // Since gcd(d, b) = 1 then d has a multiplicative inverse modulo b
                // So there exists an integer d⁻¹ such that: d·d⁻¹ ≡ 1 (mod b)
                // Compute d⁻¹ (mod b)
                BigInteger dModInverse = d.modInverse(b);
                // Compute r·d⁻¹
                BigInteger rdModInverse = r.multiply(dModInverse);
                // Compute r·d⁻¹ (mod b) = e
                BigInteger e = rdModInverse.mod(b);
                // Compute d·e
                BigInteger de = d.multiply(e);
                // Compute (d·e) (mod b) = rem
                BigInteger rem = de.mod(b);

                // Check if rem = r
                if(rem.equals(r)) {
                    counter += 1;
                    BigInteger f = n.subtract(de).divide(b);
                    result.append(String.format(Locale.getDefault(), "b: %s<br>", b));
                    result.append(String.format(Locale.getDefault(), "d: %s<br>", d));
                    result.append(String.format(Locale.getDefault(), "e: %s<br>", e));
                    result.append(String.format(Locale.getDefault(), "f: %s<br>", f));
                    result.append("<br>");
                }
            }
        }
        result.append("<br>");

        // Track execution time end
        long end = System.nanoTime();
        long durationNs = end - start;
        String formatedExecutionTime = AlgorithmHelper.formatExecutionTime(durationNs);
        result.append(String.format(Locale.getDefault(), "<font color='%s'>" + formatedExecutionTime + "</font><br>", COLOR));
    }


    /**
     * Used if gcd(n, b) > 1
     * @param b
     * @param r
     * @param result
     * @return
     * @throws InterruptedException
     */
    private void alg2NonCoprimeCase(BigInteger n, BigInteger b, BigInteger r, StringBuilder result) throws InterruptedException {
        result.append(String.format(Locale.getDefault(),"<font color='%s'>Non coprime case since gcd(n, b) > 1</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>Possible de (mod b) factors (including non-invertible d)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>For each d = {0, ..., b-1}</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>Compute g = gcd(d, b)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>If g ∤ r skip</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>Reduce to d′e ≡ r′ (mod b′)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>Solve using modular inverse and lift solutions</font><br>", COLOR));

        int counter = 0;

        // Track execution time start
        long start = System.nanoTime();

        // d = {0, ..., b-1}
        for (BigInteger d = ZERO; d.compareTo(b) < 0; d = d.add(ONE)) {
            AlgorithmHelper.checkIfCanceled();

            // Compute g = gcd(d, b)
            BigInteger g = d.gcd(b);

            // If g does not divide r → no solutions
            if (!r.mod(g).equals(ZERO)) {
                continue;
            }

            BigInteger dPrime = d.divide(g);
            BigInteger rPrime = r.divide(g);
            BigInteger bPrime = b.divide(g);

            // d' and b' are coprime, hence inverse exists
            BigInteger dPrimeInverse = dPrime.modInverse(bPrime);

            // base solution modulo b'
            BigInteger e0 = rPrime.multiply(dPrimeInverse).mod(bPrime);

            // lift solutions
            for (BigInteger k = ZERO; k.compareTo(g) < 0; k = k.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                BigInteger e = e0.add(k.multiply(bPrime));
                BigInteger de = d.multiply(e);
                BigInteger rem = de.mod(b);

                String resultString = "%s·%s = %s ≡ %s (mod %s)<br>";

                // Check if rem = r
                if(rem.equals(r)) {
                    counter += 1;
                    BigInteger f = n.subtract(de).divide(b);
                    result.append(String.format(Locale.getDefault(), "b: %s<br>", b));
                    result.append(String.format(Locale.getDefault(), "d: %s<br>", d));
                    result.append(String.format(Locale.getDefault(), "e: %s<br>", e));
                    result.append(String.format(Locale.getDefault(), "f: %s<br>", f));
                    result.append("<br>");
                }
            }
        }
        result.append("<br>");

        // Track execution time end
        long end = System.nanoTime();
        long durationNs = end - start;
        String formatedExecutionTime = AlgorithmHelper.formatExecutionTime(durationNs);
        result.append(String.format(Locale.getDefault(), "<font color='%s'>" + formatedExecutionTime + "</font><br>", COLOR));
    }


    private static class AlgResultSet {
        int counter;
        long durationNs;
    }

}
