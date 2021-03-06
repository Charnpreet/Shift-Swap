package com.example.charnpreet.shiftswap.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.charnpreet.shiftswap.main_Page.MainActivity;
import com.example.charnpreet.shiftswap.R;
import com.example.charnpreet.shiftswap.availability.Availability;
import com.example.charnpreet.shiftswap.change_password.change_password;
import com.example.charnpreet.shiftswap.chat.Chat_Activity;
import com.example.charnpreet.shiftswap.profile.Profile;
import com.example.charnpreet.shiftswap.shift_swap.ShiftSwap;
import com.example.charnpreet.shiftswap.utility.Utility;
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
    ImageView drawerImageView;
    FirebaseDatabase database;
    Utility utility;
    Availability availability = Availability.getAvailability();
    ShiftSwap shiftSwap = ShiftSwap.getShiftSwap();
    Profile profile = new Profile();
    FirebaseAuth myauth = FirebaseAuth.getInstance();
    change_password password = new change_password();
    public  static final String TITLE = "Shift-Swap";
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
        getSupportActionBar().setTitle(TITLE);
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
    private void GetingLoginUserDetails(final TextView loginusername, final TextView loginuseremail, final ImageView img){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        database = utility.FireBaseDatabaseInstance();
        DatabaseReference mRef = database.getReference().child(Utility.Users).child(userID).child(Utility.Profile);
        mRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    String name =(String) dataSnapshot.child(Utility.name).getValue();
                    String mobNum = (String) dataSnapshot.child(Utility.MObNumber).getValue();
                    if(dataSnapshot.hasChild(Utility.url)){
                        String url = dataSnapshot.child(Utility.url).getValue().toString();
                        utility.LoadPicFromServerAndDisplayToUser(getBaseContext(),url,img);
                    }

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
        drawerImageView = headerView.findViewById(R.id.imageView);
        GetingLoginUserDetails(loginusername,loginuseremail, drawerImageView);
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
