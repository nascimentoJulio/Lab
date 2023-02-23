package com.magodev.lab.gingado.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    private static final int WRITE_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        this.mViewHolder.smartTabLayout = findViewById(R.id.smart_tab_layout);
        this.mViewHolder.viewPager = findViewById(R.id.view_pager);
        this.mViewHolder.textPermissionDenied = findViewById(R.id.text_permission_denied);
        this.mViewHolder.accessDeniedDraw = findViewById(R.id.access_denied_draw);
        this.mViewHolder.givePermissionButton = findViewById(R.id.button_give_permission);

        hidePermissionLayout();

        boolean userGrantedPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        if (userGrantedPermission) {
            buildInitialScreen();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        WRITE_REQUEST_CODE);
            }
        }

        this.mViewHolder.givePermissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            WRITE_REQUEST_CODE);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_REQUEST_CODE) {
            boolean permissionIsGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (permissionIsGranted) {
                this.mViewHolder.viewPager.setVisibility(View.VISIBLE);
                this.mViewHolder.smartTabLayout.setVisibility(View.VISIBLE);
                hidePermissionLayout();
                buildInitialScreen();
            } else {
                this.mViewHolder.viewPager.setVisibility(View.GONE);
                this.mViewHolder.smartTabLayout.setVisibility(View.GONE);
                this.mViewHolder.textPermissionDenied.setVisibility(View.VISIBLE);
                this.mViewHolder.givePermissionButton.setVisibility(View.VISIBLE);
                this.mViewHolder.accessDeniedDraw.setVisibility(View.VISIBLE);
            }
        }
    }

    private void hidePermissionLayout() {
        this.mViewHolder.textPermissionDenied.setVisibility(View.GONE);
        this.mViewHolder.givePermissionButton.setVisibility(View.GONE);
        this.mViewHolder.accessDeniedDraw.setVisibility(View.GONE);
    }

    private void buildInitialScreen() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add(getString(R.string.musicas), MusicasFragment.class)
                        .add(getString(R.string.playlist), PlaylistFragment.class)
                        .add(getString(R.string.radio), RadioFragment.class)
                        .create()

        );
        this.mViewHolder.viewPager.setAdapter(adapter);
        this.mViewHolder.smartTabLayout.setViewPager(this.mViewHolder.viewPager);
    }

    private static class ViewHolder {
        private SmartTabLayout smartTabLayout;
        private ViewPager viewPager;

        private TextView textPermissionDenied;

        private ImageView accessDeniedDraw;

        private Button givePermissionButton;
    }
}