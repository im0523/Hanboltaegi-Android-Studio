package com.example.han2.Adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Dto.FragDTO;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import java.util.ArrayList;

public class InquiryAdapter extends RecyclerView.Adapter<InquiryAdapter.ViewHolder> {
    ArrayList<FragDTO> items;
    SubActivity activity;

    public InquiryAdapter(ArrayList<FragDTO> items, SubActivity activity) {
        this.items = items;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.inquiry_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("main : adapter ", "" + position);    //몇번째 포지션인지 확인

        FragDTO item = items.get(position);   //index에 있는 position을 가져와 item 에 담음
        holder.setItem(item);   //index에 있는 data가 set 돼서 보여짐

        holder.parentLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragDTO item = items.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);

                Log.d("item : ", "onClick: " + item.getNickname());
                Log.d("item : ", "onClick: " + item.getWritedate());
                Log.d("item : ", "onClick: " + item.getContent());
                activity.fragment_btnClick(5, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parentLL;
        TextView tvNo, tvTitle, tvWritedate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLL = itemView.findViewById(R.id.parentLL);
            tvNo = itemView.findViewById(R.id.tvNo);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvWritedate = itemView.findViewById(R.id.tvWritedate);
        }

        public void setItem(FragDTO item){
            tvNo.setText(item.getNum());
            tvTitle.setText(item.getTitle());
            tvWritedate.setText(item.getWritedate());
            Log.d( "settttt: " + tvNo +"번, " + tvTitle, ", " + tvWritedate);
        }

    }
}
