package com.magodev.lab.gingado.business;

import android.content.Context;

import com.magodev.lab.gingado.model.MusicModel;
import com.magodev.lab.gingado.repository.RepositorioMusica;

import java.util.List;

public class RegrasMusica {
    private RepositorioMusica mRepositorio;
    public RegrasMusica(){
        this.mRepositorio = new RepositorioMusica();
    }

    public List<MusicModel> getList(Context context){
       return this.mRepositorio.getListaMusicas(context);
    }



}
