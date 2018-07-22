package com.example.ckpenep.stackoverflow.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.common.RouterProvider;
import com.example.ckpenep.stackoverflow.model.question.Question;
import com.example.ckpenep.stackoverflow.model.system.ResourceManager;
import com.example.ckpenep.stackoverflow.presentation.presenter.QuestionPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.QuestionView;
import com.example.ckpenep.stackoverflow.ui.adapters.QuestionListAdapter;
import com.example.ckpenep.stackoverflow.utils.FrameSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class QuestionsListFragment extends MvpAppCompatFragment implements QuestionView, QuestionListAdapter.OnItemClickListener {

    Map<String, String> sortList = new HashMap<String, String>() {{
        put("Active", "activity");
        put("Votes", "votes");
        put("Newest", "creation");

    }};

    @Inject
    ResourceManager mResourceManager;

    @InjectPresenter
    QuestionPresenter mPresenter;

    @ProvidePresenter
    QuestionPresenter providePlacePresenter() {
        return new QuestionPresenter(((RouterProvider) getParentFragment()).getRouter());
    }

    protected View mFooter;
    FrameSwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar progressBar;
    TextView noQuestionsTextView;
    EditText search;
    Button clearButton;
    RecyclerView mRecyclerView;
    FloatingActionButton mFloatingActionButton;
    Spinner mSpinner;
    CoordinatorLayout mCoordinatorLayout;

    private AlertDialog mErrorDialog;
    private LinearLayoutManager layoutManager;
    private QuestionListAdapter mAdapter;

    public static QuestionsListFragment newInstance() {
        QuestionsListFragment fragment = new QuestionsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);

        mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.myCoordinatorLayout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.questions_list_view);
        mSwipeRefreshLayout = (FrameSwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        noQuestionsTextView = (TextView) view.findViewById(R.id.text_view_no_questions);
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        mSpinner = (Spinner) view.findViewById(R.id.categories);
        search = (EditText) view.findViewById(R.id.search);
        clearButton = (Button) view.findViewById(R.id.clearable_button_clear);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initList();
        initSpinner();
        initEditText();

        // SwipeRefreshLayout
        mSwipeRefreshLayout.setOnRefreshListener(() ->
        {
            String searchText = search.getText().toString().replace(' ', '-');
            String sort = sortList.get(mSpinner.getSelectedItem().toString());
            mPresenter.loadRepositories(1, searchText, sort, true);
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        mFooter = getLayoutInflater(savedInstanceState).inflate(R.layout.item_loading, mRecyclerView, false);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPresenter.FABclick();
            }
        });
    }

    @Override
    public void onDestroy() {
        if (mErrorDialog != null && mErrorDialog.isShowing()) {
            mErrorDialog.setOnCancelListener(null);
            mErrorDialog.dismiss();
        }

        super.onDestroy();
    }

    boolean userSelect = false;

    private void initSpinner() {
        List<String> result2 = new ArrayList(sortList.keySet());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, result2);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        mSpinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (userSelect) {
                    // Your selection handling code here
                    userSelect = false;

                    String searchText = search.getText().toString().replace(' ', '-');
                    String sort = sortList.get(parent.getItemAtPosition(position).toString());
                    mPresenter.loadRepositories(1, searchText, sort, true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        mSpinner.setOnItemSelectedListener(itemSelectedListener);

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                userSelect = true;
                return false;
            }
        };
        mSpinner.setOnTouchListener(touchListener);
    }

    private void initList() {
        mAdapter = new QuestionListAdapter();
        mAdapter.setOnItemClickListener(this);
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 && mFloatingActionButton.getVisibility() == View.VISIBLE) {
                    mPresenter.hideFloatingActionButton();
                } else if (dy < 0 && mFloatingActionButton.getVisibility() != View.VISIBLE) {
                    mPresenter.showFloatingActionButton();
                }
            }
        });
    }

    private void initEditText() {
        search.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.length() > 0)
                    clearButton.setVisibility(RelativeLayout.VISIBLE);
                else
                    clearButton.setVisibility(RelativeLayout.INVISIBLE);
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    String searchText = search.getText().toString().replace(' ', '-');
                    String sort = sortList.get(mSpinner.getSelectedItem().toString());
                    if (!searchText.isEmpty()) {
                        mPresenter.loadRepositories(1, searchText, sort, true);
                        View view = getActivity().getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onItemClick(Question question) {
        mPresenter.clickItem(question);
    }

    @Override
    public void hideFloatingActionButton() {
        mFloatingActionButton.hide();
    }

    @Override
    public void showFloatingActionButton() {
        mFloatingActionButton.show();
    }

    @Override
    public void showRefreshing() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideRefreshing() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void showListProgress() {
        mRecyclerView.setVisibility(View.GONE);
        noQuestionsTextView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
        mRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void refreshRepositories(List<Question> questions, boolean maybeMore) {
        mAdapter.refreshAll(questions);
        if (mAdapter.getFootersCount() == 0 && maybeMore)
            mAdapter.addFooter(mFooter);
    }

    @Override
    public void addRepositories(List<Question> questions, boolean maybeMore) {
        mAdapter.addAll(questions);
        if (mAdapter.getFootersCount() == 0 && maybeMore)
            mAdapter.addFooter(mFooter);
    }

    @Override
    public void activateLastItemViewListener() {
        enableSearchOnFinish();
    }

    @Override
    public void disableLastItemViewListener() {
        disableSearchOnFinish();
    }

    private void enableSearchOnFinish() {
        mRecyclerView.addOnScrollListener(new FinishScrollListener());
    }

    private void disableSearchOnFinish() {
        mRecyclerView.clearOnScrollListeners();
    }

    private class FinishScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition() + 1;
            int modelsCount = mAdapter.getItemCount();

            if (lastVisibleItemPosition == modelsCount && modelsCount > 10) {
                String searchText = search.getText().toString().replace(' ', '-');
                String sort = sortList.get(mSpinner.getSelectedItem().toString());
                mPresenter.loadNextRepositories(layoutManager.getItemCount(), searchText, sort);
            }
        }
    }

    @Override
    public void hideProgressBar() {
        new Handler().postDelayed(() ->
                mAdapter.removeFooter(mFooter), 10);
    }

    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG)
                .setAction(mResourceManager.getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String searchText = search.getText().toString().replace(' ', '-');
                        String sort = sortList.get(mSpinner.getSelectedItem().toString());
                        mPresenter.loadRepositories(1, searchText, sort, true);
                    }
                });
        snackbar.show();

//        mErrorDialog = new AlertDialog.Builder(getContext())
//                .setTitle(R.string.app_name)
//                .setMessage(message)
//                .setOnCancelListener(dialog -> mPresenter.onErrorCancel())
//                .show();
    }

    @Override
    public void hideError() {
        if (mErrorDialog != null && mErrorDialog.isShowing()) {
            mErrorDialog.hide();
        }
    }


}
