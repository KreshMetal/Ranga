package com.example.ranga;

import android.app.Application;
import com.example.ranga.database.AppDataBase;
import com.example.ranga.database.User;

import androidx.room.Room;

public class App extends Application
{
    public static App instance;
    private AppDataBase database;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDataBase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }
}
