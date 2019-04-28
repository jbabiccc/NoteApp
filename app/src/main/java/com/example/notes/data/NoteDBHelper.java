package com.example.notes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.notes.data.NoteContract.NoteEntry;

public class NoteDBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "shelter.db";

    private static final int DATABASE_VERSION = 1;

    public NoteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_NOTE_TABLE =  "CREATE TABLE " + NoteEntry.TABLE_NAME + "("
                + NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NoteEntry.COLUMN_TITLE_NAME + " TEXT NOT NULL, "
                + NoteEntry.COLUMN_DESCRIPTION + " TEXT " + ");";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_NOTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
