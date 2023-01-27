package com.example.javaandroid.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.javaandroid.Structures.DatabaseManager;
import com.example.javaandroid.Structures.Note;
import com.example.javaandroid.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter {
    private ArrayList<File> _list;
    private Context _context;
    private int _resource;
    private int code;


    public CustomArrayAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this._list = (ArrayList<File>) objects;
        this._context = context;
        this._resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        super.getView(position, convertView, parent);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(_resource, null);
        ImageView iv1 = convertView.findViewById(R.id.PhotoImageView);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("XXX", "klik w obrazek");
            }
        });
        Uri uri = Uri.fromFile(this._list.get(position));
        iv1.setImageURI(uri);

        Button buttonRemove = convertView.findViewById(R.id.PhotoRowDelete);
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(_context);
                alert.setTitle("Czy chcesz usunąć element?");
                alert.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        _list.get(position).delete();
                        _list.remove(position);
                        notifyDataSetChanged();
                    }
                });
                alert.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alert.show();

            }
        });


        Button buttonEdit = convertView.findViewById(R.id.PhotoRowEdit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alert = new AlertDialog.Builder(_context);
                View editView = View.inflate(_context, R.layout.note_inputs_xml, null);
                String[] colors = Note.colors;

                LinearLayout parentLayout = editView.findViewById(R.id.noteEditColors);

                final int[] colorCode = {0};

                EditText et = (EditText) editView.findViewById(R.id.NoteEditTitle);
                et.setTextColor(Color.parseColor(colors[0]));
                for (String color : colors) {
                    Button b = new Button(_context); // nowy Button
                    b.setLayoutParams(new LinearLayout.LayoutParams(100, 100)); //jego wielkość
                    b.setBackgroundColor(Color.parseColor(color)); // tło
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            colorCode[0] = Arrays.asList(colors).indexOf(color);
                            code = Arrays.asList(colors).indexOf(color);
                            et.setTextColor(Color.parseColor(color));
                        }
                    });
                    parentLayout.addView(b); // dodanie do elementu nadrzędnego
                }

                alert.setView(editView);
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                EditText etxt = (EditText) editView.findViewById(R.id.noteEditText);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DatabaseManager db = new DatabaseManager(_context);
                        db.insert(et.getText().toString(),
                                etxt.getText().toString(),
                                colorCode[0]
                        );
                        db.close();
                    }
                });
                alert.show();
            }
        });


        Button buttonShow = convertView.findViewById(R.id.PhotoRowShow);
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(_context);
                alert.setTitle("Info o zdjęciu:");
                alert.setMessage("Ścieżka zdjęcia: " + _list.get(position).getPath());
                alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();

            }
        });


        return convertView;
    }

    @Override
    public int getCount() {
        return _list.size();
    }


}












