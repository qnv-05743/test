package com.scan.test.service;

import com.scan.test.model.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers("Content-Type: text/plain")
    @POST("listData")
    @FormUrlEncoded
    Call<List> getData(@Field("datas") String data);
}
