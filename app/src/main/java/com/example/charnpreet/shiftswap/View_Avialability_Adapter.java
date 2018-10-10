package com.example.charnpreet.shiftswap;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class View_Avialability_Adapter extends RecyclerView.Adapter<ViewAvailability>{
    View view;
    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewAvailability onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from((viewGroup.getContext()));
        view = inflater.inflate(R.layout.viewavailability, viewGroup, false);

        return new ViewAvailability(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewAvailability viewAvailability, int i) {
        viewAvailability.day.setText("monday");
    }


    @Override
    public int getItemCount() {
        return 1;
    }
}
