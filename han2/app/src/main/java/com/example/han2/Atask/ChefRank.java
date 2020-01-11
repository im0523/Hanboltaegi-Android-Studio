package com.example.han2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.han2.Adapter.ChefRankAdapter;
import com.example.han2.Common.CommonMethod;
import com.example.han2.Dto.ChefRankDTO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ChefRank extends AsyncTask<Void, Void, Void> {
    ArrayList<ChefRankDTO> chefArrayList;
    ChefRankAdapter adapter;


    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;
    public ChefRank(ArrayList<ChefRankDTO> chefArrayList, ChefRankAdapter adapter) {
        this.chefArrayList = chefArrayList;
        this.adapter = adapter;
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
            String postURL = CommonMethod.ipConfig + "/chefRank";
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

    private void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                chefArrayList.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }

    }

    public ChefRankDTO readMessage(JsonReader reader) throws IOException {
        String no = "", id = "", nickname = "", good = "", recipe = "";
        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("no")) {
                no = reader.nextString();
            } else if (readStr.equals("id")) {
                id = reader.nextString();
            } else if (readStr.equals("nickname")) {
                nickname = reader.nextString();
            } else if (readStr.equals("good")) {
                good = reader.nextString();
            } else if (readStr.equals("recipe")) {
                recipe = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("login:ChefRankDTO", no + ',' + id + ',' + nickname + ',' + good + ',' + recipe);
        return new ChefRankDTO(no, id, nickname, good, recipe);

    }
}
