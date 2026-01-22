package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;

import java.math.BigInteger;

public class BinaryQuadraticFormExample {
    private final String name;
    private final BigInteger a, b, c, d, e, f, m, r;


    private BinaryQuadraticFormExample(Builder builder) {
        this.name = builder.name;
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.m = builder.m;
        this.r = builder.r;
    }


    public static class Builder {
        private String name;
        private BigInteger a, b, c, d, e, f, m, r;

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder a(BigInteger a) {
            this.a = a;
            return this;
        }
        public Builder b(BigInteger b) {
            this.b = b;
            return this;
        }
        public Builder c(BigInteger c) {
            this.c = c;
            return this;
        }
        public Builder d(BigInteger d) {
            this.d = d;
            return this;
        }
        public Builder e(BigInteger e) {
            this.e = e;
            return this;
        }
        public Builder f(BigInteger f) {
            this.f = f;
            return this;
        }
        public Builder m(BigInteger m) {
            this.m = m;
            return this;
        }
        public Builder r(BigInteger r) {
            this.r = r;
            return this;
        }

        public BinaryQuadraticFormExample build() {
            return new BinaryQuadraticFormExample(this);
        }
    }


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
    public BigInteger getM() {
        return this.m;
    }
    public BigInteger getR() {
        return this.r;
    }
}
