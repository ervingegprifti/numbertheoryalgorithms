package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


import com.gegprifti.android.numbertheoryalgorithms.common.RowItem;
import java.util.List;


public interface GridCalculator {
    List<List<RowItem>> calculate() throws InterruptedException;
}