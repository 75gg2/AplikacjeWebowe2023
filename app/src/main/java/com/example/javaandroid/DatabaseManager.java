package com.example.javaandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {
    public DatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

//    public DatabaseManager(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
//        super(context, name, version, openParams);
//    }

    public boolean insert(String a, String b) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("a", a);
        contentValues.put("b", b);

        db.insertOrThrow("tabela1", null, contentValues); // gdy insert się nie powiedzie, będzie błąd
        db.insertOrThrow("tabela1", null, contentValues); // gdy insert się nie powiedzie, będzie błąd
        db.close();
        return true;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE tabela1 (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'a' TEXT, 'b' TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tabela1");
        onCreate(sqLiteDatabase);
    }
}
