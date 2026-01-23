package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


import java.util.List;

public interface ListStringCalculator extends Calculator {
    @Override
    List<String> calculate() throws InterruptedException;
}