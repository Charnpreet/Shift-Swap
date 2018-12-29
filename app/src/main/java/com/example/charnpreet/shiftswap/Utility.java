package com.example.charnpreet.shiftswap;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import android.util.Patterns;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utility {
    private  static Utility utility = null;
    FirebaseDatabase database;
    /*
    *******************************ALL THE STATIC FIREBASE NODES AT ONE PLACE STARTS****************************************************
    */
    public  static final String MessageNode = "ChatMessages";
    public  static final String UserAvailability = "UserAvailability";
    public  static final String PermanentAvailability = "PermanentAvailability";
    public  static final String AvailableUsers = "AvailableUsers";
    public  static final String Users = "Users";
    public  static final String Profile = "Profile";
    public  static final String name = "name";
    public  static final String MObNumber = "MObNumber";
    public  static final String dob = "dob";

    /*
     *******************************ALL THE STATIC FIREBASE NODES AT ONE PLACE FINISHED****************************************************
     */


    public Utility(){
        database =FirebaseDatabase.getInstance();
    }
    public static Utility getUtility() {
        if(utility==null){
            utility = new Utility();
        }
        return utility;
    }
    //
    // matching input pattern, also checks if input fied is not empty
    public boolean IsInputTextIsEmail(String email){
        if(!TextUtils.isEmpty(email)&&  (!Patterns.EMAIL_ADDRESS.matcher(email).matches())){
                return false;
        }
        return true;
    }
    /*
    * below method returns days of week
    * At the moment it has been use by permananent avaialbility fragment
    * */
    public ArrayList<String> DaysOfWeek(){
        ArrayList<String> weekDays = new ArrayList<>();
        weekDays.add("MON");
        weekDays.add("TUE");
        weekDays.add("WED");
        weekDays.add("THU");
        weekDays.add("FRI");
        weekDays.add("SAT");
        weekDays.add("SUN");
        return weekDays;
    }

    public  FirebaseDatabase FireBaseDatabaseInstance(){

        return  database;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public ProgressBar ProgressBarInstance(Context context){
            ProgressBar bar = new ProgressBar( context, null, android.R.attr.progressBarStyleLarge);
            return bar;

    }
    public boolean ISfilled(TextView view, String input){
        if(!input.isEmpty()){
            return true;
        }
        view.setError("DO not leave this section blank");
         return false;
    }
    //
    //
    // checks  wether all sections are filled or not
    public  boolean AllsectionsFilled(String name, String pass){
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pass))
        {
            return  true;
        }
        return false;
    }

    /*
     * it compare two strings lexologically
     * */

   public String messageKey(String senderKey, String recieverKey){
        String key;
        int s = senderKey.compareTo(recieverKey);
        if(s>0){
            key = senderKey+recieverKey;
        }else {
            key = recieverKey+senderKey;
        }

        return key;
    }

}
