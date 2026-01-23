package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


import com.gegprifti.android.numbertheoryalgorithms.algorithms.*;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.binaryquadraticform.BinaryQuadraticForm;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.binaryquadraticform.BinaryQuadraticForm1;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.binaryquadraticform.BinaryQuadraticForm2;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.calculator.*;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.modfactors.ModFactorsAlg1;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.modfactors.ModFactorsAlg2;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.modfactors.ModFactorsAlg3;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.modfactors.ModFactorsCount;

import java.util.HashMap;
import java.util.Map;


public class AlgorithmFactory {
    private static final Map<AlgorithmName, AlgorithmCreator> registry = new HashMap<>();


    static {
        registry.put(AlgorithmName.CALCULATOR_ADDITION, Addition::new);
        registry.put(AlgorithmName.CALCULATOR_SUBTRACTION, Subtraction::new);
        registry.put(AlgorithmName.CALCULATOR_MULTIPLICATION, Multiplication::new);
        registry.put(AlgorithmName.CALCULATOR_DIVISION, Division::new);
        registry.put(AlgorithmName.CALCULATOR_POWER, Power::new);
        registry.put(AlgorithmName.CALCULATOR_ROOT, Root::new);
        registry.put(AlgorithmName.CALCULATOR_GCD, Gcd::new);
        registry.put(AlgorithmName.CALCULATOR_LCM, Lcm::new);
        registry.put(AlgorithmName.CALCULATOR_MOD, Mod::new);
        registry.put(AlgorithmName.CALCULATOR_MOD_INVERSE, ModInverse::new);
        registry.put(AlgorithmName.CALCULATOR_MODULAR_POWER, ModularPower::new);
        registry.put(AlgorithmName.CALCULATOR_IS_PROBABLE_PRIME, IsProbablePrime::new);
        registry.put(AlgorithmName.CALCULATOR_EULER_PHI, EulerPhi::new);
        registry.put(AlgorithmName.CALCULATOR_FACTORIAL, Factorial::new);
        registry.put(AlgorithmName.CALCULATOR_NEXT_PROBABLE_PRIME, NextProbablePrime::new);
        registry.put(AlgorithmName.CALCULATOR_NEXT_PROBABLE_TWIN_PRIME_PAIR, NextProbableTwinPrimePair::new);
        registry.put(AlgorithmName.BINARY_QUADRATIC_FORM, BinaryQuadraticForm::new);
        registry.put(AlgorithmName.BINARY_QUADRATIC_FORM_1, BinaryQuadraticForm1::new);
        registry.put(AlgorithmName.BINARY_QUADRATIC_FORM_2, BinaryQuadraticForm2::new);
        registry.put(AlgorithmName.EUCLIDEAN_ALGORITHM, EuclideanAlgorithm::new);
        registry.put(AlgorithmName.EXTENDED_EUCLIDEAN_ALGORITHM, ExtendedEuclideanAlgorithm::new);
        registry.put(AlgorithmName.LINEAR_CONGRUENCE_IN_ONE_VARIABLE, LinearCongruenceInOneVariable::new);
        registry.put(AlgorithmName.LINEAR_CONGRUENCE_IN_TWO_VARIABLES, LinearCongruenceInTwoVariables::new);
        registry.put(AlgorithmName.LINEAR_DIOPHANTINE_EQUATION_IN_TWO_VARIABLES, LinearDiophantineEquation::new);
        registry.put(AlgorithmName.TONELLI_SHANKS_ALGORITHM, TonelliShanksAlgorithm::new);
        registry.put(AlgorithmName.MOD_FACTORS_ALG1, ModFactorsAlg1::new);
        registry.put(AlgorithmName.MOD_FACTORS_ALG2, ModFactorsAlg2::new);
        registry.put(AlgorithmName.MOD_FACTORS_ALG3, ModFactorsAlg3::new);
        registry.put(AlgorithmName.MOD_FACTORS_COUNT, ModFactorsCount::new);
    }


    public static Calculator create(AlgorithmParameters algorithmParameters) {
        AlgorithmCreator algorithmCreator = registry.get(algorithmParameters.getAlgorithmName());
        if (algorithmCreator == null) {
            throw new IllegalArgumentException("Unknown algorithm: " + algorithmParameters.getAlgorithmName());
        }
        return algorithmCreator.create(algorithmParameters);
    }
}





