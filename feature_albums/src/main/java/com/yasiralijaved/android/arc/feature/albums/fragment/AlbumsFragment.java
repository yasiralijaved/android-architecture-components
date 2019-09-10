package com.yasiralijaved.android.arc.feature.albums.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasiralijaved.android.arc.feature.albums.viewmodel.AlbumsViewModel;
import com.yasiralijaved.android.arc.feature.albums.R;

public class AlbumsFragment extends Fragment {

    private AlbumsViewModel mViewModel;

    public static AlbumsFragment newInstance() {
        return new AlbumsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.albums_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AlbumsViewModel.class);
        // TODO: Use the ViewModel
    }

}
