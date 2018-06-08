package com.share.gamesdk;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sdklibrary.call.GameSdkLogic;
import com.example.sdklibrary.callback.SdkCallbackListener;
import com.example.sdklibrary.config.ConfigInfo;
import com.example.sdklibrary.config.ConstData;
import com.example.sdklibrary.config.SDKStatusCode;
import com.example.sdklibrary.mvp.model.MVPPayBean;
import com.example.sdklibrary.mvp.model.MVPPlayerBean;
import com.example.sdklibrary.tools.LoggerUtils;
import com.share.gamesdk.other.X5InfoActivity;

public class SdkDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton, payButton, myBlogButton, subInfoButton, aboutDesButton;
    private Button testLogin;
    public final String bolgUrl = "https://www.jianshu.com/u/0111a7da544b";
    public final String desUrl = "https://www.jianshu.com/p/8b9d82560a67";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        //配置信息初始化:
        configInit();
        //游戏初始化:
        init();

    }

    private void configInit() {
        //配置横竖屏,等一些可配置信息
        ConfigInfo.allowPORTRAIT = true;

    }

    //游戏前必须首先进行初始化：
    //初始化主要进行匹配参数是否符合后台规定，一些赋值操作
    private void init() {
        //这里的Object 可以扩展定义一切对象 ,你懂的
        Object object = new Object();
        GameSdkLogic.getInstance().sdkInit(this, object, new SdkCallbackListener<String>() {
            @Override
            public void callback(int code, String response) {
                switch (code) {
                    case SDKStatusCode.SUCCESS:
                        Toast.makeText(SdkDemoActivity.this, ConstData.INIT_SUCCESS,Toast.LENGTH_SHORT).show();
                        break;
                    case SDKStatusCode.FAILURE:
                        Toast.makeText(SdkDemoActivity.this, ConstData.INIT_FAILURE,Toast.LENGTH_SHORT).show();
                        break;
                    case SDKStatusCode.OTHER:
                        Toast.makeText(SdkDemoActivity.this, ConstData.INIT_FAILURE,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    //登录:
    private void loginMethod() {
        GameSdkLogic.getInstance().sdkLogin(this, new SdkCallbackListener<String>() {
            @Override
            public void callback(int code, String response) {
                switch (code) {
                    case SDKStatusCode.SUCCESS:
                        Toast.makeText(SdkDemoActivity.this, ConstData.LOGIN_SUCCESS,Toast.LENGTH_SHORT).show();
                        //这里就可以获取登录成功以后的信息:
                        LoggerUtils.i( "login callBack data : "+response);
                        break;
                    case SDKStatusCode.FAILURE:
                        Toast.makeText(SdkDemoActivity.this, ConstData.LOGIN_FAILURE,Toast.LENGTH_SHORT).show();
                        break;
                    case SDKStatusCode.OTHER:
                        Toast.makeText(SdkDemoActivity.this, ConstData.LOGIN_FAILURE,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    //提交 Player 信息 上传玩家信息
    private void subGameInfoMethod() {
        MVPPlayerBean player = new MVPPlayerBean();
        player.setGameName("梦幻西游");
        player.setName("骑小猪看流星");
        player.setServer("齐云楼");
        player.setId("1010");
        //player.setXXX.......
        GameSdkLogic.getInstance().subGameInfoMethod(player);

    }
    //支付：
    private void payMethod() {
        MVPPayBean payBean = new MVPPayBean();
        payBean.setDes("这是支付");
        payBean.setGameName("梦幻西游");
        payBean.setId("520520");
        payBean.setPayMoney(20.0);
        payBean.setUserName("骑小猪看流星");
        payBean.setSign("iLoveYou");
        payBean.setTime("20180606");

        GameSdkLogic.getInstance().sdkPay(this, payBean, new SdkCallbackListener<String>() {
            @Override
            public void callback(int code, String response) {
                switch (code) {
                    //支付成功：
                    case SDKStatusCode.PAY_SUCCESS:
                        Toast.makeText(SdkDemoActivity.this, ConstData.PAY_SUCCESS,Toast.LENGTH_SHORT).show();
                        LoggerUtils.i( "login callBack data : "+response);
                        break;
                    //支付失败：
                    case SDKStatusCode.PAY_FAILURE:
                        Toast.makeText(SdkDemoActivity.this, ConstData.PAY_FAILURE,Toast.LENGTH_SHORT).show();
                        break;
                    //支付取消：
                    case SDKStatusCode.PAY_OTHER:
                        Toast.makeText(SdkDemoActivity.this, ConstData.PAY_FAILURE,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void findView() {
        loginButton = findViewById(R.id.gameLoginBtn);
        payButton = findViewById(R.id.gamePayBtn);
        myBlogButton = findViewById(R.id.subInfo);
        subInfoButton = findViewById(R.id.myBlog);
        aboutDesButton = findViewById(R.id.aboutDes);
        testLogin = findViewById(R.id.testLogin);

        loginButton.setOnClickListener(this);
        payButton.setOnClickListener(this);
        myBlogButton.setOnClickListener(this);
        subInfoButton.setOnClickListener(this);
        aboutDesButton.setOnClickListener(this);
        testLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gameLoginBtn:
                loginMethod();
                break;
            case R.id.gamePayBtn:
                payMethod();
                break;
            case R.id.subInfo:
                subGameInfoMethod();
                break;
            case R.id.myBlog:

                jumpActivity(X5InfoActivity.class,bolgUrl);
                break;
            case R.id.aboutDes:
                jumpActivity(X5InfoActivity.class,desUrl);
                break;
            case R.id.testLogin:
                break;
        }
    }



    protected void jumpActivity(Class<?> mClass,String data){
        Intent intent = new Intent(this, mClass);
        intent.putExtra("key",data);
        startActivity(intent);
    }

}
