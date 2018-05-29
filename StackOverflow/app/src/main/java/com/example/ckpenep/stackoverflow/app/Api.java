package com.example.ckpenep.stackoverflow.app;

import com.example.ckpenep.stackoverflow.model.dto.answer.AnswersList;
import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionsList;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Api {
    Integer PAGE_SIZE = 10;

    @GET("questions?order=desc&site=stackoverflow&filter=!)rwibkp50r6joDt8-ZS7")
    Observable<QuestionsList> searchQuestions(@QueryMap Map<String, String> params);

    @GET("answers/{ids}?order=asc&sort=creation&site=stackoverflow&filter=!)rFTNQds3DAgWJzc(*De")
    Observable<AnswersList> getAnswersByIds(@Path("ids") String ids);
}
