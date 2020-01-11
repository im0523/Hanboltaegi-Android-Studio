package com.example.han2.Adapter;

import android.view.View;

public interface OnContentClickListener {

    public void onItemClick(RecipeAdapter.ViewHolder holder, View view, int position);
    public void onItemClick(InquiryAdapter.ViewHolder holder, View view, int position);
    public void onItemClick(ScrapRecipeAdapter.ViewHolder holder, View view, int position);
}
