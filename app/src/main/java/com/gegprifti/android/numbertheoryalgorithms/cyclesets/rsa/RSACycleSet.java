package com.gegprifti.android.numbertheoryalgorithms.cyclesets.rsa;


import com.gegprifti.android.numbertheoryalgorithms.cyclesets.CycleSet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RSACycleSet implements CycleSet {
    private final List<RSA> rsaList;
    private int index = 0;

    // <!--₀ ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉-->
    // https://en.wikipedia.org/wiki/RSA_Factoring_Challenge
    public RSACycleSet() {
        List<RSA> rsaList = new ArrayList<>();

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-100
        String rsa_100 = "RSA₁₀₀";
        BigInteger rsa_100_n = new BigInteger("1522605027922533360535618378132637429718068114961380688657908494580122963258952897654000350692006139");
        BigInteger rsa_100_p = new BigInteger("37975227936943673922808872755445627854565536638199");
        BigInteger rsa_100_q = new BigInteger("40094690950920881030683735292761468389214899724061");
        RSA rsa100 = new RSA(rsa_100, rsa_100_n, rsa_100_p, rsa_100_q);
        rsaList.add(rsa100);

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-110
        String rsa_110 = "RSA₁₁₀";
        BigInteger rsa_110_n = new BigInteger("35794234179725868774991807832568455403003778024228226193532908190484670252364677411513516111204504060317568667");
        BigInteger rsa_110_p = new BigInteger("6122421090493547576937037317561418841225758554253106999");
        BigInteger rsa_110_q = new BigInteger("5846418214406154678836553182979162384198610505601062333");
        RSA rsa110 = new RSA(rsa_110, rsa_110_n, rsa_110_p, rsa_110_q);
        rsaList.add(rsa110);

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-120
        String rsa_120 = "RSA₁₂₀";
        BigInteger rsa_120_n = new BigInteger("227010481295437363334259960947493668895875336466084780038173258247009162675779735389791151574049166747880487470296548479");
        BigInteger rsa_120_p = new BigInteger("327414555693498015751146303749141488063642403240171463406883");
        BigInteger rsa_120_q = new BigInteger("693342667110830181197325401899700641361965863127336680673013");
        RSA rsa120 = new RSA(rsa_120, rsa_120_n, rsa_120_p, rsa_120_q);
        rsaList.add(rsa120);

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-129
        String rsa_129 = "RSA₁₂₉";
        BigInteger rsa_129_n = new BigInteger("114381625757888867669235779976146612010218296721242362562561842935706935245733897830597123563958705058989075147599290026879543541");
        BigInteger rsa_129_p = new BigInteger("3490529510847650949147849619903898133417764638493387843990820577");
        BigInteger rsa_129_q = new BigInteger("32769132993266709549961988190834461413177642967992942539798288533");
        RSA rsa129 = new RSA(rsa_129, rsa_129_n, rsa_129_p, rsa_129_q);
        rsaList.add(rsa129);

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-130
        String rsa_130 = "RSA₁₃₀";
        BigInteger rsa_130_n = new BigInteger("1807082088687404805951656164405905566278102516769401349170127021450056662540244048387341127590812303371781887966563182013214880557");
        BigInteger rsa_130_p = new BigInteger("39685999459597454290161126162883786067576449112810064832555157243");
        BigInteger rsa_130_q = new BigInteger("45534498646735972188403686897274408864356301263205069600999044599");
        RSA rsa130 = new RSA(rsa_130, rsa_130_n, rsa_130_p, rsa_130_q);
        rsaList.add(rsa130);

        this.rsaList = Collections.unmodifiableList(rsaList);
    }


    @Override
    public Object next() {
        if (this.rsaList.isEmpty()) {
            return null;
        }
        RSA rsa = this.rsaList.get(index);
        this.index = (this.index + 1) % this.rsaList.size();
        return rsa;
    }
}
