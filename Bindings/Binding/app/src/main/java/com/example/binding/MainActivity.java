package com.example.binding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.binding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        activityMainBinding.button.setOnClickListener(v->{
            activityMainBinding.tv.setText("VIEWBINDING WORKS!");
        });


        activityMainBinding.button.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, SecondScreeen.class);
            intent.putExtra("num",activityMainBinding.et.getText());
            startActivity(intent);
        });

    }
}