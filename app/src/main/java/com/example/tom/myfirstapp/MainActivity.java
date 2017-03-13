package com.example.tom.myfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static java.security.AccessController.getContext;

public class MainActivity extends FragmentActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }
        MainActivityFragment fragment1 = new MainActivityFragment();
        fragment1.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container1, fragment1).commit();
        DisplayMessageFragment fragment2 = new DisplayMessageFragment();
        fragment2.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container2, fragment2).commit();


    }

    public void sendMessage(View view) {
        String filename = "display_message.txt";
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();

        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseExampleContract.ExampleEntry.COLUMN_NAME_TITLE, message);
        values.put(DatabaseExampleContract.ExampleEntry.COLUMN_NAME_SALARY, 5);
        long newRowId = db.insert(DatabaseExampleContract.ExampleEntry.TABLE_NAME, null, values);

        ((TextView)findViewById(R.id.display_saved_message)).setText(((TextView)findViewById(R.id.display_saved_message)).getText() +  "\n" + message);
    }

    public void clearMessages(View view) {
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.delete(DatabaseExampleContract.ExampleEntry.TABLE_NAME, null, null);
        ((TextView)findViewById(R.id.display_saved_message)).setText("");
    }

    public void gotoWebsite(View view) {
        Uri webpage = Uri.parse("http://www.tylervigen.com/spurious-correlations");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }

}