package com.magodev.lab.gingado.ui.fragments;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.business.RegrasMusica;
import com.magodev.lab.gingado.constants.Constants;
import com.magodev.lab.gingado.interfaces.OnListClick;
import com.magodev.lab.gingado.model.ModeloSom;
import com.magodev.lab.gingado.services.ServiceMusicas;
import com.magodev.lab.gingado.ui.PlayerMusicaActivity;
import com.magodev.lab.gingado.ui.adapter.RecyclerMusicaAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MusicasFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private Intent mPlay;
    private boolean mLimiteMusica;
    private RecyclerMusicaAdapter mAdapter;
    private ArrayList<ModeloSom> mMusicas;
    private ServiceConnection mServiceConnection;


    public MusicasFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_musicas, container, false);

        this.mMusicas = (ArrayList<ModeloSom>) new RegrasMusica(root.getContext()).getList(root.getContext());

        this.mViewHolder.recyclerMusicas = root.findViewById(R.id.recycler_musicas);

        OnListClick tocarMusica = new OnListClick() {
            @Override
            public void passarMusicaEntreActivities(ModeloSom musica, int position) {

                Intent abrirActivity = new Intent(getContext(), PlayerMusicaActivity.class);
                Bundle bundle = new Bundle();
                abrirActivity.putExtra("list_musica", mMusicas);
                abrirActivity.putExtra("position", position);
                abrirActivity.putExtra(Constants.BUNDLE.PASSAR_OBJETO, musica);
                root.getContext().startActivity(abrirActivity);

            }

            @Override
            public void iniciarServiceMusica(String path) {

                Bundle bundleService = new Bundle();
                bundleService.putString(Constants.BUNDLE.SERVICE_MUSIC, path);
                Intent intentService = new Intent(getContext(), ServiceMusicas.class);
                intentService.putExtras(bundleService);
                root.getContext().startService(intentService);
            }

            @Override
            public void tocarProxima(int position) {
                if (position + 1 == mMusicas.size()) {
                    ServiceMusicas.onTocarProxima(mMusicas.get(0).getPath());
                } else {
                    ServiceMusicas.onTocarProxima(mMusicas.get(position + 1).getPath());
                }
            }
        };

        this.mViewHolder.recyclerMusicas.setLayoutManager(new

                LinearLayoutManager(root.getContext()));
        this.mViewHolder.recyclerMusicas.setAdapter(new

                RecyclerMusicaAdapter(this.mMusicas, tocarMusica));

        return root;
    }

    private static class ViewHolder {
        RecyclerView recyclerMusicas;
    }
}