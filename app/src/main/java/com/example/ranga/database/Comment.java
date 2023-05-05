package com.example.ranga.database;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Comment
{
    @PrimaryKey(autoGenerate = true)
    public long id;
    @Embedded(prefix = "user")
    public User user;
    @Embedded(prefix = "comix")
    public Comix comix;
    public String text;
}
