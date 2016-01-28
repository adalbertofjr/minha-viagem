package com.adalbertofjr.minhaviagem.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.adalbertofjr.minhaviagem.R;
import com.adalbertofjr.minhaviagem.ui.fragments.ConfiguracoesFragment;

/**
 * Created by AdalbertoF on 25/01/2016.
 */
public class ConfiguracoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle(R.string.configuracoes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
                .replace(R.id.conteudo_configuracoes, new ConfiguracoesFragment())
                .commit();
    }
}
