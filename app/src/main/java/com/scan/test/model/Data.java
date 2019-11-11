
package com.scan.test.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data  {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("mgs")
    @Expose
    private String mgs;
    @SerializedName("list")
    @Expose
    private List<ListData> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMgs() {
        return mgs;
    }

    public void setMgs(String mgs) {
        this.mgs = mgs;
    }

    public List<ListData> getList() {
        return list;
    }

    public void setList(List<ListData> list) {
        this.list = list;
    }
}
