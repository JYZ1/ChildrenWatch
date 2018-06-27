package com.jyz.childrenwatch.Entity;

public class BaseEn {
    private Long userAccount;
    private String userPassword;
    private String imei;
    private String  imsi;
    private String protocolVer;
    private String appTime;

    public Long getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Long userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getProtocolVer() {
        return protocolVer;
    }

    public void setProtocolVer(String protocolVer) {
        this.protocolVer = protocolVer;
    }

    public String getAppTime() {
        return appTime;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }
}
