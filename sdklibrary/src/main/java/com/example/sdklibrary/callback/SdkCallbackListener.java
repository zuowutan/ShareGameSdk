package com.example.sdklibrary.callback;

/**
 * Created by tzw on 2018/6/5.
 * 全局回调
 */

public interface SdkCallbackListener<T> {
    void callback(int code, T response);
}
