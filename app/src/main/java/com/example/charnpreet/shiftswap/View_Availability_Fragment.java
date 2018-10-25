package com.example.charnpreet.shiftswap;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class View_Availability_Fragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    DatabaseHelper databaseHelper;
    Utility utility;
    Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.view_availability_fragment,container,false);
        utility =Utility.getUtility();
        InitRecyclerView(view);
        return view;
    }
    //
    //
    private void InitRecyclerView(View view){
        databaseHelper = new DatabaseHelper(view.getContext());
        recyclerView = view.findViewById(R.id.viewavailability);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new View_Avialability_Adapter(utility.ExecutingDaysQuerry(databaseHelper, cursor), view.getContext()));

    }



}
