package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

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
            login_button.setOnClickListener(this);
            mAuth = FirebaseAuth.getInstance();
        }
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
        login_button.setBackgroundColor(Color.WHITE);
        login_button.setText(R.string.logging_in);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }



    @Override
    public void onClick(View view) {
        LoginAtempt();
    }

}
