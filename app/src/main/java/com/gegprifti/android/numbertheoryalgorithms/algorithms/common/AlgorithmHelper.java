package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


import android.util.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class AlgorithmHelper {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private static final BigInteger EIGHT = BigInteger.valueOf(8L);


    public static String getNP(int value) {
        return  (value < 0) ? "(" + value + ")" : value + "";
    }
    public static String getNP(BigInteger value) {
        return  (value.compareTo(BigInteger.ZERO) < 0) ? "(" + value + ")" : value + "";
    }


    public static BigInteger getSign(BigInteger value) {
        return (value.compareTo(ZERO) < 0) ? ONE.negate() : ONE;
    }


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
    public static BigInteger[] calculateEEA(BigInteger a, BigInteger b) {
        BigInteger[] result = new BigInteger[3];

        if (b.equals(BigInteger.ZERO)) {
            result[0] = a;
            result[1] = ONE;
            result[2] = ZERO;
            return result;
        }

        BigInteger[] values = calculateEEA(b, a.mod(b));

        BigInteger g = values[0];
        BigInteger x = values[2];
        BigInteger y = values[1].subtract(values[2].multiply(a.divide(b)));

        result[0] = g;
        result[1] = x;
        result[2] = y;

        return result;
    }


    public static List<Solution> calculateBQFSolutions(BigInteger a, BigInteger b, BigInteger c, List<Pair<BigInteger, BigInteger>> pairFactors, boolean includeOnlyPositiveSolutions, boolean includeOnlyNegativeSolutions) throws InterruptedException {
        List<Solution> solutions = new ArrayList<>();
        for (int i = 0; i < pairFactors.size(); i++) {
            checkIfCanceled();

            Pair<BigInteger, BigInteger> pairFactor = pairFactors.get(i);
            BigInteger p = pairFactor.first;
            BigInteger q = pairFactor.second;
            boolean solForX = false;
            boolean solForY = false;
            BigInteger[] solX = p.subtract(c).divideAndRemainder(a);
            BigInteger[] solY = q.subtract(b).divideAndRemainder(a);
            BigInteger x = solX[0];
            BigInteger y = solY[0];
            if(solX[1].compareTo(ZERO) == 0) {
                solForX = true;
            }
            if(solY[1].compareTo(ZERO) == 0) {
                solForY = true;
            }
            if (solForX && solForY) {
                if (includeOnlyPositiveSolutions && !includeOnlyNegativeSolutions) {
                    // Include only positive solutions.
                    if (x.compareTo(ZERO) >= 0 && y.compareTo(ZERO) >= 0) {
                        Solution solution = new Solution(p, q, x, y);
                        solutions.add(solution);
                    }
                } else if (!includeOnlyPositiveSolutions && includeOnlyNegativeSolutions) {
                    // Include only negative solutions.
                    if (x.compareTo(ZERO) < 0 && y.compareTo(ZERO) < 0) {
                        Solution solution = new Solution(p, q, x, y);
                        solutions.add(solution);
                    }
                } else {
                    // Include positive and negative solutions.
                    Solution solution = new Solution(p, q, x, y);
                    solutions.add(solution);
                }
            }
        }
        return solutions;
    }


    public static List<Pair<BigInteger, BigInteger>> getPairFactors(BigInteger n, boolean includeTrivialSolutions) throws InterruptedException {
        List<Pair<BigInteger, BigInteger>> pairFactors = new ArrayList<>();

        boolean isNNegative = false;
        if (n.compareTo(ZERO) < 0) {
            isNNegative = true;
        }

        n = n.abs();

        if (n.compareTo(ZERO) == 0) {
            return pairFactors;
        }

        BigInteger start = TWO;
        if (includeTrivialSolutions) {
            start = ONE;
        }

        BigInteger sqrt = calculateSqRootFloor(n);
        for (BigInteger p = start; p.compareTo(sqrt) <= 0; p = p.add(ONE)) {
            checkIfCanceled();

            BigInteger[] divRem = n.divideAndRemainder(p);
            BigInteger q = divRem[0];
            BigInteger rem = divRem[1];
            if (rem.compareTo(ZERO) == 0) {
                if (isNNegative) {
                    Pair<BigInteger, BigInteger> pair1 = new Pair<>(p, q.negate());
                    Pair<BigInteger, BigInteger> pair2 = new Pair<>(p.negate(), q);
                    Pair<BigInteger, BigInteger> pair3 = new Pair<>(q.negate(), p);
                    Pair<BigInteger, BigInteger> pair4 = new Pair<>(q, p.negate());
                    pairFactors.add(pair1);
                    pairFactors.add(pair2);
                    pairFactors.add(pair3);
                    pairFactors.add(pair4);
                } else {
                    Pair<BigInteger, BigInteger> pair1 = new Pair<>(p, q);
                    Pair<BigInteger, BigInteger> pair2 = new Pair<>(p.negate(), q.negate());
                    Pair<BigInteger, BigInteger> pair3 = new Pair<>(q, p);
                    Pair<BigInteger, BigInteger> pair4 = new Pair<>(q.negate(), p.negate());
                    pairFactors.add(pair1);
                    pairFactors.add(pair2);
                    pairFactors.add(pair3);
                    pairFactors.add(pair4);
                }
            }
        }

        return pairFactors;
    }


    public static boolean doesDCompletelyDivideN(BigInteger d, BigInteger n) throws IllegalArgumentException {
        if(d.equals(BigInteger.ZERO)) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }

        BigInteger[] quotient_remainder = n.divideAndRemainder(d);
        // BigInteger quotient = quotient_remainder[0];
        BigInteger remainder = quotient_remainder[1];
        // d ∣ n -> return true;
        // d ∤ n -> return false;
        return remainder.equals(ZERO);
    }


    /**
     *
     * @param x
     * @param n
     * @return
     * @throws IllegalArgumentException
     *
     * @see <a href="https://stackoverflow.com/questions/32553108/calculating-nth-root-in-java-using-power-method">Calculating nth root in Java using power method</a>
     */
    public static BigInteger calculateNthRootFloor(BigInteger x, int n) throws IllegalArgumentException {
        if (x.compareTo(ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        int sign = x.signum();
        if (n <= 0 || (sign < 0))
            throw new IllegalArgumentException();
        if (sign == 0)
            return ZERO;
        if (n == 1)
            return x;
        if (n == 2)
            return calculateSqRootFloor(x);
        BigInteger a;
        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger bigNMinusOne = BigInteger.valueOf(n - 1);
        BigInteger b = ZERO.setBit(1 + x.bitLength() / n);
        do {
            a = b;
            b = a.multiply(bigNMinusOne).add(x.divide(a.pow(n - 1))).divide(bigN);
        }
        while (b.compareTo(a) == -1);
        return a;
    }


    /**
     *
     * @param x
     * @return
     * @throws IllegalArgumentException
     *
     * @see <a href="https://gist.github.com/JochemKuijpers/cd1ad9ec23d6d90959c549de5892d6cb">sqrt</a>
     */
    private static BigInteger calculateSqRootFloor(BigInteger x) throws IllegalArgumentException {
        if (x.compareTo(ZERO) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        BigInteger a = ONE;
        BigInteger b = x.shiftRight(5).add(EIGHT);
        while (b.compareTo(a) >= 0) {
            BigInteger mid = a.add(b).shiftRight(1);
            if (mid.multiply(mid).compareTo(x) > 0) {
                b = mid.subtract(ONE);
            } else {
                a = mid.add(ONE);
            }
        }
        return a.subtract(ONE); // Floor
    }


    /**
     * Checks if the current thread has been interrupted and throws an exception if it has.
     * This helper method can be called from any subclass's long-running loop.
     * @throws InterruptedException if the thread has been interrupted.
     */
    public static void checkIfCanceled() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("Calculation was cancelled by the user.");
        }
    }
}