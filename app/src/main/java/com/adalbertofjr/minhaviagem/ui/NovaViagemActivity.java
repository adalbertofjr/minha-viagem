package com.adalbertofjr.minhaviagem.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.adalbertofjr.minhaviagem.R;
import com.adalbertofjr.minhaviagem.data.MinhaViagemContract;
import com.adalbertofjr.minhaviagem.data.MinhaViagemDbHelper;

import java.util.Calendar;

import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.*;

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
    private EditText mDestino;
    private RadioGroup mTipoViagem;
    private EditText mOrcamento;
    private EditText mQuantidadePesssoas;
    private Button mSalvaViagem;
    private MinhaViagemDbHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_viagem);
        getSupportActionBar().setTitle("Nova Viagem");

        mDestino = (EditText) findViewById(R.id.destino);
        mTipoViagem = (RadioGroup) findViewById(R.id.tipo_viagem);
        mDataChegada = (Button) findViewById(R.id.data_chegada);
        mDataPartida = (Button) findViewById(R.id.data_partida);
        mOrcamento = (EditText) findViewById(R.id.orcamento);
        mQuantidadePesssoas = (EditText) findViewById(R.id.qtd_pessoas);
        mSalvaViagem = (Button) findViewById(R.id.salvar_viagem);

        setDataInicial(mDataChegada);
        setDataInicial(mDataPartida);

        mDataChegada.setOnClickListener(this);
        mDataPartida.setOnClickListener(this);
        mSalvaViagem.setOnClickListener(this);

        mHelper = new MinhaViagemDbHelper(this);

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

        if(id == R.id.salvar_viagem){
            salvarViagem();
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

    public void salvarViagem(){
        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ViagemEntry.DESTINO, mDestino.getText().toString());
        values.put(ViagemEntry.DATA_CHEGADA, ""); //Todo - data chegada e data de saida
        values.put(ViagemEntry.DATA_SAIDA, "");
        values.put(ViagemEntry.ORCAMENTO, mOrcamento.getText().toString());
        values.put(ViagemEntry.QTD_PESSOAS, mQuantidadePesssoas.getText().toString());
        int id = mTipoViagem.getCheckedRadioButtonId();
        if (id == R.id.lazer){
            values.put(ViagemEntry.TIPO_VIAGEM, ViagemEntry.VIAGEM_LAZER);
        }else {
            values.put(ViagemEntry.TIPO_VIAGEM, ViagemEntry.VIAGEM_NEGOCIOS);
        }

        long resultado = db.insert(ViagemEntry.TABLE_NAME, null, values);

        if(resultado != -1){
            Toast.makeText(this, "Viagem Incluida!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao Incluir!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        mHelper.close();
        super.onDestroy();
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

    private void setDataInicial(Button b) {
        Calendar calendario = Calendar.getInstance();
        ano = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        b.setText(String.format("%s/%s/%s", dia, (mes + 1), ano));
    }
}
