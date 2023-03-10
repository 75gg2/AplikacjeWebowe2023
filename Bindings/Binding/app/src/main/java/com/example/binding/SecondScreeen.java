package com.example.binding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.binding.databinding.ActivitySecondScreeenBinding;

public class SecondScreeen extends AppCompatActivity {

    private ActivitySecondScreeenBinding activitySecondScreeenBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySecondScreeenBinding = ActivitySecondScreeenBinding.inflate(getLayoutInflater());
        setContentView(activitySecondScreeenBinding.getRoot());

        int n = Integer.parseInt(getIntent().getStringExtra("num"));
        for (int i = 0; i <n ; i++) {
//            activitySecondScreeenBinding.
        }
    }
}