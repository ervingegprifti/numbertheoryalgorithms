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

        /*
            String a = "0";
            String b = "16";
            String c = "0";
            String d = "7";
            String e = "3";
            String f = "113";
            String m = "16";
            String r = "1";
         */
        //String e2Name = "E₂";
        //BinaryQuadraticFormExample e2 = new BinaryQuadraticFormExample(e2Name, e2N, e2B);
        //examples.add(e2);

        /*
                    String a = "0";
            String b = "8";
            String c = "0";
            String d = "7";
            String e = "7";
            String f = "83";
            String m = "8";
            String r = "3";
         */
        //String e3Name = "E₃";
        //BinaryQuadraticFormExample e3 = new BinaryQuadraticFormExample(e3Name, e3N, e3B);
        //examples.add(e3);

        /*
                    String a = "0";
            String b = "16";
            String c = "0";
            String d = "15";
            String e = "11";
            String f = "104";
            String m = "16";
            String r = "8";
         */
        //String e4Name = "E₄";
        //BinaryQuadraticFormExample e4 = new BinaryQuadraticFormExample(e4Name, e4N, e4B);
        //examples.add(e4);

        /*
                    String a = "0";
            String b = "8";
            String c = "0";
            String d = "3";
            String e = "5";
            String f = "14";
            String m = "8";
            String r = "6";
         */
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
