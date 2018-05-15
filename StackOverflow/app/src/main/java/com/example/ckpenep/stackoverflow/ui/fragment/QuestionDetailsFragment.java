package com.example.ckpenep.stackoverflow.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.common.RouterProvider;
import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.presentation.presenter.QuestionDetailsPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.QuestionDetailsView;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;
import com.example.ckpenep.stackoverflow.utils.FrameSwipeRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuestionDetailsFragment extends MvpAppCompatFragment implements QuestionDetailsView, BackButtonListener {

    private Unbinder mUnbinder;

    private Toolbar mToolbar;

    @InjectPresenter
    QuestionDetailsPresenter presenter;

    @BindView(R.id.swipe_refresh_layout)
    FrameSwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.question_details_list)
    RecyclerView mRecyclerView;

    public static QuestionDetailsFragment newInstance(Question question) {
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("QUESTION", question);
        fragment.setArguments(args);
        return fragment;
    }

    @ProvidePresenter
    QuestionDetailsPresenter providePlacePresenter() {
        Question mQuestion = getArguments().getParcelable("QUESTION");
        return new QuestionDetailsPresenter(((RouterProvider) getParentFragment()).getRouter(), mQuestion);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_question_details, container, false);
        mUnbinder = ButterKnife.bind(this, v);

        //tollbar
        mToolbar = (Toolbar) getActivity().findViewById(R.id.activity_main_toolbar);
       ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBackPressed();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList();

        // SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(() ->
        {

        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
    }

    private void initList() {

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
}