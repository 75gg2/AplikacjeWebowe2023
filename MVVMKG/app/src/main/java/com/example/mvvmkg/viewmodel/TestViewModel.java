package com.example.mvvmkg.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TestViewModel extends ViewModel {

    public MutableLiveData<Integer> ageLiveData;

    public TestViewModel() {
        ageLiveData = new MutableLiveData<>();
    }
    public void setupData(int age){

        this.ageLiveData.setValue(age);

    }
    public MutableLiveData<Integer> getObservedAge() {
        return ageLiveData;
    }

    public void changeAge(int age) {
        ageLiveData.setValue(age);

    }
}
