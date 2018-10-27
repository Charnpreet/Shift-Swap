package com.example.charnpreet.shiftswap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class View_Avialability_Adapter extends RecyclerView.Adapter<View_Avialability_Adapter.ViewAvailability> {
    View view;
    String[] weekDays = null;
    DatabaseHelper databaseHelper;
    Cursor cursor;

    public View_Avialability_Adapter(String[] weekDays, Context context) {
        this.weekDays = weekDays;
        databaseHelper = new DatabaseHelper(context);
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewAvailability onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from((viewGroup.getContext()));
        view = inflater.inflate(R.layout.viewavailability, viewGroup, false);
        return new ViewAvailability(view);
    }
    //
    //
    @Override
    public void onBindViewHolder(@NonNull ViewAvailability viewAvailability, int i) {
        viewAvailability.day.setText(weekDays[i]);
        clickableListener(viewAvailability, i);
        DownloadAvailabilityFromDatabase(viewAvailability, i);
    }

    private void DownloadAvailabilityFromDatabase(ViewAvailability viewAvailability, int i){
    cursor = databaseHelper.ReterievingAvailabilityforLoginMember(AfterLogin.LoginEmployee_No,i);
    if(cursor.getCount()>0){
        cursor.moveToNext();
        if((cursor.getInt(0)>0)) {
            viewAvailability.amcheckbox.setChecked(true);
        }
        if((cursor.getInt(1)>0)) {
            viewAvailability.pmcheckbox.setChecked(true);
        }
        if((cursor.getInt(2)>0)) {
            viewAvailability.ndcheckbox.setChecked(true);
        }
    }

    }
    //
    //
    //
    private void clickableListener(final ViewAvailability viewAvailability, final int i){
       final Bundle bundle = new Bundle();
       viewAvailability.amcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewAvailability.amcheckbox.isChecked()){
                    bundle.putInt("availability_status", 1);
                    Log.i("tag", "am is checked at " + weekDays[i] );

                }else{
                    bundle.putInt("availability_status", 0);

                }
                bundle.putInt("emp_no", AfterLogin.LoginEmployee_No);
                bundle.putInt("day_id", i);
                bundle.putString("slected AVailability","AM_Availability");
                new AsyncTaskHelper().execute(bundle);
            }
        });
       //
       //
        //
       viewAvailability.pmcheckbox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (viewAvailability.pmcheckbox.isChecked()){
                   bundle.putInt("availability_status", 1);
                   Log.i("tag", "pm is checked at " + weekDays[i] );
               }else{
                   bundle.putInt("availability_status", 0);
               }
               bundle.putInt("emp_no", AfterLogin.LoginEmployee_No);
               bundle.putInt("day_id", i);
               bundle.putString("slected AVailability","PM_Availability");
               new AsyncTaskHelper().execute(bundle);
           }
       });
       //
        //
        //
        viewAvailability.ndcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewAvailability.ndcheckbox.isChecked()){
                    Log.i("tag", "nd is checked at " + weekDays[i] );
                    bundle.putInt("availability_status", 1);
                }else {
                    bundle.putInt("availability_status", 0);
                }
                bundle.putInt("emp_no", AfterLogin.LoginEmployee_No);
                bundle.putInt("day_id", i);
                bundle.putString("slected AVailability","ND_Availability");
                new AsyncTaskHelper().execute(bundle);
            }
        });

    }
    //
    // class to be used to asyn task in background
    private class AsyncTaskHelper extends AsyncTask<Bundle,Void,Void>{


        @Override
        protected Void doInBackground(Bundle... bundles) {
         if(bundles!=null){
             UpdatingAvailability(bundles);
         }

            return null;
        }
        //
        // this method updates availability to database;
        private void UpdatingAvailability(Bundle... bundles){
            Bundle b = bundles[0];
            int day = b.getInt("day_id");
            int empNo = b.getInt("emp_no");
            String availabilityToBeUpdated=b.getString("slected AVailability");
            int availability_status = b.getInt("availability_status");
            if(databaseHelper.UpdateAvailability(day,empNo,availability_status,availabilityToBeUpdated)){
                Log.i("tag", "successfull");
            }
        }


    }

    @Override
    public int getItemCount() {
        return weekDays.length;
    }

    public class ViewAvailability extends RecyclerView.ViewHolder {
        TextView day;
       CheckBox amcheckbox, pmcheckbox, ndcheckbox, unAvaickbox;

        public ViewAvailability(@NonNull View itemView) {
            super(itemView);
            Init(itemView);

        }

        private void Init(View itemView) {
            day = itemView.findViewById(R.id.day);
           amcheckbox = itemView.findViewById(R.id.am);
            pmcheckbox = itemView.findViewById(R.id.pm);
            ndcheckbox = itemView.findViewById(R.id.nd);
            unAvaickbox = itemView.findViewById(R.id.un);
        }



        public TextView getTextView() {
            return day;
        }

    }
}

