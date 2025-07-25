package com.gegprifti.android.numbertheoryalgorithms.algorithms;


import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import java.math.BigInteger;


public class Lcm extends Algorithm implements StringCalculator {
    public Lcm(AlgorithmParameters algorithmParameters) {
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

        // LCM(a,b) = (ab)/GCD(a,b) since (ab)=GCD(a,b)LCM(a,b)
        BigInteger result = a.multiply(b).divide(a.gcd(b));

        return result.toString();
    }
}