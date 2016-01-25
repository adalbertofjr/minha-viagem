package com.adalbertofjr.minhaviagem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.adalbertofjr.minhaviagem.R;

/**
 * Created by AdalbertoF on 22/01/2016.
 */
public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mNovaViagem;
    private TextView mNovoGasto;
    private TextView mMinhasViagens;
    private TextView mConfiguracoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mNovaViagem = (TextView) findViewById(R.id.nova_viagem);
        mNovoGasto = (TextView) findViewById(R.id.novo_gasto);
        mMinhasViagens = (TextView) findViewById(R.id.minhas_viagens);
        mConfiguracoes = (TextView) findViewById(R.id.configuracoes);

        mNovaViagem.setOnClickListener(this);
        mNovoGasto.setOnClickListener(this);
        mMinhasViagens.setOnClickListener(this);
        mConfiguracoes.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.nova_viagem) {
            startActivity(new Intent(this, NovaViagemActivity.class));
        }

        if (id == R.id.novo_gasto){
            startActivity(new Intent(this, NovoGastoActivity.class));
        }

        if( id == R.id.minhas_viagens){
            startActivity(new Intent(this, ViagemListActivity.class));
        }

        if( id == R.id.configuracoes){
            startActivity(new Intent(this, ConfiguracoesActivity.class));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
