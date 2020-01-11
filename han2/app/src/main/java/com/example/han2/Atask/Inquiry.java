package com.example.han2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.han2.Adapter.InquiryAdapter;
import com.example.han2.Dto.FragDTO;

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

import static com.example.han2.Common.CommonMethod.ipConfig;

public class Inquiry extends AsyncTask<Void, Void, Void> {
    private  static  final String TAG = "anInquiry";

    //생성자
    ArrayList<FragDTO> InquiryArrayList;
    InquiryAdapter adapter;

    public Inquiry(ArrayList<FragDTO> InquiryArrayList, InquiryAdapter adapter) {
        this.InquiryArrayList = InquiryArrayList;
        this.adapter = adapter;
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
        InquiryArrayList.clear();
        String result = "";
        String postURL = ipConfig + "/anInquiry";
        try {
            // MultipartEntityBuild 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            // 전송
            InputStream inputStream = null;
            httpClient = AndroidHttpClient.newInstance("Android");
            httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();

            readJsonStream(inputStream);

            /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            String jsonStr = stringBuilder.toString();

            inputStream.close();*/

        } catch (Exception e) {
            Log.d("Frag6", e.getMessage());
            e.printStackTrace();
        }finally {
            if(httpEntity != null){
                httpEntity = null;
            }
            if(httpResponse != null){
                httpResponse = null;
            }
            if(httpPost != null){
                httpPost = null;
            }
            if(httpClient != null){
                httpClient = null;
            }

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
                InquiryArrayList.add(readMessage(reader));
            }
            Log.d(TAG, "InquiryArrayList: " + InquiryArrayList.size());
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public FragDTO readMessage(JsonReader reader) throws IOException {
        String title = "", nickname = "", content ="";
        String id = "", writedate = "", no="", num="";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("title")) {
                title = reader.nextString();
            } else if (readStr.equals("nickname")) {
                nickname = reader.nextString();
            } else if (readStr.equals("content")) {
                content = reader.nextString();
            } else if (readStr.equals("writedate")) {
                writedate = reader.nextString();
            } else if (readStr.equals("no")) {
                no = reader.nextString();
            } else if (readStr.equals("num")) {
                num = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("login:fragdto",  title + "," + nickname + writedate+ "," + content+ "," + id + ", " +no + "번 ");
        return new FragDTO(no, num, title, nickname, writedate, content, id);

    }

}


