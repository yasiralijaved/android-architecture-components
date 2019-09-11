package com.yasiralijaved.android.arc.components.di;

import com.yasiralijaved.android.arc.components.MainActivity;
import com.yasiralijaved.android.arc.feature.albums.di.AlbumsFragmentModule;
import com.yasiralijaved.android.arc.feature.users.di.UsersFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = {UsersFragmentModule.class, AlbumsFragmentModule.class})
    abstract MainActivity contributeMainActivity();
}
