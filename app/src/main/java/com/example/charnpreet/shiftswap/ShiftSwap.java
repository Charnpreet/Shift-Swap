package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Date;

public class ShiftSwap extends Fragment {
    private  static ShiftSwap shiftSwap=null;
    private Button enterButton;
    private Spinner spinner;
    private View rootView;
    String[] shiftOptions;
    String selectedShift;
    Date selectedDate;
    AvailableUsers users = AvailableUsers.getUsers();

    public static ShiftSwap getShiftSwap() {
        if(shiftSwap==null){
            shiftSwap=new ShiftSwap();
        }
        return shiftSwap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.shift_swap,container,false);
        Init();
        Update();
        SettingUpSpinnerListioners();
        return rootView;
    }

    //
    //
    private void SettingUpSpinnerListioners(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0){
                    selectedShift= shiftOptions[i];
                    enterButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    //
    //
    private void Update(){
        shiftOptions = new String[]{"Select you Shift","AM","PM","ND"};
        ArrayAdapter<String> spinnerAdapterForSppiner = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, shiftOptions);
        spinner.setAdapter(spinnerAdapterForSppiner);
    }
    //
    // this will start new activity with list of people who will be available on that day
    // we need implement boolean which will check if user has selected all the fields
    // we also need implement a way to store a selected list
    private void Init(){
        if(rootView!=null){
            spinner= rootView.findViewById(R.id.shift_swap_spinner);
            enterButton = rootView.findViewById(R.id.swapEnterButton);
            enterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.getId()==R.id.swapEnterButton){
                        Log.i("tag", "you clicked swap button");
                       // Intent intent = new Intent(getContext(), Chat.class);
                       // startActivity(intent);
                        ReplaceWithUsersListFragments();
                    }

                }
            });
        }

    }

    private void ReplaceWithUsersListFragments(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_layout,users);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
