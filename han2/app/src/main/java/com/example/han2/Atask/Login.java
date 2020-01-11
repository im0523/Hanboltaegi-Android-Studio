package com.example.han2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.han2.Dto.MemberDTO;
import com.example.han2.Common.CommonMethod;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.han2.MainActivity.loginDTO;


/**
 * Created by LG on 2017-02-10.
 */

public class Login extends AsyncTask<Void, Void, Void> {

    //생성자
    String id;
    String pwd;

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    public Login(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            // 문자열 및 데이터 추가
            builder.addTextBody("id", id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("pwd", pwd, ContentType.create("Multipart/related", "UTF-8"));
            String postURL = CommonMethod.ipConfig + "/HanLogin";

            // 전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            readJsonStream(inputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                loginDTO = readMessage(reader);
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }



    public MemberDTO readMessage(JsonReader reader) throws IOException {
        String id = "", pwd = "", nickname = "", email = "", cnt = "", grade_id = "", user_pic = "", follower = "", following = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("id")) {
                id = reader.nextString();
            } else if (readStr.equals("pwd")) {
                pwd = reader.nextString();
            } else if (readStr.equals("nickname")) {
                nickname = reader.nextString();
            } else if (readStr.equals("email")) {
                email = reader.nextString();
            } else if (readStr.equals("cnt")) {
                cnt = reader.nextString();
            } else if (readStr.equals("grade_id")) {
                grade_id = reader.nextString();
            } else if (readStr.equals("user_pic")) {
                user_pic = reader.nextString();
            } else if (readStr.equals("follower")) {
                follower = reader.nextString();
            } else if (readStr.equals("following")) {
                following = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("login:memberdto", id + "," + pwd + "," + nickname + "," + email + "," + grade_id + "," + user_pic + "," + follower + "," + following);
        return new MemberDTO( id,  pwd, nickname, email, cnt, grade_id, user_pic, follower, following);

    }

}
