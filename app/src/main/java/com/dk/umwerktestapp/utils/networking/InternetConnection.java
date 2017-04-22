package com.dk.umwerktestapp.utils.networking;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.IOException;

/**
 * Created by David on 22-Apr-17.
 */

public class InternetConnection {

    public static boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static boolean isConnected() throws InterruptedException, IOException
    {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec (command).waitFor() == 0);
    }
}
