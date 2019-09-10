package com.yasiralijaved.android.arc.core.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.yasiralijaved.android.arc.component.db.dao.UserDao;
import com.yasiralijaved.android.arc.component.db.entity.UserEntity;
import com.yasiralijaved.android.arc.component.http.ApiResponse;
import com.yasiralijaved.android.arc.component.http.AppExecutors;
import com.yasiralijaved.android.arc.component.http.BackendService;
import com.yasiralijaved.android.arc.component.http.response.User;
import com.yasiralijaved.android.arc.core.utils.NetworkBoundResource;
import com.yasiralijaved.android.arc.core.utils.Resource;

import java.util.List;

import javax.inject.Singleton;

@Singleton
public class UserRepository {

    private UserDao mUserDao;
    private BackendService mBackendService;

    public UserRepository(UserDao userDao, BackendService backendService) {
        this.mUserDao = userDao;
        this.mBackendService = backendService;
    }

    public LiveData<Resource<List<UserEntity>>> getUsersList(boolean forceUpdate) {
        return new NetworkBoundResource<List<UserEntity>, List<User>>(AppExecutors.getInstance()) {
            @Override
            protected void saveCallResult(@NonNull List<User> userList) {
                List<UserEntity> userEntities = User.mapHttpResponse(userList);
                mUserDao.insertUsers(userEntities);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<UserEntity> data) {
                return forceUpdate || data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<UserEntity>> loadFromDb() {
                return mUserDao.getUsers();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<List<User>>> createCall() {
                return mBackendService.listUsers();
            }
        }.asLiveData();
    }
}
