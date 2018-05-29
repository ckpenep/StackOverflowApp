package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.dto.questions.AnswerItem;
import com.example.ckpenep.stackoverflow.model.question.Answer;

import java.util.List;

import io.reactivex.Observable;

public class AnswerMapper {
    public static List<Answer> fromResultsItemToTasks(List<AnswerItem> answersItems) {
        if(answersItems != null) {
            return Observable.fromIterable(answersItems)
                    .map(answerDTO -> new Answer(
                            CommentMapper.fromResultsItemToTasks(answerDTO.getComments()),
                            OwnerMapper.fromResultsItemToTasks(answerDTO.getOwner()),
                            OwnerMapper.fromResultsItemToTasks(answerDTO.getLastEditor()),
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
        }
        else
        {
            return null;
        }
    }
}
