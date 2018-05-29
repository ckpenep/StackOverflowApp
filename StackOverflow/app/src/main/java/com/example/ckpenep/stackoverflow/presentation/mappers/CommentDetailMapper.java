package com.example.ckpenep.stackoverflow.presentation.mappers;

import com.example.ckpenep.stackoverflow.model.datails.CommentDetail;
import com.example.ckpenep.stackoverflow.model.dto.questions.CommentItem;
import com.example.ckpenep.stackoverflow.model.question.Comment;

import java.util.List;

import io.reactivex.Observable;

public class CommentDetailMapper {
    public static List<CommentDetail> fromCommentToCommentDetails(List<Comment> comments) {
        if(comments != null && comments.size() > 0) {
            return Observable.fromIterable(comments)
                    .map(showDTO -> new CommentDetail(
                            showDTO.getCommentId(),
                            showDTO.getEdited(),
                            showDTO.getScore(),
                            showDTO.getCreationDate(),
                            OwnerDetailMapper.fromOwnerToOwnerDetails(showDTO.getOwner()),
                            showDTO.getPostId(),
                            showDTO.getBodyMarkdown(),
                            showDTO.getBody()
                    ))
                    .toList()
                    .toObservable()
                    .blockingFirst();
        }
        return null;
    }

    public static List<CommentDetail> fromCommentItemToCommentDetails(List<CommentItem> comments) {
        if(comments != null && comments.size() > 0) {
            return Observable.fromIterable(comments)
                    .map(showDTO -> new CommentDetail(
                            showDTO.getCommentId(),
                            showDTO.getEdited(),
                            showDTO.getScore(),
                            showDTO.getCreationDate(),
                            OwnerDetailMapper.fromOwnerToOwnerDetails(showDTO.getOwner()),
                            showDTO.getPostId(),
                            showDTO.getBodyMarkdown(),
                            showDTO.getBody()
                    ))
                    .toList()
                    .toObservable()
                    .blockingFirst();
        }
        return null;
    }
}
