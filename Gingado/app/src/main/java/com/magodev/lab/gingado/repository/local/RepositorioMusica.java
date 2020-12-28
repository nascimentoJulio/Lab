package com.magodev.lab.gingado.repository.local;

import android.content.ContentResolver;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.model.ModeloSom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;

import android.content.Context;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ListView;
public class RepositorioMusica {
    List <ModeloSom> mMusicas = new ArrayList<>();
    public RepositorioMusica(){
    }


    public List<ModeloSom> getListaMusicas(Context context){

        return getMusicasDoDevice(context);
    }

    private List<ModeloSom> getMusicasDoDevice(Context context){
        int getArtista = 0;
        int getDuracao = 0;
        long duracao =0;
        int id = 0;
        String titulo ="";
        String artista;
        String path = "";

        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null,
                null, null);
        if (musicCursor!=null && musicCursor.moveToFirst()){
            int getTitulo = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int getId = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int getPath = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                getArtista = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                getDuracao = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            }
            while (musicCursor.moveToNext()){
                titulo = musicCursor.getString(getTitulo);
                id  = musicCursor.getInt(getId);
                path = musicCursor.getString(getPath);


                if (getArtista != 0){
                    artista = musicCursor.getString(getArtista);
                }
                else {
                    artista = context.getString(R.string.artista_desconhecido);
                }
                if (getDuracao!=0){
                    duracao = musicCursor.getLong(getDuracao);
                }

                this.mMusicas.add(new ModeloSom(path,titulo,artista,duracao));
            }

        }
        return this.mMusicas;
    }


}
/* int getArtista = 0;
        int getDuracao = 0;
        long duracao =0;
        int id = 0;
        String titulo ="";
        String artista;

        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null,
                null, null);
        if (musicCursor!=null && musicCursor.moveToFirst()){
            int getTitulo = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int getId = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                 getArtista = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                 getDuracao = musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            }
            while (musicCursor.moveToNext()){
                titulo = musicCursor.getString(getTitulo);
                id  = musicCursor.getInt(getId);

                if (getArtista != 0){
                    artista = musicCursor.getString(getArtista);
                }
                else {
                    artista = context.getString(R.string.artista_desconhecido);
                }
                if (getDuracao!=0){
                    duracao = musicCursor.getLong(getDuracao);
                }

                this.mMusicas.add(new ModeloSom(id,titulo,artista,duracao));
            }

        }*/