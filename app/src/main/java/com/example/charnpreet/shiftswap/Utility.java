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

    // returns days of week
    // is used to match days name with database
    public String SelectedDay(String selectedDay){
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
    /*  for indvidual chat application */
//private Map messageTextBody(){
//    Map  messageTextBody = new HashMap();
//    messageTextBody.put("message", textmessage);
//    messageTextBody.put("type","text");
//    messageTextBody.put("from", messageSenderID);
//    return messageTextBody;
//}

    private void SendMessage(){

        String textmessage="hello world";
        String messageSenderID="123456";
        String messageRecieverID="654321";
        String mesageSenderRef="Messages/" + messageSenderID+"/"+messageRecieverID;
        String messageReciverRef= "Messages/" + messageRecieverID+"/"+mesageSenderRef;
        DatabaseReference userMessageRef= FirebaseDatabase.getInstance().getReference().child("Messages")
                .child(messageSenderID).child(messageRecieverID).push();
        String messageKey = userMessageRef.getKey();

        Map messageTextBody = new HashMap();
        messageTextBody.put("message", textmessage);
        messageTextBody.put("type","text");
        messageTextBody.put("from", messageSenderID);

        Map messageBodyDetails = new HashMap();
        messageBodyDetails.put(mesageSenderRef+"/" + messageKey, messageTextBody);
        messageBodyDetails.put(messageRecieverID+"/" + messageKey, messageTextBody);

    }

}
