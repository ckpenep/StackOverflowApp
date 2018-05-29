package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.datails.AnswerDetail;
import com.example.ckpenep.stackoverflow.model.dto.questions.AnswerItem;
import com.example.ckpenep.stackoverflow.model.question.Answer;

import java.util.List;

import io.reactivex.Observable;

public class AnswerDetailMapper {
    public static List<AnswerDetail> fromAnswerToAnswerDetail(List<Answer> answers) {
        if (answers != null && answers.size() > 0) {
            return Observable.fromIterable(answers)
                    .map(answerDTO -> new AnswerDetail(
                            CommentDetailMapper.fromCommentToCommentDetails(answerDTO.getComments()),
                            OwnerDetailMapper.fromOwnerToOwnerDetails(answerDTO.getOwner()),
                            OwnerDetailMapper.fromOwnerToOwnerDetails(answerDTO.getLastEditor()),
                            answerDTO.getCommentCount(),
                            answerDTO.getAccepted(),
                            answerDTO.getScore(),
                            answerDTO.getLastActivityDate(),
                            answerDTO.getCreationDate(),
                            answerDTO.getAnswerId(),
                            answerDTO.getQuestionId(),
                            answerDTO.getBodyMarkdown(),
                            answerDTO.getTitle(),
                            answerDTO.getBody(),
                            answerDTO.getLastEditDate()
                    ))
                    .toList()
                    .toObservable()
                    .blockingFirst();
        } else return null;
    }

    public static List<AnswerDetail> fromAnswerItemToAnswerDetail(List<AnswerItem> answers) {
        if (answers != null && answers.size() > 0) {
            return Observable.fromIterable(answers)
                    .map(answerDTO -> new AnswerDetail(
                            CommentDetailMapper.fromCommentItemToCommentDetails(answerDTO.getComments()),
                            OwnerDetailMapper.fromOwnerToOwnerDetails(answerDTO.getOwner()),
                            OwnerDetailMapper.fromOwnerToOwnerDetails(answerDTO.getLastEditor()),
                            answerDTO.getCommentCount(),
                            answerDTO.getAccepted(),
                            answerDTO.getScore(),
                            answerDTO.getLastActivityDate(),
                            answerDTO.getCreationDate(),
                            answerDTO.getAnswerId(),
                            answerDTO.getQuestionId(),
                            answerDTO.getBodyMarkdown(),
                            answerDTO.getTitle(),
                            answerDTO.getBody(),
                            answerDTO.getLastEditDate()
                    ))
                    .toList()
                    .toObservable()
                    .blockingFirst();
        } else return null;
    }
}
