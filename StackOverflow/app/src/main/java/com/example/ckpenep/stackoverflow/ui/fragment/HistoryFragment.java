package com.example.ckpenep.stackoverflow.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.common.RouterProvider;
import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.presentation.presenter.HistoryPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.HistoryView;
import com.example.ckpenep.stackoverflow.ui.adapters.HistoryQuestionsAdapter;
import com.example.ckpenep.stackoverflow.ui.adapters.QuestionListAdapter;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryFragment extends MvpAppCompatFragment implements HistoryView, BackButtonListener, QuestionListAdapter.OnItemClickListener {

    @InjectPresenter
    HistoryPresenter presenter;

    private Unbinder mUnbinder;

    private Toolbar toolbar;
    @BindView(R.id.holder_textView)
    TextView holderTextView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.questions_list)
    RecyclerView mRecyclerView;

    private HistoryQuestionsAdapter mAdapter;
    private LinearLayoutManager layoutManager;

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @ProvidePresenter
    HistoryPresenter providePlacePresenter() {
        return new HistoryPresenter(((RouterProvider) getParentFragment()).getRouter());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_history, container, false);
        mUnbinder = ButterKnife.bind(this, v);

        //tollbar
        toolbar = (Toolbar) getActivity().findViewById(R.id.activity_main_toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(k -> presenter.onBackPressed());

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList();
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

    private void initList() {
        mAdapter = new HistoryQuestionsAdapter();
        mAdapter.setOnItemClickListener(this);
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
    }

    @Override
    public void onItemClick(Question question) {
        presenter.clickItem(question);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showResultsItemList(List<Question> questionsList) {
        mAdapter.updateContacts(questionsList);
    }
}
