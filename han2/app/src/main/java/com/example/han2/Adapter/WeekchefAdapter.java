package com.example.han2.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.han2.Dto.WeekChefDTO;
import com.example.han2.R;

import java.util.ArrayList;

public class WeekchefAdapter extends RecyclerView.Adapter<WeekchefAdapter.ViewHolder> {
    ArrayList<WeekChefDTO> items;

    public WeekchefAdapter(ArrayList<WeekChefDTO> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weekchef_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("main : adapter ", "" + position);    //몇번째 포지션인지 확인

        WeekChefDTO item = items.get(position);   //index에 있는 position을 가져와 item 에 담음
        holder.setItem(item);   //index에 있는 data가 set 돼서 보여짐
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(WeekChefDTO item){
        items.add(item);
    }

    public void allRemoveItem(){
        items.clear();
    }

    public void removeAllItem() {
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.weekchef_item_text);

        }

        public void setItem(WeekChefDTO item){
            tvTitle.setText(item.getNickname());
            Log.d("TAG", "setItem: "+item.getNickname()+"<>"+ tvTitle);


        }
    }
}

