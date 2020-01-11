package com.example.han2.Main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Adapter.CommunityAdapter;
import com.example.han2.Atask.Comment_list;
import com.example.han2.Dto.CommunityDTO;
import com.example.han2.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Fragment_community extends Fragment {

    Fragment fragment_community;
    ArrayList<CommunityDTO> CommunityArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Button co_BtnWrite;
    CommunityAdapter adapter;
    SubActivity activity;
    Bundle bundle;
    com.example.han2.Atask.Comment_list Comment_list;
    ImageButton cMore;
    Context context;

    CommunityDTO selItem;


    public static Fragment getInstance() {
        return null;
    }

    @Nullable
    @Override
    public  View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container,false);

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
        fragment_community = new Fragment_community();

        //리사이클러 뷰 시작
        adapter = new CommunityAdapter(getActivity().getApplicationContext(), activity , CommunityArrayList);
        recyclerView = view.findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);






        co_BtnWrite = view.findViewById(R.id.co_BtnWrite);
        //글쓰기
        co_BtnWrite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                //추가
                activity = (SubActivity) getActivity();
                activity.fragment_btnClick(8, bundle);

            }
        });

        Comment_list = new Comment_list(CommunityArrayList, adapter);
        try {
            Comment_list.execute().get();

        } catch (ExecutionException e) {e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        return view;

    }


}


















































