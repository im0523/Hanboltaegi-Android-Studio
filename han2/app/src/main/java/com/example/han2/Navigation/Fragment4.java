package com.example.han2.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Adapter.InquiryAdapter;
import com.example.han2.Atask.Inquiry;
import com.example.han2.Dto.FragDTO;
import com.example.han2.Main.Fragment_main;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import java.util.ArrayList;

import static com.example.han2.MainActivity.loginDTO;

public class Fragment4 extends Fragment {
    private static final String TAG = "Fragment4";

    ArrayList<FragDTO> InquiryArrayList = new ArrayList<>();
    Inquiry inquiry;
    Button inWrite;
    SubActivity activity;
    RecyclerView recyclerView;
    InquiryAdapter adapter;
    Bundle bundle;
    String id = "", grade_id="";

    public static Fragment getInstance() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);

        ImageView img = view.findViewById(R.id.back_go);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Fragment_main();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        activity = (SubActivity) getActivity();
        // 리사이클러 뷰 시작
        adapter = new InquiryAdapter(InquiryArrayList, activity);
        recyclerView = view.findViewById(R.id.recyclerView);
        id = loginDTO.getId();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        //관리자일 경우에만 글쓰기 버튼 보이게
        grade_id = loginDTO.getGrade_id();
        Log.d(TAG, "gradeid: " + grade_id + loginDTO.getId() + loginDTO.getNickname());
        if( grade_id.equals("9") || grade_id.equals("10")){
            inWrite = view.findViewById(R.id.inWrite);
            inWrite.setVisibility(view.VISIBLE);

            inWrite = view.findViewById(R.id.inWrite);
            inWrite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: Button Clicked!!!" );

                    activity = (SubActivity) getActivity();
                    //글쓰는 화면으로 이동
                    activity.fragment_btnClick(4, bundle);
                }

            });
        }



        inquiry = new Inquiry(InquiryArrayList, adapter);
        inquiry.execute();

        return view;
    }
}
