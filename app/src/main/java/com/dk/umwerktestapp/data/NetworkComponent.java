package com.dk.umwerktestapp.data;

import com.dk.umwerktestapp.AppModule;
import com.dk.umwerktestapp.data.api.service.UserApiService;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by David on 20-Apr-17.
 */
@Singleton
@Component( modules = {
        AppModule.class,
        NetworkModule.class
    }
)
public interface NetworkComponent {

    Retrofit retrofit();

    UserApiService userApiService();
}
