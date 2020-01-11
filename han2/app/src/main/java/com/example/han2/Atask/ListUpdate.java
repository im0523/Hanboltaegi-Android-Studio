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


public class ListUpdate extends AsyncTask<Void, Void, Void> {

    String recipe_id,title, subtitle,cat1,cat2,cat3,cat4,portion,time,degree;
    String material_id, material_group;
    String material_name0, material_name1, material_name2, material_name3, material_name4, material_name5, material_name6, material_name7, material_name8, material_name9;
    String material_unit0, material_unit1, material_unit2, material_unit3, material_unit4, material_unit5, material_unit6, material_unit7, material_unit8, material_unit9;

    String text1, text2, text3 ,text4 ,text5,text6,text7,text8,text9,text10;
    String imagepath1,imagepath2,imagepath3,imagepath4;
    String image0,image1,image2,image3,image4,image5,image6,image7,image8,image9,image10;

    public ListUpdate(String recipe_id, String title, String subtitle, String cat1, String cat2, String cat3, String cat4, String portion, String time, String degree, String material_name0, String material_name1, String material_name2, String material_name3, String material_name4, String material_name5, String material_name6, String material_name7, String material_name8, String material_name9, String material_unit0, String material_unit1, String material_unit2, String material_unit3, String material_unit4, String material_unit5, String material_unit6, String material_unit7, String material_unit8, String material_unit9, String text1, String text2, String text3, String text4, String text5, String text6, String text7, String text8, String text9, String text10, String imagepath1, String imagepath2, String imagepath3, String imagepath4, String image0, String image1, String image2, String image3, String image4, String image5, String image6, String image7, String image8, String image9, String image10) {
        this.recipe_id = recipe_id;
        this.title = title;
        this.subtitle = subtitle;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.cat4 = cat4;
        this.portion = portion;
        this.time = time;
        this.degree = degree;
        this.material_name0 = material_name0;
        this.material_name1 = material_name1;
        this.material_name2 = material_name2;
        this.material_name3 = material_name3;
        this.material_name4 = material_name4;
        this.material_name5 = material_name5;
        this.material_name6 = material_name6;
        this.material_name7 = material_name7;
        this.material_name8 = material_name8;
        this.material_name9 = material_name9;
        this.material_unit0 = material_unit0;
        this.material_unit1 = material_unit1;
        this.material_unit2 = material_unit2;
        this.material_unit3 = material_unit3;
        this.material_unit4 = material_unit4;
        this.material_unit5 = material_unit5;
        this.material_unit6 = material_unit6;
        this.material_unit7 = material_unit7;
        this.material_unit8 = material_unit8;
        this.material_unit9 = material_unit9;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.text5 = text5;
        this.text6 = text6;
        this.text7 = text7;
        this.text8 = text8;
        this.text9 = text9;
        this.text10 = text10;
        this.imagepath1 = imagepath1;
        this.imagepath2 = imagepath2;
        this.imagepath3 = imagepath3;
        this.imagepath4 = imagepath4;
        this.image0 = image0;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
        this.image6 = image6;
        this.image7 = image7;
        this.image8 = image8;
        this.image9 = image9;
        this.image10 = image10;
    }

    public ListUpdate(String recipe_id, String title, String subtitle, String cat1, String cat2, String cat3, String cat4, String portion, String time, String degree, String material_name0, String material_name1, String material_name2, String material_name3, String material_name4, String material_name5, String material_name6, String material_name7, String material_name8, String material_name9, String material_unit0, String material_unit1, String material_unit2, String material_unit3, String material_unit4, String material_unit5, String material_unit6, String material_unit7, String material_unit8, String material_unit9, String text1, String text2, String text3, String text4, String text5, String text6, String text7, String text8, String text9, String text10){
        this.recipe_id=recipe_id;
        this.title = title;
        this.subtitle = subtitle;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.cat4 = cat4;
        this.portion = portion;
        this.time = time;
        this.degree = degree;
        this.material_name0 = material_name0==null ? "": material_name0;
        this.material_name1 = material_name1==null ? "":material_name1;
        this.material_name2 = material_name2==null ? "":material_name2;
        this.material_name3 = material_name3==null ? "":material_name3;
        this.material_name4 = material_name4==null ? "":material_name4;
        this.material_name5 = material_name5==null ? "":material_name5;
        this.material_name6 = material_name6==null ? "":material_name6;
        this.material_name7 = material_name7==null ? "":material_name7;
        this.material_name8 = material_name8==null ? "":material_name8;
        this.material_name9 = material_name9==null ? "":material_name9;
        this.material_unit0 = material_unit0==null ? "":material_unit0;
        this.material_unit1 = material_unit1==null ? "":material_unit1;
        this.material_unit2 = material_unit2==null ? "":material_unit2;
        this.material_unit3 = material_unit3==null ? "":material_unit3;
        this.material_unit4 = material_unit4==null ? "":material_unit4;
        this.material_unit5 = material_unit5==null ? "":material_unit5;
        this.material_unit6 = material_unit6==null ? "":material_unit6;
        this.material_unit7 = material_unit7==null ? "":material_unit7;
        this.material_unit8 = material_unit8==null ? "":material_unit8;
        this.material_unit9 = material_unit9==null ? "":material_unit9;
        this.text1 = text1==null ? "" : text1;
        this.text2 = text2==null ? "" : text2;
        this.text3 = text3==null ? "" : text3;
        this.text4 = text4==null ? "" : text4;
        this.text5 = text5==null ? "" : text5;
        this.text6 = text6==null ? "" : text6;
        this.text7 = text7==null ? "" : text7;
        this.text8 = text8==null ? "" : text8;
        this.text9 = text9==null ? "" : text9;
        this.text10 = text10==null ? "" : text10;

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
            builder.addTextBody("recipe_id", recipe_id, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("title", title, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("subtitle", subtitle, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("cat1", cat1, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("cat2", cat2, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("cat3", cat3, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("cat4", cat4, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("portion", portion, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("time", time, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("degree", degree, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_name0", material_name0, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_unit0 ", material_unit0 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_name1", material_name1, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_unit1 ", material_unit1 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_name2", material_name2, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_unit2 ", material_unit2 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_name3", material_name3, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_unit3 ", material_unit3 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_name4", material_name4, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_unit4 ", material_unit4 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_name5", material_name5, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_unit5 ", material_unit5 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_name6", material_name6, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_unit6 ", material_unit6 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_name7", material_name7, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_unit7 ", material_unit7 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_name8", material_name8, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_unit8 ", material_unit8 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_name9", material_name9, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("material_unit9 ", material_unit9 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("text1 ", text1 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("text2 ", text2 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("text3 ", text3 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("text4 ", text4 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("text5 ", text5 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("text6 ", text6 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("text7 ", text7 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("text8 ", text8 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("text9 ", text9 , ContentType.create("Multipart/related", "UTF-8"));
            //builder.addTextBody("text10 ", text10 , ContentType.create("Multipart/related", "UTF-8"));
/*            builder.addTextBody("imagepath1", imagepath1, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("imagepath2 ", imagepath2, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("imagepath3 ", imagepath3 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("imagepath4 ", imagepath4 , ContentType.create("Multipart/related", "UTF-8")); */
            builder.addTextBody("imagepath ", image0, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("image1 ", image1, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("image2 ", image2, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("image3 ", image3, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("image4 ", image4, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("image5 ", image5, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("image6 ", image6, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("image7 ", image7, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("image8 ", image8, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("image9 ", image9, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("image10 ", image10, ContentType.create("Multipart/related", "UTF-8"));

            Log.d("Sub1Update11", title);
            Log.d("Sub1Update12", subtitle);
            Log.d("수정했니1", recipe_id);

            postURL = ipConfig + "/recUpdate";
            // 전송
            //InputStream inputStream = null;
            HttpClient httpClient = AndroidHttpClient.newInstance("Android");
            HttpPost httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }


}
