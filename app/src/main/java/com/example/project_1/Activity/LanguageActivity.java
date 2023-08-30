package com.example.project_1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project_1.ADAPTER.LanguageADAPTER;
import com.example.project_1.DTO.LanguageDTO;
import com.example.project_1.R;

import java.util.ArrayList;

public class LanguageActivity extends AppCompatActivity {
    RecyclerView rc_lang;
    LanguageADAPTER adapter;
    ArrayList<LanguageDTO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.check){
            Intent intent = new Intent(LanguageActivity.this, MainManageFile.class);
            startActivity(intent);
            Toast.makeText(this, "CHECKED", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}