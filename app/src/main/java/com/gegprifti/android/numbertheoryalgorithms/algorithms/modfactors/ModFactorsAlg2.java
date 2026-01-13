package com.gegprifti.android.numbertheoryalgorithms.algorithms.modfactors;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.formatSigned;

import android.util.Log;

import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;

import java.math.BigInteger;
import java.util.Locale;


public class ModFactorsAlg2 extends Algorithm implements StringCalculator {
    private final static String TAG = ModFactorsAlg2.class.getSimpleName();

    public ModFactorsAlg2(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger n = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();

            // Mod factors
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Mod factors</b></font><br>", COLOR));

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

            AlgResultSet algorithm1ResultSet = alg1(b, r, result);
            int counter1 = algorithm1ResultSet.counter;
            long durationAlg1Ns = algorithm1ResultSet.durationNs;

            result.append("<br><br>");

            AlgResultSet algorithm2ResultSet = alg3 (b, r, result);
            int counter2 = algorithm2ResultSet.counter;
            long durationAlg2Ns = algorithm2ResultSet.durationNs;

            result.append("<br><br>");

            printAlgorithmPerformance(durationAlg1Ns, durationAlg2Ns, result);

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

    private AlgResultSet alg1 (BigInteger b, BigInteger r, StringBuilder result) throws InterruptedException {
        result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Alg 1 (Brute force)</b></font><br>", COLOR));
        // Possible de (mod d) factors for each d,e = {0, ..., b-1}
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Possible de (mod b) factors</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>For each d,e = {0, ..., b-1}</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Check de ≡ r (mod b)</font><br>", COLOR));

        int bCharacters = b.toString().length();
        int bbCharacters = b.multiply(b).toString().length();

        int counter = 0;

        // Track execution time start
        long start = System.nanoTime();
        // d = {0, ..., b-1}
        for (BigInteger d = ZERO; d.compareTo(b) < 0; d = d.add(ONE)) {
            AlgorithmHelper.checkIfCanceled();

            // e = {0, ..., b-1}
            for (BigInteger e = ZERO; e.compareTo(b) < 0; e = e.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                BigInteger de = d.multiply(e);
                BigInteger rem = de.mod(b);

                String dStringFormat = AlgorithmHelper.paddingCharacters(d, bCharacters);
                String dString;
                if (d.isProbablePrime(10)) {
                    dString = String.format(Locale.getDefault(), "<font color='%s'>%s</font>", COLOR_CIAN_DARK, dStringFormat);
                } else {
                    dString = dStringFormat;
                }

                String eStringFormat = AlgorithmHelper.paddingCharacters(e, bCharacters);
                String eString;
                if (e.isProbablePrime(10)) {
                    eString = String.format(Locale.getDefault(), "<font color='%s'>%s</font>", COLOR_CIAN_DARK, eStringFormat);
                } else {
                    eString = eStringFormat;
                }

                String deString = AlgorithmHelper.paddingCharacters(de, bbCharacters);
                String resultString = "de = %s·%s = %s ≡ %s (mod %s) | ind: %s<br>";

                if(r.equals(rem)) {
                    counter += 1;
                    result.append(String.format(Locale.getDefault(), resultString, dString, eString, deString, r, b, counter));
                }
            }
        }
        // Track execution time end
        long end = System.nanoTime();
        long durationNs = end - start;
        String formatedExecutionTime = formatExecutionTime(durationNs);
        result.append(String.format(Locale.getDefault(), "<font color='%s'>" + formatedExecutionTime + "</font>", COLOR));

        AlgResultSet algResultSet = new AlgResultSet();
        algResultSet.counter = counter;
        algResultSet.durationNs = durationNs;

        return algResultSet;
    }

    /**
     * Used if gcd(n, b) = 1
     * @param b
     * @param r
     * @param result
     * @return
     * @throws InterruptedException
     */
    private AlgResultSet alg2 (BigInteger b, BigInteger r, StringBuilder result) throws InterruptedException {
        result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Alg 2</b></font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Possible de (mod b) factors</font><br>", COLOR));

        int bCharacters = b.toString().length();
        int bbCharacters = b.multiply(b).toString().length();

        int counter = 0;

        // Track execution time start
        long start = System.nanoTime();
        // d = {0, ..., b-1}
        result.append(String.format(Locale.getDefault(), "<font color='%s'>For each d = {0, ..., b-1}</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Calculate gcd(d, b)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Check if gcd(d, b) = 1</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Since gcd(d, b) = 1 then d has a multiplicative inverse modulo b</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>So there exists an integer d⁻¹ such that: d·d⁻¹ ≡ 1 (mod b)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Calculate d⁻¹ (mod b)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Calculate e·d⁻¹</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Calculate r·d⁻¹ (mod b) = e</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Calculate d·e</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>rem = (d·e) (mod b)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(), "<font color='%s'>Check if r = rem</font><br>", COLOR));
        for (BigInteger d = ZERO; d.compareTo(b) < 0; d = d.add(ONE)) {
            AlgorithmHelper.checkIfCanceled();

            // Compute g = gcd(d, b)
            BigInteger g = d.gcd(b);
            // Check if gcd(d, b) = 1
            if (g.compareTo(ONE) == 0) {
                // Since gcd(d, b) = 1 then d has a multiplicative inverse modulo b
                // So there exists an integer d⁻¹ such that: d·d⁻¹ ≡ 1 (mod b)
                // Calculate the multiplicative inverse of d modulo b. Calculate d⁻¹ (mod b)
                BigInteger dModInverse = d.modInverse(b);
                // Calculate e·d⁻¹
                BigInteger rdModInverse = r.multiply(dModInverse);
                // Calculate r·d⁻¹ (mod b) = e
                BigInteger e = rdModInverse.mod(b);
                // Calculate d·e
                BigInteger de = d.multiply(e);
                // rem = (d·e) (mod b)
                BigInteger rem = de.mod(b);

                String dStringFormat = AlgorithmHelper.paddingCharacters(d, bCharacters);
                String dString;
                if (d.isProbablePrime(10)) {
                    dString = String.format(Locale.getDefault(), "<font color='%s'>%s</font>", COLOR_CIAN_DARK, dStringFormat);
                } else {
                    dString = dStringFormat;
                }

                String eStringFormat = AlgorithmHelper.paddingCharacters(e, bCharacters);
                String eString;
                if (e.isProbablePrime(10)) {
                    eString = String.format(Locale.getDefault(), "<font color='%s'>%s</font>", COLOR_CIAN_DARK, eStringFormat);
                } else {
                    eString = eStringFormat;
                }

                String deString = AlgorithmHelper.paddingCharacters(de, bbCharacters);
                String resultString = "de = %s·%s = %s ≡ %s (mod %s) | ind: %s<br>";

                // Check if r = rem
                if(r.equals(rem)) {
                    counter += 1;
                    result.append(String.format(Locale.getDefault(), resultString, dString, eString, deString, r, b, counter));
                }
            }
        }
        // Track execution time end
        long end = System.nanoTime();
        long durationNs = end - start;
        String formatedExecutionTime = formatExecutionTime(durationNs);
        result.append(String.format(Locale.getDefault(), "<font color='%s'>" + formatedExecutionTime + "</font>", COLOR));

        AlgResultSet algResultSet = new AlgResultSet();
        algResultSet.counter = counter;
        algResultSet.durationNs = durationNs;

        return algResultSet;
    }


    /**
     * Used if gcd(n, b) > 1
     * @param b
     * @param r
     * @param result
     * @return
     * @throws InterruptedException
     */
    private AlgResultSet alg3(BigInteger b, BigInteger r, StringBuilder result) throws InterruptedException {
        result.append(String.format(Locale.getDefault(),"<font color='%s'><b>Alg 3</b></font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>Possible de (mod b) factors (including non-invertible d)</font><br>", COLOR));

        int bCharacters = b.toString().length();
        int bbCharacters = b.multiply(b).toString().length();

        int counter = 0;

        long start = System.nanoTime();

        result.append(String.format(Locale.getDefault(),"<font color='%s'>For each d = {0, ..., b-1}</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>Compute g = gcd(d, b)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>If g ∤ r skip</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>Reduce to d′e ≡ r′ (mod b′)</font><br>", COLOR));
        result.append(String.format(Locale.getDefault(),"<font color='%s'>Solve using modular inverse and lift solutions</font><br>", COLOR));

        for (BigInteger d = ZERO; d.compareTo(b) < 0; d = d.add(ONE)) {
            AlgorithmHelper.checkIfCanceled();

            BigInteger g = d.gcd(b);

            // If g does not divide r → no solutions
            if (!r.mod(g).equals(ZERO)) {
                continue;
            }

            BigInteger dPrime = d.divide(g);
            BigInteger rPrime = r.divide(g);
            BigInteger bPrime = b.divide(g);

            // d' and b' are coprime, hence inverse exists
            BigInteger dPrimeInv = dPrime.modInverse(bPrime);

            // base solution modulo b'
            BigInteger e0 = rPrime.multiply(dPrimeInv).mod(bPrime);

            // lift solutions
            for (BigInteger k = ZERO; k.compareTo(g) < 0; k = k.add(ONE)) {

                BigInteger e = e0.add(k.multiply(bPrime));
                BigInteger de = d.multiply(e);
                BigInteger rem = de.mod(b);

                if (!rem.equals(r)) {
                    continue;
                }

                counter++;

                String dString = AlgorithmHelper.paddingCharacters(d, bCharacters);
                String eString = AlgorithmHelper.paddingCharacters(e, bCharacters);
                String deString = AlgorithmHelper.paddingCharacters(de, bbCharacters);

                String resultString = "de = %s·%s = %s ≡ %s (mod %s) | ind: %s<br>";
                result.append(String.format(Locale.getDefault(), resultString, dString, eString, deString, r, b, counter));
            }
        }

        long end = System.nanoTime();
        long durationNs = end - start;

        result.append(String.format(Locale.getDefault(), "<font color='%s'>%s</font>", COLOR, formatExecutionTime(durationNs)));

        AlgResultSet algResultSet = new AlgResultSet();
        algResultSet.counter = counter;
        algResultSet.durationNs = durationNs;

        return algResultSet;
    }


    private String formatExecutionTime(long durationNs) {
        long hours = durationNs / 3_600_000_000_000L;
        durationNs %= 3_600_000_000_000L;

        long minutes = durationNs / 60_000_000_000L;
        durationNs %= 60_000_000_000L;

        long seconds = durationNs / 1_000_000_000L;
        durationNs %= 1_000_000_000L;

        long millis = durationNs / 1_000_000L;
        durationNs %= 1_000_000L;

        long micros = durationNs / 1_000L;
        long nanos  = durationNs % 1_000L;

        return String.format(Locale.getDefault(), "Time: %02dh %02dm %02ds %03dms %03dµs %03dns", hours, minutes, seconds, millis, micros, nanos);
    }

    private void printAlgorithmPerformance(long durationAlg1Ns, long durationAlg2Ns, StringBuilder result) {
        long faster;
        long slower;
        String fasterName;
        String slowerName;

        if (durationAlg1Ns < durationAlg2Ns) {
            faster = durationAlg1Ns;
            slower = durationAlg2Ns;
            fasterName = "Alg 1";
            slowerName = "Alg 2";
        } else {
            faster = durationAlg2Ns;
            slower = durationAlg1Ns;
            fasterName = "Alg 2";
            slowerName = "Alg 1";
        }

        double percentFaster = ((double) (slower - faster) / slower) * 100.0;

        result.append(String.format(Locale.getDefault(), "%s is %.2f%%%n faster than %s", fasterName, percentFaster, slowerName));
    }

    private static class AlgResultSet {
        int counter;
        long durationNs;
    }

}
