package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import java.math.BigInteger;


public class NextProbablePrime extends Algorithm implements StringCalculator {
    public NextProbablePrime(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() {
        // Input
        BigInteger a = algorithmParameters.getInput1();

        // Output
        BigInteger result = a.nextProbablePrime();

        return result.toString();
    }
}
