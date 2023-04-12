package com.example.ranga.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ComixDao
{
    @Query("SELECT * FROM Comix")
    List<Comix> getAll();

    @Query("SELECT * FROM Comix")
    LiveData<List<Comix>> getAllLD();

    @Query("SELECT * FROM Comix WHERE id = :id")
    Comix getById(long id);

    @Insert
    void insert(Comix comix);

    @Update
    void update(Comix comix);
}
