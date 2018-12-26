package com.example.charnpreet.shiftswap;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class chat_fragment_for_individual_adapter extends RecyclerView.Adapter<chat_fragment_for_individual_adapter.chat_fragment_for_individual_adapter_view_holder> {
    private List<Message> messages;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    chat_fragment_for_individual_adapter(ArrayList messages){
        this.messages = messages;

    }


    @NonNull
    @Override
    public chat_fragment_for_individual_adapter_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.individual_chat_fragment_adapter_layout,viewGroup, false);
        return new chat_fragment_for_individual_adapter_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chat_fragment_for_individual_adapter_view_holder chat_fragment_for_individual_adapter_view_holder, int i)
    {
        String sender_id = user.getUid();
        Message message = messages.get(i);
        String fromUserId = message.getFrom();
        if(fromUserId.equals(sender_id)){
            chat_fragment_for_individual_adapter_view_holder.outGoingText.setBackgroundColor(Color.WHITE);
            chat_fragment_for_individual_adapter_view_holder.outGoingText.setTextColor(Color.BLACK);
            chat_fragment_for_individual_adapter_view_holder.outGoingText.setGravity(Gravity.RIGHT);
            //chat_fragment_for_individual_adapter_view_holder.outGoingText.setText(message.getMessage());
        }else {
            chat_fragment_for_individual_adapter_view_holder.outGoingText.setBackgroundColor(Color.GREEN);
            chat_fragment_for_individual_adapter_view_holder.outGoingText.setTextColor(Color.BLACK);
            chat_fragment_for_individual_adapter_view_holder.outGoingText.setGravity(Gravity.LEFT);
            //chat_fragment_for_individual_adapter_view_holder.outGoingText.setText(message.getMessage());
        }
        chat_fragment_for_individual_adapter_view_holder.outGoingText.setText(message.getMessage());

    }

    @Override
    public int getItemCount() {
        if(messages!=null){
            return messages.size();
        }else {
            return 0;
        }

    }


    public class chat_fragment_for_individual_adapter_view_holder extends RecyclerView.ViewHolder {
        private TextView outGoingText;

        public chat_fragment_for_individual_adapter_view_holder(@NonNull View itemView) {
            super(itemView);
            Init(itemView);
        }

        private void Init(View itemView){
            //((MainActivity)(getActivity())).setSupportActionBar(toolbar);
            outGoingText=itemView.findViewById(R.id.outgoingtext);

        }

    }
}
