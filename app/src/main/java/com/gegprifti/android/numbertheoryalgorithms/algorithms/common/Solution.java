package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


import java.math.BigInteger;


public class Solution {
    private final BigInteger p;
    private final BigInteger q;
    private final BigInteger x;
    private final BigInteger y;
    public Solution (BigInteger p, BigInteger q, BigInteger x, BigInteger y) {
        this.p = p;
        this.q = q;
        this.x = x;
        this.y = y;
    }
    public BigInteger getP () {
        return this.p;
    }
    public BigInteger getQ () {
        return this.q;
    }
    public BigInteger getX () {
        return this.x;
    }
    public BigInteger getY () {
        return this.y;
    }
}
