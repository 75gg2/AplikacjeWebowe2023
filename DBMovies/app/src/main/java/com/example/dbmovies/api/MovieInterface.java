package com.example.dbmovies.api;

import com.example.dbmovies.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {
    String apiKey = "7e42d2bbce7589ee1d13789e004ed6e4";

    @GET("popular")
    Call<Movies> getPopularMovies(@Query("api_key") String api_key);
}
