package com.dk.umwerktestapp.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dk.umwerktestapp.data.api.ApiService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by David on 20-Apr-17.
 */
@Module
public class NetworkModule {

    private static String url;

    public NetworkModule(String url) {
        NetworkModule.url = url;
    }

    @Provides @Singleton SharedPreferences provideSharedPreference(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides @Singleton Gson provideGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides @Singleton Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides @Singleton CallAdapter.Factory provideCallAdapterFactory() {
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Provides @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    @Provides @Singleton Retrofit provideRetrofit(OkHttpClient client, Converter.Factory converter,
                             CallAdapter.Factory callAdapterFactory) {
        return getRetrofit(client, converter, callAdapterFactory);
    }

    private static Retrofit getRetrofit(OkHttpClient client, Converter.Factory converter,
                                        CallAdapter.Factory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(converter)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    @Provides @Singleton ApiService provideApiService(Retrofit retrofit) {
            return retrofit.create(ApiService.class);
    }
}
