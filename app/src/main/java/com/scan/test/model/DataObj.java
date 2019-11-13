package com.scan.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataObj {
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }
}
