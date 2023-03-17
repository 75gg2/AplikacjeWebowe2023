package com.example.dbmovies.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dbmovies.model.Movies;
import com.example.dbmovies.service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<Movies> mutableMoviesList;

    public MovieViewModel() {
        this.mutableMoviesList = new MutableLiveData<>();
    }

    public void getPopularMovies() {

        Call<Movies> call = RetrofitService
                .getMovieInterface()
                .getPopularMovies("api_key");

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                mutableMoviesList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.d("xxx", "onFailure:" + t);
            }
        });

    }


    // observed movies

    public MutableLiveData<Movies> getObservedMovies() {
        return mutableMoviesList;
    }
}