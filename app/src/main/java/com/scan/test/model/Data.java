
package com.scan.test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("mgs")
    @Expose
    private String mgs;
    @SerializedName("listData")
    @Expose
    private java.util.List<List> listData = null;

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

    public java.util.List<List> getListData() {
        return listData;
    }

    public void setListData(java.util.List<List> listData) {
        this.listData = listData;
    }

}
