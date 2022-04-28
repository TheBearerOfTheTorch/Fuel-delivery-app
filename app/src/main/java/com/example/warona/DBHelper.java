package com.example.warona;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "fuelDatabase";

    public DBHelper(Context context) {
        super(context, DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email VARCHAR," +
                "userType TEXT," +
                "username TEXT," +
                "password VARCHAR)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists users");
    }

    public Boolean registerUser(String email,String username,String userType, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email",email);
        contentValues.put("userType",userType);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDb.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean checkUser(String email) {
        SQLiteDatabase myDb = this.getWritableDatabase();

        Cursor cursor = myDb.rawQuery(
                "Select * from users where email = ?",
                new String[]{email});

        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else{
            return false;
        }
    }

    public String getUserType(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.rawQuery("SELECT * FROM users WHERE email=?", new String[]{email});

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int index = cursor.getColumnIndex("username");
                String userType;
                userType = cursor.getString(index);
                return userType;
            }
        }
        return "user";
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery(
                "Select * from users where email = ? and password = ?",
                new String[] {email,password});

        if(cursor.getCount()>0){
            cursor.close();
            return true;
        }
        else{
            return false;
        }
    }
}
