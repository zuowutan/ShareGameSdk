package com.example.sdklibrary.mvp.model;

/**
 * Created by tzw on 2018/6/8.
 * 支付bean
 */

public class MVPPayBean {
    private String id;
    private String time;
    private String userName;
    private String des;
    private String sign;
    private double payMoney;
    private String gameName;

    public MVPPayBean() {
    }

    public MVPPayBean(String id, String time, String userName, String des, String sign, double payMoney, String gameName) {
        this.id = id;
        this.time = time;
        this.userName = userName;
        this.des = des;
        this.sign = sign;
        this.payMoney = payMoney;
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "MVPPayBean{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", userName='" + userName + '\'' +
                ", des='" + des + '\'' +
                ", sign='" + sign + '\'' +
                ", payMoney=" + payMoney +
                ", gameName='" + gameName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
