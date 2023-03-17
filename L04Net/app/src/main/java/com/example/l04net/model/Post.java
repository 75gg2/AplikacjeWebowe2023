package com.example.l04net.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int id;
    private int userId;
    private String title;

    @SerializedName("body") // nazwa pola w API przekładana jest na
    private String text; // naszą nazwę w aplikacji - nie jest to konieczna operacja ale warto wiedzieć że się da


    public Post(int id, int userId, String title, String text) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("\nid: %s\nuserId: %s\ntitle: %s\ntext: %s",id,userId,title,text);
    }
}

