package com.example.han2.Adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Dto.TodayRecipeDTO;
import com.example.han2.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class TodayRecipeAdapter extends RecyclerView.Adapter<TodayRecipeAdapter.ViewHolder> implements ClickListener {
    ArrayList<TodayRecipeDTO> arrayList;
    ClickListener listener;

    public TodayRecipeAdapter(ArrayList<TodayRecipeDTO> arrayList) {
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public TodayRecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.today_recipe_item, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TodayRecipeAdapter.ViewHolder holder, final int position) {
        TodayRecipeDTO item = arrayList.get(position);   //index에 있는 position을 가져와 item 에 담음
        holder.setItem(item);   //index에 있는 data가 set 돼서 보여짐

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public void addItem(TodayRecipeDTO item){
        arrayList.add(item);
    }

    public void allRemoveItem(){
        arrayList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public TodayRecipeDTO getItem(int position) {
        return arrayList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, TodayRecipeDTO item){
        arrayList.set(position, item);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<TodayRecipeDTO> arrayList){
        this.arrayList = arrayList;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    @Override
    public void onItemClick(MainRecipe2list1Adapter.ViewHolder holder, View view, int position) {

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

    @Override
    public void onItemClick(MainRecipe3list3Adapter.ViewHolder holder, View view, int position) {

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


    // 외부에서 클릭리스너를 부를 수 있도록 매소드를 만들어 놓는다
    public void setOnClickListener(ClickListener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;


        public ViewHolder(@NonNull View itemView, final ClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.today_re_text);
            imageView = itemView.findViewById(R.id.today_re_img);


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

        public void setItem(TodayRecipeDTO item) {
            String title = "< "+item.getTitle()+" >";
            textView.setText(title);
            ImageLoader.getInstance().displayImage(item.getImagepath(), imageView, new ImageLoadingListener() {
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