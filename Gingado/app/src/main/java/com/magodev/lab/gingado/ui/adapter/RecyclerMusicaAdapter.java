package com.magodev.lab.gingado.ui.adapter;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.interfaces.OnListClick;
import com.magodev.lab.gingado.model.ModeloSom;
import com.magodev.lab.gingado.ui.viewholder.RecyclerMusicaViewHolder;

import java.io.File;
import java.util.List;

public class RecyclerMusicaAdapter extends RecyclerView.Adapter<RecyclerMusicaViewHolder> {
    List<ModeloSom> listMusicas;
    OnListClick tocarMusica;
    public RecyclerMusicaAdapter(List<ModeloSom> list, OnListClick tocarMusica){
        listMusicas = list;
        this.tocarMusica = tocarMusica;
    }

    @NonNull
    @Override
    public RecyclerMusicaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_musica,parent, false);
        return new RecyclerMusicaViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerMusicaViewHolder holder, int position) {
        holder.musica(listMusicas.get(position),tocarMusica);
    }


    @Override
    public int getItemCount() {
       return this.listMusicas.size();
    }
}
