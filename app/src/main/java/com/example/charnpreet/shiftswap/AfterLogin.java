package com.example.charnpreet.shiftswap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.icu.text.Replaceable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AfterLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    Toolbar toolbar;
    Intent intent;
    TextView loginusername, loginuseremail;
    FirebaseDatabase database;
    Utility utility;
    Availability availability = Availability.getAvailability();
    ShiftSwap shiftSwap = ShiftSwap.getShiftSwap();
    Profile profile = new Profile();
    FirebaseAuth myauth = FirebaseAuth.getInstance();
    change_password password = new change_password();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Init();
        AvailabilityFragment();
        HeadView();
    }
    //
    //
    //
    private  void Init(){
        utility= Utility.getUtility();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shift-Swap");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    //
    // this method is used to get login user name and email address
    private void GetingLoginUserDetails(final TextView loginusername, final TextView loginuseremail){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        database = utility.FireBaseDatabaseInstance();
        DatabaseReference mRef = database.getReference().child("Users").child(userID).child("Profile");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name =(String) dataSnapshot.child("name").getValue();
                String mobNum = (String) dataSnapshot.child("MObNumber").getValue();
                if((name!=null)&&(!name.isEmpty())){
                    loginusername.setText(name);
                }else {
                    loginusername.setText("unable to load your profile info");
                }
               if((mobNum!=null)&&(!mobNum.isEmpty())){
                   loginuseremail.setText(mobNum);
               }else {
                    loginuseremail.setText("");
               }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //
    // below method is used to update navigation drawer headings
    private void HeadView(){
        View headerView = navigationView.getHeaderView(0);
        loginusername = headerView.findViewById(R.id.textview_for_drawer);
        loginuseremail= headerView.findViewById(R.id.textview_1_for_drawer);
        GetingLoginUserDetails(loginusername,loginuseremail);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.availability:

                AvailabilityFragment();
                break;
            case R.id.swapshift :
                ShiftSwapFragment();
                break;
            case R.id.message:
                break;
            case R.id.chat:
                ChatActivity();
                break;
            case R.id.profile:
                ProfileFragment();
                break;
            case R.id.password:
                ChangePasswordFragment();
                break;
            case  R.id.log_out:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                myauth.signOut();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

private void ChatActivity(){
        Intent intent= new Intent(this, Chat_Activity.class);
        intent.setFlags((Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
        startActivityIfNeeded(intent, 0);
}

  private void AvailabilityFragment(){
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.replace(R.id.container_layout, availability);
      fragmentTransaction.addToBackStack(null);
      fragmentTransaction.commit();
  }

    private  void ShiftSwapFragment(){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_layout,shiftSwap);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void ProfileFragment(){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_layout, profile);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private  void  ChangePasswordFragment(){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_layout, password);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
