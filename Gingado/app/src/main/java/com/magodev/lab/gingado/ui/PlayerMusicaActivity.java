package com.magodev.lab.gingado.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.constants.Constants;
import com.magodev.lab.gingado.model.MusicModel;
import com.magodev.lab.gingado.services.ServiceMusicas;
import com.magodev.lab.gingado.utils.MusicFormatter;

import java.util.ArrayList;

public class PlayerMusicaActivity extends AppCompatActivity implements View.OnClickListener {

    private final ViewHolder mViewholder = new ViewHolder();

    private int mPosition = 0;

    private final int FIRST_POSITION = 0;

    private ArrayList<MusicModel> musics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_musica);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mapperUIObjects();

        this.mViewholder.startButton.setVisibility(View.GONE);

        rescueBundleValues();

        this.mViewholder.startButton.setOnClickListener(this);
        this.mViewholder.pauseButton.setOnClickListener(this);
        this.mViewholder.nextButton.setOnClickListener(this);
        this.mViewholder.previousButton.setOnClickListener(this);
    }

    private void mapperUIObjects() {
        this.mViewholder.textTempoAtual = findViewById(R.id.text_tempo_atual);
        this.mViewholder.textTempoTotal = findViewById(R.id.text_tempo_total);
        this.mViewholder.textNomeMusica = findViewById(R.id.text_nome_musica);
        this.mViewholder.albumImage = findViewById(R.id.imagem_album);
        this.mViewholder.startButton = findViewById(R.id.button_start);
        this.mViewholder.pauseButton = findViewById(R.id.button_pause);
        this.mViewholder.nextButton = findViewById(R.id.button_next);
        this.mViewholder.previousButton = findViewById(R.id.button_previous);
        this.mViewholder.musicProgress = findViewById(R.id.music_progress);
    }

    public void iniciarServiceMusica(String path) {
        Bundle bundleService = new Bundle();
        bundleService.putString(Constants.BUNDLE.SERVICE_MUSIC, path);
        Intent intentService = new Intent(this, ServiceMusicas.class);
        intentService.putExtras(bundleService);
        this.startService(intentService);
    }

    private void rescueBundleValues() {
        Bundle bundle = getIntent().getExtras();
        MusicModel music = (MusicModel) bundle.getSerializable(Constants.BUNDLE.MUSIC_KEY);
        updateMusicDetails(music);
        if (getIntent().getSerializableExtra(Constants.BUNDLE.LIST_MUSIC_KEY) != null) {
            musics = (ArrayList<MusicModel>) getIntent().getSerializableExtra(Constants.BUNDLE.LIST_MUSIC_KEY);
        }

        this.mPosition = bundle.getInt(Constants.BUNDLE.POSITION_MUSIC_KEY);
    }

    private void updateMusicDetails(MusicModel musicModel) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(musicModel.getPath());

        byte[] biteImage = retriever.getEmbeddedPicture();
        if (biteImage != null) {
            Bitmap image = BitmapFactory.decodeByteArray(biteImage, 0, biteImage.length);

            this.mViewholder.albumImage.setImageBitmap(image);
        } else {
            this.mViewholder.albumImage.setImageResource(R.drawable.defaultalbumartwork);
        }

        int duration = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));

        this.mViewholder.textNomeMusica.setText(musicModel.getTitulo());

        this.mViewholder.textTempoTotal.setText(MusicFormatter.FormattedMusicDuration(duration));

        final Handler handler = new Handler();
        this.mViewholder.musicProgress.setMax(MusicFormatter.getTotalSecondsFromMilliseconds(duration));
        iniciarServiceMusica(musicModel.getPath());

        PlayerMusicaActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int progress = ServiceMusicas.getCurrentMusicPosition();
                mViewholder.musicProgress.setProgress(progress);
                mViewholder.textTempoAtual.setText(MusicFormatter.FormattedMusicProgress(progress));
                handler.postDelayed(this, Constants.SECONDS.ONE_SECOND);
            }
        });
    }

    private void playNextMusic(ArrayList<MusicModel> musicModels) {
        boolean isLastPosition = mPosition + 1 == musicModels.size();
        mPosition = isLastPosition ? FIRST_POSITION : mPosition + 1;
        MusicModel musicModel = musicModels.get(mPosition);
        ServiceMusicas.onTocarProxima(musicModel.getPath());
        updateMusicDetails(musicModel);
    }

    private void playPreviousMusic(ArrayList<MusicModel> musicModels) {
        boolean isFirstPosition = mPosition - 1 < FIRST_POSITION;
        mPosition = isFirstPosition ? FIRST_POSITION : mPosition - 1;
        MusicModel musicModel = musicModels.get(mPosition);
        ServiceMusicas.onTocarProxima(musicModel.getPath());
        updateMusicDetails(musicModel);
    }

    private void startMusic() {
        ServiceMusicas.onStartMusic();
        mViewholder.startButton.setVisibility(View.GONE);
        mViewholder.pauseButton.setVisibility(View.VISIBLE);
    }

    private void pauseMusic() {
        ServiceMusicas.onPauseMusic();
        mViewholder.startButton.setVisibility(View.VISIBLE);
        mViewholder.pauseButton.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_start) {
            startMusic();
        } else if (view.getId() == R.id.button_pause) {
            pauseMusic();
        } else if (view.getId() == R.id.button_next) {
            playNextMusic(musics);
        } else if (view.getId() == R.id.button_previous) {
            playPreviousMusic(musics);
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

        SeekBar musicProgress;
    }
}
