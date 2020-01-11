package com.example.han2.Main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.han2.Atask.CommunityInsert;
import com.example.han2.R;

import java.util.concurrent.ExecutionException;

import static com.example.han2.MainActivity.loginDTO;


public class Comment extends Fragment {

    TextView co_Nickname;
    EditText co_content;
    Button co_btnSubmit, co_btnCancle;
    String nickname = "", content = "", userid = "";
    SubActivity activity;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);


        userid = loginDTO.getId();
        co_Nickname = view.findViewById(R.id.co_Nickname);
        co_content = view.findViewById(R.id.co_content);
        co_Nickname.setText(loginDTO.getNickname());

        /*activity = (SubActivity) getActivity();
        if (activity.mBundle != null) {\
            bundle = activity.mBundle;

            *//*String name = bundle.getString("name");
            Toast.makeText(activity, " Name : " + name, Toast.LENGTH_SHORT).show();*//*

            activity.mBundle = null;
        }*/

        co_btnSubmit = view.findViewById(R.id.co_btnSubmit);
        co_btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = co_content.getText().toString();
                co_Nickname.setText(loginDTO.getNickname());
                nickname = co_Nickname.getText().toString();

                CommunityInsert communityInsert = new CommunityInsert(nickname, content, userid);
                Log.d("seeeBegin", "이름 : " + nickname + ", 제목 :" + content);
                co_content.setText("");
                try {
                    communityInsert.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /*Bundle bundle = new Bundle();*/
                //추가
                activity = (SubActivity) getActivity();
                activity.fragment_btnClick(9, bundle);

                /*FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment_community = new Fragment_community();
                fragmentTransaction.replace(R.id.fragment_container, fragment_community).commit();*/

//                Intent showIntent = new Intent(activity.getApplicationContext(), Fragment6.class);
//                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   // 이 엑티비티 플래그를 사용하여 엑티비티를 호출하게 되면 새로운 태스크를 생성하여 그 태스크안에 엑티비티를 추가하게 됩니다. 단, 기존에 존재하는 태스크들중에 생성하려는 엑티비티와 동일한 affinity(관계, 유사)를 가지고 있는 태스크가 있다면 그곳으로 새 엑티비티가 들어가게됩니다.
//                        Intent.FLAG_ACTIVITY_SINGLE_TOP | // 엑티비티를 호출할 경우 호출된 엑티비티가 현재 태스크의 최상단에 존재하고 있었다면 새로운 인스턴스를 생성하지 않습니다. 예를 들어 ABC가 엑티비티 스택에 존재하는 상태에서 C를 호출하였다면 여전히 ABC가 존재하게 됩니다.
//                        Intent.FLAG_ACTIVITY_CLEAR_TOP); // 만약에 엑티비티스택에 호출하려는 엑티비티의 인스턴스가 이미 존재하고 있을 경우에 새로운 인스턴스를 생성하는 것 대신에 존재하고 있는 엑티비티를 포그라운드로 가져옵니다. 그리고 엑티비티스택의 최상단 엑티비티부터 포그라운드로 가져올 엑티비티까지의 모든 엑티비티를 삭제합니다.
//                startActivity(showIntent);
//
//                activity.finish();


            }/*else {
                    Toast.makeText(this, "인터넷이 연결되어 있지 않습니다.",
                            Toast.LENGTH_SHORT).show();
                }*/


            /*private boolean isNetworkConnected() {
                return false;
            }*/

        });

        co_btnCancle = view.findViewById(R.id.co_btnCancle);
        co_btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = (SubActivity) getActivity();
                //커뮤니티 페이지로 돌아가기
                activity.fragment_btnClick(9, bundle);

            }


        });
        return view;

    }


}
