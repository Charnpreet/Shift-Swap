package com.example.charnpreet.shiftswap;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class chat_fragment_for_individual extends Fragment {
    private Toolbar toolbar;
    private ImageView backarrow;
    private TextView toolbarTextView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat_fragment_for_individual_user, container,false);
        return view;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void Init(){
        toolbar = view.findViewById(R.id.chat_fragment_toolbar);
        backarrow= view.findViewById(R.id.chat_fragment_back_Arrow);
        toolbarTextView = view.findViewById(R.id.chat_fragment_toolbar_text_view);
        ((MainActivity)Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

    }
}
