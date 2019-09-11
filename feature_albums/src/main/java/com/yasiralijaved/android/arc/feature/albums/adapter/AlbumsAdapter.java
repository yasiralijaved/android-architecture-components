package com.yasiralijaved.android.arc.feature.albums.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasiralijaved.android.arc.component.db.entity.AlbumEntity;
import com.yasiralijaved.android.arc.core.utils.DataAdapter;
import com.yasiralijaved.android.arc.feature.albums.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DataAdapter<List<AlbumEntity>> {

    private List<AlbumEntity> albumsList;
    private AlbumViewHolder listener;


    @Override
    public void setData(List<AlbumEntity> items) {
        if (items != null) {
            Collections.sort(items, (album1, album2) -> album1.getTitle().compareTo(album2.getTitle()));
            albumsList = new ArrayList<>(items);
            notifyDataSetChanged();
        }
    }

    public AlbumsAdapter(AlbumViewHolder listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_row_item, parent, false);
        return new AlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        AlbumViewHolder userViewHolder = (AlbumViewHolder) holder;

        userViewHolder.tvAlbumTitle.setText(albumsList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return albumsList == null ? 0 : albumsList.size();
    }

    public interface AlbumsAdapterListener {
        void onAlbumClicked(AlbumEntity albumEntity);
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvAlbumTitle;

        AlbumViewHolder(final View rootView) {
            super(rootView);
            this.tvAlbumTitle = rootView.findViewById(R.id.tv_album_title);
        }
    }
}