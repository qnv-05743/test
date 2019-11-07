package com.scan.test;

import android.util.Log;

public class AuthRequest {
    public static final String TAG = "list";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String THUMB = "thumb";
    public static final String CONTENT = "content";

    public static String createAuthJsonString() {

        String info = "{\"list\":{\"title\":\"" + TITLE + "\",\"thumb\":\"" + THUMB + "\",\"content\":\"" + CONTENT + "\"}}";


        Log.i(TAG, info);

        return info;
    }
}
