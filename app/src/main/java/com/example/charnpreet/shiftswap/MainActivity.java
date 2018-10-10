package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login_button;
    private Button register_button;
    private EditText login_email_address, password;
    private Intent intent;
    private final static int PROFILE_SETUP= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }
    //
    //
    private void Init(){
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        login_email_address = findViewById(R.id.login_email_address);
        password = findViewById(R.id.login_password);
        login_button.setOnClickListener(this);
        register_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                intent = new Intent(this, AfterLogin.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.register_button:
                intent = new Intent(this, signup.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }
}
