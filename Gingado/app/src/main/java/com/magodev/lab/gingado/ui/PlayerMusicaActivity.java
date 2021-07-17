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
import android.widget.ImageView;
import android.widget.TextView;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.constants.Constants;
import com.magodev.lab.gingado.model.ModeloSom;

import java.io.IOException;
import java.util.Locale;

public class PlayerMusicaActivity extends AppCompatActivity {

    private ViewHolder mViewholder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_musica);
        this.mViewholder.textTempoAtual = findViewById(R.id.text_tempo_atual);
        this.mViewholder.textTempoTotal = findViewById(R.id.text_tempo_total);
        this.mViewholder.textNomeMusica = findViewById(R.id.text_nome_musica);
        this.mViewholder.albumImage = findViewById(R.id.imagem_album);

        Bundle bundle = getIntent().getExtras();
        ModeloSom musica = (ModeloSom) bundle.getSerializable(Constants.BUNDLE.PASSAR_OBJETO);

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(musica.getPath());

        byte[] biteImage = retriever.getEmbeddedPicture();
        if (biteImage != null){
        Bitmap image = BitmapFactory.decodeByteArray(biteImage,0,biteImage.length);

            this.mViewholder.albumImage.setImageBitmap(image);
        }

        int duracao = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));

        this.mViewholder.textNomeMusica.setText(musica.getTitulo());

        this.mViewholder.textTempoTotal.setText(String.format(Locale.getDefault(),
                "%d:%d", ((duracao % (1000 * 60 * 60)) / (1000 * 60)),(
                        ((duracao % (1000 * 60 * 60)) % (1000 * 60) / 1000)
                        )));

    }

    private static class ViewHolder {
        TextView textNomeMusica;
        TextView textTempoAtual;
        TextView textTempoTotal;
        ImageView albumImage;
    }

}
    
