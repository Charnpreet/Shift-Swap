package com.example.charnpreet.shiftswap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class change_password extends Fragment implements View.OnClickListener {
    private View view;
    Button saveButton;
    EditText currentPassword, newPassword, confirmPassword;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Utility utility = Utility.getUtility();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.change_password_fragment, container, false);
        Init();
        return view;
    }

    //
    // compare two Strings
    // does not work as expected
    // returns false even strings are same
    private boolean passwordMatching(String password1, String password2){
        if(password1.equals(password2)){
            return  true;
        }
        return  false;
    }

    private void CheckingCredentials(String password){
        FirebaseAuth myAuth = FirebaseAuth.getInstance();
       String email =  user.getEmail();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        assert email != null;
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);
       user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   UpdatingDetails();
               }else{
                   currentPassword.setError("Current Password Does not Match");
               }
           }
       });
    }

    private void UpdatingDetails() {
        String password = newPassword.getText().toString().trim();
        String confPassword = confirmPassword.getText().toString().trim();
        if (utility.ISfilled(newPassword, password)) {
            if (utility.ISfilled(confirmPassword, confPassword)) {
                if (passwordMatching(password, confPassword)) {
                    user.updatePassword(confPassword);
                    Toast.makeText(view.getContext(), "Password saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(view.getContext(), "Your new and confirm passwords Does not match", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    private void Init(){
        currentPassword = view.findViewById(R.id.current_paasword);
        newPassword=view.findViewById(R.id.new_password);
        confirmPassword= view.findViewById(R.id.confirm_password);
        saveButton= view.findViewById(R.id.save_password_button);
        saveButton.setOnClickListener(this);
    }

    public void SetFlags(){
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    public void ClearFlags(){
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    @Override
    public void onClick(View view) {
        String currentpassword= currentPassword.getText().toString().trim();
        if(utility.ISfilled(currentPassword, currentpassword)){
         CheckingCredentials(currentpassword);
        }

    }
}