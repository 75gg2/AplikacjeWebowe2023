package com.example.kginsta.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.kginsta.BR;

public class User extends BaseObservable {
    @Bindable
    private String login;

    @Bindable
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyPropertyChanged(BR.login);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}