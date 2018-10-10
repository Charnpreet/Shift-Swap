package com.example.charnpreet.shiftswap;
import android.text.TextUtils;

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
    // checks  wether all sections are filled or not
    public static boolean AllFilled(String name, String pass){
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pass))
        {
            return  true;
        }
        return false;
    }


}
