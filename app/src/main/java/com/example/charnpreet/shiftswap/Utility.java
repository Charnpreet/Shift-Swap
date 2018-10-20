package com.example.charnpreet.shiftswap;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

public class Utility {
    private  static Utility utility = null;

    public static Utility getUtility() {
        if(utility==null){
            utility = new Utility();
        }
        return utility;
    }

    //
    //
    // checks  wether all sections are filled or not for sign in frgament
    public  boolean AllFilledForSignIn(String name, String pass){
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pass))
        {
            return  true;
        }
        return false;
    }
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
