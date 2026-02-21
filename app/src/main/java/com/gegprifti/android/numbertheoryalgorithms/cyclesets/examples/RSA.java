package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;


import java.math.BigInteger;


public class RSA {
    public final String name, reference;
    public final BigInteger n, p, q;


    private RSA(RSA.Builder builder) {
        this.name = builder.name;
        this.reference = builder.reference;
        this.n = builder.n;
        this.p = builder.p;
        this.q = builder.q;
    }


    public static class Builder {
        private String name, reference;
        private BigInteger n, p, q;

        public RSA.Builder name(String name) {
            this.name = name;
            return this;
        }
        public RSA.Builder reference(String reference) {
            this.reference = reference;
            return this;
        }
        public RSA.Builder n(BigInteger n) {
            this.n = n;
            return this;
        }
        public RSA.Builder p(BigInteger p) {
            this.p = p;
            return this;
        }
        public RSA.Builder q(BigInteger q) {
            this.q = q;
            return this;
        }

        public RSA build() {
            return new RSA(this);
        }
    }
}
