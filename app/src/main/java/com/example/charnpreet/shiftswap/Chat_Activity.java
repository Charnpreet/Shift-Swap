package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chat_Activity extends AppCompatActivity implements View.OnClickListener {
private Toolbar toolbar;
private ImageView backarrow;
private RecyclerView recyclerView;
private ArrayList<Users> availUsers=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);
        RecievingBundle();
        Init();
    }

    private void Init(){
        toolbar= findViewById(R.id.chat_activity_toolbar);
        backarrow=findViewById(R.id.chat_activity_back_Arrow);
        backarrow.setOnClickListener(this);
        recyclerView= findViewById(R.id.chat_recyler_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        InitRecyclerView();
     Log.i("singh", "Activity  called from chsart");
    }
    private void RecievingBundle(){
        Intent intent = getIntent();
        availUsers=  intent.getParcelableArrayListExtra("employee");
    }
    private void InitRecyclerView(){
        RecyclerView.LayoutManager m = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(m);
        recyclerView.setAdapter(new chat_activity_adapter(availUsers, this));
        //
        // below code addes divider to recyler view items
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),((LinearLayoutManager) m).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onBackPressed() {
        DestroyCurrentView();
        }
        //
        //
        private void DestroyCurrentView(){
            Intent myintent = new Intent(this, AfterLogin.class);
            myintent.setFlags((Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            (this).startActivity(myintent);
        }

    @Override
    public void onClick(View view) {
        DestroyCurrentView();
    }
}
