package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;


import com.gegprifti.android.numbertheoryalgorithms.cyclesets.CycleSet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ModFactorsCycleSet implements CycleSet {
    private final List<ModFactorsExample> examples;
    private int index = 0;


    // <!--₀ ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉-->
    public ModFactorsCycleSet() {
        List<ModFactorsExample> examples = new ArrayList<>();

        String e1Name = "E₁";
        BigInteger e1N = new BigInteger("713");
        BigInteger e1B = new BigInteger("8");
        ModFactorsExample e1 = new ModFactorsExample(e1Name, e1N, e1B);
        examples.add(e1);

        String e2Name = "E₂";
        BigInteger e2N = new BigInteger("713");
        BigInteger e2B = new BigInteger("16");
        ModFactorsExample e2 = new ModFactorsExample(e2Name, e2N, e2B);
        examples.add(e2);

        String e3Name = "E₃";
        BigInteger e3N = new BigInteger("713");
        BigInteger e3B = new BigInteger("6");
        ModFactorsExample e3 = new ModFactorsExample(e3Name, e3N, e3B);
        examples.add(e3);

        String e4Name = "E₄";
        BigInteger e4N = new BigInteger("713");
        BigInteger e4B = new BigInteger("12");
        ModFactorsExample e4 = new ModFactorsExample(e4Name, e4N, e4B);
        examples.add(e4);

        String e5Name = "E₅";
        BigInteger e5N = new BigInteger("551");
        BigInteger e5B = new BigInteger("8");
        ModFactorsExample e5 = new ModFactorsExample(e5Name, e5N, e5B);
        examples.add(e5);

        String e6Name = "E₆";
        BigInteger e6N = new BigInteger("551");
        BigInteger e6B = new BigInteger("16");
        ModFactorsExample e6 = new ModFactorsExample(e6Name, e6N, e6B);
        examples.add(e6);

        String e7Name = "E₇";
        BigInteger e7N = new BigInteger("551");
        BigInteger e7B = new BigInteger("6");
        ModFactorsExample e7 = new ModFactorsExample(e7Name, e7N, e7B);
        examples.add(e7);

        String e8Name = "E₈";
        BigInteger e8N = new BigInteger("551");
        BigInteger e8B = new BigInteger("12");
        ModFactorsExample e8 = new ModFactorsExample(e8Name, e8N, e8B);
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
