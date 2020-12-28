package com.magodev.lab.gingado.ui.fragments;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.magodev.lab.gingado.model.ModeloSom;
import com.magodev.lab.gingado.ui.PlayerMusica;
import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.business.RegrasMusica;
import com.magodev.lab.gingado.constants.Constants;
import com.magodev.lab.gingado.interfaces.OnListClick;
import com.magodev.lab.gingado.ui.adapter.RecyclerMusicaAdapter;

import java.io.File;
import java.util.List;


public class MusicasFragment extends Fragment {

    private ViewHolder mViewHolder = new ViewHolder();
    private Intent mPlay;
    private boolean mLimiteMusica;
    private RecyclerMusicaAdapter mAdapter;
    private List<ModeloSom> mMusicas;
    private ServiceConnection mServiceConnection;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MusicasFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_musicas, container, false);

        this.mMusicas = new RegrasMusica(root.getContext()).getList(root.getContext());

        this.mViewHolder.recyclerMusicas = root.findViewById(R.id.recycler_musicas);

        OnListClick tocarMusica = new OnListClick() {
            @Override
            public void passarMusicaEntreActivities(String musica) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.BUNDLE.PASSAR_DADOS,musica);
                Intent intent = new Intent(getContext(), PlayerMusica.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        };


        this.mViewHolder.recyclerMusicas.setLayoutManager(new LinearLayoutManager(root.getContext()));
        this.mViewHolder.recyclerMusicas.setAdapter(new RecyclerMusicaAdapter(this.mMusicas, tocarMusica));

        return root;
    }


    private static class ViewHolder {
        RecyclerView recyclerMusicas;
    }
}