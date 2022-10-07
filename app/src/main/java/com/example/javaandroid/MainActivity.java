package com.example.javaandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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
                Intent intent = new Intent(MainActivity.this, AlbumsActivity.class);
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
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 100);
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