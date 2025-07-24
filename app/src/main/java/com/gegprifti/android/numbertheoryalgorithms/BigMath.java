package com.gegprifti.android.numbertheoryalgorithms;

import android.util.Pair;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public final class BigMath {
    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.valueOf(2L);
    private static final BigInteger THREE = BigInteger.valueOf(3L);
    private static final BigInteger FOUR = BigInteger.valueOf(4L);
    private static final BigInteger EIGHT = BigInteger.valueOf(8L);

    public static List<Pair<BigInteger, BigInteger>> PairFactors(ProgressDialog.Run run, BigInteger n, boolean includeTrivialSolutions) {
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

        BigInteger sqrt = SqRootFloor(n);
        for (BigInteger p = start; p.compareTo(sqrt) <= 0; p = p.add(ONE)) {
            // Check if the run is canceled
            if (run.isCancelled()) { return null; }

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



    public static boolean DoesDCompletelyDivideN(BigInteger d, BigInteger n) throws IllegalArgumentException {

        if(d.equals(BigInteger.ZERO))
        {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }

        BigInteger[] quotient_remainder = n.divideAndRemainder(d);
        // BigInteger quotient = quotient_remainder[0];
        BigInteger remainder = quotient_remainder[1];
        // d ∣ n -> return true;
        // d ∤ n -> return false;
        return remainder.equals(ZERO);
    }


    //region ROOTS

    /**
     *
     * @param x
     * @param n
     * @return
     * @throws IllegalArgumentException
     *
     * @see <a href="https://stackoverflow.com/questions/32553108/calculating-nth-root-in-java-using-power-method">Calculating nth root in Java using power method</a>
     */
    public static BigInteger NthRootFloor(BigInteger x, int n) throws IllegalArgumentException {
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
            return SqRootFloor(x);
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
    private static BigInteger SqRootFloor(BigInteger x) throws IllegalArgumentException {
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
    //endregion
}