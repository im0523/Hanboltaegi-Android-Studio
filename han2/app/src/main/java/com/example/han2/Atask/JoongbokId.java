package com.example.han2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.han2.Dto.JoongbokDTO;

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

import static com.example.han2.Common.CommonMethod.ipConfig;
import static com.example.han2.Navigation.RegisterActivity.joongbokDTO;


public class JoongbokId extends AsyncTask<Void, Void, Void> {

    String id;

    public JoongbokId(String id) {
        this.id = id;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            // 문자열 및 데이터 추가
            builder.addTextBody("id", id, ContentType.create("Multipart/related", "UTF-8"));

            String postURL = ipConfig + "/JoongbokId";
            // 전송
            //InputStream inputStream = null;
            HttpClient httpClient = AndroidHttpClient.newInstance("Android");
            HttpPost httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();


            //응답
            //Select 부분 복사해옴.
            InputStream inputStream = null;
            inputStream = httpEntity.getContent();

            readJsonStream(inputStream);


        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }

    //응답
    //Select 부분 복사해옴. -- json형태의 데이터를 파싱
    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                joongbokDTO = readMessage(reader);
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }
    //응답
    //Select 부분 복사해옴. -- json형태의 데이터를 파싱
    public JoongbokDTO readMessage(JsonReader reader) throws IOException {
        String id = "", nickname = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("id")) {
                id = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("login:memberdto", id );
        return new JoongbokDTO(id, nickname);

    }


}
