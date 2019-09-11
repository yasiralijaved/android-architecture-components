package com.yasiralijaved.android.arc.feature.users.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.yasiralijaved.android.arc.component.db.entity.UserEntity;
import com.yasiralijaved.android.arc.core.di.ViewModelFactory;
import com.yasiralijaved.android.arc.core.utils.Status;
import com.yasiralijaved.android.arc.feature.users.R;
import com.yasiralijaved.android.arc.feature.users.adapter.UsersAdapter;
import com.yasiralijaved.android.arc.feature.users.databinding.UsersListFragmentBinding;
import com.yasiralijaved.android.arc.feature.users.viewmodel.UsersListViewModel;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class UsersListFragment extends Fragment implements UsersAdapter.UsersAdapterListener {

    @Inject
    ViewModelFactory viewModelFactory;

    private UsersListViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(UsersListViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        UsersListFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.users_list_fragment,
                container,
                false);

        binding.setLifecycleOwner(this);
        binding.setViewmodel(mViewModel);

        View rootView = binding.getRoot();
        // Set RecyclerView Adapter
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_users);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create and set User Adapter
        UsersAdapter adapter = new UsersAdapter(this);
        recyclerView.setAdapter(adapter);

        // Initialize Swipe Refresh Functionality
        SwipeRefreshLayout swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_users);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            // Load fresh data because user forcefully want fresh data
            mViewModel.loadUsersList(true);
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Initially, load the cached data only
        mViewModel.loadUsersList(false);

        /* Handle API Error Messages */
        mViewModel.getUsersListLiveData().observe(getViewLifecycleOwner(), resource -> {
            if(resource.status == Status.ERROR) {
                if(getView() != null) {
                    Snackbar.make(getView(), resource.message != null ? resource.message : "Something went wrong!", Snackbar.LENGTH_LONG)
                            .setAction("OK", v -> {

                            }).show();
                }
            }
        });
    }

    @Override
    public void onUserClicked(View view, int position, UserEntity user) {
        if(view != null) {
            Navigation.findNavController(view).navigate(R.id.action_usersListFragment_to_userDetailFragment);
        }
    }
}
