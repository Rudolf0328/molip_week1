package com.example.molip.picturePage.data;

import android.graphics.Bitmap;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.molip.phonePage.data.Contact;

import java.util.List;

@Dao
public interface ImageDao {
    @Query("SELECT * FROM image")
    List<Image> getAll();

    @Query("SELECT * FROM image WHERE imageId IN (:imageId)")
    Image getContact(int imageId);

    @Insert
    void insert(Image... image);

    @Delete
    void delete(Image image);
}
