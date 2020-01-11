package com.example.han2.Adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Atask.RecipeCommentDelete;
import com.example.han2.Dto.RecipeCommentDTO;
import com.example.han2.R;

import java.util.ArrayList;

import static com.example.han2.MainActivity.loginDTO;

public class RecipeCommentAdapter extends RecyclerView.Adapter<RecipeCommentAdapter.ViewHolder> implements RecipeCommentClickListener {

    ArrayList<RecipeCommentDTO> arrayList;
    RecipeCommentClickListener listener;

    public RecipeCommentAdapter(ArrayList<RecipeCommentDTO> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recipe_comment_item, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        RecipeCommentDTO item = arrayList.get(position);   //index에 있는 position을 가져와 item 에 담음
        holder.setItem(item);   //index에 있는 data가 set 돼서 보여짐

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addItem(RecipeCommentDTO item) {
        arrayList.add(item);
    }

    public void allRemoveItem() {
        arrayList.clear();
    }

    // 특정 인덱스 항목 가져오기
    public RecipeCommentDTO getItem(int position) {
        return arrayList.get(position);
    }

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, RecipeCommentDTO item) {
        arrayList.set(position, item);
    }

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<RecipeCommentDTO> arrayList) {
        this.arrayList = arrayList;
    }


    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }


    // 외부에서 클릭리스너를 부를 수 있도록 매소드를 만들어 놓는다
    public void setOnClickListener(RecipeCommentClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1, textView2, textView3;
        Button button;
        String no, id;
        CardView cardView;

        public ViewHolder(@NonNull View itemView, final RecipeCommentClickListener listener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.recipeComment);
            textView2 = itemView.findViewById(R.id.recipeCommentNickname);
            textView3 = itemView.findViewById(R.id.recipeCommentdate);

            button = itemView.findViewById(R.id.recipeCommentDelete);
            cardView = itemView.findViewById(R.id.cardView);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);

                    }
                }
            });


        }

        public void setItem(RecipeCommentDTO item) {
            textView1.setText(item.getContent());
            textView2.setText(item.getNickname());
            textView3.setText(item.getWritedate());

            id = item.getMember_id();

            no = item.getNo();

            if(loginDTO.getGrade_id().equals("10") || loginDTO.getId().equals(id)) {
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle("댓글")
                                .setMessage("정말 삭제하겠습니까?")
                                .setCancelable(false)
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        RecipeCommentDelete recipeCommentDelete = new RecipeCommentDelete(no);
                                        recipeCommentDelete.execute();
                                        cardView.setVisibility(View.GONE);
                                    }
                                })
                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            } else {

            }
        }

    }
}