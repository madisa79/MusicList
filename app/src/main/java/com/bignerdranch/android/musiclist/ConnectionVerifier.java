package com.bignerdranch.android.musiclist;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.security.Permission;

public class ConnectionVerifier {


    Context context;

    public ConnectionVerifier(Context context) {
        this.context = context;
    }

    public boolean isOnline() {


        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (cm != null) {


            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) {
                return activeNetwork.isConnected();

            }


        }
        return false;
    }
}