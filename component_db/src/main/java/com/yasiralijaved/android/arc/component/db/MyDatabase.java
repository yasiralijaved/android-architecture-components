package com.yasiralijaved.android.arc.component.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yasiralijaved.android.arc.component.db.dao.AlbumDao;
import com.yasiralijaved.android.arc.component.db.dao.UserDao;
import com.yasiralijaved.android.arc.component.db.entity.AlbumEntity;
import com.yasiralijaved.android.arc.component.db.entity.UserEntity;

@Database(entities = {UserEntity.class, AlbumEntity.class},
        version = 4)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract AlbumDao albumDao();
}
