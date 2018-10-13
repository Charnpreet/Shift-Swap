package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class sign_up_fragment extends Fragment implements View.OnClickListener {
    private Button signUp, alreadyMember;
    private EditText signUp_email_address, signUp_password;
    private Intent intent;
    Spinner comapnay_Name_spiner, company_Position_sppiner;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sign_up_fragment, container, false);
        Init();
        return rootView;
    }
    private void Init() {
        if (rootView != null) {
            signUp = rootView.findViewById(R.id.signUp_button);
            alreadyMember = rootView.findViewById(R.id.already_a_member_button);
            comapnay_Name_spiner = rootView.findViewById(R.id.company_name_spinner);
            company_Position_sppiner = rootView.findViewById(R.id.position_spinner);
            signUp_email_address = rootView.findViewById(R.id.signUp_mob_no);
            signUp_password = rootView.findViewById(R.id.signUp_password);
            signUp.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View view) {
       // intent = new Intent(getContext(), MainActivity.class);
       // startActivity(intent);
        Log.i("tag","sign -up Button Clicked");

    }
}
