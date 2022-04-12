package com.example.warona;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email,String username, String password){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email",email);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDb.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery(
                "Select * from users where email = ?",
                new String[]{email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery(
                "Select * from users where email = ? and password = ?",
                new String[] {email,password});

        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
}
