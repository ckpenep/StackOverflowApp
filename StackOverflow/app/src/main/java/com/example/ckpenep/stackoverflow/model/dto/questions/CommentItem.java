package com.example.ckpenep.stackoverflow.model.dto.questions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentItem {
    @SerializedName("owner")
    @Expose
    private OwnerItem owner;
    @SerializedName("edited")
    @Expose
    private Boolean edited;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("creation_date")
    @Expose
    private Integer creationDate;
    @SerializedName("post_id")
    @Expose
    private Integer postId;
    @SerializedName("comment_id")
    @Expose
    private Integer commentId;
    @SerializedName("body_markdown")
    @Expose
    private String bodyMarkdown;
    @SerializedName("body")
    @Expose
    private String body;

    public OwnerItem getOwner() {
        return owner;
    }

    public void setOwner(OwnerItem owner) {
        this.owner = owner;
    }

    public Boolean getEdited() {
        return edited;
    }

    public void setEdited(Boolean edited) {
        this.edited = edited;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getBodyMarkdown() {
        return bodyMarkdown;
    }

    public void setBodyMarkdown(String bodyMarkdown) {
        this.bodyMarkdown = bodyMarkdown;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
