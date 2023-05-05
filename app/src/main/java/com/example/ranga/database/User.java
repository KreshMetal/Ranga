package com.example.ranga.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User
{
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String login;
    public String email;
    public String pass;
    public long avatar;
}
