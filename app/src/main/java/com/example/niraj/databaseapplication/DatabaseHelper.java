package com.example.niraj.databaseapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "Pranav.db";
    public static final String TABLE_NAME= "pranav_table";
    public static final String Col_1= "id";
    public static final String Col_2= "name";
    public static final String Col_3= "surname";
    public static final String Col_4= "marks";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,surname TEXT,marks INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
    }
    public boolean insertData(String name,String surname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Col_2, name);
        contentValues.put(Col_3, surname);
        contentValues.put(Col_4, marks);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return false;
        }else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery(" Select * from "+ TABLE_NAME,null);
        return cursor;
    }

    public boolean updateData(String id,String name,String surname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col_1,id);
        contentValues.put(Col_2,name);
        contentValues.put(Col_3,surname);
        contentValues.put(Col_4,marks);
        db.update(TABLE_NAME,contentValues," id =?",new String[] {id});
        return true;
    }

    public boolean deleteData(String id,String name,String surname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col_1,id);
        contentValues.put(Col_2,name);
        contentValues.put(Col_3,surname);
        contentValues.put(Col_4,marks);
        db.delete(TABLE_NAME," id =?",new String[] {id} );
        return true;

    }
}
