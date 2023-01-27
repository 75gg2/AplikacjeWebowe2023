package com.example.javaandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.javaandroid.Adapters.NotesAdapter;
import com.example.javaandroid.R;
import com.example.javaandroid.Structures.DatabaseManager;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        DatabaseManager db = new DatabaseManager(NotesActivity.this);
        NotesAdapter notesAdapter = new NotesAdapter(
                NotesActivity.this,
                R.layout.notes_adapter_row,
                db.getAll()
                );
        ListView lv = findViewById(R.id.NotesListView);
        lv.setAdapter(notesAdapter);

    }
}