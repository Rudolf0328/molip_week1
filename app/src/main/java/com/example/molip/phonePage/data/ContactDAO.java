package com.example.molip.phonePage.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {
    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Query("SELECT * FROM contact WHERE contactId IN (:contactId)")
    List<Contact> loadAllByIds(int[] contactId);

    @Query("SELECT * FROM contact WHERE contactId IN (:contactId)")
    Contact getContact(int contactId);

    @Insert
    void insert(Contact... contact);

    @Delete
    void delete(Contact contact);
}