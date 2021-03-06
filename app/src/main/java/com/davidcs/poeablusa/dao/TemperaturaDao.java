package com.davidcs.poeablusa.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.davidcs.poeablusa.model.Temperatura;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by logonrm on 07/03/2017.
 */

public class TemperaturaDao {
    private DBOpenHelper banco;

    public TemperaturaDao(Context context){banco = new DBOpenHelper(context);}
    public static final String TABELA_TEMPERATURAS="temperatura";
    public static final String COLUNA_ID="id";
    public static final String COLUNA_frio="frio";
    public static final String COLUNA_calor="calor";
    public static final String COLUNA_chuva="chuva";

    public List<Temperatura> getAll() {
        List<Temperatura> temperaturas = new LinkedList<>();
        String query = "SELECT * FROM " + TABELA_TEMPERATURAS;
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Temperatura temperatura = null;
        if (cursor.moveToFirst()) {
            do {
                temperatura = new Temperatura();
                temperatura.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                temperatura.setFrio(cursor.getInt(cursor.getColumnIndex(COLUNA_frio)));
                temperatura.setCalor(cursor.getInt(cursor.getColumnIndex(COLUNA_calor)));
                temperatura.setChuva(cursor.getInt(cursor.getColumnIndex(COLUNA_chuva)));
                temperaturas.add(temperatura);


            } while (cursor.moveToNext());
        }
        return temperaturas;
    }

        public Temperatura getBy(int id){
        SQLiteDatabase db= banco.getReadableDatabase();
        String colunas[] ={COLUNA_ID ,COLUNA_frio, COLUNA_calor,COLUNA_chuva};
        String where = "id= "+id;
        Cursor cursor = db.query(true, TABELA_TEMPERATURAS, colunas, where, null, null,
                null, null, null);

         Temperatura temperatura =null;

            if(cursor !=null)
            {
             cursor.moveToFirst();
             temperatura = new Temperatura();
             temperatura.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
             temperatura.setCalor(cursor.getInt(cursor.getColumnIndex(COLUNA_calor)));
             temperatura.setChuva(cursor.getInt(cursor.getColumnIndex(COLUNA_chuva)));
             temperatura.setFrio(cursor.getInt(cursor.getColumnIndex(COLUNA_frio)));
            }
            return temperatura;
    }

}

