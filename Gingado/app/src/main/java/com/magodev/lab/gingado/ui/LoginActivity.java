package com.magodev.lab.gingado.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.business.RegrasContas;

public class LoginActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private RegrasContas mLogin = new RegrasContas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mViewHolder.editEmail = findViewById(R.id.email_login);
        this.mViewHolder.editSenha = findViewById(R.id.senha_usuario);
        this.mViewHolder.entrar = findViewById(R.id.button_login_log);

        this.mViewHolder.entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mViewHolder.editEmail.getText().toString();
                String senha = mViewHolder.editSenha.getText().toString();
                final boolean loginPermitido = mLogin.loginUsuario(email, senha);

                if (loginPermitido) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Usuario ou senha incorretos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private static class ViewHolder {
        EditText editEmail;
        EditText editSenha;
        Button entrar;
    }
}
