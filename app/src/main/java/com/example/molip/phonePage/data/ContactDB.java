package com.example.molip.phonePage.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDB extends RoomDatabase {
    private static ContactDB INSTANCE = null;

    public abstract ContactDao contactDAO();


    public static ContactDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDB.class, "contact.db").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
