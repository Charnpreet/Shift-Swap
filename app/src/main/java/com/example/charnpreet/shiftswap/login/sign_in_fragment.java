package com.example.charnpreet.shiftswap.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.charnpreet.shiftswap.home.AfterLogin;
import com.example.charnpreet.shiftswap.R;
import com.example.charnpreet.shiftswap.utility.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_in_fragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private Button login_button;
    private Intent intent;
    private EditText login_email_address, password;
    private Utility utility;
    private FirebaseAuth mAuth;
    private View coordinateVIewFOrSnackBar;
    ViewGroup container;
    ProgressBar bar;
    private final String LOGGING_IN = "SIGNING IN...";
    private final String LOG_IN = "LOG IN";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sign_in_fragment, container, false);
        this.container = container;
        Init();
        return rootView;
    }
    //
    private void Init(){
        if (rootView != null) {
            utility = Utility.getUtility();
            coordinateVIewFOrSnackBar=rootView.findViewById(R.id.myCoordinatorLayout);
            login_email_address = rootView.findViewById(R.id.login_email_address);
            password = rootView.findViewById(R.id.login_password);
            login_button = rootView.findViewById(R.id.login_button);
            login_button.setBackgroundColor(Color.GREEN);
            login_button.setOnClickListener(this);
            mAuth = FirebaseAuth.getInstance();
        }
    }
    /*
    *this method is used to changes button color and text
    * depends up situation
    * for now it is used while signing
    * */
    private void ButtonDesign(int clr, String btnText){
        login_button.setBackgroundColor(clr);
        login_button.setText(btnText);
    }

    private void StartIntent(){
        intent = new Intent(getContext(), AfterLogin.class);
        startActivity(intent);
    }
    //
    //
    private void LoginAtempt(){
        final String  email =login_email_address.getText().toString().trim();
        final String pass= password.getText().toString().trim();
        bar= utility.ProgressBarInstance(rootView.getContext());
       if(utility.AllsectionsFilled(email, pass)){
           LayoutForProgressBar();
           mAuth.signInWithEmailAndPassword(email, pass)
                   .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               StartIntent();
                               bar.setVisibility(View.INVISIBLE);
                           } else {
                               Snackbar.make(coordinateVIewFOrSnackBar, task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                               bar.setVisibility(View.INVISIBLE);
                               getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                               ButtonDesign(Color.GREEN, LOG_IN);
                           }
                       }
                   });
       }
       else {
           Snackbar.make(coordinateVIewFOrSnackBar, "Check your input details", Snackbar.LENGTH_LONG).show();
       }
    }
    private void LayoutForProgressBar(){
        int width = Math.round(login_button.getWidth()*25/100);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,login_button.getHeight()-8);
        params.setMargins(Math.round(login_button.getY()-30),Math.round(login_button.getY()),0,0);
        container.addView(bar, params);
        bar.setVisibility(View.VISIBLE);
        ButtonDesign(Color.WHITE, LOGGING_IN);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }



    @Override
    public void onClick(View view) {
        LoginAtempt();

    }

}
