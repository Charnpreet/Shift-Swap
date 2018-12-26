package com.example.charnpreet.shiftswap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Profile extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView profieImage;
    private TextView name,dob,mob, changeProfileImage;
    private final static int GET_FROM_GALLERY=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.profile_fragment, container,false);
       Init();
       PushingDetailsToProfile();
       return view;
    }
    private void Init(){
        changeProfileImage=view.findViewById(R.id.profile_edit_profile_image);
        changeProfileImage.setOnClickListener(this);
        profieImage = view.findViewById(R.id.profile_image);
        name = view.findViewById(R.id.profile_name);
        dob= view.findViewById(R.id.Profile_DOB);
        mob= view.findViewById(R.id.Profile_Mob_no);

    }

    private void PushingDetailsToProfile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        assert user != null;
        DatabaseReference mRef=database.getReference().child("Users").child(user.getUid()).child("Profile");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    dob.setText(dataSnapshot.child("dob").getValue().toString());
                    mob.setText(dataSnapshot.child("MObNumber").getValue().toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    /*
    * starting an itent for result
    * it basically let user pick an image from galley

    * */
    private void GalaryIntent(){

        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }
    /*
     **************** issues*****************
     * some images gets rotated automatically when selected from gallery
     *
     * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
               //Bitmap bitmap1= modifyOrientation(bitmap,path);
                if( bitmap!=null){

                    profieImage.setImageBitmap(bitmap);
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                Log.i("singh"," file not found exception " +e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.i("singh", "IOException "+e.getMessage());
                e.printStackTrace();
            }
        }
    }



    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.profile_edit_profile_image){
            GalaryIntent();
        }

    }
}

