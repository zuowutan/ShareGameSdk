package com.example.sdklibrary.mvp.Imp;

import android.content.Context;
import android.text.TextUtils;

import com.example.sdklibrary.callback.SdkCallbackListener;
import com.example.sdklibrary.config.ConstData;
import com.example.sdklibrary.config.SDKStatusCode;
import com.example.sdklibrary.config.HttpUrlConstants;
import com.example.sdklibrary.config.LogTAG;
import com.example.sdklibrary.mvp.model.MVPLoginResultBean;
import com.example.sdklibrary.mvp.model.MVPRegistResultBean;
import com.example.sdklibrary.mvp.model.MVPRegisterBean;
import com.example.sdklibrary.mvp.presenter.RegistPresenter;
import com.example.sdklibrary.mvp.view.MVPRegistView;
import com.example.sdklibrary.tools.GsonUtils;
import com.example.sdklibrary.tools.HttpRequestUtil;
import com.example.sdklibrary.tools.LoggerUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tzw on 2018/6/5.
 * 注册逻辑类 请求---响应判断---接口回调
 */

public class RegistPresenterImp implements RegistPresenter {

    private String userName;
    private String passWord;
    private String sepassWord;

    private MVPRegistView mvpRegistView;

    @Override
    public void attachView(MVPRegistView mvpRegistView) {
        this.mvpRegistView = mvpRegistView;
    }

    @Override
    public void regist(MVPRegisterBean user, Context context) {
        userName = user.getUserName().toString().trim();
        passWord = user.getPassWord().toString().trim();
        sepassWord = user.getSepassWord().toString().trim();

        if ((!TextUtils.isEmpty(userName)) && (!TextUtils.isEmpty(passWord)) && (!TextUtils.isEmpty(sepassWord))) {
            registMethod( HttpUrlConstants.getRegisterUrl(),userName,passWord,sepassWord);
        } else {
            mvpRegistView.showAppInfo("","帐号或密码输入为空");
        }
    }

    private void registMethod(String url,String userName,String passWord,String pwd){
        Map<String,String> map = new HashMap<>();
        map.put("username",userName);
        map.put("password",passWord);
        map.put("repassword",passWord);

        HttpRequestUtil.okPostFormRequest(url, map, new HttpRequestUtil.DataCallBack() {
            @Override
            public void requestSuccess(String result) throws Exception {
                LoggerUtils.i(LogTAG.login,"responseBody:"+result);

                MVPRegistResultBean bean = GsonUtils.GsonToBean(result, MVPRegistResultBean.class);

                int dataCode =  bean.getErrorCode();
                String msg = bean.getErrorMsg();

                if (dataCode == 0){
                    mvpRegistView.registSuccess(ConstData.REGIST_SUCCESS,result);
                    LoggerUtils.i(LogTAG.register,"regist Success");

                }else {
                    mvpRegistView.registFailed(ConstData.REGIST_FAILURE,msg);
                    LoggerUtils.i(LogTAG.register,"regist Failure");
                }
            }

            @Override
            public void requestFailure(String request, IOException e) {
                mvpRegistView.registFailed(ConstData.REGIST_FAILURE,HttpUrlConstants.SERVER_ERROR);
            }

            @Override
            public void requestNoConnect(String msg, String data) {
                mvpRegistView.registFailed(ConstData.REGIST_FAILURE,HttpUrlConstants.NET_NO_LINKING);
            }
        });
    }

    @Override
    public void detachView() {
        this.mvpRegistView = null;
    }

}
