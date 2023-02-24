package com.example.javaandroid;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.javaandroid.Activities.AlbumsActivity;
import com.example.javaandroid.Activities.ImageData;
import com.example.javaandroid.Activities.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CollageMakerActivity extends AppCompatActivity {

    ArrayList<ImageView> imageViews  = new ArrayList<>();
    ImageView lastIv =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collage_maker);

        ArrayList<ImageData> list = (ArrayList<ImageData>) getIntent().getExtras().getSerializable("list");

        FrameLayout fl = findViewById(R.id.frameLayout);
        fl.setDrawingCacheEnabled(true);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float m = size.x/200;

        for (ImageData i : list) {
            ImageView iv = new ImageView(CollageMakerActivity.this);
            imageViews.add(iv);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, 100 + list.indexOf(i));
                }
            });
            iv.setX(i.getX()*m);
            iv.setY(i.getY()*m);
            iv.setLayoutParams(new FrameLayout.LayoutParams((int) (i.getW()*m), (int) (i.getH()*m)));
//            iv.setBackgroundColor(Color.parseColor("#0000ff"));
////            iv.setPadding(10,10,10,10);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

            Bitmap icon = BitmapFactory.decodeResource(CollageMakerActivity.this.getResources(),R.drawable.addphoto);
            iv.setImageBitmap(icon);
            fl.addView(iv);
        }

        ImageButton ib1 = findViewById(R.id.coB1);
        ImageButton ib2 = findViewById(R.id.coB2);
        ImageButton ib3 = findViewById(R.id.coB3);

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastIv != null){
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);

                    Bitmap oryginal = ((BitmapDrawable) lastIv.getDrawable()).getBitmap();
                    Bitmap rotated = Bitmap.createBitmap(oryginal, 0, 0, oryginal.getWidth(), oryginal.getHeight(), matrix, true);
                    lastIv.setImageBitmap(rotated);
                }
            }
        });
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TAGooo", "onClick: "+lastIv);
                if(lastIv != null){
                    Matrix matrix = new Matrix();
                    matrix.postScale(-1.0f, 1.0f);

                    Bitmap oryginal = ((BitmapDrawable) lastIv.getDrawable()).getBitmap();
                    Bitmap rotated = Bitmap.createBitmap(oryginal, 0, 0, oryginal.getWidth(), oryginal.getHeight(), matrix, true);
                    lastIv.setImageBitmap(rotated);
                }
            }

        });

        ib3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Bitmap b = fl.getDrawingCache(true);
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String d = df.format(new Date());
                if(!AlbumsActivity.getMyFolders().contains("collages")){
                    File f1 = new File(AlbumsActivity.getMyFolder(), "collages");
                    f1.mkdir();
                }
                String path = AlbumsActivity.getMyFolder().getPath() + "/"+  "collages" +"/"+ d + ".jpg";
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                try {
                    FileOutputStream fs = null;
                    fs = new FileOutputStream(path);
                    fs.write(byteArray);
                    fs.close();
                }catch  (Exception e){
                    Log.e("ErrorC",e.toString());
                    Toast.makeText(CollageMakerActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("TAGxxx", "onActivityResult: "+requestCode);
        if (requestCode == 100 || requestCode == 101 || requestCode == 102) {
            if (resultCode == RESULT_OK) {

                Log.d("TAGxxx", "onActivityResult: "+requestCode);
                assert data != null;
                Uri imgData = data.getData();
                InputStream stream = null;
                try {
                    stream = getContentResolver().openInputStream(imgData);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Bitmap b = BitmapFactory.decodeStream(stream);

                lastIv = imageViews.get(requestCode - 100);
                lastIv.setImageBitmap(b);
                lastIv.setBackgroundColor(Color.parseColor("#ff0f00"));
            }
        }
    }
}