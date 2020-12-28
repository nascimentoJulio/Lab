package com.magodev.lab.gingado.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.magodev.lab.gingado.R;

public class StartActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if (getSupportActionBar()!= null){
            getSupportActionBar().hide();
        }

        this.mViewHolder.buttonLogin = findViewById(R.id.button_login);
        this.mViewHolder.buttonCadastrar = findViewById(R.id.button_cadastrar);
        this.mViewHolder.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
        this.mViewHolder.buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CadastroActivity.class));
            }
        });
    }
    private static class ViewHolder{
        Button buttonLogin;
        Button buttonCadastrar;
    }
}