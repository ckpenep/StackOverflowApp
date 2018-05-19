package com.example.ckpenep.stackoverflow.model.datails;

import android.support.v7.widget.RecyclerView;

import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderDetailsFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerDetail implements DetailsRowType {
    @SerializedName("owner")
    @Expose
    private OwnerDetail owner;
    @SerializedName("is_accepted")
    @Expose
    private Boolean isAccepted;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("last_activity_date")
    @Expose
    private Integer lastActivityDate;
    @SerializedName("creation_date")
    @Expose
    private Integer creationDate;
    @SerializedName("answer_id")
    @Expose
    private Integer answerId;
    @SerializedName("question_id")
    @Expose
    private Integer questionId;

    public OwnerDetail getOwner() {
        return owner;
    }

    public void setOwner(OwnerDetail owner) {
        this.owner = owner;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Integer lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public int getItemViewType() {
        return DetailsRowType.ANSWER_ROW_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderDetailsFactory.AnswerViewHolder historyViewHolder = (ViewHolderDetailsFactory.AnswerViewHolder) viewHolder;


    }
}
