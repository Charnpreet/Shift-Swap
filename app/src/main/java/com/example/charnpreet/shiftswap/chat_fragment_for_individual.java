package com.example.charnpreet.shiftswap;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class chat_fragment_for_individual extends Fragment implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageView backarrow;
    private TextView toolbarTextView,toolbar_text_view;
    private RecyclerView recyclerView;
    private View view;
    private Button sentButton;
    private EditText editTextView;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference rootref = database.getReference();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private List<Message> sentmessagesList = new ArrayList<>();
    Utility utility = Utility.getUtility();
    private String message_sender_ref;
    LinearLayoutManager m;
    chat_fragment_for_individual_adapter chat_fragment_for_individual_adapter;
    public  static final String MessageNode = "ChatMessages";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat_fragment_for_individual_user, container,false);
        Init();
        return view;
    }
    //
    //
    private void Init(){
        toolbar = view.findViewById(R.id.chat_fragment_toolbar);
        toolbar_text_view = view.findViewById(R.id.toolbar_text_view);
        editTextView = view.findViewById(R.id.edittext_view_for_indivdual_chat);
        sentButton = view.findViewById(R.id.button);
        sentButton.setOnClickListener(this);
        backarrow= view.findViewById(R.id.chat_fragment_back_Arrow);
        recyclerView = view.findViewById(R.id.recyclerView_for_individual_chat_fragment);
        backarrow.setOnClickListener(this);
        toolbarTextView = view.findViewById(R.id.chat_fragment_toolbar_text_view);
        InitRecyclerView();
        PersaonalInfo();
    }


    private void PersaonalInfo(){
        String key =  chat_activity_adapter.messageRecieverKey;
        DatabaseReference mRef = database.getReference().child(Utility.Users).child(key).child(Utility.Profile);
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users user = (Users) dataSnapshot.getValue(Users.class);

                String users = user.getName();
                toolbar_text_view.setText(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    /*
     *
     * */
    private void FetchSentMessages(){
        String senderKey = user.getUid();
        String recieverKey = chat_activity_adapter.messageRecieverKey;
        final String key = utility.messageKey(senderKey,recieverKey);
        rootref.child(MessageNode).child(key)
          .addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                sentmessagesList.add(message);
                chat_fragment_for_individual_adapter.notifyDataSetChanged();
                m.scrollToPosition(sentmessagesList.size()-1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                sentmessagesList.clear();
               chat_fragment_for_individual_adapter.notifyDataSetChanged();
            }


            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                chat_fragment_for_individual_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
     *
     * */
    private Map MessageBody(String textMessage){

        String senderKey = user.getUid();
        Map messageTextBody = new HashMap();
        messageTextBody.put("message", textMessage);
        messageTextBody.put("time", ServerValue.TIMESTAMP);
        messageTextBody.put("type", "text");
        messageTextBody.put("from", senderKey);
        return  messageTextBody;
    }
    /*
     *
     * */
    private Map MessageBodyDetails(String message_uinique_Key,String textMessage){
        Map messageBodyDetails = new HashMap();
        messageBodyDetails.put(message_sender_ref +"/" + message_uinique_Key, MessageBody(textMessage));
        return messageBodyDetails;
    }



    /*
    *
    * */
    private void SavingDataToDataBase(String textMessage){
        String senderKey = user.getUid();
        String recieverKey =  chat_activity_adapter.messageRecieverKey;
        message_sender_ref = MessageNode+"/" + utility.messageKey(senderKey,recieverKey);
        DatabaseReference mref = database.getReference().child(MessageNode).child(message_sender_ref).push();
        /*
        * unique key for individual message
        * */
        String message_uinique_Key = mref.getKey();
        Map messagebody = MessageBodyDetails(message_uinique_Key,textMessage);

        rootref.updateChildren(messagebody, new DatabaseReference.CompletionListener() {
           @Override
           public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference)
           {
            if(databaseError!=null){
                Log.i("singh", databaseError.getMessage());
            }else{
                editTextView.setText("");
            }
           }
       });
    }

    /*
     *
     * */
    private void SendMessage(){
        String textMessage = editTextView.getText().toString().trim();
        if(TextUtils.isEmpty(textMessage)){
            Toast.makeText(view.getContext(), "Please write your message", Toast.LENGTH_SHORT).show();
        }else{
            SavingDataToDataBase(textMessage);
        }

    }
    private void InitRecyclerView(){
        chat_fragment_for_individual_adapter = new chat_fragment_for_individual_adapter((ArrayList) sentmessagesList); //sentmessagesList
         m = new LinearLayoutManager(view.getContext());
        m.setStackFromEnd(true);
        recyclerView.setLayoutManager(m);
        recyclerView.setAdapter(chat_fragment_for_individual_adapter);
        FetchSentMessages();
    }
    /*
    *
    * */
    @Override
    public void onClick(View view) {
        /* this calls individual_chat_Holder_Activity activity,
         *  and individual_chat_Holder_Activity shows last hosted fragment
         * */
        if(view.getId()==R.id.chat_fragment_back_Arrow){
            Intent myintent = new Intent(view.getContext(), Chat_Activity.class);
            myintent.setFlags((Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
            (view.getContext()).startActivity(myintent);
        }
        else{
            SendMessage();
        }
    }

}
