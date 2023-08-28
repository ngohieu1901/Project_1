package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.project_1.FRAGMENT.FragBookmark;
import com.example.project_1.FRAGMENT.FragHome;
import com.example.project_1.FRAGMENT.FragSetting;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainManageFile extends AppCompatActivity {
FragmentManager manager;
FragHome fragHome;
FragBookmark fragBookmark;
FragSetting fragSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manage_file);
        BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);

        fragHome = new FragHome();
        fragBookmark = new FragBookmark();
        fragSetting = new FragSetting();

        manager =getSupportFragmentManager();
        manager.beginTransaction().add(R.id.frag_container_file,fragHome).commit();

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                if(item.getItemId() == R.id.home){
                    manager.beginTransaction().replace(R.id.frag_container_file,fragHome).commit();
                } else if (item.getItemId() == R.id.bookmark) {
                    manager.beginTransaction().replace(R.id.frag_container_file,fragBookmark).commit();
                }else if (item.getItemId()  ==R.id.setting){
                    manager.beginTransaction().replace(R.id.frag_container_file,fragSetting).commit();
                }
                return true;
            }
        });
    }

}