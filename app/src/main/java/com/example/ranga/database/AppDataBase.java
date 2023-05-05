package com.example.ranga.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Comix.class, Evaluation.class, Comment.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase
{
    public abstract UserDao userDao();
    public abstract ComixDao comixDao();
    public abstract EvalutationDao evalutationDao();
    public abstract  CommentDao commentDao();
}