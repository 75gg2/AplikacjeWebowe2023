package com.example.a06room.database;


import com.example.a06room.model.Note;

import java.util.List;

public class NoteRepository implements NoteDAO{

    private NoteDAO dao;

    public NoteRepository(NoteDAO dao) {

        this.dao = dao;
    }

    @Override
    public void insert(Note... note) {
        dao.insert(note);
    }

    @Override
    public List<Note> getAll() {
        return dao.getAll();
    }

    @Override
    public void update(Note... note) {
        dao.update(note);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public void delete(Note... note) {
        dao.delete(note);
    }
}
