
package com.scan.test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("mgs")
    @Expose
    private String mgs;
    @SerializedName("listData")
    @Expose

    private java.util.List<ListData> list = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMgs() {
        return mgs;
    }

    public void setMgs(String mgs) {
        this.mgs = mgs;
    }

    public java.util.List<ListData> getListData() {
        return list;
    }

    public void setListData(java.util.List<ListData> listData) {
        this.list = listData;
    }

}
