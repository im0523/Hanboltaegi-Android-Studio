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

public class MemberUpdate extends AsyncTask<Void, Void, Void> {

    String nickname, pwd, email, id;

    public MemberUpdate(String nickname, String pwd, String email, String id) {
        this.nickname = nickname;
        this.pwd = pwd;
        this.email = email;
        this.id = id;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // MultipartEntityBuild 생성
            String postURL = "";
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가
            builder.addTextBody("nickname", nickname, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("pwd", pwd, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("email", email, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("id", id, ContentType.create("Multipart/related", "UTF-8"));

            Log.d("HanUpdate", nickname);
            Log.d("HanUpdate", pwd);
            Log.d("HanUpdate", email);
            Log.d("HanUpdate", id);


            postURL = ipConfig + "/HanUpdate";
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
                }*/
            //inputStream.close();

            // 응답결과
               /* String result = stringBuilder.toString();
                Log.d("response", result);*/



        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //dialog.dismiss();

    }


}
