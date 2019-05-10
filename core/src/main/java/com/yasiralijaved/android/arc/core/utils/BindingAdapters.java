package com.yasiralijaved.android.arc.core.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class BindingAdapters {

    @BindingAdapter("data_adapter")
    public static <T> void setRecyclerViewAdapter(RecyclerView recyclerView, T items) {
        if(recyclerView.getAdapter() != null && recyclerView.getAdapter() instanceof DataAdapter) {
            DataAdapter<T> adapter = (DataAdapter<T>) recyclerView.getAdapter();
            adapter.setData(items);
        }
    }

    @BindingAdapter("refreshing")
    public static void setSwipeRefreshState(SwipeRefreshLayout swipeRefreshLayout, Status status) {
        swipeRefreshLayout.setRefreshing(status == Status.LOADING);
    }
}
