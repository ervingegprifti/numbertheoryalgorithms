package com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator;


import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmHelper;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Algorithm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.StringCalculator;
import java.math.BigInteger;


/**
 * <p>
 * Factorial calculator algorithm.<br>
 *
 * a! a ∊ ℤ with a ≥ 0<br>
 * 0! = 1<br>
 * 1! = 1<br>
 * 2! = 1 * 2 = 2<br>
 * 3! = 1 * 2 * 3 = 6<br>
 * 4! = 1 * 2 * 3 * 4 = 24
 *
 * @see <a href="https://www.britannica.com/science/factorial">factorial</a>
 */
public class Factorial extends Algorithm implements StringCalculator {
    public Factorial(AlgorithmParameters algorithmParameters) {
        super(algorithmParameters);
    }


    @Override
    public String calculate() throws InterruptedException {
        // Get input.
        BigInteger a = algorithmParameters.getInput1();

        // Calculate output
        if (a.compareTo(ZERO) < 0) {
            return "a must be greater than or equal to zero!";
        }

        if (a.compareTo(ZERO) == 0) {
            return "1";
        }

        BigInteger factorial = ONE;
        for(BigInteger i = ONE; i.compareTo(a) <= 0; i = i.add(ONE)) {
            AlgorithmHelper.checkIfCanceled();
            factorial = factorial.multiply(i);
        }

        return factorial.toString();
    }
}