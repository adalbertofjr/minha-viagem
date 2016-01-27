package com.adalbertofjr.minhaviagem.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.adalbertofjr.minhaviagem.R;
import com.adalbertofjr.minhaviagem.dao.MinhaViagemDAO;
import com.adalbertofjr.minhaviagem.data.MinhaViagemContract;
import com.adalbertofjr.minhaviagem.data.MinhaViagemDbHelper;
import com.adalbertofjr.minhaviagem.dominio.Viagem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.adalbertofjr.minhaviagem.data.MinhaViagemContract.ViagemEntry;

/**
 * Created by AdalbertoF on 22/01/2016.
 */
public class ViagemActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String VIAGEM_EXTRA = "viagem_id";
    private int ano;
    private int mes;
    private int dia;
    private Date dataChegada, dataPartida;

    private Button mDataPartida;
    private Button mDataChegada;
    private EditText mDestino;
    private RadioGroup mTipoViagem;
    private EditText mOrcamento;
    private EditText mQuantidadePesssoas;
    private Button mSalvaViagem;

    private MinhaViagemDbHelper mHelper;
    private int mViagemId;

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

        mHelper = new MinhaViagemDbHelper(this);

        mViagemId = getIntent().getIntExtra(VIAGEM_EXTRA, -1);

        setDataInicial(mDataChegada);
        setDataInicial(mDataPartida);

        if (mViagemId != -1) {
            prepararEdicao(mViagemId);
        }

        mDataChegada.setOnClickListener(this);
        mDataPartida.setOnClickListener(this);
        mSalvaViagem.setOnClickListener(this);


    }

    private void prepararEdicao(int mViagemId) {
        SQLiteDatabase db = mHelper.getReadableDatabase();

        Cursor cursor =
                db.rawQuery("SELECT * FROM VIAGEM WHERE _ID = ?", new String[]{mViagemId + ""});
        cursor.moveToFirst();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (cursor.getInt(0) == ViagemEntry.VIAGEM_LAZER) {
            mTipoViagem.check(R.id.lazer);
        } else {
            mTipoViagem.check(R.id.negocios);
        }

        mDestino.setText(cursor.getString(cursor.getColumnIndex(ViagemEntry.DESTINO)));
        dataChegada = new Date(cursor.getLong(cursor.getColumnIndex(ViagemEntry.DATA_CHEGADA)));
        dataPartida = new Date(cursor.getLong(cursor.getColumnIndex(ViagemEntry.DATA_PARTIDA)));
        mDataChegada.setText(dateFormat.format(dataChegada));
        mDataPartida.setText(dateFormat.format(dataPartida));
        mQuantidadePesssoas.setText(cursor.getString(cursor.getColumnIndex(ViagemEntry.QTD_PESSOAS)));
        mOrcamento.setText(cursor.getString(cursor.getColumnIndex(ViagemEntry.ORCAMENTO)));
        cursor.close();

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.data_chegada) {
            showDialog(id);
        }

        if (id == R.id.data_partida) {
            showDialog(id);
        }

        if (id == R.id.salvar_viagem) {
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

        if (item.getItemId() == R.id.novo_gasto) {
            startActivity(new Intent(this, GastoActivity.class));
            return true;
        }

        if (item.getItemId() == R.id.remover) {
            //TODO - Remover viagem
            Toast.makeText(this, "Remover Viagem", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void salvarViagem() {
        Viagem viagem = getViagemDados();
        MinhaViagemDAO viagemDAO = new MinhaViagemDAO(this);

        long resultado = viagemDAO.salvar(viagem);
        if (resultado != -1) {
            String msg = mViagemId == -1 ? "Viagem Incluida!" : "Viagem Atualizada!";
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao Incluir!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Pega dados da activity e converte em um objeto.
     * @return viagem
     */
    @NonNull
    private Viagem getViagemDados() {
        int tipoViagemId = mTipoViagem.getCheckedRadioButtonId();

        if (tipoViagemId == R.id.lazer) {
            tipoViagemId = ViagemEntry.VIAGEM_LAZER;
        } else {
            tipoViagemId = ViagemEntry.VIAGEM_NEGOCIOS;
        }

        Viagem viagem = new Viagem(
                Long.parseLong(mViagemId + ""),
                mDestino.getText().toString(),
                tipoViagemId,
                new Date(dataChegada.getTime()),
                new Date(dataPartida.getTime()),
                Double.parseDouble(mOrcamento.getText().toString()),
                Integer.parseInt(mQuantidadePesssoas.getText().toString()));

        return viagem;
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
        if (id == R.id.data_chegada) {
            return new DatePickerDialog(this, dataChegadalistener, ano, mes, dia);
        }

        if (id == R.id.data_partida) {
            return new DatePickerDialog(this, dataSaidaListener, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dataChegadalistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataChegada = criarData(ano, mes, dia);
            mDataChegada.setText(String.format("%s/%s/%s", dia, (mes + 1), ano));
        }
    };

    private DatePickerDialog.OnDateSetListener dataSaidaListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            dataPartida = criarData(ano, mes, dia);
            mDataPartida.setText(String.format("%s/%s/%s", dia, (mes + 1), ano));
        }
    };

    private Date criarData(int anoSelecionado, int mesSelecionado, int diaSelecionado) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(anoSelecionado, mesSelecionado, diaSelecionado);
        return calendar.getTime();
    }

    private void setDataInicial(Button b) {
        Calendar calendario = Calendar.getInstance();
        ano = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        dataChegada = criarData(ano, mes, dia);
        dataPartida = criarData(ano, mes, dia);
        b.setText(String.format("%s/%s/%s", dia, (mes + 1), ano));
    }
}
