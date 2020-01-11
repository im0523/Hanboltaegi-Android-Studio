package com.example.han2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Dto.SearchAllDTO;
import com.example.han2.R;

import java.util.ArrayList;

public class SearchAllListAdapter extends RecyclerView.Adapter<SearchAllListAdapter.ViewHolder>  {
    ArrayList<SearchAllDTO> arrayList;

    public SearchAllListAdapter(ArrayList<SearchAllDTO> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weekchef_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        SearchAllDTO item = arrayList.get(position);   //index에 있는 position을 가져와 item 에 담음
        holder.setItem(item);   //index에 있는 data가 set 돼서 보여짐

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public void addItem(SearchAllDTO item){
        arrayList.add(item);
    }

    public void allRemoveItem(){
        arrayList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public SearchAllDTO getItem(int position) {
        return arrayList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, SearchAllDTO item){
        arrayList.set(position, item);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<SearchAllDTO> arrayList){
        this.arrayList = arrayList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.weekchef_item_text);

        }
        public void setItem(SearchAllDTO item) {
            textView1.setText(item.getContent());

        }

    }
}