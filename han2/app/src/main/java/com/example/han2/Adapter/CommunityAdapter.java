package com.example.han2.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Atask.CommunityDelete;
import com.example.han2.Dto.CommunityDTO;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import java.util.ArrayList;

import static com.example.han2.Main.SubActivity.selItem;
import static com.example.han2.MainActivity.loginDTO;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    private static final String TAG = "CommunityAdapter";
    
    Context context;
    ArrayList<CommunityDTO> items;
    SubActivity activity;

    public CommunityAdapter(Context context, SubActivity activity, ArrayList<CommunityDTO> items) {
        this.context = context;
        this.activity = activity;
        this.items = items;
    }



    @NonNull
    @Override
    public CommunityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.community_item, parent, false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "size : " + items.size());
        Log.d(TAG, "" + position);    //몇번째 포지션인지 확인


        CommunityDTO item = items.get(position);   //index에 있는 position을 가져와 item 에 담음
        holder.setItem(item);   //index에 있는 data가 set 돼서 보여짐

        holder.cMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selItem = getItem(position);
                Log.d(TAG, "onClick: getitem" + selItem.getContent());
                //PopupMenu 객체 생성
                PopupMenu popup = new PopupMenu(context, view);

                //설정한 popup XML을 inflate
                popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

                //팝업메뉴 클릭 시 이벤트
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){

                            case R.id.mUpdate:
                                //수정 선택했을 때 이벤트 실행 코드 작성
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("selItem", selItem);
                                Log.d(TAG, "보내는 넘버 : " + selItem.getNo());
                                Log.d(TAG, "아이템 닉네임 : " + selItem.getNickname() + "   " + "로그인 닉네임 : " + loginDTO.getNickname());
                                if(selItem.getUserid().equals(loginDTO.getId()) || loginDTO.getId().equals("admin")  ){
                                    //추가
                                    activity.fragment_btnClick(10, bundle);

                                    break;
                                }else {
                                    Toast.makeText(context, "본인만 수정할 수 있습니다.", Toast.LENGTH_SHORT).show();
                               }


                            case R.id.mDelete:
                                //삭제 선택했을 때 이벤트 실행 코드 작성
                                if( selItem.getUserid().equals(loginDTO.getId()) ){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                    builder.setMessage("정말 삭제하시겠습니까?");
                                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //삭제 확인 버튼 눌렀을 때
                                            items.remove(position);
                                            notifyItemRemoved(position);
                                            notifyItemRangeChanged(position, items.size());
                                            CommunityDelete delete = new CommunityDelete(selItem.getNo());
                                            delete.execute();


                                            Toast.makeText(context, "글을 삭제하였습니다.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //삭제 취소 버튼 눌렀을 때

                                        }
                                    });
                                    builder.show();
                                }else{
                                    Toast.makeText(context, "본인만 삭제할 수 있습니다.", Toast.LENGTH_SHORT).show();
                                }


                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(CommunityDTO item){
        items.add(item);
    }

    // 리사이클러뷰 내용 모두 지우기
    public void allRemoveItem(){
        items.clear();
    }

    // 특정 인덱스 항목 가져오기
    public CommunityDTO getItem(int position){return items.get(position);}

    // 특정 인덱스 항목 셋팅하기
    public void setItem(int position, CommunityDTO item) {items.set(position, item);}

    // arrayList 통째로 셋팅하기
    public void setItems(ArrayList<CommunityDTO> items) {
        this.items = items;
    }









    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView cComment, cNickname, cWritedate;
        String no = "", userid = "";
        ImageButton cMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            no = "";
            cComment = itemView.findViewById(R.id.cComment);
            cNickname = itemView.findViewById(R.id.cNickname);
            cWritedate = itemView.findViewById(R.id.cWritedate);
            cMore = itemView.findViewById(R.id.cMore);
            userid = "";
        }

        public void setItem(CommunityDTO item){

            no = item.getNo();
            cComment.setText(item.getContent());
            cNickname.setText(item.getNickname());
            cWritedate.setText(item.getWritedate());
            userid = item.getUserid();

            Log.d("selectAAAdapter", "닉네임 : " + item.getNickname() + "아이디 : " + item.getUserid());

        }
    }




}

