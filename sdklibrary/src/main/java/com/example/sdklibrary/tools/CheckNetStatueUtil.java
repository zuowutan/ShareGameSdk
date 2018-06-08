package com.example.sdklibrary.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by tzw on 2018/6/4.
 */

public class CheckNetStatueUtil {

    /**
     * 检查是否有网络
     */
    public static boolean check_NET(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            LoggerUtils.i("NetWork_Statue : " + "ConnectivityManager == null");
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        LoggerUtils.i("NetWork_Statue : " + "ConnectivityManager == Availabel");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检查是否是WIFI
     */
    public static boolean check_WIFI(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查是否是移动流量网络
     */
    public static boolean check_MOBILE_Traffic(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE)
                return true;
        }
        return false;
    }


    private static NetworkInfo getNetworkInfo(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
}
