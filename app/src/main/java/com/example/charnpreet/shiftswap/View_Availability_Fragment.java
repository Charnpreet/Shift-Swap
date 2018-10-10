package com.example.charnpreet.shiftswap;
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


public class View_Availability_Fragment extends Fragment {
    RecyclerView recyclerView;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.view_availability_fragment,container,false);
        InitRecyclerView(view);
        return view;
    }
    private void InitRecyclerView(View view){
        recyclerView = view.findViewById(R.id.viewavailability);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
           recyclerView.setAdapter(new View_Avialability_Adapter());

    }

}
