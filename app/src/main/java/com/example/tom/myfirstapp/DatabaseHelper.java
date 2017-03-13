package com.example.tom.myfirstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tom on 1/30/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myTables.sql";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseExampleContract.ExampleEntry.TABLE_NAME;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseExampleContract.ExampleEntry.TABLE_NAME + " (" +
                    DatabaseExampleContract.ExampleEntry._ID + " INTEGER PRIMARY KEY," +
                    DatabaseExampleContract.ExampleEntry.COLUMN_NAME_TITLE + " TEXT," +
                    DatabaseExampleContract.ExampleEntry.COLUMN_NAME_SALARY + " INT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
