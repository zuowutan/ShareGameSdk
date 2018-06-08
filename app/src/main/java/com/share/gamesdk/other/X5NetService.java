package com.share.gamesdk.other;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.tencent.smtt.sdk.QbSdk;


public class X5NetService extends IntentService {

    public static final String TAG = "x5Web";
    public X5NetService(){
        super(TAG);
    }
    public X5NetService(String name) {
        super(TAG);
    }

    @Override
    public void onHandleIntent(@Nullable Intent intent) {
        initX5Web();
    }
    public void initX5Web() {
        if (!QbSdk.isTbsCoreInited()) {
            // 设置X5初始化完成的回调接口
            QbSdk.preInit(getApplicationContext(), null);
        }
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
        @Override
        public void onViewInitFinished(boolean arg0) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onCoreInitFinished() {
            // TODO Auto-generated method stub
        }
    };


}



