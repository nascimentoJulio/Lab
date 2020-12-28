package com.magodev.lab.gingado.business;

import com.magodev.lab.gingado.model.UsuarioModel;
import com.magodev.lab.gingado.repository.remoto.AutenticacaoFirebase;

public class RegrasContas {
    private AutenticacaoFirebase repositorioContas;

    public RegrasContas(){
        this.repositorioContas = new AutenticacaoFirebase();
    }
    public boolean criarUsuario(UsuarioModel model){
        return this.repositorioContas.criarUsuario(model);
    }
    public boolean loginUsuario(String email, String senha){
     return   this.repositorioContas.loginUsuario(email,senha);
    }
}
