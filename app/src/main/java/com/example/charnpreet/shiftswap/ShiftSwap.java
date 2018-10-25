package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ShiftSwap extends Fragment {
    private  static ShiftSwap shiftSwap=null;
    private Button enterButton;
    private Spinner spinner;
    private View rootView;
    String[] shiftOptions;
    String selectedShift;
    ScrollView scrollView;
    DatabaseHelper databaseHelper;
    DatePicker datePicker;
    Cursor cursor;
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
            datePicker=rootView.findViewById(R.id.datePicker);
            enterButton = rootView.findViewById(R.id.swapEnterButton);
            databaseHelper = new DatabaseHelper(rootView.getContext());
            enterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.getId()==R.id.swapEnterButton){
                        ReplaceWithUsersListFragments();
                    }

                }
            });
        }

        // below code is to get toolbar title
       ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("swap shifts");

    }
    private String ExtractDayFromDate(){
        final String selectedDay=null;
        Calendar calendar = Calendar.getInstance();
        DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener()
        {
            public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth)
            {

                //selectedDay=Integer.toString(Calendar.get)
            }
        };

        return  selectedDay;
    }
    //
    // this method will return employee name and email address
    // needs to be stored in an employee object then passed to user fragment to be displayed in a list view of recyler
    private void ExcutingAQueery() {

        String selectedDay = ExtractDayFromDate();
        String shiftname = SelectedShift();
        if((shiftname != null)&&(selectedDay != null)) {
            cursor = databaseHelper.RetrieveAvailableUserData(selectedDay, shiftname);
        }
        else{
            Log.i("tag", "There is an errror in your request Please try again later");
        }
    }
    private String SelectedShift(){
        String shiftname =null;
        if(selectedShift=="AM"){
            shiftname="AM_Availability";
        }
        if(selectedShift=="PM"){
            shiftname="PM_Availability";
        }
        if(selectedShift=="ND"){
            shiftname="ND_Availability";
        }
        return shiftname;
    }
    private void ReplaceWithUsersListFragments(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_layout,users);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
