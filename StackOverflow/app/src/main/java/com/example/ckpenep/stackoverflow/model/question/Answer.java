package com.example.ckpenep.stackoverflow.model.question;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Answer implements Parcelable {

    private List<Comment> comments = null;
    private Owner owner;
    private Owner lastEditor;
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

    public Answer(List<Comment> comments, Owner owner, Owner lastEditor, Integer commentCount, Boolean isAccepted, Integer score, Integer lastActivityDate, Integer creationDate, Integer answerId, Integer questionId, String bodyMarkdown, String title, String body, Integer lastEditDate) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public Owner getOwner() {
        return owner;
    }

    public Owner getLastEditor() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (this.comments != null) dest.writeList(comments);
        if (this.owner != null) dest.writeParcelable(owner, flags);
        if (this.lastEditor != null) dest.writeParcelable(lastEditor, flags);
        if (this.commentCount != null) dest.writeInt(this.commentCount);
        if (this.isAccepted != null) dest.writeByte((byte) (this.isAccepted ? 1 : 0));
        if (this.score != null) dest.writeInt(this.score);
        if (this.lastActivityDate != null) dest.writeInt(this.lastActivityDate);
        if (this.creationDate != null) dest.writeInt(this.creationDate);
        if (this.answerId != null) dest.writeInt(this.answerId);
        if (this.questionId != null) dest.writeInt(this.questionId);
        if (this.bodyMarkdown != null) dest.writeString(this.bodyMarkdown);
        if (this.title != null) dest.writeString(this.title);
        if (this.body != null) dest.writeString(this.body);
        if (this.lastEditDate != null) dest.writeInt(this.lastEditDate);
    }

    protected Answer(Parcel in) {
        this.comments = new ArrayList<>();
        in.readArrayList(Comment.class.getClassLoader());
        this.owner = (Owner) in.readParcelable(Owner.class.getClassLoader());
        this.lastEditor = (Owner) in.readParcelable(Owner.class.getClassLoader());
        this.commentCount = in.readInt();
        this.isAccepted = in.readByte() != 0;
        this.score = in.readInt();
        this.lastActivityDate = in.readInt();
        this.creationDate = in.readInt();
        this.answerId = in.readInt();
        this.questionId = in.readInt();
        this.bodyMarkdown = in.readString();
        this.title = in.readString();
        this.body = in.readString();
        this.lastEditDate = in.readInt();
    }

    public static final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel source) {
            return new Answer(source);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
