package com.example.charnpreet.shiftswap.chat;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.charnpreet.shiftswap.classes.Message;
import com.example.charnpreet.shiftswap.R;
import com.example.charnpreet.shiftswap.main_Page.MainActivity;
import com.example.charnpreet.shiftswap.utility.Utility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat_fragment_for_individual_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messages;
    private static final int INCOMINGMESSAGE = 2;
    private static final int OUTGOINGMESSAGE = 0;
    View view;
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

    private void LoadingProfileImg(String key, final ImageView imgView){
        final DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        databaseRef.child(Utility.Users).child(key).child(Utility.Profile)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(Utility.url)){
                        String url =dataSnapshot.child(Utility.url).getValue().toString();
                            Glide.with(view.getContext())
                            .load(url).into(imgView);
                    }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        String sender_id = user.getUid();
        Message message = messages.get(i);
        String fromUserId = message.getFrom();
            if(fromUserId.equals(sender_id)) {
                chat_fragment_for_individual_adapter_outgoing_view_holder chat_fragment_for_individual_adapter_outgoing_view_holder = new chat_fragment_for_individual_adapter_outgoing_view_holder(viewHolder.itemView);
                chat_fragment_for_individual_adapter_outgoing_view_holder.outGoingText.setText(message.getMessage());
                LoadingProfileImg(sender_id, chat_fragment_for_individual_adapter_outgoing_view_holder.imageView);

            }
      else {
                chat_fragment_for_individual_adapter_incoming_view_holder chat_fragment_for_individual_adapter_incoming_view_holder = new chat_fragment_for_individual_adapter_incoming_view_holder(viewHolder.itemView);
                chat_fragment_for_individual_adapter_incoming_view_holder.incomingText.setText(message.getMessage());
                chat_fragment_for_individual_adapter_incoming_view_holder.incomingText.setTextColor(Color.BLACK);
                chat_fragment_for_individual_adapter_incoming_view_holder.incomingText.setGravity(Gravity.RIGHT);
                LoadingProfileImg(fromUserId, chat_fragment_for_individual_adapter_incoming_view_holder.imageView);


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
           // ((MainActivity)(getActivity())).setSupportActionBar(toolbar);
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
