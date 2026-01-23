package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;


import java.math.BigInteger;


public class ModFactorsExample {
    private final String name;
    private final BigInteger n, b;


    private ModFactorsExample(ModFactorsExample.Builder builder) {
        this.name = builder.name;
        this.n = builder.n;
        this.b = builder.b;
    }


    public static class Builder {
        private String name;
        private BigInteger n, b;

        public ModFactorsExample.Builder name(String name) {
            this.name = name;
            return this;
        }
        public ModFactorsExample.Builder n(BigInteger n) {
            this.n = n;
            return this;
        }
        public ModFactorsExample.Builder b(BigInteger b) {
            this.b = b;
            return this;
        }

        public ModFactorsExample build() {
            return new ModFactorsExample(this);
        }
    }


    public String getName() {
        return this.name;
    }
    public BigInteger getN() {
        return this.n;
    }
    public BigInteger getB() {
        return this.b;
    }
}
