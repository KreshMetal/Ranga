package com.example.ranga.database;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Evaluation
{
    @PrimaryKey(autoGenerate = true)
    public long id;
    @Embedded(prefix = "user")
    public User user;
    @Embedded(prefix = "comix")
    public Comix comix;
    public long rating;
}
