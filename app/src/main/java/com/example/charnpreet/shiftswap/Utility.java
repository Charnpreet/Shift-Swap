package com.example.charnpreet.shiftswap;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

public class Utility {
    private  static Utility utility = null;
    private  static DatabaseHelper databaseHelper=null;

    public static Utility getUtility() {
        if(utility==null){
            utility = new Utility();
        }
        return utility;
    }
    //
    // experiment not used yet
    public static DatabaseHelper getDatabaseHelperInstance(Context context){
        if(databaseHelper==null){
            databaseHelper = new DatabaseHelper(context);
        }
        return  databaseHelper;
    }
    public boolean IsInputTextIsEmail(String email){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            return false;
        }
        return true;
    }
    //
    //
    // checks  wether all sections are filled or not for sign in frgament
    public  boolean AllFilledForSignIn(String name, String pass){
        if (!TextUtils.isEmpty(name.trim()) && !TextUtils.isEmpty(pass.trim()))
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
