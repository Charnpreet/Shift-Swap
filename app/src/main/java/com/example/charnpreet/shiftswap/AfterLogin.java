package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.database.Cursor;
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

public class AfterLogin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    Toolbar toolbar;
    Intent intent;
    DatabaseHelper databaseHelper;
    Cursor cursor;
    TextView loginusername, loginuseremail;
    public  static int LoginEmployee_No;
    Availability availability = Availability.getAvailability();
    ShiftSwap shiftSwap = ShiftSwap.getShiftSwap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        Init();
        AvailabilityFragment();
        LoginEmployee_No = getIntent().getIntExtra("employee_no",0);
        HeadView();
    }
    //
    //
    //
    private  void Init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shift-Swap");
        databaseHelper = new DatabaseHelper(this);
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
    private void GetingLoginUserDetails(TextView loginusername, TextView loginuseremail){
        cursor=databaseHelper.LoginUserDetails(LoginEmployee_No);
        cursor.moveToFirst();
            loginusername.setText(cursor.getString(0));
            loginuseremail.setText(cursor.getString(1));
            cursor.moveToNext();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.after_login, menu);
        return true;
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
            case  R.id.log_out:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}
