package com.example.han2.Navigation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Adapter.RecipeCommentAdapter;
import com.example.han2.Atask.GoodUpdate;
import com.example.han2.Atask.ListDelete;
import com.example.han2.Atask.RecipeComment;
import com.example.han2.Atask.RecipeCommentInsert;
import com.example.han2.Atask.ScrapInsert;
import com.example.han2.Dto.MyRecipeDTO;
import com.example.han2.Dto.RecipeCommentDTO;
import com.example.han2.Dto.RecipeDTO;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;
import com.example.han2.RecWrite.RecUpdate;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.han2.MainActivity.loginDTO;

public class RecipeDetail extends AppCompatActivity {
    private static final String TAG = "Bottom";
    ImageView imageView;
    TextView nickname, title, comment, portion, time, degree;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10;
    ImageView good_detail, scrap;
    TextView text0, text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;
    String MyRecipeDTO = null;
    String scr_userid = "", scr_recipeid = "", target_userid = "";
    String good = "", recipe_id = "";

    RecipeComment recipeComment;
    RecipeCommentAdapter adapter;
    RecyclerView recyclerView;

    ImageLoader imageLoader;
    RecipeCommentDTO recipeCommentDTO = null;
    ImageView btn_update,btn_delete;
    Button button1;
    EditText editText;
    String member_id = "", content = "", id ="";
    TextView t_update, t_delete;
   

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintodayrecipedetail);

        ImageView img = findViewById(R.id.back_go);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        final MyRecipeDTO myRecipeDTO = (MyRecipeDTO)intent.getSerializableExtra("item");
        final RecipeDTO selItem = (RecipeDTO) intent.getSerializableExtra("selItem");

        //수정,삭제버튼 작성자와 관리자만 보기
        btn_update = findViewById(R.id.recipe_update);
        btn_delete = findViewById(R.id.recipe_delete);
        t_update = findViewById(R.id.recipe_update_text);
        t_delete = findViewById(R.id.recipe_delete_text);
        id = myRecipeDTO.getNickname();
        if(loginDTO.getGrade_id().equals("10") || loginDTO.getNickname().equals(id)){
            btn_update.setVisibility(View.VISIBLE);
            btn_delete.setVisibility(View.VISIBLE);
            t_update.setVisibility(View.VISIBLE);
            t_delete.setVisibility(View.VISIBLE);
        } else {
            btn_update.setVisibility(View.GONE);
            btn_delete.setVisibility(View.GONE);
            t_update.setVisibility(View.GONE);
            t_delete.setVisibility(View.GONE);
        }

        //레시피 수정버튼클릭시
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myRecipeDTO != null){
                    Log.d("sub1:update1", myRecipeDTO.getRecipe_id());
                    Intent refresh = new Intent(getApplicationContext(), SubActivity.class);
                    startActivity(refresh);

                    Intent intent = new Intent(getApplicationContext(), RecUpdate.class);
                    intent.putExtra("selItem", myRecipeDTO);

                    startActivity(intent);
                    finish();
                    adapter.notifyDataSetChanged();

                }else {
                    Toast.makeText(getApplicationContext(), "항목 선택을 해 주세요",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //레시피 삭제버튼 클릭시
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("레시피")
                        .setMessage("정말 삭제하겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(myRecipeDTO != null){
                                    ListDelete listDelete = new ListDelete(myRecipeDTO.getRecipe_id());
                                    listDelete.execute();

                                    // 화면갱신
                                    Intent refresh = new Intent(getApplicationContext(), SubActivity.class);
                                    startActivity(refresh);
                                    finish();

                                    adapter.notifyDataSetChanged();
                                }else {
                                    Toast.makeText(getApplicationContext(), "항목 선택을 해 주세요",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        //상세레시피 코멘트 데이터받기
        ArrayList<RecipeCommentDTO> arrayList = new ArrayList<>();
        adapter = new RecipeCommentAdapter(arrayList);
        recyclerView = findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        //상세레시피 코멘트작성하기
        button1 = findViewById(R.id.btnAdd);
        editText = findViewById(R.id.recipeComment_et);
        recipe_id = myRecipeDTO.getRecipe_id();

        Log.d(TAG, "onCreate:recipe_id "+recipe_id);

        member_id = loginDTO.getId();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                content = editText.getText().toString();
                editText.setText("");
                RecipeCommentInsert recipeCommentInsert = new RecipeCommentInsert(recipe_id, member_id, content);
                try {
                    recipeCommentInsert.execute().get();
                    ArrayList<RecipeCommentDTO> arrayList = new ArrayList<>();
                    adapter = new RecipeCommentAdapter(arrayList);
                    recyclerView = findViewById(R.id.recyclerView1);
                    LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager1);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setNestedScrollingEnabled(false);
                    recipeComment = new RecipeComment(arrayList, adapter, recipe_id);
                    recipeComment.execute();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("recipeCommentInsert", "레시피아이디 : " + recipe_id + ", 글쓴이 :" + member_id + ", 내용 :" + content);


            }
        });

        scr_userid = loginDTO.getId();
        scr_recipeid = myRecipeDTO.getRecipe_id();
        target_userid = myRecipeDTO.getNickname();
        good = myRecipeDTO.getGood();


        imageView = findViewById(R.id.image_detail_main);
        good_detail = findViewById(R.id.good_detail);
        scrap = findViewById(R.id.scrap_detail);

        nickname = findViewById(R.id.nickname_detail);
        title = findViewById(R.id.title_detail);
        comment = findViewById(R.id.comment_detail);
        portion = findViewById(R.id.portion_detail);
        time = findViewById(R.id.time_detail);
        degree = findViewById(R.id.degree_detail);

        imageView1 = findViewById(R.id.recipe_pic1_detail);
        imageView2 = findViewById(R.id.recipe_pic2_detail);
        imageView3 = findViewById(R.id.recipe_pic3_detail);
        imageView4 = findViewById(R.id.recipe_pic4_detail);
        imageView5 = findViewById(R.id.recipe_pic5_detail);
        imageView6 = findViewById(R.id.recipe_pic6_detail);
        imageView7 = findViewById(R.id.recipe_pic7_detail);
        imageView8 = findViewById(R.id.recipe_pic8_detail);
        imageView9 = findViewById(R.id.recipe_pic9_detail);
        imageView10 = findViewById(R.id.recipe_pic10_detail);

        text0 = findViewById(R.id.material_detail);

        text1 = findViewById(R.id.recipe_text1_detail);
        text2 = findViewById(R.id.recipe_text2_detail);
        text3 = findViewById(R.id.recipe_text3_detail);
        text4 = findViewById(R.id.recipe_text4_detail);
        text5 = findViewById(R.id.recipe_text5_detail);
        text6 = findViewById(R.id.recipe_text6_detail);
        text7 = findViewById(R.id.recipe_text7_detail);
        text8 = findViewById(R.id.recipe_text8_detail);
        text9 = findViewById(R.id.recipe_text9_detail);
        text10 = findViewById(R.id.recipe_text10_detail);

        //좋아요 이미지 클릭
        good_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodUpdate goodUpdate = new GoodUpdate(good, recipe_id);
                goodUpdate.execute();
                Toast.makeText(RecipeDetail.this, "좋아요", Toast.LENGTH_SHORT).show();
                good_detail.setEnabled(false);
                good_detail.setImageResource(R.drawable.like);
            }
        });

        //스크랩하기 이미지클릭
        scrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ScrapInsert scrapInsert = new ScrapInsert(scr_userid, scr_recipeid, target_userid);
                scrapInsert.execute();
                Toast.makeText(RecipeDetail.this, "스크랩 완료", Toast.LENGTH_SHORT).show();
                scrap.setEnabled(false);
            }
        });


        ImageLoader.getInstance().displayImage(myRecipeDTO.getImagepath(), imageView);
        nickname.setText(myRecipeDTO.getNickname());
        title.setText("< " + myRecipeDTO.getTitle() + " >");
        comment.setText(myRecipeDTO.getSubtitle());
        portion.setText(myRecipeDTO.getPortion());
        time.setText(myRecipeDTO.getTime());
        degree.setText(myRecipeDTO.getDegree());

        //재료넣기
        if(!myRecipeDTO.getMaterial_name0().equals("")) {
            text0.setText(String.format("%s %s", myRecipeDTO.getMaterial_name0(), myRecipeDTO.getMaterial_unit0()));
        }
        if(!myRecipeDTO.getMaterial_name1().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name1() + " " + myRecipeDTO.getMaterial_unit1());
            Log.d("TAG: Material1", "onCreate: "+myRecipeDTO.getMaterial_name1()+ myRecipeDTO.getMaterial_unit1());
        }
        if(!myRecipeDTO.getMaterial_name2().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name2() + " " + myRecipeDTO.getMaterial_unit2());
        }
        if(!myRecipeDTO.getMaterial_name3().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name3() + " " + myRecipeDTO.getMaterial_unit3());
        }
        if(!myRecipeDTO.getMaterial_name4().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name4() + " " + myRecipeDTO.getMaterial_unit4());
        }
        if(!myRecipeDTO.getMaterial_name5().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name5() + " " + myRecipeDTO.getMaterial_unit5());
        }
        if(!myRecipeDTO.getMaterial_name6().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name6() + " " + myRecipeDTO.getMaterial_unit6());
        }
        if(!myRecipeDTO.getMaterial_name7().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name7() + " " + myRecipeDTO.getMaterial_unit7());
        }
        if(!myRecipeDTO.getMaterial_name8().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name8() + " " + myRecipeDTO.getMaterial_unit8());
        }
        if(!myRecipeDTO.getMaterial_name9().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name9() + " " + myRecipeDTO.getMaterial_unit9());
        }
        if(!myRecipeDTO.getMaterial_name10().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name10() + " " + myRecipeDTO.getMaterial_unit10());
        }
        if(!myRecipeDTO.getMaterial_name11().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name11() + " " + myRecipeDTO.getMaterial_unit11());
        }
        if(!myRecipeDTO.getMaterial_name12().equals("")) {
            text0.append(", " +myRecipeDTO.getMaterial_name12() + " " + myRecipeDTO.getMaterial_unit12());
        }


        //요리과정 보이기
        //1번
        if (myRecipeDTO.getImage1().equals("") || myRecipeDTO.getText1().equals("")) {
            imageView1.setVisibility(View.GONE);
            text1.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(myRecipeDTO.getImage1(), imageView1);
            text1.setText(String.format("1. %s", myRecipeDTO.getText1()));
        }
        //2번
        if (myRecipeDTO.getImage2().equals("") || myRecipeDTO.getText2().equals("")) {
            imageView2.setVisibility(View.GONE);
            text2.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(myRecipeDTO.getImage2(), imageView2);
            text2.setText(String.format("2. %s", myRecipeDTO.getText2()));
        }
        //3번
        if (myRecipeDTO.getImage3().equals("") || myRecipeDTO.getText3().equals("")) {
            imageView3.setVisibility(View.GONE);
            text3.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(myRecipeDTO.getImage3(), imageView3);
            text3.setText(String.format("3. %s", myRecipeDTO.getText3()));
        }
        //4번
        if (myRecipeDTO.getImage4().equals("") || myRecipeDTO.getText4().equals("")) {
            imageView4.setVisibility(View.GONE);
            text4.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(myRecipeDTO.getImage4(), imageView4);
            text4.setText(String.format("4. %s", myRecipeDTO.getText4()));
        }
        //5번

        if (myRecipeDTO.getImage5().equals("") || myRecipeDTO.getText5().equals("")) {
            imageView5.setVisibility(View.GONE);
            text5.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(myRecipeDTO.getImage5(), imageView5);
            text5.setText(String.format("5. %s", myRecipeDTO.getText5()));
        }
        //6번
        if (myRecipeDTO.getImage6().equals("") || myRecipeDTO.getText6().equals("")) {
            imageView6.setVisibility(View.GONE);
            text6.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(myRecipeDTO.getImage6(), imageView6);
            text6.setText(String.format("6. %s", myRecipeDTO.getText6()));
        }
        //7번
        if (myRecipeDTO.getImage7().equals("") || myRecipeDTO.getText7().equals("")) {
            imageView7.setVisibility(View.GONE);
            text7.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(myRecipeDTO.getImage7(), imageView7);
            text7.setText(String.format("7. %s", myRecipeDTO.getText7()));
        }
        //8번
        if (myRecipeDTO.getImage8().equals("") || myRecipeDTO.getText8().equals("")) {
            imageView8.setVisibility(View.GONE);
            text8.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(myRecipeDTO.getImage8(), imageView8);
            text8.setText(String.format("8. %s", myRecipeDTO.getText8()));
        }
        //9번
        if (myRecipeDTO.getImage9().equals("") || myRecipeDTO.getText9().equals("")) {
            imageView9.setVisibility(View.GONE);
            text9.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(myRecipeDTO.getImage9(), imageView9);
            text9.setText(String.format("9. %s", myRecipeDTO.getText9()));
        }
        //10번
        if (myRecipeDTO.getImage10().equals("") || myRecipeDTO.getText10().equals("")) {
            imageView10.setVisibility(View.GONE);
            text10.setVisibility(View.GONE);
        } else {
            ImageLoader.getInstance().displayImage(myRecipeDTO.getImage10(), imageView10);
            text10.setText(String.format("10. %s", myRecipeDTO.getText10()));
        }


        recipeComment = new RecipeComment(arrayList, adapter,recipe_id);
        recipeComment.execute();


    }
}
