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
        String rsa100Name = "RSA₁₀₀";
        BigInteger rsa100N = new BigInteger("1522605027922533360535618378132637429718068114961380688657908494580122963258952897654000350692006139");
        BigInteger rsa100P = new BigInteger("37975227936943673922808872755445627854565536638199");
        BigInteger rsa100Q = new BigInteger("40094690950920881030683735292761468389214899724061");
        RSA rsa100 = new RSA(rsa100Name, rsa100N, rsa100P, rsa100Q);
        rsaList.add(rsa100);

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-110
        String rsa110Name = "RSA₁₁₀";
        BigInteger rsa110N = new BigInteger("35794234179725868774991807832568455403003778024228226193532908190484670252364677411513516111204504060317568667");
        BigInteger rsa110P = new BigInteger("6122421090493547576937037317561418841225758554253106999");
        BigInteger rsa110Q = new BigInteger("5846418214406154678836553182979162384198610505601062333");
        RSA rsa110 = new RSA(rsa110Name, rsa110N, rsa110P, rsa110Q);
        rsaList.add(rsa110);

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-120
        String rsa120Name = "RSA₁₂₀";
        BigInteger rsa120N = new BigInteger("227010481295437363334259960947493668895875336466084780038173258247009162675779735389791151574049166747880487470296548479");
        BigInteger rsa120P = new BigInteger("327414555693498015751146303749141488063642403240171463406883");
        BigInteger rsa120Q = new BigInteger("693342667110830181197325401899700641361965863127336680673013");
        RSA rsa120 = new RSA(rsa120Name, rsa120N, rsa120P, rsa120Q);
        rsaList.add(rsa120);

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-129
        String rsa129Name = "RSA₁₂₉";
        BigInteger rsa129N = new BigInteger("114381625757888867669235779976146612010218296721242362562561842935706935245733897830597123563958705058989075147599290026879543541");
        BigInteger rsa129P = new BigInteger("3490529510847650949147849619903898133417764638493387843990820577");
        BigInteger rsa129Q = new BigInteger("32769132993266709549961988190834461413177642967992942539798288533");
        RSA rsa129 = new RSA(rsa129Name, rsa129N, rsa129P, rsa129Q);
        rsaList.add(rsa129);

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-130
        String rsa130Name = "RSA₁₃₀";
        BigInteger rsa130N = new BigInteger("1807082088687404805951656164405905566278102516769401349170127021450056662540244048387341127590812303371781887966563182013214880557");
        BigInteger rsa130P = new BigInteger("39685999459597454290161126162883786067576449112810064832555157243");
        BigInteger rsa130Q = new BigInteger("45534498646735972188403686897274408864356301263205069600999044599");
        RSA rsa130 = new RSA(rsa130Name, rsa130N, rsa130P, rsa130Q);
        rsaList.add(rsa130);

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-140
        String rsa140Name = "RSA₁₄₀";
        BigInteger rsa140N = new BigInteger("21290246318258757547497882016271517497806703963277216278233383215381949984056495911366573853021918316783107387995317230889569230873441936471");
        BigInteger rsa140P = new BigInteger("3398717423028438554530123627613875835633986495969597423490929302771479");
        BigInteger rsa140Q = new BigInteger("6264200187401285096151654948264442219302037178623509019111660653946049");
        RSA rsa140 = new RSA(rsa140Name, rsa140N, rsa140P, rsa140Q);
        rsaList.add(rsa140);

        // https://en.wikipedia.org/wiki/RSA_numbers#RSA-150
        String rsa150Name = "RSA₁₅₀";
        BigInteger rsa150N = new BigInteger("155089812478348440509606754370011861770654545830995430655466945774312632703463465954363335027577729025391453996787414027003501631772186840890795964683");
        BigInteger rsa150P = new BigInteger("348009867102283695483970451047593424831012817350385456889559637548278410717");
        BigInteger rsa150Q = new BigInteger("445647744903640741533241125787086176005442536297766153493419724532460296199");
        RSA rsa150 = new RSA(rsa150Name, rsa150N, rsa150P, rsa150Q);
        rsaList.add(rsa150);

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
