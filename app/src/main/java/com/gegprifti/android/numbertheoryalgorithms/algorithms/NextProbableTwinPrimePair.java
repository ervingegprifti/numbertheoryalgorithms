package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import java.math.BigInteger;


/**
 *
 *
 * @see <a href="https://en.wikipedia.org/wiki/Twin_prime">Twin prime</a>
 */
public class NextProbableTwinPrimePair extends Algorithm implements StringCalculator {
    public NextProbableTwinPrimePair(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() {
        // Input
        BigInteger a = algorithmParameters.getInput1();

        // Output
        BigInteger prime1 = null;
        BigInteger prime2 = null;
        if (a.isProbablePrime(10)) {
            prime1 = a;
            prime2 = prime1.nextProbablePrime();
        } else {
            prime1 = a.nextProbablePrime();
            prime2 = prime1.nextProbablePrime();
        }
        if ((prime2.subtract(prime1)).compareTo(TWO) == 0) {
            return prime1 + "_" + prime2;
        }
        boolean twinPrimeNotFoundYet = true;
        while (twinPrimeNotFoundYet) {
            prime1 = prime2;
            prime2 = prime1.nextProbablePrime();
            if ((prime2.subtract(prime1)).compareTo(TWO) == 0) {
                twinPrimeNotFoundYet = false;
            }
        }

        // prime1_prime2
        return prime1 + "_" + prime2;
    }
}