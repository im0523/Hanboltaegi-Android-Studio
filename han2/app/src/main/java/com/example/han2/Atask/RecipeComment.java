package com.example.han2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.han2.Adapter.RecipeCommentAdapter;
import com.example.han2.Common.CommonMethod;
import com.example.han2.Dto.RecipeCommentDTO;

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
import java.util.ArrayList;

public class RecipeComment extends AsyncTask<Void, Void, Void> {
    ArrayList<RecipeCommentDTO> arrayList;
    RecipeCommentAdapter adapter;
    String rc_id;

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;


    public RecipeComment(ArrayList<RecipeCommentDTO> arrayList, RecipeCommentAdapter adapter, String rc_id) {
        this.arrayList = arrayList;
        this.adapter = adapter;
        this.rc_id = rc_id;
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
            builder.addTextBody("rc_id", rc_id, ContentType.create("Multipart/related", "UTF-8"));
            String postURL = CommonMethod.ipConfig + "/recipeComment";
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

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.notifyDataSetChanged();
    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                arrayList.add(readMessage(reader));

            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }


    public RecipeCommentDTO readMessage(JsonReader reader) throws IOException {
        String nickname = "", rc_id= "", member_id= "", content= "", writedate= "", no= "";
        ;
        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("nickname")) {
                nickname = reader.nextString();
            } else if (readStr.equals("rc_id")) {
                rc_id = reader.nextString();
            } else if (readStr.equals("member_id")) {
                member_id = reader.nextString();
            } else if (readStr.equals("content")) {
                content = reader.nextString();
            } else if (readStr.equals("writedate")) {
                writedate = reader.nextString();
            } else if (readStr.equals("no")) {
                no = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("recipecom", nickname + ',' + rc_id + ',' + member_id + ',' + content);
        return new RecipeCommentDTO(nickname, rc_id, member_id, content, writedate, no);

    }
}
