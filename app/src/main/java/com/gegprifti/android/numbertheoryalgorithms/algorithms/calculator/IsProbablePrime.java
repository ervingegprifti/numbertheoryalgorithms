package com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator;


import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;


public class IsProbablePrime extends Algorithm implements StringCalculator {
    public IsProbablePrime(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        // Input
        BigInteger a = algorithmParameters.getInput1();
        BigInteger b = algorithmParameters.getInput2();

        // Output

        // TODO +++
        // Is there a way to cancel this.
        // Perhaps custom write the algorithm so to be cancelable for long calculations.

        boolean isProbablePrime = a.isProbablePrime(b.intValue());
        if(isProbablePrime) {
            return "1";
        } else {
            return "0";
        }
    }
}