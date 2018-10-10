package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class signup extends AppCompatActivity implements View.OnClickListener  {
    private Button signUp, alreadyMember;
    private EditText signUp_email_address, signUp_password;
    private Intent intent;
    Spinner comapnay_Name_spiner, company_Position_sppiner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Init();
    }
    private void Init(){
        signUp = findViewById(R.id.signUp_button);
        alreadyMember = findViewById(R.id.already_a_member_button);
        comapnay_Name_spiner = findViewById(R.id.company_name_spinner);
        company_Position_sppiner = findViewById(R.id.position_spinner);
        signUp_email_address = findViewById(R.id.signUp_mob_no);
        signUp_password = findViewById(R.id.signUp_password);
        signUp.setOnClickListener(this);
        alreadyMember.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.already_a_member_button:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.signUp_button:
                Log.i("tag","signup Button Clicked");
                break;
        }


    }
}
