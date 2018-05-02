package com.example.ckpenep.stackoverflow.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.ckpenep.stackoverflow.model.converters.TagConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Entity
public class Question implements Parcelable {
    @NonNull
    @PrimaryKey
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
    @TypeConverters(TagConverter.class)
    private List<String> tags = null;

    public Question(Integer id, String title, Boolean isAnswered, Integer viewCount, Integer answerCount, Integer score, Integer lastActivityDate, Integer creationDate, String link, Integer lastEditDate, Integer acceptedAnswerId, Integer protectedDate, List<String> tags)
    {
        this.mId = id;
        this.title = title;
        this.isAnswered = isAnswered;
        this.viewCount = viewCount;
        this.answerCount = answerCount;
        this.score = score;
        this.lastActivityDate = lastActivityDate;
        this.creationDate = creationDate;
        this.link = link;
        this.lastEditDate = lastEditDate;
        this.acceptedAnswerId = acceptedAnswerId;
        this.protectedDate = protectedDate;
        this.tags = tags;
    }

    @NonNull
    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return title;
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

    public String getTagsByString()
    {
        String result = "";

        for (int i=0; i < tags.size(); i++)
        {
            if(i != 0)result += ", ";
            result += tags.get(i);
        }
        return result;
    }

    public String getDateByString()
    {
        String result = "";
        try {

            Date dd = new Date(creationDate * 1000L);
            Date nowTime = new Date();

            long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(nowTime.getTime() - dd.getTime());

            if(differenceInSeconds < 60)
            {
                result = differenceInSeconds + " seconds ago";
            }
            else if(differenceInSeconds < 3600)
            {
                long minutes = differenceInSeconds / 60;
                result = minutes + " minutes ago";
            }
            else if(differenceInSeconds < 86400)
            {
                long hours = differenceInSeconds / (60 * 60);
                result = hours + " hours ago";
            }
            else if(differenceInSeconds < 31_536_000)
            {
                long days = differenceInSeconds / (60 * 60 * 24);
                result = days + " days ago";
            }
            else
            {
                long years = differenceInSeconds / (60 * 60 * 24 * 365);
                result = years + " years ago";
            }
        }
        catch (Exception e){
            return "";
        }

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(this.link != null)dest.writeString(this.link);
        if(this.title != null)dest.writeString(this.title);
        if(this.acceptedAnswerId != null)dest.writeInt(this.acceptedAnswerId);
        if(this.answerCount != null)dest.writeInt(this.answerCount);
        if(this.creationDate != null)dest.writeInt(this.creationDate);
        if(this.isAnswered != null)dest.writeByte((byte) (this.isAnswered ? 1 : 0));
        if(this.lastActivityDate != null)dest.writeInt(this.lastActivityDate);
        if(this.lastEditDate != null)dest.writeInt(this.lastEditDate);
        if(this.mId != null)dest.writeInt(this.mId);
        if(this.protectedDate != null)dest.writeInt(this.protectedDate);
        if(this.score != null)dest.writeInt(this.score);
        if(this.tags != null)dest.writeStringList(this.tags);
        if(this.viewCount != null)dest.writeInt(this.viewCount);
    }

    protected Question(Parcel in) {
        this.tags = new ArrayList<>();
        in.readStringList(tags);
        this.acceptedAnswerId = in.readInt();
        this.answerCount = in.readInt();
        this.creationDate = in.readInt();
        this.isAnswered = in.readByte() != 0;
        this.lastActivityDate = in.readInt();
        this.lastEditDate = in.readInt();
        this.link = in.readString();
        this.mId = in.readInt();
        this.protectedDate = in.readInt();
        this.score = in.readInt();
        this.title = in.readString();
        this.viewCount = in.readInt();
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
