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
    public String name;

    @ColumnInfo(name="phone")
    public String phone;

    @ColumnInfo(name="profile")
    public String profile;

    @ColumnInfo(name="ban")
    public String ban;

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
}
