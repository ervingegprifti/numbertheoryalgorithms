package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import java.math.BigInteger;


public class Division extends Algorithm implements StringCalculator {
    public Division(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() {
        // Input
        BigInteger a = algorithmParameters.getInput1();
        BigInteger b = algorithmParameters.getInput2();

        // Output
        BigInteger[] result = a.divideAndRemainder(b);

        // quotient_remainder
        return  result[0].toString() + "_" + result[1].toString();
    }
}