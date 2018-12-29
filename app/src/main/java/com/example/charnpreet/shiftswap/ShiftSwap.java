package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class ShiftSwap extends Fragment implements View.OnClickListener {
    private  static ShiftSwap shiftSwap=null;
    private Button enterButton;
    private Spinner spinner;
    private View rootView;
    String[] shiftOptions;
    String selectedShift;
    ScrollView scrollView;
    CalendarView calendarView;
    String selectedDay=null;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String currentDate;

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
            assert getFragmentManager() != null;
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
            GetCurrentDate();
            spinner = rootView.findViewById(R.id.shift_swap_spinner);
            calendarView = rootView.findViewById(R.id.datePicker);
            enterButton = rootView.findViewById(R.id.swapEnterButton);
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
              Date calendar = new Date( i, i1, i2-1);
              //SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
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
        String selectedDay = ExtractDayFromDate();
        if ((selectedShift != null) && (selectedDay != null)) {
                // feteching data from firebase and storing it to availEmployeeList
                AvailabilityQuery(selectedDay,selectedShift);
        }
    }
    // this method connects to fire base itrates through all the users
    // then checks for selected day and shift timing
    // if user is available then it retrives its user id and adds to array list of strings
        private void AvailabilityQuery(final String selectedDay, final String shiftname ){
            DatabaseReference mRef = database.getReference().child(Utility.UserAvailability).child(Utility.PermanentAvailability);
            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot snap:dataSnapshot.getChildren()) {
                        if (snap.child(selectedDay).child(shiftname).getValue() != null) {
                            if ((Long) snap.child(selectedDay).child(shiftname).getValue() > 0) {
                                UserInfoForSelectedUsers(snap.getKey());
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.i("singh"," there is an error in processing your request");

                }
            });

        }
        // this will return user name for selected users
        // need to pass list of users
        // list should come from AvailabilityQuery method
        //
        private void UserInfoForSelectedUsers(String key) {
            DatabaseReference mRef = database.getReference().child(Utility.Users).child(key);
            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String currentuser = dataSnapshot.getKey();
                    if(!currentuser.equals(user.getUid())) {
                        CreatingAvailableUserNode(currentuser);
                        CallingActivityFromFragment();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.i("singh"," there is an error in loading info");
                }
            });
        }
        private void CreatingAvailableUserNode(String availuser){
            DatabaseReference mRef = database.getReference();
            mRef.child(Utility.AvailableUsers).child(user.getUid()).child(availuser).setValue(currentDate);
        }
        private void GetCurrentDate(){

            SimpleDateFormat cdate = new SimpleDateFormat("dd-mm-yyyy");
            Date dateobj = new Date();
            currentDate = cdate.format(dateobj);

        }
    // returns days of week
    // is used to match days name with database
    public String SelectedDay(String selectedDay){
        String day=selectedDay;
        if(selectedDay.equals("Monday")){
            day="MON";
        }
        if(selectedDay.equals("Tuesday")){
            day="TUE";
        }
        if(selectedDay.equals("Wednesday")){
            day="WED";
        }
        if(selectedDay.equals("Thursday")){
            day="THU";
        }
        if(selectedDay.equals("Friday")){
            day="FRI";
        }
        if(selectedDay.equals("Saturday")){
            day="SAT";
        }
        if(selectedDay.equals("Sunday")){
            day="SUN";
        }


        return day;
    }
        //ArrayList<Users> availusers
    private void CallingActivityFromFragment(){

        Intent myintent = new Intent(getActivity(), Chat_Activity.class);
        //myintent.putParcelableArrayListExtra("employee",availusers);
       (getActivity()).startActivity(myintent);
    }

    @Override
    public void onClick(View view) {
        ExcutingAQueery();
        spinner.setSelection(0);
        enterButton.setVisibility(View.INVISIBLE);
    }
}
/*
*  as data is loaded on background threaad, need to find a way to stop
*  Main UI thread to make wait until data is loaded
*  once data is loaded then it should move to next fragment
* */