package com.example.javaandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumsActivity extends AppCompatActivity {
    File pic;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<String> dirsNames() {
        List<String> xd = Arrays.stream(pic.listFiles())
                .filter(File::isDirectory)
                .map(File::getName)
                .collect(Collectors.toList());

        return xd;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        setContentView(R.layout.activity_albums);
        ListView lv = findViewById(R.id.albumsListView);
        List<String> arr = dirsNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AlbumsActivity.this,
                R.layout.album_row,
                R.id.album_row_text,
                arr
        );
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int i, long l) {
                Log.d("TAG", "numer klikanego wiersza w ListView = " + i);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int i, long l) {
                Log.d("TAG", "numer klikanego wiersza w ListView = " + i);
                return false;
            }
        });
    }
}