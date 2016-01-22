package com.adalbertofjr.minhaviagem.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.adalbertofjr.minhaviagem.R;

/**
 * Created by AdalbertoF on 22/01/2016.
 */
public class NovaViagemActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_viagem);
        getSupportActionBar().setTitle("Nova Viagem");

    }

    @Override
    public void onClick(View v) {

    }
}
