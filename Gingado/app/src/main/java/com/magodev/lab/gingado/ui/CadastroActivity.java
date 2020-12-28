package com.magodev.lab.gingado.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.business.RegrasContas;
import com.magodev.lab.gingado.model.UsuarioModel;

public class CadastroActivity extends AppCompatActivity {

    private RegrasContas mUsuario = new RegrasContas();
    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.mViewHolder.nomeUsuario = findViewById(R.id.nome_usuario);
        this.mViewHolder.emailUsuario = findViewById(R.id.email_usuario);
        this.mViewHolder.senhaUsuario = findViewById(R.id.senha_usuario);
        this.mViewHolder.cadastrarUsuario = findViewById(R.id.button_cadastrarUsuario);

        this.mViewHolder.cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = mViewHolder.nomeUsuario.getText().toString();
                String email = mViewHolder.emailUsuario.getText().toString();
                String senha = mViewHolder.senhaUsuario.getText().toString();
                boolean tudoCertoMeuPatrão = validadarUsuario(email,senha);
                if (tudoCertoMeuPatrão){
                    UsuarioModel model = new UsuarioModel(nome,email,senha);
                    if (mUsuario.criarUsuario(model)){
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }else {
                    String s = "";
                    Toast.makeText(getApplicationContext(),"Informe todos os dados corretamente",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });



    }
    private boolean validadarUsuario(String email, String senha ){
        boolean contemNumeros= false;
        boolean contemLetrasMinusculas = false;
        boolean contemLetrasMaiusculas= false;
        boolean senhaValida = false;

        if (email.equals("") || senha.equals("") || senha.length()<8){
            return false;
        }

        else {
            //verificar senha

            for (int i = 0; i<senha.length(); i++){

                if (senha.charAt(i)>=65 && senha.charAt(i)<=90){
                    contemLetrasMaiusculas =true;
                }
                else if (senha.charAt(i)>=97 && senha.charAt(i)<=122){
                    contemLetrasMinusculas =true;
                }
                else if (senha.charAt(i)>=48 && senha.charAt(i)<=57){
                    contemNumeros = true;
                }
                senhaValida = contemLetrasMaiusculas && contemLetrasMinusculas && contemNumeros;
            }
            return email.contains("@") && senhaValida &&email.contains(".com");
        }
    }

    private static class ViewHolder {
        EditText nomeUsuario;
        EditText emailUsuario;
        EditText senhaUsuario;
        Button cadastrarUsuario;
    }
}