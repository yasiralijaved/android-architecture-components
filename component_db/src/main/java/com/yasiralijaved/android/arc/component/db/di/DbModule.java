package com.yasiralijaved.android.arc.component.db.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.yasiralijaved.android.arc.component.db.MyDatabase;
import com.yasiralijaved.android.arc.component.db.dao.AlbumDao;
import com.yasiralijaved.android.arc.component.db.dao.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    MyDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                MyDatabase.class, "my_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(@NonNull MyDatabase database) {
        return database.userDao();
    }

    @Provides
    @Singleton
    AlbumDao provideAlbumDao(@NonNull MyDatabase database) {
        return database.albumDao();
    }
}
