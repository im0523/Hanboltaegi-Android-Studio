package com.example.han2.Adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Dto.RecipeDTO;
import com.example.han2.R;
import com.example.han2.RecWrite.OnItemClickListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class MyRecyclerviewAdapter extends RecyclerView.Adapter<MyRecyclerviewAdapter.ItemViewHolder>
                            implements OnItemClickListener {

    ArrayList<RecipeDTO> arrayList;
    OnItemClickListener listener;

    public MyRecyclerviewAdapter(ArrayList<RecipeDTO> arrayList){
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.rec_list, parent, false);

        return new ItemViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d("main:adapter",""+position);

        RecipeDTO item = arrayList.get(position);
        holder.setitem(item);
    }

    @Override
    public int getItemCount() {   return arrayList.size();   }

    public RecipeDTO getItem(int position){
        return arrayList.get(position);
    }

    public void setItem(int position, RecipeDTO item){
        arrayList.set(position, item);
    }

    // 리사이클러뷰 내용 모두 지우기
    public void removeAllItem(){
        arrayList.clear();
    }

    public void setItems(ArrayList<RecipeDTO> arrayList){
        this.arrayList = arrayList;
    }

    // OnItemClickListener 를 implements 해서 반드시 구현해야 하는 매소드
    @Override
    public void onItemClick(ItemViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    // 외부에서 클릭리스너를 부를 수 있도록 매소드를 만들어 놓는다
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // 어댑터에 매소드 만들기
    // 모든 항목 백그라운드 색 흰색으로 바꿈
    /*public void setBackgroundColor(ItemViewHolder holder){
        for(int i = 0; i < arrayList.size(); i++){
            onBindViewHolder(holder, i);
        }

    }*/

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView recipe_id,title;
        public ProgressBar progressBar;
        public ImageView iv_img;

        public ItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            recipe_id = itemView.findViewById(R.id.recid);
            title = itemView.findViewById(R.id.rectitle);
            iv_img = itemView.findViewById(R.id.iv_img);
            progressBar = itemView.findViewById(R.id.progressBar);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ItemViewHolder.this,view,position);
                    }
                }
            });
        }

        public void setitem(RecipeDTO item) {
            itemView.setBackgroundColor(Color.WHITE);

            recipe_id.setText(item.getRecipe_id());
            title.setText(item.getTitle());

            ImageLoader.getInstance().displayImage(item.getImagepath(), iv_img, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    // Log.d("Sub1 : String s", s);
                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {
                    progressBar.setVisibility(View.GONE);
//                            Log.d("Sub1:ImageFail", failReason.getCause().toString());
                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String s, View view) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

}