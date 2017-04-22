package com.dk.umwerktestapp.data.api.response.user;

/**
 * Created by David on 22-Apr-17.
 */

public class UserSearchResponse extends BaseUserResponse {

    private String avatarUrl;
    private long id;

    public UserSearchResponse(String login) {
        super(login);
    }

    public long getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
