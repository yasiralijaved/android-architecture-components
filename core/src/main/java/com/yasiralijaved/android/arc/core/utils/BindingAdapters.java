package com.yasiralijaved.android.arc.core.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class BindingAdapters {

    @BindingAdapter("data_adapter")
    public static <T> void setRecyclerViewAdapter(RecyclerView recyclerView, T items) {

        if(items == null) return;

        if(recyclerView.getAdapter() == null) {
            ((DataAdapter<T>)recyclerView.getAdapter()).setData(items);
        }
    }

    @BindingAdapter("refreshing")
    public static void setSwipeRefreshState(SwipeRefreshLayout swipeRefreshLayout, Status status) {
        swipeRefreshLayout.setRefreshing(status == Status.LOADING);
    }
}
