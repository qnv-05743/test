package com.scan.test.service;

import com.scan.test.model.Employes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("api/v1/employees")
    Call<List<Employes>> getAllProduct();
}
