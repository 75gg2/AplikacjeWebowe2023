package com.example.a06room.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.a06room.model.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note... note);

    @Query("SELECT * FROM notes")
    List<Note> getAll();

    @Update()
    void update(Note ...note);

    @Query("DELETE FROM notes")
    void deleteAll();

    @Delete()
    void delete(Note ...note);
}
