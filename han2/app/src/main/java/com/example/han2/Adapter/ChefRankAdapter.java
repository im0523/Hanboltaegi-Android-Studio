package com.example.han2.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Dto.ChefRankDTO;
import com.example.han2.R;

import java.util.ArrayList;

public class ChefRankAdapter extends RecyclerView.Adapter<ChefRankAdapter.ViewHolder> {
    ArrayList<ChefRankDTO> items;

    public ChefRankAdapter(ArrayList<ChefRankDTO> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ChefRankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.chefrank_item, parent, false);

        return new ChefRankAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChefRankAdapter.ViewHolder holder, int position) {
        Log.d("main : adapter ", "" + position);    //몇번째 포지션인지 확인

        ChefRankDTO item = items.get(position);   //index에 있는 position을 가져와 item 에 담음
        holder.setItem(item);   //index에 있는 data가 set 돼서 보여짐
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(ChefRankDTO item){
        items.add(item);
    }

    public void allRemoveItem(){
        items.clear();
    }

    public void removeAllItem() {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView1, textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.chef_item_1);
            textView2 = itemView.findViewById(R.id.chef_item_2);

        }

        public void setItem(ChefRankDTO item){

            textView1.setText(item.getRownum());
            textView2.setText(item.getNickname());


        }
    }
}

