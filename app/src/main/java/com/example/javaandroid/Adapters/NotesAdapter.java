package com.example.javaandroid.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.javaandroid.R;
import com.example.javaandroid.Structures.Note;

import java.util.ArrayList;
import java.util.List;

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


        TextView NotesAdapterRowID= convertView.findViewById(R.id.NotesAdapterRowID);
        TextView NoteEditTitle= convertView.findViewById(R.id.NotesAdapterRowTitle);
        TextView NotesAdapterRowDescription= convertView.findViewById(R.id.NotesAdapterRowDescription);
        TextView NotesAdapterEditButton= convertView.findViewById(R.id.NotesAdapterEditButton);

        NotesAdapterRowID.setText(String.valueOf(position));
        Note n  =_list.get(position);
        Log.d("xxx1", "getView: "+NoteEditTitle);
        NoteEditTitle.setText(n.getTitle());
        NoteEditTitle.setTextColor(Color.parseColor(n.getColor()));
        NotesAdapterRowDescription.setText(n.getDescription());



        NotesAdapterEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog =  new AlertDialog.Builder(_context);
                dialog.setTitle("Edycja notatek");
                String [] lista = new String[]{"edytuj","usuń","sortuj wg tytułu","sortuj wg koloru"};
                dialog.setItems(lista, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:  break;
                            case 1:  break;
                            case 2:  _list = _list.stream().sorted((a,b)->a.getColorCode()<b.getColorCode())
                            case 3:  break;

                        }
                    }
                });
            }
        });

        return convertView;
    }
}
