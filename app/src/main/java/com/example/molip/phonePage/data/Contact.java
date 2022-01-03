package com.example.molip.phonePage.data;

import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public int contactId;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="phone")
    private String phone;

    @ColumnInfo(name="profile")
    private String profile;

    @ColumnInfo(name="ban")
    private String ban;

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", profile='" + profile + '\'' +
                ", ban='" + ban + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBan() {
        return ban;
    }

    public void setBan(String ban) {
        this.ban = ban;
    }
}
