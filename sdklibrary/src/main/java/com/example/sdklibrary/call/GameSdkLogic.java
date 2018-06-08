package com.example.sdklibrary.call;

import android.content.Context;
import android.content.Intent;

import com.example.sdklibrary.callback.SdkCallbackListener;
import com.example.sdklibrary.config.ConstData;
import com.example.sdklibrary.config.SDKStatusCode;
import com.example.sdklibrary.mvp.model.MVPPayBean;
import com.example.sdklibrary.mvp.model.MVPPlayerBean;
import com.example.sdklibrary.tools.LoggerUtils;
import com.example.sdklibrary.ui.SdkLoginActivity;
import com.example.sdklibrary.ui.SdkPayActivity;

/**
 * Created by tzw on 2018/6/5.
 * 供接入使用SDK开发人员调用的核心类
 */

public class GameSdkLogic {
    private boolean checkInit;

    private GameSdkLogic(){ }

    private volatile static GameSdkLogic sdkLogic;

    public static GameSdkLogic getInstance(){
        if (sdkLogic == null){
            synchronized (GameSdkLogic.class){
                if (sdkLogic == null){
                    sdkLogic = new GameSdkLogic();
                }
            }
        }
        return sdkLogic;
    }

    //游戏初始化:
    //这里没有商业接口,固定是初始化成功,实际开发需要根据后台去判断成功/失败
    //只有当初始化的时候才可以进行后续操作
    public void sdkInit(Context context,final Object o, final SdkCallbackListener<String> callback){
        callback.callback(SDKStatusCode.SUCCESS, "初始化成功");
        checkInit = true;
    }

    //登录:
    //理论上初始化成功才可以登录 这里的接口使用的是 玩Android 开放接口
    public void sdkLogin(Context context, final SdkCallbackListener<String> callback){
        LoggerUtils.i("SdkLogic Login");
        if (checkInit){
            Intent intent = new Intent(context, SdkLoginActivity.class);
            context.startActivity(intent);
            Delegate.listener = callback;
        }else {
            callback.callback(SDKStatusCode.FAILURE, ConstData.INIT_FAILURE);
            return;
        }

    }

    //支付:
    //需要将SDK支付信息传递给具体的方式中
    public void sdkPay(Context context, MVPPayBean bean,final SdkCallbackListener<String> callback){
        LoggerUtils.i("SdkLogic Pay");
        if (checkInit){
            Intent intent = new Intent(context, SdkPayActivity.class);
            context.startActivity(intent);
            Delegate.listener = callback;
        }else {
            callback.callback(SDKStatusCode.FAILURE, ConstData.INIT_FAILURE);
            return;
        }
    }


    //提交游戏信息：
    public void subGameInfoMethod(MVPPlayerBean bean){
        //doing something:
        LoggerUtils.i("submit Player Information");
        //step:
        //Build HttpRequest   ----> server get Request ------->server return ResponseBody

        //This function is mainly used to record and count player information
    }


}
