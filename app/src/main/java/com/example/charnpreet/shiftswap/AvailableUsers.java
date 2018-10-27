package com.example.charnpreet.shiftswap;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class AvailableUsers extends Fragment {
View rootView;
RecyclerView recyclerView;
    ArrayList<Employee> employees=new ArrayList<>();

private  static AvailableUsers users = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.available_users, container,false);
        RecievingBundle();
        InitRecyclerView(rootView);
        return  rootView;
    }

    public static AvailableUsers getUsers() {
        if(users==null){
            users = new AvailableUsers();
        }
        return users;
    }
    private void RecievingBundle(){
        Bundle bundle = getArguments();
        employees=  bundle.getParcelableArrayList("employee");
    }
    private void InitRecyclerView(View view){
        recyclerView = view.findViewById(R.id.users_recyclerView);
        RecyclerView.LayoutManager m = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(m);
        recyclerView.setAdapter(new Available_User_Adapater(employees));
        //
        // below code addes divider to recyler view items
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),((LinearLayoutManager) m).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Available Staff");
    }
}
