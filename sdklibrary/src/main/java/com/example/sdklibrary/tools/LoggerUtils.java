package com.example.sdklibrary.tools;

import android.util.Log;

/**
 * Created by tzw on 2018/6/4.
 * 日志工具类
 * 支持debug/release(打开/关闭 调试日志)
 * 支持自定义日志TAG
 */

public class LoggerUtils {
    public static final boolean logTAG = true;
    public static final String TAG = "log";

    public static void i(String debugData){
        if (logTAG){
            Log.i(TAG, debugData);
        }else {
            Log.i(TAG, "debugData was close ");
        }

    }

    public static void i(String debugTag,String debugData){
        if (logTAG){
            Log.i(debugTag, debugData);
        }else {
            Log.i(debugTag, "debugData was close ");
        }

    }

    public static void e(String debugData){
        if (logTAG){
            Log.e(TAG, " :"+debugData);
        }
        else {
            Log.e(TAG, "debugData was close ");
        }
    }

    public static void e(String debugTag,String debugData ){
        if (logTAG){
            Log.e(debugTag, " :"+debugData);
        }
        else {
            Log.e(debugTag, "debugData was close ");

        }
    }
}
