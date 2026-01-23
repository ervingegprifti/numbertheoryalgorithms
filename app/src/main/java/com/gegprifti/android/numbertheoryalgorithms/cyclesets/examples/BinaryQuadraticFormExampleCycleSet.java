package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;


import com.gegprifti.android.numbertheoryalgorithms.cyclesets.CycleSet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BinaryQuadraticFormExampleCycleSet implements CycleSet {
    private final List<BinaryQuadraticFormExample> examples;
    private int index = 0;


    public BinaryQuadraticFormExampleCycleSet() {
        List<BinaryQuadraticFormExample> examples = new ArrayList<>();

        BinaryQuadraticFormExample e1 = new BinaryQuadraticFormExample.Builder()
                .name("E₁")
                .a(new BigInteger("0"))
                .b(new BigInteger("8"))
                .c(new BigInteger("0"))
                .d(new BigInteger("3"))
                .e(new BigInteger("3"))
                .f(new BigInteger("88"))
                .m(new BigInteger("8"))
                .r(new BigInteger("0"))
                .build();
        examples.add(e1);

        BinaryQuadraticFormExample e2 = new BinaryQuadraticFormExample.Builder()
                .name("E₂")
                .a(new BigInteger("0"))
                .b(new BigInteger("16"))
                .c(new BigInteger("0"))
                .d(new BigInteger("7"))
                .e(new BigInteger("3"))
                .f(new BigInteger("113"))
                .m(new BigInteger("16"))
                .r(new BigInteger("1"))
                .build();
        examples.add(e2);

        BinaryQuadraticFormExample e3 = new BinaryQuadraticFormExample.Builder()
                .name("E₃")
                .a(new BigInteger("0"))
                .b(new BigInteger("8"))
                .c(new BigInteger("0"))
                .d(new BigInteger("7"))
                .e(new BigInteger("7"))
                .f(new BigInteger("83"))
                .m(new BigInteger("8"))
                .r(new BigInteger("3"))
                .build();
        examples.add(e3);

        BinaryQuadraticFormExample e4 = new BinaryQuadraticFormExample.Builder()
                .name("E₄")
                .a(new BigInteger("0"))
                .b(new BigInteger("16"))
                .c(new BigInteger("0"))
                .d(new BigInteger("15"))
                .e(new BigInteger("11"))
                .f(new BigInteger("104"))
                .m(new BigInteger("16"))
                .r(new BigInteger("8"))
                .build();
        examples.add(e4);

        BinaryQuadraticFormExample e5 = new BinaryQuadraticFormExample.Builder()
                .name("E₅")
                .a(new BigInteger("0"))
                .b(new BigInteger("8"))
                .c(new BigInteger("0"))
                .d(new BigInteger("3"))
                .e(new BigInteger("5"))
                .f(new BigInteger("14"))
                .m(new BigInteger("8"))
                .r(new BigInteger("6"))
                .build();
        examples.add(e5);

        this.examples = Collections.unmodifiableList(examples);
    }

    @Override
    public Object next() {
        if (this.examples.isEmpty()) {
            return null;
        }
        BinaryQuadraticFormExample example = this.examples.get(index);
        this.index = (this.index + 1) % this.examples.size();
        return example;
    }
}
