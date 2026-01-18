package com.gegprifti.android.numbertheoryalgorithms.algorithms.modfactors;


import android.util.Log;

import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.ListStringCalculator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ModFactorsAlg3 extends Algorithm implements ListStringCalculator {
    private final static String TAG = ModFactorsAlg3.class.getSimpleName();

    public ModFactorsAlg3(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public List<String> calculate() throws InterruptedException {
        try {
            BigInteger n = algorithmParameters.getInput1();
            BigInteger b = algorithmParameters.getInput2();

            List<String> result = new ArrayList<>();
            result.add(String.format(Locale.getDefault(), "<font color='%s'><b>Mod factors: Alg 3</b></font>", COLOR));

            BigInteger gcdnb = n.gcd(b);

            List<String> modFactors;
            if (gcdnb.compareTo(ONE) == 0) {
                modFactors = alg2CoprimeCase (n, b);
                result.addAll(modFactors);
            } else {
                modFactors = alg2NonCoprimeCase(n, b);
                result.addAll(modFactors);
            }

            return result;
        } catch (InterruptedException ex) {
            // This specifically handles the cancellation.
            // Re-throw it so ProgressManager can handle it correctly.
            throw ex;
        } catch (Exception ex) {
            Log.e(TAG, "", ex);
            throw ex;
        }
    }


    /**
     * Used if gcd(n, b) = 1
     * @param b
     * @return
     * @throws InterruptedException
     */
    private List<String> alg2CoprimeCase (BigInteger n, BigInteger b) throws InterruptedException {
        List<String> modFactors = new ArrayList<>();

        BigInteger r = n.mod(b);

        for (BigInteger d = ZERO; d.compareTo(b) < 0; d = d.add(ONE)) {
            AlgorithmHelper.checkIfCanceled();

            BigInteger g = d.gcd(b);
            if (g.compareTo(ONE) == 0) {
                BigInteger dModInverse = d.modInverse(b);
                BigInteger rdModInverse = r.multiply(dModInverse);
                BigInteger e = rdModInverse.mod(b);
                BigInteger de = d.multiply(e);
                BigInteger rem = de.mod(b);
                if(rem.equals(r)) {
                    BigInteger f = n.subtract(de).divide(b);
                    String modFactor = "b: " + b + "\n" + "d: " + d + "\n" + "e: " + e + "\n" + "f: " + f + "\n" + "m: " + b + "\n" + "r: " + r;
                            modFactors.add(modFactor);
                }
            }
        }

        return modFactors;
    }


    /**
     * Used if gcd(n, b) > 1
     * @param b
     * @return
     * @throws InterruptedException
     */
    private List<String> alg2NonCoprimeCase(BigInteger n, BigInteger b) throws InterruptedException {
        List<String> modFactors = new ArrayList<>();

        BigInteger r = n.mod(b);

        for (BigInteger d = ZERO; d.compareTo(b) < 0; d = d.add(ONE)) {
            AlgorithmHelper.checkIfCanceled();

            BigInteger g = d.gcd(b);

            if (!r.mod(g).equals(ZERO)) {
                continue;
            }

            BigInteger dPrime = d.divide(g);
            BigInteger rPrime = r.divide(g);
            BigInteger bPrime = b.divide(g);

            BigInteger dPrimeInverse = dPrime.modInverse(bPrime);
            BigInteger e0 = rPrime.multiply(dPrimeInverse).mod(bPrime);

            for (BigInteger k = ZERO; k.compareTo(g) < 0; k = k.add(ONE)) {
                AlgorithmHelper.checkIfCanceled();

                BigInteger e = e0.add(k.multiply(bPrime));
                BigInteger de = d.multiply(e);
                BigInteger rem = de.mod(b);

                if(rem.equals(r)) {
                    BigInteger f = n.subtract(de).divide(b);
                    String modFactor = "b: " + b + "\n" + "d: " + d + "\n" + "e: " + e + "\n" + "f: " + f + "\n" + "m: " + b + "\n" + "r: " + r;
                    modFactors.add(modFactor);
                }
            }
        }

        return modFactors;
    }
}
