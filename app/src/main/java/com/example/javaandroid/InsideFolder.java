package com.example.javaandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        Bundle bundle = getIntent().getExtras();
        String folderName = bundle.getString("folderName");
        LinearLayout linearLayout = findViewById(R.id.PhotoRowListView);
        File folder = new File(AlbumsActivity.getMyFolder(), folderName);

        for (File file : folder.listFiles()){
            if(!file.isFile())
                continue;
            ImageView img = new ImageView(InsideFolder.this);
            linearLayout.addView(img);
            String imagepath = file.getPath();
            Bitmap bmp = betterImageDecode(imagepath);
            img.setImageBitmap(bmp);
        }


        TextView title = findViewById(R.id.InsideFolderText);
        title.setText(folderName);
        File[] flist = folder.listFiles();
        ArrayList<File> list = new ArrayList<File>(Arrays.asList(flist));
        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(InsideFolder.this,
                R.layout.photo_row,
                list);
        ListView lv = findViewById(R.id.InsideFolderListView);
        lv.setAdapter(customArrayAdapter);
    }

    private Bitmap betterImageDecode(String filePath) {
        Bitmap myBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        myBitmap = BitmapFactory.decodeFile(filePath, options);
        return myBitmap;
    }
}