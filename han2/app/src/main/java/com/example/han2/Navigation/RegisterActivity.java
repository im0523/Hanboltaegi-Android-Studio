package com.example.han2.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.han2.Atask.InsertRegister;
import com.example.han2.Atask.JoongbokId;
import com.example.han2.Atask.JoongbokNick;
import com.example.han2.Dto.JoongbokDTO;
import com.example.han2.Dto.MemberDTO;
import com.example.han2.MainActivity;
import com.example.han2.R;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.han2.Common.CommonMethod.isNetworkConnected;

public class RegisterActivity extends AppCompatActivity {

    public static JoongbokDTO joongbokDTO = null;
    Button btnBack;
    EditText userid, userpwd, userpwd_ck, e_email, e_nickname;
    TextView joongbokId, joongbokNick;
    String id = "", pwd = "", nickname = "", email = "";
    boolean jbokId = false;
    boolean jbokNick = false;
    ImageView setImage1, setImage2;

    MemberDTO item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        btnBack = findViewById(R.id.btnBack);
        userid = findViewById(R.id.userid);
        userpwd = findViewById(R.id.userpwd);
        userpwd_ck = findViewById(R.id.userpwd_ck);
        e_email = findViewById(R.id.email);
        e_nickname = findViewById(R.id.nickname);
        joongbokId = findViewById(R.id.joongbokId);
        joongbokNick = findViewById(R.id.joongbokNick);
        setImage1 = findViewById(R.id.setImage1);
        setImage2 = findViewById(R.id.setImage2);

        //닉네임 중복확인 버튼 클릭 시
        joongbokNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = e_nickname.getText().toString();

                JoongbokNick joongbokNick = new JoongbokNick(nickname);
                try {
                    joongbokNick.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(joongbokDTO != null){
                    Toast.makeText(RegisterActivity.this, "이미 사용중인 닉네임입니다!", Toast.LENGTH_SHORT).show();
                    joongbokDTO = null;
                    return;
                }else{
                    Toast.makeText(RegisterActivity.this, "사용 가능한 닉네임입니다!", Toast.LENGTH_SHORT).show();
                    jbokNick = true;
                }

            }
        });






        //아이디 중복확인 버튼 클릭 시
        joongbokId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = userid.getText().toString();

                JoongbokId joongbokId = new JoongbokId(id);
                try {
                    joongbokId.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(joongbokDTO != null){
                    Toast.makeText(RegisterActivity.this, "이미 사용중인 아이디입니다!", Toast.LENGTH_SHORT).show();
                    joongbokDTO = null;
                    return;
                }else{
                    Toast.makeText(RegisterActivity.this, "사용 가능한 아이디입니다!", Toast.LENGTH_SHORT).show();
                    jbokId = true;
                }

            }
        });

        //비밀번호 확인 시 체크표시
        userpwd_ck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(userpwd.getText().toString().equals(userpwd_ck.getText().toString())){
                    setImage1.setImageResource(R.drawable.ic_check_green_24dp);
                } else {
                    setImage1.setImageResource(R.drawable.ic_close_red_24dp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





        //이메일형식 체크표시
        e_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!checkEmail(e_email.getText().toString())) {
                    setImage2.setImageResource(R.drawable.ic_close_red_24dp);
                }else{
                    setImage2.setImageResource(R.drawable.ic_check_green_24dp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });






        //뒤로가기 버튼 클릭 시
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, TermsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
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




    //가입하기 버튼 클릭 시
    public void btnRegClicked(View view) {
        //아이디 공백일 경우
        if( userid.getText().toString().length() == 0){
            Toast.makeText(this, "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
            userid.requestFocus();
            return;
        }

        //아이디 중복확인 안 했을 경우
        if (jbokId == false) {
            Toast.makeText(this, "아이디 중복확인 하세요!", Toast.LENGTH_SHORT).show();
            return;
        }

        //닉네임 공백일 경우
        if( e_nickname.getText().toString().length() == 0){
            Toast.makeText(this, "닉네임을 입력하세요!", Toast.LENGTH_SHORT).show();
            e_nickname.requestFocus();
            return;
        }

        //닉네임 중복확인 안 했을 경우
        if (jbokNick == false) {
            Toast.makeText(this, "닉네임 중복확인 하세요!", Toast.LENGTH_SHORT).show();
            return;
        }


        //비밀번호 공백일 경우
        if( userpwd.getText().toString().length() == 0){
            Toast.makeText(this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
            userpwd.requestFocus();
            return;
        }

        //비번확인 공백일 경우
        if( userpwd_ck.getText().toString().length() == 0){
            Toast.makeText(this, "비밀번호를 한번 더 입력해주세요!", Toast.LENGTH_SHORT).show();
            userpwd_ck.requestFocus();
            return;
        }

        //비밀번호 불일치할 경우
        if( !userpwd_ck.getText().toString().equals(userpwd.getText().toString()) ){
            Toast.makeText(this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
            userpwd.setText("");
            userpwd_ck.setText("");
            userpwd.requestFocus();
            return;
        }

        //비밀번호 형식 불일치할 경우
        if ( !isValidPassword(userpwd.getText().toString())) {
            Toast.makeText(this, "비밀번호는 숫자와 영문을 포함하여 8글자 이상, 20글자 이하로 작성해주십시오!", Toast.LENGTH_SHORT).show();
            userpwd.requestFocus();
            return;
        }

        //이메일 공백일 경우
        if( e_email.getText().toString().length() == 0){
            Toast.makeText(this, "이메일을 입력하세요!", Toast.LENGTH_SHORT).show();
            e_email.requestFocus();
            return;
        }

        //이메일 형식이 잘못될 경우
        if(!checkEmail(e_email.getText().toString())){
            Toast.makeText(this, "잘못된 이메일입니다!", Toast.LENGTH_SHORT).show();
            e_email.requestFocus();
            return;
        }












        if (isNetworkConnected(this) == true) {

            id = userid.getText().toString();
            pwd = userpwd.getText().toString();
            nickname = e_nickname.getText().toString();
            email = e_email.getText().toString();

            InsertRegister listInsert = new InsertRegister(id, pwd, nickname, email);
            listInsert.execute();
            Toast.makeText(this, "회원으로 가입되셨습니다!", Toast.LENGTH_SHORT).show();


            Intent showIntent = new Intent(getApplicationContext(), MainActivity.class);
            showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   // 이 엑티비티 플래그를 사용하여 엑티비티를 호출하게 되면 새로운 태스크를 생성하여 그 태스크안에 엑티비티를 추가하게 됩니다. 단, 기존에 존재하는 태스크들중에 생성하려는 엑티비티와 동일한 affinity(관계, 유사)를 가지고 있는 태스크가 있다면 그곳으로 새 엑티비티가 들어가게됩니다.
                    Intent.FLAG_ACTIVITY_SINGLE_TOP | // 엑티비티를 호출할 경우 호출된 엑티비티가 현재 태스크의 최상단에 존재하고 있었다면 새로운 인스턴스를 생성하지 않습니다. 예를 들어 ABC가 엑티비티 스택에 존재하는 상태에서 C를 호출하였다면 여전히 ABC가 존재하게 됩니다.
                    Intent.FLAG_ACTIVITY_CLEAR_TOP); // 만약에 엑티비티스택에 호출하려는 엑티비티의 인스턴스가 이미 존재하고 있을 경우에 새로운 인스턴스를 생성하는 것 대신에 존재하고 있는 엑티비티를 포그라운드로 가져옵니다. 그리고 엑티비티스택의 최상단 엑티비티부터 포그라운드로 가져올 엑티비티까지의 모든 엑티비티를 삭제합니다.
            startActivity(showIntent);

            finish();


        } else {
            Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                    Toast.LENGTH_SHORT).show();
        }
    }


}


