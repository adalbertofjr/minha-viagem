package com.adalbertofjr.minhaviagem.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.*;

/**
 * Created by AdalbertoF on 25/01/2016.
 */
public class MinhaViagemDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "minhaviagem.db";
    private static final int DATABASE_VERSION = 1;

    public MinhaViagemDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE_VIAGEM = "CREATE TABLE " + ViagemEntry.TABLE_NAME + " ("
                + ViagemEntry.ID + " INTEGER PRIMARY KEY, "
                + ViagemEntry.DESTINO + " TEXT, "
                + ViagemEntry.TIPO_VIAGEM + " INTEGER, "
                + ViagemEntry.DATA_CHEGADA + " TEXT, "
                + ViagemEntry.DATA_SAIDA + " TEXT, "
                + ViagemEntry.ORCAMENTO + " DOUBLE, "
                + ViagemEntry.QTD_PESSOAS + " INTEGER"
                + ");";

        final String SQL_CREATE_TABLE_GASTO = "CREATE TABLE " + GastoEntry.TABLE_NAME + " ("
                + GastoEntry.ID + " INTEGER PRIMARY KEY, "
                + GastoEntry.CATEGORIA + " TEXT, "
                + GastoEntry.DATA + " STRING, "
                + GastoEntry.VALOR + " DOUBLE, "
                + GastoEntry.DESCRICAO + " TEXT, "
                + GastoEntry.VIAGEM_ID + " INTEGER, "
                + "FOREIGN KEY(" + GastoEntry.VIAGEM_ID + ") REFERENCES "
                + ViagemEntry.TABLE_NAME + "(" + ViagemEntry.ID + ")"
                +")";

        db.execSQL(SQL_CREATE_TABLE_VIAGEM);
        db.execSQL(SQL_CREATE_TABLE_GASTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
