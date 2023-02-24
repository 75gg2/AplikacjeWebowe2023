package com.example.javaandroid.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.javaandroid.Adapters.CustomArrayAdapter;
import com.example.javaandroid.R;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

public class NewAlbums extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_albums);

        Bundle bundle = getIntent().getExtras();
        String folderName = bundle.getString("folderName");
        LinearLayout linearLayout = findViewById(R.id.PhotoRowListView);
        File folder = new File(AlbumsActivity.getMyFolder(), folderName);
        File[] pliki = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile();
            }
        });
        ArrayList<File> lista = new ArrayList<>(Arrays.asList(pliki));
        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(NewAlbums.this,
                R.layout.photo_row,
                lista);
        ListView lv = findViewById(R.id.newAlbumsLV);
        lv.setAdapter(customArrayAdapter);

    }
}