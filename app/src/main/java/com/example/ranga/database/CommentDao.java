package com.example.ranga.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CommentDao
{

    @Query("SELECT * FROM comment WHERE comixid = :id")
    LiveData<List<Comment>> getByIdComix(long id);

    @Insert
    void insert(Comment evaluation);

}
