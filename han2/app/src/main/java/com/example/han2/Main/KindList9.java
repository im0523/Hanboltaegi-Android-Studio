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

import com.example.han2.Adapter.BbangRecipeAdapter;
import com.example.han2.Adapter.BobRecipeAdapter;
import com.example.han2.Adapter.DessertRecipeAdapter;
import com.example.han2.Adapter.GitaRecipeAdapter;
import com.example.han2.Adapter.MonthRecipeAdapter;
import com.example.han2.Adapter.NoodleRecipeAdapter;
import com.example.han2.Adapter.RecipeClickListener;
import com.example.han2.Adapter.SaladRecipeAdapter;
import com.example.han2.Adapter.ScrapRecipeAdapter;
import com.example.han2.Adapter.SideRecipeAdapter;
import com.example.han2.Adapter.SoupRecipeAdapter;
import com.example.han2.Adapter.SourceRecipeAdapter;
import com.example.han2.Adapter.WeekRecipeAdapter;
import com.example.han2.Atask.GitaRecipe;
import com.example.han2.Dto.WeekRecipeDTO;
import com.example.han2.R;

import java.util.ArrayList;

public class KindList9 extends Fragment {


    GitaRecipe gitaRecipe;
    GitaRecipeAdapter adapter;
    RecyclerView recyclerView;

    WeekRecipeDTO weekRecipeDTO = null;
    TextView category_tv1, category_tv2, category_tv3, category_tv4;
    ImageView category_img1, category_img2, category_img3, category_img4;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gita_recipe, container, false);

        ImageView img = view.findViewById(R.id.back_go);
        img.setOnClickListener(new View.OnClickListener() {
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

        //레시피 데이터받기
        ArrayList<WeekRecipeDTO> GitaArrayList = new ArrayList<>();
        adapter = new GitaRecipeAdapter(GitaArrayList);
        recyclerView = view.findViewById(R.id.recyclerView_gita_recipe);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        gitaRecipe = new GitaRecipe(GitaArrayList, adapter);
        gitaRecipe.execute();


        //레시피 클릭 시 디테일로
        adapter.setOnClickListener(new RecipeClickListener() {
            @Override
            public void onItemClick(WeekRecipeAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onItemClick(MonthRecipeAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onItemClick(SideRecipeAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onItemClick(SoupRecipeAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onItemClick(DessertRecipeAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onItemClick(NoodleRecipeAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onItemClick(SourceRecipeAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onItemClick(BobRecipeAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onItemClick(SaladRecipeAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onItemClick(BbangRecipeAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            public void onItemClick(GitaRecipeAdapter.ViewHolder holder, View view, int position) {
                weekRecipeDTO = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), BottomRecipeWeekRank.class);
                intent.putExtra("weekRecipeDTO", weekRecipeDTO);
                startActivity(intent);
            }

            @Override
            public void onItemClick(ScrapRecipeAdapter.ViewHolder holder, View view, int position) {

            }
        });


        return view;
    }

}
