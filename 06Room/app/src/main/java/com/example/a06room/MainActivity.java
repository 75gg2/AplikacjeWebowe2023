package com.example.a06room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.a06room.database.NoteDatabase;
import com.example.a06room.database.UserDatabase;
import com.example.a06room.databinding.ActivityMainBinding;
import com.example.a06room.model.User;
import com.example.a06room.viewmodel.NotesViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NotesViewModel notesViewModel;
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        binding.setNotesViewModel(notesViewModel);

        adapter = new NotesAdapter(notesViewModel);

        binding.listView.setAdapter(adapter);

        notesViewModel.getObservedNotes().observe(this, l -> {

         adapter.notifyDataSetChanged();

        });


    }
}