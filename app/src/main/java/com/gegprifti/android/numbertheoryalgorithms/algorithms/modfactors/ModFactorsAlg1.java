package com.gegprifti.android.numbertheoryalgorithms.algorithms.modfactors;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.formatSigned;
import android.util.Log;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;
import java.util.Locale;

/**
 * Brute force possible de (mod d) factors for each d,e = {0, ..., b-1}
 */
public class ModFactorsAlg1 extends Algorithm implements StringCalculator {
    private final static String TAG = ModFactorsAlg1.class.getSimpleName();

    public ModFactorsAlg1(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        try {
            BigInteger n = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();

            // Mod factors
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Mod factors: Alg 1 (Brute force)</b></font><br>", COLOR));

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

                    String resultStringDEqualToE = "■ %s | %s·%s = %s ≡ %s (mod %s)<br>";
                    String resultStringDNotEqualToE = "□ %s | %s·%s = %s ≡ %s (mod %s)<br>";

                    // Check if r = rem
                    if(r.equals(rem)) {
                        String deString = AlgorithmHelper.paddingCharacters(de, bbCharacters);
                        counter += 1;
                        String counterStringFormat = AlgorithmHelper.paddingCharacters(counter, bCharacters + 1);
                        if(d.compareTo(e) == 0) {
                            result.append(String.format(Locale.getDefault(), resultStringDEqualToE, counterStringFormat, dString, eString, deString, r, b));
                        } else {
                            result.append(String.format(Locale.getDefault(), resultStringDNotEqualToE, counterStringFormat, dString, eString, deString, r, b));
                        }
                    }
                }
            }
            result.append("<br>");

            // Track execution time end
            long end = System.nanoTime();
            long durationNs = end - start;
            String formatedExecutionTime = AlgorithmHelper.formatExecutionTime(durationNs);
            result.append(String.format(Locale.getDefault(), "<font color='%s'>" + formatedExecutionTime + "</font><br>", COLOR));
            result.append("<br>");

            result.append(String.format(Locale.getDefault(), "<font color='%s'>Legend</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>■ → d = e</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>□ → d ≠ e</font>", COLOR));

            // Return
            return result.toString();
        } catch (InterruptedException ex) {
            // This specifically handles the cancellation.
            // Re-throw it so ProgressManager can handle it correctly.
            throw ex;
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
            return ex.toString();
        }
    }


}
