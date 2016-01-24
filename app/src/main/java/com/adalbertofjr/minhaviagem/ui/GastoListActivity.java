package com.adalbertofjr.minhaviagem.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.adalbertofjr.minhaviagem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AdalbertoF on 24/01/2016.
 */
public class GastoListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Map<String, Object>> mGastos;
    private ListView mListaGastos;

    private String dataAnterior = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gastos);

        mListaGastos = (ListView) findViewById(R.id.listar_gastos);


        String[] from = {"data", "descricao", "valor", "categoria"};
        int[] to = {R.id.data, R.id.descricao, R.id.valor, R.id.categoria};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listarGastos(),
                R.layout.lista_gastos,
                from,
                to);

        simpleAdapter.setViewBinder(new GastoViewBinder());
        mListaGastos.setAdapter(simpleAdapter);

        registerForContextMenu(mListaGastos);

        mListaGastos.setOnItemClickListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_gasto, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.remover) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                    item.getMenuInfo();
            mGastos.remove(info.position);
            mListaGastos.invalidateViews();
            dataAnterior = "";

            //Todo - Remover gasto do banco

            return true;
        }
        return super.onContextItemSelected(item);
    }

    private List<Map<String, Object>> listarGastos() {
        mGastos = new ArrayList<>();

        Map<String, Object> gasto = new HashMap<>();
        gasto.put("data", "24/01/2016");
        gasto.put("descricao", "Diária do Hotel");
        gasto.put("valor", "R$ 260,00");
        gasto.put("categoria", R.color.categoria_hospedagem);
        mGastos.add(gasto);

        gasto = new HashMap<>();
        gasto.put("data", "24/01/2016");
        gasto.put("descricao", "Diária do Hotel");
        gasto.put("valor", "R$ 260,00");
        gasto.put("categoria", R.color.categoria_hospedagem);
        mGastos.add(gasto);

        gasto = new HashMap<>();
        gasto.put("data", "24/01/2016");
        gasto.put("descricao", "Diária do Hotel");
        gasto.put("valor", "R$ 260,00");
        gasto.put("categoria", R.color.categoria_hospedagem);
        mGastos.add(gasto);

        gasto = new HashMap<>();
        gasto.put("data", "25/01/2016");
        gasto.put("descricao", "Comida");
        gasto.put("valor", "R$ 260,00");
        gasto.put("categoria", R.color.categoria_alimentacao);
        mGastos.add(gasto);

        return mGastos;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> map = mGastos.get(position);
        String descricao = (String) map.get("descricao");
        String mensagem = "Gasto selecionado: " + descricao;
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private class GastoViewBinder implements SimpleAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {

            if (view.getId() == R.id.data) {
                if (!dataAnterior.equals(data)) {
                    TextView textView = (TextView) view;
                    textView.setText(textRepresentation);
                    dataAnterior = textRepresentation;
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
                return true;
            }

            if (view.getId() == R.id.categoria) {
                Integer id = (Integer) data;
                view.setBackgroundColor(getResources().getColor(id));
                return true;
            }
            return false;
        }
    }
}
