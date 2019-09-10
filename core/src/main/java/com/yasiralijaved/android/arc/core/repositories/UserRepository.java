package com.yasiralijaved.android.arc.core.repositories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.yasiralijaved.android.arc.component.db.dao.UserDao;
import com.yasiralijaved.android.arc.component.db.entities.UserEntity;
import com.yasiralijaved.android.arc.component.http.ApiResponse;
import com.yasiralijaved.android.arc.component.http.AppExecutors;
import com.yasiralijaved.android.arc.component.http.BackendService;
import com.yasiralijaved.android.arc.component.http.responses.User;
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

//    public synchronized static UserRepository getInstance(Context context) {
//        if (mInstance == null) {
//            mInstance = new UserRepository();
//            mInstance.mUserDao = MyDatabase.getDatabase(context).userDao();
//
//            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//
//            OkHttpClient httpClient = builder.connectTimeout(10, TimeUnit.SECONDS)
//                    .readTimeout(10, TimeUnit.SECONDS)
//                    .writeTimeout(10, TimeUnit.SECONDS)
//                    .addInterceptor(loggingInterceptor)
//                    .build();
//
//            Gson gson = new GsonBuilder()
//                    .create();
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .client(httpClient)
//                    .baseUrl("https://jsonplaceholder.typicode.com/")
//                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//
//            mBackendService = retrofit.create(BackendService.class);
//
//        }
//        return mInstance;
//    }

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
