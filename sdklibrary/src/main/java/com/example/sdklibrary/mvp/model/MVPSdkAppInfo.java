package com.example.sdklibrary.mvp.model;

/**
 * Created by tzw on 2018/6/5.
 * APP配置参数的信息:
 * 比如 高德地图对应的key 等等
 * 这里可以根据后台随意拓展
 */

public class MVPSdkAppInfo {
    private String appId;
    private String appKey;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public MVPSdkAppInfo(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }

    public MVPSdkAppInfo() {
    }

    @Override
    public String toString() {
        return "MVPSdkAppInfo{" +
                "appId='" + appId + '\'' +
                ", appKey='" + appKey + '\'' +
                '}';
    }
}
