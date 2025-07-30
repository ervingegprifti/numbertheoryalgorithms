package com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator;


import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;


public class Root extends Algorithm implements StringCalculator {
    public Root(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        // Input
        BigInteger a = algorithmParameters.getInput1();
        int b = algorithmParameters.getInput2().intValue();

        // Output

        // TODO +++
        // Is there a way to cancel this.
        // Perhaps custom write the algorithm so to be cancelable for long calculations.

        // Root, Power & Remainder
        BigInteger root = AlgorithmHelper.calculateNthRootFloor(a, b);
        BigInteger power = root.pow(b);
        BigInteger remainder = a.subtract(power);

        // Return root_remainder
        return root.toString() + "_" + remainder.toString();
    }
}