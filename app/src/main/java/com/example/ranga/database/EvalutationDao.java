package com.example.ranga.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EvalutationDao
{
    @Query("SELECT * FROM evaluation")
    List<Evaluation> getAll();

    @Query("SELECT * FROM evaluation WHERE comixid = :id")
    LiveData<List<Evaluation>> getByIdComix(long id);

    @Query("SELECT * FROM evaluation WHERE comixid = :idcomix AND userid = :iduser")
    Evaluation getByIdComixForUser(long idcomix, long iduser);

    @Insert
    void insert(Evaluation evaluation);

    @Update
    void update(Evaluation evaluation);
}
