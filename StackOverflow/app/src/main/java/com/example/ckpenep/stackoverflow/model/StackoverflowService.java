package com.example.ckpenep.stackoverflow.model;

import com.example.ckpenep.stackoverflow.app.Api;
import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionsList;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;

import java.util.Map;

import io.reactivex.Observable;

public class StackoverflowService {
    private Api api;

    public StackoverflowService(Api mApi) {
        api = mApi;
    }

    public Observable<QuestionsList> getUserRepos(Map<String, String> params) {
        return api.searchQuestions(params);
    }

    public Observable<DetailsRowType> getAnswersByQuestion(Integer id) {
        return null;
    }
}
