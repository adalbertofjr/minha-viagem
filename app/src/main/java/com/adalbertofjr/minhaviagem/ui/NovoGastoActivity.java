package com.adalbertofjr.minhaviagem.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.adalbertofjr.minhaviagem.R;

import java.util.Calendar;

/**
 * Created by AdalbertoF on 22/01/2016.
 */
public class NovoGastoActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner mTipoGasto;
    private Button mDataGasto;

    int ano, mes, dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_gasto);
        getSupportActionBar().setTitle("Novo gasto - Recife");

        mDataGasto = (Button) findViewById(R.id.data_gasto);
        mTipoGasto = (Spinner) findViewById(R.id.tipo_gasto);

        setDadosTipoGasto();
        setDataGasto(mDataGasto);

        mDataGasto.setOnClickListener(this);
    }

    @NonNull
    private void setDadosTipoGasto() {
        String[] tipoGasto = this.getResources().getStringArray(R.array.tipo_gasto);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tipoGasto);
        mTipoGasto.setAdapter(adapter);
    }

    private void setDataGasto(Button v) {
        Calendar calendario = Calendar.getInstance();
        ano = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        v.setText(String.format("%s/%s/%s", dia, (mes + 1), ano));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.data_gasto) {
            showDialog(id);
        }
    }

    /**
     * Selecionar data para despesa.
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == R.id.data_gasto) {
            return new DatePickerDialog(this, listener, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            mDataGasto.setText(String.format("%s/%s/%s", dia, (mes + 1), ano));
        }
    };
}
