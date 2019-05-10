package com.yasiralijaved.android.arc.feature.users.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.yasiralijaved.android.arc.component.db.entities.UserEntity;
import com.yasiralijaved.android.arc.core.repositories.UserRepository;
import com.yasiralijaved.android.arc.core.utils.Resource;
import com.yasiralijaved.android.arc.core.utils.SingleLiveEvent;

import java.util.List;

public class UsersListViewModel extends AndroidViewModel {

    private SingleLiveEvent<Void> goToUserDetailCommand = new SingleLiveEvent<>();
    private MutableLiveData<Boolean> getUsersListCommand = new MutableLiveData<>();

    public UsersListViewModel(@NonNull Application application) {
        super(application);
        usersListLiveData = Transformations.switchMap(getUsersListCommand,
                forceUpdate -> UserRepository.getInstance(application.getApplicationContext()).getUsersList(forceUpdate));
    }

    /* START - Launch UserEntity Detail */
    public MutableLiveData<Void> getUserDetailCommand() {
        return goToUserDetailCommand;
    }

    public void goToUserDetail() {
        //goToUserDetailCommand.postValue(true);
    }
    /* END - Launch UserEntity Detail */

    /* START - Get Users List */
    // Users List Observable Field
    private LiveData<Resource<List<UserEntity>>> usersListLiveData;
    public LiveData<Resource<List<UserEntity>>> getUsersListLiveData() {
        if(usersListLiveData == null) usersListLiveData = new MutableLiveData<>();
        return usersListLiveData;
    }

    public void loadUsersList(boolean forceUpdate) {
        getUsersListCommand.setValue(forceUpdate);
    }
    /* END - Get Users List */
}
