package com.example.l04net;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.l04net.databinding.ActivityMainBinding;
import com.example.l04net.model.Comment;
import com.example.l04net.model.Post;
import com.example.l04net.api.PostsAPI;

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

        PostsAPI postAPI = retrofit.create(PostsAPI.class);


        binding.btn1.setOnClickListener(view -> unpackCall(postAPI.getAllPosts()));
        binding.btn2.setOnClickListener(view -> unpackCall(postAPI.getCommentsById(3)));
        binding.btn3.setOnClickListener(view -> unpackCall(postAPI.getPostsByUserId(4)));
        binding.btn4.setOnClickListener(view -> unpackCall(postAPI.getUserPosts(2, "id","asc")));
        binding.btn5.setOnClickListener(view -> unpackCall(postAPI.createPost(new Post(1,2,"T","tx"))));
        binding.btn6.setOnClickListener(view -> unpackCall(postAPI.createPost(2,"T","txt")));
        binding.btn7.setOnClickListener(view -> unpackCall(postAPI.putPost(32,new Post(32,2,"T","tx"))));
        binding.btn8.setOnClickListener(view -> unpackCall(postAPI.patchPost(1, new Post(32,2,"T","tx"))));
        binding.btn9.setOnClickListener(view -> unpackCall(postAPI.deletePost(2)));
    }

//    <T> void unpackCall (Call<List<T>> call){
//        call.enqueue(new Callback<List<T>>() {
//            @Override
//            public void onResponse(Call<List<T>> call, Response<List<T>> response) {
//                binding.tv.setText(response.body() != null ? response.body().toString() : "Tu nic nie ma :/");
//            }
//
//            @Override
//            public void onFailure(Call<List<T>> call, Throwable t) {
//                binding.tv.setText("Wystąpił bład \n" + t.toString());
//            }
//        });
//    };

    <T> void unpackCall (Call<T> call){
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                binding.tv.setText(response.body() != null ? response.body().toString() : "Tu nic nie ma :/");
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                binding.tv.setText("Wystąpił bład \n" + t.toString());
            }
        });
    };
}