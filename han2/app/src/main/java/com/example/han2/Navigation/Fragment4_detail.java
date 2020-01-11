package com.example.han2.Navigation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.han2.Atask.InquiryDelete;
import com.example.han2.Dto.FragDTO;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import static com.example.han2.MainActivity.loginDTO;

public class Fragment4_detail extends Fragment {

    Button detailUpdate, detailDelete, detailCancel;
    TextView in_detail_title, in_detail_writer, in_detail_writedate, in_detail_content;
    Bundle bundle = new Bundle();
    FragDTO item;
    SubActivity activity;

    String title, nickname, content, writedate;
    String grade_id="";

    public static Fragment getInstance() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4_detail, container,false);

        activity = (SubActivity) getActivity();

        //데이터 값을 받아오는 처리
        if(activity.mBundle!= null){
            bundle = activity.mBundle;
            item = (FragDTO) bundle.getSerializable("item");
            Log.d("bundle", "datavalue: " + item.getNickname());
            Log.d("bundle", "datavalue: " + item.getTitle());
            title = item.getTitle();
            nickname = item.getNickname();
            writedate = item.getWritedate();
            content = item.getContent();
        }

        //nickname = loginDTO.getId();

        //데이터를 받아서 뿌려야돼
        in_detail_title = view.findViewById(R.id.in_detail_title);
        in_detail_writer = view.findViewById(R.id.in_detail_writer);
        in_detail_writedate = view.findViewById(R.id.in_detail_writedate);
        in_detail_content = view.findViewById(R.id.in_detail_content);

        in_detail_title.setText(title);
        in_detail_writer.setText(nickname);
        in_detail_writedate.setText(writedate);
        in_detail_content.setText(content);

        Log.d("detail", "onCreateView: " +in_detail_writer.getText() );

        //관리자일 경우에만 수정, 삭제 버튼 보이게
        grade_id = loginDTO.getGrade_id();
        if( grade_id.equals("9") || grade_id.equals("10")){
            detailUpdate = view.findViewById(R.id.detailUpdate);
            detailDelete= view.findViewById(R.id.detailDelete);

            detailUpdate.setVisibility(view.VISIBLE);
            detailDelete.setVisibility(view.VISIBLE);

            detailUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*activity = (SubActivity) getActivity();
                    activity.fragment_btnClick(6, bundle);*/

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment4_update = new Fragment4_update();
                    fragmentTransaction.remove(Fragment4_detail.this);
                    fragmentTransaction.replace(R.id.fragment_container, fragment4_update).commit();
                }
            });

            detailDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("정말 삭제하시겠습니까?");
                    builder.setPositiveButton("아니오",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    builder.setNegativeButton("예",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    InquiryDelete inquiryDelete = new InquiryDelete(item.getNo());
                                    inquiryDelete.execute();

                                    FragmentManager fragmentManager = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    Fragment fragment4 = new Fragment4();
                                    fragmentTransaction.remove(Fragment4_detail.this);
                                    fragmentTransaction.replace(R.id.fragment_container, fragment4).commit();
                                }
                            });
                    builder.show();


                }
            });

        }


        detailCancel = view.findViewById(R.id.detailCancel);
        detailCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment4 = new Fragment4();
                fragmentTransaction.remove(Fragment4_detail.this);
                fragmentTransaction.replace(R.id.fragment_container, fragment4).commit();
            }
        });



    /*    inquiryDetail = new InquiryDetail(InquiryArrayList, adapter);
        inquiryDetail.execute();
*/
        return view;

    }

    public void setItem(FragDTO item){
        in_detail_title.setText(item.getTitle());
        in_detail_writer.setText(item.getNickname());
        in_detail_writedate.setText(item.getWritedate());
        in_detail_content.setText(item.getContent());
    }

}

