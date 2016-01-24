package com.adalbertofjr.minhaviagem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adalbertofjr.minhaviagem.R;

/**
 * Created by AdalbertoF on 22/01/2016.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsuario;
    private EditText mSenha;
    private Button mEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsuario = (EditText) findViewById(R.id.usuario);
        mSenha = (EditText) findViewById(R.id.senha);
        mEntrar = (Button) findViewById(R.id.entrar);

        mEntrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.entrar) {
            String usuario = mUsuario.getText().toString();
            String senha = mSenha.getText().toString();

            if ("leitor".equals(usuario) && "123".equals(senha)) {
                startActivity(new Intent(this, DashBoardActivity.class));
            } else {
                Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
