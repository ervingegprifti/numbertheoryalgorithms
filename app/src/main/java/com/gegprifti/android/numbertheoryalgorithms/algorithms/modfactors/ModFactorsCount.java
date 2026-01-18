package com.gegprifti.android.numbertheoryalgorithms.algorithms.modfactors;


import android.util.Log;

import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;

import java.math.BigInteger;
import java.util.Locale;


public class ModFactorsCount extends Algorithm implements StringCalculator {
    private final static String TAG = ModFactorsCount.class.getSimpleName();

    public ModFactorsCount(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger n = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();

            // Mod factors count
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Mod factors count </font><br>", COLOR));
            result.append("Find n ≡ de (mod b) where <br>");
            result.append("(b<b>x</b> + e)(b<b>y</b> + d) = n </font><br>");
            result.append("b(b<b>x</b><b>y</b> + d<b>x</b> + e<b>y</b>) + de = n </font><br>");
            result.append("b<b>x</b><b>y</b> + d<b>x</b> + e<b>y</b> = (n-de)/b </font><br>");
            result.append("<br>");

            // Inputs
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Inputs</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "n = %s<br>", n));
            result.append(String.format(Locale.getDefault(), "b = %s<br>", b));
            result.append("<br>");

            // Count possible de mod factors for each d,e = {0, ... , b'-1}, b' = {2, ... , b}
            result.append(String.format(Locale.getDefault(), "<font color='%s'>For each b' = {2, ..., b} </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Compute n (mod b') = r</font><br>",COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>For each d,e = {0, ..., b'-1} count</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>de ≡ r (mod b') possible factors</font><br>", COLOR));

            int bCharacters = b.toString().length();

            // Track execution time start
            long start = System.nanoTime();

            // b' = {2, ... , b}
            for(BigInteger bPrime = TWO; bPrime.compareTo(b) <= 0; bPrime = bPrime.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                String bPrimStringFormat = AlgorithmHelper.paddingCharacters(bPrime, bCharacters);
                BigInteger r = n.mod(bPrime);
                String rStringFormat = AlgorithmHelper.paddingCharacters(r, bCharacters);

                // // Slow mod factors counter brute force.
                // int counter = 0;
                // // d = {0, ... , b'-1}
                // for (BigInteger d = ZERO; d.compareTo(bPrime) < 0; d = d.add(ONE)) {
                //     AlgorithmHelper.checkIfCanceled();
                //     // e = {0, ... , b'-1}
                //     for (BigInteger e = ZERO; e.compareTo(bPrime) < 0; e = e.add(ONE)) {
                //         AlgorithmHelper.checkIfCanceled();
                //         BigInteger de = d.multiply(e);
                //         BigInteger rem = de.mod(bPrime);
                //         if(rem.equals(r)) {
                //             counter += 1;
                //         }
                //     }
                // }
                // String counterStringFormat = AlgorithmHelper.paddingCharacters(counter, bCharacters + 1);
                // result.append(String.format(Locale.getDefault(), "de ≡ %s (mod %s) %s %s<br>", rStringFormat, bPrimStringFormat, RIGHT_ARROW_COLORED, counterStringFormat));

                // Fast mod factors counter.
                BigInteger gcdnbPrime = n.gcd(bPrime);
                int counter = 0;
                if (gcdnbPrime.compareTo(ONE) == 0) {
                    counter = alg2CoprimeCase (bPrime, r);
                } else {
                    counter = alg2NonCoprimeCase(bPrime, r);
                }
                String counterStringFormat = AlgorithmHelper.paddingCharacters(counter, bCharacters + 1);

                BigInteger percent = BigInteger.valueOf(counter).multiply(BigInteger.valueOf(100)).divide(bPrime);
                String percentToDots = "<small><small>" + percentToDots(percent, 100) + "</small></small>";

                result.append(String.format(Locale.getDefault(), "de ≡ %s (mod %s) %s %s %s<br>",
                        rStringFormat, bPrimStringFormat, RIGHT_ARROW_COLORED, counterStringFormat, percentToDots));
            }

            // Track execution time end
            long end = System.nanoTime();
            long durationNs = end - start;
            String formatedExecutionTime = AlgorithmHelper.formatExecutionTime(durationNs);
            result.append("<br>");
            result.append(String.format(Locale.getDefault(), "<font color='%s'>" + formatedExecutionTime + "</font>", COLOR));

            // Return
            return  result.toString();
        } catch (InterruptedException ex) {
            // This specifically handles the cancellation.
            // Re-throw it so ProgressManager can handle it correctly.
            throw ex;
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
            return ex.toString();
        }
    }


    private int alg2CoprimeCase (BigInteger b, BigInteger r) throws InterruptedException {
        int counter = 0;

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
                }
            }
        }

        return counter;
    }


    private int alg2NonCoprimeCase(BigInteger b, BigInteger r) throws InterruptedException {
        int counter = 0;

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

                // Check if rem = r
                if(rem.equals(r)) {
                    counter += 1;
                }
            }
        }

        return counter;
    }


    static String percentToDots(BigInteger percent, int totalDots) {
        BigInteger hundred = BigInteger.valueOf(100);
        BigInteger dots = BigInteger.valueOf(totalDots);

        // Clamp percent
        BigInteger clamped = percent.max(BigInteger.ZERO).min(hundred);

        // Proportional rounding
        int filled = clamped.multiply(dots)
                        .add(hundred.divide(BigInteger.valueOf(2))) // +50
                        .divide(hundred)
                        .intValue();

        filled = Math.min(totalDots, Math.max(0, filled));

        return "■".repeat(filled) + "□".repeat(totalDots - filled);
        // return ".".repeat(filled);
    }


}