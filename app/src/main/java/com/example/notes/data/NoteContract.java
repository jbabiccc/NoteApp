package com.example.notes.data;

import android.provider.BaseColumns;

public final class NoteContract {

    public NoteContract() {}

    public static final class NoteEntry implements BaseColumns{

        public static String TABLE_NAME = "notes";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_TITLE_NAME ="title";

        public final static String COLUMN_DESCRIPTION = "description";

    }
}
