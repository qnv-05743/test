package com.scan.test.service;

import com.scan.test.model.Data;
import com.scan.test.model.DataObj;
import com.scan.test.model.ListData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @FormUrlEncoded
    @POST("/testapi/")
    Call<DataObj> getData(
            @Field("datas") String datas,
            @Field("token") String token);

    @GET("/testapi/")
    Call<List<ListData>> getAll();
}
