package com.bignerdranch.android.musiclist;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PopJSONPlaceHolderApi {

    @GET("y5s8m")
    Call<PopLab> getPops();
}
