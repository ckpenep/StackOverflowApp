package com.example.ckpenep.stackoverflow.model.datails;

import android.support.v7.widget.RecyclerView;

import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderDetailsFactory;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AnswerDetail implements DetailsRowType {

    private List<CommentDetail> comments = null;
    private OwnerDetail owner;
    private OwnerDetail lastEditor;
    private Integer commentCount;
    private Boolean isAccepted;
    private Integer score;
    private Integer lastActivityDate;
    private Integer creationDate;
    private Integer answerId;
    private Integer questionId;
    private String bodyMarkdown;
    private String title;
    private String body;
    private Integer lastEditDate;
    private boolean expanded = false;

    public AnswerDetail(List<CommentDetail> comments, OwnerDetail owner, OwnerDetail lastEditor, Integer commentCount, Boolean isAccepted, Integer score, Integer lastActivityDate, Integer creationDate, Integer answerId, Integer questionId, String bodyMarkdown, String title, String body, Integer lastEditDate) {
        this.comments = comments;
        this.owner = owner;
        this.lastEditor = lastEditor;
        this.commentCount = commentCount;
        this.isAccepted = isAccepted;
        this.score = score;
        this.lastActivityDate = lastActivityDate;
        this.creationDate = creationDate;
        this.answerId = answerId;
        this.questionId = questionId;
        this.bodyMarkdown = bodyMarkdown;
        this.title = title;
        this.body = body;
        this.lastEditDate = lastEditDate;
    }

    public List<CommentDetail> getComments() {
        return comments;
    }

    public OwnerDetail getOwner() {
        return owner;
    }

    public OwnerDetail getLastEditor() {
        return lastEditor;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getLastActivityDate() {
        return lastActivityDate;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getBodyMarkdown() {
        return bodyMarkdown;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Integer getLastEditDate() {
        return lastEditDate;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getDateByString(Integer createDate) {
        String result = "";
        try {

            Date dd = new Date(createDate * 1000L);
            Date nowTime = new Date();

            long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(nowTime.getTime() - dd.getTime());

            if (differenceInSeconds < 60) {
                result = differenceInSeconds + " seconds ago";
            } else if (differenceInSeconds < 3600) {
                long minutes = differenceInSeconds / 60;
                result = minutes + " minutes ago";
            } else if (differenceInSeconds < 86400) {
                long hours = differenceInSeconds / (60 * 60);
                result = hours + " hours ago";
            } else if (differenceInSeconds < 31_536_000) {
                long days = differenceInSeconds / (60 * 60 * 24);
                result = days + " days ago";
            } else {
                long years = differenceInSeconds / (60 * 60 * 24 * 365);
                result = years + " years ago";
            }
        } catch (Exception e) {
            return "";
        }

        return result;
    }

    @Override
    public int getItemViewType() {
        return DetailsRowType.ANSWER_ROW_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderDetailsFactory.AnswerViewHolder answerViewHolder = (ViewHolderDetailsFactory.AnswerViewHolder) viewHolder;
        answerViewHolder.bind(this);
    }
}
