package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
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
import android.widget.Toast;

public class sign_in_fragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private Button login_button;
    private Intent intent;
    private EditText login_email_address, password;
    DatabaseHelper databaseHelper;
    Cursor cursor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sign_in_fragment, container, false);
        Init();
        return rootView;
    }

    private void Init(){
        if (rootView != null) {
            databaseHelper = new DatabaseHelper(rootView.getContext());
            login_email_address = rootView.findViewById(R.id.login_email_address);
            password = rootView.findViewById(R.id.login_password);
            login_button = rootView.findViewById(R.id.login_button);
            login_button.setOnClickListener(this);
        }else {
            Log.i("tag "," there has been an error loading in root view for sign in activity");
        }
    }

    @Override
    public void onClick(View view) {
//        String savedUname = null;
//        String savedPword = null;
//        cursor = databaseHelper.LoginQuerry(Integer.parseInt(login_email_address.getText().toString()));
//        int unameIndex = cursor.getColumnIndex("Employee_No");
//        int pwordIndex = cursor.getColumnIndex("Employee_Password");
//        cursor.moveToFirst();
//        if(cursor!=null)
//        {
//           try {
//                    savedUname = cursor.getString(unameIndex);
//                    savedPword = cursor.getString(pwordIndex);
//                {
//                    {
//
//
//                        if (savedUname.equals(login_email_address.getText().toString())) {
//
//                            if (savedPword.equals(password.getText().toString())) {
//                                intent = new Intent(getContext(), AfterLogin.class);
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(rootView.getContext(), " Incorrect username or password ", Toast.LENGTH_LONG).show();
//                            }
//                        } else {
//                            Toast.makeText(rootView.getContext(), " Incorrect username ", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                }
//                }   catch (CursorIndexOutOfBoundsException es)
//                    {
//                        Toast.makeText(rootView.getContext(), " User does not exist", Toast.LENGTH_LONG).show();
//                    }
//        }
        intent = new Intent(getContext(), AfterLogin.class);
                                startActivity(intent);
    }
}
