package com.example.han2.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.han2.Atask.InquiryInsert;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import java.util.concurrent.ExecutionException;

import static com.example.han2.MainActivity.loginDTO;

public class Fragment4_1 extends Fragment {
    SubActivity activity;
    Bundle bundle = null;
    Button btnSubmit, btnCancel;

    String title = "", nickname = "", content = "", id="", grade_id="";
    EditText etTitle, etContent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4_1, container, false);

        etTitle = view.findViewById(R.id.etTitle);
        nickname = loginDTO.getNickname();
        etContent = view.findViewById(R.id.etContent);
        id = loginDTO.getId();
        grade_id = loginDTO.getGrade_id();

/*
        //관리자가 답글작성 버튼 클릭시 원문 내용을 가져오는 처리
        if( grade_id.equals("9") || grade_id.equals("10")){
            //데이터를 가져와야돼

        }
*/
        activity = (SubActivity) getActivity();
        if (activity.mBundle != null) {
            bundle = activity.mBundle;

            /*String name = bundle.getString("name");
            Toast.makeText(activity, " Name : " + name, Toast.LENGTH_SHORT).show();*/

            activity.mBundle = null;
        }

        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = etTitle.getText().toString();
                content = etContent.getText().toString();
                etTitle.setText("");
                etContent.setText("");

                InquiryInsert inquiryInsert = new InquiryInsert(title, nickname, content, id);
                Log.d("seeeInsert", "title : " + title + "nickname : " + nickname +" content : " + content + " 아이디: " + id);
                try {
                    inquiryInsert.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment4 = new Fragment4();
                fragmentTransaction.replace(R.id.fragment_container, fragment4).commit();

            }/*else {
                    Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                            Toast.LENGTH_SHORT).show();
                }*/


            /*private boolean isNetworkConnected() {
                return false;
            }*/

        });

        btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(Fragment6_1.this).commit();
                fragmentManager.popBackStack();*/

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment4 = new Fragment4();
                fragmentTransaction.remove(Fragment4_1.this);
                fragmentTransaction.replace(R.id.fragment_container, fragment4).commit();

              /* fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

            }


        });
        return view;

    }

}

