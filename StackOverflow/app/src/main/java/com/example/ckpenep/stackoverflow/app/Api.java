package com.example.ckpenep.stackoverflow.app;

import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionsList;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Api {
    Integer PAGE_SIZE = 10;

    @GET("questions?order=desc&site=stackoverflow")
    Observable<QuestionsList> searchQuestions(@QueryMap Map<String, String> params);
}
