package com.yasiralijaved.android.arc.feature.users.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasiralijaved.android.arc.component.db.entities.UserEntity;
import com.yasiralijaved.android.arc.feature.users.R;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<UserEntity> userList;
    private UsersAdapterListener listener;

    public void setData(List<UserEntity> items) {
        if(items != null) {
            userList = new ArrayList<>(items);
            notifyDataSetChanged();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvUserName;

        MyViewHolder(final View rootView) {
            super(rootView);
            this.tvUserName = rootView.findViewById(R.id.tv_user_name);
        }
    }

    public UsersAdapter(UsersAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvUserName.setText(userList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    public interface UsersAdapterListener {
        void onUserClicked(UserEntity user);
    }
}