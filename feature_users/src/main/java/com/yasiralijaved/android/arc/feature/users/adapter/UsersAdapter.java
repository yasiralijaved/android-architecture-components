package com.yasiralijaved.android.arc.feature.users.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.yasiralijaved.android.arc.component.db.entity.UserEntity;
import com.yasiralijaved.android.arc.core.utils.CircleTransform;
import com.yasiralijaved.android.arc.core.utils.DataAdapter;
import com.yasiralijaved.android.arc.feature.users.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DataAdapter<List<UserEntity>> {

    private List<UserEntity> userList;
    private UsersAdapterListener listener;

    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;

    private final CircleTransform circleTransform = new CircleTransform();

    @Override
    public void setData(List<UserEntity> items) {
        if (items != null) {
            Collections.sort(items, (user1, user2) -> user1.getName().compareTo(user2.getName()));

            List<UserEntity> fullUserList = new ArrayList<>();

            String lastHeader = "";

            int size = items.size();

            for (int i = 0; i < size; i++) {

                UserEntity user = items.get(i);
                String header = String.valueOf(user.getName().charAt(0)).toUpperCase();

                if (!TextUtils.equals(lastHeader, header)) {
                    lastHeader = header;
                    fullUserList.add(new UserEntity("-1", "", "", header, "", "", ""));
                }

                String photoUrl = String.format("https://randomuser.me/api/portraits/%s/%s.jpg", i%2 == 0 ? "men" : "women", i+10);
                user.setPhotoUrl(photoUrl);
                fullUserList.add(user);
            }

            userList = new ArrayList<>(fullUserList);
            notifyDataSetChanged();
        }
    }

    public UsersAdapter(UsersAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case TYPE_HEADER:
                View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_header, parent, false);
                viewHolder = new HeaderViewHolder(headerView);
                break;
            case TYPE_ITEM:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);
                viewHolder = new UserViewHolder(itemView, this.listener);
                break;
            default:
                View defaultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);
                viewHolder = new UserViewHolder(defaultView, this.listener);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.tvHeader.setText(userList.get(position).getName());
        } else {
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            Picasso.get()
                    .load(userList.get(position).getPhotoUrl())
                    .transform(circleTransform)
                    .into(userViewHolder.ivUserPhoto);

            userViewHolder.tvUserName.setText(userList.get(position).getName());
            userViewHolder.tvUserEmail.setText(userList.get(position).getEmail());
            userViewHolder.tvUserPhone.setText(userList.get(position).getPhone());
        }
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (userList.get(position).getId().equalsIgnoreCase("-1")) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public interface UsersAdapterListener {
        void onUserClicked(View view, int position, UserEntity user);
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView ivUserPhoto;
        private final TextView tvUserName;
        private final TextView tvUserEmail;
        private final TextView tvUserPhone;
        private final UsersAdapterListener listener;

        UserViewHolder(final View rootView, UsersAdapterListener listener) {
            super(rootView);
            this.ivUserPhoto = rootView.findViewById(R.id.iv_user_photo);
            this.tvUserName = rootView.findViewById(R.id.tv_user_name);
            this.tvUserEmail = rootView.findViewById(R.id.tv_user_email);
            this.tvUserPhone = rootView.findViewById(R.id.tv_user_phone);
            this.listener = listener;
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(this.listener != null && userList != null && userList.size() > getAdapterPosition()) {
                this.listener.onUserClicked(view, getAdapterPosition(), userList.get(getAdapterPosition()));
            }
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvHeader;

        HeaderViewHolder(final View rootView) {
            super(rootView);
            this.tvHeader = rootView.findViewById(R.id.tv_user_header);
        }
    }
}