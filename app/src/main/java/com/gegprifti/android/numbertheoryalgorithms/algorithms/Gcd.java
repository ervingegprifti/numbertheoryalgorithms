package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import java.math.BigInteger;


public class Gcd extends Algorithm implements StringCalculator {
    public Gcd(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() {
        // Input
        BigInteger a = algorithmParameters.getInput1();
        BigInteger b = algorithmParameters.getInput2();

        // Output
        BigInteger result = a.gcd(b);
        return result.toString();
    }
}