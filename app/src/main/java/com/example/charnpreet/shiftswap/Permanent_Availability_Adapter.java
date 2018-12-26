package com.example.charnpreet.shiftswap;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Permanent_Availability_Adapter extends RecyclerView.Adapter<Permanent_Availability_Adapter.PermanentAvailability > {
    View view;
    ArrayList<String> weekDays =null;
    FirebaseDatabase database;

    public Permanent_Availability_Adapter(ArrayList<String> weekDays ) {
        this.weekDays=weekDays;

    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public PermanentAvailability onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from((viewGroup.getContext()));
        view = inflater.inflate(R.layout.enteravailability, viewGroup, false);
        return new PermanentAvailability (view);
    }
    //
    //
    @Override
    public void onBindViewHolder(@NonNull PermanentAvailability  viewAvailability, int i) {
        viewAvailability.day.setText(weekDays.get(i));
        clickableListener(viewAvailability, i);
       DownloadAvailabilityFromDatabase(viewAvailability, i);
    }

    private void DownloadAvailabilityFromDatabase(final PermanentAvailability viewAvailability, final int i){

        database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        DatabaseReference mRef = database.getReference().child("UserAvailability").child("PermanentAvailability").child(user.getUid());
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long AM = (Long) dataSnapshot.child(weekDays.get(i)).child("AM").getValue();
                Long PM = (Long) dataSnapshot.child(weekDays.get(i)).child("PM").getValue();
                Long ND = (Long) dataSnapshot.child(weekDays.get(i)).child("ND").getValue();
               if((AM!=null)&&(PM!=null)&&(ND!=null)) {
                   if (AM >0) {
                       viewAvailability.amcheckbox.setChecked(true);
                   }
                   if (PM > 0) {
                       viewAvailability.pmcheckbox.setChecked(true);
                   }

                   if (ND > 0) {
                       viewAvailability.ndcheckbox.setChecked(true);
                   }
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    //
    //
    //
    private void clickableListener(final PermanentAvailability  viewAvailability, final int i){
        database = FirebaseDatabase.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference mRef = database.getReference().child("UserAvailability").child("PermanentAvailability").child(user.getUid());
       viewAvailability.amcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewAvailability.amcheckbox.isChecked()){
                    mRef.child(weekDays.get(i)).child("AM").setValue(1);


                }else{
                    mRef.child(weekDays.get(i)).child("AM").setValue(0);
                }
           }
        });
       //
       //
        //
       viewAvailability.pmcheckbox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (viewAvailability.pmcheckbox.isChecked()){
                   mRef.child(weekDays.get(i)).child("PM").setValue(1);

               }else{
                   mRef.child(weekDays.get(i)).child("PM").setValue(0);
               }
//
           }
       });
       //
        //
        //
        viewAvailability.ndcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewAvailability.ndcheckbox.isChecked()){
                    mRef.child(weekDays.get(i)).child("ND").setValue(1);
                }else {
                    mRef.child(weekDays.get(i)).child("ND").setValue(0);
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return weekDays.size();
    }

    public class PermanentAvailability extends RecyclerView.ViewHolder {
        TextView day;
       CheckBox amcheckbox, pmcheckbox, ndcheckbox;

        public PermanentAvailability (@NonNull View itemView) {
            super(itemView);
            Init(itemView);

        }

        private void Init(View itemView) {
            day = itemView.findViewById(R.id.day);
            amcheckbox = itemView.findViewById(R.id.am);
            pmcheckbox = itemView.findViewById(R.id.pm);
            ndcheckbox = itemView.findViewById(R.id.nd);
        }



        public TextView getTextView() {
            return day;
        }

    }
}

