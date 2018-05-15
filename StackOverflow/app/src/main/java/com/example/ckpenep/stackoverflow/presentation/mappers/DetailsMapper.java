package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.model.dto.datails.QuestionDetail;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class DetailsMapper implements Function<Question, DetailsRowType> {

    @Inject
    public DetailsMapper() {

    }

    @Override
    public DetailsRowType apply(Question question) {
        return Observable.just(question)
                .map(dQuestion -> new QuestionDetail(dQuestion.getId(), dQuestion.getAnswered(), dQuestion.getViewCount(), dQuestion.getAnswerCount(), dQuestion.getScore(),
                        dQuestion.getLastActivityDate(), dQuestion.getCreationDate(), dQuestion.getLink(), dQuestion.getTitle(), dQuestion.getLastEditDate(),
                        dQuestion.getAcceptedAnswerId(), dQuestion.getProtectedDate(), dQuestion.getTags(), dQuestion.getBodyMarkdown(), dQuestion.getBody(), dQuestion.getOwner()))
                .blockingFirst();
    }
}
