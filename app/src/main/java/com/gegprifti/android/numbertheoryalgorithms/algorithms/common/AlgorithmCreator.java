package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


/**
 * A functional interface for creating an algorithm instance.
 * This is used to support API levels below 24.
 */
public interface AlgorithmCreator {
    Calculator create(AlgorithmParameters params);
}