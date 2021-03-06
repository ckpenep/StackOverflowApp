package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.question.Question;
import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionItem;

import java.util.List;

import io.reactivex.Observable;

public class QuestionMapper {
    public static List<Question> fromResultsItemToTasks(List<QuestionItem> getResultsItems) {
        return Observable.fromIterable(getResultsItems)
                .map(showDTO -> new Question(
                        showDTO.getQuestionId(),
                        showDTO.getIsAnswered(),
                        showDTO.getViewCount(),
                        showDTO.getAnswerCount(),
                        showDTO.getScore(),
                        showDTO.getLastActivityDate(),
                        showDTO.getCreationDate(),
                        showDTO.getLink(),
                        showDTO.getTitle(),
                        showDTO.getLastEditDate(),
                        showDTO.getAcceptedAnswerId(),
                        showDTO.getProtectedDate(),
                        showDTO.getTags(),
                        showDTO.getBodyMarkdown(),
                        showDTO.getBody(),
                        OwnerMapper.fromResultsItemToTasks(showDTO.getOwner()),
                        OwnerMapper.fromResultsItemToTasks(showDTO.getEditor()),
                        CommentMapper.fromResultsItemToTasks(showDTO.getComments()),
                        AnswerMapper.fromResultsItemToTasks(showDTO.getAnswers())
                ))
                .toList()
                .toObservable()
                .blockingFirst();
    }
}
