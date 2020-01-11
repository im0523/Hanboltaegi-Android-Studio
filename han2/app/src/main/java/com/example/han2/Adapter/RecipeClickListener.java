package com.example.han2.Adapter;

import android.view.View;

public interface RecipeClickListener {
     void onItemClick(WeekRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(MonthRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(SideRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(SoupRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(DessertRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(NoodleRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(SourceRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(BobRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(SaladRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(BbangRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(GitaRecipeAdapter.ViewHolder holder, View view, int position);
     void onItemClick(ScrapRecipeAdapter.ViewHolder holder, View view, int position);
}
