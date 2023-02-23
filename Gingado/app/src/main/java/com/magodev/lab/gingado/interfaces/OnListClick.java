package com.magodev.lab.gingado.interfaces;

import android.content.Context;
import android.net.Uri;

import com.magodev.lab.gingado.model.ModeloSom;


public interface OnListClick {
    void passarMusicaEntreActivities(ModeloSom musica, int position);

    void iniciarServiceMusica(String path);

    void tocarProxima(int position);
}
