package com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator;


import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;

import java.math.BigInteger;


public class ModularPower extends Algorithm implements StringCalculator {
    public ModularPower(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        // InputGroup
        BigInteger a = algorithmParameters.getInput1();
        BigInteger b = algorithmParameters.getInput2();
        BigInteger c = algorithmParameters.getInput3();

        // Output

        // TODO +++
        // Is there a way to cancel this.
        // Perhaps custom write the algorithm so to be cancelable for long calculations.

        BigInteger result = a.modPow(b, c);
        return result.toString();
    }
}