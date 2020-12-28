package com.magodev.lab.gingado.model;

public class UsuarioModel {
    private String name;
    private String email;
    private String senha;

    public UsuarioModel(String name, String email, String senha) {
        this.name = name;
        this.email = email;
        this.senha = senha;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
