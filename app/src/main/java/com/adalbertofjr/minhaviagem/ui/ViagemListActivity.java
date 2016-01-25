package com.adalbertofjr.minhaviagem.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.adalbertofjr.minhaviagem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AdalbertoF on 24/01/2016.
 */
public class ViagemListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListarViagens;
    private List<Map<String, Object>> mViagens;
    private AlertDialog mAlertDialog;
    private int mViagemSelecionada;
    private AlertDialog mAlertDialogConfirmacao;

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
        mViagens = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<>();
        item.put("imagem", R.mipmap.ic_launcher);
        item.put("destino", "São Paulo");
        item.put("data", "24/01/2016 a 28/01/2016");
        item.put("total", "Gasto total de R$ 314,98");
        item.put("barraProgresso", new Double[]{ 500.0, 450.0, 314.98});
        mViagens.add(item);

        item = new HashMap<>();
        item.put("imagem", R.mipmap.ic_launcher);
        item.put("destino", "Maceió");
        item.put("data", "24/01/2016 a 28/01/2016");
        item.put("total", "Gasto total de R$ 14,98");
        item.put("barraProgresso", new Double[]{ 200.0, 150.0, 14.98});
        mViagens.add(item);

        return mViagens;
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

    private class ViagemViewBinder implements SimpleAdapter.ViewBinder{

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            if(view.getId() == R.id.barra_progresso){
                Double valores[] = (Double[]) data;
                ProgressBar progressBar= (ProgressBar) view;
                progressBar.setMax(valores[0].intValue());
                progressBar.setSecondaryProgress(valores[1].intValue());
                progressBar.setProgress(valores[2].intValue());

                return true;
            }

            return false;
        }
    }
}
