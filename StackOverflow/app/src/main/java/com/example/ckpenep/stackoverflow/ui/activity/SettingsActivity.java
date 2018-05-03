package com.example.ckpenep.stackoverflow.ui.activity;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.presentation.view.SettingsView;

import butterknife.ButterKnife;

public class SettingsActivity extends MvpAppCompatActivity implements SettingsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }
}
