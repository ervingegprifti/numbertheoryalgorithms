package com.gegprifti.android.numbertheoryalgorithms.algorithms;


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
            BigInteger a = algorithmParameters.getInput2();

            // Mod factors count
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Mod factors count </font><br>", COLOR));
            result.append("(a<b>x</b> + c)(a<b>y</b> + b) = a(a<b>x</b><b>y</b> + b<b>x</b> + c<b>y</b>) + bc = n </font><br>");
            result.append("<br>");

            // InputGroup
            result.append(String.format(Locale.getDefault(), "<font color='%s'>InputGroup</font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "n = %s<br>", n));
            result.append(String.format(Locale.getDefault(), "a = %s<br>", a));
            result.append("<br>");

            // Count possible bc mod factors for each b,c = {0, ... , k-1}, k = {2, ... , a}
            result.append(String.format(Locale.getDefault(), "<font color='%s'>Count possible bc mod factors for each b,c = {0, ... , k-1}, k = {2, ... , a} </font><br>", COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>n (mod k) = r</font><br>",COLOR));
            result.append(String.format(Locale.getDefault(), "<font color='%s'>bc ≡ r (mod k) %s p possible bc mod factors counted per each k</font><br>",COLOR, RIGHT_ARROW_COLORED));
            for(BigInteger k = TWO; k.compareTo(a) <= 0; k = k.add(ONE))
            {
                BigInteger m = n.mod(k);
                int counter = 0;
                for (BigInteger b = ZERO; b.compareTo(k) < 0; b = b.add(ONE)) {
                    AlgorithmHelper.checkIfCanceled();
                    for (BigInteger c = ZERO; c.compareTo(k) < 0; c = c.add(ONE)) {
                        AlgorithmHelper.checkIfCanceled();
                        if(b.compareTo(c) <= 0) {
                            // Get only one of the pair and the ones with the same values.
                            // e.x. from 1,2 and 2,1 get only one pair 1,2. Get all the pairs like 0,0; 1,1; 2,2;...
                            BigInteger rem = b.multiply(c).mod(k);
                            if(m.equals(rem)) {
                                counter += 1;
                            }
                        }
                    }
                }
                if(k.equals(a)) {
                    result.append(String.format(Locale.getDefault(), "bc ≡ %s (mod %s) %s %s", m, k, RIGHT_ARROW_COLORED, counter));
                } else {
                    result.append(String.format(Locale.getDefault(), "bc ≡ %s (mod %s) %s %s<br>", m, k, RIGHT_ARROW_COLORED, counter));
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