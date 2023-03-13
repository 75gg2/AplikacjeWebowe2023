package com.example.l04net.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsAPI {
    @GET("/posts")
    Call<List<Post>> getAllPosts();

}
