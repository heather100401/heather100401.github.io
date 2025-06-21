package com.example.weighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WeightDatabaseHelper extends SQLiteOpenHelper {

    public static final String dbName = "WeightDB";
    public static final String tableName = "userWeights";

    public WeightDatabaseHelper(@Nullable Context context) {
        super(context, "WeightDB" , null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase WeightDB){
        WeightDB.execSQL("CREATE TABLE userWeights (userID TEXT, date TEXT, weight TEXT, goalWeight TEXT, PRIMARY KEY(userID, date))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase WeightDB, int oldVersion, int newVersion){
        WeightDB.execSQL("DROP TABLE IF EXISTS userWeights");
        onCreate(WeightDB);
    }

    // Inserts data into table
    // Returns true if successful and false if unsuccessful
    public boolean insertData(String userID, String date, String weight, String goalWeight){
        SQLiteDatabase WeightDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID", userID);
        contentValues.put("date", date);
        contentValues.put("weight", weight);
        contentValues.put("goalWeight", goalWeight);

        long result = WeightDB.replace("userWeights", null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }
    // Deletes data from table
    // Returns true if successful and false if unsuccessful
    public boolean deleteData(String userID, String date){
        SQLiteDatabase WeightDB = this.getWritableDatabase();
        int result = WeightDB.delete("userWeights", "userID = ? AND date = ?", new String[]{userID, date});
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllWeights(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT date, weight FROM userWeights WHERE userID = ? AND weight IS NOT NULL ORDER BY date DESC", new String[]{userID});
    }

    public String getGoalWeight(String userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT goalWeight FROM userWeights WHERE userID = ? AND goalWeight IS NOT NULL AND date = ?", new String[]{userID, "goal"});
        if (cursor.moveToFirst()) {
            String goalWeight = cursor.getString(0);
            cursor.close();
            return goalWeight;
        }
        cursor.close();
        return null;
    }

    public boolean insertGoalWeight(String userID, String goalWeight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID", userID);
        contentValues.put("date", "goal");
        contentValues.put("goalWeight", goalWeight);

        long result = db.replace("userWeights", null, contentValues);
        return result != -1;
    }


}

