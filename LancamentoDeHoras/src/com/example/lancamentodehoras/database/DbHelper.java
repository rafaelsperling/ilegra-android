package com.example.lancamentodehoras.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "horas.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_SIMULATION_TABLE = "CREATE TABLE IF NOT EXISTS HORAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATA_LANCAMENTO TEXT,DATA_FINAL_LANCAMENTO TEXT, DESCRICAO TEXT)";
 
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_SIMULATION_TABLE);
    }
     
   
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelper.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS HORAS");
        onCreate(db);
    }
}
