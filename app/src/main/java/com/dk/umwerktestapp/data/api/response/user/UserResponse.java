package com.dk.umwerktestapp.data.api.response.user;

/**
 * Created by David on 20-Apr-17.
 */

public class UserResponse extends BaseUserResponse{
    private String createdAt;
    private String email;
    private String followers;
    private String htmlUrl;
    private String company;
    private String location;

    public UserResponse(String login) {
        super(login);
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
