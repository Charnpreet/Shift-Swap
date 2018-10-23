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
    private Button nextButton, alreadyMember;
    private EditText signUp_email_address, signUp_password, signUp_name, signUp_no;
    private TextView next;
    private Intent intent;
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
            coordinateVIewFOrSnackBar=rootView.findViewById(R.id.myCoordinatorLayout);
            signUp_no=rootView.findViewById(R.id.editText);
            next = rootView.findViewById(R.id.next);
            signUp_name= rootView.findViewById(R.id.signUp_name);
            alreadyMember = rootView.findViewById(R.id.already_a_member_button);
            signUp_email_address = rootView.findViewById(R.id.signUp_mob_no);
            signUp_password = rootView.findViewById(R.id.signUp_password);
            next.setOnClickListener(this);
        }else {
            Log.i("tag "," there has been an error loading in root view for sign up activity");
        }
    }
    //    //
//    //
//    // checks  wether all sections are filled or not for sign up fragment int emp_no
    public  boolean AllFilledForSignUp(String name, String pass, String email){

        if (!TextUtils.isEmpty(name.trim()) && !TextUtils.isEmpty(pass.trim()) && !TextUtils.isEmpty(email.trim()) ) //&& (emp_no>0)
        {
            return  true;
        }
        return false;
    }
    private Employee ExtractValues(){
        Employee employee= new Employee();
        employee.setEmp_no(Integer.parseInt(signUp_no.getText().toString()));
        employee.setName(signUp_name.getText().toString());
        employee.setEmp_Password(signUp_password.getText().toString());
        employee.setEmailAddress(signUp_email_address.getText().toString());
        return  employee;
    }
    @Override
    public void onClick(View view) {
        if(utility.IsInputTextIsEmail(signUp_email_address.getText().toString())&&(AllFilledForSignUp(signUp_name.getText().toString(),signUp_password.getText().toString(), signUp_email_address.getText().toString() ))){
            ReplacingFragment(ExtractValues());
        }else{

<<<<<<< HEAD
            Snackbar.make(coordinateVIewFOrSnackBar, "Please Fill ALL Sections Or\n Make sure email address is valid", Snackbar.LENGTH_LONG).show();
=======
            Snackbar.make(coordinateVIewFOrSnackBar, "Please Fill ALl Sections Or Make sure email address is valid", Snackbar.LENGTH_LONG).show();
>>>>>>> 714f9096761a0aab3f6d43105edd582b5a34d051

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
