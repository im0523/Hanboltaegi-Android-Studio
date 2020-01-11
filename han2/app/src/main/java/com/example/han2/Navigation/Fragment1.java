package com.example.han2.Navigation;

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

import com.example.han2.Adapter.RecipeAdapter;
import com.example.han2.Atask.MyRecipe;
import com.example.han2.Dto.MyRecipeDTO;
import com.example.han2.Main.Fragment_main;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import java.util.ArrayList;

import static com.example.han2.MainActivity.loginDTO;

public class Fragment1 extends Fragment {
    private static final String TAG = "Fragment1";

    ArrayList<MyRecipeDTO> MyRecipeArrayList = new ArrayList<>();
    MyRecipe myRecipe;
    SubActivity activity;
    RecyclerView recyclerView;
    RecipeAdapter adapter;
    Bundle bundle;
    String userid = "";

    public static Fragment getInstance() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

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

        userid = loginDTO.getId();
        activity = (SubActivity) getActivity();
        // 리사이클러 뷰 시작
        adapter = new RecipeAdapter(MyRecipeArrayList, activity);
        recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        myRecipe = new MyRecipe(MyRecipeArrayList, adapter, userid);
        myRecipe.execute();

        return view;
    }
}