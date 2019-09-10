package com.yasiralijaved.android.arc.component.http.response;

import com.yasiralijaved.android.arc.component.db.entity.AlbumEntity;

import java.util.ArrayList;
import java.util.List;

public class Album {

    private int id;
    private int userId;
    private String title;

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
        return "Album{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                '}';
    }

    public static AlbumEntity mapHttpResponse(Album album) {
        return new AlbumEntity(album.getId(),album.userId, album.title);
    }

    public static List<AlbumEntity> mapHttpResponse(List<Album> albumList) {

        List<AlbumEntity> albumEntities = new ArrayList<>();
        for(Album album : albumList) {
            albumEntities.add(mapHttpResponse(album));
        }
        return albumEntities;
    }
}
