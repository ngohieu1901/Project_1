package com.example.project_1.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project_1.ADAPTER.ViewPagerADAPTER;
import com.example.project_1.R;

public class MyCollecFragScreenSaver extends Fragment {
    ViewPager2 pager2;
    ViewPagerADAPTER adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mycollecfrag_screensaver,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager2 = view.findViewById(R.id.pager_2);
        adapter = new ViewPagerADAPTER(this);
        pager2.setAdapter(adapter);
    }
}
