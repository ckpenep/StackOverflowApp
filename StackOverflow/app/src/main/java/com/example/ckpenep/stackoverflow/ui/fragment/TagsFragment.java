package com.example.ckpenep.stackoverflow.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.presentation.presenter.TagsPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.TagsView;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.terrakok.cicerone.Router;

public class TagsFragment extends MvpAppCompatFragment implements TagsView, BackButtonListener {

    @Inject
    Router router;

    @InjectPresenter
    TagsPresenter presenter;

    private Unbinder mUnbinder;
    private Toolbar toolbar;
    private MenuItem previewMenu, postMenu;

    public static TagsFragment newInstance() {
        TagsFragment fragment = new TagsFragment();
        return fragment;
    }

    @ProvidePresenter
    TagsPresenter providePlacePresenter() {
        return new TagsPresenter(router);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_tags, container, false);
        mUnbinder = ButterKnife.bind(this, v);

        //tollbar
        toolbar = (Toolbar) getActivity().findViewById(R.id.activity_ask_toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(k -> presenter.onBackPressed());

        return v;
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public boolean onBackPressed() {
        presenter.onBackPressed();
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem register = menu.findItem(R.id.menu_preview);
        register.setVisible(false);
        MenuItem post = menu.findItem(R.id.menu_post);
        post.setVisible(false);
    }
}
