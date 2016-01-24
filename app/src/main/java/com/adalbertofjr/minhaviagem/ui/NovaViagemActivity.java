package com.adalbertofjr.minhaviagem.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.adalbertofjr.minhaviagem.R;

import java.util.Calendar;

/**
 * Created by AdalbertoF on 22/01/2016.
 */
public class NovaViagemActivity extends AppCompatActivity implements View.OnClickListener{


    private Button mDataChegada;
    private Button mDataPartida;
    private int ano;
    private int mes;
    private int dia;
    private Button mButtonDateSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_viagem);
        getSupportActionBar().setTitle("Nova Viagem");

        mDataChegada = (Button) findViewById(R.id.data_chegada);
        mDataPartida = (Button) findViewById(R.id.data_partida);

        setDataInicial(mDataChegada);
        setDataInicial(mDataPartida);


        mDataChegada.setOnClickListener(this);
        mDataPartida.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.data_chegada){
            showDialog(id);
        }

        if(id == R.id.data_partida){
            showDialog(id);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_viagem, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.novo_gasto){
            startActivity(new Intent(this, NovoGastoActivity.class));
            return true;
        }

        if (item.getItemId() == R.id.remover){
            //TODO - Remover viagem
            Toast.makeText(this, "Remover Viagem", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * Selecionar data de chegada e partida.
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        mButtonDateSelected = (Button) findViewById(id);

        if(id == R.id.data_chegada){
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }

        if(id == R.id.data_partida){
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            mButtonDateSelected.setText(String.format("%s/%s/%s", dia, (mes + 1), ano));
        }
    };

    private void setDataInicial(Button v) {
        Calendar calendario = Calendar.getInstance();
        ano = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        v.setText(String.format("%s/%s/%s", dia, (mes + 1), ano));
    }
}
