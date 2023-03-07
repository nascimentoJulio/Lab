package com.magodev.lab.gingado.interfaces;

import com.magodev.lab.gingado.model.MusicModel;


public interface OnListClick {
    void passarMusicaEntreActivities(MusicModel musica, int position);


    void tocarProxima(int position);
}
