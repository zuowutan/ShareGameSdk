package com.example.sdklibrary.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sdklibrary.R;
import com.example.sdklibrary.base.SdkBaseActivity;

/**
 * Created by tzw on 2018/6/8.
 * 支付Activtiy
 * 这里没有接口，所以需要自己完成
 */

public class SdkPayActivity extends SdkBaseActivity {
    private RadioGroup mRadioGroup;
    private RadioButton mAlipay;
    private RadioButton mWechatpay;
    private RadioButton mPlatCoinpay;
    private Button mPay;
    private ImageView mBack;

    private String payTAG = "1";

    @Override
    public int getLayoutId() {
        return R.layout.pay;
    }

    @Override
    public void initViews() {
        mRadioGroup = $(R.id.radioGrouppay);
        mAlipay = $(R.id.rbalipay);
        mWechatpay = $(R.id.rbwechatpay);
        mPlatCoinpay = $(R.id.rbplatcoinpay);
        mPlatCoinpay = $(R.id.rbplatcoinpay);

        mPay = $(R.id.pay);
        mBack = $(R.id.goback);
    }

    @Override
    public void initListener() {
        setOnClick(mRadioGroup);
        setOnClick(mAlipay);
        setOnClick(mWechatpay);
        setOnClick(mPlatCoinpay);
        setOnClick(mPay);
        setOnClick(mBack);
    }

    @Override
    public void initData() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (mAlipay.getId() == checkedId) {
                    payTAG = "1";
                } else if (mWechatpay.getId() == checkedId) {
                    payTAG = "2";
                } else if (mPlatCoinpay.getId() == checkedId) {
                    payTAG = "3";
                } else {

                }
            }
        });
    }

    @Override
    public void processClick(View v) {
        int id = v.getId();
        if (id == R.id.pay) {
            payMethod();
        } else if (id == R.id.goback) {
            jumpActivity(SdkLoginActivity.class);
            finish();
        } else {

        }
    }

    private void payMethod() {

        if (payTAG.equals("1")) {
            alipayMethod();
        } else if (payTAG.equals("2")) {
            wechatpayMethod();
        } else if (payTAG.equals("3")) {
            coinpayMethod();
        } else {
        }
    }


    private void coinpayMethod() {
        Toast.makeText(this, "点击金币支付", Toast.LENGTH_LONG).show();
    }

    private void wechatpayMethod() {
        Toast.makeText(this, "点击微信支付", Toast.LENGTH_LONG).show();
    }

    private void alipayMethod() {
        Toast.makeText(this, "点击支付宝支付", Toast.LENGTH_LONG).show();
    }
}
