package com.example.ckpenep.stackoverflow.ui.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.ckpenep.stackoverflow.R;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.model.tag.Tag;
import com.example.ckpenep.stackoverflow.presentation.presenter.TagsPresenter;
import com.example.ckpenep.stackoverflow.presentation.view.TagsView;
import com.example.ckpenep.stackoverflow.ui.common.BackButtonListener;
import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.ChipInterface;
import android.content.DialogInterface.OnCancelListener;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.terrakok.cicerone.Router;

public class TagsFragment extends MvpAppCompatFragment implements TagsView, BackButtonListener {

    @Inject
    Router router;

    @InjectPresenter
    TagsPresenter presenter;

    private ProgressDialog progressDialog;
    private ChipsInput mChipsInput;
    private Unbinder mUnbinder;
    private Toolbar toolbar;
    private EditText editText;

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

        showProgressDialog();
    }

    private void showProgressDialog() {
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

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_tags, container, false);
        mUnbinder = ButterKnife.bind(this, v);

        editText = (EditText)v.findViewById(R.id.tags_search_edittext);
        mChipsInput = (ChipsInput) v.findViewById(R.id.chips_input);

        iniComponent();

        return v;
    }

    private void iniComponent()
    {
        //tollbar
        toolbar = (Toolbar) getActivity().findViewById(R.id.activity_ask_toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(k -> presenter.onBackPressed());

        // chips listener
        mChipsInput.addChipsListener(new ChipsInput.ChipsListener() {
            @Override
            public void onChipAdded(ChipInterface chip, int newSize) {

            }

            @Override
            public void onChipRemoved(ChipInterface chip, int newSize) {

            }

            @Override
            public void onTextChanged(CharSequence text) {
                //Log.e(TAG, "text changed: " + text.toString());
            }
        });

        //editText
        Drawable workingDrawable = getResources().getDrawable(R.drawable.ic_magnify);
        Bitmap bitmap = ((BitmapDrawable) workingDrawable).getBitmap();
        Drawable draw = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
        editText.setCompoundDrawablesWithIntrinsicBounds(draw, null, null, null);
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

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showListProgress() {

    }

    @Override
    public void hideListProgress() {

    }

    @Override
    public void setTags(List<Tag> tags) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
