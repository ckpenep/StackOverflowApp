package com.example.ckpenep.stackoverflow.model.dto.datails;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.ckpenep.stackoverflow.model.Owner;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.DetailsRowType;
import com.example.ckpenep.stackoverflow.ui.adapters.factories.ViewHolderDetailsFactory;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private Owner editor;

    public QuestionDetail(Integer id, Boolean isAnswered, Integer viewCount, Integer answerCount, Integer score, Integer lastActivityDate, Integer creationDate, String link, String title, Integer lastEditDate, Integer acceptedAnswerId, Integer protectedDate, List<String> tags, String bodyMarkdown, String body, Owner owner, Owner editor) {
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
        this.editor = editor;
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

    public String getTagsByString() {
        String result = "";

        if (tags != null && tags.size() > 0) {
            for (int i = 0; i < tags.size(); i++) {
                if (i != 0) result += ", ";
                result += tags.get(i);
            }
        }
        return result;
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
        return DetailsRowType.QUESTION_ROW_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder) {
        ViewHolderDetailsFactory.QuestionViewHolder questionViewHolder = (ViewHolderDetailsFactory.QuestionViewHolder) viewHolder;

        Log.d("TAGS", getTagsByString());
        questionViewHolder.title.setText(getTitle());
        questionViewHolder.count_likes.setText(getAnswerCount().toString());
        questionViewHolder.tags.setText(getTagsByString());
        questionViewHolder.webView.loadData(getBody(), "text/html", null);

        if (owner != null) {
            if (owner.getProfileImage() != null)
                Picasso.get().load(owner.getProfileImage()).into(questionViewHolder.ownerImage);
            if (owner.getDisplayName() != null)
                questionViewHolder.ownerName.setText(owner.getDisplayName());
            if (owner.getReputation() != null)
                questionViewHolder.ownerRating.setText(Integer.toString(owner.getReputation()));
            if (getCreationDate() != null)
                questionViewHolder.dateCreateQuestion.setText("Asked " + getDateByString(getCreationDate()));
        }
        if (editor != null) {
            if (editor.getProfileImage() != null)
                Picasso.get().load(editor.getProfileImage()).into(questionViewHolder.editorImage);
            if (editor.getDisplayName() != null)
                questionViewHolder.editorName.setText(editor.getDisplayName());
            if (editor.getReputation() != null)
                questionViewHolder.editorRating.setText(Integer.toString(editor.getReputation()));
            if (getLastEditDate() != null)
                questionViewHolder.dateEditQuestion.setText("Edited " + getDateByString(getLastEditDate()));
        } else {
            questionViewHolder.editorContainer.setVisibility(View.GONE);
        }
    }
}
