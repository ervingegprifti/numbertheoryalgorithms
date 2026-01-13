package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


import java.math.BigInteger;


public abstract class Algorithm implements Calculator {
    protected static final BigInteger ZERO = BigInteger.ZERO;
    protected static final BigInteger ONE = BigInteger.ONE;
    protected static final BigInteger TWO = BigInteger.valueOf(2L);
    protected static final BigInteger THREE = BigInteger.valueOf(3L);
    protected static final BigInteger FOUR = BigInteger.valueOf(4L);

    protected final AlgorithmParameters algorithmParameters;
    protected static final String COLOR = "#8C5900";
    protected static final String COLOR_CIAN_DARK = "#008b8b";

    protected static final String BULLET = ""; // "•"; // TODO +++ Useless, remove it later.
    protected static final String TAB = ""; // "\t" // TODO +++ Useless, remove it later.
    protected static final String STOP = "■";
    protected static final String RIGHT_ARROW_COLORED = "<font color='#8C5900'>➡</font>";
    protected static final String NBSP = "&nbsp;";
    protected static final String LEFT_RIGHT_ARROW = "<b>⬌</b>";


    public Algorithm(AlgorithmParameters algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
    }
}