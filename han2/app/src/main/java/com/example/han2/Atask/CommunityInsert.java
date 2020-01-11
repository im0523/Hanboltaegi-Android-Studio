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

public class CommunityInsert extends AsyncTask<Void, Void, Void> {

    String nickname, content, userid;

    public CommunityInsert(String nickname, String content, String userid){
        this.nickname = nickname;
        this.content = content;
        this.userid = userid;
    }

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

            builder.addTextBody("nickname", nickname, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("content", content, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("userid", userid, ContentType.create("Multipart/related", "UTF-8"));
            /*builder.addTextBody("writedate", writedate, ContentType.create("Multipart/related", "UTF-8"));*/
            Log.d("seeeAfter",  "이름 : " + nickname + ", 내용: " + content + ", 아이디: " + userid);

            String postURL = ipConfig + "/CommunityInsert";
            // 전송
            //InputStream inputStream = null;
            HttpClient httpClient = AndroidHttpClient.newInstance("Android");
            HttpPost httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
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
        Log.d("CommunityInsert:", "추가성공");

    }


}






















