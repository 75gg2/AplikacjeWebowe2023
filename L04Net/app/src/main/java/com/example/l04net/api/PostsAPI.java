package com.example.l04net.api;

import com.example.l04net.model.Comment;
import com.example.l04net.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostsAPI {
    @GET("/posts")
    Call<List<Post>> getAllPosts();

    @GET("/posts/{id}/comments")
    Call<List<Comment>> getCommentsById(@Path("id") int postId);

    @GET("/posts")
    Call<List<Post>> getPostsByUserId(@Query("userId") int userId);

    @GET("/posts")
    Call<List<Post>> getUserPosts(
            @Query("userId") int userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );


    @POST("/posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("/posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );


    @PUT("/posts/{id}")
    Call<Post> putPost(@Path ("id") int id, @Body Post post);

    @PATCH("/posts/{id}")
    Call<Post> patchPost(@Path ("id") int id, @Body Post post);

    @DELETE("/posts/{id}")
    Call<Void> deletePost(@Path ("id") int id);
}
