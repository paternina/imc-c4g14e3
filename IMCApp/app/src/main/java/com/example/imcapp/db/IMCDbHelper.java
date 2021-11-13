package com.example.imcapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.imcapp.db.ImcContract.UserEntry;
import com.example.imcapp.db.ImcContract.IMCEntry;

public class IMCDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "imc.db";
    private static final int DATABASE_VERSION = 1;

    public IMCDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USERS_TABLE = "CREATE TABLE " + UserEntry.TABLE_NAME + "("
                + UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserEntry.COLUMN_NIT + "  TEXT NOT NULL UNIQUE, "
                + UserEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + UserEntry.COLUMN_LAST_NAME + " TEXT NOT NULL, "
                + UserEntry.COLUMN_GENDER + " INTEGER NOT NULL, "
                + UserEntry.COLUMN_AGE + " INTEGER NOT NULL, "
                + UserEntry.COLUMN_PHONE + " INTEGER NOT NULL)";

        String SQL_CREATE_IMC_TABLE = "CREATE TABLE " + IMCEntry.TABLE_NAME + "("
                + IMCEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + IMCEntry.COLUMN_DATE + " DATE NOT NULL, "
                + IMCEntry.COLUMN_HEIGHT + " DOUBLE NOT NULL, "
                + IMCEntry.COLUMN_WEIGHT + " DOUBLE NOT NULL, "
                + IMCEntry.COLUMN_RESULT + " DOUBLE NOT NULL, "
                + IMCEntry.COLUMN_USER_ID + " INTEGER, "
                + " FOREIGN KEY (" + IMCEntry.COLUMN_USER_ID + ") REFERENCES " + UserEntry.TABLE_NAME + "(" + UserEntry._ID + "))";

        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_IMC_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
