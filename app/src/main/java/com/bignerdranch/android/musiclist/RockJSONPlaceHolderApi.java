package com.bignerdranch.android.musiclist;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RockJSONPlaceHolderApi {


    @GET("eih9y")
    Call<RockLab> getRocks();


}
