package com.example.sdpoem.logic.network;

import android.util.Log;

import com.example.sdpoem.logic.model.PoemResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomPoem {
    static Callback<PoemResponse> responseCallback = new Callback<PoemResponse>() {
        @Override
        public void onResponse(Call<PoemResponse> call, Response<PoemResponse> response) {
            Log.d("TAG", "onResponse: 321123" + response.body().getAuthor());
        }

        @Override
        public void onFailure(Call<PoemResponse> call, Throwable t) {
            Log.d("TAG", "onFailure: ");
        }
    };

    static PoemService poemService = ServiceCreator.create(PoemService.class);

    static public void getRandomPoem() {
        poemService.getRandomPoem().enqueue(responseCallback);
    }
}
