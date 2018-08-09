package com.gracefield.finalapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Abdulgafar Obeitor on 8/2/2018.
 */
public class DatabaseManager extends SQLiteOpenHelper{

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id integer primary key autoincrement, fname varchar[40], sname varchar[40], gender varchar[7]" +
                ", department varchar[40], state_of_origin varchar[20]");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public DatabaseManager(Context context){
        super(context,"database.db",null,1);
    }


    public ArrayList<String> getAllNames(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM users WHERE fname like ",null);
        rs.moveToFirst();
        ArrayList<String> names = new ArrayList<>();
        while(!rs.isAfterLast()) {
            String usersName = rs.getString(rs.getColumnIndex("fname")) + " " + rs.getString(rs.getColumnIndex("sname"));
            names.add(usersName);
            rs.moveToNext();
        }
        return names;
    }
    public void insertUser(String fname, String sname, String gender, String deparment, String state){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fname",fname);
        cv.put("sname",sname);
        cv.put("gender",gender);
        cv.put("department",deparment);
        cv.put("state_of_origin",state);
        db.insert("user",null,cv);
    }

}
