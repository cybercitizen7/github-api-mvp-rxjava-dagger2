package com.dk.umwerktestapp.ui.main.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by David on 21-Apr-17.
 */
@Parcel
public class UiBaseUser {

    private String login;
    private String avatarUrl;
    private long id;

    @ParcelConstructor
    public UiBaseUser(String login, String avatarUrl, long id) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public long getId() {
        return id;
    }
}
