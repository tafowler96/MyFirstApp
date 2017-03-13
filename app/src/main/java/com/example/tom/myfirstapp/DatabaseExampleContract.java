package com.example.tom.myfirstapp;

import android.provider.BaseColumns;

/**
 * Created by Tom on 1/30/2017.
 */

public final class DatabaseExampleContract {
    private DatabaseExampleContract() {
        //Made private so that you can't instantiate it
    }

    public static class ExampleEntry implements BaseColumns {
        public static final String TABLE_NAME = "myTable";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SALARY = "salary";
    }
}
