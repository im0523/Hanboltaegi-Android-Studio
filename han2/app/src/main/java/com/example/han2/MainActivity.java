package com.example.han2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.han2.Atask.Grade;
import com.example.han2.Atask.Grade1;
import com.example.han2.Atask.Grade2;
import com.example.han2.Atask.Login;
import com.example.han2.Common.CommonMethod;
import com.example.han2.Dto.MemberDTO;
import com.example.han2.Dto.MyRecipeDTO;
import com.example.han2.Main.SubActivity;
import com.example.han2.Navigation.TermsActivity;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private SessionCallback callback;
    public static MemberDTO loginDTO = null;
    public static MyRecipeDTO recipeDTO = null;
    ImageLoader imageLoader;

    EditText userid, userpwd;
    Button btnLogin;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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

        //전체화면으로 보기
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.first_main);
        KakaoSDK.init(new GlobalApplication.KakaoSDKAdapter());

        userid = findViewById(R.id.userid);
        userpwd = findViewById(R.id.userpwd);
        btnLogin = findViewById(R.id.btnLogin);
        register = findViewById(R.id.register);


        //회원가입 버튼 클릭 시
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TermsActivity.class);
                startActivity(intent);
            }
        });


        //로그인 버튼 클릭 시
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CommonMethod.isNetworkConnected(getApplicationContext()) == true) {


                    String id = userid.getText().toString();
                    String pwd = userpwd.getText().toString();
                    if ((id.length() == 0) || (pwd.length() == 0)) {
                        Toast.makeText(MainActivity.this,
                                "ID/PASSWORD를 모두 입력하세요!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Login login = new Login(id, pwd);
                    try {
                        login.execute().get();

                        int ids = Integer.parseInt(loginDTO.getCnt());
                        String ids2 = String.valueOf(ids);
                        Log.d("TAG: gradeupdate", "onClick: cnt " + ids);
                        if (1 < ids && ids < 10) {
                            Grade grade = new Grade(ids2);
                            grade.execute();
                        } else if (10 < ids && ids < 30) {
                            Grade1 grade1 = new Grade1(ids2);
                            grade1.execute();
                        } else if (30< ids){
                            Grade2 grade2 = new Grade2(ids2);
                            grade2.execute();
                        }


                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (loginDTO != null) {
                        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                        startActivity(intent);
                        checkDangerousPermissions();
                        userid.setText("");
                        userpwd.setText("");


                    } else {
                        userid.setText("");
                        userpwd.setText("");
                        Toast.makeText(MainActivity.this,
                                "아이디나 비밀번호가 일치하지않습니다!!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "인터넷이 연결되어 있지 않습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
        }
    }

    protected void redirectSignupActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
                /* Manifest.permission.FLASHLIGHT*/
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}