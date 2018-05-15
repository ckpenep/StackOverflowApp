package com.example.ckpenep.stackoverflow.model.dto.datails;

import android.support.v7.widget.RecyclerView;

import com.example.ckpenep.stackoverflow.model.Owner;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderDetailsFactory;

import java.util.List;

public class QuestionDetail implements DetailsRowType {
    private Integer mId;
    private Boolean isAnswered;
    private Integer viewCount;
    private Integer answerCount;
    private Integer score;
    private Integer lastActivityDate;
    private Integer creationDate;
    private String link;
    private String title;
    private Integer lastEditDate;
    private Integer acceptedAnswerId;
    private Integer protectedDate;
    private List<String> tags = null;
    private String bodyMarkdown;
    private String body;
    private Owner owner;

    public QuestionDetail(Integer id, Boolean isAnswered, Integer viewCount, Integer answerCount, Integer score, Integer lastActivityDate, Integer creationDate, String link, String title, Integer lastEditDate, Integer acceptedAnswerId, Integer protectedDate, List<String> tags, String bodyMarkdown, String body, Owner owner) {
        mId = id;
        this.isAnswered = isAnswered;
        this.viewCount = viewCount;
        this.answerCount = answerCount;
        this.score = score;
        this.lastActivityDate = lastActivityDate;
        this.creationDate = creationDate;
        this.link = link;
        this.title = title;
        this.lastEditDate = lastEditDate;
        this.acceptedAnswerId = acceptedAnswerId;
        this.protectedDate = protectedDate;
        this.tags = tags;
        this.bodyMarkdown = bodyMarkdown;
        this.body = body;
        this.owner = owner;
    }

    public Integer getId() {
        return mId;
    }

    public Boolean getAnswered() {
        return isAnswered;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
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

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public Integer getLastEditDate() {
        return lastEditDate;
    }

    public Integer getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public Integer getProtectedDate() {
        return protectedDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getBodyMarkdown() {
        return bodyMarkdown;
    }

    public String getBody() {
        return body;
    }

    public Owner getOwner() {
        return owner;
    }


    @Override
    public int getItemViewType() {
        return DetailsRowType.QUESTION_ROW_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderDetailsFactory.QuestionViewHolder historyViewHolder = (ViewHolderDetailsFactory.QuestionViewHolder) viewHolder;


    }
}
