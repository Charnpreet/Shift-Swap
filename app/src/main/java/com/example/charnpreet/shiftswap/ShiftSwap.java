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
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ShiftSwap extends Fragment implements View.OnClickListener {
    private  static ShiftSwap shiftSwap=null;
    private Button enterButton;
    private Spinner spinner;
    private View rootView;
    String[] shiftOptions;
    String selectedShift;
    ScrollView scrollView;
    DatabaseHelper databaseHelper;
   CalendarView calendarView;
    String selectedDay=null;
    Cursor cursor;
    ArrayList<Employee> availEmployee = new ArrayList<>();
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).commit();
        }
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
    private void Init() {
        if (rootView != null) {
            spinner = rootView.findViewById(R.id.shift_swap_spinner);
            calendarView = rootView.findViewById(R.id.datePicker);
            enterButton = rootView.findViewById(R.id.swapEnterButton);
            databaseHelper = new DatabaseHelper(rootView.getContext());
            enterButton.setOnClickListener(this);
            // below code is to get toolbar title
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("swap shifts");
            String selectedDay= ExtractDayFromDate();
        }
    }

    private String ExtractDayFromDate(){


      calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
          @Override
          public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
              Date calendar = new Date( i, i1, i2);
              SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
              SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
           String  day = outFormat.format(calendar);
           selectedDay= SelectedDay(day);

          }
      });
      return selectedDay;
    }
    //
    // this method will return employee name and email address
    // needs to be stored in an employee object then passed to user fragment to be displayed in a list view of recyler
    private void ExcutingAQueery() {
        String selectedDay= ExtractDayFromDate();
        String shiftname = SelectedShift();

        if((shiftname != null)&&(selectedDay != null)) {
            try{
                if(availEmployee.size()>0){
                    availEmployee.clear();
                }
                cursor = databaseHelper.RetrieveAvailableUserData(selectedDay, shiftname, AfterLogin.LoginEmployee_No);
                if (cursor.moveToFirst()) {

                    do {
                       Employee emp= new Employee();
                       emp.name=cursor.getString(0);
                       emp.emailAddress=cursor.getString(1);
                        availEmployee.add(emp);

                    } while (cursor.moveToNext());
                }
                ReplaceWithUsersListFragments(availEmployee);
            }catch (Exception ex){
                Toast.makeText(rootView.getContext(), "There are no employees available to cover your shift for selected date", Toast.LENGTH_LONG).show();

            }

        }
    }

    // this method can be replaced with Dictionary
    private String SelectedDay(String selectedDay){
        String day=selectedDay;
        if(selectedDay.equals("Monday")){
            day="Mon";
        }
        if(selectedDay.equals("Tuesday")){
            day="Tue";
        }
        if(selectedDay.equals("Wednesday")){
            day="Wed";
        }
        if(selectedDay.equals("Thursday")){
            day="Thu";
        }
        if(selectedDay.equals("Friday")){
            day="Fri";
        }
        if(selectedDay.equals("Saturday")){
            day="Sat";
        }
        if(selectedDay.equals("Sunday")){
            day="Sun";
        }


        return day;
    }
    //
    // this returns apropirate availability column in database
    // this method can be replaced with Dictionary
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
    private void ReplaceWithUsersListFragments(ArrayList<Employee> employee){
        Bundle args = new Bundle();
        args.putParcelableArrayList("employee",employee);
        users.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_layout,users);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        ExcutingAQueery();
        spinner.setSelection(0);
        enterButton.setVisibility(View.INVISIBLE);
    }
}
