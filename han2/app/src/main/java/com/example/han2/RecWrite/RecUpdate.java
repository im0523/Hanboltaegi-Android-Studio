package com.example.han2.RecWrite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.han2.Atask.ListUpdate;
import com.example.han2.Dto.MyRecipeDTO;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecUpdate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView rec_save;
    EditText et_title, et_subtitle;

    View rec_update_0,rec_update_1,rec_update_2,rec_update_3,rec_update_4,stuff_update_0,stuff_update_1,stuff_update_2,stuff_update_3,stuff_update_4;
    View frame_0,frame_1,frame_2,frame_3,frame_4,frame_5,frame_6,frame_7,frame_8,frame_9;

    EditText spic_et_name0,spic_et_name1,spic_et_name2,spic_et_name3,spic_et_name4;
    EditText spic_et_unit0,spic_et_unit1,spic_et_unit2,spic_et_unit3,spic_et_unit4;

    EditText stuff_et_name0,stuff_et_name1,stuff_et_name2,stuff_et_name3,stuff_et_name4;
    EditText stuff_et_unit0,stuff_et_unit1,stuff_et_unit2,stuff_et_unit3,stuff_et_unit4;

    TextView pro_tv0,pro_tv1,pro_tv2,pro_tv3,pro_tv4,pro_tv5,pro_tv6,pro_tv7,pro_tv8,pro_tv9;
    EditText pro_et0,pro_et1,pro_et2,pro_et3,pro_et4,pro_et5,pro_et6,pro_et7,pro_et8,pro_et9;
    ImageView pro_iv0,pro_iv1,pro_iv2,pro_iv3,pro_iv4,pro_iv5,pro_iv6,pro_iv7,pro_iv8,pro_iv9;
    Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmap5,bitmap6,bitmap7,bitmap8,bitmap9,bitmap10;

    String rec_title_up, rec_subtitle_up ,rec_id,cat1_c,cat2_c,cat3_c,cat4_c;
    String material_name0, material_name1, material_name2, material_name3, material_name4, material_name5, material_name6, material_name7, material_name8, material_name9;
    String material_unit0, material_unit1, material_unit2, material_unit3, material_unit4, material_unit5, material_unit6, material_unit7, material_unit8, material_unit9;
    String text1, text2, text3 ,text4 ,text5,text6,text7,text8,text9,text10;

    //대표이미지
    Bitmap bitmap0;
    ImageView rec_image;

    TextView stuff_tv_name0,material_tv_name0;

    //요리정보
    TextView cok_portion_tv_up, cok_time_tv_up, cok_degree_tv_up;
    String portion,time,degree;
    TextView rec_update;

    private ProgressDialog progressDialog;

    //이미지 실제주소 가져오기
    public static String image0,image1,image2,image3,image4,image5,image6,image7,image8,image9,image10;

    //완성사진
    public static String imagepath1,imagepath2,imagepath3,imagepath4;
    Bitmap bitpath1,bitpath2,bitpath3,bitpath4;
    ImageView pathimage1,pathimage2,pathimage3,pathimage4;
    String id;




    //카테고리
    EditText cat1_up,cat2_up,cat3_up,cat4_up;
    ArrayAdapter<CharSequence> adapter_cat1, adapter_cat2, adapter_cat3, adapter_cat4;
    Spinner cat1,cat2,cat3,cat4;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        //레시피 수정
        et_title = findViewById(R.id.rec_title_up);
        et_subtitle = findViewById(R.id.rec_subtitle_up);

        /*
        cat1_up = findViewById(R.id.cat1_up);
        cat2_up = findViewById(R.id.cat2_up);
        cat3_up = findViewById(R.id.cat3_up);
        cat4_up = findViewById(R.id.cat4_up);
        */


        //재료
        spic_et_name0 = findViewById(R.id.spic_et_name0);
        spic_et_name1 = findViewById(R.id.spic_et_name1);
        spic_et_name2 = findViewById(R.id.spic_et_name2);
        spic_et_name3 = findViewById(R.id.spic_et_name3);
        spic_et_name4 = findViewById(R.id.spic_et_name4);
        spic_et_unit0 = findViewById(R.id.spic_et_unit0);
        spic_et_unit1 = findViewById(R.id.spic_et_unit1);
        spic_et_unit2 = findViewById(R.id.spic_et_unit2);
        spic_et_unit3 = findViewById(R.id.spic_et_unit3);
        spic_et_unit4 = findViewById(R.id.spic_et_unit4);

        //양념
        stuff_et_name0 = findViewById(R.id.stuff_et_name0);
        stuff_et_name1 = findViewById(R.id.stuff_et_name1);
        stuff_et_name2 = findViewById(R.id.stuff_et_name2);
        stuff_et_name3 = findViewById(R.id.stuff_et_name3);
        stuff_et_name4 = findViewById(R.id.stuff_et_name4);
        stuff_et_unit0 = findViewById(R.id.stuff_et_unit0);
        stuff_et_unit1 = findViewById(R.id.stuff_et_unit1);
        stuff_et_unit2 = findViewById(R.id.stuff_et_unit2);
        stuff_et_unit3 = findViewById(R.id.stuff_et_unit3);
        stuff_et_unit4 = findViewById(R.id.stuff_et_unit4);


        //요리소개
        pro_et0 = findViewById(R.id.pro_et0);
        pro_et1 = findViewById(R.id.pro_et1);
        pro_et2 = findViewById(R.id.pro_et2);
        pro_et3 = findViewById(R.id.pro_et3);
        pro_et4 = findViewById(R.id.pro_et4);
        pro_et5 = findViewById(R.id.pro_et5);
        pro_et6 = findViewById(R.id.pro_et6);
        pro_et7 = findViewById(R.id.pro_et7);
        pro_et8 = findViewById(R.id.pro_et8);
        pro_et9 = findViewById(R.id.pro_et9);
        pro_tv0 = findViewById(R.id.pro_tv0);
        pro_tv1 = findViewById(R.id.pro_tv1);
        pro_tv2 = findViewById(R.id.pro_tv2);
        pro_tv3 = findViewById(R.id.pro_tv3);
        pro_tv4 = findViewById(R.id.pro_tv4);
        pro_tv5 = findViewById(R.id.pro_tv5);
        pro_tv6 = findViewById(R.id.pro_tv6);
        pro_tv7 = findViewById(R.id.pro_tv7);
        pro_tv8 = findViewById(R.id.pro_tv8);
        pro_tv9 = findViewById(R.id.pro_tv9);
        pro_iv0 = findViewById(R.id.pro_iv0);
        pro_iv1 = findViewById(R.id.pro_iv1);
        pro_iv2 = findViewById(R.id.pro_iv2);
        pro_iv3 = findViewById(R.id.pro_iv3);
        pro_iv4 = findViewById(R.id.pro_iv4);
        pro_iv5 = findViewById(R.id.pro_iv5);
        pro_iv6 = findViewById(R.id.pro_iv6);
        pro_iv7 = findViewById(R.id.pro_iv7);
        pro_iv8 = findViewById(R.id.pro_iv8);
        pro_iv9 = findViewById(R.id.pro_iv9);

        //레이아웃
        frame_0 = findViewById(R.id.frame_0);
        frame_1 = findViewById(R.id.frame_1);
        frame_2 = findViewById(R.id.frame_2);
        frame_3 = findViewById(R.id.frame_3);
        frame_4 = findViewById(R.id.frame_4);
        frame_5 = findViewById(R.id.frame_5);
        frame_6 = findViewById(R.id.frame_6);
        frame_7 = findViewById(R.id.frame_7);
        frame_8 = findViewById(R.id.frame_8);
        frame_9 = findViewById(R.id.frame_9);
        rec_update_0 = findViewById(R.id.rec_update_0);
        rec_update_1 = findViewById(R.id.rec_update_1);
        rec_update_2 = findViewById(R.id.rec_update_2);
        rec_update_3 = findViewById(R.id.rec_update_3);
        rec_update_4 = findViewById(R.id.rec_update_4);
        stuff_update_0 = findViewById(R.id.stuff_update_0);
        stuff_update_1 = findViewById(R.id.stuff_update_1);
        stuff_update_2 = findViewById(R.id.stuff_update_2);
        stuff_update_3 = findViewById(R.id.stuff_update_3);
        stuff_update_4 = findViewById(R.id.stuff_update_4);


        //요리정보
        cok_portion_tv_up = findViewById(R.id.cok_portion_tv_up);
        cok_time_tv_up = findViewById(R.id.cok_time_tv_up);
        cok_degree_tv_up = findViewById(R.id.cok_degree_tv_up);



        // 보내온 값 파싱
        Intent intent = getIntent();
        //final RecipeDTO selItem = (RecipeDTO) intent.getSerializableExtra("selItem");
        final MyRecipeDTO selItem = (MyRecipeDTO) intent.getSerializableExtra("selItem");

        rec_title_up = selItem.getTitle();
        rec_subtitle_up = selItem.getSubtitle();
        rec_id = selItem.getRecipe_id();
        cat1_c = selItem.getCat1();
        cat2_c = selItem.getCat2();
        cat3_c = selItem.getCat3();
        cat4_c = selItem.getCat4();
        portion = selItem.getPortion();
        time = selItem.getTime();
        degree = selItem.getDegree();

        material_name0 = selItem.getMaterial_name0();
        material_name1 = selItem.getMaterial_name1();
        material_name2 = selItem.getMaterial_name2();
        material_name3 = selItem.getMaterial_name3();
        material_name4 = selItem.getMaterial_name4();
        material_name5 = selItem.getMaterial_name5();
        material_name6 = selItem.getMaterial_name6();
        material_name7 = selItem.getMaterial_name7();
        material_name8 = selItem.getMaterial_name8();
        material_name9 = selItem.getMaterial_name9();
        material_unit0 = selItem.getMaterial_unit0();
        material_unit1 = selItem.getMaterial_unit1();
        material_unit2 = selItem.getMaterial_unit2();
        material_unit3 = selItem.getMaterial_unit3();
        material_unit4 = selItem.getMaterial_unit4();
        material_unit5 = selItem.getMaterial_unit5();
        material_unit6 = selItem.getMaterial_unit6();
        material_unit7 = selItem.getMaterial_unit7();
        material_unit8 = selItem.getMaterial_unit8();
        material_unit9 = selItem.getMaterial_unit9();
        text1 = selItem.getText1();
        text2 = selItem.getText2();
        text3 = selItem.getText3();
        text4 = selItem.getText4();
        text5 = selItem.getText5();
        text6 = selItem.getText6();
        text7 = selItem.getText7();
        text8 = selItem.getText8();
        text9 = selItem.getText9();
        text10 = selItem.getText10();

        /*
        rec_id = intent.getStringExtra("id");
        rec_title = intent.getStringExtra("title");
        rec_subtitle = intent.getStringExtra("subtitle");
*/
        // 가져온 값 써 넣기
        et_title.setText(rec_title_up);
        et_subtitle.setText(rec_subtitle_up);

        cok_portion_tv_up.setText(portion);
        cok_time_tv_up.setText(time);
        cok_degree_tv_up.setText(degree);

        if( material_unit0.equals("") )    stuff_update_0.setVisibility(View.GONE);
        if( material_unit1.equals("") )    stuff_update_1.setVisibility(View.GONE);
        if( material_unit2.equals("") )    stuff_update_2.setVisibility(View.GONE);
        if( material_unit3.equals("") )    stuff_update_3.setVisibility(View.GONE);
        if( material_unit4.equals("") )    stuff_update_4.setVisibility(View.GONE);
        if( material_unit5.equals("") )    rec_update_0.setVisibility(View.GONE);
        if( material_unit6.equals("") )    rec_update_1.setVisibility(View.GONE);
        if( material_unit7.equals("") )    rec_update_2.setVisibility(View.GONE);
        if( material_unit8.equals("") )    rec_update_3.setVisibility(View.GONE);
        if( material_unit9.equals("") )    rec_update_4.setVisibility(View.GONE);

        if ( text1.equals("") || pro_iv0.equals("") ) frame_0.setVisibility(View.GONE);
        if ( text2.equals("") || pro_iv1.equals("") ) frame_1.setVisibility(View.GONE);
        if ( text3.equals("") || pro_iv2.equals("") ) frame_2.setVisibility(View.GONE);
        if ( text4.equals("")|| pro_iv3.equals("") ) frame_3.setVisibility(View.GONE);
        if ( text5.equals("") || pro_iv4.equals("") ) frame_4.setVisibility(View.GONE);
        if ( text6.equals("") || pro_iv5.equals("") ) frame_5.setVisibility(View.GONE);
        if ( text7.equals("") || pro_iv6.equals("") ) frame_6.setVisibility(View.GONE);
        if ( text8.equals("") || pro_iv7.equals("") ) frame_7.setVisibility(View.GONE);
        if ( text9.equals("") || pro_iv8.equals("") ) frame_8.setVisibility(View.GONE);
        if ( !text10.equals("") || pro_iv9.equals("") ) frame_9.setVisibility(View.GONE);

        stuff_tv_name0 = findViewById(R.id.stuff_tv_name0);
        material_tv_name0 = findViewById(R.id.material_tv_name0);

        if( material_name5.equals("") && material_name6.equals("") && material_name7.equals("") && material_name8.equals("") && material_name9.equals("") )
            stuff_tv_name0.setVisibility(View.GONE);
        if (material_name0.equals("") && material_name1.equals("") )
            material_tv_name0.setVisibility(View.GONE);


        /*
        cat1_up.setText(cat1_c);
        cat2_up.setText(cat2_c);
        cat3_up.setText(cat3_c);
        cat4_up.setText(cat4_c);
        */

        //카테고리
        cat1 = findViewById(R.id.cat1);
        adapter_cat1 = ArrayAdapter.createFromResource(this, R.array.cat1, android.R.layout.simple_spinner_item);
        adapter_cat1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat1.setAdapter(adapter_cat1);
        cat1.setOnItemSelectedListener(this);

        cat2 = findViewById(R.id.cat2);
        adapter_cat2 = ArrayAdapter.createFromResource(this, R.array.cat2, android.R.layout.simple_spinner_item);
        adapter_cat2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat2.setAdapter(adapter_cat2);
        cat2.setOnItemSelectedListener(this);

        cat3 = findViewById(R.id.cat3);
        adapter_cat3 = ArrayAdapter.createFromResource(this, R.array.cat3, android.R.layout.simple_spinner_item);
        adapter_cat3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat3.setAdapter(adapter_cat3);
        cat3.setOnItemSelectedListener(this);

        cat4 = findViewById(R.id.cat4);
        adapter_cat4 = ArrayAdapter.createFromResource(this, R.array.cat4, android.R.layout.simple_spinner_item);
        adapter_cat4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat4.setAdapter(adapter_cat4);
        cat4.setOnItemSelectedListener(this);
        spic_et_name0.setText(material_name0);
        spic_et_unit0.setText(material_unit0);
        spic_et_name1.setText(material_name1);
        spic_et_unit1.setText(material_unit1);
        spic_et_name2.setText(material_name2);
        spic_et_unit2.setText(material_unit2);
        spic_et_name3.setText(material_name3);
        spic_et_unit3.setText(material_unit3);
        spic_et_name4.setText(material_name4);
        spic_et_unit4.setText(material_unit4);

        //양념
        stuff_et_name0.setText(material_name5);
        stuff_et_unit0.setText(material_unit5);
        stuff_et_name1.setText(material_name6);
        stuff_et_unit1.setText(material_unit6);
        stuff_et_name2.setText(material_name7);
        stuff_et_unit2.setText(material_unit7);
        stuff_et_name3.setText(material_name8);
        stuff_et_unit3.setText(material_unit8);
        stuff_et_name4.setText(material_name9);
        stuff_et_unit4.setText(material_unit9);


        //요리소개
        pro_et0.setText(text1);
        pro_et1.setText(text2);
        pro_et2.setText(text3);
        pro_et3.setText(text4);
        pro_et4.setText(text5);
        pro_et5.setText(text6);
        pro_et6.setText(text7);
        pro_et7.setText(text8);
        pro_et8.setText(text9);
        pro_et9.setText(text10);



        pro_tv0.setText("1");
        pro_tv1.setText("2");
        pro_tv2.setText("3");
        pro_tv3.setText("4");
        pro_tv4.setText("5");
        pro_tv5.setText("6");
        pro_tv6.setText("7");
        pro_tv7.setText("8");
        pro_tv8.setText("9");
        pro_tv9.setText("10");

        //요리ㅅ사진 가지고오는곳
        getimge();

        rec_update = findViewById(R.id.rec_update);

        rec_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 다시 쓰는곳
                rec_title_up = et_title.getText().toString();
                rec_subtitle_up = et_subtitle.getText().toString();
                /*
                cat1_c = cat1_up.getText().toString();
                cat2_c = cat2_up.getText().toString();
                cat3_c = cat3_up.getText().toString();
                cat4_c = cat4_up.getText().toString();
                 */


                portion = cok_portion_tv_up.getText().toString();
                time = cok_time_tv_up.getText().toString();
                degree = cok_degree_tv_up.getText().toString();
                material_name0 = spic_et_name0.getText().toString();
                material_name1 = spic_et_name1.getText().toString();
                material_name2 = spic_et_name2.getText().toString();
                material_name3 = spic_et_name3.getText().toString();
                material_name4 = spic_et_name4.getText().toString();
                material_name5 = stuff_et_name0.getText().toString();
                material_name6 = stuff_et_name1.getText().toString();
                material_name7 = stuff_et_name2.getText().toString();
                material_name8 = stuff_et_name3.getText().toString();
                material_name9 = stuff_et_name4.getText().toString();
                material_unit0 = spic_et_unit0.getText().toString();
                material_unit1 = spic_et_unit1.getText().toString();
                material_unit2 = spic_et_unit2.getText().toString();
                material_unit3 = spic_et_unit3.getText().toString();
                material_unit4 = spic_et_unit4.getText().toString();
                material_unit5 = stuff_et_unit0.getText().toString();
                material_unit6 = stuff_et_unit1.getText().toString();
                material_unit7 = stuff_et_unit2.getText().toString();
                material_unit8 = stuff_et_unit3.getText().toString();
                material_unit9 = stuff_et_unit4.getText().toString();
                text1 = pro_et0.getText().toString();
                text2 = pro_et1.getText().toString();
                text3 = pro_et2.getText().toString();
                text4 = pro_et3.getText().toString();
                text5 = pro_et4.getText().toString();
                text6 = pro_et5.getText().toString();
                text7 = pro_et6.getText().toString();
                text8 = pro_et7.getText().toString();
                text9 = pro_et8.getText().toString();
                text10 = pro_et9.getText().toString();
                imagepath1 = selItem.getImagepath1();
                imagepath2 = selItem.getImagepath2();
                imagepath3 = selItem.getImagepath3();
                imagepath4 = selItem.getImagepath4();
                image0 = selItem.getImagepath();
                image1 = selItem.getImage1();
                image2 = selItem.getImage2();
                image3 = selItem.getImage3();
                image4 = selItem.getImage4();
                image5 = selItem.getImage5();
                image6 = selItem.getImage6();
                image7 = selItem.getImage7();
                image8 = selItem.getImage8();
                image9 = selItem.getImage9();
                image10 = selItem.getImage10();

                ListUpdate listUpdate = new ListUpdate(rec_id,rec_title_up, rec_subtitle_up,
                        cat1_c,cat2_c,cat3_c,cat4_c,
                        portion,time,degree,
                        material_name0, material_name1, material_name2,
                        material_name3, material_name4, material_name5,
                        material_name6, material_name7, material_name8,
                        material_name9,material_unit0, material_unit1,
                        material_unit2, material_unit3, material_unit4,
                        material_unit5, material_unit6, material_unit7,
                        material_unit8, material_unit9,
                        text1, text2, text3 ,text4 ,text5,text6,text7,text8,text9,text10,
                        imagepath1,imagepath2,imagepath3,imagepath4,
                        image0,image1,image2,image3,image4,image5,image6,image7,image8,image9,image10);
                Log.d("itemselected", "onClick: "+ cat1_c + ", " + cat2_c + ", " + cat3_c + ", " + cat4_c);
                listUpdate.execute();

                Toast.makeText(getApplicationContext(), "수정성공", Toast.LENGTH_LONG).show();
                Log.d("수정했니2", rec_id);
                Intent showIntent = new Intent(getApplicationContext(), SubActivity.class);
                showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   // 이 엑티비티 플래그를 사용하여 엑티비티를 호출하게 되면 새로운 태스크를 생성하여 그 태스크안에 엑티비티를 추가하게 됩니다. 단, 기존에 존재하는 태스크들중에 생성하려는 엑티비티와 동일한 affinity(관계, 유사)를 가지고 있는 태스크가 있다면 그곳으로 새 엑티비티가 들어가게됩니다.
                        Intent.FLAG_ACTIVITY_SINGLE_TOP | // 엑티비티를 호출할 경우 호출된 엑티비티가 현재 태스크의 최상단에 존재하고 있었다면 새로운 인스턴스를 생성하지 않습니다. 예를 들어 ABC가 엑티비티 스택에 존재하는 상태에서 C를 호출하였다면 여전히 ABC가 존재하게 됩니다.
                        Intent.FLAG_ACTIVITY_CLEAR_TOP); // 만약에 엑티비티스택에 호출하려는 엑티비티의 인스턴스가 이미 존재하고 있을 경우에 새로운 인스턴스를 생성하는 것 대신에 존재하고 있는 엑티비티를 포그라운드로 가져옵니다. 그리고 엑티비티스택의 최상단 엑티비티부터 포그라운드로 가져올 엑티비티까지의 모든 엑티비티를 삭제합니다.
                startActivity(showIntent);

                finish();
            }
        });

    }

    private void getimge(){
        Intent intent = getIntent();
        //RecipeDTO selItem = (RecipeDTO) intent.getSerializableExtra("selItem");
        MyRecipeDTO selItem = (MyRecipeDTO) intent.getSerializableExtra("selItem");
        //요리소개 사진넣는곳
        rec_image = findViewById(R.id.rec_ImageView_up);
        pro_iv0 = findViewById(R.id.pro_iv0);
        pro_iv1 = findViewById(R.id.pro_iv1);
        pro_iv2 = findViewById(R.id.pro_iv2);
        pro_iv3 = findViewById(R.id.pro_iv3);
        pro_iv4 = findViewById(R.id.pro_iv4);
        pro_iv5 = findViewById(R.id.pro_iv5);
        pro_iv6 = findViewById(R.id.pro_iv6);
        pro_iv7 = findViewById(R.id.pro_iv7);
        pro_iv8 = findViewById(R.id.pro_iv8);
        pro_iv9 = findViewById(R.id.pro_iv9);

        pathimage1 = findViewById(R.id.image1);
        pathimage2 = findViewById(R.id.image2);
        pathimage3 = findViewById(R.id.image3);
        pathimage4 = findViewById(R.id.image4);


        image0 = selItem.getImagepath();
        image1 = selItem.getImage1();
        image2 = selItem.getImage2();
        image3 = selItem.getImage3();
        image4 = selItem.getImage4();
        image5 = selItem.getImage5();
        image6 = selItem.getImage6();
        image7 = selItem.getImage7();
        image8 = selItem.getImage8();
        image9 = selItem.getImage9();
        image10 = selItem.getImage10();
        imagepath1 =selItem.getImagepath1();
        imagepath2 = selItem.getImagepath2();
        imagepath3 = selItem.getImagepath3();
        imagepath4 = selItem.getImagepath4();

        Log.d("imagepathupdate", "getimge: " + image0 + image1);

        Thread mThread0 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image0); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap0 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread0.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread0.join();
            rec_image.setImageBitmap(bitmap0);
        } catch (InterruptedException e) {

        }


        Thread mThread1 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image1); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap1 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread1.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread1.join();
            pro_iv0.setImageBitmap(bitmap1);
        } catch (InterruptedException e) {

        }


        Thread mThread2 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image2); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap2 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread2.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread2.join();
            pro_iv1.setImageBitmap(bitmap2);
        } catch (InterruptedException e) {

        }


        Thread mThread3 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image3); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap3 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread3.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread3.join();
            pro_iv2.setImageBitmap(bitmap3);
        } catch (InterruptedException e) {

        }


        Thread mThread10 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image10); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap10 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread10.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread10.join();
            pro_iv9.setImageBitmap(bitmap10);
        } catch (InterruptedException e) {

        }


        Thread mThread4 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image4); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap4 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread4.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread4.join();
            pro_iv3.setImageBitmap(bitmap4);
        } catch (InterruptedException e) {

        }


        Thread mThread5 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image5); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap5 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread5.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread5.join();
            pro_iv4.setImageBitmap(bitmap5);
        } catch (InterruptedException e) {

        }


        Thread mThread6 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image6); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap6 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread6.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread6.join();
            pro_iv5.setImageBitmap(bitmap6);
        } catch (InterruptedException e) {

        }


        Thread mThread7 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image7); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap7 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread7.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread7.join();
            pro_iv6.setImageBitmap(bitmap7);
        } catch (InterruptedException e) {

        }


        Thread mThread8 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image8); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap8 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread8.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread8.join();
            pro_iv7.setImageBitmap(bitmap8);
        } catch (InterruptedException e) {

        }


        Thread mThread9 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(image9); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap9 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        mThread9.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            mThread9.join();
            pro_iv8.setImageBitmap(bitmap9);
        } catch (InterruptedException e) {

        }


        //완성사진
        Thread cookThread1 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(imagepath1); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitpath1 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        Log.d("imagepathupdate", "getimge: " + imagepath1);

        cookThread1.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            cookThread1.join();
            pathimage1.setImageBitmap(bitpath1);
        } catch (InterruptedException e) {

        }


        Thread cookThread2 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(imagepath2); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitpath2 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        cookThread2.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            cookThread2.join();
            pathimage2.setImageBitmap(bitpath2);
        } catch (InterruptedException e) {

        }


        Thread cookThread3 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(imagepath3); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitpath3 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        cookThread3.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            cookThread3.join();
            pathimage3.setImageBitmap(bitpath3);
        } catch (InterruptedException e) {

        }


        Thread cookThread4 = new Thread() {

            @Override
            public void run() {

                try {
                    URL url = new URL(imagepath4); // URL 주소를 이용해서 URL 객체 생성

                    //  아래 코드는 웹에서 이미지를 가져온 뒤
                    //  이미지 뷰에 지정할 Bitmap을 생성하는 과정

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitpath4 = BitmapFactory.decodeStream(is);

                } catch(Exception e) {

                }
            }
        };

        cookThread4.start(); // 웹에서 이미지를 가져오는 작업 스레드 실행.

        try {
            cookThread4.join();
            pathimage4.setImageBitmap(bitpath4);
        } catch (InterruptedException e) {

        }


    }

    //카테고리 선택 됐을 경우 Toast
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        switch (adapterView.getId()) {
            case R.id.cat1:
                if (adapterView.getSelectedItemPosition() > 0) {
                    /*cat1_c = String.valueOf(adapterView.getSelectedItemPosition());*/
                    cat1_c = adapterView.getItemAtPosition(position).toString();
                    Log.d("itemselect", "onItemSelected: " + cat1_c);
                }
                break;
            case R.id.cat2:
                if (adapterView.getSelectedItemPosition() > 0) {
                    /*cat2_c = String.valueOf(adapterView.getSelectedItemPosition());*/
                    cat2_c = adapterView.getItemAtPosition(position).toString();
                    Log.d("itemselect", "onItemSelected: " + cat2_c);
                }
                break;
            case R.id.cat3:
                if (adapterView.getSelectedItemPosition() > 0) {
                    /*cat3_c = String.valueOf(adapterView.getSelectedItemPosition());*/
                    cat3_c = adapterView.getItemAtPosition(position).toString();
                    Log.d("itemselect", "onItemSelected: " + cat3_c);
                }
                break;
            case R.id.cat4:
                if (adapterView.getSelectedItemPosition() > 0) {
                    /*cat4_c = String.valueOf(adapterView.getSelectedItemPosition());*/
                    cat4_c = adapterView.getItemAtPosition(position).toString();
                    Log.d("itemselect", "onItemSelected: " + cat4_c);
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}