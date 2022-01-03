package com.example.molip.picturePage.data;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.ByteArrayOutputStream;

@Entity
public class Image {
    @PrimaryKey(autoGenerate = true)
    public int imageId;

    @ColumnInfo(name="img")
    private Bitmap img;

    @Override
    public String toString() {
        return "Contact{" +
                "imageId=" + imageId +
                ", profile='" + img + '\'' +
                '}';
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
