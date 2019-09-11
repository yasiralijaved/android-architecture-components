package com.yasiralijaved.android.arc.feature.users.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.yasiralijaved.android.arc.component.db.dao.UserDao;
import com.yasiralijaved.android.arc.component.db.entity.UserEntity;
import com.yasiralijaved.android.arc.component.http.BackendService;
import com.yasiralijaved.android.arc.core.repository.UserRepository;
import com.yasiralijaved.android.arc.core.utils.Resource;
import com.yasiralijaved.android.arc.core.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

public class UsersListViewModel extends ViewModel {

    private UserRepository mUserRepository;
    private SingleLiveEvent<Void> goToUserDetailCommand = new SingleLiveEvent<>();
    private MutableLiveData<Boolean> getUsersListCommand = new MutableLiveData<>();

    /*
     * We are injecting the UserDao class
     * and the BackendService class to the ViewModel.
     * */
    @Inject
    UsersListViewModel(UserDao userDao, BackendService backendService) {
        super();
        mUserRepository = new UserRepository(userDao, backendService);
        usersListLiveData = Transformations.switchMap(getUsersListCommand,
                forceUpdate -> mUserRepository.getUsersList(forceUpdate));
    }

    /* START - Launch UserEntity Detail */
    public MutableLiveData<Void> getUserDetailCommand() {
        return goToUserDetailCommand;
    }

    public void goToUserDetail() {
        goToUserDetailCommand.postValue(null);
    }
    /* END - Launch UserEntity Detail */

    /* START - Get Users List */
    // Users List Observable Field
    private LiveData<Resource<List<UserEntity>>> usersListLiveData;
    public LiveData<Resource<List<UserEntity>>> getUsersListLiveData() {
        return usersListLiveData;
    }


    public void loadUsersList(boolean forceUpdate) {
        getUsersListCommand.setValue(forceUpdate);
    }
    /* END - Get Users List */
}
