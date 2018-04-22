package com.example.ckpenep.stackoverflow.model;

import com.example.ckpenep.stackoverflow.app.Api;
import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionsList;

import io.reactivex.Observable;

public class StackoverflowService {
    private Api api;

    public StackoverflowService(Api mApi) {
        api = mApi;
    }

    public Observable<QuestionsList> getUserRepos(int page, int pagesize, String order) {
        return api.searchQuestions(page, pagesize, order);
    }
}
