package com.example.han2.RecWrite;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.han2.Adapter.ProcedureAdapter;
import com.example.han2.Atask.ListInsert;
import com.example.han2.Common.CommonMethod;
import com.example.han2.Main.SubActivity;
import com.example.han2.R;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.han2.Common.CommonMethod.ipConfig;
import static com.example.han2.MainActivity.loginDTO;


public class RecWrite extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    TextView cok_portion_tv, cok_time_tv, cok_degree_tv, rec_save;
    ArrayAdapter<CharSequence> adapter_cat1, adapter_cat2, adapter_cat3, adapter_cat4;
    String cat1_c = "";
    String cat2_c = "";
    String cat3_c = "";
    String cat4_c = "";


    EditText etTitle, etSubTitle;
    String title = "", subtitle = "";
    Spinner cat1,cat2,cat3,cat4;

    String portion_c = "", time_c = "", degree_c = "";


    //procedureAdapter
    Button procadd;

    //요리순서 이미지부분
    private static final int PRO_REQUEST0 = 2002;
    private static final int PRO_REQUEST0_LOAD = 2012;
    private static final int PRO_REQUEST1 = 2003;
    private static final int PRO_REQUEST1_LOAD = 2013;
    private static final int PRO_REQUEST2 = 2004;
    private static final int PRO_REQUEST2_LOAD = 2014;
    private static final int PRO_REQUEST3 = 2005;
    private static final int PRO_REQUEST3_LOAD = 2015;
    private static final int PRO_REQUEST4 = 2006;
    private static final int PRO_REQUEST4_LOAD = 2016;
    private static final int PRO_REQUEST5 = 2007;
    private static final int PRO_REQUEST5_LOAD = 2017;
    private static final int PRO_REQUEST6 = 2008;
    private static final int PRO_REQUEST6_LOAD = 2018;
    private static final int PRO_REQUEST7 = 2009;
    private static final int PRO_REQUEST7_LOAD = 2019;
    private static final int PRO_REQUEST8 = 2010;
    private static final int PRO_REQUEST8_LOAD = 2020;
    private static final int PRO_REQUEST9 = 2011;
    private static final int PRO_REQUEST9_LOAD = 2021;
    ImageView pro_iv0,pro_iv1,pro_iv2,pro_iv3,pro_iv4,pro_iv5,pro_iv6,pro_iv7,pro_iv8,pro_iv9;
    ImageView pro_delete0,pro_delete1,pro_delete2,pro_delete3,pro_delete4,pro_delete5,pro_delete6,pro_delete7,pro_delete8,pro_delete9;
    EditText pro_et0,pro_et1,pro_et2,pro_et3,pro_et4,pro_et5,pro_et6,pro_et7,pro_et8,pro_et9;
    TextView pro_tv0,pro_tv1,pro_tv2,pro_tv3,pro_tv4,pro_tv5,pro_tv6,pro_tv7,pro_tv8,pro_tv9;
    int pcnt = 0;
    FrameLayout frameLayout0,frameLayout1,frameLayout2,frameLayout3,frameLayout4,frameLayout5,frameLayout6,frameLayout7,frameLayout8,frameLayout9;
    public String imageRealPathA0, imagepathA0,imageRealPathA1, imagepathA1,imageRealPathA2, imagepathA2,imageRealPathA3, imagepathA3,imageRealPathA4, imagepathA4,imageRealPathA5, imagepathA5,imageRealPathA6, imagepathA6,imageRealPathA7, imagepathA7
            ,imageRealPathA8, imagepathA8,imageRealPathA9, imagepathA9;

    //재료 adapter
    int stuff_list = 3;
    Button stuffadd;
    private RecyclerView stuff_recyclerView;
    private LinearLayoutManager stuff_llm;
    private RecyclerView.Adapter stuff_adapter;
    String[] stuff_text1 = new String[5];
    String[] stuff_text2 = new String[5];

    //양념 adapter
    int spic_list = 3;
    Button spicadd;
    private RecyclerView spic_recyclerView;
    private LinearLayoutManager spic_llm;
    private RecyclerView.Adapter spic_adapter;
    String[] spic_text1 = new String[5];
    String[] spic_text2 = new String[5];

    //사진 및 카메라 권한
    final int CAMERA_REQUEST = 1000;
    final int LOAD_IMAGE = 1001;
    public String imageRealPathA, imagepath;
    File file = null;
    long fileSize = 0;
    java.text.SimpleDateFormat tmpDateFormat;
    ImageView rec_ImageView;
    final Context context = this;

    //멀티 앨범
    final int REQUEST_TAKE_ALBUM = 10;
    public String[] imageRealPathM = new String[4];
    public String imagepath1,imagepath2,imagepath3,imagepath4;

    //플로팅버튼
    private FloatingActionButton mFab;
    private int mPreviousVisibleItem;
    ScrollView rec_scrollView;
    String id;
    Date now;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        //풀로팅
        mFab = findViewById(R.id.fab);
        rec_scrollView = findViewById(R.id.rec_scrollView);


        id = loginDTO.getId();


        mFab.hide(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mFab.show(true);
                mFab.setShowAnimation(AnimationUtils.loadAnimation(RecWrite.this, R.anim.show_from_bottom));
                mFab.setHideAnimation(AnimationUtils.loadAnimation(RecWrite.this, R.anim.hide_to_bottom));
            }
        }, 500);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rec_scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if( scrollY > mPreviousVisibleItem){
                        mFab.show(true);
                    }else if( scrollY < mPreviousVisibleItem) {
                        mFab.hide(true);

                    }
                    mPreviousVisibleItem = scrollY;
                }
            });
        }

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("taginsert", "넘어오는지 테스트 " + imagepath +  ", " + imagepath1 + ", "+ imagepath2 + ", " + imagepath3 + ", " + imagepath4);
                if(fileSize <= 30000000) { // 파일크기가 30메가 보다 작아야 업로드 할수 있음
                    String title2 = etTitle.getText().toString();
                    String subtitle2 = etSubTitle.getText().toString();
                    String portion, time, degree;

                    String material_name0, material_name1, material_name2, material_name3, material_name4, material_name5, material_name6, material_name7, material_name8, material_name9;
                    String material_unit0, material_unit1, material_unit2, material_unit3, material_unit4, material_unit5, material_unit6, material_unit7, material_unit8, material_unit9;
                    String text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;

                    title = title2;
                    subtitle = subtitle2;
                    portion = portion_c;
                    time = time_c;
                    degree = degree_c;
                    material_name0 = stuff_text1[0];
                    material_name1 = stuff_text1[1];
                    material_name2 = stuff_text1[2];
                    material_name3 = stuff_text1[3];
                    material_name4 = stuff_text1[4];
                    material_name5 = spic_text1[0];
                    material_name6 = spic_text1[1];
                    material_name7 = spic_text1[2];
                    material_name8 = spic_text1[3];
                    material_name9 = spic_text1[4];

                    material_unit0 = stuff_text2[0];
                    material_unit1 = stuff_text2[1];
                    material_unit2 = stuff_text2[2];
                    material_unit3 = stuff_text2[3];
                    material_unit4 = stuff_text2[4];
                    material_unit5 = spic_text2[0];
                    material_unit6 = spic_text2[1];
                    material_unit7 = spic_text2[2];
                    material_unit8 = spic_text2[3];
                    material_unit9 = spic_text2[4];

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

                    Log.d("taginsert", "onClick: ");


                    String imageRealPathM1 = imageRealPathM[0];
                    String imageRealPathM2 = imageRealPathM[1];
                    String imageRealPathM3 = imageRealPathM[2];
                    String imageRealPathM4 = imageRealPathM[3];


                    Log.d("insert", title + "," + subtitle + cat1_c + "," + cat2_c + "," + cat3_c + "," + cat4_c + "," + portion_c + "," + time_c + "," + degree_c);

                    ListInsert insert = new ListInsert(title, subtitle, cat1_c, cat2_c, cat3_c, cat4_c, portion, time, degree,
                            imagepath,imagepath1,imagepath2,imagepath3,imagepath4, imageRealPathA, imageRealPathM1,imageRealPathM2,imageRealPathM3,imageRealPathM4,
                            material_name0, material_name1, material_name2, material_name3, material_name4, material_name5, material_name6, material_name7, material_name8, material_name9, material_unit0, material_unit1, material_unit2, material_unit3, material_unit4, material_unit5, material_unit6, material_unit7, material_unit8, material_unit9,
                            text1, text2, text3, text4, text5, text6, text7, text8, text9, text10,
                            imageRealPathA0, imagepathA0,imageRealPathA1, imagepathA1,imageRealPathA2, imagepathA2,imageRealPathA3, imagepathA3,imageRealPathA4, imagepathA4,imageRealPathA5, imagepathA5,imageRealPathA6, imagepathA6,imageRealPathA7, imagepathA7
                            ,imageRealPathA8, imagepathA8,imageRealPathA9, imagepathA9,id);
                    insert.execute();


                    Intent showIntent = new Intent(getApplicationContext(), SubActivity.class);
                    showIntent.putExtra("cat1", cat1.getSelectedItem().toString());
                    showIntent.putExtra("cat2", cat2.getSelectedItem().toString());
                    showIntent.putExtra("cat3", cat3.getSelectedItem().toString());
                    showIntent.putExtra("cat4", cat4.getSelectedItem().toString());
                    showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   // 이 엑티비티 플래그를 사용하여 엑티비티를 호출하게 되면 새로운 태스크를 생성하여 그 태스크안에 엑티비티를 추가하게 됩니다. 단, 기존에 존재하는 태스크들중에 생성하려는 엑티비티와 동일한 affinity(관계, 유사)를 가지고 있는 태스크가 있다면 그곳으로 새 엑티비티가 들어가게됩니다.
                            Intent.FLAG_ACTIVITY_SINGLE_TOP | // 엑티비티를 호출할 경우 호출된 엑티비티가 현재 태스크의 최상단에 존재하고 있었다면 새로운 인스턴스를 생성하지 않습니다. 예를 들어 ABC가 엑티비티 스택에 존재하는 상태에서 C를 호출하였다면 여전히 ABC가 존재하게 됩니다.
                            Intent.FLAG_ACTIVITY_CLEAR_TOP); // 만약에 엑티비티스택에 호출하려는 엑티비티의 인스턴스가 이미 존재하고 있을 경우에 새로운 인스턴스를 생성하는 것 대신에 존재하고 있는 엑티비티를 포그라운드로 가져옵니다. 그리고 엑티비티스택의 최상단 엑티비티부터 포그라운드로 가져올 엑티비티까지의 모든 엑티비티를 삭제합니다.
                    startActivity(showIntent);

                    finish();
                }
            }
        });



        //위험권한
        checkDangerousPermissions();

        //대표이미지
        tmpDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        rec_ImageView = findViewById(R.id.rec_ImageView);
        rec_ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // 제목셋팅
                alertDialogBuilder.setTitle("대표 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                // 프로그램을 종료한다
                                //사진촬영
                                if( id == 0){
                                    try{
                                        file = createFile();
                                        Log.d("FilePath ", file.getAbsolutePath());

                                    }catch(Exception e){
                                        Log.d("Sub1Add", "Something Wrong", e);
                                    }

                                    rec_ImageView.setVisibility(View.VISIBLE);

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                                FileProvider.getUriForFile(getApplicationContext(),
                                                        getApplicationContext().getPackageName() + ".fileprovider", file));
                                        Log.d("sub1:appId", getApplicationContext().getPackageName());
                                    }else {
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                    }

                                    if (intent.resolveActivity(getPackageManager()) != null) {
                                        startActivityForResult(intent, CAMERA_REQUEST);
                                    }

                                //사진로드
                                }else {
                                    rec_ImageView.setVisibility(View.VISIBLE);

                                    Intent intent = new Intent();
                                    intent.setType("image/*");
                                    intent.setAction(Intent.ACTION_PICK);
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), LOAD_IMAGE);

                                }

                                //Toast.makeText(getApplicationContext(),items[id] + " 선택했습니다.",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                // 다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                // 다이얼로그 보여주기
                alertDialog.show();

            }
        });


        etTitle = findViewById(R.id.rec_title);
        etSubTitle = findViewById(R.id.rec_subtitle);

        //재료 액티비티
        stuffadd = findViewById(R.id.stuffAdd);
        stuff_recyclerView = findViewById(R.id.stuffrv_insert);
        stuff_recyclerView.setLayoutManager(stuff_llm = new LinearLayoutManager(RecWrite.this, LinearLayoutManager.VERTICAL, false));
        stuff_recyclerView.addItemDecoration(new DividerItemDecoration(RecWrite.this, ProcedureAdapter.VERTICAL_LIST));
        stuff_recyclerView.setAdapter(stuff_adapter = new RecyclerView.Adapter() {//입력 방법
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            //edittext텍스트 컨텐츠 수집
            SparseArray<String> etTextAry = new SparseArray();
            SparseArray<String> etTextAry2 = new SparseArray();
            //edittext초점 위치
            int etFocusPos = -1;
            int etFocusPos2 = -1;
            TextWatcher textWatcher = new TextWatcher() {
                // s 문자열이 start 위치로부터 count 길이만큼이 after 길이로 변경되려고 한다는
                // 내용을 전달해주면서 호출되는 함수
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                // s 가 start 위치로부터 count 길이만큼 변경되었다는 것을 알려주는 함수
                // 이전 문자열에서 before 길이만큼 바뀌었다는 것을 알려준다.
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                // s 내의 어느 문자열이 변경되었음을 알려주기 위해 호출되는 함수
                // 조심해야 할 것은 s를 변경할 시에 다시 재호출이 되므로 무한루프에 빠질 가능성이 생김
                @Override
                public void afterTextChanged(Editable s) {
                    //텍스트가 수정 될 때마다 데이터 수집에 저장
                    Log.e("tag", "index=" + etFocusPos + ",save=" + s.toString());
                    etTextAry.put(etFocusPos, s.toString());
                    stuff_text1[etFocusPos] = s.toString();
                }
            };
            TextWatcher textWatcher2 = new TextWatcher() {
                // s 문자열이 start 위치로부터 count 길이만큼이 after 길이로 변경되려고 한다는
                // 내용을 전달해주면서 호출되는 함수
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    //텍스트가 수정 될 때마다 데이터 수집에 저장
                    Log.e("tag2", "index=" + etFocusPos2 + ",save=" + s.toString());
                    etTextAry2.put(etFocusPos2, s.toString());
                    stuff_text2[etFocusPos2] = s.toString();

                }
            };


            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemLayout = LayoutInflater.from(RecWrite.this).inflate(
                        R.layout.cok_stuff, parent, false);
                return new ItemHolder(itemLayout);
            }

            @Override
            public synchronized void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
                Log.e("tag", "바인딩 Holder,index=" + i);
                final int position = i;

                ItemHolder viewHolder = (ItemHolder) holder;
                viewHolder.stuff_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        stuff_adapter.notifyItemRemoved(position);
                        stuff_list -= 1;
                        stuff_adapter.notifyItemRangeChanged(position, stuff_list);
                    }
                });
                viewHolder.stuff_et_name.setText(etTextAry.get(position));
                viewHolder.stuff_et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) {
                            etFocusPos = position;
                            Log.e("tag", "etFocusPos 초점 선택-" + etFocusPos);
                        }
                    }
                });
                viewHolder.stuff_et_unit.setText(etTextAry2.get(position));
                viewHolder.stuff_et_unit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) {
                            etFocusPos2 = position;
                            Log.e("tag", "etFocusPos 초점 선택-" + etFocusPos2);
                        }
                    }
                });
            }

            @Override
            public int getItemCount() {
                stuffadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        stuff_list += 1;
                        notifyItemInserted(stuff_list);
                    }
                });
                return stuff_list;
            }

            @Override
            public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
                super.onViewDetachedFromWindow(holder);
                Log.e("tag", "숨기기 item=" + holder.getAdapterPosition());
                ItemHolder viewHolder = (ItemHolder) holder;
                viewHolder.stuff_et_name.removeTextChangedListener(textWatcher);
                viewHolder.stuff_et_name.clearFocus();
                viewHolder.stuff_et_unit.removeTextChangedListener(textWatcher2);
                viewHolder.stuff_et_unit.clearFocus();
                if (etFocusPos == holder.getAdapterPosition()) {
                    inputMethodManager.hideSoftInputFromWindow(((ItemHolder) holder).stuff_et_name.getWindowToken(), 0);
                    inputMethodManager.hideSoftInputFromWindow(((ItemHolder) holder).stuff_et_unit.getWindowToken(), 0);
                }
            }

            @Override
            public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
                super.onViewAttachedToWindow(holder);
                Log.e("tag", "디스플레이 item=" + holder.getAdapterPosition());
                ItemHolder viewHolder = (ItemHolder) holder;
                viewHolder.stuff_et_name.addTextChangedListener(textWatcher);
                viewHolder.stuff_et_unit.addTextChangedListener(textWatcher2);
                if (etFocusPos == holder.getAdapterPosition()) {
                    viewHolder.stuff_et_name.requestFocus();
                    viewHolder.stuff_et_name.setSelection(viewHolder.stuff_et_name.getText().length());
                } else if (etFocusPos2 == holder.getAdapterPosition()) {
                    viewHolder.stuff_et_unit.requestFocus();
                    viewHolder.stuff_et_unit.setSelection(viewHolder.stuff_et_unit.getText().length());
                }
            }

            class ItemHolder extends RecyclerView.ViewHolder {
                private EditText stuff_et_unit, stuff_et_name;
                private ImageView stuff_delete;

                public ItemHolder(View itemView) {
                    super(itemView);
                    stuff_delete = (ImageView) itemView.findViewById(R.id.stuff_delete);
                    stuff_et_name = (EditText) itemView.findViewById(R.id.stuff_et_name);
                    stuff_et_unit = (EditText) itemView.findViewById(R.id.stuff_et_unit);
                }


            }
        }); //setAdapter

        //양념
        spicadd = findViewById(R.id.spicAdd);
        spic_recyclerView = findViewById(R.id.spicrv_insert);
        spic_recyclerView.setLayoutManager(spic_llm = new LinearLayoutManager(RecWrite.this, LinearLayoutManager.VERTICAL, false));
        spic_recyclerView.addItemDecoration(new DividerItemDecoration(RecWrite.this, ProcedureAdapter.VERTICAL_LIST));
        spic_recyclerView.setAdapter(spic_adapter = new RecyclerView.Adapter() {//입력 방법
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            //edittext텍스트 컨텐츠 수집
            SparseArray<String> etTextAry = new SparseArray();
            SparseArray<String> etTextAry2 = new SparseArray();
            //edittext초점 위치
            int etFocusPos = -1;
            int etFocusPos2 = -1;
            TextWatcher textWatcher = new TextWatcher() {
                // s 문자열이 start 위치로부터 count 길이만큼이 after 길이로 변경되려고 한다는
                // 내용을 전달해주면서 호출되는 함수
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                // s 가 start 위치로부터 count 길이만큼 변경되었다는 것을 알려주는 함수
                // 이전 문자열에서 before 길이만큼 바뀌었다는 것을 알려준다.
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                // s 내의 어느 문자열이 변경되었음을 알려주기 위해 호출되는 함수
                // 조심해야 할 것은 s를 변경할 시에 다시 재호출이 되므로 무한루프에 빠질 가능성이 생김
                @Override
                public void afterTextChanged(Editable s) {
                    //텍스트가 수정 될 때마다 데이터 수집에 저장
                    Log.e("tag", "index=" + etFocusPos + ",save=" + s.toString());
                    etTextAry.put(etFocusPos, s.toString());
                    spic_text1[etFocusPos] = s.toString();
                }
            };
            TextWatcher textWatcher2 = new TextWatcher() {
                // s 문자열이 start 위치로부터 count 길이만큼이 after 길이로 변경되려고 한다는
                // 내용을 전달해주면서 호출되는 함수
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                // s 가 start 위치로부터 count 길이만큼 변경되었다는 것을 알려주는 함수
                // 이전 문자열에서 before 길이만큼 바뀌었다는 것을 알려준다.
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                // s 내의 어느 문자열이 변경되었음을 알려주기 위해 호출되는 함수
                // 조심해야 할 것은 s를 변경할 시에 다시 재호출이 되므로 무한루프에 빠질 가능성이 생김
                @Override
                public void afterTextChanged(Editable s) {
                    //텍스트가 수정 될 때마다 데이터 수집에 저장
                    Log.e("tag22", "index=" + etFocusPos2 + ",save=" + s.toString());
                    etTextAry2.put(etFocusPos2, s.toString());
                    spic_text2[etFocusPos2] = s.toString();
                }
            };


            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemLayout = LayoutInflater.from(RecWrite.this).inflate(
                        R.layout.cok_spic, parent, false);
                return new ItemHolder(itemLayout);
            }

            @Override
            public synchronized void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
                Log.e("tag", "바인딩 Holder,index=" + i);
                final int position = i;


                ItemHolder viewHolder = (ItemHolder) holder;
                final ItemHolder VH = (ItemHolder) holder;
                viewHolder.spic_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        spic_adapter.notifyItemRemoved(position);
                        spic_list -= 1;
                        spic_adapter.notifyItemRangeChanged(position, spic_list);
                    }
                });
                viewHolder.spic_et_name.setText(etTextAry.get(position));
                viewHolder.spic_et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {

                        if (b) {
                            etFocusPos = position;
                            Log.e("tag", "etFocusPos 초점 선택-" + etFocusPos);
                        }
                    }
                });
                viewHolder.spic_et_unit.setText(etTextAry2.get(position));
                viewHolder.spic_et_unit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) {
                            etFocusPos2 = position;
                            Log.e("tag2", "etFocusPos 초점 선택-" + etFocusPos2);

                        }
                    }
                });

            }

            @Override
            public int getItemCount() {
                spicadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        spic_list += 1;
                        notifyItemInserted(spic_list);
                    }
                });
                return spic_list;
            }

            @Override
            public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
                super.onViewDetachedFromWindow(holder);
                Log.e("tag", "숨기기 item=" + holder.getAdapterPosition());
                ItemHolder viewHolder = (ItemHolder) holder;
                viewHolder.spic_et_name.removeTextChangedListener(textWatcher);
                viewHolder.spic_et_name.clearFocus();
                viewHolder.spic_et_unit.removeTextChangedListener(textWatcher2);
                viewHolder.spic_et_unit.clearFocus();
                if (etFocusPos == holder.getAdapterPosition()) {
                    inputMethodManager.hideSoftInputFromWindow(((ItemHolder) holder).spic_et_name.getWindowToken(), 0);
                } else if (etFocusPos2 == holder.getAdapterPosition()) {
                    inputMethodManager.hideSoftInputFromWindow(((ItemHolder) holder).spic_et_unit.getWindowToken(), 0);
                }
            }

            @Override
            public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
                super.onViewAttachedToWindow(holder);
                Log.e("tag", "디스플레이 item=" + holder.getAdapterPosition());
                ItemHolder viewHolder = (ItemHolder) holder;
                ItemHolder VH = (ItemHolder) holder;
                viewHolder.spic_et_name.addTextChangedListener(textWatcher);
                viewHolder.spic_et_unit.addTextChangedListener(textWatcher2);
                if (etFocusPos == holder.getAdapterPosition()) {
                    viewHolder.spic_et_name.requestFocus();
                    viewHolder.spic_et_name.setSelection(viewHolder.spic_et_name.getText().length());
                } else if (etFocusPos2 == holder.getAdapterPosition()) {
                    viewHolder.spic_et_unit.requestFocus();
                    viewHolder.spic_et_unit.setSelection(viewHolder.spic_et_unit.getText().length());
                }
            }

            class ItemHolder extends RecyclerView.ViewHolder {
                private EditText spic_et_unit, spic_et_name;
                private ImageView spic_delete;

                public ItemHolder(View itemView) {
                    super(itemView);
                    spic_delete = (ImageView) itemView.findViewById(R.id.spic_delete);
                    spic_et_name = (EditText) itemView.findViewById(R.id.spic_et_name);
                    spic_et_unit = (EditText) itemView.findViewById(R.id.spic_et_unit);
                }


            }
        }); //setAdapter


        /*stuffrecycle.setAdapter(stuffadapter);
        stuffadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stuffadapter.addItem(new Stuff("예) 돼지고기","예) 300g"));
                stuffadapter.notifyDataSetChanged();
            }
        });
        spicrecycle = findViewById(R.id.spicrecycle);
        spicadapter = new StuffAdapter();
        spicadapter.addItem(new Stuff("예) 간장","예) 1.5T"));
        spicadapter.addItem(new Stuff("예) 맛술","예) 2T"));
        spicadapter.addItem(new Stuff("예) 다진마늘","예) 1T"));
        GridLayoutManager linearLayoutManager1 = new GridLayoutManager(this,1);

        spicrecycle.setNestedScrollingEnabled(false);
        spicrecycle.setLayoutManager(linearLayoutManager1);


        //양념 액티비티
        spicrecycle.setAdapter(spicadapter);
        spicadd = findViewById(R.id.spicAdd);
        spicadd.setOnClickListener(this);*/



        //요리소개
        procadd = findViewById(R.id.procAdd);

        /*
        pro_recyclerView = findViewById(R.id.prorv_insert);
        pro_recyclerView.setLayoutManager(pro_llm = new LinearLayoutManager(RecWrite.this, LinearLayoutManager.VERTICAL, false));
        pro_recyclerView.addItemDecoration(new DividerItemDecoration(RecWrite.this, ProcedureAdapter.VERTICAL_LIST));
        pro_recyclerView.setAdapter(pro_adapter = new RecyclerView.Adapter() {

            //입력 방법
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            //edittext텍스트 컨텐츠 수집
            SparseArray<String> etTextAry = new SparseArray();
            //edittext초점 위치
            int etFocusPos = -1;
            TextWatcher textWatcher = new TextWatcher() {
                // s 문자열이 start 위치로부터 count 길이만큼이 after 길이로 변경되려고 한다는
                // 내용을 전달해주면서 호출되는 함수
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                // s 가 start 위치로부터 count 길이만큼 변경되었다는 것을 알려주는 함수
                // 이전 문자열에서 before 길이만큼 바뀌었다는 것을 알려준다.
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                // s 내의 어느 문자열이 변경되었음을 알려주기 위해 호출되는 함수
                // 조심해야 할 것은 s를 변경할 시에 다시 재호출이 되므로 무한루프에 빠질 가능성이 생김
                @Override
                public void afterTextChanged(Editable s) {
                    //텍스트가 수정 될 때마다 데이터 수집에 저장
                    Log.e("tag", "index=" + etFocusPos + ",save=" + s.toString());
                    etTextAry.put(etFocusPos, s.toString());
                    pro_text1[etFocusPos] = s.toString();
                }
            };

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemLayout = LayoutInflater.from(RecWrite.this).inflate(
                        R.layout.cok_procedure, parent, false);
                return new ItemHolder(itemLayout);
            }

            @Override
            public synchronized void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
                Log.e("tag", "바인딩 Holder,index=" + i);
                final int position = i;
                //이미지 구현 부분
                ItemHolder viewHolder = (ItemHolder) holder;
                //((ItemHolder) holder).getPro_iv().setImageBitmap(imageBitmap);




                final ItemHolder VH = (ItemHolder)holder;
                viewHolder.pro_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pro_adapter.notifyItemRemoved(position);
                        pro_list -= 1;
                        pro_adapter.notifyItemRangeChanged(position, pro_list);
                    }
                });
                viewHolder.pro_iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final CharSequence[] items = {"사진촬영", "사진로드"};
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                        // 제목셋팅
                        alertDialogBuilder.setTitle("대표 이미지");
                        alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                // 프로그램을 종료한다
                                //사진촬영
                                if( id == 0){
                                    try{
                                        file = createFile();
                                        Log.d("FilePath ", file.getAbsolutePath());

                                    }catch(Exception e){
                                        Log.d("Sub1Add", "Something Wrong", e);
                                    }

                                    //VH.pro_iv.setVisibility(View.VISIBLE);

                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                                FileProvider.getUriForFile(getApplicationContext(),
                                                        getApplicationContext().getPackageName() + ".fileprovider", file));
                                        Log.d("sub1:appId", getApplicationContext().getPackageName());
                                    }else {
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                    }

                                    if (intent.resolveActivity(getPackageManager()) != null) {
                                        startActivityForResult(intent, CAMERA_REQUEST);
                                    }

                                    //사진로드
                                }else {
                                    VH.pro_iv.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent();
                                    intent.setType("image/*");
                                    intent.setAction(Intent.ACTION_PICK);
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_QQ );
                                    //startActivityForResult(Intent.createChooser(intent, "Select Picture"), (PRO_REQUEST + position) );
                                    //Log.d("recylcerviewimage", "onClick: " + (PRO_REQUEST + position) );
                                }

                                //Toast.makeText(getApplicationContext(),items[id] + " 선택했습니다.",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        // 다이얼로그 생성
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // 다이얼로그 보여주기
                        alertDialog.show();
                    }
                });
                viewHolder.pro_tv.setText(position + 1 + "");
                viewHolder.pro_et.setText(etTextAry.get(position));
                viewHolder.pro_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) {
                            etFocusPos = position;
                            Log.e("tag", "etFocusPos 초점 선택-" + etFocusPos);
                        }
                    }
                });
            }

            @Override
            public int getItemCount() {
                procadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pro_list += 1;
                        notifyItemInserted(pro_list);
                    }
                });
                return pro_list;
            }

            @Override
            public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
                super.onViewDetachedFromWindow(holder);
                Log.e("tag", "숨기기 item=" + holder.getAdapterPosition());
                ItemHolder viewHolder = (ItemHolder) holder;
                viewHolder.pro_et.removeTextChangedListener(textWatcher);
                viewHolder.pro_et.clearFocus();
                if (etFocusPos == holder.getAdapterPosition()) {
                    inputMethodManager.hideSoftInputFromWindow(((ItemHolder) holder).pro_et.getWindowToken(), 0);
                }
            }

            @Override
            public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
                super.onViewAttachedToWindow(holder);
                Log.e("tag", "디스플레이 item=" + holder.getAdapterPosition());
                ItemHolder viewHolder = (ItemHolder) holder;
                viewHolder.pro_et.addTextChangedListener(textWatcher);
                if (etFocusPos == holder.getAdapterPosition()) {
                    viewHolder.pro_et.requestFocus();
                    viewHolder.pro_et.setSelection(viewHolder.pro_et.getText().length());
                }
            }

             class  ItemHolder extends RecyclerView.ViewHolder {
                public TextView pro_tv;
                public EditText pro_et;
                public ImageView pro_iv;
                public ImageView pro_delete;

                public ItemHolder(View itemView) {
                    super(itemView);
                    pro_tv = (TextView) itemView.findViewById(R.id.pro_tv);
                    pro_et = (EditText) itemView.findViewById(R.id.pro_et);
                    pro_iv = (ImageView) itemView.findViewById(R.id.pro_iv);
                    pro_delete = (ImageView) itemView.findViewById(R.id.pro_delete);
                }

             }
        }); //setAdapter
         */

        /*procrecycle = findViewById(R.id.procrecycle);
        procadd = findViewById(R.id.procAdd);
        procadapter = new ProcedureAdapter(Context context, int orientation);
        procadapter.addItem(new ProcedureDTO(""+(procadapter.getItemCount()+1),"예) 준비된 양념으로 먼저 고기를 조물조물 재워둡니다.",R.drawable.pic_none3));
        procadapter.addItem(new ProcedureDTO(""+(procadapter.getItemCount()+1),"예) 고기가 반쯤 익어갈때 양파와 함께 볶습니다.",R.drawable.pic_none3));
        procadapter.addItem(new ProcedureDTO(""+(procadapter.getItemCount()+1),"예) 그 사이 양파와 버섯, 대파도 썰어서 준비하세요.",R.drawable.pic_none3));
        GridLayoutManager linearLayoutManager2 = new GridLayoutManager(this,1);
        procrecycle.setLayoutManager(linearLayoutManager2);*/

        /*//조리순서 액티비티
        procrecycle.setAdapter(procadapter);
        procadd.setOnClickListener(this);*/
/*

        //Swipe delete 부분 왼쪽으로 밀어서 삭제
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int i) {
                int position = target.getAdapterPosition();
                stuffadapter.removeItem(position);
                stuffadapter.notifyDataSetChanged();

            }
        });
        ItemTouchHelper helper1 = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int i) {
                int position = target.getAdapterPosition();
                spicadapter.removeItem(position);
                spicadapter.notifyDataSetChanged();

            }
        });
        ItemTouchHelper helper2 = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int i) {
                int position = target.getAdapterPosition();

                pro_adapter.notifyDataSetChanged();

            }
        });

        //swipe recyclerview 에 삽입하는곳
        helper.attachToRecyclerView(stuffrecycle);
        helper1.attachToRecyclerView(spicrecycle);
        helper2.attachToRecyclerView(procrecycle);


        */
        //요리순서
        pro_delete0 = findViewById(R.id.pro_delete0);
        pro_delete1 = findViewById(R.id.pro_delete1);
        pro_delete2 = findViewById(R.id.pro_delete2);
        pro_delete3 = findViewById(R.id.pro_delete3);
        pro_delete4 = findViewById(R.id.pro_delete4);
        pro_delete5 = findViewById(R.id.pro_delete5);
        pro_delete6 = findViewById(R.id.pro_delete6);
        pro_delete7 = findViewById(R.id.pro_delete7);
        pro_delete8 = findViewById(R.id.pro_delete8);
        pro_delete9 = findViewById(R.id.pro_delete9);
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
        frameLayout0 = findViewById(R.id.frameLayout0);
        frameLayout1 = findViewById(R.id.frameLayout1);
        frameLayout2 = findViewById(R.id.frameLayout2);
        frameLayout3 = findViewById(R.id.frameLayout3);
        frameLayout4 = findViewById(R.id.frameLayout4);
        frameLayout5 = findViewById(R.id.frameLayout5);
        frameLayout6 = findViewById(R.id.frameLayout6);
        frameLayout7 = findViewById(R.id.frameLayout7);
        frameLayout8 = findViewById(R.id.frameLayout8);
        frameLayout9 = findViewById(R.id.frameLayout9);
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

        pro_iv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // 제목셋팅
                alertDialogBuilder.setTitle("요리순서 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if( id == 0){
                            try{
                                file = createFile();
                                Log.d("FilePath ", file.getAbsolutePath());
                            }catch(Exception e){
                                Log.d("Sub1Add", "Something Wrong", e);
                            }
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        FileProvider.getUriForFile(getApplicationContext(),
                                                getApplicationContext().getPackageName() + ".fileprovider", file));
                                Log.d("sub1:appId", getApplicationContext().getPackageName());
                            }else {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, PRO_REQUEST0);
                            }
                        }else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_REQUEST0_LOAD);
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        pro_iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // 제목셋팅
                alertDialogBuilder.setTitle("요리순서 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if( id == 0){
                            try{
                                file = createFile();
                                Log.d("FilePath ", file.getAbsolutePath());
                            }catch(Exception e){
                                Log.d("Sub1Add", "Something Wrong", e);
                            }
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        FileProvider.getUriForFile(getApplicationContext(),
                                                getApplicationContext().getPackageName() + ".fileprovider", file));
                                Log.d("sub1:appId", getApplicationContext().getPackageName());
                            }else {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, PRO_REQUEST1);
                            }
                        }else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_REQUEST1_LOAD);
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        pro_iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("요리순서 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if( id == 0){
                            try{
                                file = createFile();
                                Log.d("FilePath ", file.getAbsolutePath());
                            }catch(Exception e){
                                Log.d("Sub1Add", "Something Wrong", e);
                            }
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        FileProvider.getUriForFile(getApplicationContext(),
                                                getApplicationContext().getPackageName() + ".fileprovider", file));
                                Log.d("sub1:appId", getApplicationContext().getPackageName());
                            }else {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, PRO_REQUEST2);
                            }
                        }else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_REQUEST2_LOAD);
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        pro_iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("요리순서 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if( id == 0){
                            try{
                                file = createFile();
                                Log.d("FilePath ", file.getAbsolutePath());
                            }catch(Exception e){
                                Log.d("Sub1Add", "Something Wrong", e);
                            }
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        FileProvider.getUriForFile(getApplicationContext(),
                                                getApplicationContext().getPackageName() + ".fileprovider", file));
                                Log.d("sub1:appId", getApplicationContext().getPackageName());
                            }else {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, PRO_REQUEST3);
                            }
                        }else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_REQUEST3_LOAD);
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        pro_iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("요리순서 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if( id == 0){
                            try{
                                file = createFile();
                                Log.d("FilePath ", file.getAbsolutePath());
                            }catch(Exception e){
                                Log.d("Sub1Add", "Something Wrong", e);
                            }
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        FileProvider.getUriForFile(getApplicationContext(),
                                                getApplicationContext().getPackageName() + ".fileprovider", file));
                                Log.d("sub1:appId", getApplicationContext().getPackageName());
                            }else {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, PRO_REQUEST4);
                            }
                        }else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_REQUEST4_LOAD);

                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        pro_iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("요리순서 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if( id == 0){
                            try{
                                file = createFile();
                                Log.d("FilePath ", file.getAbsolutePath());
                            }catch(Exception e){
                                Log.d("Sub1Add", "Something Wrong", e);
                            }
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        FileProvider.getUriForFile(getApplicationContext(),
                                                getApplicationContext().getPackageName() + ".fileprovider", file));
                                Log.d("sub1:appId", getApplicationContext().getPackageName());
                            }else {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, PRO_REQUEST5);
                            }
                        }else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_REQUEST5_LOAD);
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        pro_iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("요리순서 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if( id == 0){
                            try{
                                file = createFile();
                                Log.d("FilePath ", file.getAbsolutePath());
                            }catch(Exception e){
                                Log.d("Sub1Add", "Something Wrong", e);
                            }
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        FileProvider.getUriForFile(getApplicationContext(),
                                                getApplicationContext().getPackageName() + ".fileprovider", file));
                                Log.d("sub1:appId", getApplicationContext().getPackageName());
                            }else {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, PRO_REQUEST6);
                            }
                        }else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_REQUEST6_LOAD);
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        pro_iv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("요리순서 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if( id == 0){
                            try{
                                file = createFile();
                                Log.d("FilePath ", file.getAbsolutePath());
                            }catch(Exception e){
                                Log.d("Sub1Add", "Something Wrong", e);
                            }
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        FileProvider.getUriForFile(getApplicationContext(),
                                                getApplicationContext().getPackageName() + ".fileprovider", file));
                                Log.d("sub1:appId", getApplicationContext().getPackageName());
                            }else {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, PRO_REQUEST7);
                            }
                        }else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_REQUEST7_LOAD);
                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        pro_iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("요리순서 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if( id == 0){
                            try{
                                file = createFile();
                                Log.d("FilePath ", file.getAbsolutePath());
                            }catch(Exception e){
                                Log.d("Sub1Add", "Something Wrong", e);
                            }
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        FileProvider.getUriForFile(getApplicationContext(),
                                                getApplicationContext().getPackageName() + ".fileprovider", file));
                                Log.d("sub1:appId", getApplicationContext().getPackageName());
                            }else {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, PRO_REQUEST8);
                            }
                        }else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_REQUEST8_LOAD);

                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        pro_iv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"사진촬영", "사진로드"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("요리순서 이미지");
                alertDialogBuilder.setItems(items,  new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if( id == 0){
                            try{
                                file = createFile();
                                Log.d("FilePath ", file.getAbsolutePath());
                            }catch(Exception e){
                                Log.d("Sub1Add", "Something Wrong", e);
                            }
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // API24 이상 부터
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                        FileProvider.getUriForFile(getApplicationContext(),
                                                getApplicationContext().getPackageName() + ".fileprovider", file));
                                Log.d("sub1:appId", getApplicationContext().getPackageName());
                            }else {
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                            }
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(intent, PRO_REQUEST9);
                            }
                        }else {
                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_PICK);
                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PRO_REQUEST9_LOAD);

                        }
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        procadd.setOnClickListener(this);

        pro_delete0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout0.setVisibility(View.GONE);
                pro_et0.setText("");
                if( frameLayout1.getVisibility() == View.VISIBLE){
                    pro_tv1.setText("1"); pro_tv2.setText("2"); pro_tv3.setText("3"); pro_tv4.setText("4"); pro_tv5.setText("5"); pro_tv6.setText("6"); pro_tv7.setText("7"); pro_tv8.setText("8"); pro_tv9.setText("9");
                }
            }
        });

        pro_delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout1.setVisibility(View.GONE);
                pro_et1.setText("");
                if( frameLayout2.getVisibility() == View.VISIBLE){
                    pro_tv2.setText("2"); pro_tv3.setText("3"); pro_tv4.setText("4"); pro_tv5.setText("5"); pro_tv6.setText("6"); pro_tv7.setText("7"); pro_tv8.setText("8"); pro_tv9.setText("9");
                }
            }
        });

        pro_delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout2.setVisibility(View.GONE);
                pro_et2.setText("");
                if( frameLayout3.getVisibility() == View.VISIBLE){
                    pro_tv3.setText("3"); pro_tv4.setText("4"); pro_tv5.setText("5"); pro_tv6.setText("6"); pro_tv7.setText("7"); pro_tv8.setText("8"); pro_tv9.setText("9");
                }
            }
        });

        pro_delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout3.setVisibility(View.GONE);
                pro_et3.setText("");
                if( frameLayout4.getVisibility() == View.VISIBLE){
                    pro_tv4.setText("4"); pro_tv5.setText("5"); pro_tv6.setText("6"); pro_tv7.setText("7"); pro_tv8.setText("8"); pro_tv9.setText("9");
                }
            }
        });

        pro_delete4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout4.setVisibility(View.GONE);
                pro_et4.setText("");
                if( frameLayout5.getVisibility() == View.VISIBLE){
                    pro_tv5.setText("5"); pro_tv6.setText("6"); pro_tv7.setText("7"); pro_tv8.setText("8"); pro_tv9.setText("9");
                }
            }
        });

        pro_delete5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout5.setVisibility(View.GONE);
                pro_et5.setText("");
                if( frameLayout6.getVisibility() == View.VISIBLE){
                    pro_tv6.setText("6"); pro_tv7.setText("7"); pro_tv8.setText("8"); pro_tv9.setText("9");
                }
            }
        });

        pro_delete6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout6.setVisibility(View.GONE);
                pro_et6.setText("");
                if( frameLayout7.getVisibility() == View.VISIBLE){
                    pro_tv7.setText("7"); pro_tv8.setText("8"); pro_tv9.setText("9");
                }
            }
        });

        pro_delete7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout7.setVisibility(View.GONE);
                pro_et7.setText("");
                if( frameLayout8.getVisibility() == View.VISIBLE){
                    pro_tv8.setText("8"); pro_tv9.setText("9");
                }
            }
        });

        pro_delete8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout8.setVisibility(View.GONE);
                pro_et8.setText("");
                pro_tv8.setText("");
                if( frameLayout9.getVisibility() == View.VISIBLE){
                    pro_tv9.setText("9");
                }
            }
        });

        pro_delete9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout9.setVisibility(View.GONE);
                pro_et9.setText("");
                pro_tv9.setText("");
            }
        });

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

        cok_portion_tv = findViewById(R.id.cok_portion_tv);
        cok_time_tv = findViewById(R.id.cok_time_tv);
        cok_degree_tv = findViewById(R.id.cok_degree_tv);
        cok_portion_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(1);
            }
        });
        cok_time_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(2);
            }
        });
        cok_degree_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(3);
            }
        });


        //레시피 저장
        rec_save = findViewById(R.id.rec_save);
        rec_save.setOnClickListener(this);



    }


/*    private void openRecListActivity() {
        Intent intent = new Intent(this, RecListActivity.class);
        startActivity(intent);
    }*/

    //이미지 관련된것
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if (requestCode == PRO_REQUEST0) {
                try {
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                    if (newBitmap != null) {
                        pro_iv0.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }

                    imageRealPathA0 = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA0.split("/")[imageRealPathA0.split("/").length - 1];
                    imagepathA0 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PRO_REQUEST0_LOAD) {

                try {
                    String path = "";
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        path = getPathFromURI(selectedImageUri);
                    }
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                    if (newBitmap != null) {
                        pro_iv0.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA0 = path;
                    Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA0);
                    String uploadFileName = imageRealPathA0.split("/")[imageRealPathA0.split("/").length - 1];
                    imagepathA0 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == PRO_REQUEST1) {
                try {
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                    if (newBitmap != null) {
                        pro_iv1.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }

                    imageRealPathA1 = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA1.split("/")[imageRealPathA1.split("/").length - 1];
                    imagepathA1 = ipConfig + "/resources/"+ uploadFileName;



                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PRO_REQUEST1_LOAD) {

                try {
                    String path = "";
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        path = getPathFromURI(selectedImageUri);
                    }
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                    if (newBitmap != null) {
                        pro_iv1.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA1 = path;
                    Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA1);
                    String uploadFileName = imageRealPathA1.split("/")[imageRealPathA1.split("/").length - 1];
                    imagepathA1 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == PRO_REQUEST2) {
                try {
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                    if (newBitmap != null) {
                        pro_iv2.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }

                    imageRealPathA2 = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA2.split("/")[imageRealPathA2.split("/").length - 1];
                    imagepathA2 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PRO_REQUEST2_LOAD) {

                try {
                    String path = "";
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        path = getPathFromURI(selectedImageUri);
                    }
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                    if (newBitmap != null) {
                        pro_iv2.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA2 = path;
                    Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA2);
                    String uploadFileName = imageRealPathA2.split("/")[imageRealPathA2.split("/").length - 1];
                    imagepathA2 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == PRO_REQUEST3) {
                try {
                    // 이미지 돌리기 및 리사이즈
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                    if (newBitmap != null) {
                        pro_iv3.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }

                    imageRealPathA3 = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA3.split("/")[imageRealPathA3.split("/").length - 1];
                    imagepathA3 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PRO_REQUEST3_LOAD) {

                try {
                    String path = "";
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        path = getPathFromURI(selectedImageUri);
                    }
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                    if (newBitmap != null) {
                        pro_iv3.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA3 = path;
                    Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA3);
                    String uploadFileName = imageRealPathA3.split("/")[imageRealPathA3.split("/").length - 1];
                    imagepathA3 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == PRO_REQUEST4) {
                try {
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                    if (newBitmap != null) {
                        pro_iv4.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }

                    imageRealPathA4 = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA4.split("/")[imageRealPathA4.split("/").length - 1];
                    imagepathA4 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PRO_REQUEST4_LOAD) {

                try {
                    String path = "";
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        path = getPathFromURI(selectedImageUri);
                    }
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                    if (newBitmap != null) {
                        pro_iv4.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA4 = path;
                    Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA4);
                    String uploadFileName = imageRealPathA4.split("/")[imageRealPathA4.split("/").length - 1];
                    imagepathA4 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == PRO_REQUEST5) {
                try {
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                    if (newBitmap != null) {
                        pro_iv5.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }

                    imageRealPathA5 = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA5.split("/")[imageRealPathA5.split("/").length - 1];
                    imagepathA5 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PRO_REQUEST5_LOAD) {

                try {
                    String path = "";
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        path = getPathFromURI(selectedImageUri);
                    }
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                    if (newBitmap != null) {
                        pro_iv5.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA5 = path;
                    Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA5);
                    String uploadFileName = imageRealPathA5.split("/")[imageRealPathA5.split("/").length - 1];
                    imagepathA5 = ipConfig + "/resources/"+ uploadFileName;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == PRO_REQUEST6) {
                try {
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                    if (newBitmap != null) {
                        pro_iv6.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }

                    imageRealPathA6 = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA6.split("/")[imageRealPathA6.split("/").length - 1];
                    imagepathA6 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PRO_REQUEST6_LOAD) {

                try {
                    String path = "";
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        path = getPathFromURI(selectedImageUri);
                    }
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                    if (newBitmap != null) {
                        pro_iv6.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA6 = path;
                    Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA6);
                    String uploadFileName = imageRealPathA6.split("/")[imageRealPathA6.split("/").length - 1];
                    imagepathA6 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == PRO_REQUEST7) {
                try {
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                    if (newBitmap != null) {
                        pro_iv7.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA7 = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA7.split("/")[imageRealPathA7.split("/").length - 1];
                    imagepathA7 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PRO_REQUEST7_LOAD) {

                try {
                    String path = "";
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        path = getPathFromURI(selectedImageUri);
                    }
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                    if (newBitmap != null) {
                        pro_iv7.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA7 = path;
                    Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA7);
                    String uploadFileName = imageRealPathA7.split("/")[imageRealPathA7.split("/").length - 1];
                    imagepathA7 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == PRO_REQUEST8) {
                try {
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                    if (newBitmap != null) {
                        pro_iv8.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }

                    imageRealPathA8 = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA8.split("/")[imageRealPathA8.split("/").length - 1];
                    imagepathA8 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PRO_REQUEST8_LOAD) {

                try {
                    String path = "";
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        path = getPathFromURI(selectedImageUri);
                    }
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                    if (newBitmap != null) {
                        pro_iv8.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA8 = path;
                    Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA8);
                    String uploadFileName = imageRealPathA8.split("/")[imageRealPathA8.split("/").length - 1];
                    imagepathA8 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (requestCode == PRO_REQUEST9) {
                try {
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                    if (newBitmap != null) {
                        pro_iv9.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }

                    imageRealPathA9 = file.getAbsolutePath();
                    String uploadFileName = imageRealPathA9.split("/")[imageRealPathA9.split("/").length - 1];
                    imagepathA9 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == PRO_REQUEST9_LOAD) {

                try {
                    String path = "";
                    Uri selectedImageUri = data.getData();
                    if (selectedImageUri != null) {
                        path = getPathFromURI(selectedImageUri);
                    }
                    Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                    if (newBitmap != null) {
                        pro_iv9.setImageBitmap(newBitmap);
                    } else {
                        Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                    }
                    imageRealPathA9 = path;
                    Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA9);
                    String uploadFileName = imageRealPathA9.split("/")[imageRealPathA9.split("/").length - 1];
                    imagepathA9 = ipConfig + "/resources/"+ uploadFileName;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }//PRO_REQUEST_LOAD


        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {

            try {
                // 이미지 돌리기 및 리사이즈
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(file.getAbsolutePath());
                if (newBitmap != null) {
                    rec_ImageView.setImageBitmap(newBitmap);
                } else {
                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                }

                imageRealPathA = file.getAbsolutePath();
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imagepath = ipConfig + "/resources/"+ uploadFileName;



            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == LOAD_IMAGE && resultCode == RESULT_OK) {

            try {
                String path = "";
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    path = getPathFromURI(selectedImageUri);
                }
                Bitmap newBitmap = CommonMethod.imageRotateAndResize(path);
                if (newBitmap != null) {
                    rec_ImageView.setImageBitmap(newBitmap);
                } else {
                    Toast.makeText(this, "이미지가 null 입니다...", Toast.LENGTH_SHORT).show();
                }
                imageRealPathA = path;
                Log.d("Sub1Add", "imageFilePathA Path : " + imageRealPathA);
                String uploadFileName = imageRealPathA.split("/")[imageRealPathA.split("/").length - 1];
                imagepath = ipConfig + "/resources/"+ uploadFileName;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //요리순서 이미지 부분
    }

    // Get the real path from the URI
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private File createFile() throws IOException {

        String imageFileName = "My" + tmpDateFormat.format(new Date()) + ".jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir, imageFileName);

        return curFile;
    }

    //=================================================================================================================================


    //권한설정
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            /*Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();*/
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }
    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    */





    /*//handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch ( requestCode ) {
            case PERMISSION_CODE: {
                if ( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    //permission was granted
                    pickImageFromGallery();
                }else {
                    //permission was denied
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }*/


    //카테고리 선택 됐을 경우 Toast
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        switch (adapterView.getId()) {
            case R.id.cat1:
                if (adapterView.getSelectedItemPosition() > 0) {
                    /*cat1_c = String.valueOf(adapterView.getSelectedItemPosition());*/
                    cat1_c = adapterView.getItemAtPosition(position).toString();
                }
                break;
            case R.id.cat2:
                if (adapterView.getSelectedItemPosition() > 0) {
                    /*cat2_c = String.valueOf(adapterView.getSelectedItemPosition());*/
                    cat2_c = adapterView.getItemAtPosition(position).toString();
                }
                break;
            case R.id.cat3:
                if (adapterView.getSelectedItemPosition() > 0) {
                    /*cat3_c = String.valueOf(adapterView.getSelectedItemPosition());*/
                    cat3_c = adapterView.getItemAtPosition(position).toString();
                }
                break;
            case R.id.cat4:
                if (adapterView.getSelectedItemPosition() > 0) {
                    /*cat4_c = String.valueOf(adapterView.getSelectedItemPosition());*/
                    cat4_c = adapterView.getItemAtPosition(position).toString();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    //setOnClickListener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rec_save:
                Log.d("taginsert", "넘어오는지 테스트 " + imagepath +  ", " + imagepath1 + ", "+ imagepath2 + ", " + imagepath3 + ", " + imagepath4);
                if(fileSize <= 30000000) { // 파일크기가 30메가 보다 작아야 업로드 할수 있음
                    String title2 = etTitle.getText().toString();
                    String subtitle2 = etSubTitle.getText().toString();
                    String portion, time, degree;

                    String material_name0, material_name1, material_name2, material_name3, material_name4, material_name5, material_name6, material_name7, material_name8, material_name9;
                    String material_unit0, material_unit1, material_unit2, material_unit3, material_unit4, material_unit5, material_unit6, material_unit7, material_unit8, material_unit9;
                    String text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;

                    title = title2;
                    subtitle = subtitle2;
                    portion = portion_c;
                    time = time_c;
                    degree = degree_c;
                    material_name0 = stuff_text1[0];
                    material_name1 = stuff_text1[1];
                    material_name2 = stuff_text1[2];
                    material_name3 = stuff_text1[3];
                    material_name4 = stuff_text1[4];
                    material_name5 = spic_text1[0];
                    material_name6 = spic_text1[1];
                    material_name7 = spic_text1[2];
                    material_name8 = spic_text1[3];
                    material_name9 = spic_text1[4];

                    material_unit0 = stuff_text2[0];
                    material_unit1 = stuff_text2[1];
                    material_unit2 = stuff_text2[2];
                    material_unit3 = stuff_text2[3];
                    material_unit4 = stuff_text2[4];
                    material_unit5 = spic_text2[0];
                    material_unit6 = spic_text2[1];
                    material_unit7 = spic_text2[2];
                    material_unit8 = spic_text2[3];
                    material_unit9 = spic_text2[4];

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

                    Log.d("taginsert", "onClick: ");


                    String imageRealPathM1 = imageRealPathM[0];
                    String imageRealPathM2 = imageRealPathM[1];
                    String imageRealPathM3 = imageRealPathM[2];
                    String imageRealPathM4 = imageRealPathM[3];


                    Log.d("insert", title + "," + subtitle + cat1_c + "," + cat2_c + "," + cat3_c + "," + cat4_c + "," + portion_c + "," + time_c + "," + degree_c);

                    ListInsert insert = new ListInsert(title, subtitle, cat1_c, cat2_c, cat3_c, cat4_c, portion, time, degree,
                            imagepath,imagepath1,imagepath2,imagepath3,imagepath4, imageRealPathA, imageRealPathM1,imageRealPathM2,imageRealPathM3,imageRealPathM4,
                            material_name0, material_name1, material_name2, material_name3, material_name4, material_name5, material_name6, material_name7, material_name8, material_name9, material_unit0, material_unit1, material_unit2, material_unit3, material_unit4, material_unit5, material_unit6, material_unit7, material_unit8, material_unit9,
                            text1, text2, text3, text4, text5, text6, text7, text8, text9, text10,
                            imageRealPathA0, imagepathA0,imageRealPathA1, imagepathA1,imageRealPathA2, imagepathA2,imageRealPathA3, imagepathA3,imageRealPathA4, imagepathA4,imageRealPathA5, imagepathA5,imageRealPathA6, imagepathA6,imageRealPathA7, imagepathA7
                            ,imageRealPathA8, imagepathA8,imageRealPathA9, imagepathA9,id);
                    insert.execute();


                    Intent showIntent = new Intent(getApplicationContext(), SubActivity.class);
                    showIntent.putExtra("cat1", cat1.getSelectedItem().toString());
                    showIntent.putExtra("cat2", cat2.getSelectedItem().toString());
                    showIntent.putExtra("cat3", cat3.getSelectedItem().toString());
                    showIntent.putExtra("cat4", cat4.getSelectedItem().toString());
                    showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |   // 이 엑티비티 플래그를 사용하여 엑티비티를 호출하게 되면 새로운 태스크를 생성하여 그 태스크안에 엑티비티를 추가하게 됩니다. 단, 기존에 존재하는 태스크들중에 생성하려는 엑티비티와 동일한 affinity(관계, 유사)를 가지고 있는 태스크가 있다면 그곳으로 새 엑티비티가 들어가게됩니다.
                            Intent.FLAG_ACTIVITY_SINGLE_TOP | // 엑티비티를 호출할 경우 호출된 엑티비티가 현재 태스크의 최상단에 존재하고 있었다면 새로운 인스턴스를 생성하지 않습니다. 예를 들어 ABC가 엑티비티 스택에 존재하는 상태에서 C를 호출하였다면 여전히 ABC가 존재하게 됩니다.
                            Intent.FLAG_ACTIVITY_CLEAR_TOP); // 만약에 엑티비티스택에 호출하려는 엑티비티의 인스턴스가 이미 존재하고 있을 경우에 새로운 인스턴스를 생성하는 것 대신에 존재하고 있는 엑티비티를 포그라운드로 가져옵니다. 그리고 엑티비티스택의 최상단 엑티비티부터 포그라운드로 가져올 엑티비티까지의 모든 엑티비티를 삭제합니다.
                    startActivity(showIntent);

                    finish();
                }
                break;

            case R.id.procAdd:
                if(frameLayout8.getVisibility() == View.VISIBLE){
                    pcnt = 9;
                }else if(frameLayout7.getVisibility() == View.VISIBLE){
                    pcnt = 8;
                }else if(frameLayout6.getVisibility() == View.VISIBLE){
                    pcnt = 7;
                }else if(frameLayout5.getVisibility() == View.VISIBLE){
                    pcnt = 6;
                }else if(frameLayout4.getVisibility() == View.VISIBLE){
                    pcnt = 5;
                }else if(frameLayout3.getVisibility() == View.VISIBLE){
                    pcnt = 4;
                }else if(frameLayout2.getVisibility() == View.VISIBLE){
                    pcnt = 3;
                }else if(frameLayout1.getVisibility() == View.VISIBLE){
                    pcnt = 2;
                }else if(frameLayout0.getVisibility() == View.VISIBLE){
                    pcnt = 1;
                }

                if(pcnt == 0){
                    frameLayout0.setVisibility(View.VISIBLE);
                    pcnt++;
                }else if ( pcnt == 1){
                    frameLayout1.setVisibility(View.VISIBLE);
                    pcnt++;
                }else if ( pcnt == 2){
                    frameLayout2.setVisibility(View.VISIBLE);
                    pcnt++;
                }else if ( pcnt == 3){
                    frameLayout3.setVisibility(View.VISIBLE);
                    pcnt++;
                }else if ( pcnt == 4){
                    frameLayout4.setVisibility(View.VISIBLE);
                    pcnt++;
                }else if ( pcnt == 5){
                    frameLayout5.setVisibility(View.VISIBLE);
                    pcnt++;
                }else if ( pcnt == 6){
                    frameLayout6.setVisibility(View.VISIBLE);
                    pcnt++;
                }else if ( pcnt == 7){
                    frameLayout7.setVisibility(View.VISIBLE);
                    pcnt++;
                }else if ( pcnt == 8){
                    frameLayout8.setVisibility(View.VISIBLE);
                    pcnt++;
                }else if ( pcnt == 9){
                    frameLayout9.setVisibility(View.VISIBLE);
                    pcnt++;
                }

                break;
        }
    }

    //카테고리 종류별 넣는탭
    void show(final int cnt) {
        String[] cok_portion1 = new String[0];
        if (cnt == 1) {
            cok_portion1 = getResources().getStringArray(R.array.cok_portion);
        } else if (cnt == 2) {
            cok_portion1 = getResources().getStringArray(R.array.cok_time);
        } else if (cnt == 3) {
            cok_portion1 = getResources().getStringArray(R.array.cok_degree);
        }


        // 카테고리 stringarray 배열 하나씩 넣기
        final List<String> ListItems = new ArrayList<>();
        for (int i = 0; i < cok_portion1.length; i++) {
            ListItems.add(cok_portion1[i]);
        }

        final CharSequence[] items = ListItems.toArray(new String[ListItems.size()]);

        final List SelectedItems = new ArrayList();
        int defaultItem = 0;
        SelectedItems.add(defaultItem);


        //요리정보
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (cnt == 1) builder.setTitle("인원");
        else if (cnt == 2) builder.setTitle("시간");
        else if (cnt == 3) builder.setTitle("난이도");
        builder.setSingleChoiceItems(items, defaultItem,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SelectedItems.clear();
                        SelectedItems.add(which);
                    }
                });


        //요리정보 확인 눌렀을 경우
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = "";

                        if (!SelectedItems.isEmpty()) {
                            int index = (int) SelectedItems.get(0);
                            msg = ListItems.get(index);
                            if (cnt == 1) {
                                cok_portion_tv.setText(msg);
                                /*portion_c = String.valueOf(index);*/
                                portion_c = msg;
                                Toast.makeText(RecWrite.this, portion_c, Toast.LENGTH_SHORT).show();
                            } else if (cnt == 2) {
                                cok_time_tv.setText(msg);
                                /*time_c = String.valueOf(index);*/
                                time_c = msg;
                                Toast.makeText(RecWrite.this, time_c, Toast.LENGTH_SHORT).show();
                            } else if (cnt == 3) {
                                cok_degree_tv.setText(msg);
                                /*degree_c = String.valueOf(index);*/
                                degree_c = msg;
                                Toast.makeText(RecWrite.this, degree_c, Toast.LENGTH_SHORT).show();
                            }
                        }
                        /*Toast.makeText(getApplicationContext(),
                                "Items Selected.\n"+ msg , Toast.LENGTH_LONG)
                                .show();*/
                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }


    private void getAlbum(){
        //앨범호출
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        //사진을 여러개 선택할수 있도록 한다
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),  REQUEST_TAKE_ALBUM);


    }

}


