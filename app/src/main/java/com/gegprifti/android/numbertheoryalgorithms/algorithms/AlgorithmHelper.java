package com.gegprifti.android.numbertheoryalgorithms.algorithms;

import java.math.BigInteger;

public class AlgorithmHelper {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;


    /**
     * Extended Euclidean Algorithm. Must be called like EEA(|a|, |b|)
     * @param a must be the in the absolute value of an integer when called
     * @param b must be the in the absolute value of an integer when called
     * @return array [g, x, y] such that ax + by = gcd(x, y) = g<br>
     * [0] = GCD(a, b)<br>
     * [1] = x<br>
     * [2] = y
     *
     * @see <a href="https://math.stackexchange.com/questions/37806/extended-euclidean-algorithm-with-negative-numbers">Extended Euclidean algorithm with negative numbers</a>
     */
    public static BigInteger[] EEA (BigInteger a, BigInteger b) {
        BigInteger[] result = new BigInteger[3];

        if (b.equals(BigInteger.ZERO)) {
            result[0] = a;
            result[1] = ONE;
            result[2] = ZERO;
            return result;
        }

        BigInteger[] values = EEA(b, a.mod(b));

        BigInteger g = values[0];
        BigInteger x = values[2];
        BigInteger y = values[1].subtract(values[2].multiply(a.divide(b)));

        result[0] = g;
        result[1] = x;
        result[2] = y;

        return result;
    }
}