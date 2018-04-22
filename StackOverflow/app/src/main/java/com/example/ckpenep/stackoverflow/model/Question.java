package com.example.ckpenep.stackoverflow.model;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Question {
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
}
