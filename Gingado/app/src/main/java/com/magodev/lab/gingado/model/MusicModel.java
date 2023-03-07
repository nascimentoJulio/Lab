package com.magodev.lab.gingado.model;

import java.io.Serializable;

public class MusicModel implements Serializable {

    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public long getDuração() {
        return duração;
    }

    public void setDuração(long duração) {
        this.duração = duração;
    }

    private String path;
    private String artista;
    private long duração;

    public MusicModel(String path, String titulo, String artista, long duracao) {
        this.titulo = titulo;
        this.path = path;
        this.artista = artista;
        this.duração = duracao;
    }


}
