package com.example.han2.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Adapter.ClickListener;
import com.example.han2.Adapter.MainRecipe2list1Adapter;
import com.example.han2.Adapter.MainRecipe2list2Adapter;
import com.example.han2.Adapter.MainRecipe2list3Adapter;
import com.example.han2.Adapter.MainRecipe2list4Adapter;
import com.example.han2.Adapter.MainRecipe2list5Adapter;
import com.example.han2.Adapter.MainRecipe2list6Adapter;
import com.example.han2.Adapter.MainRecipe3list1Adapter;
import com.example.han2.Adapter.MainRecipe3list2Adapter;
import com.example.han2.Adapter.MainRecipe3list3Adapter;
import com.example.han2.Adapter.MainRecipe4list1Adapter;
import com.example.han2.Adapter.MainRecipe4list2Adapter;
import com.example.han2.Adapter.MainRecipe4list3Adapter;
import com.example.han2.Adapter.MainRecipe4list4Adapter;
import com.example.han2.Adapter.TodayRecipeAdapter;
import com.example.han2.Atask.MainRecipe4list1;
import com.example.han2.Dto.WeekRecipeDTO;
import com.example.han2.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class MainRecipe4_list1 extends Fragment {
    MainRecipe4list1 mainRecipe4List1;
    MainRecipe4list1Adapter adapter;
    RecyclerView recyclerView;

    ImageLoader imageLoader;
    WeekRecipeDTO weekRecipeDTO = null;
    TextView category_tv1, category_tv2, category_tv3, category_tv4;
    ImageView category_img1, category_img2, category_img3, category_img4;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe4_list1, container, false);

        ImageView img = view.findViewById(R.id.back_go);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main4();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        category_tv1 = view.findViewById(R.id.category_tv1);
        category_tv2 = view.findViewById(R.id.category_tv2);
        category_tv3 = view.findViewById(R.id.category_tv3);
        category_tv4 = view.findViewById(R.id.category_tv4);
        category_img1 = view.findViewById(R.id.category_img1);
        category_img2 = view.findViewById(R.id.category_img2);
        category_img3 = view.findViewById(R.id.category_img3);
        category_img4 = view.findViewById(R.id.category_img4);

        //종류별 클릭시
        category_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main1();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        category_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main1();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        //상황별 클릭시
        category_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main2();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        category_tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main2();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //재료별 클릭 시
        category_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main3();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        category_tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main3();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        //제철요리 클릭 시
        category_img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main4();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        category_tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main4();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ArrayList<WeekRecipeDTO> arrayList = new ArrayList<>();
        adapter = new MainRecipe4list1Adapter(arrayList);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        mainRecipe4List1 = new MainRecipe4list1(arrayList, adapter);
        mainRecipe4List1.execute();

        adapter.setOnClickListener(new ClickListener() {
            @Override
            public void onItemClick(TodayRecipeAdapter.ViewHolder holder, View view, int position) {

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
                weekRecipeDTO = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), BottomRecipeWeekRank.class);
                intent.putExtra("weekRecipeDTO", weekRecipeDTO);
                startActivity(intent);
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
        });

        return  view;
    }
}
