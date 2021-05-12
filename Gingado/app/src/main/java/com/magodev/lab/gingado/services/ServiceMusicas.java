package com.magodev.lab.gingado.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.magodev.lab.gingado.constants.Constants;

import java.io.IOException;

public class ServiceMusicas extends Service {
    private MediaPlayer player = new MediaPlayer();
    private Context mContext;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        if (player.isPlaying()){
            Log.d("teste", "tava tocando pia");
            player.stop();

        }
        Bundle bundle = intent.getExtras();
        String teste = bundle.getString(Constants.BUNDLE.PASSAR_DADOS);

        player = MediaPlayer.create(mContext, Uri.parse(teste));
        player.start();

        return super.onStartCommand(intent, flags, startId);
    }
}
