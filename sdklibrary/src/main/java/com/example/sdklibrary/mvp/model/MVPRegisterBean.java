package com.example.sdklibrary.mvp.model;

/**
 * Created by tzw on 2018/6/5.
 * 注册
 */

public class MVPRegisterBean {

    private String userName;
    private String passWord;
    private String sepassWord;

    public MVPRegisterBean() {
        super();
        // TODO Auto-generated constructor stub
    }
    public MVPRegisterBean(String userName, String passWord, String sepassWord) {
        this.userName = userName;
        this.passWord = passWord;
        this.sepassWord = sepassWord;
    }
    @Override
    public String toString() {
        return "MVPRegisterBean{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", sepassWord='" + sepassWord + '\'' +
                '}';
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

    public String getSepassWord() {
        return sepassWord;
    }

    public void setSepassWord(String sepassWord) {
        this.sepassWord = sepassWord;
    }
}
