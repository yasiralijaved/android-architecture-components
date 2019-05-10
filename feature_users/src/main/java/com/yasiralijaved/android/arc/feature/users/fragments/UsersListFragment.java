package com.yasiralijaved.android.arc.feature.users.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasiralijaved.android.arc.component.db.entities.UserEntity;
import com.yasiralijaved.android.arc.core.utils.Resource;
import com.yasiralijaved.android.arc.feature.users.R;
import com.yasiralijaved.android.arc.feature.users.adapters.UsersAdapter;
import com.yasiralijaved.android.arc.feature.users.databinding.UsersListFragmentBinding;
import com.yasiralijaved.android.arc.feature.users.viewmodels.UsersListViewModel;

import java.util.List;

public class UsersListFragment extends Fragment {

    private UsersListViewModel mViewModel;
    private UsersAdapter mUserAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        UsersListFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.users_list_fragment,
                container,
                false);

        mViewModel = ViewModelProviders.of(this).get(UsersListViewModel.class);
        binding.setViewmodel(mViewModel);

        View rootView = binding.getRoot();
        // Set RecyclerView Adapter
        RecyclerView recyclerView = rootView.findViewById(R.id.rv_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mUserAdapter = new UsersAdapter(null);
        recyclerView.setAdapter(mUserAdapter);

        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_users);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.loadUsersList(true);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel.getUserDetailCommand().observe(getViewLifecycleOwner(), new Observer<Void>() {
            @Override
            public void onChanged(Void ignore) {
                if (getView() != null)
                    Navigation.findNavController(getView()).navigate(R.id.action_usersListFragment_to_userDetailFragment);
            }
        });

        mViewModel.getUsersListLiveData().observe(this, new Observer<Resource<List<UserEntity>>>() {
            @Override
            public void onChanged(Resource<List<UserEntity>> resource) {
                switch (resource.status) {
                    case LOADING:
                        mSwipeRefreshLayout.setRefreshing(true);
                        mUserAdapter.setData(resource.data);
                        break;
                    case SUCCESS:
                        mSwipeRefreshLayout.setRefreshing(false);
                        mUserAdapter.setData(resource.data);
                        break;
                    case ERROR:
                        mSwipeRefreshLayout.setRefreshing(false);
                        break;
                    default:
                        break;
                }

            }
        });

        mViewModel.loadUsersList(true);


    }
}
