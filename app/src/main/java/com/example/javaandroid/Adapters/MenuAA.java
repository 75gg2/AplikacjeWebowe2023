package com.example.javaandroid.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.bikomobile.multipart.Multipart;
import com.example.javaandroid.Activities.InsideFolder;
import com.example.javaandroid.R;
import com.example.javaandroid.Structures.Networking;
import com.example.javaandroid.Structures.Note;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MenuAA extends ArrayAdapter {
    private String ipStr = "192.168.1.17";
    private ArrayList<String> _list;
    private Context _context;
    private int _resource;
    private int editIndex = -1;
    private String path;

    public MenuAA(@NonNull Context context, int resource, String path) {
        super(context, resource);
        ArrayList<String> myList = new ArrayList<>(Arrays.asList("Ip", "Upload", "Share", "Crop"));
        _list = myList;
        _context = context;
        _resource = resource;
        this.path = path;
    }

    @Override
    public int getCount() {
        return _list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(_resource, null);
        TextView menuText = convertView.findViewById(R.id.MenuRowId);
        ImageView MenuImage = convertView.findViewById(R.id.MenuImage);
        menuText.setText(_list.get(position));
        switch (position) {
            case 0:
                convertView.findViewById(R.id.mlIP).setVisibility(View.VISIBLE);
                EditText ip = convertView.findViewById(R.id.mlIPIn);
                Button bip = convertView.findViewById(R.id.mlIPSv);
                ip.setText(ipStr);
                menuText.setVisibility(View.GONE);
                MenuImage.setVisibility(View.GONE);

                bip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ipStr = String.valueOf(ip.getText());
                    }
                });
            case 1:
                menuText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!Networking.check(_context)) {
                            AlertDialog.Builder alert = new AlertDialog.Builder(_context);
                            alert.setTitle("Internet error");
                            alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   return;
                                }
                            });
                            alert.show();
                        }else{
                            Bitmap b = InsideFolder.betterImageDecode(path);
                            Multipart multipart = new Multipart(_context);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            b.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            multipart.addFile("image/jpeg", "file", "plik.jpg", byteArray);

                            Log.d("xxxD", "http://"+ipStr+":3005/upload");
                            multipart.launchRequest("http://"+ipStr+":3005/upload",
                                    response -> {
                                        Log.d("xxx", "success");
                                    },
                                    error -> {
                                        Log.d("xxx", "error");
                                        Log.d("xxx", "http://"+ipStr+":3005/upload");
                                    });
                        }
                    }
                });
        }

        return convertView;
    }
}
