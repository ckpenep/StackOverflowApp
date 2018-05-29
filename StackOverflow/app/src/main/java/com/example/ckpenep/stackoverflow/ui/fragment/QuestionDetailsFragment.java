package com.example.ckpenep.stackoverflow.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.common.RouterProvider;
import com.example.ckpenep.stackoverflow.model.question.Question;
import com.example.ckpenep.stackoverflow.presentation.presenter.QuestionDetailsPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.QuestionDetailsView;
import com.example.ckpenep.stackoverflow.ui.adapters.QuestionDetailsAdapter;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;
import com.example.ckpenep.stackoverflow.utils.FrameSwipeRefreshLayout;

import java.util.List;

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

    private QuestionDetailsAdapter mAdapter;
    private LinearLayoutManager layoutManager;

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

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
    }

    private void initList() {
        mAdapter = new QuestionDetailsAdapter();
        //mAdapter.setOnItemClickListener(this);
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.getRecycledViewPool().setMaxRecycledViews(DetailsRowType.QUESTION_ROW_TYPE, 1);
        mRecyclerView.getRecycledViewPool().setMaxRecycledViews(DetailsRowType.HEADER_ROW_TYPE, 1);

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                // TODO Auto-generated method stub
                //super.onScrollStateChanged(recyclerView, newState);
                try {
                    RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
                    int firstPos = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

                    Log.d("FIRST_POS", Integer.toString(firstPos));
                    if (firstPos != 0) {
                        mSwipeRefreshLayout.setEnabled(false);
                    } else {
                        mSwipeRefreshLayout.setEnabled(true);
                        if (mRecyclerView.getScrollState() == 1)
                            if (mSwipeRefreshLayout.isRefreshing())
                                mRecyclerView.stopScroll();
                    }

                } catch (Exception e) {
                    //Log.e(TAG, "Scroll Error : " + e.getLocalizedMessage());
                }
            }
        });
    }

    @Override
    public void showResult(List<DetailsRowType> result) {
        mAdapter.updateDetails(result);
    }

    @Override
    public void showError(String error) {
        Snackbar
                .make(getView(), "No network connection.",Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onStart() {
        super.onStart();

//        mSwipeRefreshLayout.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                if (mWebView.getScrollY() == 0)
//                    mSwipeRefreshLayout.setEnabled(true);
//                else
//                    mSwipeRefreshLayout.setEnabled(false);
//            }
//        });
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
