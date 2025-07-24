package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import java.math.BigInteger;


public class IsProbablePrime extends Algorithm implements StringCalculator {
    public IsProbablePrime(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() {
        // Input
        BigInteger a = algorithmParameters.getInput1();
        BigInteger b = algorithmParameters.getInput2();

        // Output
        boolean isProbablePrime = a.isProbablePrime(b.intValue());
        if(isProbablePrime) {
            return "1";
        } else {
            return "0";
        }
    }
}