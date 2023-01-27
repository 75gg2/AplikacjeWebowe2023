package com.example.javaandroid.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.example.javaandroid.R;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private LinearLayout camera;
    private LinearLayout albums;
    private LinearLayout collage;
    private LinearLayout network;
    private LinearLayout newAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {Log.d("xxx", DebugDB.getAddressLog());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View someView = findViewById(R.id.activityMainForBg);

        // Find the root view
        View root = someView.getRootView();

        // Set the color
        root.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

        ActionBar b = getSupportActionBar();
        assert b != null;
        b.hide();

//                Log.d("xxx", "klik");
//                Log.e("xxx", "klik");
//                Log.i("xxx", "klik");
//                intent.putExtra("data","dane");


        camera = findViewById(R.id.bt1);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 200);
                }
            }
        });
        albums = findViewById(R.id.bt2);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlbumsActivity.class);
                intent.putExtra("next","oldAlbumsInside");
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
        network = findViewById(R.id.bt5);
        network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
        newAlbums = findViewById(R.id.bt6);
        newAlbums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlbumsActivity.class);
                intent.putExtra("next","newAlbumCustom");
                startActivity(intent);
            }
        });
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 100);
        checkPermission(Manifest.permission.CAMERA, 100);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap b = (Bitmap) extras.get("data");
                ImageView iv = new ImageView(MainActivity.this);
                iv.setImageBitmap(b);
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("W którym folderze zapisać zdjęcie?");
                String[] arr = AlbumsActivity.getMyFolders().toArray(new String[0]);
                alert.setItems(arr, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
                        String d = df.format(new Date());
                        String path = AlbumsActivity.getMyFolder().getPath() + "/"+  arr[which]+"/"+ d + ".jpg";
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
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.show();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //tak TODO actions here and below
                } else {
                    //nie
                }
                break;
            case 101:

                break;
        }
    }

    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
        } else {
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

}