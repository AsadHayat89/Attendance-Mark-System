package com.example.studentmark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RegisterDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "School.db";
    public static final String CONTACTS_TABLE_NAME = "Registration";
    public static final String CONTACTS_COLUMN_ID = "Id";
    public static final String CONTACTS_COLUMN_NAME = "Name";
    public static final String CONTACTS_COLUMN_EMAIL = "Email";
    public static final String CONTACTS_Contact = "contact";
    public static final String CONTACTS_password = "Password";
    public static final String CONTACTS_ConPassword = "Conpassword";

    public RegisterDatabase(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table Registration " +
                        "(Id integer primary key, Name text,contact integer,Email text, Password text,ConPassword text)"
        );
        db.execSQL(
                "create table Attendance " +
                        "(Id integer primary key, StudentID String,day int,month int,year int,status int)"
        );
        db.execSQL(
                "create table application " +
                        "(Id integer primary key, StudentID String,body String,subject String,status int)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean AddStudent (String name, int phone, String email, String password,String conPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("contact", phone);
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        contentValues.put("ConPassword", conPassword);
        db.insert("Registration", null, contentValues);
        return true;
    }

    public String searchStudent(String mail,String pass){
        String query = "SELECT * FROM Registration WHERE Email == '"+mail+"' AND Password == '"+pass+"'";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null,null);
        String TAG="value";
        if (cursor.moveToFirst()) {
            //Log.d(TAG,"Name is: "+cursor.getString(4));
            return "yes";
        }
        if(cursor.getCount()==0){
            return "false";
        }
        return "null";

    }
    public String getID(String mail,String pass){
        String query = "SELECT * FROM Registration WHERE Email == '"+mail+"' AND Password == '"+pass+"'";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null,null);
        String TAG="value";
        if (cursor.moveToFirst()) {
            //Log.d(TAG,"Name is: "+cursor.getString(4));

            return cursor.getString(0);
        }
        if(cursor.getCount()==0){
            return "false";
        }
        return "null";

    }
    public String getName(String mail,String pass){
        String query = "SELECT * FROM Registration WHERE Email == '"+mail+"' AND Password == '"+pass+"'";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null,null);
        String TAG="value";
        if (cursor.moveToFirst()) {
            //Log.d(TAG,"Name is: "+cursor.getString(4));

            return cursor.getString(1);
        }

        return "null";

    }

    public Register getStudent(String ID){
        String query = "SELECT * FROM Registration WHERE Id == '"+ID+"'";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null,null);
        String TAG="value";
        if (cursor.moveToFirst()) {
            //Log.d(TAG,"Name is: "+cursor.getString(4));
            Register re=new Register();
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
    public ArrayList<String> getIDs(){
        String query = "SELECT * FROM Registration";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null,null);
        String TAG="value";
        int i=0;
        ArrayList<String> RA=new ArrayList<>();
        if (cursor.moveToFirst()) {
            //Log.d(TAG,"Name is: "+cursor.getString(4));

            //return cursor.getString(1);
            RA.add(cursor.getString(0));
            i++;
        }
        if(i==0){
            return null;
        }
        else{
            return RA;
        }

    }
    public ArrayList<attendance> getAllStudentAttendance(String ID){
        String query = "SELECT * FROM Attendance WHERE StudentID == '"+ID+"'";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null,null);
        String TAG="value";
        int i=0;
        ArrayList<attendance> RA=new ArrayList<>();
        if (cursor.moveToFirst()) {
            Log.d(TAG,"Name is: "+cursor.getString(1));
            attendance re=new attendance();

            re.setStudentId(cursor.getString(1));
            re.setDay(Integer.parseInt(cursor.getString(2)));
            re.setMonth(Integer.parseInt(cursor.getString(3)));
            re.setYear(Integer.parseInt(cursor.getString(4)));
            re.setStatus(Integer.parseInt(cursor.getString(5)));

            //return cursor.getString(1);
            RA.add(re);
            i++;
        }
        if(i==0){
            return null;
        }
        else{
            return RA;
        }

    }

    public ArrayList<attendance> getfullAttendence(){
        String query = "SELECT * FROM Attendance";


        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null,null);
        String TAG="value";
        int i=0;
        ArrayList<attendance> RA=new ArrayList<>();
        if (cursor.moveToFirst()) {
            Log.d(TAG,"Name is: "+cursor.getString(1));
            attendance re=new attendance();

            re.setStudentId(cursor.getString(1));
            re.setDay(Integer.parseInt(cursor.getString(2)));
            re.setMonth(Integer.parseInt(cursor.getString(3)));
            re.setYear(Integer.parseInt(cursor.getString(4)));
            re.setStatus(Integer.parseInt(cursor.getString(5)));

            //return cursor.getString(1);
            RA.add(re);
            i++;
        }
        if(i==0){
            return null;
        }
        else{
            return RA;
        }

    }
    public boolean editStudent(String sTID,String name, int phone, String email, String password,String conPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("contact", phone);
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        contentValues.put("ConPassword", conPassword);
        int var=db.update("Registration",contentValues,"ID"+"= ?",new String[]{sTID});
        if(var>0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean addApllication(String SID,String head,String body, int sta){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("StudentID", SID);
        contentValues.put("subject", head);
        contentValues.put("body", body);
        contentValues.put("status", sta);

        int var= (int) db.insert("application", null, contentValues);
        if(var>0){
            return true;
        }else{
            return false;
        }
    }
    public boolean AddAttendenca (String SID,int d,int m,int y, int sta) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("StudentID", SID);
        contentValues.put("day", d);
        contentValues.put("month", m);
        contentValues.put("year", y);
        contentValues.put("status", sta);

        db.insert("Attendance", null, contentValues);
        return true;
    }

    public String checkdate(int da,int mo,int ye,String STID){
        String query = "SELECT * FROM Attendance WHERE day=='"+da+"' AND month=='"+mo+"' AND year=='"+ye+"' AND StudentID=='"+STID+"' ";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query,null,null);
        String TAG="value";
        if (cursor.moveToFirst()) {
            Log.d(TAG,"Name is: "+cursor.getString(2));

            return "true";
        }
        if(cursor.getCount()==0){
            return "false";
        }
        return "null";

    }
}
