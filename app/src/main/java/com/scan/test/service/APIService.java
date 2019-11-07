package com.scan.test.service;

import com.scan.test.model.Data;
import com.scan.test.model.ListData;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("testapi/")
    Call<Data> getData(@Body String requestBody);

}
