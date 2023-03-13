package com.example.l04.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ItemViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> mutableItemList;

    private ArrayList<String> itemList;

    public ItemViewModel() {
        this.mutableItemList = new MutableLiveData<>();
        this.itemList = new ArrayList<>();
        itemList.add("category 1");
        itemList.add("category 2");
        itemList.add("category 3");

        this.mutableItemList.setValue(this.itemList);
    }

    public MutableLiveData<ArrayList<String>> getObservedItemList() {

        return mutableItemList;
    }
}