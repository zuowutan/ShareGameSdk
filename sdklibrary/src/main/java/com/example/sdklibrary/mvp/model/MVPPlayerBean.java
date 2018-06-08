package com.example.sdklibrary.mvp.model;

/**
 * Created by tzw on 2018/6/7.
 */

public class MVPPlayerBean {
    private String name;
    private String server;
    private String gameName;
    private String id;

    public MVPPlayerBean() {
    }

    public MVPPlayerBean(String name, String server, String gameName, String id) {
        this.name = name;
        this.server = server;
        this.gameName = gameName;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MVPPlayerBean{" +
                "name='" + name + '\'' +
                ", server='" + server + '\'' +
                ", gameName='" + gameName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


}
