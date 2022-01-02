package com.example.molip.phonePage.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    ArrayList<Contact> getAll();

    @Query("SELECT * FROM contact WHERE id in (:contactId)")
    Contact getContactById(int contactId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact contact);

    @Delete
    void delete(Contact contact);
//
//    @Query("SELECT COUNT(*) FROM contact")
//    LiveData<Integer> getDataCount();
}
