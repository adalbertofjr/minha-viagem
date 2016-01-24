package com.adalbertofjr.minhaviagem.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AdalbertoF on 24/01/2016.
 */
public class GastoListActivity extends ListActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listarGastos()));
        ListView listaGastos = getListView();
        listaGastos.setOnItemClickListener(this);
    }

    private List<String> listarGastos() {
        return Arrays.asList("Sanduiche R$ 19,90",
                "Táxi R$ 34,90",
                "Revista R$ 12,00");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view;
        Toast.makeText(this, "Gasto selecionado: " + textView.getText(), Toast.LENGTH_SHORT ).show();

    }
}
