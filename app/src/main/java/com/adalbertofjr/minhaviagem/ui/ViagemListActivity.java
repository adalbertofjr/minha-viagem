package com.adalbertofjr.minhaviagem.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.adalbertofjr.minhaviagem.R;
import com.adalbertofjr.minhaviagem.data.MinhaViagemContract;
import com.adalbertofjr.minhaviagem.data.MinhaViagemDbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.ViagemEntry.DATA_CHEGADA;
import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.ViagemEntry.DATA_SAIDA;
import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.ViagemEntry.DESTINO;
import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.ViagemEntry.ID;
import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.ViagemEntry.ORCAMENTO;
import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.ViagemEntry.TIPO_VIAGEM;
import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.ViagemEntry.VIAGEM_LAZER;

/**
 * Created by AdalbertoF on 24/01/2016.
 */
public class ViagemListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private ListView mListarViagens;
    private List<Map<String, Object>> mViagens;
    private AlertDialog mAlertDialog;
    private int mViagemSelecionada;
    private AlertDialog mAlertDialogConfirmacao;
    private MinhaViagemDbHelper mDbHelper;
    private Double mValorLimite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens);

        mListarViagens = (ListView) findViewById(R.id.listar_viagens);

        String from[] = {"imagem", "destino", "data", "total", "barraProgresso"};
        int[] to = {R.id.tipo_viagem, R.id.destino, R.id.data, R.id.valor, R.id.barra_progresso};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listarViagens(),
                R.layout.lista_viagem,
                from,
                to);

        simpleAdapter.setViewBinder(new ViagemViewBinder());
        mListarViagens.setAdapter(simpleAdapter);

        mListarViagens.setOnItemClickListener(this);
        this.mAlertDialog = criarAlertDialog();
        this.mAlertDialogConfirmacao = criarAlertConfirmacao();
    }

    private List<Map<String, Object>> listarViagens() {
        mDbHelper = new MinhaViagemDbHelper(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String valor = preferences.getString("valor_limite", "-1");
        mValorLimite = Double.valueOf(valor);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "Select * from viagem", null);
        cursor.moveToFirst();

        mViagens = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < cursor.getCount(); i++) {
            Map<String, Object> item = new HashMap<>();

            int id = cursor.getInt(cursor.getColumnIndex(ID));
            int tipoViagem = cursor.getInt(cursor.getColumnIndex(TIPO_VIAGEM));
            String destino = cursor.getString(cursor.getColumnIndex(DESTINO));
            long dataChegada = cursor.getLong(cursor.getColumnIndex(DATA_CHEGADA));
            long dataSaida = cursor.getLong(cursor.getColumnIndex(DATA_SAIDA));
            double orcamento = cursor.getDouble(cursor.getColumnIndex(ORCAMENTO));

            item.put(ID, id);
            //Todo - incluir imagem de lazer e negócios
            if (tipoViagem == VIAGEM_LAZER) {
                item.put("imagem", R.mipmap.ic_launcher);
            } else {
                item.put("imagem", R.mipmap.ic_launcher);
            }
            item.put(DESTINO, destino);
            Date dataChegadaDate = new Date(dataChegada);
            Date dataSaidaDate = new Date(dataSaida);

            String periodo = DATE_FORMAT.format(dataChegadaDate) +
                    " a " + DATE_FORMAT.format(dataSaidaDate);
            item.put("data", periodo);

            double totalGasto = calcularTotalGasto(db, id);
            item.put("total", "Gasto Total R$" + totalGasto);

            double alerta = orcamento * mValorLimite / 100;
            Double[] valores = new Double[]{
                    orcamento, alerta, totalGasto
            };
            item.put("barraProgresso", valores);

            mViagens.add(item);
            cursor.moveToNext();
        }

        cursor.close();

        return mViagens;
    }

    private double calcularTotalGasto(SQLiteDatabase db, int id) {
        Cursor cursor = db.rawQuery("Select SUM(valor) from gasto where viagem_id = ?",
                new String[]{id + ""});
        cursor.moveToFirst();
        double total = cursor.getDouble(0);
        cursor.close();
        return total;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //startActivity(new Intent(this, GastoListActivity.class));
        this.mViagemSelecionada = position;
        mAlertDialog.show();
    }

    /**
     * Menu Contexto para viagens.
     */
    private AlertDialog criarAlertDialog() {
        final String[] items = {
                getString(R.string.editar),
                getString(R.string.novo_gasto),
                getString(R.string.gastos_realizados),
                getString(R.string.remover)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.opcoes));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        startActivity(new Intent(ViagemListActivity.this, NovaViagemActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(ViagemListActivity.this, NovoGastoActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(ViagemListActivity.this, GastoListActivity.class));
                        break;
                    case 3:
                        mAlertDialogConfirmacao.show();
                        break;
                }
            }
        });
        return builder.create();
    }

    /**
     * Confirmar remoção de viagem.
     *
     * @return
     */
    private AlertDialog criarAlertConfirmacao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.message_remover_viagem));
        builder.setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAlertDialogConfirmacao.dismiss();
            }
        });
        builder.setPositiveButton(getString(R.string.remover), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Todo - Remover viagem no banco de dados
                mViagens.remove(mViagemSelecionada);
                mListarViagens.invalidateViews();
            }
        });

        return builder.create();
    }

    private class ViagemViewBinder implements SimpleAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            if (view.getId() == R.id.barra_progresso) {
                Double valores[] = (Double[]) data;
                ProgressBar progressBar = (ProgressBar) view;
                progressBar.setMax(valores[0].intValue());
                progressBar.setSecondaryProgress(valores[1].intValue());
                progressBar.setProgress(valores[2].intValue());

                return true;
            }

            return false;
        }
    }
}
