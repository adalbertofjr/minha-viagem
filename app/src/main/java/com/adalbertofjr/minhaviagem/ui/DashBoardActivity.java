package com.adalbertofjr.minhaviagem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.adalbertofjr.minhaviagem.R;

/**
 * Created by AdalbertoF on 22/01/2016.
 */
public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mNovaViagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mNovaViagem = (TextView) findViewById(R.id.nova_viagem);

        mNovaViagem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.nova_viagem){
            startActivity(new Intent(this, NovaViagemActivity.class));
        }

    }
}
