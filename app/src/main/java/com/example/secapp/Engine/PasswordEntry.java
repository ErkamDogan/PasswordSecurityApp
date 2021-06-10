package com.example.secapp.Engine;

public class PasswordEntry {
    private String appName;
    private String password;

    public PasswordEntry(String appName, String password) {
        this.appName = appName;
        this.password = password;
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
}
