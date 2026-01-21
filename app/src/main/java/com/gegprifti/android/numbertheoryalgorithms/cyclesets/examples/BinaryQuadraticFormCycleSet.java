package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;


import com.gegprifti.android.numbertheoryalgorithms.cyclesets.CycleSet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BinaryQuadraticFormCycleSet implements CycleSet {
    private final List<BinaryQuadraticFormExample> examples;
    private int index = 0;

    // <!--₀ ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉-->
    public BinaryQuadraticFormCycleSet() {
        List<BinaryQuadraticFormExample> examples = new ArrayList<>();

        String e1Name = "E₁";
        BigInteger e1A = new BigInteger("0");
        BigInteger e1B = new BigInteger("8");
        BigInteger e1C = new BigInteger("0");
        BigInteger e1D = new BigInteger("3");
        BigInteger e1E = new BigInteger("3");
        BigInteger e1F = new BigInteger("88");
        BinaryQuadraticFormExample e1 = new BinaryQuadraticFormExample(e1Name, e1A, e1B, e1C, e1D, e1E, e1F);
        examples.add(e1);

        //String e2Name = "E₂";
        //BinaryQuadraticFormExample e2 = new BinaryQuadraticFormExample(e2Name, e2N, e2B);
        //examples.add(e2);

        //String e3Name = "E₃";
        //BinaryQuadraticFormExample e3 = new BinaryQuadraticFormExample(e3Name, e3N, e3B);
        //examples.add(e3);

        //String e4Name = "E₄";
        //BinaryQuadraticFormExample e4 = new BinaryQuadraticFormExample(e4Name, e4N, e4B);
        //examples.add(e4);

        //String e5Name = "E₅";
        //BinaryQuadraticFormExample e5 = new BinaryQuadraticFormExample(e5Name, e5N, e5B);
        //examples.add(e5);

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
