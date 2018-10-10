package com.example.charnpreet.shiftswap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class PageAdapter extends FragmentPagerAdapter {
    private int noOfTabs;
    View_Availability_Fragment view_availability_fragment;
    Enter_Availability_Fragment enter_availability_fragment;

    public PageAdapter(FragmentManager fm,int tabCount)
    {
        super(fm);
        noOfTabs = tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        if(i ==0){
            return view_availability_fragment = new View_Availability_Fragment();
        }else
            {

            return enter_availability_fragment = new Enter_Availability_Fragment();
        }


    }

    public View_Availability_Fragment getView_availability_fragment()
    {
        return view_availability_fragment;
    }

    @Override
    public int getCount() {
        return  noOfTabs;
    }
}
