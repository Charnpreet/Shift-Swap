package com.example.charnpreet.shiftswap.chat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.charnpreet.shiftswap.R;
import com.example.charnpreet.shiftswap.classes.Users;
import com.example.charnpreet.shiftswap.utility.Utility;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat_activity_adapter extends RecyclerView.Adapter<chat_activity_adapter.chat_activity_adapter_view_holder>{
    private ArrayList<Users> availUsers;
    AppCompatActivity activity;
    public static String messageRecieverKey;
    View view;
    Utility utility = Utility.getUtility();
    public chat_activity_adapter(ArrayList<Users> users, AppCompatActivity activity) {
            availUsers = users;
            this.activity = activity;
    }

    @NonNull
    @Override
    public chat_activity_adapter_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.chat_activity_adapter_layout,viewGroup, false);
        return new chat_activity_adapter_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final chat_activity_adapter_view_holder chat_activity_adapter_view_holder, int i) {
        chat_activity_adapter_view_holder.user.setText(availUsers.get(i).getName().toUpperCase());
        utility.LoadPicFromServerAndDisplayToUser(view.getContext(), availUsers.get(i).getUrl(),chat_activity_adapter_view_holder.imageView);
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
        private CircleImageView imageView;
        public chat_activity_adapter_view_holder(@NonNull final View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.forchat_activity_adapter_text_view);
            imageView=itemView.findViewById(R.id.imageView_forchat_activity_adapter);
            /*
            * setting up a listner on each item of recyler view
            * not a good practise as a lot listners will be created if recyler view item list grows
            * but as listners are very light wieghted objects it may not show visible signs on app
            * */
            itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        individualchatActivity (view, getAdapterPosition());
                    }
                });

        }
        /*
        * starts new activity to hold fragments
        * below activity will act as a container to hold all fragments related to individual chats
        * */
        private void individualchatActivity(View view, int id){
            messageRecieverKey = availUsers.get(id).getKey();;
            Intent myintent = new Intent(view.getContext(), individual_chat_Holder_Activity.class);
            //
            // passing selected user userkey
            // this has not been used
            // as intents has been using old value
            // intent will reuse same old value which placed first time inside the intents
           // myintent.putExtra("key", key);
           // myintent.setFlags((Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            (view.getContext()).startActivity(myintent);
        }


    }
}
