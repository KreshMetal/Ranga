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
    @Query("SELECT * FROM evaluation WHERE comix_id = :id")
    LiveData<List<Evaluation>> getByIdComix(long id);

    @Query("SELECT * FROM Evaluation WHERE userid = :user")
    LiveData<List<Evaluation>> getAllUserEvaluations(long user);

    @Query("SELECT * FROM evaluation WHERE comix_id = :idcomix AND userid = :iduser")
    Evaluation getByIdComixForUser(long idcomix, long iduser);

    @Insert
    void insert(Evaluation evaluation);

    @Update
    void update(Evaluation evaluation);
}
