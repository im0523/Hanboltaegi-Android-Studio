package com.example.han2.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Adapter.RecipeAdapter;
import com.example.han2.Adapter.SearchAllListAdapter;
import com.example.han2.Atask.SearchAllList;
import com.example.han2.Atask.SearchInsert;
import com.example.han2.Atask.SearchUpdate;
import com.example.han2.Common.CommonMethod;
import com.example.han2.Dto.CommunityDTO;
import com.example.han2.Dto.ScrapDTO;
import com.example.han2.Dto.SearchAllDTO;
import com.example.han2.Dto.SearchWeekDTO;
import com.example.han2.Dto.WeekRecipeDTO;
import com.example.han2.Navigation.Frag_Search;
import com.example.han2.Navigation.Fragment1;
import com.example.han2.Navigation.Fragment3;
import com.example.han2.Navigation.Fragment4;
import com.example.han2.Navigation.Fragment4_1;
import com.example.han2.Navigation.Fragment4_detail;
import com.example.han2.Navigation.Fragment4_update;
import com.example.han2.Navigation.MemberInfo;
import com.example.han2.Navigation.Nav_logout;
import com.example.han2.R;
import com.example.han2.RecWrite.RecWrite;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static com.example.han2.MainActivity.loginDTO;


public class SubActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static Toolbar toolbar;
    public static String text;
    RecipeAdapter adapter;

    private DrawerLayout drawer;
    private FloatingActionButton mFaba;

    Fragment fragment1, fragment3, fragment4, fragment4_1, fragment4_detail, recipe_detail, fragment4_update, fragment_main, fragment_community, fragment_setrank, comment, commentupdate;
    NavigationView navigationView;
    EditText search_title;      //검색어를 입력할 input 창
    Button search_btn;

    String content = "", count = "";
    public Bundle mBundle = null;
    public static CommunityDTO selItem = null;
    public static WeekRecipeDTO wItem = null;
    public static ScrapDTO sItem = null;

    SearchWeekDTO searchWeekDTO = null;



    SearchAllList searchAllList;
    SearchAllListAdapter adapter3;
    RecyclerView recyclerView3;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     /*   checkDangerousPermissions();*/

        fragment1 = new Fragment1();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment4_1 = new Fragment4_1();
        fragment4_detail = new Fragment4_detail();
        fragment4_update = new Fragment4_update();

        fragment_main = new Fragment_main();
        fragment_community = new Fragment_community();
        comment = new Comment();
        fragment_setrank = new Fragment_setrank();
        commentupdate = new CommentUpdate();


        toolbar = findViewById(R.id.toolbar);

        search_title = findViewById(R.id.search_title);
        search_btn = findViewById(R.id.search_btn);

        //검색어 저장처리 리스트가져오기
        final ArrayList<SearchAllDTO> searchweek = new ArrayList<>();
        adapter3 = new SearchAllListAdapter(searchweek);
        recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setVisibility(View.GONE);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setAdapter(adapter3);
        recyclerView3.setNestedScrollingEnabled(false);
        searchAllList = new SearchAllList(searchweek, adapter3);
        searchAllList.execute();

        mFaba = findViewById(R.id.faba);
        mFaba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent refresh = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(refresh);
                finish();

                Intent intent = new Intent(getApplicationContext(), RecWrite.class);
                startActivity(intent);
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = search_title.getText().toString();
                Log.d("TAG:search", "onClick: "+text);
                content = text;
                boolean isExist = false;
                for( int i=0; i < searchweek.size(); i++ ) {
                    if (searchweek.get(i).getContent().equals(content)) {
                        isExist = true;
                           SearchUpdate searchUpdate = new SearchUpdate(searchweek.get(i).getCount(),searchweek.get(i).getNo());
                           searchUpdate.execute();
                        Log.d("searchupdate", "onClick: "+searchweek.get(i).getCount()+"<>"+searchweek.get(i).getNo()+"<>"+searchweek.get(i).getContent()+"<>"+content);
                        break;
                    }
                }

                if (isExist == false) {
                    SearchInsert searchInsert = new SearchInsert(content);
                    searchInsert.execute();
                    Log.d("searchlnsert", "onClick: " + content);
                }

                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(search_title.getWindowToken(), 0);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Frag_Search()).commit();
                search_title.setText("");
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_main()).commit();
            navigationView.setCheckedItem(R.id.my_recipe1);
        }

        //네비게이션 드로어 버튼클릭시
        View headerView = navigationView.getHeaderView(0);
        Button button1 = headerView.findViewById(R.id.btn_alarm);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemberInfo.class);
                startActivity(intent);
                finish();
            }
        });
        Button button2 = headerView.findViewById(R.id.btn_logout);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Nav_logout.class);
                startActivity(intent);
                finish();
            }
        });

        //로그인시 네비게이션드로어에 로그인 정보 띄우기
        if (CommonMethod.isNetworkConnected(getApplicationContext()) == true) {
            TextView nav_text1;
            nav_text1 = headerView.findViewById(R.id.nav_text1);
            nav_text1.setText(loginDTO.getNickname());
        }


        Log.d("TAG:logingrade", "onCreate: "+loginDTO.getId()+loginDTO.getGrade_id());
        ImageView imageview = headerView.findViewById(R.id.imageView_log);
        if(loginDTO.getGrade_id().equals("10")){
            imageview.setImageResource(R.drawable.crown);
        } else if (loginDTO.getGrade_id().equals("1")){
            imageview.setImageResource(R.drawable.crown2);
        }
        if(loginDTO.getGrade_id().equals("2")){
            imageview.setImageResource(R.drawable.crown3);
        }

        //바텀바 선택시 화면전환
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_bottom1:
                        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment_main).commit();
                        break;
                    case R.id.action_bottom2:
                        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment_community).commit();
                        break;
                    case R.id.action_bottom3:
                        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment_setrank).commit();
                        break;
                }
                return true;

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //네비게이션 드로어 선택시 화면선택
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.my_recipe1:
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment1).commit();
                break;
            case R.id.my_re_sc:
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment3).commit();
                break;
            case R.id.nav_notice:
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment4).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void fragment_btnClick(int state, Bundle bundle) {
        this.mBundle = bundle;

        if (state == 4) { // 공지사항 글쓰기 화면으로 가기
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment4_1).commit();
        }
        if(state == 5){ // 공지사항 detail 화면으로 가기
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment4_detail).commit();
        }
        if(state == 8){ //comment 글 작성 페이지로 가기
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, comment).commit();
        }
        if(state == 9){ //community 커뮤니티 페이지로 가기
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment_community).commit();
        }
        if(state == 10){ //commentUpdate 수정 페이지로 가기
            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, commentupdate).commit();
        }
    }
}
