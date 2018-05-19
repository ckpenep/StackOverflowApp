package com.example.ckpenep.stackoverflow.model.question;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class Owner implements Parcelable {

    private Integer userId;
    private Integer reputation;
    private String userType;
    private Integer acceptRate;
    private String profileImage;
    private String displayName;
    private String link;

    public Owner(Integer reputation, Integer userId, String userType, Integer acceptRate, String profileImage, String displayName, String link) {
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

    public Integer getUserId() {
        return userId;
    }

    public String getUserType() {
        return userType;
    }

    public Integer getAcceptRate() {
        return acceptRate;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLink() {
        return link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(this.userId != null)dest.writeInt(this.userId);
        if(this.reputation != null)dest.writeInt(this.reputation);
        if(this.userType != null)dest.writeString(this.userType);
        if(this.acceptRate != null)dest.writeInt(this.acceptRate);
        if(this.profileImage != null)dest.writeString(this.profileImage);
        if(this.displayName != null)dest.writeString(this.displayName);
        if(this.link != null)dest.writeString(this.link);
    }

    protected Owner(Parcel in) {
        this.userId = in.readInt();
        this.reputation = in.readInt();
        this.userType = in.readString();
        this.acceptRate = in.readInt();
        this.profileImage = in.readString();
        this.displayName = in.readString();
        this.link = in.readString();
    }

    public static final Parcelable.Creator<Owner> CREATOR = new Parcelable.Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel source) {
            return new Owner(source);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };
}
