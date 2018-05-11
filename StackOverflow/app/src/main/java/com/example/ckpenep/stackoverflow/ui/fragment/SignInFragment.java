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
import com.example.ckpenep.stackoverflow.presentation.presenter.SignInPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.SignInView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SignInFragment extends MvpAppCompatFragment implements SignInView {
    @InjectPresenter
    SignInPresenter presenter;

    @ProvidePresenter
    SignInPresenter provideDetailsPresenter() {
        return new SignInPresenter(((RouterProvider) getParentFragment()).getRouter());
    }

    private Unbinder mUnbinder;

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }
}
