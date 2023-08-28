package com.example.project_1.ADAPTER;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.project_1.FRAGMENT.ScreenSaver1;
import com.example.project_1.FRAGMENT.ScreenSaver2;
import com.example.project_1.FRAGMENT.ScreenSaver3;

public class ViewPagerADAPTER extends FragmentStateAdapter {
    int soPage = 3;

    public ViewPagerADAPTER(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new ScreenSaver1();
                case 1:
                    return new ScreenSaver2();
                default:
                    return new ScreenSaver3();
            }
    }

    @Override
    public int getItemCount() {
        return soPage;
    }
}
