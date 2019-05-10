package com.yasiralijaved.android.arc.component.http;

import androidx.lifecycle.LiveData;

import com.yasiralijaved.android.arc.component.http.responses.User;

import java.util.List;

import retrofit2.http.GET;

public interface BackendService {
    @GET("users")
    LiveData<ApiResponse<List<User>>> listUsers();
}
