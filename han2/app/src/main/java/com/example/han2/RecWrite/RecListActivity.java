package com.example.han2.RecWrite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Adapter.MyRecyclerviewAdapter;
import com.example.han2.Atask.ListDelete;
import com.example.han2.Atask.ListSelect;
import com.example.han2.Dto.MaterialDTO;
import com.example.han2.Dto.ProcedureDTO;
import com.example.han2.Dto.RecipeDTO;
import com.example.han2.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;


public class RecListActivity extends AppCompatActivity {

    ListSelect listSelect;

    ArrayList<RecipeDTO> recipeDTOArrayList;
    Button btnUpdate, btnDelete,btnInsertView;

    RecyclerView recyclerView;
    MyRecyclerviewAdapter adapter;

    RecipeDTO selItem = null;
    MaterialDTO selItem1 = null;
    ProcedureDTO selItem2 = null;


    ImageLoader imageLoader;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rec_list);

        // 이미지 로딩 써드파트
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.blank) // resource or drawable
                .showImageForEmptyUri(R.drawable.blank) // resource or drawable
                .showImageOnFail(R.drawable.blank)// resource or drawable
                .build();

        ImageLoaderConfiguration config =
                new ImageLoaderConfiguration.Builder(getApplicationContext())
                        //  .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 10 * 1000))
                        .defaultDisplayImageOptions(options)
                        .build();

        imageLoader.getInstance().init(config); // Get singleton instance

        btnInsertView = findViewById(R.id.btnInsertView);

        btnInsertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecWrite.class);
                startActivity(intent);
                finish();
            }
        });

        // 리사이클러 뷰 시작
        recipeDTOArrayList = new ArrayList();
        adapter = new MyRecyclerviewAdapter(recipeDTOArrayList);
        recyclerView = findViewById(R.id.reclist);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        // 리싸이클러 뷰 항목이 눌릴때
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(MyRecyclerviewAdapter.ItemViewHolder holder, View view, int position) {

                selItem = adapter.getItem(position);
                Toast.makeText(RecListActivity.this, "아이템 선택됨 : " + selItem.getRecipe_id(), Toast.LENGTH_SHORT).show();

            }
        });
        // 리사이클러 뷰 종료

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        listSelect = new ListSelect(recipeDTOArrayList, adapter,progressDialog);
        listSelect.execute();

        //레시피 수정
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selItem != null){
                    Log.d("sub1:update1", selItem.getRecipe_id());

                    Intent intent = new Intent(getApplicationContext(), RecUpdate.class);
                    intent.putExtra("selItem", selItem);

                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(getApplicationContext(), "항목 선택을 해 주세요",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //삭제
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selItem != null){
                    ListDelete listDelete = new ListDelete(selItem.getRecipe_id());
                    listDelete.execute();

                    // 화면갱신
                    Intent refresh = new Intent(getApplicationContext(), RecListActivity.class);
                    startActivity(refresh);
                    finish();

                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(), "항목 선택을 해 주세요",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    // 이미 화면이 있을때 받는곳
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("Sub1", "onNewIntent() 호출됨");

        // 새로고침하면서 이미지가 겹치는 현상 없애기 위해...
        adapter.removeAllItem();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("데이터 업로딩");
        progressDialog.setMessage("데이터 업로딩 중입니다\n" + "잠시만 기다려주세요 ...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        processIntent(intent);


    }

    private void processIntent(Intent intent){
        if(intent != null){
            listSelect = new ListSelect(recipeDTOArrayList, adapter,progressDialog);
            listSelect.execute();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}