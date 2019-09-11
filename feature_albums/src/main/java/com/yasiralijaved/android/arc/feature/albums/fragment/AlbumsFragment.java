package com.yasiralijaved.android.arc.feature.albums.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasiralijaved.android.arc.core.di.ViewModelFactory;
import com.yasiralijaved.android.arc.feature.albums.adapter.AlbumsAdapter;
import com.yasiralijaved.android.arc.feature.albums.databinding.AlbumsFragmentBinding;
import com.yasiralijaved.android.arc.feature.albums.viewmodel.AlbumsViewModel;
import com.yasiralijaved.android.arc.feature.albums.R;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class AlbumsFragment extends Fragment {

    @Inject
    ViewModelFactory viewModelFactory;

    private AlbumsViewModel mViewModel;
    private int userId;

    public static AlbumsFragment newInstance(int userId) {
        return new AlbumsFragment(userId);
    }

    private AlbumsFragment(int userId) {
        this.userId = userId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(AlbumsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        AlbumsFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.albums_fragment,
                container,
                false);

        binding.setLifecycleOwner(this);
        binding.setViewmodel(mViewModel);

        View rootView = binding.getRoot();
        // Set RecyclerView Adapter
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_albums);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create and set User Adapter
        AlbumsAdapter adapter = new AlbumsAdapter(null);
        recyclerView.setAdapter(adapter);

        // Initialize Swipe Refresh Functionality
        SwipeRefreshLayout swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_albums);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Load fresh data because user forcefully want fresh data
            mViewModel.loadAlbumsList(true, 1);
        });


        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadAlbumsForUser(userId);
    }

    private void loadAlbumsForUser(int userId) {
        // Initially, load the cached data only
        mViewModel.loadAlbumsList(false, userId);
    }

}
