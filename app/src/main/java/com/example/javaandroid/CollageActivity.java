package com.example.javaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.javaandroid.Activities.ImageData;

import java.util.ArrayList;

public class CollageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage);

        ImageButton c1 = findViewById(R.id.collage2);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ImageData> list = new ArrayList<>();
                list.add(new ImageData(0,0,100,200));   //[][]
                list.add(new ImageData(100,0,100,200)); //[][]
                list.add(new ImageData(0,200,200,100)); //[  ]
                Intent intent = new Intent(CollageActivity.this, CollageMakerActivity.class);
                intent.putExtra("list", list);
                startActivity(intent);
            }
        });
        ImageButton c2 = findViewById(R.id.collage1);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ImageData> list = new ArrayList<>();
                list.add(new ImageData(0,0,100,300));
                list.add(new ImageData(100,0,100,100));
                list.add(new ImageData(100,100,100,100));
                list.add(new ImageData(100,200,100,100));
                Intent intent = new Intent(CollageActivity.this, CollageMakerActivity.class);
                intent.putExtra("list", list);
                startActivity(intent);
            }
        });
        ImageButton c3 = findViewById(R.id.collage3);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ImageData> list = new ArrayList<>();
                list.add(new ImageData(0,100,200,200));   //[][]
                list.add(new ImageData(0,0,80,100)); //[][]
                list.add(new ImageData(80,0,60,100)); //[][]
                list.add(new ImageData(140,0,60,100)); //[][]
                Intent intent = new Intent(CollageActivity.this, CollageMakerActivity.class);
                intent.putExtra("list", list);
                startActivity(intent);
            }
        });
    }
}