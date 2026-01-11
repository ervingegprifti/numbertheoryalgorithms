package com.gegprifti.android.numbertheoryalgorithms.algorithms.modfactors;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.formatSigned;
import android.util.Log;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;
import java.util.Locale;


public class ModFactors extends Algorithm implements StringCalculator {
    private final static String TAG = ModFactors.class.getSimpleName();

    public ModFactors(AlgorithmParameters algorithmParameters) {
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

            // Possible de mod factors for each d,e = {0, ..., b-1}
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Possible de mod factors for each d,e = {0, ..., b-1} </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>de ≡ r (mod b)</font><br>", COLOR));
            int counter = 0;
            int bCharacters = b.toString().length();
            int bbCharacters = b.multiply(b).toString().length();
            for (BigInteger d = ZERO; d.compareTo(b) < 0; d = d.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();
                for (BigInteger e = ZERO; e.compareTo(b) < 0; e = e.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();
                    if(d.compareTo(e) <= 0) {
                        // Get only one of the pair and the ones with the same values. e.x. from 1,2 and 2,1 get only one pair 1,2. Get all the pairs like 00; 11; 22...
                        BigInteger de = d.multiply(e);
                        BigInteger rem = de.mod(b);
                        if(r.equals(rem)) {
                            counter += 1;

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
                            result.append(String.format(Locale.getDefault(), "de = %s·%s = %s ≡ %s (mod %s)<br>", dString, eString, deString, r, b));
                        }
                    }
                }
            }
            result.append("<br>");

            // Possible de mod factors counted
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Possible de mod factors counted </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "counter = %s", counter));

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
}
