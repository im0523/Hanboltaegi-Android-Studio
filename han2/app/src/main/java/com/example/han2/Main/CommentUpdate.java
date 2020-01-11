package com.example.han2.Main;

import android.content.Context;
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

import com.example.han2.Adapter.CommunityAdapter;
import com.example.han2.Atask.CommunityUpdate;
import com.example.han2.Dto.CommunityDTO;
import com.example.han2.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.han2.MainActivity.loginDTO;


public class CommentUpdate extends Fragment {
    private static final String TAG = "CommentUpdate";

    TextView up_Nickname;
    EditText up_content;
    Button up_btnSubmit, up_btnCancle;
    String no;
    Context context;
    SubActivity activity;
    Bundle bundle;
    String nickname = "", content = "", userid = "";
    CommunityDTO selItem;
    ArrayList<CommunityDTO> CommunityArrayList;
    CommunityAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_comment_update, container, false);



        context = container.getContext();
        activity = (SubActivity) getActivity();
        userid = loginDTO.getId();

        //보내온 값 파싱
        if(activity.mBundle != null) {
            Bundle bundle = activity.mBundle;
            selItem = (CommunityDTO) bundle.getSerializable("selItem");

            no = selItem.getNo();
            nickname = selItem.getNickname();
            content = selItem.getContent();


        }

        //가져온 값 써 넣기
        up_Nickname = view.findViewById(R.id.up_Nickname);
        up_content = view.findViewById(R.id.up_content);
        up_btnSubmit = view.findViewById(R.id.up_btnSubmit);
        up_btnCancle = view.findViewById(R.id.up_btnCancle);

        up_Nickname.setText(nickname);
        up_content.setText(content);


        //수정 버튼 클릭 시
        up_btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    nickname = up_Nickname.getText().toString();
                    content = up_content.getText().toString();
                    /*up_Nickname.setText("");*/

                    CommunityUpdate update = new CommunityUpdate(no, content, nickname, userid);
                Log.d("seeeBegin", "번호 : " + no + "이름 : " + nickname + ", 제목 :" + content + ", 아이디: " + userid);

                try {
                    update.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                activity = (SubActivity) getActivity();
                //커뮤니티 페이지로 돌아가기
                activity.fragment_btnClick(9, bundle);


            }


        });

        //취소 버튼 누를 시
        up_btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (SubActivity) getActivity();
                //커뮤니티 페이지로 돌아가기
                activity.fragment_btnClick(9, null);

                /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(CommentUpdate.this).commit();
                fragmentManager.popBackStack();*/


            }
        });







        return view;

    }







}
