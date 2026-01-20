package com.gegprifti.android.numbertheoryalgorithms.cyclesets.rsa;


import java.math.BigInteger;


public class RSA {
    private final String name;
    private final BigInteger n;
    private final BigInteger p;
    private final BigInteger q;


    public String getName() {
        return this.name;
    }

    public BigInteger getN() {
        return this.n;
    }

    public BigInteger getP() {
        return this.p;
    }

    public BigInteger getQ() {
        return this.q;
    }


    public RSA(String name, BigInteger n, BigInteger p, BigInteger q) {
        this.name = name;
        this.n = n;
        this.p = p;
        this.q = q;
    }
}
