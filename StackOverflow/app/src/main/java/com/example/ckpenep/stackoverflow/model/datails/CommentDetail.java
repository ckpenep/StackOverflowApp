package com.example.ckpenep.stackoverflow.model.datails;

public class CommentDetail {
    private Integer commentId;
    private Boolean edited;
    private Integer score;
    private Integer creationDate;
    private OwnerDetail owner;
    private Integer postId;
    private String bodyMarkdown;
    private String body;

    public CommentDetail(Integer commentId, Boolean edited, Integer score, Integer creationDate, OwnerDetail owner, Integer postId, String bodyMarkdown, String body) {
        this.commentId = commentId;
        this.edited = edited;
        this.score = score;
        this.creationDate = creationDate;
        this.owner = owner;
        this.postId = postId;
        this.bodyMarkdown = bodyMarkdown;
        this.body = body;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Boolean getEdited() {
        return edited;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public OwnerDetail getOwner() {
        return owner;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getBodyMarkdown() {
        return bodyMarkdown;
    }

    public String getBody() {
        return body;
    }
}
