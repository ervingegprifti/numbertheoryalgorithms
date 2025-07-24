package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import java.math.BigInteger;


public class Addition extends Algorithm implements StringCalculator {
    public Addition(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() {
        // Input
        BigInteger a = algorithmParameters.getInput1();
        BigInteger b = algorithmParameters.getInput2();

        // Output
        BigInteger result = a.add(b);

        return result.toString();
    }
}