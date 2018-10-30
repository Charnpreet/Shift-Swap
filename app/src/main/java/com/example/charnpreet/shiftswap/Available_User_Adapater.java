package com.example.charnpreet.shiftswap;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
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

private ArrayList<Employee> employees= null;

    public Available_User_Adapater(ArrayList<Employee> employees){
        this.employees=employees;


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
        if((employees.get(i).name.toUpperCase()!=null) && (employees.get(i).emailAddress!=null )){
            available_user_view_holder.user.setText(employees.get(i).name.toUpperCase());
            available_user_view_holder.email.setText(employees.get(i).emailAddress);
        }

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class Available_user_view_Holder extends RecyclerView.ViewHolder implements Button.OnClickListener{
        TextView user, email;
        Button contactButton;

        public Available_user_view_Holder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.available_user_text_view);
            email=itemView.findViewById(R.id.textView8);
            contactButton= itemView.findViewById(R.id.share_intent_button);
            contactButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent result = new Intent();
            result.setAction(Intent.ACTION_SENDTO);
            result.setData(Uri.parse("mailto: " +email.getText().toString()+""));
            result.putExtra(Intent.EXTRA_SUBJECT, "Swap Shifts");
            startActivity(itemView.getContext(), result,null);


        }
    }
}
