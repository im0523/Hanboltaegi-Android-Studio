package com.example.han2.Atask;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.nio.charset.Charset;

import static com.example.han2.Common.CommonMethod.ipConfig;

public class MaterialInsert extends AsyncTask<Void, Void, Void> {

    String material_id, material_group,recipe_id;
    String material_name0, material_name1, material_name2, material_name3, material_name4, material_name5, material_name6, material_name7, material_name8, material_name9;
    String material_unit0, material_unit1, material_unit2, material_unit3, material_unit4, material_unit5, material_unit6, material_unit7, material_unit8, material_unit9;

    public String getMaterial_id() {
        return material_id;
    }
    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }
    public String getMaterial_group() {
        return material_group;
    }
    public void setMaterial_group(String material_group) {
        this.material_group = material_group;
    }
    public String getRecipe_id() {
        return recipe_id;
    }
    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }
    public String getMaterial_name0() {
        return material_name0;
    }
    public void setMaterial_name0(String material_name0) {
        this.material_name0 = material_name0;
    }
    public String getMaterial_name1() {
        return material_name1;
    }
    public void setMaterial_name1(String material_name1) {
        this.material_name1 = material_name1;
    }
    public String getMaterial_name2() {
        return material_name2;
    }
    public void setMaterial_name2(String material_name2) {
        this.material_name2 = material_name2;
    }
    public String getMaterial_name3() {
        return material_name3;
    }
    public void setMaterial_name3(String material_name3) {
        this.material_name3 = material_name3;
    }
    public String getMaterial_name4() {
        return material_name4;
    }
    public void setMaterial_name4(String material_name4) {
        this.material_name4 = material_name4;
    }
    public String getMaterial_name5() {
        return material_name5;
    }
    public void setMaterial_name5(String material_name5) {
        this.material_name5 = material_name5;
    }
    public String getMaterial_name6() {
        return material_name6;
    }
    public void setMaterial_name6(String material_name6) {
        this.material_name6 = material_name6;
    }
    public String getMaterial_name7() {
        return material_name7;
    }
    public void setMaterial_name7(String material_name7) {
        this.material_name7 = material_name7;
    }
    public String getMaterial_name8() {
        return material_name8;
    }
    public void setMaterial_name8(String material_name8) {
        this.material_name8 = material_name8;
    }
    public String getMaterial_name9() {
        return material_name9;
    }
    public void setMaterial_name9(String material_name9) {
        this.material_name9 = material_name9;
    }
    public String getMaterial_unit0() {
        return material_unit0;
    }
    public void setMaterial_unit0(String material_unit0) {
        this.material_unit0 = material_unit0;
    }
    public String getMaterial_unit1() {
        return material_unit1;
    }
    public void setMaterial_unit1(String material_unit1) {
        this.material_unit1 = material_unit1;
    }
    public String getMaterial_unit2() {
        return material_unit2;
    }
    public void setMaterial_unit2(String material_unit2) {
        this.material_unit2 = material_unit2;
    }
    public String getMaterial_unit3() {
        return material_unit3;
    }
    public void setMaterial_unit3(String material_unit3) {
        this.material_unit3 = material_unit3;
    }
    public String getMaterial_unit4() {
        return material_unit4;
    }
    public void setMaterial_unit4(String material_unit4) {
        this.material_unit4 = material_unit4;
    }
    public String getMaterial_unit5() {
        return material_unit5;
    }
    public void setMaterial_unit5(String material_unit5) {
        this.material_unit5 = material_unit5;
    }
    public String getMaterial_unit6() {
        return material_unit6;
    }
    public void setMaterial_unit6(String material_unit6) {
        this.material_unit6 = material_unit6;
    }
    public String getMaterial_unit7() {
        return material_unit7;
    }
    public void setMaterial_unit7(String material_unit7) {
        this.material_unit7 = material_unit7;
    }
    public String getMaterial_unit8() {
        return material_unit8;
    }
    public void setMaterial_unit8(String material_unit8) {
        this.material_unit8 = material_unit8;
    }
    public String getMaterial_unit9() {
        return material_unit9;
    }
    public void setMaterial_unit9(String material_unit9) {
        this.material_unit9 = material_unit9;
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
            builder.setCharset(Charset.forName("UTF-8"));

            // 문자열 및 데이터 추가







            String postURL = ipConfig + "/MaterialInsert";

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
        Log.d("Insert", "추가성공");

    }

}
