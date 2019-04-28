package com.example.notes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.notes.data.NoteContract;
import com.example.notes.data.NoteContract.NoteEntry;
import com.example.notes.data.NoteDBHelper;


public class MainMenu extends AppCompatActivity {
    private NoteDBHelper mDBHelper;
    private MainMenuAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        FloatingActionButton fab = findViewById(R.id.button_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, AddNote.class));
            }
        });

        mDBHelper = new NoteDBHelper(this);
        mDBHelper.getWritableDatabase();

    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDBHelper.getReadableDatabase();


        String [] columns = {
                NoteEntry._ID,
                NoteEntry.COLUMN_TITLE_NAME,
                NoteEntry.COLUMN_DESCRIPTION

        };

        Cursor cursor = db.query(NoteEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);


        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainMenuAdapter(this,cursor);
        recyclerView.setAdapter(adapter);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_main.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Log Out" menu option
            case R.id.log_out:

                startActivity(new Intent(MainMenu.this, LogIn.class));
            case R.id.action_delete:
                deleteNotes();
                displayDatabaseInfo();
                return true;
            case R.id.details:
                GettingData p = new GettingData();
                p.execute();
                startActivity(new Intent(MainMenu.this, Networking.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void deleteNotes()
    {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.delete(NoteContract.NoteEntry.TABLE_NAME,null,null);
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }
}
