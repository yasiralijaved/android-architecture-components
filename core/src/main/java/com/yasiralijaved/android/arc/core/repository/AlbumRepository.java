package com.yasiralijaved.android.arc.core.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.yasiralijaved.android.arc.component.db.dao.AlbumDao;
import com.yasiralijaved.android.arc.component.db.dao.UserDao;
import com.yasiralijaved.android.arc.component.db.entity.AlbumEntity;
import com.yasiralijaved.android.arc.component.db.entity.UserEntity;
import com.yasiralijaved.android.arc.component.http.ApiResponse;
import com.yasiralijaved.android.arc.component.http.AppExecutors;
import com.yasiralijaved.android.arc.component.http.BackendService;
import com.yasiralijaved.android.arc.component.http.response.Album;
import com.yasiralijaved.android.arc.component.http.response.User;
import com.yasiralijaved.android.arc.core.utils.NetworkBoundResource;
import com.yasiralijaved.android.arc.core.utils.Resource;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class AlbumRepository {

    private AlbumDao mAlbumDao;
    private BackendService mBackendService;

    public AlbumRepository(AlbumDao albumDao, BackendService backendService) {
        this.mAlbumDao = albumDao;
        this.mBackendService = backendService;
    }

    public LiveData<Resource<List<AlbumEntity>>> getAlbumsList(boolean forceUpdate, int userId) {
        return new NetworkBoundResource<List<AlbumEntity>, List<Album>>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull List<Album> albumsList) {
                List<AlbumEntity> albumEntities = Album.mapHttpResponse(albumsList);
                // First remove the already added similar albums
                mAlbumDao.deleteAlbums(albumEntities);
                // Insert fresh data of the downloaded albums
                mAlbumDao.insertAlbums(albumEntities);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<AlbumEntity> data) {
                return forceUpdate || data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<AlbumEntity>> loadFromDb() {
                return mAlbumDao.getAlbumsByUserId(userId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<Album>>> createCall() {
                return mBackendService.listAlbums(userId);
            }
        }.asLiveData();
    }
}
