package com.example.ckpenep.stackoverflow.app;

import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionsList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {Integer PAGE_SIZE = 20;

    @GET("search?order=desc&sort=activity&site=stackoverflow")
    Observable<QuestionsList> searchQuestions(
            @Query("page") int page,
            @Query("pagesize") int pageSize,
            @Query("tagged") String order
    );
}
