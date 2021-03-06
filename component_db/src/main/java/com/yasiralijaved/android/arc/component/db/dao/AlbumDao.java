package com.yasiralijaved.android.arc.component.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yasiralijaved.android.arc.component.db.entity.AlbumEntity;
import com.yasiralijaved.android.arc.component.db.entity.UserEntity;

import java.util.List;

@Dao
public interface AlbumDao {

    @Query("SELECT * FROM "+ AlbumEntity.TABLE_NAME)
    LiveData<List<AlbumEntity>> getAlbums();


    @Query("SELECT * FROM "+ AlbumEntity.TABLE_NAME + " WHERE userId=:userId")
    LiveData<List<AlbumEntity>> getAlbumsByUserId(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbums(List<AlbumEntity> albumEntities);

    @Delete
    void deleteAlbums(List<AlbumEntity> albumEntities);

    @Query("DELETE FROM " + AlbumEntity.TABLE_NAME)
    void deleteAll();
}
