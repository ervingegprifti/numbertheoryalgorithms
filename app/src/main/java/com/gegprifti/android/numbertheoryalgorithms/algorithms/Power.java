package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import java.math.BigInteger;


public class Power extends Algorithm implements StringCalculator {
    public Power(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() {
        // Input
        BigInteger a = algorithmParameters.getInput1();
        int b = algorithmParameters.getInput2().intValue(); // 0 <= b >= 2147483647

        // Output
        // 0ᵇ = 0
        if (a.compareTo(ZERO) == 0) {
            return ZERO.toString();
        }
        // 1ᵇ = 1
        if (a.compareTo(ONE) == 0) {
            return ONE.toString();
        }
        // a⁰ = 1
        if (b == 0) {
            return "1";
        }
        // a¹ = a
        if (b == 1) {
            return a.toString();
        }
        // BigInteger power = a.pow(b); // Is no way to cancel if we use this?
        BigInteger power = a;
        for (int i = 2; i <= b; i++) {
            // Perform the power
            power = power.multiply(a);
        }

        return power.toString();
    }
}