package com.adalbertofjr.minhaviagem.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.adalbertofjr.minhaviagem.data.MinhaViagemContract;
import com.adalbertofjr.minhaviagem.data.MinhaViagemContract.GastoEntry;
import com.adalbertofjr.minhaviagem.data.MinhaViagemDbHelper;
import com.adalbertofjr.minhaviagem.dominio.Gasto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AdalbertoF on 27/01/2016.
 */
public class GastoDAO {
    private MinhaViagemDbHelper mDBhelper;
    private SQLiteDatabase database;

    public GastoDAO(Context context) {
        this.mDBhelper = new MinhaViagemDbHelper(context);
    }

    private SQLiteDatabase getDb() {
        if (database == null) {
            database = mDBhelper.getWritableDatabase();
        }
        return database;
    }

    public void closeDb() {
        mDBhelper.close();
    }

    private Long inserir(Gasto gasto) {
        long id = getDb().insert(GastoEntry.TABLE_NAME, null, gastoToValue(gasto));
        closeDb();
        return id;
    }

    private long atualizar(Gasto gasto) {
        String where = GastoEntry.ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(gasto.getId())};
        long linhasAlteradas = getDb().update(GastoEntry.TABLE_NAME, gastoToValue(gasto), where, whereArgs);

        closeDb();
        return linhasAlteradas;
    }

    public Long salvar(Gasto gasto) {
        if (gasto.getId() == -1) {
            return inserir(gasto);
        }
        return atualizar(gasto);
    }

    public int excluir(Gasto gasto) {
        String where = GastoEntry.ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(GastoEntry.ID)};
        int linhasExcluidas = getDb().update(GastoEntry.TABLE_NAME, gastoToValue(gasto), where, whereArgs);

        closeDb();
        return linhasExcluidas;
    }

    public List<Gasto> listarViagens(String filtro) {
        String sql = "SELECT * FROM " + GastoEntry.TABLE_NAME;
        String[] argumentos = null;

        if (filtro != null) {
            sql += " WHERE " + filtro;
            //argumentos = new String[]{filtro};
        }

        //sql += " ORDER BY " + GastoEntry.DATA_CHEGADA;

        Cursor cursor = getDb().rawQuery(sql, null);

        List<Gasto> gastos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Gasto gasto = new Gasto();
            gasto.setId(cursor.getLong(cursor.getColumnIndex(GastoEntry.ID)));
            gasto.setData(new Date(cursor.getLong(cursor.getColumnIndex(GastoEntry.DATA))));
            gasto.setCategoria(cursor.getString(cursor.getColumnIndex(GastoEntry.CATEGORIA)));
            gasto.setDescricao(cursor.getString(cursor.getColumnIndex(GastoEntry.DESCRICAO)));
            gasto.setValor(cursor.getDouble(cursor.getColumnIndex(GastoEntry.VALOR)));
            gasto.setLocal(cursor.getString(cursor.getColumnIndex(GastoEntry.LOCAL)));
            gasto.setViagemId(cursor.getInt(cursor.getColumnIndex(GastoEntry.VIAGEM_ID)));

            gastos.add(gasto);
        }

        cursor.close();
        closeDb();

        return gastos;
    }

    private ContentValues gastoToValue(Gasto gasto) {
        ContentValues values = new ContentValues();

        if (gasto.getId() != -1) values.put(GastoEntry.ID, gasto.getId());
        values.put(GastoEntry.DATA, gasto.getData().getTime());
        values.put(GastoEntry.CATEGORIA, gasto.getCategoria());
        values.put(GastoEntry.DESCRICAO, gasto.getDescricao());
        values.put(GastoEntry.VALOR, gasto.getValor());
        values.put(GastoEntry.LOCAL, gasto.getLocal());
        values.put(GastoEntry.VIAGEM_ID, gasto.getViagemId());

        return values;
    }

}
