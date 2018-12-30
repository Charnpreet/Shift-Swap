package com.example.charnpreet.shiftswap;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat_fragment_for_individual_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messages;
    private static final int INCOMINGMESSAGE = 2;
    private static final int OUTGOINGMESSAGE = 0;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    chat_fragment_for_individual_adapter(ArrayList messages){
        this.messages = messages;


    }

    @Override
    public int getItemViewType(int position) {
        String sender_id = user.getUid();
        if(messages.get(position).getFrom().equals(sender_id)){
            return  OUTGOINGMESSAGE;

        }else {
            return INCOMINGMESSAGE;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType){
            case 0:
                view  =  inflater.inflate(R.layout.outgoing_personal_messages,viewGroup, false);
                return new chat_fragment_for_individual_adapter_outgoing_view_holder(view);
            case 2:
                view = inflater.inflate(R.layout.incoming_personal_messages, viewGroup,false);
                return new chat_fragment_for_individual_adapter_incoming_view_holder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String sender_id = user.getUid();
        Message message = messages.get(i);
        String fromUserId = message.getFrom();
            if(fromUserId.equals(sender_id)) {
                chat_fragment_for_individual_adapter_outgoing_view_holder chat_fragment_for_individual_adapter_outgoing_view_holder = new chat_fragment_for_individual_adapter_outgoing_view_holder(viewHolder.itemView);
                chat_fragment_for_individual_adapter_outgoing_view_holder.outGoingText.setText(message.getMessage());
            }
      else {
                chat_fragment_for_individual_adapter_incoming_view_holder chat_fragment_for_individual_adapter_incoming_view_holder = new chat_fragment_for_individual_adapter_incoming_view_holder(viewHolder.itemView);
                chat_fragment_for_individual_adapter_incoming_view_holder.incomingText.setText(message.getMessage());
                chat_fragment_for_individual_adapter_incoming_view_holder.incomingText.setTextColor(Color.BLACK);
                chat_fragment_for_individual_adapter_incoming_view_holder.incomingText.setGravity(Gravity.RIGHT);


        }
    }

    @Override
    public int getItemCount() {
        if(messages!=null){
            return messages.size();
        }else {
            return 0;
        }

    }
    public class chat_fragment_for_individual_adapter_incoming_view_holder extends RecyclerView.ViewHolder{
        private TextView incomingText;
        private CircleImageView imageView;
        public chat_fragment_for_individual_adapter_incoming_view_holder(@NonNull View itemView) {
            super(itemView);
            Init(itemView);
        }
        private void Init(View itemView){
            //((MainActivity)(getActivity())).setSupportActionBar(toolbar);
            incomingText=itemView.findViewById(R.id.incoming_messages);
            imageView = itemView.findViewById(R.id.imageView_for_1_1_chat_incoming);

        }
    }

    public class chat_fragment_for_individual_adapter_outgoing_view_holder extends RecyclerView.ViewHolder {
        private TextView outGoingText;
        private CircleImageView imageView;

        public chat_fragment_for_individual_adapter_outgoing_view_holder(@NonNull View itemView) {
            super(itemView);
            Init(itemView);
        }

        private void Init(View itemView){
            //((MainActivity)(getActivity())).setSupportActionBar(toolbar);
            outGoingText=itemView.findViewById(R.id.outgoingtext);
            imageView = itemView.findViewById(R.id.imageView_for_1_1_chat);

        }

    }
}
