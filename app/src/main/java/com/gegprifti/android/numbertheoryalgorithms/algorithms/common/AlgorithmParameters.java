package com.gegprifti.android.numbertheoryalgorithms.algorithms.common;


import androidx.annotation.NonNull;
import com.gegprifti.android.numbertheoryalgorithms.fragments.common.Callback;
import java.math.BigInteger;


public class AlgorithmParameters {
    private final AlgorithmName algorithmName;
    private final Callback callback;
    private BigInteger input1;
    private BigInteger input2;
    private BigInteger input3;
    private BigInteger input4;
    private BigInteger input5;
    private BigInteger input6;
    private boolean includeTrivialSolutions;
    private boolean includeOnlyPositiveSolutions;
    private boolean includeOnlyNegativeSolutions;

    public void setInput1(BigInteger input1) { this.input1 = input1; }
    public void setInput2(BigInteger input2) { this.input2 = input2; }
    public void setInput3(BigInteger input3) { this.input3 = input3; }
    public void setInput4(BigInteger input4) { this.input4 = input4; }
    public void setInput5(BigInteger input5) { this.input5 = input5; }
    public void setInput6(BigInteger input6) { this.input6 = input6; }
    public void setIncludeTrivialSolutions(boolean includeTrivialSolutions) { this.includeTrivialSolutions = includeTrivialSolutions; }
    public void setIncludeOnlyPositiveSolutions(boolean includeOnlyPositiveSolutions) { this.includeOnlyPositiveSolutions = includeOnlyPositiveSolutions; }
    public void setIncludeOnlyNegativeSolutions(boolean includeOnlyNegativeSolutions) { this.includeOnlyNegativeSolutions = includeOnlyNegativeSolutions; }

    public AlgorithmName getAlgorithmName() { return this.algorithmName; }
    public Callback getCallback() { return this.callback; }
    public BigInteger getInput1() { return this.input1; }
    public BigInteger getInput2() { return this.input2; }
    public BigInteger getInput3() { return this.input3; }
    public BigInteger getInput4() { return this.input4; }
    public BigInteger getInput5() { return this.input5; }
    public BigInteger getInput6() { return this.input6; }
    public boolean getIncludeTrivialSolutions() { return  this.includeTrivialSolutions; }
    public boolean getIncludeOnlyPositiveSolutions() { return  this.includeOnlyPositiveSolutions; }
    public boolean getIncludeOnlyNegativeSolutions() { return  this.includeOnlyNegativeSolutions; }

    public AlgorithmParameters(AlgorithmName algorithmName, Callback callback) {
        this.algorithmName = algorithmName;
        this.callback = callback;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "algorithmName: " + this.algorithmName + "; " +
                "includeTrivialSolutions: " + this.includeTrivialSolutions + "; " +
                "includeOnlyPositiveSolutions: " + this.includeOnlyPositiveSolutions + "; " +
                "includeOnlyNegativeSolutions: " + this.includeOnlyNegativeSolutions +
                "}";
    }
}