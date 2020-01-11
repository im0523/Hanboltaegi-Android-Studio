package com.example.han2.Main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Adapter.ChefRankAdapter;
import com.example.han2.Atask.ChefRank;
import com.example.han2.Dto.ChefRankDTO;
import com.example.han2.R;

import java.util.ArrayList;

public class RankMenu3 extends Activity {
    ArrayList<ChefRankDTO> chefRankArrayList = new ArrayList<>();
    ChefRank chefRank;
    RecyclerView recyclerView;
    ChefRankAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_rank);

        

        ImageView img = findViewById(R.id.back_go);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //바텀바 쉐프랭킹 데이터받기
        adapter = new ChefRankAdapter(chefRankArrayList);
        recyclerView = findViewById(R.id.recyclerView_chef_rank);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        chefRank = new ChefRank(chefRankArrayList, adapter);
        chefRank.execute();

    }

       @Override
    public void onResume() {
        chefRankArrayList.clear();
        super.onResume();
    }


}
