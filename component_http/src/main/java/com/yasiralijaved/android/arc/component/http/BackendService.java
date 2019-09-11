package com.yasiralijaved.android.arc.component.http;

import androidx.lifecycle.LiveData;

import com.yasiralijaved.android.arc.component.http.response.Album;
import com.yasiralijaved.android.arc.component.http.response.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BackendService {
    @GET("users")
    LiveData<ApiResponse<List<User>>> listUsers();

    @GET("albums")
    LiveData<ApiResponse<List<Album>>> listAlbums(@Query("userId") int userId);
}
