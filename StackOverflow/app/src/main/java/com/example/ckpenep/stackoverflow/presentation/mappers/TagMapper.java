package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.dto.questions.QuestionItem;
import com.example.ckpenep.stackoverflow.model.dto.tag.TagItem;
import com.example.ckpenep.stackoverflow.model.question.Question;
import com.example.ckpenep.stackoverflow.model.tag.Tag;

import java.util.List;

import io.reactivex.Observable;

public class TagMapper {
    public static List<Tag> fromResultsItemToTasks(List<TagItem> getResultsItems) {
        return Observable.fromIterable(getResultsItems)
                .map(showDTO -> new Tag(
                        showDTO.getHasSynonyms(),
                        showDTO.getIsModeratorOnly(),
                        showDTO.getIsRequired(),
                        showDTO.getCount(),
                        showDTO.getName()
                ))
                .toList()
                .toObservable()
                .blockingFirst();
    }
}
