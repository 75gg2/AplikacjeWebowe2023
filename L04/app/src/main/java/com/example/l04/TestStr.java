package com.example.l04;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class TestStr extends BaseObservable{
    @Bindable
    private int str;

    public TestStr(int str) {
        this.str = str;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }
}
