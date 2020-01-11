package com.example.han2.Main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import com.example.han2.Adapter.SearchWeekAdapter;
import com.example.han2.Adapter.TodayRecipeAdapter;
import com.example.han2.Adapter.WeekchefAdapter;
import com.example.han2.Atask.SearchWeek;
import com.example.han2.Atask.TodayRecipe;
import com.example.han2.Atask.WeekChefRank;
import com.example.han2.Dto.SearchWeekDTO;
import com.example.han2.Dto.TodayRecipeDTO;
import com.example.han2.Dto.WeekChefDTO;
import com.example.han2.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.Objects;


public class Fragment_main extends Fragment   {
    WeekChefRank weekChefRank;
    WeekchefAdapter adapter;
    RecyclerView recyclerView;

    TodayRecipe todayRecipe;
    TodayRecipeAdapter adapter2;
    RecyclerView recyclerView2;

    Fragment menu_re1, menu_re2, menu_re3, menu_re4;

    SearchWeek searchWeek;
    SearchWeekAdapter adapter3;
    RecyclerView recyclerView3;

    ImageLoader imageLoader;
    TodayRecipeDTO todayRecipeDTO = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        // 이미지 로딩 써드파트
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.blank) // resource or drawable
                .showImageForEmptyUri(R.drawable.blank) // resource or drawable
                .showImageOnFail(R.drawable.blank)// resource or drawable
                .build();

        ImageLoaderConfiguration config = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            config = new ImageLoaderConfiguration.Builder(Objects.requireNonNull(getContext()))
                    //  .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 10 * 1000))
                    .defaultDisplayImageOptions(options)
                    .build();
        }

        imageLoader.getInstance().init(config); // Get singleton instance


        //메인화면 오늘인기 레시피 데이터받기
        ArrayList<TodayRecipeDTO> todayArrayList = new ArrayList<>();
        adapter2 = new TodayRecipeAdapter(todayArrayList);
        recyclerView2 = view.findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager1);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setNestedScrollingEnabled(false);
        todayRecipe = new TodayRecipe(todayArrayList, adapter2);
        todayRecipe.execute();

        //메인화면 쉐프랭킹 데이터받기
        ArrayList<WeekChefDTO> weekArrayList = new ArrayList<>();
        adapter = new WeekchefAdapter(weekArrayList);
        recyclerView = view.findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        weekChefRank = new WeekChefRank(weekArrayList, adapter);
        weekChefRank.execute();

        //메인화면 인기검색어 데이터 받기
        ArrayList<SearchWeekDTO> searchweek = new ArrayList<>();
        adapter3 = new SearchWeekAdapter(searchweek);
        recyclerView3 = view.findViewById(R.id.recyclerView3);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setAdapter(adapter3);
        recyclerView3.setNestedScrollingEnabled(false);
        searchWeek = new SearchWeek(searchweek, adapter3);
        searchWeek.execute();


        //메인화면 오늘인기 레시피 버튼클릭시
        adapter2.setOnClickListener(new ClickListener() {
            @Override
            public void onItemClick(TodayRecipeAdapter.ViewHolder holder, View view, int position) {
                todayRecipeDTO = adapter2.getItem(position);
                Log.d("TAG:today", "onItemClick: "+todayRecipeDTO);
                Intent intent = new Intent(getActivity(),MainTodayRecipeDetail.class);
                intent.putExtra("todayRecipeDTO", todayRecipeDTO);
                startActivity(intent);
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



        //메인화면 메뉴선택시 화면이동
        menu_re1 = new Fragment();
        Button btn1 = view.findViewById(R.id.main_recipe1);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main1();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        menu_re2 = new Fragment();
        Button btn2 = view.findViewById(R.id.main_recipe2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main2();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        menu_re3 = new Fragment();
        Button btn3 = view.findViewById(R.id.main_recipe3);
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main3();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        menu_re4 = new Fragment();
        Button btn4 = view.findViewById(R.id.main_recipe4);
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = new Main4();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }


}
