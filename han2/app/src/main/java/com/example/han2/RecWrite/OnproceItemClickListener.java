package com.example.han2.RecWrite;

import android.view.View;

import com.example.han2.Adapter.ProcedureAdapter;

public interface OnproceItemClickListener {
    void onItemClick(ProcedureAdapter.ViewHolder holder, View view, int position);
}
