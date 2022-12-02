package com.example.javaandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class InsideFolder extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_foleder);
        TextView title = findViewById(R.id.InsideFolderText);
        Bundle bundle = getIntent().getExtras();
        String folderName = bundle.getString("folderName");
        title.setText(folderName);
        File folder = new File(AlbumsActivity.getMyFolder(), folderName);
        File[] flist = folder.listFiles();
        ArrayList<File> list = new ArrayList<File>(Arrays.asList(flist));
        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(InsideFolder.this,
                R.layout.photo_row,
                list);
        ListView lv = findViewById(R.id.InsideFolderListView);
        lv.setAdapter(customArrayAdapter);
    }
}