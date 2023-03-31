package com.example.camera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.camera.databinding.ActivityMainBinding;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final int PERMISSIONS_REQUEST_CODE = 100;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};

    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private ImageCapture imageCapture;

    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {

        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(binding.previewView.getSurfaceProvider());


        imageCapture = new ImageCapture.Builder()
                .setTargetRotation(binding.previewView.getDisplay().getRotation())
                .build();


        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();


        cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture, preview);
    }

    private void cameraInit() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(MainActivity.this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (InterruptedException | ExecutionException e) {
                // No errors need to be handled for this Future. This should never be reached.
            }
        }, ContextCompat.getMainExecutor(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (!checkIfPermissionsGranted()) {
            requestPermissions(REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
        } else {
            cameraInit();
        }

        binding.takePhoto.setOnClickListener(view -> {
            File mImageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photos");
            boolean isDirectoryCreated = mImageDir.exists() || mImageDir.mkdirs();
            File file;
            if (isDirectoryCreated) {
               file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/photos/", "x.jpg");
            }
            else return;
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME,new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date()));
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
//            ImageCapture.OutputFileOptions outputFileOptions =
//                    new ImageCapture.OutputFileOptions.Builder(
//                            getContentResolver(),
//                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                            contentValues)
//                            .build();
            ImageCapture.OutputFileOptions outputFileOptions =
                    new ImageCapture.OutputFileOptions.Builder(file).build();
            imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this),
                    new ImageCapture.OnImageSavedCallback() {
                        @Override
                        public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                            // photo saved!!!
                        }

                        @Override
                        public void onError(@NonNull ImageCaptureException exception) {
                        }
                    });

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.PERMISSIONS_REQUEST_CODE) {
            boolean allGranted = true;
            for (int r : grantResults)
                if (r != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            if (!allGranted) {
                Toast.makeText(MainActivity.this, "Error: Permissions not granted", Toast.LENGTH_LONG).show();

            }

        }
    }

    private boolean checkIfPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}