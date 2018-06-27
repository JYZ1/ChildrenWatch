package com.jyz.childrenwatch.dto;

/**
 * Created by Administrator on 2018/6/22.
 */

public class SmsDto {
    private String phoneNo;
    private Integer smsType;
    private String content;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
