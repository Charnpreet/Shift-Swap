package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button register_button;
    Fragment fragment ;
    DatabaseHelper databaseHelper;

    private final static String buttonRegisterString="Register";
    private final static String buttonAlreadyMemberString="Already Member";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //databaseHelper = new DatabaseHelper(this);
        //databaseHelper.getWritableDatabase();
        Init();
    }
    //
    //
    private void Init(){
        register_button = findViewById(R.id.register_button);
        register_button.setOnClickListener(this);
        fragment = getSupportFragmentManager().findFragmentById(R.id.main_activity_fragment);
        CreateLoginFragment();

    }



    @Override
    public void onClick(View view)
    {
        if(register_button.getText()==buttonAlreadyMemberString) {
            LoginFragment();
            register_button.setText(buttonRegisterString);
        }
        else {
           //
            register_button.setText(buttonAlreadyMemberString);
            SignUpFragment();
        }

    }
    //
    // this will create one fragment to start with
    private void CreateLoginFragment(){
        sign_in_fragment sign_in_fragment = new sign_in_fragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.main_activity_fragment, sign_in_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    // this will be used to replace Signup fragment wirth Login fragment
    private void LoginFragment(){
        sign_in_fragment sign_in_fragment = new sign_in_fragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_fragment, sign_in_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
    //
    // this will be used to replace Login fragment wirth Signup fragment
    private void SignUpFragment(){
        sign_up_fragment sign_up_fragment = new sign_up_fragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_fragment, sign_up_fragment," tag");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();

    }

}
