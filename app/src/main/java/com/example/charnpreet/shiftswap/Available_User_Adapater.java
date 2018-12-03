package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class Available_User_Adapater extends RecyclerView.Adapter< Available_User_Adapater.Available_user_view_Holder>  {

private ArrayList<Users> availUsers;

    public Available_User_Adapater(ArrayList<Users> availUsers){
        this.availUsers =availUsers;
    }

    @NonNull
    @Override
    public Available_user_view_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.available_user_list_for_view_adapater, viewGroup,false);
        return new Available_user_view_Holder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Available_user_view_Holder available_user_view_holder, int i) {
            available_user_view_holder.user.setText(availUsers.get(i).getName().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return availUsers.size();
    }

    public class Available_user_view_Holder extends RecyclerView.ViewHolder {
        TextView user;
        FloatingActionButton addbutton;

        public Available_user_view_Holder(@NonNull final View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.available_user_text_view);
            addbutton = itemView.findViewById(R.id.floatingActionButton3);
            addbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), user.getText().toString() + " is added to your chat list", Toast.LENGTH_SHORT).show();
                }
            });
//            parentlayout=itemView.findViewById(R.id.parentlayout);
//            parentlayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.i("singh", " you clicked at " + user.getText().toString());
//                }
//            });

        }
    }
}
