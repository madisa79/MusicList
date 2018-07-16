package com.bignerdranch.android.musiclist;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClassicJSONPlaceHolderApi {
    @GET("vpwnq")
        //https://api.myjson.com/bins/
    Call<ClassicLab> getClassics();
}
