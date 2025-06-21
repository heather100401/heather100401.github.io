package com.example.weighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginDatabaseHelper extends SQLiteOpenHelper {

    public static final String dbName = "LoginDB";

    public LoginDatabaseHelper(@Nullable Context context) {
        super(context, "LoginDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase LoginDB) {
        LoginDB.execSQL("CREATE TABLE allusers(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase LoginDB, int oldVersion, int newVersion) {
        LoginDB.execSQL("DROP TABLE IF EXISTS allusers");
    }

    // Inserts data into table
    // Returns true if successful and false if unsuccessful
    public boolean insertData(String username, String password){
        SQLiteDatabase LoginDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = LoginDB.insert("allusers", null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    // Validates username
    public boolean checkUsername(String username){
        SQLiteDatabase LoginDB = this.getWritableDatabase();
        Cursor cursor = LoginDB.rawQuery("SELECT * FROM allusers WHERE username = ?", new String[]{username});

        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    // Validates username and password
    public boolean checkPassword(String username, String password){
        SQLiteDatabase LoginDB = this.getWritableDatabase();
        Cursor cursor = LoginDB.rawQuery("SELECT * FROM allusers WHERE username = ? AND password = ?", new String[]{username, password});

        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
