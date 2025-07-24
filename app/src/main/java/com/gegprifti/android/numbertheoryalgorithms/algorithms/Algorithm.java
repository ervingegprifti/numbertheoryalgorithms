package com.gegprifti.android.numbertheoryalgorithms.algorithms;

import com.gegprifti.android.numbertheoryalgorithms.AlgorithmParameters;
import com.gegprifti.android.numbertheoryalgorithms.ProgressManager;

import java.math.BigInteger;

public abstract class Algorithm {
    protected static final BigInteger ZERO = BigInteger.ZERO;
    protected static final BigInteger ONE = BigInteger.ONE;
    protected static final BigInteger TWO = BigInteger.valueOf(2L);
    protected static final BigInteger THREE = BigInteger.valueOf(3L);
    protected static final BigInteger FOUR = BigInteger.valueOf(4L);

    protected final AlgorithmParameters algorithmParameters;


    public Algorithm(AlgorithmParameters algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
    }
}