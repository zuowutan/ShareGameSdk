package com.share.gamesdk.other;

import android.content.Intent;
import android.view.View;

import com.example.sdklibrary.base.SdkBaseActivity;
import com.example.sdklibrary.tools.LoggerUtils;
import com.share.gamesdk.R;

/**
 * Created by tzw on 2018/6/8.
 */

public class X5InfoActivity extends SdkBaseActivity{
    MyX5WebView myX5WebView;
    private   String url;
    @Override
    public int getLayoutId() {
        Intent intent = getIntent();
         url = intent.getStringExtra("key");
        LoggerUtils.i("web url == "+url);
        return R.layout.x5layout;
    }

    @Override
    public void initViews() {
        myX5WebView = findViewById(R.id.myx5);
        myX5WebView.loadUrl(url);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {

    }
}
