package com.example.han2.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Dto.MyRecipeDTO;
import com.example.han2.Main.SubActivity;
import com.example.han2.Navigation.RecipeDetail;
import com.example.han2.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

import static com.example.han2.MainActivity.loginDTO;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> implements OnContentClickListener {

    ArrayList<MyRecipeDTO> items;
    SubActivity activity;

    public RecipeAdapter(ArrayList<MyRecipeDTO> items, SubActivity activity) {
        this.items = items;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recipe_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d("main : adapter ", "" + position);    //몇번째 포지션인지 확인
        //nickname= loginDTO.getNickname();

        MyRecipeDTO item = items.get(position);   //index에 있는 position을 가져와 item 에 담음
        holder.setItem(item);   //index에 있는 data가 set 돼서 보여짐

        holder.parentLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyRecipeDTO item = items.get(position);
                Intent intent = new Intent(view.getContext(), RecipeDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("item", item);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void  clearAllList(){
        items.clear();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(InquiryAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(ScrapRecipeAdapter.ViewHolder holder, View view, int position) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout parentLL;
        TextView tvTitle, tvSubTitle, tvWritedate;
        ImageView imageView;
        String userid="";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLL = itemView.findViewById(R.id.parentLL);
            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubTitle= itemView.findViewById(R.id.tvSubTitle);
            tvWritedate = itemView.findViewById(R.id.tvWritedate);

        }

        public void setItem(MyRecipeDTO item){
            //imageView.setImageResource(Integer.parseInt(item.getImagepath()));
            //imageView.setImageResource(item.getImagepath());
            tvTitle.setText(item.getTitle());
            tvSubTitle.setText(item.getSubtitle());
            tvWritedate.setText(item.getWritedate());
            userid = loginDTO.getId();
            Log.d( "settttt: " + tvTitle, ", " + tvWritedate);

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
