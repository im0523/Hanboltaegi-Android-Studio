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
import org.apache.http.entity.mime.content.FileBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import static com.example.han2.Common.CommonMethod.ipConfig;

public class ListInsert extends AsyncTask<Void, Void, Void> {

    String title, subtitle,cat1,cat2,cat3,cat4,portion,time,degree,imagepath,imagepath1,imagepath2,imagepath3,imagepath4, imageRealPathA;
    String material_id, material_group,recipe_id;
    String imageRealPathM1,imageRealPathM2,imageRealPathM3,imageRealPathM4;
    String material_name0, material_name1, material_name2, material_name3, material_name4, material_name5, material_name6, material_name7, material_name8, material_name9;
    String material_unit0, material_unit1, material_unit2, material_unit3, material_unit4, material_unit5, material_unit6, material_unit7, material_unit8, material_unit9;
    String text1, text2, text3 ,text4 ,text5,text6,text7,text8,text9,text10;
    String imageRealPathA0,imagepathA0,imageRealPathA1,imagepathA1,imageRealPathA2,imagepathA2,imageRealPathA3,imagepathA3,imageRealPathA4,imagepathA4,imageRealPathA5,imagepathA5,imageRealPathA6,imagepathA6,imageRealPathA7,imagepathA7,imageRealPathA8,imagepathA8,imageRealPathA9,imagepathA9;
    String userid;

    //public ListInsert(String title, String subtitle, String cat1, String cat2, String cat3, String cat4, String portion, String time, String degree, String imagepath, String imagepath1, String imagepath2, String imagepath3, String imagepath4, String imageRealPathA, String material_id, String material_group, String recipe_id, String imageRealPathM1, String imageRealPathM2, String imageRealPathM3, String imageRealPathM4, String material_name0, String material_name1, String material_name2, String material_name3, String material_name4, String material_name5, String material_name6, String material_name7, String material_name8, String material_name9, String material_unit0, String material_unit1, String material_unit2, String material_unit3, String material_unit4, String material_unit5, String material_unit6, String material_unit7, String material_unit8, String material_unit9, String text1, String text2, String text3, String text4, String text5, String text6, String text7, String text8, String text9, String text10, String imageRealPathA0, String imagepathA0, String imageRealPathA1, String imagepathA1, String imageRealPathA2, String imagepathA2, String imageRealPathA3, String imagepathA3, String imageRealPathA4, String imagepathA4, String imageRealPathA5, String imagepathA5, String imageRealPathA6, String imagepathA6, String imageRealPathA7, String imagepathA7, String imageRealPathA8, String imagepathA8, String imageRealPathA9, String imagepathA9) {
    public ListInsert(String title, String subtitle, String cat1, String cat2, String cat3, String cat4, String portion, String time, String degree, String imagepath, String imagepath1, String imagepath2, String imagepath3, String imagepath4, String imageRealPathA, String imageRealPathM1, String imageRealPathM2, String imageRealPathM3, String imageRealPathM4, String material_name0, String material_name1, String material_name2, String material_name3, String material_name4, String material_name5, String material_name6, String material_name7, String material_name8, String material_name9, String material_unit0, String material_unit1, String material_unit2, String material_unit3, String material_unit4, String material_unit5, String material_unit6, String material_unit7, String material_unit8, String material_unit9, String text1, String text2, String text3, String text4, String text5, String text6, String text7, String text8, String text9, String text10, String imageRealPathA0, String imagepathA0, String imageRealPathA1, String imagepathA1, String imageRealPathA2, String imagepathA2, String imageRealPathA3, String imagepathA3, String imageRealPathA4, String imagepathA4, String imageRealPathA5, String imagepathA5, String imageRealPathA6, String imagepathA6, String imageRealPathA7, String imagepathA7, String imageRealPathA8, String imagepathA8, String imageRealPathA9, String imagepathA9,String userid) {
        this.title = title;
        this.subtitle = subtitle;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.cat4 = cat4;
        this.portion = portion;
        this.time = time;
        this.degree = degree;
        this.imagepath = imagepath == null ? "" : imagepath;
        this.imagepath1 = imagepath1 == null ? "" : imagepath1;
        this.imagepath2 = imagepath2 == null ? "" : imagepath2;
        this.imagepath3 = imagepath3 == null ? "" : imagepath3;
        this.imagepath4 = imagepath4 == null ? "" : imagepath4;
        this.imageRealPathA = imageRealPathA == null ? "" : imageRealPathA;
        this.imageRealPathM1 = imageRealPathM1 == null ? "" : imageRealPathM1;
        this.imageRealPathM2 = imageRealPathM2 == null ? "" : imageRealPathM2;
        this.imageRealPathM3 = imageRealPathM3 == null ? "" : imageRealPathM3;
        this.imageRealPathM4 = imageRealPathM4 == null ? "" : imageRealPathM4;
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
        this.imageRealPathA0 = imageRealPathA0 == null ? "" : imageRealPathA0;
        this.imagepathA0 = imagepathA0 == null ? "" : imagepathA0;
        this.imageRealPathA1 = imageRealPathA1 == null ? "" : imageRealPathA1;
        this.imagepathA1 = imagepathA1 == null ? "" : imagepathA1;
        this.imageRealPathA2 = imageRealPathA2 == null ? "" : imageRealPathA2;
        this.imagepathA2 = imagepathA2 == null ? "" : imagepathA2;
        this.imageRealPathA3 = imageRealPathA3== null ? "" : imageRealPathA3;
        this.imagepathA3 = imagepathA3== null ? "" : imagepathA3;
        this.imageRealPathA4 = imageRealPathA4== null ? "" : imageRealPathA4;
        this.imagepathA4 = imagepathA4== null ? "" : imagepathA4;
        this.imageRealPathA5 = imageRealPathA5== null ? "" : imageRealPathA5;
        this.imagepathA5 = imagepathA5== null ? "" : imagepathA5;
        this.imageRealPathA6 = imageRealPathA6== null ? "" : imageRealPathA6;
        this.imagepathA6 = imagepathA6== null ? "" : imagepathA6;
        this.imageRealPathA7 = imageRealPathA7== null ? "" : imageRealPathA7;
        this.imagepathA7 = imagepathA7== null ? "" : imagepathA7;
        this.imageRealPathA8 = imageRealPathA8== null ? "" : imageRealPathA8;
        this.imagepathA8 = imagepathA8== null ? "" : imagepathA8;
        this.imageRealPathA9 = imageRealPathA9== null ? "" : imageRealPathA9;
        this.imagepathA9 = imagepathA9== null ? "" : imagepathA9;
        this.userid=userid;
    }


    /*
        public ListInsert(String title, String subtitle, String cat1, String cat2, String cat3, String cat4, String portion, String time, String degree,
                          String imagepath, String imagepath1, String imagepath2, String imagepath3, String imagepath4,
                          String imageRealPathA, String imageRealPathM1, String imageRealPathM2, String imageRealPathM3, String imageRealPathM4,
                          String material_name0, String material_name1, String material_name2, String material_name3, String material_name4, String material_name5, String material_name6, String material_name7, String material_name8, String material_name9, String material_unit0, String material_unit1, String material_unit2, String material_unit3, String material_unit4, String material_unit5, String material_unit6, String material_unit7, String material_unit8, String material_unit9, String text1, String text2, String text3, String text4, String text5, String text6, String text7, String text8, String text9, String text10) {
            this.title = title;
            this.subtitle = subtitle;
            this.cat1 = cat1;
            this.cat2 = cat2;
            this.cat3 = cat3;
            this.cat4 = cat4;
            this.portion = portion;
            this.time = time;
            this.degree = degree;
            this.imagepath = imagepath == null ? "" : imagepath;
            this.imagepath1 = imagepath1 == null ? "" : imagepath1;
            this.imagepath2 = imagepath2 == null ? "" : imagepath2;
            this.imagepath3 = imagepath3 == null ? "" : imagepath3;
            this.imagepath4 = imagepath4 == null ? "" : imagepath4;
            this.imageRealPathA = imageRealPathA == null ? "" : imageRealPathA;
            this.imageRealPathM1 = imageRealPathM1 == null ? "" : imageRealPathM1;
            this.imageRealPathM2 = imageRealPathM2 == null ? "" : imageRealPathM2;
            this.imageRealPathM3 = imageRealPathM3 == null ? "" : imageRealPathM3;
            this.imageRealPathM4 = imageRealPathM4 == null ? "" : imageRealPathM4;
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
    */
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
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가
            int cntt = 0;

            Log.d("insert", "InsertClass" + title + subtitle + cat1 + cat2 + cat3 + cat4);
            Log.d("insert", "m_id : " + material_id + ", m_group : " +  material_group
                    + ", recipe_id : " +recipe_id
                    + ", m_name 0 : " + material_name0 + ", m_unit 0 : " + material_unit0
                    + ", m_name 1 : " + material_name1 + ", m_unit 1 : " + material_unit1
                    + ", m_name 2 : " + material_name2 + ", m_unit 2 : " + material_unit2
                    + ", m_name 3 : " + material_name3 + ", m_unit 3 : " + material_unit3
                    + ", m_name 4 : " + material_name4 + ", m_unit 4 : " + material_unit4
                    + ", m_name 5 : " + material_name5 + ", m_unit 5 : " + material_unit5
                    + ", m_name 6 : " + material_name6 + ", m_unit 6 : " + material_unit6
                    + ", m_name 7 : " + material_name7 + ", m_unit 7 : " + material_unit7
                    + ", m_name 8 : " + material_name8 + ", m_unit 8 : " + material_unit8
                    + ", m_name 9 : " + material_name9 + ", m_unit 9 : " + material_unit9
            );

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
            builder.addTextBody("text10 ", text10 , ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("userid ", userid , ContentType.create("Multipart/related", "UTF-8"));

            int pntt = 0;
            if ( !imagepathA0.equals("") ){
                builder.addTextBody("imagepathA0 ", imagepathA0, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("imageA0 ", new FileBody(new File(imageRealPathA0)));
                pntt++;
            }
            if( !imagepathA1.equals("") ) {
                builder.addTextBody("imagepathA1 ", imagepathA1, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("imageA1 ", new FileBody(new File(imageRealPathA1)));
                pntt++;
            }
            if( !imagepathA2.equals("") ) {
                builder.addTextBody("imagepathA2 ", imagepathA2, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("imageA2 ", new FileBody(new File(imageRealPathA2)));
                pntt++;
            }
            if( !imagepathA3.equals("") ) {
                builder.addTextBody("imagepathA3 ", imagepathA3, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("imageA3 ", new FileBody(new File(imageRealPathA3)));
                pntt++;
            }
            if( !imagepathA4.equals("") ) {
                builder.addTextBody("imagepathA4 ", imagepathA4, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("imageA4 ", new FileBody(new File(imageRealPathA4)));
                pntt++;
            }
            if( !imagepathA5.equals("") ) {
                builder.addTextBody("imagepathA5 ", imagepathA5, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("imageA5 ", new FileBody(new File(imageRealPathA5)));
                pntt++;
            }
            if( !imagepathA6.equals("") ) {
                builder.addTextBody("imagepathA6 ", imagepathA6, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("imageA6 ", new FileBody(new File(imageRealPathA6)));
                pntt++;
            }
            if( !imagepathA7.equals("") ) {
                builder.addTextBody("imagepathA7 ", imagepathA7, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("imageA7 ", new FileBody(new File(imageRealPathA7)));
                pntt++;
            }
            if( !imagepathA8.equals("") ) {
                builder.addTextBody("imagepathA8 ", imagepathA8, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("imageA8 ", new FileBody(new File(imageRealPathA8)));
                pntt++;
            }
            if( !imagepathA9.equals("") ) {
                builder.addTextBody("imagepathA9 ", imagepathA9, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("imageA9 ", new FileBody(new File(imageRealPathA9)));
                pntt++;
            }

            Log.d("insertLTE", "doInBackground: " + imagepathA0 + imagepathA1 + imagepathA2 + imagepathA3);


            if( !imagepath.equals("") ) {
                builder.addTextBody("imagepath ", imagepath, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("image ", new FileBody(new File(imageRealPathA)));
                cntt++;
            }
            if( !imagepath1.equals("") ) {
                builder.addPart("image1 ", new FileBody(new File(imageRealPathM1)));
                cntt++;
            }
            if( !imagepath2.equals("") ) {
                builder.addTextBody("imagepath2 ", imagepath2, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("image2 ", new FileBody(new File(imageRealPathM2)));
                cntt++;
            }
            if( !imagepath3.equals("") ) {
                builder.addTextBody("imagepath3 ", imagepath3, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("image3 ", new FileBody(new File(imageRealPathM3)));
                cntt++;
            }
            if( !imagepath4.equals("") ) {
                builder.addTextBody("imagepath4 ", imagepath4, ContentType.create("Multipart/related", "UTF-8"));
                builder.addPart("image4 ", new FileBody(new File(imageRealPathM4)));
                cntt++;
            }
            String cnt = "" + cntt;
            String pnt = "" + pntt;
            builder.addTextBody("cnt",cnt, ContentType.create("Multipart/related", "UTF-8"));
            builder.addTextBody("pnt",pnt, ContentType.create("Multipart/related", "UTF-8"));
            Log.d("imageppp", "doInBackground: " + cnt + ", " +imagepath + ", " + imageRealPathA + imagepath1 + ", " + imageRealPathM1 + ", " +imagepath2 + ", " + imageRealPathM2  + imagepath3 + ", " + imageRealPathM3 + imagepath4 + ", " + imageRealPathM4);

            String postURL = ipConfig + "/recInsert";

            // 전송
            //InputStream inputStream = null;
            HttpClient httpClient = AndroidHttpClient.newInstance("Android");
            HttpPost httpPost = new HttpPost(postURL);
            httpPost.setEntity(builder.build());
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            // 응답
            InputStream inputStream = null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }
            inputStream.close();

            // 응답결과
            String result = stringBuilder.toString();
            Log.d("response", result);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("Insert", "추가성공");

    }

}
