package com.yasiralijaved.android.arc.components.di;

import com.yasiralijaved.android.arc.feature.users.fragment.UsersListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class FragmentModule {
    /*
     * We define the name of the Fragment we are going
     * to inject the ViewModelFactory into. i.e. in our case
     * The name of the fragment: MovieListFragment
     */
    @ContributesAndroidInjector
    abstract UsersListFragment contributeUsersListFragment();
}
