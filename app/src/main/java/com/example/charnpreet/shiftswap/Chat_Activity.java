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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chat_Activity extends AppCompatActivity implements View.OnClickListener {
private Toolbar toolbar;
private ImageView backarrow;
private RecyclerView recyclerView;
private ArrayList<Users> availUsers=new ArrayList<>();
private FirebaseDatabase database = FirebaseDatabase.getInstance();
private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_);
        Init();
        ReterievingAvaialableUserList();
    }
    /*
    * initializing all the values,
    *
    * */
    private void Init(){
        toolbar= findViewById(R.id.chat_activity_toolbar);
        backarrow=findViewById(R.id.chat_activity_back_Arrow);
        backarrow.setOnClickListener(this);
        recyclerView= findViewById(R.id.chat_recyler_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }
    /*
     * reterving children keys from avaialble user node
     * after getting keys it calls another method and passes retreieved keys as a parameter
     *
     * */
    private void ReterievingAvaialableUserList(){
        DatabaseReference mRef = database.getReference().child(Utility.AvailableUsers).child(user.getUid());
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshot1 = dataSnapshot.getChildren();
               for(DataSnapshot data : dataSnapshot1){
                    String key = data.getKey();
                   GetingProfileInfoForAvailavleUsers(key);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    /*
     * this method retrieved profile data from passed string key
     * it stores the value in User class
     * which then stred in avaialable user array list
     *after that it calls a method InitRecyclerView
     * */
    private void GetingProfileInfoForAvailavleUsers(String key){
        DatabaseReference mRef = database.getReference().child(Utility.Users).child(key).child(Utility.Profile);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                availUsers.add(user);
                InitRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
     * this method initlize recyler view holder
     * and also passes array list of available user to its adapter
     *
     * */
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
        /*
         * this simply destroyes current activity
         * take users back to after login activity
         *
         * */
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
