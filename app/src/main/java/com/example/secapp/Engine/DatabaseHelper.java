package com.example.secapp.Engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "my_passwords.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NOTE_CREATE =
            "CREATE TABLE " + TablesInfo.PasswordTableEntry.TABLE_NAME + " (" +
                    TablesInfo.PasswordTableEntry.Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TablesInfo.PasswordTableEntry.Name + " TEXT, " +
                    TablesInfo.PasswordTableEntry.Password + " TEXT, " +
                    TablesInfo.PasswordTableEntry.CreateDate + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                    ")";

    private static final String TABLE_PIN_CREATE =
            "CREATE TABLE " + TablesInfo.PinTableEntry.TABLE_NAME + " (" +
                    TablesInfo.PinTableEntry.Pin + " TEXT" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_NOTE_CREATE);
        db.execSQL(TABLE_PIN_CREATE);
        ContentValues cv = new ContentValues();
        cv.put(TablesInfo.PinTableEntry.Pin, "000000");

        long result = db.insert(TablesInfo.PinTableEntry.TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TablesInfo.PasswordTableEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TablesInfo.PinTableEntry.TABLE_NAME);

        onCreate(db);
    }

    public long changePin(String pin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TablesInfo.PinTableEntry.Pin, pin);

        long result = db.update(TablesInfo.PinTableEntry.TABLE_NAME,cv, null, null);

        db.close();
        return result;
    }
    public long addPassword(String appName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TablesInfo.PasswordTableEntry.Name, appName.trim());
        cv.put(TablesInfo.PasswordTableEntry.Password, password.trim());

        long result = db.insert(TablesInfo.PasswordTableEntry.TABLE_NAME, null, cv);

        db.close();
        return  result;
    }

    public void deletePassword(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TablesInfo.PasswordTableEntry.TABLE_NAME, TablesInfo.PasswordTableEntry.Id + "=?", new String[]{id});
        db.close();
    }
    public void updatePassword(String id, String appName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TablesInfo.PasswordTableEntry.Name, appName); //These Fields should be your String values of actual column names
        cv.put(TablesInfo.PasswordTableEntry.Password, password);
        db.update(TablesInfo.PasswordTableEntry.TABLE_NAME, cv, TablesInfo.PasswordTableEntry.Id + "=?", new String[]{id});
        db.close();
    }

    public ArrayList<PasswordEntry> getPaswordList() {
        ArrayList<PasswordEntry> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                TablesInfo.PasswordTableEntry.Id,
                TablesInfo.PasswordTableEntry.Name,
                TablesInfo.PasswordTableEntry.Password};

        Cursor c = db.query(TablesInfo.PasswordTableEntry.TABLE_NAME, projection, null, null, null, null, null);
        while (c.moveToNext()) {
            data.add(new PasswordEntry(c.getString(c.getColumnIndex(TablesInfo.PasswordTableEntry.Name)), c.getString(c.getColumnIndex(TablesInfo.PasswordTableEntry.Password)), c.getString(c.getColumnIndex(TablesInfo.PasswordTableEntry.Id))));
        }

        c.close();
        db.close();

        return data;
    }

    public String getPin() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = { TablesInfo.PinTableEntry.Pin};

        Cursor cursor = db.query(TablesInfo.PinTableEntry.TABLE_NAME,
                projection, null, null, null, null, null);
        String pin;

        if( cursor != null && cursor.moveToFirst() ){
            pin = cursor.getString(cursor.getColumnIndex("pin"));
            cursor.close();
            return pin;
        } else {
            return null;
        }
    }

}
