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

public class MainRecipe3list3Adapter extends RecyclerView.Adapter<MainRecipe3list3Adapter.ViewHolder> implements ClickListener {
    ArrayList<WeekRecipeDTO> arrayList;
    ClickListener listener;

    public MainRecipe3list3Adapter(ArrayList<WeekRecipeDTO> arrayList) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        WeekRecipeDTO item = arrayList.get(position);   //index에 있는 position을 가져와 item 에 담음
        holder.setItem(item);   //index에 있는 data가 set 돼서 보여짐

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
    public void onItemClick(TodayRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe2list1Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    @Override
    public void onItemClick(MainRecipe4list1Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe4list2Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe4list3Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe4list4Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe2list2Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe2list3Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe2list4Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe2list5Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe2list6Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe3list1Adapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MainRecipe3list2Adapter.ViewHolder holder, View view, int position) {

    }


    // 외부에서 클릭리스너를 부를 수 있도록 매소드를 만들어 놓는다
    public void setOnClickListener(ClickListener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1, textView2,textView3;
        public ImageView imageView1;


        public ViewHolder(@NonNull View itemView, final ClickListener listener) {
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
                        listener.onItemClick(ViewHolder.this, view, position);

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