package com.example.sdklibrary.base;

import android.app.Application;

import com.example.sdklibrary.tools.SPDataUtils;

/**
 * Created by tzw on 2018/6/4.
 * 自定义Application
 */

public class GameSdkApplication extends Application{

    //doing you want do

    private static GameSdkApplication homeApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        homeApplication=this;
        SPDataUtils.init(getApplicationContext());
    }

    public static GameSdkApplication getInstance(){
        return homeApplication;
    }

}
