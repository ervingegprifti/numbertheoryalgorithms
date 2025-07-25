package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.BigMath;
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
        BigInteger root = BigMath.NthRootFloor(a, b);
        BigInteger power = root.pow(b);
        BigInteger remainder = a.subtract(power);

        // Return root_remainder
        return root.toString() + "_" + remainder.toString();
    }
}