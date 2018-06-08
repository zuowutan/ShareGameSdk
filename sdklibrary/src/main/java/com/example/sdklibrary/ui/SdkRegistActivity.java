package com.example.sdklibrary.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sdklibrary.R;
import com.example.sdklibrary.base.SdkBaseActivity;
import com.example.sdklibrary.call.Delegate;
import com.example.sdklibrary.config.SDKStatusCode;
import com.example.sdklibrary.mvp.Imp.LoginPresenterImp;
import com.example.sdklibrary.mvp.Imp.RegistPresenterImp;
import com.example.sdklibrary.mvp.model.MVPLoginBean;
import com.example.sdklibrary.mvp.model.MVPRegisterBean;
import com.example.sdklibrary.mvp.view.MVPLoginView;
import com.example.sdklibrary.mvp.view.MVPRegistView;
import com.example.sdklibrary.tools.LoggerUtils;

/**
 * Created by tzw on 2018/6/4.
 * 注册
 */

public class SdkRegistActivity extends SdkBaseActivity implements MVPRegistView{

    private EditText username,passWord,secpassWord;
    private Button register;
    private ImageView goback;

    private RegistPresenterImp registPresenterImp;
    private String mUserName,mPassWord,mSecPassWord;
    protected boolean accountTag,passwordTag,secpasswordTag;

    private final int ACCOUNT_MAX_LENGTH = 20;
    private final int ACCOUNT_MIN_LENGTH = 4;
    private final int PASSWORD_MAX_LENGTH = 20;
    private final int PASSWORD_MIN_LENGTH = 4;

    private final String LOGIN_FORMERROR = "帐号/密码长度格式错误";
    private final String LENGTH_EMPTY = "请检查帐号/密码输入";
    private final String CONTENT_ERROR = "两次密码输入不一致";

    @Override
    public int getLayoutId() {
        return R.layout.regist;
    }

    @Override
    public void initViews() {
        username =  $(R.id.registusername);
        passWord =  $(R.id.registerpassword);
        secpassWord =  $(R.id.secondpwd);
        register = $(R.id.regist);
        goback = $(R.id.goback);

    }

    @Override
    public void initListener() {
        setOnClick(register);
        setOnClick(goback);
    }

    @Override
    public void initData() {
        registPresenterImp = new RegistPresenterImp();
        registPresenterImp.attachView(this);
    }

    @Override
    public void processClick(View v) {
        int id = v.getId();
        if (id == R.id.regist){
            registeMethod();
        }else if (id == R.id.goback){
            goBackMainUI();
        }
    }

    private void goBackMainUI() {

        startActivity(new Intent(this,SdkLoginActivity.class));
    }

    private void registeMethod(){

        mUserName = username.getText().toString().trim();
        mPassWord = passWord.getText().toString().trim();
        mSecPassWord = secpassWord.getText().toString().trim();

        accountTag = (mUserName.length() > ACCOUNT_MIN_LENGTH) && (mUserName.length() < ACCOUNT_MAX_LENGTH);
        passwordTag = (mPassWord.length() > PASSWORD_MIN_LENGTH) && (mPassWord.length() < PASSWORD_MAX_LENGTH);
        secpasswordTag = (mSecPassWord.length() > PASSWORD_MIN_LENGTH) && (mSecPassWord.length() < PASSWORD_MAX_LENGTH);

        if ((TextUtils.isEmpty(mUserName)) && (TextUtils.isEmpty(mPassWord))  &&(TextUtils.isEmpty(mSecPassWord) ) ) {
            showToast(LENGTH_EMPTY);
            return;
        } else if ( ! mPassWord.equals(mPassWord) ){
            showToast(CONTENT_ERROR);
            return;
        }
        else {
            if (accountTag && passwordTag && secpasswordTag) {
                MVPRegisterBean bean = new MVPRegisterBean(mUserName, mPassWord,mSecPassWord);
                registPresenterImp.regist(bean, SdkRegistActivity.this);
            } else {
                showToast(LOGIN_FORMERROR);
                return;
            }
        }
    }

    @Override
    public void registSuccess(String msg, String data) {
        Delegate.listener.callback(SDKStatusCode.SUCCESS,"regist success");
        LoggerUtils.i("注册成功");
    }

    @Override
    public void registFailed(String msg, String data) {
        Delegate.listener.callback(SDKStatusCode.FAILURE,"regist failure");
        LoggerUtils.i("注册失败");
    }


    @Override
    public void showAppInfo(String msg, String data) {
        showToast(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registPresenterImp.detachView();
    }

}
