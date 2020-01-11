package com.example.han2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.han2.Adapter.BobRecipeAdapter;
import com.example.han2.Common.CommonMethod;
import com.example.han2.Dto.WeekRecipeDTO;

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

public class BobRecipe extends AsyncTask<Void, Void, Void> {
    ArrayList<WeekRecipeDTO> bobArrayList;
    BobRecipeAdapter adapter;

    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse httpResponse;
    HttpEntity httpEntity;

    public BobRecipe(ArrayList<WeekRecipeDTO> bobArrayList, BobRecipeAdapter adapter) {
        this.bobArrayList = bobArrayList;
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
            String postURL = CommonMethod.ipConfig + "/BobRecipe";
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
                bobArrayList.add(readMessage(reader));

            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }


    public WeekRecipeDTO readMessage(JsonReader reader) throws IOException {
        String nickname = "", title = "", recipe_id = "", subtitle = "", portion = "", time = "",cat1 = "", cat2 = "", cat3 = "", cat4 = "", degree = "", writedate = "", pid = "", good = "", scrap = "", imagepath = "",
                image1 = "", image2 = "", image3 = "", image4 = "", image5 = "", image6 = "", image7 = "", image8= "", image9= "", image10= "", text1= "", text2= "", text3= "", text4= "", text5= "", text6= "", text7= "", text8= "", text9= "", text10= "", material_name0 = "", material_unit0 = "", material_name1 = "", material_unit1 = "", material_name2 = "", material_unit2 = "",
                material_name3 = "", material_unit3 = "", material_name4 = "", material_unit4 = "", material_name5 = "", material_unit5 = "",
                material_name6 = "", material_unit6 = "", material_name7 = "", material_unit7 = "", material_name8 = "", material_unit8 = "",
                material_name9 = "", material_unit9 = "", material_name10 = "", material_unit10 = "", material_name11 = "", material_unit11 = "",
                material_name12= "", material_unit12 = "", material_name13 = "", material_unit13 = "", material_name14 = "", material_unit14 = "",
                material_name15 = "", material_unit15 = "";
        ;
        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("nickname")) {
                nickname = reader.nextString();
            } else if (readStr.equals("title")) {
                    title = reader.nextString();
            } else if (readStr.equals("recipe_id")) {
                recipe_id = reader.nextString();
            } else if (readStr.equals("subtitle")) {
                subtitle = reader.nextString();
            } else if (readStr.equals("portion")) {
                portion = reader.nextString();
            } else if (readStr.equals("time")) {
                time = reader.nextString();
            } else if (readStr.equals("cat1")) {
                cat1 = reader.nextString();
            } else if (readStr.equals("cat2")) {
                cat2 = reader.nextString();
            } else if (readStr.equals("cat3")) {
                cat3 = reader.nextString();
            } else if (readStr.equals("cat4")) {
                cat4 = reader.nextString();
            } else if (readStr.equals("degree")) {
                degree = reader.nextString();
            } else if (readStr.equals("writedate")) {
                writedate = reader.nextString();
            } else if (readStr.equals("pid")) {
                pid = reader.nextString();
            } else if (readStr.equals("good")) {
                good = reader.nextString();
            } else if (readStr.equals("scrap")) {
                scrap = reader.nextString();
            } else if (readStr.equals("imagepath")) {
                imagepath = reader.nextString();
            } else if (readStr.equals("image1")) {
                image1 = reader.nextString();
            } else if (readStr.equals("image2")) {
                image2 = reader.nextString();
            } else if (readStr.equals("image3")) {
                image3 = reader.nextString();
            } else if (readStr.equals("image4")) {
                image4 = reader.nextString();
            } else if (readStr.equals("image5")) {
                image5 = reader.nextString();
            } else if (readStr.equals("image6")) {
                image6 = reader.nextString();
            } else if (readStr.equals("image7")) {
                image7 = reader.nextString();
            } else if (readStr.equals("image8")) {
                image8 = reader.nextString();
            } else if (readStr.equals("image9")) {
                image9 = reader.nextString();
            } else if (readStr.equals("image10")) {
                image10 = reader.nextString();
            } else if (readStr.equals("text1")) {
                text1 = reader.nextString();
            } else if (readStr.equals("text2")) {
                text2 = reader.nextString();
            } else if (readStr.equals("text3")) {
                text3 = reader.nextString();
            } else if (readStr.equals("text4")) {
                text4 = reader.nextString();
            } else if (readStr.equals("text5")) {
                text5 = reader.nextString();
            } else if (readStr.equals("text6")) {
                text6 = reader.nextString();
            } else if (readStr.equals("text7")) {
                text7 = reader.nextString();
            } else if (readStr.equals("text8")) {
                text8 = reader.nextString();
            } else if (readStr.equals("text9")) {
                text9 = reader.nextString();
            } else if (readStr.equals("text10")) {
                text10 = reader.nextString();
            } else if (readStr.equals("material_name0")) {
                material_name0 = reader.nextString();
            } else if (readStr.equals("material_unit0")) {
                material_unit0 = reader.nextString();
            } else if (readStr.equals("material_name1")) {
                material_name1 = reader.nextString();
            } else if (readStr.equals("material_unit1")) {
                material_unit1 = reader.nextString();
            } else if (readStr.equals("material_name2")) {
                material_name2 = reader.nextString();
            } else if (readStr.equals("material_unit2")) {
                material_unit2 = reader.nextString();
            } else if (readStr.equals("material_name3")) {
                material_name3 = reader.nextString();
            } else if (readStr.equals("material_unit3")) {
                material_unit3 = reader.nextString();
            } else if (readStr.equals("material_name4")) {
                material_name4 = reader.nextString();
            } else if (readStr.equals("material_unit4")) {
                material_unit4 = reader.nextString();
            } else if (readStr.equals("material_name5")) {
                material_name5 = reader.nextString();
            } else if (readStr.equals("material_unit5")) {
                material_unit5 = reader.nextString();
            } else if (readStr.equals("material_name6")) {
                material_name6 = reader.nextString();
            } else if (readStr.equals("material_unit6")) {
                material_unit6 = reader.nextString();
            } else if (readStr.equals("material_name7")) {
                material_name7 = reader.nextString();
            } else if (readStr.equals("material_unit7")) {
                material_unit7 = reader.nextString();
            } else if (readStr.equals("material_name8")) {
                material_name8 = reader.nextString();
            } else if (readStr.equals("material_unit8")) {
                material_unit8 = reader.nextString();
            } else if (readStr.equals("material_name9")) {
                material_name9 = reader.nextString();
            } else if (readStr.equals("material_unit9")) {
                material_unit9 = reader.nextString();
            } else if (readStr.equals("material_name10")) {
                material_name10 = reader.nextString();
            } else if (readStr.equals("material_unit10")) {
                material_unit10 = reader.nextString();
            } else if (readStr.equals("material_name11")) {
                material_name11 = reader.nextString();
            } else if (readStr.equals("material_unit11")) {
                material_unit11 = reader.nextString();
            } else if (readStr.equals("material_name12")) {
                material_name12 = reader.nextString();
            } else if (readStr.equals("material_unit12")) {
                material_unit12 = reader.nextString();


            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("login:WeekChefDTO", title + ',' + recipe_id + ',' + subtitle + ',' + imagepath);
        return new WeekRecipeDTO(nickname, title, recipe_id, subtitle, portion, time, cat1, cat2, cat3, cat4, degree, writedate, pid, good, scrap, imagepath,
                image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, material_name0, material_unit0, material_name1, material_unit1, material_name2, material_unit2,
                material_name3, material_unit3, material_name4, material_unit4, material_name5, material_unit5,
                material_name6, material_unit6, material_name7, material_unit7, material_name8, material_unit8,
                material_name9, material_unit9, material_name10, material_unit10, material_name11, material_unit11,
                material_name12, material_unit12);

    }
}
