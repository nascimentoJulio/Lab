package com.magodev.lab.gingado.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.business.RegrasMusica;
import com.magodev.lab.gingado.constants.Constants;
import com.magodev.lab.gingado.interfaces.OnListClick;
import com.magodev.lab.gingado.model.MusicModel;
import com.magodev.lab.gingado.ui.PlayerMusicActivity;
import com.magodev.lab.gingado.ui.adapter.RecyclerMusicAdapter;

import java.util.ArrayList;


public class MusicsFragment extends Fragment {

    private final ViewHolder mViewHolder = new ViewHolder();
    private ArrayList<MusicModel> mMusics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_musicas, container, false);

        this.mMusics = (ArrayList<MusicModel>) new RegrasMusica().getList(root.getContext());

        this.mViewHolder.recyclerMusics = root.findViewById(R.id.recycler_musicas);

        OnListClick listClick = new OnListClick() {
            @Override
            public void passMusicBetweenActivities(MusicModel music, int position) {
                Intent intent = new Intent(getContext(), PlayerMusicActivity.class);
                intent.putExtra(Constants.BUNDLE.LIST_MUSIC_KEY, mMusics);
                intent.putExtra(Constants.BUNDLE.POSITION_MUSIC_KEY, position);
                intent.putExtra(Constants.BUNDLE.MUSIC_KEY, music);
                root.getContext().startActivity(intent);
            }
        };

        this.mViewHolder.recyclerMusics.setLayoutManager(new LinearLayoutManager(root.getContext()));
        this.mViewHolder.recyclerMusics.setAdapter(new RecyclerMusicAdapter(this.mMusics, listClick));

        return root;
    }

    private static class ViewHolder {
        RecyclerView recyclerMusics;
    }
}