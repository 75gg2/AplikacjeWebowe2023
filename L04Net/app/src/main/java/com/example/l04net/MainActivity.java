package com.example.l04net;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.l04net.databinding.ActivityMainBinding;
import com.example.l04net.model.Post;
import com.example.l04net.model.PostsAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        binding.btn.setOnClickListener((l)->{
            PostsAPI postAPI = retrofit.create(PostsAPI.class);
            Call<List<Post>> call = postAPI.getAllPosts();
            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    binding.tv.setText(response.body().toString());
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {

                }
            });
        });
    }
}