package com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator;


import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;


public class Power extends Algorithm implements StringCalculator {
    public Power(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
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

        // This is hard to cancel.
        // BigInteger power = a.pow(b);

        BigInteger power = a;
        for (int i = 2; i <= b; i++) {
            AlgorithmHelper.checkIfCanceled();
            // Perform the power
            power = power.multiply(a);
        }

        return power.toString();
    }
}