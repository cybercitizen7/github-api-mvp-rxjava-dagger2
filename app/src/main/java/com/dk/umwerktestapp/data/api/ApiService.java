package com.dk.umwerktestapp.data.api;

import com.dk.umwerktestapp.data.api.response.user.UserSearchResponses;
import com.dk.umwerktestapp.data.api.response.user.UserResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by David on 20-Apr-17.
 */

public interface ApiService {

    @GET("/users/{username}")
    Single<UserResponse> getUserData(@Path("username") String username);

    @GET("/search/users")
    Single<UserSearchResponses> getJavaUsers(@Query("q") String q,
                                             @Query("per_page") String page);
}
