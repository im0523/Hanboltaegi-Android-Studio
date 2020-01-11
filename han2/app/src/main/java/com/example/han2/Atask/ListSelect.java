package com.example.han2.Atask;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.example.han2.Adapter.MyRecyclerviewAdapter;
import com.example.han2.Dto.RecipeDTO;

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

// doInBackground 파라미터 타입, onProgressUpdate파라미터 타입, onPostExecute 파라미터 타입 순서
// AsyncTask <Params, Progress, Result> 순서임
public class ListSelect extends AsyncTask<Void, Void, Void> {
    // 생성자
    ArrayList<RecipeDTO> recipeDTOArrayList;
    MyRecyclerviewAdapter adapter;
    ProgressDialog progressDialog;

    public ListSelect(ArrayList<RecipeDTO> recipeDTOArrayList, MyRecyclerviewAdapter adapter, ProgressDialog progressDialog) {
        this.recipeDTOArrayList = recipeDTOArrayList;
        this.adapter = adapter;
        this.progressDialog = progressDialog;
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
        recipeDTOArrayList.clear();
        String result = "";
        String postURL = ipConfig + "/recSelect";

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

        } catch (Exception e) {
            Log.d("Sub1", e.getMessage());
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

        if(progressDialog != null){
            progressDialog.dismiss();
        }

        Log.d("Sub1", "List Select Complete!!!");

        adapter.notifyDataSetChanged();
    }

    public void readJsonStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            reader.beginArray();
            while (reader.hasNext()) {
                recipeDTOArrayList.add(readMessage(reader));
            }
            reader.endArray();
        } finally {
            reader.close();
        }
    }

    public RecipeDTO readMessage(JsonReader reader) throws IOException {
        String recipe_id = "", title = "", subtitle = "", cat1="",cat2="",cat3="",cat4="",portion="",
                time="",degree="",writedate="";
        String material_id="",material_group="",material_name0="",material_name1="",material_name2="",material_name3="";
        String material_name4="",material_name5="",material_name6="",material_name7="",material_name8="",material_name9="",material_unit0="";
        String material_unit1="",material_unit2="",material_unit3="",material_unit4="",material_unit5="",material_unit6="",material_unit7="",material_unit8="",material_unit9="";
        String text1="",text2="",text3="",text4="",text5="",text6="",text7="",text8="",text9="",text10="";
        String imagepath ="", imagepath1="",imagepath2="",imagepath3="",imagepath4="",image1="",image2="",image3="",image4="",image5="",image6="",image7="",image8="",image9="",image10="";
        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("recipe_id")) {
                recipe_id = reader.nextString();
            }else if (readStr.equals("imagepath")) {
                imagepath = reader.nextString();
            }else if (readStr.equals("imagepath1")) {
                imagepath1 = reader.nextString();
            }else if (readStr.equals("imagepath2")) {
                imagepath2 = reader.nextString();
            }else if (readStr.equals("imagepath3")) {
                imagepath3 = reader.nextString();
            }else if (readStr.equals("imagepath4")) {
                imagepath4 = reader.nextString();
            }else if (readStr.equals("title")) {
                title = reader.nextString();
            } else if (readStr.equals("subtitle")) {
                subtitle = reader.nextString();
            } else if (readStr.equals("cat1")) {
                cat1 = reader.nextString();
            }else if (readStr.equals("cat2")) {
                cat2 = reader.nextString();
            }else if (readStr.equals("cat3")) {
                cat3 = reader.nextString();
            }else if (readStr.equals("cat4")) {
                cat4 = reader.nextString();
            }else if (readStr.equals("portion")) {
                portion = reader.nextString();
            }else if (readStr.equals("time")) {
                time = reader.nextString();
            }else if (readStr.equals("degree")) {
                degree = reader.nextString();
            }else if (readStr.equals("material_id")) {
                material_id = reader.nextString();
            }else if (readStr.equals("material_group")) {
                material_group = reader.nextString();
            }else if (readStr.equals("material_name0")) {
                material_name0 = reader.nextString();
            }else if (readStr.equals("material_name1")) {
                material_name1 = reader.nextString();
            }else if (readStr.equals("material_name2")) {
                material_name2 = reader.nextString();
            }else if (readStr.equals("material_name3")) {
                material_name3 = reader.nextString();
            }else if (readStr.equals("material_name4")) {
                material_name4 = reader.nextString();
            }else if (readStr.equals("material_name5")) {
                material_name5 = reader.nextString();
            }else if (readStr.equals("material_name6")) {
                material_name6 = reader.nextString();
            }else if (readStr.equals("material_name7")) {
                material_name7 = reader.nextString();
            }else if (readStr.equals("material_name8")) {
                material_name8 = reader.nextString();
            }else if (readStr.equals("material_name9")) {
                material_name9 = reader.nextString();
            }else if (readStr.equals("material_unit0")) {
                material_unit0 = reader.nextString();
            }else if (readStr.equals("material_unit1")) {
                material_unit1 = reader.nextString();
            }else if (readStr.equals("material_unit2")) {
                material_unit2 = reader.nextString();
            }else if (readStr.equals("material_unit3")) {
                material_unit3 = reader.nextString();
            }else if (readStr.equals("material_unit4")) {
                material_unit4 = reader.nextString();
            }else if (readStr.equals("material_unit5")) {
                material_unit5 = reader.nextString();
            }else if (readStr.equals("material_unit6")) {
                material_unit6 = reader.nextString();
            }else if (readStr.equals("material_unit7")) {
                material_unit7 = reader.nextString();
            }else if (readStr.equals("material_unit8")) {
                material_unit8 = reader.nextString();
            }else if (readStr.equals("material_unit9")) {
                material_unit9 = reader.nextString();
            }else if (readStr.equals("text1")) {
                text1 = reader.nextString();
            }else if (readStr.equals("text2")) {
                text2 = reader.nextString();
            }else if (readStr.equals("text3")) {
                text3 = reader.nextString();
            }else if (readStr.equals("text4")) {
                text4 = reader.nextString();
            }else if (readStr.equals("text5")) {
                text5 = reader.nextString();
            }else if (readStr.equals("text6")) {
                text6 = reader.nextString();
            }else if (readStr.equals("text7")) {
                text7 = reader.nextString();
            }else if (readStr.equals("text8")) {
                text8 = reader.nextString();
            }else if (readStr.equals("text9")) {
                text9 = reader.nextString();
            }else if (readStr.equals("text10")) {
                text10 = reader.nextString();
            }else if (readStr.equals("image1")) {
                image1 = reader.nextString();
            }else if (readStr.equals("image2")) {
                image2 = reader.nextString();
            }else if (readStr.equals("image3")) {
                image3 = reader.nextString();
            }else if (readStr.equals("image4")) {
                image4 = reader.nextString();
            }else if (readStr.equals("image5")) {
                image5 = reader.nextString();
            }else if (readStr.equals("image6")) {
                image6 = reader.nextString();
            }else if (readStr.equals("image7")) {
                image7 = reader.nextString();
            }else if (readStr.equals("image8")) {
                image8 = reader.nextString();
            }else if (readStr.equals("image9")) {
                image9 = reader.nextString();
            }else if (readStr.equals("image10")) {
                image10 = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("listselect:myitem", "imagepath: "+ imagepath + ", "+ recipe_id + "," + title + "," + subtitle + ", " + cat1 + cat2 + cat3 + cat4 +  ", " +portion + time + degree + ", " + writedate +
                 "\n material_name : " + material_name0  + ", material_unit0 :" + material_unit0);
        return new RecipeDTO(recipe_id, title, subtitle,cat1,cat2,cat3,cat4,portion,time,degree,
                imagepath,imagepath1,imagepath2,imagepath3,imagepath4,
                writedate,material_id, material_group,
                text1, text2, text3 ,text4 ,text5,text6,text7,text8,text9,text10,
                material_name0, material_name1, material_name2, material_name3, material_name4, material_name5, material_name6, material_name7, material_name8, material_name9,
                material_unit0, material_unit1, material_unit2, material_unit3, material_unit4, material_unit5, material_unit6, material_unit7, material_unit8, material_unit9,
                image1,image2,image3,image4,image5,image6,image7,image8,image9,image10);

    }


}
