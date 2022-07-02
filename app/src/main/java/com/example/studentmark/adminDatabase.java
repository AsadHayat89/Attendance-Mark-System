package com.example.studentmark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class adminDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "admin.db";
    public static final String CONTACTS_TABLE_NAME = "Registration";

    public adminDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table Admin " +
                        "(Id integer primary key, Name text,contact integer,Email text, Password text,ConPassword text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean AddAdmin(String name, int phone, String email, String password, String conPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("contact", phone);
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        contentValues.put("ConPassword", conPassword);
        db.insert("Admin", null, contentValues);
        return true;
    }

    public String searchAdmin(String mail, String pass) {
        String query = "SELECT * FROM Admin WHERE Email == '" + mail + "' AND Password == '" + pass + "'";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null, null);
        String TAG = "value";
        if (cursor.moveToFirst()) {
            //Log.d(TAG,"Name is: "+cursor.getString(4));
            return "yes";
        }
        if (cursor.getCount() == 0) {
            return "false";
        }
        return "null";

    }

    public String getID(String mail, String pass) {
        String query = "SELECT * FROM Admin WHERE Email == '" + mail + "' AND Password == '" + pass + "'";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null, null);
        String TAG = "value";
        if (cursor.moveToFirst()) {
            //Log.d(TAG,"Name is: "+cursor.getString(4));

            return cursor.getString(0);
        }
        if (cursor.getCount() == 0) {
            return "false";
        }
        return "null";

    }

    public String getName(String mail, String pass) {
        String query = "SELECT * FROM Admin WHERE Email == '" + mail + "' AND Password == '" + pass + "'";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null, null);
        String TAG = "value";
        if (cursor.moveToFirst()) {
            //Log.d(TAG,"Name is: "+cursor.getString(4));

            return cursor.getString(1);
        }

        return "null";

    }

    public admin getAdmin(String ID) {
        String query = "SELECT * FROM Admin WHERE Id == '" + ID + "'";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null, null);
        String TAG = "value";
        if (cursor.moveToFirst()) {
            //Log.d(TAG,"Name is: "+cursor.getString(4));
            admin re = new admin();
            re.setMail(cursor.getString(3));
            re.setName(cursor.getString(1));

            re.setContact(Integer.parseInt(cursor.getString(2)));
            re.setPassword(cursor.getString(4));
            re.setConPassword(cursor.getString(5));
            //return cursor.getString(1);
            return re;
        }
        return null;
    }
}
