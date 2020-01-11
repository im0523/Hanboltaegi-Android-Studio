package com.example.han2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.han2.Adapter.CommunityAdapter;
import com.example.han2.Dto.CommunityDTO;

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

public class Comment_list extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "Comment";

    //생성자
    ArrayList<CommunityDTO> CommentArrayList;
    CommunityAdapter adapter;

    public Comment_list(ArrayList<CommunityDTO> commentArrayList, CommunityAdapter adapter) {
        this.CommentArrayList = commentArrayList;
        this.adapter = adapter;
    }

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    public Comment_list(ArrayList<CommunityDTO> communityArrayList) {

    }

    @Override
    protected void onPreExecute() { super.onPreExecute(); }

    @Override
    protected Void doInBackground(Void... voids) {
        CommentArrayList.clear();
        String result = "";
        String postURL = ipConfig + "/Comment";
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
            Log.d("Comment", e.getMessage());
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
                CommentArrayList.add(readMessage(reader));
            }
            Log.d(TAG, "CommentArrayList: " + CommentArrayList.size());
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public CommunityDTO readMessage(JsonReader reader) throws IOException {
        String no = "", content ="", nickname = "", writedate ="";
        String co_file1 = "", co_filename1 = "", userid = "";


        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("no")) {
                no = reader.nextString();
            } else if (readStr.equals("content")) {
                content = reader.nextString();
            }else if (readStr.equals("nickname")) {
                nickname = reader.nextString();
            }else if (readStr.equals("writedate")) {
                writedate = reader.nextString();
            }else if (readStr.equals("userid")) {
                userid = reader.nextString();
            }else {
                reader.skipValue();
            }
        }

        Log.d("selectAAComment_list", writedate + ", " + nickname + ", " + no);
        reader.endObject();
        Log.d("login:communitydto", nickname + "," + content + "," + writedate + ", " + no);

        return new CommunityDTO(no, content, nickname, writedate, co_file1, co_filename1, userid);

    }




}







































