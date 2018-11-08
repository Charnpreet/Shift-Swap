package com.example.charnpreet.shiftswap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class PageAdapter extends FragmentPagerAdapter {
    private int noOfTabs;
    Enter_Availability_Fragment venter_availability_fragment;
    View_Availability_Fragment view_Availability_Fragment;

    public PageAdapter(FragmentManager fm,int tabCount)
    {
        super(fm);
        noOfTabs = tabCount;
    }

    @Override
    public Fragment getItem(int i) {
        if(i ==0){
            return venter_availability_fragment = new Enter_Availability_Fragment();
        }else
            {

            return view_Availability_Fragment = new View_Availability_Fragment();
        }


    }

    public Enter_Availability_Fragment getView_availability_fragment()
    {
        return venter_availability_fragment;
    }

    @Override
    public int getCount() {
        return  noOfTabs;
    }
}
