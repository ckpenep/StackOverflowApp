package com.example.ckpenep.stackoverflow.model.datails;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentDetail implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(this.commentId != null)dest.writeInt(this.commentId);
        if(this.edited != null)dest.writeByte((byte) (this.edited ? 1 : 0));
        if(this.score != null)dest.writeInt(this.score);
        if(this.creationDate != null)dest.writeInt(this.creationDate);
        if(this.owner != null)dest.writeParcelable((Parcelable) owner, flags);
        if(this.postId != null)dest.writeInt(this.postId);
        if(this.body != null)dest.writeString(this.body);
        if(this.bodyMarkdown != null)dest.writeString(this.bodyMarkdown);
    }


    protected CommentDetail(Parcel in) {
        this.commentId = in.readInt();
        this.edited = in.readByte() != 0;
        this.score = in.readInt();
        this.creationDate = in.readInt();
        this.postId = in.readInt();
        this.owner = (OwnerDetail) in.readParcelable(OwnerDetail.class.getClassLoader());
        this.body = in.readString();
        this.bodyMarkdown = in.readString();
    }

    public static final Parcelable.Creator<CommentDetail> CREATOR = new Parcelable.Creator<CommentDetail>() {
        @Override
        public CommentDetail createFromParcel(Parcel source) {
            return new CommentDetail(source);
        }

        @Override
        public CommentDetail[] newArray(int size) {
            return new CommentDetail[size];
        }
    };
}
