package com.example.ckpenep.stackoverflow.model.datails;

public class OwnerDetail {

    private Integer reputation;
    private Integer userId;
    private String userType;
    private Integer acceptRate;
    private String profileImage;
    private String displayName;
    private String link;

    public OwnerDetail(Integer reputation, Integer userId, String userType, Integer acceptRate, String profileImage, String displayName, String link) {
        this.reputation = reputation;
        this.userId = userId;
        this.userType = userType;
        this.acceptRate = acceptRate;
        this.profileImage = profileImage;
        this.displayName = displayName;
        this.link = link;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getAcceptRate() {
        return acceptRate;
    }

    public void setAcceptRate(Integer acceptRate) {
        this.acceptRate = acceptRate;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
