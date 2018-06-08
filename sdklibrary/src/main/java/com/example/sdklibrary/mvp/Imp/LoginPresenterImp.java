package com.example.sdklibrary.mvp.Imp;

import android.content.Context;
import android.text.TextUtils;
import com.example.sdklibrary.callback.SdkCallbackListener;
import com.example.sdklibrary.config.ConstData;
import com.example.sdklibrary.config.SDKStatusCode;
import com.example.sdklibrary.config.HttpUrlConstants;
import com.example.sdklibrary.config.LogTAG;
import com.example.sdklibrary.mvp.model.MVPLoginBean;
import com.example.sdklibrary.mvp.model.MVPLoginResultBean;
import com.example.sdklibrary.mvp.presenter.LoginPresenter;
import com.example.sdklibrary.mvp.view.MVPLoginView;
import com.example.sdklibrary.tools.GsonUtils;
import com.example.sdklibrary.tools.HttpRequestUtil;
import com.example.sdklibrary.tools.LoggerUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tzw on 2018/6/5.
 * 登录逻辑类 请求---响应判断---
 * 通过MVPLoginView将结果回调出去给View
 *
 */

public class LoginPresenterImp  implements LoginPresenter {

    private String userName;
    private String passWord;
    private MVPLoginView mvpLoginView;

    @Override
    public void attachView(MVPLoginView mvpLoginView) {
        this.mvpLoginView = mvpLoginView;
    }

    @Override
    public void login(MVPLoginBean user, Context context) {
        userName = user.getUserName().toString().trim();
        passWord = user.getPassWord().toString().trim();

        if ((!TextUtils.isEmpty(userName)) && (!TextUtils.isEmpty(passWord))) {
            loginMethod( HttpUrlConstants.getLoginUrl(),userName,passWord );
        } else {
            mvpLoginView.showAppInfo("","帐号或密码输入为空");
        }

    }
    //测试登录账号：xiaowu 123456
    private void loginMethod(String url,String userName,String passWord){
        Map<String,String> map = new HashMap<>();
        map.put("username",userName);
        map.put("password",passWord);

        HttpRequestUtil.okPostFormRequest(url, map, new HttpRequestUtil.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                LoggerUtils.i(LogTAG.login,"responseBody:"+result);
                MVPLoginResultBean mvpLoginResultBean = GsonUtils.GsonToBean(result, MVPLoginResultBean.class);

                int dataCode =  mvpLoginResultBean.getErrorCode();
                String msg = mvpLoginResultBean.getErrorMsg();

                if (dataCode == 0){
                    mvpLoginView.loginSuccess(ConstData.LOGIN_SUCCESS,result);
                    LoggerUtils.i(LogTAG.login,"responseBody: login Success");

                }else {
                    mvpLoginView.loginFailed(ConstData.LOGIN_FAILURE,msg);
                    LoggerUtils.i(LogTAG.login,"responseBody: login Failure");
                }
            }

            @Override
            public void requestFailure(String request, IOException e) {
                mvpLoginView.loginFailed(HttpUrlConstants.SERVER_ERROR,HttpUrlConstants.SERVER_ERROR);
            }

            @Override
            public void requestNoConnect(String msg, String data) {
                mvpLoginView.loginFailed(HttpUrlConstants.NET_NO_LINKING,HttpUrlConstants.NET_NO_LINKING);
            }
        });
    }

    @Override
    public void detachView() {
        this.mvpLoginView = null;
    }


}
