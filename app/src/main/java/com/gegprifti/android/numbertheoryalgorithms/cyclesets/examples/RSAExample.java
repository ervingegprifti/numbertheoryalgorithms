package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;


import java.math.BigInteger;


public class RSAExample {
    private final String name;
    private final BigInteger n, p, q;


    private RSAExample(RSAExample.Builder builder) {
        this.name = builder.name;
        this.n = builder.n;
        this.p = builder.p;
        this.q = builder.q;
    }


    public static class Builder {
        private String name;
        private BigInteger n, p, q;

        public RSAExample.Builder name(String name) {
            this.name = name;
            return this;
        }
        public RSAExample.Builder n(BigInteger n) {
            this.n = n;
            return this;
        }
        public RSAExample.Builder p(BigInteger p) {
            this.p = p;
            return this;
        }
        public RSAExample.Builder q(BigInteger q) {
            this.q = q;
            return this;
        }

        public RSAExample build() {
            return new RSAExample(this);
        }
    }


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
}
