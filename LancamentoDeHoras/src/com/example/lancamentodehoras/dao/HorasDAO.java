package com.example.lancamentodehoras.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lancamentodehoras.database.DbHelper;
import com.example.lancamentodehoras.domain.Horas;

public class HorasDAO {

	private SQLiteDatabase database;
	private DbHelper dbHelper;

	public HorasDAO(Context context) {
		dbHelper = new DbHelper(context);
	}

	public void open() throws SQLException {
		if (database != null && database.isOpen())
			return;
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void saveHoras(Horas hora) {
		open();

		ContentValues cv = new ContentValues();
		cv.put("DATA_LANCAMENTO", hora.getData());
		cv.put("DATA_FINAL_LANCAMENTO", hora.getDataFinal());
		cv.put("DESCRICAO", hora.getDesc());

		database.insert("HORAS", null, cv);
		dbHelper.close();
	}
	
	public List<Horas> retrieveHoras(){
		List<Horas> horas = new ArrayList<Horas>();
		open();
		
		Cursor cursor = database.query("HORAS", new String[]{"ID", "DATA_LANCAMENTO", "DATA_FINAL_LANCAMENTO","DESCRICAO"}, null, null, null, null, null);
	    cursor.moveToFirst();
	    
	    while(!cursor.isAfterLast()){
	    	String id = cursor.getString(0);
	    	String data = cursor.getString(1);
	    	String dataFinal = cursor.getString(2);
	    	String desc = cursor.getString(3);
	    	
	    	Horas hora = new Horas(id, data, dataFinal, desc);
	    	horas.add(hora);
	    	 
	    	cursor.moveToNext();
	    }
	    
	    return horas;
	}

	
	public void showLogHoras(){
		open();
		
		Cursor cursor = database.query("HORAS", new String[]{"ID", "DATA_LANCAMENTO","DESCRICAO"}, null, null, null, null, null);
	    cursor.moveToFirst();
	    
	    while(!cursor.isAfterLast()){
	    	String id = cursor.getString(0);
	    	String data = cursor.getString(1);
	    	String desc = cursor.getString(2);
	    	
	    	 Log.w(HorasDAO.class.getName(),  "ID: "+id+" DATA: "+data+" DESCRICAO: "+desc);
	    	 
	    	cursor.moveToNext();
	    }
	}

}
