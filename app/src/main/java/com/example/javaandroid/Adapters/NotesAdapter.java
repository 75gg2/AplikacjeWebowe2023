package com.example.javaandroid.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.javaandroid.R;
import com.example.javaandroid.Structures.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends ArrayAdapter {
    private ArrayList<Note> _list;
    private Context _context;
    private int _resource;

    public NotesAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        _context = context;
        _resource = resource;
        _list = (ArrayList<Note>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(_resource, null);

        TextView NotesAdapterRowID= convertView.findViewById(R.id.NotesAdapterRowID);
        TextView NoteEditTitle= convertView.findViewById(R.id.NoteEditTitle);
        TextView NotesAdapterRowDescription= convertView.findViewById(R.id.NotesAdapterRowDescription);

        NotesAdapterRowID.setText(position);
        NoteEditTitle.setText(_list.get(position).getTitle());
        NoteEditTitle.setTextColor(Color.parseColor(_list.get(position).getColorCode()));
        NotesAdapterRowDescription.setText(_list.get(position).getDescription());

        return convertView;
    }
}
