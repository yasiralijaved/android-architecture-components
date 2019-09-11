package com.yasiralijaved.android.arc.feature.users.fragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasiralijaved.android.arc.feature.albums.fragment.AlbumsFragment;
import com.yasiralijaved.android.arc.feature.users.R;
import com.yasiralijaved.android.arc.feature.users.viewmodel.UserDetailViewModel;

public class UserDetailFragment extends Fragment {

    private UserDetailViewModel mViewModel;

    public static UserDetailFragment newInstance() {
        return new UserDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.user_detail_fragment, container, false);
        AlbumsFragment albumsFragment = new AlbumsFragment();
        getChildFragmentManager().beginTransaction().add(R.id.content_parent, albumsFragment).commit();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserDetailViewModel.class);
        setHasOptionsMenu(true);
    }

}
