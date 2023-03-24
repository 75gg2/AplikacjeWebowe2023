package com.example.a06room.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.a06room.database.NoteDatabase;
import com.example.a06room.database.NoteRepository;
import com.example.a06room.model.Note;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Note>> mutableNotesList;

    private NoteRepository noteRepository;

    private NoteDatabase noteDatabase;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        noteDatabase = NoteDatabase.getInstance(application.getApplicationContext());

        noteRepository = new NoteRepository(noteDatabase.noteDAO());

        this.mutableNotesList = new MutableLiveData<>();
        this.mutableNotesList.setValue(noteRepository.getAll());

    }

//        zwracamy listę do mainactivity dla obserwacji zmian

    public MutableLiveData<List<Note>> getObservedNotes() {
        return mutableNotesList;
    }
    public void refresh(){
        this.mutableNotesList.setValue(noteRepository.getAll());
    }

    public void addNote(Note... note) {
        noteRepository.insert(note);
        refresh();
    }


    public void delete(Note... note){
        noteRepository.delete(note);
        refresh();
    }

    public void clear(){
        noteRepository.deleteAll();
        refresh();
    }

}