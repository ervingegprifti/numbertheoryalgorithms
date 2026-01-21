package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;

import java.math.BigInteger;

public class BinaryQuadraticFormExample {
    private final String name;
    private final BigInteger a;
    private final BigInteger b;
    private final BigInteger c;
    private final BigInteger d;
    private final BigInteger e;
    private final BigInteger f;


    public String getName() {
        return this.name;
    }

    public BigInteger getA() {
        return this.a;
    }

    public BigInteger getB() {
        return this.b;
    }

    public BigInteger getC() {
        return this.c;
    }

    public BigInteger getD() {
        return this.d;
    }

    public BigInteger getE() {
        return this.e;
    }

    public BigInteger getF() {
        return this.f;
    }

    public BinaryQuadraticFormExample(String name, BigInteger a, BigInteger b, BigInteger c, BigInteger d, BigInteger e, BigInteger f) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
}
