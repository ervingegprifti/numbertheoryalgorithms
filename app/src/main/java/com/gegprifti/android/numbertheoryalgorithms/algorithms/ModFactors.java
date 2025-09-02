package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import static com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper.getNP;
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
            BigInteger a = algorithmParameters.getInput2();

            // Mod factors
            result.append(String.format(Locale.getDefault(), "<font color='%s'><b>Mod factors</b></font><br>", COLOR));

            // Find n ≡ bc (mod a) where
            result.append("Find n ≡ bc (mod a) where <br>");
            result.append("(a<b>x</b> + c)(a<b>y</b> + b) = n <br>");
            result.append("a(a<b>x</b><b>y</b> + b<b>x</b> + c<b>y</b>) + bc = n <br>");
            result.append("<br>");

            // InputGroup
            result.append(String.format(Locale.getDefault(), "<font color='%s'>InputGroup</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "n = %s<br>", getNP(n)));
            result.append(String.format(Locale.getDefault(), "a = %s<br>", getNP(a)));
            result.append("<br>");

            // n (mod a) = r
            result.append(String.format(Locale.getDefault(), "<font color='%s'>n (mod a) = r</font><br>", COLOR));
            BigInteger r = n.mod(a);
            result.append(String.format(Locale.getDefault(), "n (mod a) = %s<br>", r));
            result.append("<br>");

            // Possible bc mod factors for each b,c = {0, ..., a-1}
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Possible bc mod factors for each b,c = {0, ..., a-1} </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>bc ≡ r (mod a)</font><br>", COLOR));
            int counter = 0;
            int aCharacters = a.toString().length();
            int aaCharacters = a.multiply(a).toString().length();
            for (BigInteger b = ZERO; b.compareTo(a) < 0; b = b.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();
                for (BigInteger c = ZERO; c.compareTo(a) < 0; c = c.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();
                    if(b.compareTo(c) <= 0) {
                        // Get only one of the pair and the ones with the same values. e.x. from 1,2 and 2,1 get only one pair 1,2. Get all the pairs like 00; 11; 22...
                        BigInteger bc = b.multiply(c);
                        BigInteger rem = bc.mod(a);
                        if(r.equals(rem)) {
                            counter += 1;

                            String bStringFormat = AlgorithmHelper.paddingCharacters(b, aCharacters);
                            String bString;
                            if (b.isProbablePrime(10)) {
                                bString = String.format(Locale.getDefault(), "<font color='%s'>%s</font>", COLOR_CIAN_DARK, bStringFormat);
                            } else {
                                bString = bStringFormat;
                            }

                            String cStringFormat = AlgorithmHelper.paddingCharacters(c, aCharacters);
                            String cString;
                            if (c.isProbablePrime(10)) {
                                cString = String.format(Locale.getDefault(), "<font color='%s'>%s</font>", COLOR_CIAN_DARK, cStringFormat);
                            } else {
                                cString = cStringFormat;
                            }

                            String bcString = AlgorithmHelper.paddingCharacters(bc, aaCharacters);
                            result.append(String.format(Locale.getDefault(), "bc = %s·%s = %s ≡ %s (mod %s)<br>", bString, cString, bcString, r, a));
                        }
                    }
                }
            }
            result.append("<br>");

            // Possible bc mod factors counted
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Possible bc mod factors counted </font><br>", COLOR));
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