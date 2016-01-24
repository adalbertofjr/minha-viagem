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

import com.adalbertofjr.minhaviagem.R;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AdalbertoF on 24/01/2016.
 */
public class ViagemListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView mListarViagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens);

        mListarViagens = (ListView) findViewById(R.id.listar_viagens);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listarViagens());
        mListarViagens.setAdapter(adapter);

        mListarViagens.setOnItemClickListener(this);
    }

    private List<String> listarViagens() {
        return Arrays.asList("São Paulo", "Maceió", "Bonito");
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
