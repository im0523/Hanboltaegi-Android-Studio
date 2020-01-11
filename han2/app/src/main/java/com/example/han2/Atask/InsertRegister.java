package com.example.han2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.nio.charset.Charset;

import static com.example.han2.Common.CommonMethod.ipConfig;

public class InsertRegister extends AsyncTask<Void, Void, Void> {

    String id, pwd, nickname, email;

    // id, name, date, 업로드타입(이미지,비디오), 디비에 입력할 이미지경로, 실제업로드할 이미지경로,디비에 입력할 비디오경로, 실제업로드할 비디오경로
    public InsertRegister(String id, String pwd, String nickname, String email){
        this.id = id;
        this.pwd = pwd;
        this.nickname = nickname;
        this.email = email;
    }

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가

            builder.addTextBody("id", id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("pwd", pwd, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("nickname", nickname, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("email", email, ContentType.create("Multipart/related", "UTF-8"));


            String postURL = ipConfig + "/HanInsert";
            // 전송
            //InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            //inputStream = httpEntity.getContent();

            // 응답
                /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }
                inputStream.close();*/

            // 응답결과
                /*String result = stringBuilder.toString();
                Log.d("response", result);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("Insert", "추가성공");

    }

}
