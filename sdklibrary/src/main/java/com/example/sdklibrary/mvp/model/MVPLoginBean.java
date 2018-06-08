package com.example.sdklibrary.mvp.model;

/**
 * Created by tzw on 2018/6/5.
 * 登录
 */

public class MVPLoginBean {

    private String userName;
    private String passWord;

    public MVPLoginBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MVPLoginBean(String userName, String passWord) {
        super();
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "MVPLoginBean [userName=" + userName + ", passWord=" + passWord
                + "]";
    }
}
