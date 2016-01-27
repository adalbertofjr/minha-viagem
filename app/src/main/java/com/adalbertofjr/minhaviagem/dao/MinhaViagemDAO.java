package com.adalbertofjr.minhaviagem.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.adalbertofjr.minhaviagem.data.MinhaViagemDbHelper;
import com.adalbertofjr.minhaviagem.dominio.Viagem;
import com.adalbertofjr.minhaviagem.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.ViagemEntry;

/**
 * Created by AdalbertoF on 27/01/2016.
 */
public class MinhaViagemDAO {

    private MinhaViagemDbHelper mDBhelper;
    private SQLiteDatabase database;

    public MinhaViagemDAO(Context context) {
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

    private Long inserir(Viagem tarefa) {
        long id = getDb().insert(ViagemEntry.TABLE_NAME, null, viagemToValue(tarefa));
        closeDb();
        return id;
    }

    private long atualizar(Viagem viagem) {
        String where = ViagemEntry.ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(viagem.getId())};
        long linhasAlteradas = getDb().update(ViagemEntry.TABLE_NAME, viagemToValue(viagem), where, whereArgs);

        closeDb();
        return linhasAlteradas;
    }

    public Long salvar(Viagem viagem) {
        if (viagem.getId() == -1) {
            return inserir(viagem);
        }
        return atualizar(viagem);
    }

    public int excluir(Viagem viagem) {
        String where = ViagemEntry.ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(ViagemEntry.ID)};
        int linhasExcluidas = getDb().update(ViagemEntry.TABLE_NAME, viagemToValue(viagem), where, whereArgs);

        closeDb();
        return linhasExcluidas;
    }

    public List<Viagem> listarViagens(String filtro) {
        String sql = "SELECT * FROM " + ViagemEntry.TABLE_NAME;
        String[] argumentos = null;

        if (filtro != null) {
            sql += " WHERE " + filtro;
            //argumentos = new String[]{filtro};
        }

        //sql += " ORDER BY " + ViagemEntry.DATA_CHEGADA;

        Cursor cursor = getDb().rawQuery(sql, null);

        List<Viagem> viagens = new ArrayList<>();

        while (cursor.moveToNext()) {
            Viagem viagem = new Viagem();
            viagem.setId(cursor.getLong(cursor.getColumnIndex(ViagemEntry.ID)));
            viagem.setDestino(cursor.getString(cursor.getColumnIndex(ViagemEntry.DESTINO)));
            viagem.setTipoViagem(cursor.getInt(cursor.getColumnIndex(ViagemEntry.TIPO_VIAGEM)));
            viagem.setDataChegada(new Date(
                    cursor.getLong(cursor.getColumnIndex(ViagemEntry.DATA_CHEGADA))
            ));
            viagem.setDataPartida(new Date(
                    cursor.getLong(cursor.getColumnIndex(ViagemEntry.DATA_PARTIDA))
            ));
            viagem.setOrcamento(cursor.getDouble(cursor.getColumnIndex(ViagemEntry.ORCAMENTO)));
            viagem.setQuantidadePessoas(cursor.getInt(cursor.getColumnIndex(ViagemEntry.QTD_PESSOAS)));

            viagens.add(viagem);
        }

        cursor.close();
        closeDb();

        return viagens;
    }

    private ContentValues viagemToValue(Viagem viagem) {
        ContentValues values = new ContentValues();

        if (viagem.getId() != -1) values.put(ViagemEntry.ID, viagem.getId());
        values.put(ViagemEntry.DESTINO, viagem.getDestino());
        values.put(ViagemEntry.TIPO_VIAGEM, viagem.getTipoViagem());
        values.put(ViagemEntry.DATA_CHEGADA, viagem.getDataChegada().getTime());
        values.put(ViagemEntry.DATA_PARTIDA, viagem.getDataPartida().getTime());
        values.put(ViagemEntry.ORCAMENTO, viagem.getOrcamento());
        values.put(ViagemEntry.QTD_PESSOAS, viagem.getQuantidadePessoas());

        return values;
    }
}
