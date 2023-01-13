package com.example.javaandroid;

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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter {
    private ArrayList<File> _list;
    private Context _context;
    private int _resource;

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
                String[] colors = {"#DFFF00", "#FFBF00", "#FF7F50", "#DE3163", "#9FE2BF", "#40E0D0", "#6495ED", "#CCCCFF"};

                LinearLayout parentLayout = editView.findViewById(R.id.noteEditColors);
                for (String color : colors) {
                    Button b = new Button(_context); // nowy Button
                    b.setLayoutParams(new LinearLayout.LayoutParams(100, 100)); //jego wielkość
                    b.setBackgroundColor(Color.parseColor(color)); // tło
                    parentLayout.addView(b); // dodanie do elementu nadrzędnego
                }

                alert.setView(editView);
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        EditText et = (EditText) editView.findViewById(R.id.NoteEditTitle);
                        EditText etxt = (EditText) editView.findViewById(R.id.noteEditText);
                        DatabaseManager db = new DatabaseManager(
                                _context, // activity z galerią zdjęć
                                "NotatkiGargulaKamil.db", // nazwa bazy
                                null,
                                1 //wersja bazy, po zmianie schematu bazy należy ją zwiększyć
                        );
                        db.insert(et.getText().toString(), etxt.getText().toString());
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












