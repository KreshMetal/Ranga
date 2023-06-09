package com.example.ranga.database;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Comix.class, parentColumns = "id", childColumns = "comix_id", onDelete = CASCADE))
public class Evaluation
{
    @PrimaryKey(autoGenerate = true)
    public long id;
    @Embedded(prefix = "user")
    public User user;
    @ColumnInfo(name = "comix_id")
    public long comixId;
    public long rating;
}
