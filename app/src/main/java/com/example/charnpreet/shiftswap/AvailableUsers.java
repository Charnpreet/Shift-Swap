package com.example.charnpreet.shiftswap;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AvailableUsers extends Fragment {
View rootView;
RecyclerView recyclerView;
private  static AvailableUsers users = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.available_users, container,false);
        InitRecyclerView(rootView);
        return  rootView;
    }

    public static AvailableUsers getUsers() {
        if(users==null){
            users = new AvailableUsers();
        }
        return users;
    }
    private void InitRecyclerView(View view){
        recyclerView = view.findViewById(R.id.users_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new Available_User_Adapater());
    }
}
