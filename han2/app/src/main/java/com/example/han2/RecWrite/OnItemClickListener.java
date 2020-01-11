package com.example.han2.RecWrite;

import android.view.View;

import com.example.han2.Adapter.MyRecyclerviewAdapter;

public interface OnItemClickListener {
    void onItemClick(MyRecyclerviewAdapter.ItemViewHolder holder, View view, int position);
}
