package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Chat_Activity extends AppCompatActivity implements View.OnClickListener {
private Toolbar toolbar;
private ImageView backarrow;
private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);
        Init();
    }

    private void Init(){
        toolbar= findViewById(R.id.chat_activity_toolbar);
        backarrow=findViewById(R.id.chat_activity_back_Arrow);
        backarrow.setOnClickListener(this);
        recyclerView= findViewById(R.id.chat_recyler_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.chat_activity_back_Arrow){
            this.finish();
        }
        else {
            Log.i("tag", " hello world");
        }

    }

}
