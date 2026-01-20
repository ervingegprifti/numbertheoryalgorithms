package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;


import java.math.BigInteger;


public class ModFactorsExample {
    private final String name;
    private final BigInteger n;
    private final BigInteger b;


    public String getName() {
        return this.name;
    }

    public BigInteger getN() {
        return this.n;
    }

    public BigInteger getB() {
        return this.b;
    }


    public ModFactorsExample(String name, BigInteger n, BigInteger b) {
        this.name = name;
        this.n = n;
        this.b = b;
    }
}
