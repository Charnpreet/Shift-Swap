package com.example.charnpreet.shiftswap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class individual_chat_Holder_Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView backarrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_chat__holder_);
        individualchatFragment();
    }
    private void individualchatFragment(){
        chat_fragment_for_individual fragment_for_individual = new chat_fragment_for_individual();
        android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.individual_chat_Holder_Activity, fragment_for_individual,  "chat");
        fragmentTransaction.addToBackStack("chat");
       fragmentTransaction.commit();
    }
}
