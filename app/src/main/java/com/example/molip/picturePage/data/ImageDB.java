package com.example.molip.picturePage.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.molip.phonePage.data.Contact;
import com.example.molip.phonePage.data.ContactDB;
import com.example.molip.phonePage.data.ContactDao;

@Database(entities = {Image.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class ImageDB extends RoomDatabase {
    private static ImageDB INSTANCE = null;

    public abstract ImageDao imageDao();


    public static ImageDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ImageDB.class, "image.db").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
