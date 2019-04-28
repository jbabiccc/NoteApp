package com.example.notes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.data.NoteContract;
import com.example.notes.data.NoteDBHelper;

public class AddNote  extends AppCompatActivity {
    private EditText title;
    private EditText description;
    private Button submit;
    private NoteDBHelper mDbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);
        mDbHelper = new NoteDBHelper(this);
       this.title = findViewById(R.id.edit_title);
       this.description = findViewById(R.id.edit_description);
       this.submit = findViewById(R.id.submitNote);

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               insertNote();
               finish();
               startActivity(new Intent(AddNote.this, MainMenu.class));
           }
       });

    }
    private void insertNote(){

        String titleString =title.getText().toString().trim();
        String descriptionString = description.getText().toString().trim();


        NoteDBHelper mDbHelper = new NoteDBHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and note attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteEntry.COLUMN_TITLE_NAME, titleString);
        values.put(NoteContract.NoteEntry.COLUMN_DESCRIPTION, descriptionString);

        long newRowId = db.insert(NoteContract.NoteEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_note,menu);
        return true;
    }



}
