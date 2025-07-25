package com.gegprifti.android.numbertheoryalgorithms.fragments.common;


import com.gegprifti.android.numbertheoryalgorithms.progress.ProgressStatus;
import com.gegprifti.android.numbertheoryalgorithms.algorithms.common.AlgorithmName;


public interface Callback {
    void callbackResult(AlgorithmName algorithmName, Object result, ProgressStatus progressStatus);
}