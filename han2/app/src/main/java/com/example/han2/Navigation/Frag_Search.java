package com.example.han2.Navigation;

import android.os.Bundle;
import android.util.Log;
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

import com.example.han2.Adapter.RecipeAdapter;
import com.example.han2.Atask.SearchRecipe;
import com.example.han2.Dto.MyRecipeDTO;
import com.example.han2.Main.Fragment_main;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import java.util.ArrayList;

import static com.example.han2.Main.SubActivity.text;

public class Frag_Search extends Fragment {
    ArrayList<MyRecipeDTO> MyRecipeArrayList = new ArrayList<>();
    SearchRecipe searchRecipe;
    SubActivity activity;
    RecyclerView search_recyclerView;
    RecipeAdapter adapter;
    ArrayList<MyRecipeDTO> myItemList;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_searchrecipe, container, false);

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
        myItemList = new ArrayList<>();
        // 리사이클러 뷰 시작
        adapter = new RecipeAdapter(MyRecipeArrayList, activity);

        search_recyclerView = view.findViewById(R.id.search_recyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        searchRecipe = new SearchRecipe(MyRecipeArrayList, adapter);
        try {
            searchRecipe.execute().get();
        } catch (Exception e) {
            Log.d("Frag_ser", "onCreateView: " + e.getMessage());
        }

        //검색어를 입력 후 보여지는 레시피 목록
        if(text.length() != 0){
            for( int i=0; i < MyRecipeArrayList.size(); i++ ){
                if( MyRecipeArrayList.get(i).getTitle().toLowerCase().contains(text) ) {
                    myItemList.add(MyRecipeArrayList.get(i));
                    Log.d("Sub", "search text : " + myItemList.get(0).getTitle());
                }
            }
            search_recyclerView.setLayoutManager(layoutManager);
            adapter = new RecipeAdapter(myItemList, activity);
            search_recyclerView.setAdapter(adapter);
        }else{
            //검색어를 입력하지 않았을 때 보여주는 전체 레시피 목록
            search_recyclerView.setLayoutManager(layoutManager);
            search_recyclerView.setAdapter(adapter);
        }

        return view;
    }
}