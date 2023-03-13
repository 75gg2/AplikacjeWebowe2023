package com.example.twowaybinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Movie extends BaseObservable {


    @Bindable
    private String title;

    public String getTitle() {
        return title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        notifyPropertyChanged(BR.checked);
    }

    @Bindable
    public boolean checked = false;

    public Movie(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
}
