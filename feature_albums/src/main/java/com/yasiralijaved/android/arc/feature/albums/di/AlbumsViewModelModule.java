package com.yasiralijaved.android.arc.feature.albums.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.yasiralijaved.android.arc.core.di.ViewModelFactory;
import com.yasiralijaved.android.arc.core.di.ViewModelKey;
import com.yasiralijaved.android.arc.feature.albums.viewmodel.AlbumsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AlbumsViewModelModule {

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
    @ViewModelKey(AlbumsViewModel.class)
    protected abstract ViewModel albumsViewModel(AlbumsViewModel albumsViewModel);
}