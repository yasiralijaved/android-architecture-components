package com.yasiralijaved.android.arc.feature.users.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.yasiralijaved.android.arc.core.SingleLiveEvent;

public class UsersListViewModel extends AndroidViewModel {

    private SingleLiveEvent<Void> goToUserDetailCommand = new SingleLiveEvent<>();

    public UsersListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Void> getUserDetailCommand() {
        return goToUserDetailCommand;
    }

    public void goToUserDetail() {
        goToUserDetailCommand.postValue(null);
    }
}
