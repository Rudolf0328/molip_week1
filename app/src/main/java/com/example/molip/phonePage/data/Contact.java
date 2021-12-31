package com.example.molip.phonePage.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "profile")
    public String profile;

    @ColumnInfo(name = "name")
    public String pname;

    @ColumnInfo(name = "phone")
    public String phone;
}
