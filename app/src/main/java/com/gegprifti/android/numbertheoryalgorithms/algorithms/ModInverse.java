package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import java.math.BigInteger;


public class ModInverse extends Algorithm implements StringCalculator {
    public ModInverse(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() {
        // Input
        BigInteger a = algorithmParameters.getInput1();
        BigInteger b = algorithmParameters.getInput2();

        // Output
        if (a.gcd(b).equals(ONE)) {
            BigInteger result = a.modInverse(b);
            return result.toString();
        } else {
            return  "GCD(a, b) ≠ 1, hence there is no inverse a modulo b";
        }
    }
}