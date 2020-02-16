package com.example.meusupermercado.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.meusupermercado.model.Compras;

import java.util.ArrayList;
import java.util.List;

public class ComprasDAO implements ICompras {
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public ComprasDAO(Context context){
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Compras compras) {
        try {
            ContentValues cv = new ContentValues();
            cv.put("item", compras.getItem());
            cv.put("quantidade", compras.getQuantidade());
            cv.put("valor", compras.getValor());
            escreve.insert(DbHelper.NOME_TABELA, null, cv);
            Log.i("INFO", "Item salva com sucesso!");

        }catch (Exception e){
            Log.e("INFO", "Erro ao Salvar item" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Compras compras) {
        ContentValues cv = new ContentValues();
        cv.put("item", compras.getItem());
        cv.put("quantidade", compras.getQuantidade());
        cv.put("valor", compras.getValor());
        try {
            String[] args = {compras.getId().toString()};
            escreve.update(DbHelper.NOME_TABELA, cv, "id=?",args);
            Log.i("INFO", "Item atualizado com sucesso!");
        }catch (Exception e){
            Log.i("INFO", "Erro ao atualizar  item! " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Compras compras) {
        return false;
    }

    @Override
    public List<Compras> listar() {
        List<Compras> listaCompras = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.NOME_TABELA + ";";
        Cursor cursor = le.rawQuery(sql, null);
        while (cursor.moveToNext()){
            Compras compras = new Compras();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String item = cursor.getString(cursor.getColumnIndex("item"));
            int quantidade = cursor.getInt(cursor.getColumnIndex("quantidade"));
            double valor = cursor.getDouble(cursor.getColumnIndex("valor"));

            //popular objeto compras
            compras.setId(id);
            compras.setItem(item);
            compras.setQuantidade(quantidade);
            compras.setValor(valor);
            listaCompras.add(compras);
        }
        return listaCompras;
    }
}
