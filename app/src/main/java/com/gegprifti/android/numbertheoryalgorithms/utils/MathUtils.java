package com.gegprifti.android.numbertheoryalgorithms.utils;

import java.math.BigInteger;

public class MathUtils {
    private final static String TAG = "MathUtils";

    /**
     * Checks whether a {@link BigInteger} value is exactly divisible by a given divisor.
     *
     * @param value   the dividend to test; may be negative, zero, or positive
     * @param divisor the number to divide by; must not be zero
     * @return {@code true} if {@code value} is exactly divisible by {@code divisor}
     *         (i.e. {@code value % divisor == 0}); {@code false} otherwise
     * @throws ArithmeticException if {@code divisor} is zero
     * @throws NullPointerException if {@code value} or {@code divisor} is {@code null}
     *
     * @see BigInteger#remainder(BigInteger)
     */
    public static boolean isDivisibleBy(BigInteger value, BigInteger divisor) {
        return value.remainder(divisor).signum() == 0;
    }


    public static boolean isOdd(BigInteger number) {
        return number.testBit(0);
    }


    public static boolean isEven(BigInteger number) {
        return !number.testBit(0);
    }
}
