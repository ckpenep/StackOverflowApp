package com.example.ckpenep.stackoverflow.ui.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.model.tag.Tag;
import com.example.ckpenep.stackoverflow.presentation.presenter.TagsPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.TagsView;
import com.example.ckpenep.stackoverflow.ui.adapters.ChipAdapter;
import com.example.ckpenep.stackoverflow.ui.adapters.TagsAdapter;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.terrakok.cicerone.Router;

public class TagsFragment extends MvpAppCompatFragment implements TagsView, BackButtonListener, TagsAdapter.OnItemClickListener, ChipAdapter.OnChipsClickItem {

    @Inject
    Router router;

    @InjectPresenter
    TagsPresenter presenter;

    private Unbinder mUnbinder;

    private Toolbar toolbar;
    private ProgressDialog progressDialog;

    @BindView(R.id.exist_tags_textview)
    TextView existTags;
    @BindView(R.id.search_tags_edittext)
    EditText searchEditText;
    @BindView(R.id.tags_recyclerview)
    RecyclerView tagsRecyclerView;
    @BindView(R.id.chips_recyclerview)
    RecyclerView chipsRecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar loadingTagsProgressBar;

    private TagsAdapter mAdapter;
    private LinearLayoutManager layoutManager;

    private ChipAdapter mChipAdapter;
    private FlexboxLayoutManager mFlexboxLayoutManager;

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

        initProgressDialog();
        iniComponent();

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.main_dialog_progress_title));
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
                presenter.onBackPressed();
            }
        });

        progressDialog.show();
    }

    private void initList() {
        mAdapter = new TagsAdapter();
        mAdapter.setOnItemClickListener(this);

        layoutManager = new LinearLayoutManager(getContext());
        tagsRecyclerView.setHasFixedSize(true);
        tagsRecyclerView.setLayoutManager(layoutManager);
        tagsRecyclerView.setAdapter(mAdapter);
        tagsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));

        mChipAdapter = new ChipAdapter();
        mChipAdapter.setOnItemClickListener(this);

        mFlexboxLayoutManager = new FlexboxLayoutManager(getContext());
        mFlexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        mFlexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);

        chipsRecyclerView.setLayoutManager(mFlexboxLayoutManager);
        chipsRecyclerView.setAdapter(mChipAdapter);
    }

    private void iniComponent() {
        //tollbar
        toolbar = (Toolbar) getActivity().findViewById(R.id.activity_ask_toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(k -> presenter.onBackPressed());

        //searchEditText
        Drawable workingDrawable = getResources().getDrawable(R.drawable.ic_magnify);
        Bitmap bitmap = ((BitmapDrawable) workingDrawable).getBitmap();
        Drawable draw = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
        searchEditText.setCompoundDrawablesWithIntrinsicBounds(draw, null, null, null);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

            @Override
            public void afterTextChanged(Editable arg0) {}
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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
    public void onChipsClick(int position) {
        presenter.deleteItemChips(position);

        if (presenter.countSelectedTags() == 0) {
            existTags.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTagClick(Tag tag) {
        if (presenter.countSelectedTags() < 5) {
            if(!presenter.isExistTag(tag)) presenter.addItemChips(tag);
        } else {
            Toast.makeText(getContext(), "Sorry, maximum five tags", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showListProgress() {
        tagsRecyclerView.setVisibility(View.GONE);
        loadingTagsProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListProgress() {
        loadingTagsProgressBar.setVisibility(View.GONE);
        tagsRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setTags(List<Tag> tags) {
        if (mAdapter != null && tags != null && tags.size() > 0) {
            mAdapter.setData(tags);
        }
    }

    @Override
    public void setChips(List<String> names) {
        existTags.setVisibility(View.GONE);
        mChipAdapter.setData(names);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
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
