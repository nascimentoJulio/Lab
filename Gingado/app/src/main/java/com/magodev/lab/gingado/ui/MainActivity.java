package com.magodev.lab.gingado.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.magodev.lab.gingado.R;
import com.magodev.lab.gingado.ui.fragments.MusicasFragment;
import com.magodev.lab.gingado.ui.fragments.PlaylistFragment;
import com.magodev.lab.gingado.ui.fragments.RadioFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {
    private final ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        this.mViewHolder.mSmartTabLayout = findViewById(R.id.smart_tab_layout);
        this.mViewHolder.mViewPager = findViewById(R.id.view_pager);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                    FragmentPagerItems.with(this)
                            .add(getString(R.string.musicas), MusicasFragment.class)
                            .add(getString(R.string.playlist), PlaylistFragment.class)
                            .add(getString(R.string.radio), RadioFragment.class)
                            .create()

            );
            this.mViewHolder.mViewPager.setAdapter(adapter);
            this.mViewHolder.mSmartTabLayout.setViewPager(this.mViewHolder.mViewPager);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        0);

            }
            FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                    FragmentPagerItems.with(this)
                            .add(getString(R.string.musicas), MusicasFragment.class)
                            .add(getString(R.string.playlist), PlaylistFragment.class)
                            .add(getString(R.string.radio), RadioFragment.class)
                            .create());
        }

    }


    private static class ViewHolder {
        private SmartTabLayout mSmartTabLayout;
        private ViewPager mViewPager;
    }
}