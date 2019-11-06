package com.scan.test.service;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static volatile APIClient mInstance = null;
    private Retrofit retrofit;

    private APIClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.200.254/")
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
    }

    public static APIClient self() {
        if (mInstance == null)
            mInstance = new APIClient();
        return mInstance;
    }

}
