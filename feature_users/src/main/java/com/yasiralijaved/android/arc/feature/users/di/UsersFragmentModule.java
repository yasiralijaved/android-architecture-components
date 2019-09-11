package com.yasiralijaved.android.arc.feature.users.di;

import com.yasiralijaved.android.arc.feature.users.fragment.UsersListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UsersFragmentModule {
    /*
     * We define the name of the Fragment we are going
     * to inject the ViewModelFactory into. i.e. in our case
     * The name of the fragment: UsersListFragment
     */
    @ContributesAndroidInjector
    abstract UsersListFragment contributeUsersListFragment();
}
