package com.example.charnpreet.shiftswap.login;

import android.net.Uri;
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

import com.example.charnpreet.shiftswap.R;

import static android.support.v4.content.ContextCompat.startActivity;

public class sign_up_fragment extends Fragment implements View.OnClickListener {
    View rootView;
    Button contactButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sign_up_fragment, container, false);
        Init();
        return rootView;
    }
    private void Init(){
        contactButton=rootView.findViewById(R.id.signupemail);
        contactButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent result = new Intent();
        result.setAction(Intent.ACTION_SENDTO);
        result.setData(Uri.parse("mailto: scrubnclean@gmail.com"));
        result.putExtra(Intent.EXTRA_SUBJECT, "Swap Shifts");
        startActivity(result,null);
    }
}
