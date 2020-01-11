package com.example.han2.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
import com.example.han2.Atask.ScrapRecipe;
import com.example.han2.Dto.WeekRecipeDTO;
import com.example.han2.Main.BottomRecipeWeekRank;
import com.example.han2.Main.Fragment_main;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import java.util.ArrayList;

import static com.example.han2.MainActivity.loginDTO;

public class Fragment3 extends Fragment {

    ScrapRecipe scrapRecipe;
    ScrapRecipeAdapter adapter;
    RecyclerView recyclerView;
    String scr_userid = "";
    SubActivity activity;
    WeekRecipeDTO weekRecipeDTO = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

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

        scr_userid = loginDTO.getId();
        activity = (SubActivity) getActivity();


        //레시피 데이터받기
        ArrayList<WeekRecipeDTO> ScrapArrayList = new ArrayList<>();
        adapter = new ScrapRecipeAdapter(ScrapArrayList);
        recyclerView = view.findViewById(R.id.recyclerView_scrap_recipe);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        scrapRecipe = new ScrapRecipe(ScrapArrayList, adapter, scr_userid);
        scrapRecipe.execute();


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

            }

            @Override
            public void onItemClick(ScrapRecipeAdapter.ViewHolder holder, View view, int position) {
                weekRecipeDTO = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), BottomRecipeWeekRank.class);
                intent.putExtra("weekRecipeDTO", weekRecipeDTO);
                startActivity(intent);
            }
        });




        return view;
    }





}
