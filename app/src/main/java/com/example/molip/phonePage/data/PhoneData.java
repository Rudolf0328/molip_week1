package com.example.molip.phonePage.data;

public class PhoneData {
    private String profileRes, name, phoneNum;

    public PhoneData() {}

    public PhoneData(String profileRes, String name, String phoneNum) {
        this.profileRes = profileRes;
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public String getProfileRes() {
        return profileRes;
    }

    public void setProfileRes(String profileRes) {
        this.profileRes = profileRes;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
