package com.magodev.lab.gingado.repository.remoto;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.magodev.lab.gingado.model.UsuarioModel;

public class AutenticacaoFirebase {
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    private boolean sucesso;


    public boolean criarUsuario(UsuarioModel usuario){

        this.usuario.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                sucesso = task.isSuccessful();

            }
        });
        return sucesso;
    }
    public boolean loginUsuario(String email, String senha){
        usuario.signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                sucesso = task.isSuccessful();
            }
        });

        return sucesso;
    }
}
