package com.example.ckpenep.stackoverflow.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.common.RouterProvider;
import com.example.ckpenep.stackoverflow.presentation.presenter.AskPresenter;
import com.example.ckpenep.stackoverflow.presentation.presenter.MorePresenter;
import com.example.ckpenep.stackoverflow.presentation.view.AskView;
import com.example.ckpenep.stackoverflow.presentation.view.MoreView;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AskFragment extends MvpAppCompatFragment implements AskView, BackButtonListener {

    @InjectPresenter
    AskPresenter presenter;

    @ProvidePresenter
    AskPresenter provideDetailsPresenter() {
        return new AskPresenter(((RouterProvider) getParentFragment()).getRouter());
    }

    private Unbinder mUnbinder;

    public static AskFragment newInstance() {
        AskFragment fragment = new AskFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ask, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }
}
