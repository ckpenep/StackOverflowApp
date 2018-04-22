package com.example.ckpenep.stackoverflow.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.presentation.presenter.AchievementPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.AchievementView;

public class AchievementFragment extends MvpAppCompatFragment implements AchievementView {

    @InjectPresenter
    AchievementPresenter presenter;

    public static AchievementFragment newInstance() {
        AchievementFragment fragment = new AchievementFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_achievement, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
