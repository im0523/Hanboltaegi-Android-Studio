package com.example.han2.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.han2.MainActivity;
import com.example.han2.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TermsActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    CheckBox chk_all = null;
    CheckBox chk_1 = null;
    CheckBox chk_2 = null;
    Button btnAgree, btnDisagree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        ImageView img = findViewById(R.id.back_go);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        chk_all = findViewById(R.id.chk_all);
        chk_1 = findViewById(R.id.chk_1);
        chk_2 = findViewById(R.id.chk_2);
        btnAgree = findViewById(R.id.btnAgree);
        btnDisagree = findViewById(R.id.btnDisagree);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        //동의 버튼 클릭 시
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chk_all.isChecked()){
                    Intent intent = new Intent(TermsActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(TermsActivity.this, "약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //전체 동의 선택 시
        chk_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chk_all.isChecked()){
                    chk_1.setChecked(true);
                    chk_2.setChecked(true);
                }else{
                    chk_1.setChecked(false);
                    chk_2.setChecked(false);
                }
            }//onClick
        });

        //첫번째 약관 동의 시
        chk_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !chk_1.isChecked() ){
                    chk_all.setChecked(false);
                }
                if( chk_1.isChecked() && chk_2.isChecked() ){
                    chk_all.setChecked(true);
                }
            }
        });

        //두번째 약관 동의 시
        chk_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !chk_2.isChecked() ){
                    chk_all.setChecked(false);
                }
                if(chk_2.isChecked() && chk_1.isChecked()){
                    chk_all.setChecked(true);
                }

            }
        });




        //비동의 버튼 클릭 시
        btnDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   // 이 엑티비티 플래그를 사용하여 엑티비티를 호출하게 되면 새로운 태스크를 생성하여 그 태스크안에 엑티비티를 추가하게 됩니다. 단, 기존에 존재하는 태스크들중에 생성하려는 엑티비티와 동일한 affinity(관계, 유사)를 가지고 있는 태스크가 있다면 그곳으로 새 엑티비티가 들어가게됩니다.
                        Intent.FLAG_ACTIVITY_SINGLE_TOP | // 엑티비티를 호출할 경우 호출된 엑티비티가 현재 태스크의 최상단에 존재하고 있었다면 새로운 인스턴스를 생성하지 않습니다. 예를 들어 ABC가 엑티비티 스택에 존재하는 상태에서 C를 호출하였다면 여전히 ABC가 존재하게 됩니다.
                        Intent.FLAG_ACTIVITY_CLEAR_TOP); // 만약에 엑티비티스택에 호출하려는 엑티비티의 인스턴스가 이미 존재하고 있을 경우에 새로운 인스턴스를 생성하는 것 대신에 존재하고 있는 엑티비티를 포그라운드로 가져옵니다. 그리고 엑티비티스택의 최상단 엑티비티부터 포그라운드로 가져올 엑티비티까지의 모든 엑티비티를 삭제합니다.
                startActivity(showIntent);

                finish();
            }
        });


        textView1 = findViewById(R.id.textView1);
        String data = null;
        InputStream inputStream = getResources().openRawResource(R.raw.service);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try{
            while ((line = bufferedReader.readLine()) != null){
                textView1.append(line);
                Log.d("log", line);
                textView1.append("\n");
            }
        } catch (Exception e){
            Log.d("log", "읽기 실패");
        }

        textView2 = findViewById(R.id.textView2);
        data = null;
        inputStream = getResources().openRawResource(R.raw.personal);
        inputreader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputreader);
        text = new StringBuilder();

        try{
            while ((line = bufferedReader.readLine()) != null){
                textView2.append(line);
                Log.d("log", line);
                textView2.append("\n");
            }
        }catch (Exception e){
            Log.d("log", "읽기 실패");
        }

    }

}



