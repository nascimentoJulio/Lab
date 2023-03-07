package com.magodev.lab.gingado.interfaces;

import com.magodev.lab.gingado.model.MusicModel;


public interface OnListClick {
    void passMusicBetweenActivities(MusicModel music, int position);
}
