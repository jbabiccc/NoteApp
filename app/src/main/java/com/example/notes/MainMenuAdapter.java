package com.example.notes;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.notes.data.*;

import java.util.ArrayList;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder>  {
    private Context context;
    private Cursor cursor;
    public MainMenuAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public MainMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_of_notes, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainMenuAdapter.ViewHolder viewHolder, int i) {
        if(!cursor.moveToPosition(i)){
            return;
        }
        String title = cursor.getString(cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_TITLE_NAME));
        String description = cursor.getString(cursor.getColumnIndex(NoteContract.NoteEntry.COLUMN_DESCRIPTION));
        viewHolder.titleView.setText(title);
        viewHolder.description.setText(description);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView description;
        public ViewHolder(View itemView) {
            super(itemView);
            titleView= itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);

        }
    }
}