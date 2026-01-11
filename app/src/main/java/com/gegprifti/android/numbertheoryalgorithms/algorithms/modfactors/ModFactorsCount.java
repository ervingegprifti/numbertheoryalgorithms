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

            // Count possible de mod factors for each d,e = {0, ... , k-1}, k = {2, ... , b}
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Count possible de mod factors for each d,e = {0, ... , k-1}, k = {2, ... , b} </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>n (mod k) = r</font><br>",COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>de ≡ r (mod k) %s p possible de mod factors counted per each k</font><br>",COLOR, RIGHT_ARROW_COLORED));

            //
            for(BigInteger k = TWO; k.compareTo(b) <= 0; k = k.add(ONE)) {
                BigInteger m = n.mod(k);
                int counter = 0;
                for (BigInteger d = ZERO; d.compareTo(k) < 0; d = d.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();
                    for (BigInteger e = ZERO; e.compareTo(k) < 0; e = e.add(ONE)) {
                        AlgorithmHelper.checkIfCanceled();
                        if(d.compareTo(e) <= 0) {
                            // Get only one of the pair and the ones with the same values.
                            // e.x. from 1,2 and 2,1 get only one pair 1,2. Get all the pairs like 0,0; 1,1; 2,2;...
                            BigInteger rem = d.multiply(e).mod(k);
                            if(m.equals(rem)) {
                                counter += 1;
                            }
                        }
                    }
                }
                if(k.equals(b)) {
                    result.append(String.format(Locale.getDefault(), "de ≡ %s (mod %s) %s %s", m, k, RIGHT_ARROW_COLORED, counter));
                } else {
                    result.append(String.format(Locale.getDefault(), "de ≡ %s (mod %s) %s %s<br>", m, k, RIGHT_ARROW_COLORED, counter));
                }
            }

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
}