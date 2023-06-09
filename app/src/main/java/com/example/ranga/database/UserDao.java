package com.example.ranga.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao
{
    @Query("SELECT * FROM User WHERE login = :login")
    User getByLogin(String login);

    @Insert
    void insert(User user);

    @Update
    void update(User user);
}
