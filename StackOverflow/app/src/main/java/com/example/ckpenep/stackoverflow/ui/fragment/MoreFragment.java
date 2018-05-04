package com.example.ckpenep.stackoverflow.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.common.RouterProvider;
import com.example.ckpenep.stackoverflow.presentation.presenter.MorePresenter;
import com.example.ckpenep.stackoverflow.presentation.view.MoreView;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoreFragment extends MvpAppCompatFragment implements MoreView, BackButtonListener {

    @InjectPresenter
    MorePresenter presenter;

    @ProvidePresenter
    MorePresenter provideDetailsPresenter() {
        return new MorePresenter(((RouterProvider) getParentFragment()).getRouter());
    }

    @BindView(R.id.sign_in)
    Button signInButton;
    @BindView(R.id.ask_question_button)
    Button askQuestionsButton;
    @BindView(R.id.drafts_button)
    Button draftButton;
    @BindView(R.id.favorite_button)
    Button favoriteButton;
    @BindView(R.id.history_button)
    Button historyButton;

    private Unbinder mUnbinder;

    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        historyButton.setOnClickListener(v -> presenter.clickHistoryButton());
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
