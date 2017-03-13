package com.example.tom.myfirstapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 1/25/2017.
 */

public class DisplayMessageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_display_message, container, false);
        String message = "Didn't find anything from memory";
        DatabaseHelper mDbHelper = new DatabaseHelper(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selection = DatabaseExampleContract.ExampleEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "Saved"};
        String sortOrder = DatabaseExampleContract.ExampleEntry.COLUMN_NAME_TITLE + " DESC";

        Cursor cursor = db.rawQuery("SELECT * from " + DatabaseExampleContract.ExampleEntry.TABLE_NAME, null);

        StringBuffer fileContent = new StringBuffer("");
        while (cursor.moveToNext()) {
            String title = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseExampleContract.ExampleEntry.COLUMN_NAME_TITLE)
            );
            fileContent.append(title + "\n");
        }
        cursor.close();
        message = fileContent.toString();
        ((TextView) view.findViewById(R.id.display_saved_message)).setText(message);
        return view;
    }


}
