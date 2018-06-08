package com.example.sdklibrary.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.sdklibrary.base.GameSdkApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tzw on 2018/6/4.
 * Cookies Interceptor拦截器 关于此拦截器的说明：
 * https://www.jianshu.com/p/bd1be47a16c1
 */

public class SaveCookiesInterceptor implements Interceptor{

    private static final String COOKIE_PREF = "cookies_prefs";
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (!response.headers("set-cookie").isEmpty()) {

            List<String> cookies = response.headers("set-cookie");

            String cookie = encodeCookie(cookies);

            saveCookie(request.url().toString(), request.url().host(), cookie);
        }
        return response;
    }
    /**
     * 整合cookie为唯一字符串
     * @param cookies
     * @return
     */
    private String encodeCookie(List<String> cookies) {

        StringBuilder sb = new StringBuilder();

        List<String> set = new ArrayList<>();
        for (String cookie : cookies) {
            String[] arr = cookie.split(";");
            for (String s : arr) {
                if (set.contains(s)) {
                    continue;
                }
                set.add(s);
            }
        }

        Iterator<String> ite = set.iterator();
        while (ite.hasNext()) {
            String cookie = ite.next();
            sb.append(cookie).append(";");
        }
        int last = sb.lastIndexOf(";");
        if (sb.length() - 1 == last) {
            sb.deleteCharAt(last);
        }
        return sb.toString();

    }
    /**
     * 持久化cookie
     */
    private void saveCookie(String url, String domain, String cookies) {
        SharedPreferences sp = GameSdkApplication.getInstance().getSharedPreferences(COOKIE_PREF,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (TextUtils.isEmpty(url)) {
            throw new NullPointerException("url is null.");
        }else{
            editor.putString(url, cookies);
        }

        if (!TextUtils.isEmpty(domain)) {
            editor.putString(domain, cookies);
        }

        editor.apply();
    }
}
