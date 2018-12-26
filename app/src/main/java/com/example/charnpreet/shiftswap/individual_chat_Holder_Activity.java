package com.example.charnpreet.shiftswap;

import android.content.Intent;
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
    private String RecieevIntent(){
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        return key;
    }

    private void individualchatFragment(){
        Bundle bundle = new Bundle();
        bundle.putString("key",RecieevIntent());
        chat_fragment_for_individual fragment_for_individual = new chat_fragment_for_individual();
        fragment_for_individual.setArguments(bundle);
        android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.individual_chat_Holder_Activity, fragment_for_individual);
        fragmentTransaction.addToBackStack(null);
       fragmentTransaction.commit();
    }
}