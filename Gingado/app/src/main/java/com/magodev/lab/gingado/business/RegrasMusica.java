package com.magodev.lab.gingado.business;

import android.content.Context;

import com.magodev.lab.gingado.model.ModeloSom;
import com.magodev.lab.gingado.repository.local.RepositorioMusica;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RegrasMusica {
    private RepositorioMusica mRepositorio;
    public RegrasMusica(Context context){
        this.mRepositorio = new RepositorioMusica();
    }

    public List<ModeloSom> getList(Context context){
       return this.mRepositorio.getListaMusicas(context);

    }

}