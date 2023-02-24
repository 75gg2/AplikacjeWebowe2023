package com.example.javaandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.javaandroid.Adapters.MenuAA;
import com.example.javaandroid.Adapters.NotesAdapter;
import com.example.javaandroid.R;

public class Photo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        String imagePath = getIntent().getStringExtra("img");
        Bitmap bmp = InsideFolder.betterImageDecode(imagePath);
        ImageView iv = findViewById(R.id.OnePhoto);
        iv.setImageBitmap(bmp);


        MenuAA menuAA = new MenuAA(
                Photo.this,
                R.layout.menu_row
                ,imagePath
        );
        ListView lv = findViewById(R.id.menuMenu);
        lv.setAdapter(menuAA);
    }
}