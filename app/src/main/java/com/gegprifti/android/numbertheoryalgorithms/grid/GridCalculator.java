package com.gegprifti.android.numbertheoryalgorithms.grid;


import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.Calculator;

import java.util.List;


public interface GridCalculator extends Calculator {
    @Override
    Grid calculate() throws InterruptedException;
}