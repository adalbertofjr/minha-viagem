package com.adalbertofjr.minhaviagem.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.adalbertofjr.minhaviagem.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AdalbertoF on 24/01/2016.
 */
public class ViagemListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListarViagens;
    private List<Map<String, Object>> mViagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens);

        mListarViagens = (ListView) findViewById(R.id.listar_viagens);

        String from[] = {"imagem", "destino", "data", "total"};
        int[] to = {R.id.tipo_viagem, R.id.destino, R.id.data, R.id.valor};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listarViagens(),
                R.layout.lista_viagem,
                from,
                to);

        mListarViagens.setAdapter(simpleAdapter);

        mListarViagens.setOnItemClickListener(this);
    }

    private List<Map<String, Object>> listarViagens() {
        mViagens = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<>();
        item.put("imagem", R.mipmap.ic_launcher);
        item.put("destino", "São Paulo");
        item.put("data", "24/01/2016 a 28/01/2016");
        item.put("total", "Gasto total de R$ 34,18");
        mViagens.add(item);

        item = new HashMap<>();
        item.put("imagem", R.mipmap.ic_launcher);
        item.put("destino", "Maceió");
        item.put("data", "24/01/2016 a 28/01/2016");
        item.put("total", "Gasto total de R$ 3445,18");
        mViagens.add(item);

        return mViagens;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, GastoListActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_viagem, menu);
        return super.onCreateOptionsMenu(menu);

    }
}
