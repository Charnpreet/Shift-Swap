package com.example.charnpreet.shiftswap;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Availability extends Fragment {
    private  static Availability availability = null;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter adapter;
    View view;

    public static Availability getAvailability() {
        if(availability==null){
            availability= new Availability();
        }
        return availability;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          view =inflater.inflate(R.layout.availability,container,false);
        SettingUpTabLayout();
        SettingUpViewPAger();
        SettingUpListenorsOnViewPager();

        return view;
    }
    private void SettingUpTabLayout(){
        tabLayout = view.findViewById(R.id.tabLayout);

    }
    private void SettingUpViewPAger(){
        viewPager = view.findViewById(R.id.viewpager);
        adapter = new PageAdapter(getChildFragmentManager(),tabLayout.getTabCount());
       viewPager.setAdapter(adapter);
    }
    private void SettingUpListenorsOnViewPager(){
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }
}
