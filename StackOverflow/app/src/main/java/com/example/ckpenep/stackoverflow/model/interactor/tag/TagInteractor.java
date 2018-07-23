package com.example.ckpenep.stackoverflow.model.interactor.tag;

import com.example.ckpenep.stackoverflow.app.App;
import com.example.ckpenep.stackoverflow.model.StackoverflowService;
import com.example.ckpenep.stackoverflow.model.dto.tag.TagsList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TagInteractor {
    @Inject
    StackoverflowService mStackoverflowService;

    public TagInteractor() {
        App.getAppComponent().inject(this);
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
}
