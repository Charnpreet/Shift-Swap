package com.example.charnpreet.shiftswap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


public class Permanent_Availability_Fragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    Utility utility;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.enter_availability_fragment,container,false);
        utility =Utility.getUtility();
        InitRecyclerView(view);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //
        // needs to de-attach and attach fragment to make sure it always sync with database automatically
        // it was not fetch data if re-occuring availability fragment is visible at the begining when clicking
        // availability tab
        if(isVisibleToUser){
            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }

    }
    //
    //
    private void InitRecyclerView(View view){
        recyclerView = view.findViewById(R.id.viewavailability);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new Permanent_Availability_Adapter(utility.DaysOfWeek()));
    }



}
