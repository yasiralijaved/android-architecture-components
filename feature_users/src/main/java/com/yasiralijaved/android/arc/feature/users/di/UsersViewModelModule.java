package com.yasiralijaved.android.arc.feature.users.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yasiralijaved.android.arc.core.di.ViewModelFactory;
import com.yasiralijaved.android.arc.core.di.ViewModelKey;
import com.yasiralijaved.android.arc.feature.users.viewmodel.UsersListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class UsersViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);


    /*
     * This method basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  UsersListViewModel.class as key,
     * and a Provider that will build a UsersListViewModel
     * object.
     *
     * */

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel.class)
    protected abstract ViewModel usersListViewModel(UsersListViewModel usersListViewModel);
}