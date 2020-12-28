package com.magodev.lab.gingado.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.constants.Constants;

import java.io.IOException;

public class PlayerMusica extends AppCompatActivity {
    boolean estaTocando = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_musica);



        Bundle bundle = getIntent().getExtras();

        if (bundle!=null){
            String musica = bundle.getString(Constants.BUNDLE.PASSAR_DADOS);
            MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse(musica));
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(this, Uri.parse(musica));
                mediaPlayer.start();

            }
        }
        }
    
