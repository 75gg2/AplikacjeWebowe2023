package com.example.javaandroid.Structures;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.javaandroid.Activities.NotesActivity;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(@Nullable Context context) {
        super(context,
                "NotatkiGargulaKamil.db",
                null,
                3
        );
    }

    public DatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

//    public DatabaseManager(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
//        super(context, name, version, openParams);
//    }

    @SuppressLint("Range")
    public ArrayList<Note> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Note> notes = new ArrayList<>();
        Cursor result = db.rawQuery("SELECT * FROM notes", null);

        while (result.moveToNext()) {
            notes.add(
                    new Note(
                            result.getString(result.getColumnIndex("title")),
                            result.getString(result.getColumnIndex("description")),
                            result.getString(result.getColumnIndex("color"))
                    )
            );
        }
        return notes;

    }

    public boolean insert(String title, String description, int colorCode) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("color", colorCode);

        db.insertOrThrow("notes", null, contentValues); // gdy insert się nie powiedzie, będzie błąd
        db.close();
        return true;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE notes (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'title' TEXT, 'description' TEXT, 'color' INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(sqLiteDatabase);
    }
}
