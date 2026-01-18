package com.gegprifti.android.numbertheoryalgorithms.cyclesets.rsa;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RSASet {
    private final List<RSA> rsaList;
    private int index = 0;

    // <!--₀ ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉-->
    // https://en.wikipedia.org/wiki/RSA_Factoring_Challenge
    public RSASet() {
        List<RSA> list = new ArrayList<>();

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-100
        String rsa_100 = "RSA₁₀₀";
        BigInteger rsa_100_n = new BigInteger("1522605027922533360535618378132637429718068114961380688657908494580122963258952897654000350692006139");
        BigInteger rsa_100_p = new BigInteger("37975227936943673922808872755445627854565536638199");
        BigInteger rsa_100_q = new BigInteger("40094690950920881030683735292761468389214899724061");
        RSA rsa100 = new RSA(rsa_100, rsa_100_n, rsa_100_p, rsa_100_q);
        list.add(rsa100);
        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-110
        String rsa_110 = "RSA₁₁₀";
        BigInteger rsa_110_n = new BigInteger("35794234179725868774991807832568455403003778024228226193532908190484670252364677411513516111204504060317568667");
        BigInteger rsa_110_p = new BigInteger("6122421090493547576937037317561418841225758554253106999");
        BigInteger rsa_110_q = new BigInteger("5846418214406154678836553182979162384198610505601062333");
        RSA rsa110 = new RSA(rsa_110, rsa_110_n, rsa_110_p, rsa_110_q);
        list.add(rsa110);
        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-120
        String rsa_120 = "RSA₁₂₀";
        BigInteger rsa_120_n = new BigInteger("227010481295437363334259960947493668895875336466084780038173258247009162675779735389791151574049166747880487470296548479");
        BigInteger rsa_120_p = new BigInteger("327414555693498015751146303749141488063642403240171463406883");
        BigInteger rsa_120_q = new BigInteger("693342667110830181197325401899700641361965863127336680673013");
        RSA rsa120 = new RSA(rsa_120, rsa_120_n, rsa_120_p, rsa_120_q);
        list.add(rsa120);

        this.rsaList = Collections.unmodifiableList(list);
    }

    public RSA getNextRSA() {
        if (rsaList.isEmpty()) {
            return null;
        }
        RSA rsa = rsaList.get(index);
        index = (index + 1) % rsaList.size();
        return rsa;
    }
}
