package com.adalbertofjr.minhaviagem.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.adalbertofjr.minhaviagem.R;

/**
 * Created by AdalbertoF on 22/01/2016.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String MANTER_CONECTADO = "manter_conectado";

    private EditText mUsuario;
    private EditText mSenha;
    private Button mEntrar;
    private CheckBox mManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsuario = (EditText) findViewById(R.id.usuario);
        mSenha = (EditText) findViewById(R.id.senha);
        mEntrar = (Button) findViewById(R.id.entrar);
        mManterConectado = (CheckBox) findViewById(R.id.manter_conectado);

        estaConectado();

        mEntrar.setOnClickListener(this);
    }

    private void estaConectado() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean conectado = preferences.getBoolean(MANTER_CONECTADO, false);
        if(conectado){
            startActivity( new Intent(this, DashBoardActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.entrar) {
            String usuario = mUsuario.getText().toString();
            String senha = mSenha.getText().toString();

            if ("leitor".equals(usuario) && "123".equals(senha)) {
                definirConexao();
                startActivity(new Intent(this, DashBoardActivity.class));
            } else {
                Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void definirConexao() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(MANTER_CONECTADO,
                mManterConectado.isChecked());
        editor.commit();
    }
}
