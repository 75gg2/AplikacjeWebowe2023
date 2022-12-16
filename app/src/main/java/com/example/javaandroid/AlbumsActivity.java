package com.example.javaandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumsActivity extends AppCompatActivity {
    File mainFolder;
    File myFolder;
    private final static String folderName = "Gargula";
    Button addButton;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<String> getMyFolders() {
        return dirsNames(AlbumsActivity.getMyFolder());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static File getMyFolder() {
        File mainFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(mainFolder, folderName);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static List<String> dirsNames(File folder) {
        List<String> xd = Arrays.stream(folder.listFiles())
                .filter(File::isDirectory)
                .map(File::getName)
                .collect(Collectors.toList());

        return xd;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();
        String nextSlide = bundle.getString("next");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AlbumsActivity.this);
                alert.setTitle("Stwórz folder");
                alert.setMessage("Podaj nazwę");
                EditText input = new EditText(alert.getContext());
                alert.setView(input);
                alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        File f1 = new File(myFolder, String.valueOf(input.getText()));
                        f1.mkdir();
                    }

                });
                alert.show();

            }
        });
        mainFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        ListView lv = findViewById(R.id.albumsListView);
        List<String> arr = dirsNames(mainFolder);
        myFolder = new File(mainFolder, folderName);
        if (!arr.contains(folderName)) {
            myFolder.mkdir();
            for (String name : new String[]{"ludzie", "miejsca", "rzeczy"}) {
                File f1 = new File(myFolder, name);
                f1.mkdir();
            }
        }
        arr = dirsNames(myFolder);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AlbumsActivity.this,
                R.layout.album_row,
                R.id.album_row_text,
                arr
        );
        List<String> finalArr = arr;
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (nextSlide.equals("newAlbumCustom")) {
                    Intent intent = new Intent(AlbumsActivity.this, NewAlbums.class);
                    intent.putExtra("folderName", finalArr.get(position));
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(AlbumsActivity.this, InsideFolder.class);
                    intent.putExtra("folderName", finalArr.get(position));
                    startActivity(intent);
                }
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,
                                           int i, long l) {
                File f = new File(myFolder, finalArr.get(i));
                AlertDialog.Builder alert = new AlertDialog.Builder(AlbumsActivity.this);
                alert.setTitle("Uwaga!");
                alert.setMessage("Usunąć: " + f.getName() + "?");
                alert.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        for (File file : f.listFiles()) {
                            file.delete();
                        }
                        f.delete();

                    }

                });

//no
                alert.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //wyświetl which
                    }
                });
//
                alert.show();
                Log.d("TAG", "numer klikanego wiersza w ListView = " + i);
                return false;
            }
        });
    }


}