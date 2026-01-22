package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;


import com.gegprifti.android.numbertheoryalgorithms.cyclesets.CycleSet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ModFactorsExampleCycleSet implements CycleSet {
    private final List<ModFactorsExample> examples;
    private int index = 0;


    public ModFactorsExampleCycleSet() {
        List<ModFactorsExample> examples = new ArrayList<>();

        ModFactorsExample e1 = new ModFactorsExample.Builder()
                .name("E₁")
                .n(new BigInteger("713"))
                .b(new BigInteger("8"))
                .build();
        examples.add(e1);

        ModFactorsExample e2 = new ModFactorsExample.Builder()
                .name("E₂")
                .n(new BigInteger("713"))
                .b(new BigInteger("16"))
                .build();
        examples.add(e2);

        ModFactorsExample e3 = new ModFactorsExample.Builder()
                .name("E₃")
                .n(new BigInteger("713"))
                .b(new BigInteger("6"))
                .build();
        examples.add(e3);

        ModFactorsExample e4 = new ModFactorsExample.Builder()
                .name("E₄")
                .n(new BigInteger("713"))
                .b(new BigInteger("12"))
                .build();
        examples.add(e4);

        ModFactorsExample e5 = new ModFactorsExample.Builder()
                .name("E₅")
                .n(new BigInteger("551"))
                .b(new BigInteger("8"))
                .build();
        examples.add(e5);

        ModFactorsExample e6 = new ModFactorsExample.Builder()
                .name("E₆")
                .n(new BigInteger("551"))
                .b(new BigInteger("16"))
                .build();
        examples.add(e6);

        ModFactorsExample e7 = new ModFactorsExample.Builder()
                .name("E₇")
                .n(new BigInteger("551"))
                .b(new BigInteger("6"))
                .build();
        examples.add(e7);

        ModFactorsExample e8 = new ModFactorsExample.Builder()
                .name("E₈")
                .n(new BigInteger("551"))
                .b(new BigInteger("12"))
                .build();
        examples.add(e8);

        this.examples = Collections.unmodifiableList(examples);
    }


    @Override
    public Object next() {
        if (this.examples.isEmpty()) {
            return null;
        }
        ModFactorsExample example = this.examples.get(index);
        this.index = (this.index + 1) % this.examples.size();
        return example;
    }
}
