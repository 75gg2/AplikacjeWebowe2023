package com.example.javaandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.javaandroid.Adapters.NotesAdapter;
import com.example.javaandroid.R;
import com.example.javaandroid.Structures.DatabaseManager;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        DatabaseManager db = new DatabaseManager(
                NotesActivity.this, // activity z galerią zdjęć
                "NotatkiGargulaKamil.db", // nazwa bazy
                null,
                1 //wersja bazy, po zmianie schematu bazy należy ją zwiększyć
        );
        NotesAdapter notesAdapter = new NotesAdapter(
                NotesActivity.this,
                R.layout.notes_adapter_row,
                db.getAll()
                );
    }
}