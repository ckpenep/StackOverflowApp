package com.example.ckpenep.stackoverflow.model;

import com.example.ckpenep.stackoverflow.app.Api;
import com.example.ckpenep.stackoverflow.model.dto.answer.AnswersList;
import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionsList;

import java.util.Map;

import io.reactivex.Observable;

public class StackoverflowService {
    private Api api;

    public StackoverflowService(Api mApi) {
        api = mApi;
    }

    public Observable<QuestionsList> getAllQuestions(Map<String, String> params) {
        return api.searchQuestions(params);
    }

    public Observable<AnswersList> getAnswersByIds(String ids) {
        return api.getAnswersByIds(ids);
    }
}
