package com.example.sdpoem.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator {
    private static final String BASE_URL = "https://api.9228.eu/";

    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    public static <T> T create(final Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
