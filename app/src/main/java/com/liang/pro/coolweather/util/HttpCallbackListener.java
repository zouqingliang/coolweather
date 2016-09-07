package com.liang.pro.coolweather.util;

/**
 * Created by Administrator on 2016/9/7.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
