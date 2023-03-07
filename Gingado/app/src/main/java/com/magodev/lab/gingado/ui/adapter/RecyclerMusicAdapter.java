package com.magodev.lab.gingado.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.interfaces.OnListClick;
import com.magodev.lab.gingado.model.MusicModel;
import com.magodev.lab.gingado.ui.viewholder.RecyclerMusicaViewHolder;

import java.util.List;

public class RecyclerMusicAdapter extends RecyclerView.Adapter<RecyclerMusicaViewHolder> {
    List<MusicModel> listMusicas;
    OnListClick tocarMusica;
    public RecyclerMusicAdapter(List<MusicModel> list, OnListClick tocarMusica){
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
        holder.musica(listMusicas.get(position),tocarMusica, position);
        Log.i("position","position" + position);
    }


    @Override
    public int getItemCount() {
       return this.listMusicas.size();
    }
}
