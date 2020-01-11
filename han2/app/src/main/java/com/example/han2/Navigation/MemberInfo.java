package com.example.han2.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.han2.Atask.JoongbokNick;
import com.example.han2.Atask.MemberUpdate;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.han2.MainActivity.loginDTO;
import static com.example.han2.Navigation.RegisterActivity.joongbokDTO;

public class MemberInfo extends Activity {

    private static final String TAG = "MemberUpdate";
    boolean jbN = false;
    EditText chNick, chPwd, chPwd_ck, chEmail;
    ImageView setCk1, setCk2;
    String nickname = "", pwd = "", email = "", id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_change);

        chNick = findViewById(R.id.chNick);
        chPwd = findViewById(R.id.chPwd);
        chPwd_ck = findViewById(R.id.chPwd_ck);
        chEmail = findViewById(R.id.chEmail);
        setCk1 = findViewById(R.id.setCk1);
        setCk2 = findViewById(R.id.setCk2);


        //로그인 정보 띄우기
        /*if (CommonMethod.isNetworkConnected(getApplicationContext()) == true) {

            EditText editText1, editText3;
            editText1 = findViewById(R.id.userid);
            editText1.setText(loginDTO.getNickname());
            editText3 = findViewById(R.id.email);
            editText3.setText(loginDTO.getEmail());
        }*/

        id = loginDTO.getId();
        chNick.setText(loginDTO.getNickname());
        chEmail.setText(loginDTO.getEmail());

        //버튼 클릭시
        Button button2 = findViewById(R.id.btnMain);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //닉네임 중복확인 클릭 시
        TextView jbNick = findViewById(R.id.jbNick);
        jbNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = chNick.getText().toString();

                JoongbokNick joongbokNick = new JoongbokNick(nickname);
                try {
                    joongbokNick.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(joongbokDTO != null){
                    Toast.makeText(MemberInfo.this, "이미 사용중인 닉네임입니다!", Toast.LENGTH_SHORT).show();
                    joongbokDTO = null;
                    return;
                }else{
                    Toast.makeText(MemberInfo.this, "사용 가능한 닉네임입니다!", Toast.LENGTH_SHORT).show();
                    jbN = true;
                }
            }
        });


        //비밀번호 확인 시 체크 표시
        chPwd_ck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(chPwd.getText().toString().equals(chPwd_ck.getText().toString())){
                    setCk1.setImageResource(R.drawable.ic_check_green_24dp);
                } else {
                    setCk1.setImageResource(R.drawable.ic_close_red_24dp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //이메일 형식 체크 표시
        chEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!checkEmail(chEmail.getText().toString())) {
                    setCk2.setImageResource(R.drawable.ic_close_red_24dp);
                }else{
                    setCk2.setImageResource(R.drawable.ic_check_green_24dp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        //정보 변경 버튼 클릭 시
        Button chBtnReg = findViewById(R.id.chBtnReg);
        chBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //닉네임 공백일 경우
                if( chNick.getText().toString().length() == 0){
                    Toast.makeText(MemberInfo.this, "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
                    chNick.requestFocus();
                    return;
                }

                //비밀번호 공백일 경우
                if( chPwd.getText().toString().length() == 0){
                    Toast.makeText(MemberInfo.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    chPwd.requestFocus();
                    return;
                }

                //비번확인 공백일 경우
                if( chPwd_ck.getText().toString().length() == 0){
                    Toast.makeText(MemberInfo.this, "비밀번호를 한번 더 입력해주세요!", Toast.LENGTH_SHORT).show();
                    chPwd_ck.requestFocus();
                    return;
                }

                //비밀번호 불일치할 경우
                if( !chPwd_ck.getText().toString().equals(chPwd.getText().toString()) ){
                    Toast.makeText(MemberInfo.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    chPwd.setText("");
                    chPwd_ck.setText("");
                    chPwd.requestFocus();
                    return;
                }

                //비밀번호 형식 불일치할 경우
                if ( !isValidPassword(chPwd.getText().toString())) {
                    Toast.makeText(MemberInfo.this, "비밀번호는 숫자와 영문을 포함하여 8글자 이상, 20글자 이하로 작성해주십시오!", Toast.LENGTH_SHORT).show();
                    chPwd.requestFocus();
                    return;
                }

                //이메일 공백일 경우
                if( chEmail.getText().toString().length() == 0){
                    Toast.makeText(MemberInfo.this, "이메일을 입력하세요!", Toast.LENGTH_SHORT).show();
                    chEmail.requestFocus();
                    return;
                }

                //이메일 형식이 잘못될 경우
                if(!checkEmail(chEmail.getText().toString())){
                    Toast.makeText(MemberInfo.this, "잘못된 이메일입니다!", Toast.LENGTH_SHORT).show();
                    chEmail.requestFocus();
                    return;
                }

                //닉네임 중복확인 안 했을 경우
                if (jbN == false) {
                    Toast.makeText(MemberInfo.this, "닉네임 중복확인 하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }



                nickname = chNick.getText().toString();
                pwd = chPwd.getText().toString();
                email = chEmail.getText().toString();


                MemberUpdate memberUpdate = new MemberUpdate(nickname, pwd, email, id);
                Log.d(TAG, "회원정보변경 : " + nickname + ", " + pwd + ", " + email + ", " + id );
                try {
                    memberUpdate.execute().get();
                    loginDTO.setNickname(nickname);
                    loginDTO.setEmail(email);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
                finish();

            }
        });




    }//onCreate


    //이메일 유효성 검사
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }


    //비밀번호 유효성 검사
    public static boolean isValidPassword(String pwd){

        boolean returnValue = false;
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pwd);

        if (m.matches()) {

            returnValue = true;

        }
        return returnValue;
    }


}//class
