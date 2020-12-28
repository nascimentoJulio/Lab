package com.magodev.lab.gingado.model;

public class ModeloSom {

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

    public ModeloSom(String path, String titulo, String artista, long duracao) {
        this.titulo = titulo;
        this.path = path;
        this.artista = artista;
        this.duração = duracao;
    }


}