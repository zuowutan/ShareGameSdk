package com.share.gamesdk.other;

import android.content.Intent;

import com.example.sdklibrary.base.GameSdkApplication;

/**
 * Created by tzw on 2018/6/8.
 */

public class SDKUseApp extends GameSdkApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        preInitX5();
    }

    void preInitX5(){
        Intent intent = new Intent(SDKUseApp.this,X5NetService.class);
        startService(intent);
    }
}
