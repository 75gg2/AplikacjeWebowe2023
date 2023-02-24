package com.example.javaandroid.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.javaandroid.R;
import com.example.javaandroid.Structures.DatabaseManager;
import com.example.javaandroid.Structures.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NotesAdapter extends ArrayAdapter {
    private ArrayList<Note> _list;
    private Context _context;
    private int _resource;
    private int editIndex = -1;

    public NotesAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        _context = context;
        _resource = resource;
        _list = (ArrayList<Note>) objects;
        Log.d("xxx", String.valueOf(_list.size()));
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


        TextView NotesAdapterRowID = convertView.findViewById(R.id.NotesAdapterRowID);
        TextView NoteEditTitle = convertView.findViewById(R.id.NotesAdapterRowTitle);
        TextView NotesAdapterRowDescription = convertView.findViewById(R.id.NotesAdapterRowDescription);
        ImageView NotesAdapterEditButton = convertView.findViewById(R.id.NotesAdapterEditButton);

        NotesAdapterRowID.setText(String.valueOf(position));
        Note n = _list.get(position);
        Log.d("xxx1", "getView: " + NoteEditTitle);
        NoteEditTitle.setText(n.getTitle());
        NoteEditTitle.setTextColor(Color.parseColor(n.getColor()));
        NotesAdapterRowDescription.setText(n.getDescription());


        NotesAdapterEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("xxx", "onClick: edit");
                AlertDialog.Builder dialog = new AlertDialog.Builder(_context);
                dialog.setTitle("Edycja notatek");
                String[] lista = new String[]{"edytuj", "usuń", "sortuj wg tytułu", "sortuj wg koloru"};
                dialog.setItems(lista, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                AlertDialog.Builder alert = new AlertDialog.Builder(_context);
                                View editView = View.inflate(_context, R.layout.note_inputs_xml, null);
                                String[] colors = Note.colors;

                                LinearLayout parentLayout = editView.findViewById(R.id.noteEditColors);

                                final int[] colorCode = {n.getColorCodeInt()};

                                EditText et = (EditText) editView.findViewById(R.id.NoteEditTitle);
                                et.setTextColor(Color.parseColor(colors[colorCode[0]]));
                                et.setText(n.getTitle());
                                for (String color : colors) {
                                    Button b = new Button(_context); // nowy Button
                                    b.setLayoutParams(new LinearLayout.LayoutParams(100, 100)); //jego wielkość
                                    b.setBackgroundColor(Color.parseColor(color)); // tło
                                    b.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            colorCode[0] = Arrays.asList(colors).indexOf(color);
//                                            int code = Arrays.asList(colors).indexOf(color);
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
                                etxt.setText(n.getDescription());

                                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        DatabaseManager db = new DatabaseManager(_context);
                                        db.update(
                                                n.getId(),
                                                et.getText().toString(),
                                                etxt.getText().toString(),
                                                colorCode[0]
                                        );
                                        db.close();

                                        n.setTitle(et.getText().toString());
                                        n.setDescription(etxt.getText().toString());
                                        n.setColorCode(String.valueOf(colorCode[0]));
                                        notifyDataSetChanged();
                                    }
                                });
                                alert.show();
                                break;
                            case 1:
                                DatabaseManager db = new DatabaseManager(_context);
                                db.delete(n.getId());
                                db.close();
                                _list.remove(n);
                                break;
                            case 2:
                                _list = new ArrayList(_list.stream()
                                        .sorted((a, b) -> a.getTitle().compareTo(b.getTitle()))
                                        .collect(Collectors.toList()));
                                break;
                            case 3:
                                _list = new ArrayList(_list.stream()
                                        .sorted((a, b) -> a.getColorCodeInt() - b.getColorCodeInt())
                                        .collect(Collectors.toList()));
                                break;

                        }

                        notifyDataSetChanged();
                    }
                });
                dialog.show();
            }
        });

        return convertView;
    }
}
