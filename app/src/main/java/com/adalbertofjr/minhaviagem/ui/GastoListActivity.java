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
import com.adalbertofjr.minhaviagem.adapter.GastoListAdapter;
import com.adalbertofjr.minhaviagem.dao.GastoDAO;
import com.adalbertofjr.minhaviagem.data.MinhaViagemDbHelper;
import com.adalbertofjr.minhaviagem.dominio.Gasto;
import com.adalbertofjr.minhaviagem.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AdalbertoF on 24/01/2016.
 */
public class GastoListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // private List<Gasto> mGastos;
    private ListView mListaGastos;
    List<Gasto> mGastos;

    private String dataAnterior = "";
    private Gasto mGastoSelecionado;
    private GastoListAdapter mGastosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gastos);

        mListaGastos = (ListView) findViewById(R.id.listar_gastos);

        mGastos = listarGastos();
        mGastosAdapter = new GastoListAdapter(this, mGastos);
        mListaGastos.setAdapter(mGastosAdapter);

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
            if (mGastoSelecionado != null) {
                removerGasto(mGastoSelecionado);
            }
            return true;
        }

        return super.onContextItemSelected(item);
    }

    private void removerGasto(Gasto gasto) {
        GastoDAO gastoDAO = new GastoDAO(this);
        long result = gastoDAO.excluir(gasto);

        if (result > 0) {
            Toast.makeText(this, "Gasto Removido", Toast.LENGTH_SHORT).show();
            mGastosAdapter.remover(gasto);
            mGastosAdapter.notifyDataSetChanged();
            mGastoSelecionado = null;
            dataAnterior = "";
        }
    }

    private List<Gasto> listarGastos() {
        GastoDAO gastoDAO = new GastoDAO(this);
        List<Gasto> gastos = gastoDAO.listarGastos(null);

        return gastos;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mGastoSelecionado = mGastos.get(position);
    }

}
