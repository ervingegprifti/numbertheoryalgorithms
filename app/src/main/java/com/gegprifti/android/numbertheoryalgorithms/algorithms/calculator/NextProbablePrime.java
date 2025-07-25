package com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator;


import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;


public class NextProbablePrime extends Algorithm implements StringCalculator {
    public NextProbablePrime(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        // Input
        BigInteger a = algorithmParameters.getInput1();

        // Output

        // TODO +++
        // Is there a way to cancel this.
        // Perhaps custom write the algorithm so to be cancelable for long calculations.

        BigInteger result = a.nextProbablePrime();

        return result.toString();
    }
}
