package com.example.meusupermercado.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "LISTA_COMPRAS";
    public static String NOME_TABELA = "compras";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String sql = "CREATE TABLE IF NOT EXISTS " + NOME_TABELA + " (id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT NOT NULL, quantidade INT(255), valor DOUBLE(100, 2)); ";
        try{
            db.execSQL(sql);
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar tabela "+ e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
