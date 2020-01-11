package com.example.han2.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.han2.Atask.InquiryUpdate;
import com.example.han2.Dto.FragDTO;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import java.util.concurrent.ExecutionException;

import static com.example.han2.MainActivity.loginDTO;

public class Fragment4_update extends Fragment {

    SubActivity activity;
    Bundle bundle;
    Button inUpdateSub, inUpdateCanc;
    String no;
    FragDTO item;

    TextView in_detail_writer, in_detail_writedate;
    EditText in_update_title, in_update_content;
    String tvTitle, tvContent;
    String nickname="";
    //저장버튼 눌러 데이터 전송하기 위한 변수
    String title = "", content = "", id="";
    EditText etTitle, etContent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4_update, container,false);

        activity = (SubActivity) getActivity();
        id = loginDTO.getId();


        //데이터 값을 받아오는 처리
        if(activity.mBundle!= null){
            bundle = activity.mBundle;
            item = (FragDTO) bundle.getSerializable("item");
            no = item.getNo();

            tvTitle = item.getTitle();
            nickname = item.getNickname();
            tvContent = item.getContent();
        }


        //데이터를 받아서 뿌려야돼
        in_update_title = view.findViewById(R.id.in_update_title);
        in_detail_writer = view.findViewById(R.id.in_detail_writer);
        in_detail_writedate = view.findViewById(R.id.in_detail_writedate);
        in_update_content = view.findViewById(R.id.in_update_content);

        in_update_title.setText(tvTitle);
        in_detail_writer.setText(nickname);
        in_update_content.setText(tvContent);

        //저장버튼 눌렀을때
        inUpdateSub = view.findViewById(R.id.inUpdateSub);
        inUpdateSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = in_update_title.getText().toString();
                content = in_update_content.getText().toString();
                in_update_title.setText("");
                in_update_content.setText("");

                InquiryUpdate inquiryUpdate = new InquiryUpdate(title, nickname, content, id, no);
                Log.d("seeeUpdate", "title : " + title + "nickname : " + nickname +" content : " + content + " 아이디: " + id + ", no : " + no);
                try {
                    inquiryUpdate.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment4 = new Fragment4();
                fragmentTransaction.replace(R.id.fragment_container, fragment4).commit();
            }
        });

        inUpdateCanc = view.findViewById(R.id.inUpdateCanc);
        inUpdateCanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment4_detail = new Fragment4_detail();
                fragmentTransaction.remove(Fragment4_update.this);
                fragmentTransaction.replace(R.id.fragment_container, fragment4_detail).commit();
            }
        });

        return view;

    }

    /*public void setItem(FragDTO item){
        in_update_title.setText(item.getTvTitle());

        in_detail_writedate.setText(item.getWritedate());
        in_update_content.setText(item.getTvContent());
    }*/

}

