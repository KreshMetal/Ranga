package com.example.ranga;

import android.app.Application;
import com.example.ranga.database.AppDataBase;
import androidx.room.Room;

public class App extends Application
{
    public static App instance;

    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDataBase.class, "database")
                .addMigrations(AppDataBase.MIGRATION_1_2)
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }
}
