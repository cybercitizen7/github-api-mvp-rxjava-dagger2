package com.dk.umwerktestapp.data.api.response.user;

/**
 * Created by David on 21-Apr-17.
 */

public abstract class BaseUserResponse {

    private String login;

    public BaseUserResponse(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
