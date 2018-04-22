package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.Question;
import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionItem;

import java.util.List;

import io.reactivex.Observable;

public class QuestionMapper {
    public static List<Question> fromResultsItemToTasks(List<QuestionItem> getResultsItems) {
        return Observable.fromIterable(getResultsItems)
                .map(showDTO -> new Question(
                        showDTO.getQuestionId(),
                        showDTO.getTitle(),
                        showDTO.getIsAnswered(),
                        showDTO.getViewCount(),
                        showDTO.getAnswerCount(),
                        showDTO.getScore(),
                        showDTO.getLastActivityDate(),
                        showDTO.getCreationDate(),
                        showDTO.getLink(),
                        showDTO.getLastEditDate(),
                        showDTO.getAcceptedAnswerId(),
                        showDTO.getProtectedDate(),
                        showDTO.getTags()
                ))
                .toList()
                .toObservable()
                .blockingFirst();
    }
}
