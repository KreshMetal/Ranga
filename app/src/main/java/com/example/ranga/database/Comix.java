package com.example.ranga.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Comix
{
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "name_rus")
    public String nameRus;
    @ColumnInfo(name = "name_eng")
    public String nameEng;
    @ColumnInfo(name = "description")
    public String desc;
    @ColumnInfo(name = "name_folder")
    public String nameFolder;
    public long size;
    public String author;
}
