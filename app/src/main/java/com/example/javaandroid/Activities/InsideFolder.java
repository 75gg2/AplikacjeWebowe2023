package com.example.javaandroid.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.javaandroid.R;

import java.io.File;

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
            String imagepath = file.getPath();
            Bitmap bmp = betterImageDecode(imagepath);
            img.setImageBitmap(bmp);
            img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,600));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            linearLayout.addView(img);
        }


        TextView title = findViewById(R.id.InsideFolderText);
        title.setText(folderName);
    }

    private Bitmap betterImageDecode(String filePath) {
        Bitmap myBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        myBitmap = BitmapFactory.decodeFile(filePath, options);
        return myBitmap;
    }
}