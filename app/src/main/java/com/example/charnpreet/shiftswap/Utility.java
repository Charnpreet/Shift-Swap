package com.example.charnpreet.shiftswap;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Patterns;

public class Utility {
    private  static Utility utility = null;

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
    //
    // this will days of week which are stored on Database
    public String[] ExecutingDaysQuerry(DatabaseHelper databaseHelper, Cursor cursor){
        cursor= databaseHelper.DaysOfWeekQuerry();
        String[] weekDays= new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i=0; i<cursor.getCount(); i++){
            weekDays[i]=cursor.getString(0);
            cursor.moveToNext();
        }

        return weekDays;
    }


}
