package com.example.ckpenep.stackoverflow.model.interactor.tag;

import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.model.StackoverflowService;
import com.example.ckpenep.stackoverflow.model.dto.tag.TagsList;
import com.example.ckpenep.stackoverflow.model.tag.Tag;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TagInteractor {

    @Inject
    StackoverflowService mStackoverflowService;

    private List<Tag> selectedTags;
    private List<String> selectedChips;

    public TagInteractor() {
        App.getAppComponent().inject(this);
        selectedChips = new ArrayList<>();
        selectedTags = new ArrayList<>();
    }

    public Observable<TagsList> getTagList(String tagName)
    {
        if(tagName == null || tagName.isEmpty())
        {
            return mStackoverflowService.getTagList();
        }
        else {
            return mStackoverflowService.getTagListByName(tagName);
        }
    }

    public List<String> getSelectedChips()
    {
        return selectedChips;
    }

    public void setSelectedChips(List<String> selectedChips)
    {
        this.selectedChips.addAll(selectedChips);
    }

    public void clearSelectedChips()
    {
        this.selectedChips.clear();
    }

    public List<Tag> getSelectedTags()
    {
        return selectedTags;
    }

    public void setSelectedTags(List<Tag> selectedTags)
    {
        this.selectedTags.addAll(selectedTags);
    }

    public void clearSelectedTags()
    {
        this.selectedTags.clear();
    }
}
