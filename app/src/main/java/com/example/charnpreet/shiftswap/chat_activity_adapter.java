package com.example.charnpreet.shiftswap;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class chat_activity_adapter extends RecyclerView.Adapter<chat_activity_adapter.chat_activity_adapter_view_holder> {
    private ArrayList<Users> availUsers;

    public chat_activity_adapter(ArrayList<Users> users){
            availUsers = users;
    }

    @NonNull
    @Override
    public chat_activity_adapter_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.chat_activity_adapter_layout,viewGroup, false);
        return new chat_activity_adapter_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chat_activity_adapter_view_holder chat_activity_adapter_view_holder, int i) {
        chat_activity_adapter_view_holder.user.setText(availUsers.get(i).getName().toUpperCase());

    }

    @Override
    public int getItemCount() {
        if(availUsers!=null){
            return availUsers.size();
        }
        else {
            return 0;
        }
    }

    public class chat_activity_adapter_view_holder extends RecyclerView.ViewHolder{
        TextView user;
        public chat_activity_adapter_view_holder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.forchat_activity_adapter_text_view);

//            parentlayout=itemView.findViewById(R.id.parentlayout);
//            parentlayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Log.i("singh", " you clicked at " + user.getText().toString());
//               }
//            });
        }
    }
}
