package com.dk.umwerktestapp;

import android.app.Application;

import com.dk.umwerktestapp.data.DaggerNetworkComponent;
import com.dk.umwerktestapp.data.NetworkComponent;
import com.dk.umwerktestapp.data.NetworkModule;

/**
 * Created by David on 20-Apr-17.
 */

public class App extends Application {

    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(ApplicationConstants.GITHUB_API_URL))
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
