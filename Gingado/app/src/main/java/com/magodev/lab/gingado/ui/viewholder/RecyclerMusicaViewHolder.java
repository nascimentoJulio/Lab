package com.magodev.lab.gingado.ui.viewholder;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.interfaces.OnListClick;
import com.magodev.lab.gingado.model.ModeloSom;

import java.io.File;

public class RecyclerMusicaViewHolder extends RecyclerView.ViewHolder {
    TextView textNomemusica;
    public RecyclerMusicaViewHolder(@NonNull View itemView) {
        super(itemView);
        textNomemusica = itemView.findViewById(R.id.nome_musica);

    }
    public void musica(final ModeloSom musica, final OnListClick tocarMusica) {
        String titulo = musica.getTitulo();
        if (titulo.length()>=20){
           titulo = titulo.replace(titulo.substring(20),"...");
        }
        this.textNomemusica.setText(titulo);

        this.textNomemusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tocarMusica.passarMusicaEntreActivities(musica.getPath());
            }
        });



    }



}
 /* Uri uri = Uri.parse(music.toString());
        final MediaPlayer mediaPlayer = MediaPlayer.create(itemView.getContext(),uri);
        textNomemusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer!=null){
                    mediaPlayer.start();
                }
            }
        });*/