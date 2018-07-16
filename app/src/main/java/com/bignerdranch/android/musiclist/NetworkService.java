package com.bignerdranch.android.musiclist;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static final String BASE_URL = "https://api.myjson.com/bins/";
    private static NetworkService mInstance;
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public RockJSONPlaceHolderApi getRockJSONApi() {
        return mRetrofit.create(RockJSONPlaceHolderApi.class);
    }


    public PopJSONPlaceHolderApi getPopJSONApi() {
        return mRetrofit.create(PopJSONPlaceHolderApi.class);
    }

    public ClassicJSONPlaceHolderApi getClassicJSONApi() {
        return mRetrofit.create(ClassicJSONPlaceHolderApi.class);
    }
}
