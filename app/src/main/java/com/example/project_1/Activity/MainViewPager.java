package com.example.project_1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_1.ADAPTER.ViewPagerADAPTER;
import com.example.project_1.R;

import me.relex.circleindicator.CircleIndicator;

public class MainViewPager extends AppCompatActivity {
    CircleIndicator indicator;
    ViewPager pager;
    ViewPagerADAPTER adapter;
    TextView tv_skip;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_pager);

        tv_skip = findViewById(R.id.tv_skip);
        pager = findViewById(R.id.pager);
        indicator = findViewById(R.id.indicator);
        btn_next = findViewById(R.id.btn_next);

        adapter = new ViewPagerADAPTER(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pager.setAdapter(adapter);

        indicator.setViewPager(pager);

        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pager.getCurrentItem() < 2){
                    pager.setCurrentItem(pager.getCurrentItem() + 1);
                } else if (pager.getCurrentItem() == 2) {
                    Intent intent = new Intent(MainViewPager.this,LanguageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position ==2 ){
                    tv_skip.setText("");
                    btn_next.setText("GET STARTED");
                }else {
                    tv_skip.setText("Skip");
                    btn_next.setText("NEXT");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}