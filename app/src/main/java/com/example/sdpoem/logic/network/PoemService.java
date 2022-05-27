package com.example.sdpoem.logic.network;

import com.example.sdpoem.logic.model.PoemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PoemService {
    @GET("app/poem")
    Call<PoemResponse> getRandomPoem();
}
