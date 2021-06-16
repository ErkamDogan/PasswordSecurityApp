package com.example.secapp.Engine;

public class PasswordEntry {
    private String appName;
    private String password;
    private String id;

    public PasswordEntry(String appName, String password, String id) {
        this.appName = appName;
        this.password = password;
        this.id = id;
    }

    public PasswordEntry(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
