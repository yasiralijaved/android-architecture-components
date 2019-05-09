package com.yasiralijaved.android.arc.feature.users.fragments;

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
import com.yasiralijaved.android.arc.feature.users.viewmodels.UsersListViewModel;

public class UsersListFragment extends Fragment {

    private UsersListViewModel mViewModel;

    public static UsersListFragment newInstance() {
        return new UsersListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.users_list_fragment, container, false);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_usersListFragment_to_userDetailFragment);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UsersListViewModel.class);
        // TODO: Use the ViewModel
    }

}
