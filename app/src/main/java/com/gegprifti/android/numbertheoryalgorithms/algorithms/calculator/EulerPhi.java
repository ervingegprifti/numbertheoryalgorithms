package com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator;


import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;


/**
 * ϕ(1) = 1 because GCD(1,1) = 1 ➡ counter = 1<br><br>
 *
 * ϕ(2) = 1 because GCD(1,2) = 1 ➡ counter = 1<br>
 *                  GCD(2,2) = 2<br><br>
 *
 * ϕ(3) = 2 because GCD(1,3) = 1 ➡ counter = 1<br>
 *                  GCD(2,3) = 1 ➡ counter = 2<br>
 *                  GCD(3,3) = 3<br><br>
 *
 * ϕ(4) = 2 because GCD(1,4) = 1 ➡ counter = 1<br>
 *                  GCD(2,4) = 2<br>
 *                  GCD(3,4) = 1 ➡ counter = 2<br>
 *                  GCD(4,4) = 4<br><br>
 *
 * ϕ(5) = 4 because GCD(1,5) = 1 ➡ counter = 1<br>
 *                  GCD(2,5) = 1 ➡ counter = 2<br>
 *                  GCD(3,5) = 1 ➡ counter = 3<br>
 *                  GCD(4,5) = 1 ➡ counter = 4<br>
 *                  GCD(5,5) = 5
 */
public class EulerPhi extends Algorithm implements StringCalculator {
    public EulerPhi(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        // Input
        BigInteger a = algorithmParameters.getInput1();

        // Output
        if (a.compareTo(ZERO) <= 0) {
            return "a must be greater than zero!";
        }
        if (a.isProbablePrime(10)) {
            return (a.subtract(ONE)).toString();
        }
        BigInteger counter = ZERO;
        for(BigInteger i = ONE; i.compareTo(a) <= 0; i = i.add(ONE)) {
            AlgorithmHelper.checkIfCanceled();
            // Calculate Euler's phi-function
            BigInteger g = i.gcd(a);
            if (g.compareTo(ONE) == 0) {
                counter = counter.add(ONE);
            }
        }

        return counter.toString();
    }
}