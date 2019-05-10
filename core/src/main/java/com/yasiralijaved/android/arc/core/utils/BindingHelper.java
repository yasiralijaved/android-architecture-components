package com.yasiralijaved.android.arc.core.utils;

import androidx.recyclerview.widget.RecyclerView;

public class BindingHelper {

    public static <T> void setAdapterItems(RecyclerView recyclerView, DataAdapter<T>  adapter, T items) {

        if(items == null) return;

        if(recyclerView.getAdapter() == null) {
            recyclerView.setAdapter((RecyclerView.Adapter)adapter);

        }
        adapter.setData(items);
    }
}
