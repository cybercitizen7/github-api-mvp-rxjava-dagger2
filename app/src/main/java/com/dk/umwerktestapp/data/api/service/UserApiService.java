package com.dk.umwerktestapp.data.api.service;

import com.dk.umwerktestapp.data.api.ApiService;
import com.dk.umwerktestapp.data.api.response.user.UserResponse;
import com.dk.umwerktestapp.data.api.response.user.UserSearchResponses;

import javax.inject.Inject;

import rx.Single;

/**
 * Created by David on 20-Apr-17.
 */
public class UserApiService {

    private ApiService apiService;

    @Inject
    public UserApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<UserResponse> getUserData(String username) {
        return apiService.getUserData(username);
    }

    public Single<UserSearchResponses> getJavaUsers(String q, String page) {
        return  apiService.getJavaUsers(q, page);
    }
}
