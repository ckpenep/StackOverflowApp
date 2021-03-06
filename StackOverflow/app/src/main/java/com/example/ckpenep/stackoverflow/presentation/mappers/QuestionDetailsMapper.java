package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.question.Question;
import com.example.ckpenep.stackoverflow.model.datails.QuestionDetail;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class QuestionDetailsMapper implements Function<Question, QuestionDetail> {

    @Inject
    public QuestionDetailsMapper() {

    }

    @Override
    public QuestionDetail apply(Question detailsMovie) {
        return Observable.just(detailsMovie)
                .map(dQuestion -> new QuestionDetail(dQuestion.getId(), dQuestion.getAnswered(), dQuestion.getViewCount(), dQuestion.getAnswerCount(), dQuestion.getScore(),
                        dQuestion.getLastActivityDate(), dQuestion.getCreationDate(), dQuestion.getLink(), dQuestion.getTitle(), dQuestion.getLastEditDate(),
                        dQuestion.getAcceptedAnswerId(), dQuestion.getProtectedDate(), dQuestion.getTags(), dQuestion.getBodyMarkdown(), dQuestion.getBody(),
                        OwnerDetailMapper.fromOwnerToOwnerDetails(dQuestion.getOwner()),
                        OwnerDetailMapper.fromOwnerToOwnerDetails(dQuestion.getEditor()),
                        CommentDetailMapper.fromCommentToCommentDetails(dQuestion.getComments()),
                        AnswerDetailMapper.fromAnswerToAnswerDetail(dQuestion.getAnswers())
                ))
                .blockingFirst();
    }
}
