package com.example.ckpenep.stackoverflow.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.presentation.presenter.AskPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.AskView;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.terrakok.cicerone.Router;

public class AskFragment extends MvpAppCompatFragment implements AskView, BackButtonListener, TextWatcher {

    @Inject
    Router router;

    @InjectPresenter
    AskPresenter presenter;

    @ProvidePresenter
    AskPresenter provideDetailsPresenter() {
        return new AskPresenter(router);
    }

    @BindView(R.id.tags_textview)
    TextView tagsTextView;
    @BindView(R.id.fragment_ask_question_title)
    EditText ask_title;
    @BindView(R.id.fragment_ask_question_body)
    EditText ask_body;

    private Unbinder mUnbinder;
    private MenuItem previewMenu, postMenu;

    public static AskFragment newInstance() {
        AskFragment fragment = new AskFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ask, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tagsTextView.setOnClickListener(v -> presenter.clickTagsButton());

        ask_title.addTextChangedListener(this);
        ask_body.addTextChangedListener(this);
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

    @Override
    public void setMenuVisibility(boolean flag) {
        previewMenu.setEnabled(flag);
        postMenu.setEnabled(flag);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        presenter.textChanged(ask_title.getText().toString(), ask_body.getText().toString());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);

        previewMenu = menu.findItem(R.id.menu_preview);
        postMenu = menu.findItem(R.id.menu_post);
    }
}
