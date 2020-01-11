package com.example.han2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.han2.Adapter.SearchWeekAdapter;
import com.example.han2.Common.CommonMethod;
import com.example.han2.Dto.SearchWeekDTO;

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

public class SearchWeek extends AsyncTask<Void, Void, Void> {
    ArrayList<SearchWeekDTO> arrayList;
    SearchWeekAdapter adapter;

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    public SearchWeek(ArrayList<SearchWeekDTO> arrayList, SearchWeekAdapter adapter) {
        this.arrayList = arrayList;
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
            String postURL = CommonMethod.ipConfig + "/searchWeek";
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
                Log.d("searchweek", "readJsonStream: "+arrayList.size());
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }


    public SearchWeekDTO readMessage(JsonReader reader) throws IOException {
        String no = "", content = "", count = "", writedate = "";
        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("no")) {
                no = reader.nextString();
            } else if (readStr.equals("content")) {
                content = reader.nextString();
            } else if (readStr.equals("count")) {
                count = reader.nextString();
            } else if (readStr.equals("writedate")) {
                writedate = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new SearchWeekDTO(no, content, count, writedate);

    }
}
