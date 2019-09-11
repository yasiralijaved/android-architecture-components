package com.yasiralijaved.android.arc.component.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AlbumEntity.TABLE_NAME)
public class AlbumEntity {
    public static final String TABLE_NAME = "album_table";
    public static final String COLUMN_ALBUM_ID = "album_id";

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ALBUM_ID)
    private int id;
    private int userId;
    private String title;

    public AlbumEntity(int id, int userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AlbumEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                '}';
    }
}
