package com.example.mvvmkg.model;

public class Settings {
    private String url;
    private String port;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Settings(String url, String port) {
        this.url = url;
        this.port = port;
    }
}
