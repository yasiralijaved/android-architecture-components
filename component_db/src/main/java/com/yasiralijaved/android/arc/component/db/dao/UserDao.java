package com.yasiralijaved.android.arc.component.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yasiralijaved.android.arc.component.db.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM "+ UserEntity.TABLE_NAME)
    LiveData<List<UserEntity>> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<UserEntity> userEntities);
}
