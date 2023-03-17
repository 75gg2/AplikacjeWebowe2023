package com.example.l04net.model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    private int id;
    private int postId;
    private String name;
    private String email;
    @SerializedName("body")
    private String text;

    @Override
    public String toString() {
        return "\n\nComment{" +
                "id=" + id +
                ", postId=" + postId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
