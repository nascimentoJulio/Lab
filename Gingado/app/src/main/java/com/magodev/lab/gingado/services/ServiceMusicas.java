package com.magodev.lab.gingado.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.magodev.lab.gingado.constants.Constants;
import com.magodev.lab.gingado.ui.MainActivity;

import java.io.IOException;

public class ServiceMusicas extends Service {
    private static MediaPlayer player = new MediaPlayer();
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


        if (player.isPlaying()) {
            Log.d("teste", "tava tocando pia");
            player.stop();

        }
        Bundle bundle = intent.getExtras();
        String music = bundle.getString(Constants.BUNDLE.SERVICE_MUSIC);

        player = MediaPlayer.create(mContext, Uri.parse(music));
        player.start();

        return super.onStartCommand(intent, flags, startId);
    }

    public static void onPauseMusic() {
        if (player.isPlaying()) {
            Log.d("teste", "tava tocando pia");
            player.pause();
        }
    }

    public static void onStartMusic() {
        if (!player.isPlaying()) {
            Log.d("teste", "tava tocando pia");
            player.start();
        }
    }

    public static void onTocarProxima(String path) {
        player.stop();
        player.reset();
        try {
            player.setDataSource(path);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.i("ServiceMusic", "Erro ao tocar musica");
        }
    }

    public static int getMusicDuration(){
        return player.getDuration();
    }
    public static int getCurrentMusicPosition() {
        return player.getCurrentPosition() / 1000;
    }
}
