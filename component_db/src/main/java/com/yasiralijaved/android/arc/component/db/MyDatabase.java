package com.yasiralijaved.android.arc.component.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yasiralijaved.android.arc.component.db.dao.UserDao;
import com.yasiralijaved.android.arc.component.db.entities.UserEntity;

@Database(entities = {UserEntity.class},
        version = 2)
public abstract class MyDatabase extends RoomDatabase {
    private static volatile MyDatabase INSTANCE;

    public static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "my_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();
}
