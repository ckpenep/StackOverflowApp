package com.example.ckpenep.stackoverflow.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
import com.example.ckpenep.stackoverflow.model.system.ResourceManager;
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
    @Inject
    ResourceManager resourceManager;
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
    private AlertDialog mAlertDialog;

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
    public void showQuestionDialog() {
        AlertDialog.Builder sayWindows = new AlertDialog.Builder(getContext());
        sayWindows.setTitle(resourceManager.getString(R.string.dialog_tag_question));
        sayWindows.setPositiveButton(resourceManager.getString(R.string.start_new_post), null);
        sayWindows.setNegativeButton(resourceManager.getString(R.string.last_draft), null);

        mAlertDialog = sayWindows.create();
        mAlertDialog.setOnShowListener(dialog -> {

            mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(view -> presenter.clickNewPostButton());

            mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(view -> {
                presenter.isShowDialog();
                presenter.getSelectedTags();
            });
        });
        mAlertDialog.setCanceledOnTouchOutside(false); //ToDO: ??
        mAlertDialog.setOnKeyListener((arg0, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                mAlertDialog.dismiss();
                presenter.clickNewPostButton();
            }
            return true;
        });
        mAlertDialog.show();
    }

    @Override
    public void hideQuestionDialog() {
        if(mAlertDialog != null) mAlertDialog.dismiss();
    }

    @Override
    public void setSelectedTags(String tags) {
        if (tags != null && !tags.isEmpty()) {
            tagsTextView.setText(tags);
        }
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
        if (previewMenu != null && postMenu != null) {
            previewMenu.setEnabled(flag);
            postMenu.setEnabled(flag);
        }
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

        presenter.textChanged(ask_title.getText().toString(), ask_body.getText().toString());
    }
}
