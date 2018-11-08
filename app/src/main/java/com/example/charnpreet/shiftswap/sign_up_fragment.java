package com.example.charnpreet.shiftswap;

import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class sign_up_fragment extends Fragment implements View.OnClickListener {
    private Sign_up_fragment_continue sign_up_fragment_continue = new Sign_up_fragment_continue();
    private EditText signUp_email_address, signUp_password, signUp_name, signUp_no;
    private TextView next;
    Utility utility;
    View rootView;
    private View coordinateVIewFOrSnackBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sign_up_fragment, container, false);
        Init();
        return rootView;
    }

    private void Init() {
        if (rootView != null) {
            utility = Utility.getUtility();
            coordinateVIewFOrSnackBar = rootView.findViewById(R.id.myCoordinatorLayout);
            signUp_no = rootView.findViewById(R.id.editText);
            next = rootView.findViewById(R.id.next);
            signUp_name = rootView.findViewById(R.id.signUp_name);
            signUp_email_address = rootView.findViewById(R.id.signUp_mob_no);
            signUp_password = rootView.findViewById(R.id.signUp_password);
            next.setOnClickListener(this);
        }
    }

    private Employee ExtractValues(){
        Employee employee= new Employee();
        employee.setEmp_no(Integer.parseInt(signUp_no.getText().toString()));
        employee.setName(signUp_name.getText().toString());
        employee.setEmp_Password(signUp_password.getText().toString());
        employee.setEmailAddress(signUp_email_address.getText().toString());
        return  employee;
    }

    private void ValidatingDetails(){
        String  username =signUp_name.getText().toString().trim();
        String pass= signUp_password.getText().toString().trim();
        String email= signUp_email_address.getText().toString().trim();
        if (utility.IsInputTextIsEmail(email)) {
            if (utility.AllsectionsFilled(username, pass)) {
                ReplacingFragment(ExtractValues());
            } else {
                Snackbar.make(coordinateVIewFOrSnackBar, "Please Make sure All Fields have been filed ", Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(coordinateVIewFOrSnackBar, "Make sure email address is valid", Snackbar.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View view) {
    try {
            ValidatingDetails();
    }catch (NumberFormatException es){
        Snackbar.make(coordinateVIewFOrSnackBar, "make sure your user name is correct", Snackbar.LENGTH_LONG).show();
    }
    catch (Exception es){
        Snackbar.make(coordinateVIewFOrSnackBar, "there is an error in your input", Snackbar.LENGTH_LONG).show();
    }
}



    private void ReplacingFragment(Employee employee){
        Bundle args = new Bundle();
        args.putParcelable("employee", employee);
        sign_up_fragment_continue.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_fragment, sign_up_fragment_continue);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
