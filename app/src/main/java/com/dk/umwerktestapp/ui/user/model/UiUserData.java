package com.dk.umwerktestapp.ui.user.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by David on 22-Apr-17.
 */

@Parcel(Parcel.Serialization.BEAN)
public class UiUserData {

    private String createdAt;
    private String email;
    private String followers;
    private String htmlUrl;
    private String company;
    private String location;


    @ParcelConstructor
    public UiUserData(String createdAt, String email,String followers, String htmlUrl,
                      String location, String company) {
        this.createdAt = createdAt;
        this.email = email;
        this.followers = followers;
        this.htmlUrl = htmlUrl;
        this.location = location;
        this.company = company;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getEmail() {
        return email;
    }

    public String getFollowers() {
        return followers;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }
}