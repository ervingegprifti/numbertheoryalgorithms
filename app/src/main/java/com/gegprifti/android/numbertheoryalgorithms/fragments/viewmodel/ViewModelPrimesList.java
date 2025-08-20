package com.gegprifti.android.numbertheoryalgorithms.fragments.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gegprifti.android.numbertheoryalgorithms.fragments.FragmentPrimesList;

public class ViewModelPrimesList extends ViewModel {
    private final MutableLiveData<String> columnsText = new MutableLiveData<>(String.valueOf(FragmentPrimesList.COLUMNS_DEFAULT_VALUE));
    private final MutableLiveData<String> numbersText = new MutableLiveData<>(String.valueOf(FragmentPrimesList.NUMBERS_DEFAULT_VALUE));
    private final MutableLiveData<Boolean> biggerControls = new MutableLiveData<>(false);


    public LiveData<String> getColumnsButtonText() {
        return columnsText;
    }


    public LiveData<String> getNumbersButtonText() {
        return numbersText;
    }


    public LiveData<Boolean> getBiggerControls() {
        return biggerControls;
    }


    public void setColumnsButtonText(String text) {
        columnsText.setValue(text);
    }


    public void setNumbersButtonText(String text) {
        numbersText.setValue(text);
    }


    public void setBiggerControls(boolean value) {
        Boolean currentValue = biggerControls.getValue();
        if (currentValue == null || currentValue != value) {
            biggerControls.setValue(value);
        }
    }
}