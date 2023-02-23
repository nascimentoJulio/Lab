package com.magodev.lab.gingado.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.constants.Constants;
import com.magodev.lab.gingado.model.ModeloSom;
import com.magodev.lab.gingado.services.ServiceMusicas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class PlayerMusicaActivity extends AppCompatActivity {

    private ViewHolder mViewholder = new ViewHolder();

    private int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_musica);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        this.mViewholder.textTempoAtual = findViewById(R.id.text_tempo_atual);
        this.mViewholder.textTempoTotal = findViewById(R.id.text_tempo_total);
        this.mViewholder.textNomeMusica = findViewById(R.id.text_nome_musica);
        this.mViewholder.albumImage = findViewById(R.id.imagem_album);
        this.mViewholder.startButton = findViewById(R.id.button_start);
        this.mViewholder.pauseButton = findViewById(R.id.button_pause);
        this.mViewholder.nextButton = findViewById(R.id.button_next);
        this.mViewholder.previousButton = findViewById(R.id.button_previous);

        this.mViewholder.startButton.setVisibility(View.GONE);

        Bundle bundle = getIntent().getExtras();
        ModeloSom musica = (ModeloSom) bundle.getSerializable(Constants.BUNDLE.PASSAR_OBJETO);
        final ArrayList<ModeloSom> listMusic = (ArrayList<ModeloSom>) getIntent().getSerializableExtra("list_musica");
        this.mPosition = bundle.getInt("position");
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(musica.getPath());

        byte[] biteImage = retriever.getEmbeddedPicture();
        if (biteImage != null) {
            Bitmap image = BitmapFactory.decodeByteArray(biteImage, 0, biteImage.length);

            this.mViewholder.albumImage.setImageBitmap(image);
        }

        int duracao = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));

        this.mViewholder.textNomeMusica.setText(musica.getTitulo());

        this.mViewholder.textTempoTotal.setText(String.format(Locale.getDefault(),
                "%d:%d", ((duracao % (1000 * 60 * 60)) / (1000 * 60)), (
                        ((duracao % (1000 * 60 * 60)) % (1000 * 60) / 1000)
                )));

        this.mViewholder.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceMusicas.onStartMusic();
                mViewholder.startButton.setVisibility(View.GONE);
                mViewholder.pauseButton.setVisibility(View.VISIBLE);
            }
        });

        this.mViewholder.pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceMusicas.onPauseMusic();
                mViewholder.startButton.setVisibility(View.VISIBLE);
                mViewholder.pauseButton.setVisibility(View.GONE);
            }
        });

        this.mViewholder.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playNextMusic(listMusic);
            }
        });

        this.mViewholder.previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPreviousMusic(listMusic);
            }
        });
    }

    private void playNextMusic(ArrayList<ModeloSom> modeloSons) {
        if (mPosition + 1 == modeloSons.size()) {
            mPosition = 0;
            ServiceMusicas.onTocarProxima(modeloSons.get(mPosition).getPath());
        } else {
            mPosition++;
            ServiceMusicas.onTocarProxima(modeloSons.get(mPosition).getPath());
        }

    }

    private void playPreviousMusic(ArrayList<ModeloSom> modeloSons) {
        if (mPosition - 1 < 0) {
            mPosition = 0;
            ServiceMusicas.onTocarProxima(modeloSons.get(mPosition).getPath());
        } else {
            mPosition--;
            ServiceMusicas.onTocarProxima(modeloSons.get(mPosition).getPath());
        }

    }

    private static class ViewHolder {
        TextView textNomeMusica;
        TextView textTempoAtual;
        TextView textTempoTotal;
        ImageView albumImage;

        ImageButton startButton;

        ImageButton pauseButton;

        ImageButton nextButton;

        ImageButton previousButton;
    }

}
