package com.gegprifti.android.numbertheoryalgorithms.cyclesets.examples;


import com.gegprifti.android.numbertheoryalgorithms.cyclesets.CycleSet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RSACycleSet implements CycleSet {
    private final List<RSA> elements;
    private int index = 0;


    // <!--₀ ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉ ₍ ₎-->
    // https://en.wikipedia.org/wiki/RSA_Factoring_Challenge
    public RSACycleSet() {
        List<RSA> elements = new ArrayList<>();

        RSA rsa100 = new RSA.Builder()
                .name("RSA₁₀₀")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-100")
                .n(new BigInteger("1522605027922533360535618378132637429718068114961380688657908494580122963258952897654000350692006139"))
                .p(new BigInteger("37975227936943673922808872755445627854565536638199"))
                .q(new BigInteger("40094690950920881030683735292761468389214899724061"))
                .build();
        elements.add(rsa100);

        RSA rsa110 = new RSA.Builder()
                .name("RSA₁₁₀")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-110")
                .n(new BigInteger("35794234179725868774991807832568455403003778024228226193532908190484670252364677411513516111204504060317568667"))
                .p(new BigInteger("6122421090493547576937037317561418841225758554253106999"))
                .q(new BigInteger("5846418214406154678836553182979162384198610505601062333"))
                .build();
        elements.add(rsa110);

        RSA rsa120 = new RSA.Builder()
                .name("RSA₁₂₀")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-120")
                .n(new BigInteger("227010481295437363334259960947493668895875336466084780038173258247009162675779735389791151574049166747880487470296548479"))
                .p(new BigInteger("327414555693498015751146303749141488063642403240171463406883"))
                .q(new BigInteger("693342667110830181197325401899700641361965863127336680673013"))
                .build();
        elements.add(rsa120);

        RSA rsa129 = new RSA.Builder()
                .name("RSA₁₂₉")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-129")
                .n(new BigInteger("114381625757888867669235779976146612010218296721242362562561842935706935245733897830597123563958705058989075147599290026879543541"))
                .p(new BigInteger("3490529510847650949147849619903898133417764638493387843990820577"))
                .q(new BigInteger("32769132993266709549961988190834461413177642967992942539798288533"))
                .build();
        elements.add(rsa129);

        RSA rsa130 = new RSA.Builder()
                .name("RSA₁₃₀")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-130")
                .n(new BigInteger("1807082088687404805951656164405905566278102516769401349170127021450056662540244048387341127590812303371781887966563182013214880557"))
                .p(new BigInteger("39685999459597454290161126162883786067576449112810064832555157243"))
                .q(new BigInteger("45534498646735972188403686897274408864356301263205069600999044599"))
                .build();
        elements.add(rsa130);

        RSA rsa140 = new RSA.Builder()
                .name("RSA₁₄₀")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-140")
                .n(new BigInteger("21290246318258757547497882016271517497806703963277216278233383215381949984056495911366573853021918316783107387995317230889569230873441936471"))
                .p(new BigInteger("3398717423028438554530123627613875835633986495969597423490929302771479"))
                .q(new BigInteger("6264200187401285096151654948264442219302037178623509019111660653946049"))
                .build();
        elements.add(rsa140);

        RSA rsa150 = new RSA.Builder()
                .name("RSA₁₅₀")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-150")
                .n(new BigInteger("155089812478348440509606754370011861770654545830995430655466945774312632703463465954363335027577729025391453996787414027003501631772186840890795964683"))
                .p(new BigInteger("348009867102283695483970451047593424831012817350385456889559637548278410717"))
                .q(new BigInteger("445647744903640741533241125787086176005442536297766153493419724532460296199"))
                .build();
        elements.add(rsa150);

        RSA rsa155 = new RSA.Builder()
                .name("RSA₁₅₅")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-155")
                .n(new BigInteger("10941738641570527421809707322040357612003732945449205990913842131476349984288934784717997257891267332497625752899781833797076537244027146743531593354333897"))
                .p(new BigInteger("102639592829741105772054196573991675900716567808038066803341933521790711307779"))
                .q(new BigInteger("106603488380168454820927220360012878679207958575989291522270608237193062808643"))
                .build();
        elements.add(rsa155);

        RSA rsa160 = new RSA.Builder()
                .name("RSA₁₆₀")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-160")
                .n(new BigInteger("2152741102718889701896015201312825429257773588845675980170497676778133145218859135673011059773491059602497907111585214302079314665202840140619946994927570407753"))
                .p(new BigInteger("45427892858481394071686190649738831656137145778469793250959984709250004157335359"))
                .q(new BigInteger("47388090603832016196633832303788951973268922921040957944741354648812028493909367"))
                .build();
        elements.add(rsa160);

        RSA rsa170 = new RSA.Builder()
                .name("RSA₁₇₀")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-170")
                .n(new BigInteger("26062623684139844921529879266674432197085925380486406416164785191859999628542069361450283931914514618683512198164805919882053057222974116478065095809832377336510711545759"))
                .p(new BigInteger("3586420730428501486799804587268520423291459681059978161140231860633948450858040593963"))
                .q(new BigInteger("7267029064107019078863797763923946264136137803856996670313708936002281582249587494493"))
                .build();
        elements.add(rsa170);

        RSA rsa174 = new RSA.Builder()
                .name("RSA₁₇₄")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-576")
                .n(new BigInteger("188198812920607963838697239461650439807163563379417382700763356422988859715234665485319060606504743045317388011303396716199692321205734031879550656996221305168759307650257059"))
                .p(new BigInteger("398075086424064937397125500550386491199064362342526708406385189575946388957261768583317"))
                .q(new BigInteger("472772146107435302536223071973048224632914695302097116459852171130520711256363590397527"))
                .build();
        elements.add(rsa174);

        RSA rsa180 = new RSA.Builder()
                .name("RSA₁₈₀")
                .reference("https://en.wikipedia.org/wiki/RSA_numbers#RSA-180")
                .n(new BigInteger("191147927718986609689229466631454649812986246276667354864188503638807260703436799058776201365135161278134258296128109200046702912984568752800330221777752773957404540495707851421041"))
                .p(new BigInteger("400780082329750877952581339104100572526829317815807176564882178998497572771950624613470377"))
                .q(new BigInteger("476939688738611836995535477357070857939902076027788232031989775824606225595773435668861833"))
                .build();
        elements.add(rsa180);

//        RSA rsaXXX = new RSA.Builder()
//                .name("RSA₀ ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉ ₍ ₎")
//                .reference("XXXXXX")
//                .n(new BigInteger("XXXXXX"))
//                .p(new BigInteger("XXXXXX"))
//                .q(new BigInteger("XXXXXX"))
//                .build();
//        elements.add(rsaXXX);

        this.elements = Collections.unmodifiableList(elements);
    }


    @Override
    public Object next() {
        if (this.elements.isEmpty()) {
            return null;
        }
        RSA rsa = this.elements.get(index);
        this.index = (this.index + 1) % this.elements.size();
        return rsa;
    }
}
