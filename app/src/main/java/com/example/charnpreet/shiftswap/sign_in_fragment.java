package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sign_in_fragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private Button login_button;
    private Intent intent;
    private EditText login_email_address, password;
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private Utility utility = Utility.getUtility();
    DatabaseHelper databaseHelper;
    Cursor cursor;
    private View coordinateVIewFOrSnackBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sign_in_fragment, container, false);
        Init();
        return rootView;
    }
    //
    private void Init(){
        if (rootView != null) {
            coordinateVIewFOrSnackBar=rootView.findViewById(R.id.myCoordinatorLayout);
            databaseHelper = new DatabaseHelper(rootView.getContext());
            login_email_address = rootView.findViewById(R.id.login_email_address);
            password = rootView.findViewById(R.id.login_password);
            login_button = rootView.findViewById(R.id.login_button);
            login_button.setOnClickListener(this);
        }
    }

    private void StartIntent(String username, String pass){
        intent = new Intent(getContext(), AfterLogin.class);
        intent.putExtra("employee_no",Integer.parseInt(username));
        startActivity(intent);
    }
    //
    // is called to let user login to the app
    private void LoginAtempt(){
        String savedUname = null;
        String savedPword = null;
        String  username =login_email_address.getText().toString().trim();
        String pass= password.getText().toString().trim();
        if(utility.AllsectionsFilled(username, pass)){
            cursor = databaseHelper.LoginQuerry(Integer.parseInt(username));
            int unameIndex = cursor.getColumnIndex("Employee_No");
            int pwordIndex = cursor.getColumnIndex("Employee_Password");
            cursor.moveToFirst();
            if (cursor != null) {
                try {
                    savedUname = cursor.getString(unameIndex);
                    savedPword = cursor.getString(pwordIndex);
                    {
                        {
                            if (savedUname.equals(username)) {

                                if (savedPword.equals(pass)) {
                                    StartIntent(username, pass);
                                } else {
                                    Snackbar.make(coordinateVIewFOrSnackBar, "Incorrect password ", Snackbar.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                } catch (CursorIndexOutOfBoundsException es) {
                    Snackbar.make(coordinateVIewFOrSnackBar, "User Does Not Exist", Snackbar.LENGTH_LONG).show();
                }
            }
        }else{
            Snackbar.make(coordinateVIewFOrSnackBar, "There is a Problem With your Detail,\n Make sure all sections are filled", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        LoginAtempt();
    }
}
