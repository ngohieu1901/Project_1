package com.example.project_1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.project_1.ADAPTER.LanguageADAPTER;
import com.example.project_1.DTO.LanguageDTO;
import com.example.project_1.R;

import java.util.ArrayList;

public class LanguageActivity extends AppCompatActivity  {
    RecyclerView rc_lang;
    LanguageADAPTER adapter;
    ArrayList<LanguageDTO> list;
    private boolean itemSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.language));
        }

        rc_lang = findViewById(R.id.rc_lang);
        list = new ArrayList<>();
        list.add(new LanguageDTO(R.drawable.anh,"English",false));
        list.add(new LanguageDTO(R.drawable.tbn_2,"Spainish",false));
        list.add(new LanguageDTO(R.drawable.bdn_2,"Portuguese",false));
        list.add(new LanguageDTO(R.drawable.duc_2,"German",false));
        list.add(new LanguageDTO(R.drawable.phap,"French",false));

        adapter = new LanguageADAPTER(this,list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rc_lang.setLayoutManager(linearLayoutManager);
        rc_lang.setAdapter(adapter);

        ImageView tv_check = findViewById(R.id.tv_check);

        rc_lang.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    // Lấy vị trí mục được chọn
                    int position = rc_lang.getChildAdapterPosition(rv.findChildViewUnder(e.getX(), e.getY()));

                    // Cập nhật trạng thái của TextView dựa trên việc có mục nào được chọn hay không
                    if (position == RecyclerView.NO_POSITION || position == 2 || position ==3 ) {
                        tv_check.setVisibility(View.INVISIBLE);
                    } else {
                        tv_check.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LanguageActivity.this, MainManageFile.class);
                startActivity(intent);
                finish();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.check){
//            Intent intent = new Intent(LanguageActivity.this, MainManageFile.class);
//            startActivity(intent);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}