package com.example.ckpenep.stackoverflow.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.common.Utils;
import com.example.ckpenep.stackoverflow.error.handler.ErrorHandler;
import com.example.ckpenep.stackoverflow.model.dto.tag.TagItem;
import com.example.ckpenep.stackoverflow.model.dto.tag.TagsList;
import com.example.ckpenep.stackoverflow.model.interactor.tag.TagInteractor;
import com.example.ckpenep.stackoverflow.model.tag.Tag;
import com.example.ckpenep.stackoverflow.presentation.mappers.TagMapper;
import com.example.ckpenep.stackoverflow.presentation.view.TagsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class TagsPresenter extends MvpPresenter<TagsView> {

    @Inject
    TagInteractor mTagInteractor;
    @Inject
    ErrorHandler mErrorHandler;

    private Router router;
    private Disposable subscription = Disposables.empty();

    private boolean mIsInLoading;

    private List<Tag> selectedTags;
    private List<String> selectedChipsName;

    public TagsPresenter(Router router) {
        App.getAppComponent().inject(this);
        this.router = router;
        this.selectedTags = new ArrayList<>();
        this.selectedChipsName = new ArrayList<>();
    }

    @Override
    public void onDestroy() {
        if (!subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    @Override
    public void attachView(TagsView view) {
        super.attachView(view);
        mErrorHandler.attachView(view);
    }

    @Override
    public void detachView(TagsView view) {
        super.detachView(view);
        mErrorHandler.detachView();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadTags(null);
    }

    public void loadTagsByName(String tagName)
    {
        getViewState().showListProgress();
    }

    private void loadTags(String tagName) {
        if (mIsInLoading) {
            return;
        }

        mIsInLoading = true;

        final Observable<TagsList> observable = mTagInteractor.getTagList(tagName);

        if (!subscription.isDisposed()) {
            subscription.dispose();
        }

        subscription = observable
                .compose(Utils.applySchedulers())
                .subscribe(questions -> {
                    onLoadingFinish();
                    onLoadingSuccess(questions);
                }, error -> {
                    onLoadingFinish();
                    onLoadingFailed(error);
                });
    }

    private void onLoadingFinish() {
        mIsInLoading = false;
        getViewState().hideProgressDialog();
        getViewState().hideListProgress();
    }

    private void onLoadingSuccess(TagsList tags) {

        List<TagItem> resultsItems = tags.getItems();
        List<Tag> showingsList = TagMapper.fromResultsItemToTasks(resultsItems);

        getViewState().setTags(showingsList);
    }

    private void onLoadingFailed(Throwable error) {
        getViewState().hideListProgress();
        mErrorHandler.handleError(error);
    }

    public int countSelectedTags()
    {
        return selectedTags.size();
    }

    public boolean isExistTag(Tag tag)
    {
        return selectedTags.contains(tag);
    }

    public void addItemChips(Tag item)
    {
        if(selectedTags != null && selectedChipsName != null) {
            selectedTags.add(item);
            selectedChipsName.add(item.getName());
        }
        getViewState().setChips(selectedChipsName);
    }

    public void deleteItemChips(int position)
    {
        if(selectedTags != null && selectedChipsName != null) {
            selectedTags.remove(selectedTags.get(position));
            selectedChipsName.remove(position);
        }
        getViewState().setChips(selectedChipsName);
    }

    public void onBackPressed() {
        router.exit();
    }
}
