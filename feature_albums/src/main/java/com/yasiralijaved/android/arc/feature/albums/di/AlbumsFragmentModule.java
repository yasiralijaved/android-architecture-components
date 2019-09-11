package com.yasiralijaved.android.arc.feature.albums.di;

import com.yasiralijaved.android.arc.feature.albums.fragment.AlbumsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AlbumsFragmentModule {
    /*
     * We define the name of the Fragment we are going
     * to inject the ViewModelFactory into. i.e. in our case
     * The name of the fragment: AlbumsFragment
     */

    @ContributesAndroidInjector
    abstract AlbumsFragment contributeAlbumsFragment();
}
