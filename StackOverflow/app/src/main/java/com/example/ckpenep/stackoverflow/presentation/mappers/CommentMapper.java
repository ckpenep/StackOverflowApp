package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.dto.questions.CommentItem;
import com.example.ckpenep.stackoverflow.model.question.Comment;

import java.util.List;

import io.reactivex.Observable;

public class CommentMapper {
    public static List<Comment> fromResultsItemToTasks(List<CommentItem> commentsItems) {
        if(commentsItems != null) {
            return Observable.fromIterable(commentsItems)
                    .map(commentDTO -> new Comment(
                            commentDTO.getCommentId(),
                            commentDTO.getEdited(),
                            commentDTO.getScore(),
                            commentDTO.getCreationDate(),
                            OwnerMapper.fromResultsItemToTasks(commentDTO.getOwner()),
                            commentDTO.getPostId(),
                            commentDTO.getBodyMarkdown(),
                            commentDTO.getBody()))
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
