package com.example.charnpreet.shiftswap;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class chat_fragment_for_individual extends Fragment implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageView backarrow;
    private TextView toolbarTextView;
    private RecyclerView recyclerView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat_fragment_for_individual_user, container,false);
        Init();
        return view;
    }
    private void Init(){
        toolbar = view.findViewById(R.id.chat_fragment_toolbar);
        backarrow= view.findViewById(R.id.chat_fragment_back_Arrow);
        recyclerView = view.findViewById(R.id.recyclerView_for_individual_chat_fragment);
        backarrow.setOnClickListener(this);
        toolbarTextView = view.findViewById(R.id.chat_fragment_toolbar_text_view);
        InitRecyclerView();
    }
    private void InitRecyclerView(){
        RecyclerView.LayoutManager m = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(m);
        recyclerView.setAdapter(new chat_fragment_for_individual_adapter());
    }
    @Override
    public void onClick(View view) {
        /* this calls individual_chat_Holder_Activity activity,
         *  after individual_chat_Holder_Activity shows last hosted fragment
         * */
        Intent myintent = new Intent(view.getContext(), Chat_Activity.class);
        myintent.setFlags((Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
        (view.getContext()).startActivity(myintent);
    }
}
