package com.jyz.childrenwatch.Entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/6/25.
 */

public class LoginResponse extends CommResponse {
    public static class Result {
        String serverTime;
        String serverInfo;
        String newVerInfo;
        String apkUrl;
        Integer serverInfoState;
        Integer versionState;
        float newVerCode;

        public String getServerTime() {
            return serverTime;
        }

        public void setServerTime(String serverTime) {
            this.serverTime = serverTime;
        }

        public String getServerInfo() {
            return serverInfo;
        }

        public void setServerInfo(String serverInfo) {
            this.serverInfo = serverInfo;
        }

        public String getNewVerInfo() {
            return newVerInfo;
        }

        public void setNewVerInfo(String newVerInfo) {
            this.newVerInfo = newVerInfo;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public Integer getServerInfoState() {
            return serverInfoState;
        }

        public void setServerInfoState(Integer serverInfoState) {
            this.serverInfoState = serverInfoState;
        }

        public Integer getVersionState() {
            return versionState;
        }

        public void setVersionState(Integer versionState) {
            this.versionState = versionState;
        }

        public float getNewVerCode() {
            return newVerCode;
        }

        public void setNewVerCode(float newVerCode) {
            this.newVerCode = newVerCode;
        }

        public List<Device> getDeviceList() {
            return deviceList;
        }

        public void setDeviceList(List<Device> deviceList) {
            this.deviceList = deviceList;
        }

        List<Device> deviceList;

    }

    class Device {
        Long deviceId;
        Long userAccount;
        String nickName;
        String relationship;
        Integer isManager;
        Integer isEmpowered;
        String devicePhoneNo;
        String imsi;
        String imei;
        String wifiMac;
        String btMac;
        Integer gatherTime;
        String iconUrl;
        String sex;
        Date birthday;
        float height;
        float weight;
        String grade;

        public Long getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(Long deviceId) {
            this.deviceId = deviceId;
        }

        public Long getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(Long userAccount) {
            this.userAccount = userAccount;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRelationship() {
            return relationship;
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }

        public Integer getIsManager() {
            return isManager;
        }

        public void setIsManager(Integer isManager) {
            this.isManager = isManager;
        }

        public Integer getIsEmpowered() {
            return isEmpowered;
        }

        public void setIsEmpowered(Integer isEmpowered) {
            this.isEmpowered = isEmpowered;
        }

        public String getDevicePhoneNo() {
            return devicePhoneNo;
        }

        public void setDevicePhoneNo(String devicePhoneNo) {
            this.devicePhoneNo = devicePhoneNo;
        }

        public String getImsi() {
            return imsi;
        }

        public void setImsi(String imsi) {
            this.imsi = imsi;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getWifiMac() {
            return wifiMac;
        }

        public void setWifiMac(String wifiMac) {
            this.wifiMac = wifiMac;
        }

        public String getBtMac() {
            return btMac;
        }

        public void setBtMac(String btMac) {
            this.btMac = btMac;
        }

        public Integer getGatherTime() {
            return gatherTime;
        }

        public void setGatherTime(Integer gatherTime) {
            this.gatherTime = gatherTime;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
    }
}
