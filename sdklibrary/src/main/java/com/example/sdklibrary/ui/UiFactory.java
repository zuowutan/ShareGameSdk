package com.example.sdklibrary.ui;

/**
 * Created by tzw on 2018/6/4.
 * 工厂类
 */

public class UiFactory {

    public static int getDifferentUI(String type) {

        if ("Login".equals(type)) {
            return 1;
        } else if ("Pay".equals(type)) {
            return 2;
        } else if ("Des".equals(type)) {
            return 3;
        } else {

        }

        return 0;
    }


}
