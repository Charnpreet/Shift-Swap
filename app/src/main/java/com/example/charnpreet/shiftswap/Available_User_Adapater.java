package com.example.charnpreet.shiftswap;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Available_User_Adapater extends RecyclerView.Adapter< Available_User_Adapater.Available_user_view_Holder>  {


    @NonNull
    @Override
    public Available_user_view_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.available_user_list_for_view_adapater, viewGroup,false);
        return new Available_user_view_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Available_user_view_Holder available_user_view_holder, int i) {
        available_user_view_holder.user.setText("charn");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class Available_user_view_Holder extends RecyclerView.ViewHolder{
        TextView user;

        public Available_user_view_Holder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.available_user_text_view);
        }
    }
}
