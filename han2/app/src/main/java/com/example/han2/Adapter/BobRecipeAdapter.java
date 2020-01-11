package com.example.han2.Adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Dto.WeekRecipeDTO;
import com.example.han2.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class BobRecipeAdapter extends RecyclerView.Adapter<BobRecipeAdapter.ViewHolder> implements RecipeClickListener {
    ArrayList<WeekRecipeDTO> arrayList;
    RecipeClickListener listener;

    public BobRecipeAdapter(ArrayList<WeekRecipeDTO> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeekRecipeDTO item = arrayList.get(position);   //index에 있는 position을 가져와 item 에 담음
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addItem(WeekRecipeDTO item){
        arrayList.add(item);
    }

    public void allRemoveItem(){
        arrayList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public WeekRecipeDTO getItem(int position) {
        return arrayList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, WeekRecipeDTO item){
        arrayList.set(position, item);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<WeekRecipeDTO> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public void onItemClick(WeekRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MonthRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(SideRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    @Override
    public void onItemClick(SaladRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(BbangRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(GitaRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(ScrapRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(SoupRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(DessertRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(NoodleRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(SourceRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    // 외부에서 클릭리스너를 부를 수 있도록 매소드를 만들어 놓는다
    public void setOnClickListener(RecipeClickListener listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1, textView2,textView3;
        public ImageView imageView1;

        public ViewHolder(@NonNull View itemView, final RecipeClickListener listener) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.tvTitle);
            textView2 = itemView.findViewById(R.id.tvSubTitle);
            textView3 = itemView.findViewById(R.id.tvWritedate);
            imageView1 = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this,view,position);

                    }

                }
            });
        }


        public void setItem(WeekRecipeDTO item) {
            textView1.setText(item.getTitle());
            textView2.setText(item.getSubtitle());
            textView3.setText(item.getWritedate());
            ImageLoader.getInstance().displayImage(item.getImagepath(), imageView1, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String s, View view) {

                }

                @Override
                public void onLoadingFailed(String s, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String s, View view, Bitmap bitmap) {

                }

                @Override
                public void onLoadingCancelled(String s, View view) {

                }
            });
        }
    }
}