package com.yasiralijaved.android.arc.feature.users.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasiralijaved.android.arc.feature.users.R;
import com.yasiralijaved.android.arc.feature.users.databinding.UsersListFragmentBinding;
import com.yasiralijaved.android.arc.feature.users.viewmodels.UsersListViewModel;

public class UsersListFragment extends Fragment {

    private UsersListViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        UsersListFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.users_list_fragment,
                container,
                false);
        mViewModel = ViewModelProviders.of(this).get(UsersListViewModel.class);
        binding.setViewmodel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel.getUserDetailCommand().observe(getViewLifecycleOwner(), new Observer<Void>() {
            @Override
            public void onChanged(Void ignore) {
                if(getView() != null)
                    Navigation.findNavController(getView()).navigate(R.id.action_usersListFragment_to_userDetailFragment);
            }
        });
    }
}