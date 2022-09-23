package com.example.javaandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout camera;
    private LinearLayout albums;
    private LinearLayout collage;
    private LinearLayout network;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar b = getSupportActionBar();
        assert b != null;
        b.hide();

        camera = findViewById(R.id.bt1);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("xxx", "klik");
//                Log.e("xxx", "klik");
//                Log.i("xxx", "klik");

                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
//                intent.putExtra("data","dane");
                startActivity(intent);
            }
        });
        albums = findViewById(R.id.bt2);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
        collage = findViewById(R.id.bt3);
        collage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
        network = findViewById(R.id.bt4);
        network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
    }
}