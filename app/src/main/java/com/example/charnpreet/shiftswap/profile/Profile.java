package com.example.charnpreet.shiftswap.profile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.charnpreet.shiftswap.R;
import com.example.charnpreet.shiftswap.classes.Users;
import com.example.charnpreet.shiftswap.utility.Utility;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;


public class Profile extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView profieImage;
    private TextView name,dob,mob, changeProfileImage;
    private final static int GET_FROM_GALLERY=0;
   private FirebaseStorage storage;
    private StorageReference storageReference;
    FirebaseUser user;
    String url;
    Utility utility = Utility.getUtility();
    private static final String PROFILE = "Profile";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.profile_fragment, container,false);
       Init();
        RetreivingUserProfileDetails();
        utility.LoadPicFromServerAndDisplayToUser(view.getContext(), url,profieImage);
       return view;
    }
    private void Init(){
        changeProfileImage=view.findViewById(R.id.profile_edit_profile_image);
        changeProfileImage.setOnClickListener(this);
        profieImage = view.findViewById(R.id.profile_image);
        name = view.findViewById(R.id.profile_name);
        dob= view.findViewById(R.id.Profile_DOB);
        mob= view.findViewById(R.id.Profile_Mob_no);
        storage = FirebaseStorage.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = storage.getReference().child(Utility.images).child(Utility.users).child(user.getUid()).child(Utility.profilePic);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(PROFILE);
    }


    private void RetreivingUserProfileDetails(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        assert user != null;
        DatabaseReference mRef=database.getReference().child(Utility.Users).child(user.getUid()).child(Utility.Profile);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    Users users = dataSnapshot.getValue(Users.class);
                    assert users != null;
                    name.setText(users.getName());
                    dob.setText(users.getDob());
                    mob.setText(dataSnapshot.child(Utility.MObNumber).getValue().toString());
                    if(dataSnapshot.hasChild(Utility.url)){
                        url = users.getUrl();
                        utility.LoadPicFromServerAndDisplayToUser(view.getContext(), url,profieImage);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    /*
    * this method should update the link in users main profile
    *
    * */
    private void UpdatingUserProfileImge(final Uri dowloadeduri, final ProgressDialog progressDialog){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mRef=database.getReference().child(Utility.Users).child(user.getUid()).child(Utility.Profile).child(Utility.url);
        mRef.setValue(String.valueOf(dowloadeduri)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog.dismiss();
                Toast.makeText(view.getContext(), "image uploaded", Toast.LENGTH_SHORT).show();
            }

        });

    }

    /*
    * this method will be used to save data to database
    * if data is still stored at firebase
    * then it will store in firebase storage
    * */
    private void savingProfileImageToDataBase(final Uri selectedImage){
        final ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
        storageReference.putFile(selectedImage);

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               Uri dowloadeduri = uri;
                UpdatingUserProfileImge(dowloadeduri, progressDialog);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(view.getContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    /*
    * starting an itent for result
    * it basically let user pick an image from galley

    * */
    private void GalaryIntent(){

        startActivityForResult(
        new Intent
        (Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
        GET_FROM_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            savingProfileImageToDataBase(selectedImage);
        }
    }
    /*
    *
    * */
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.profile_edit_profile_image){
            GalaryIntent();
        }

    }
    /*
     ***************************** issues*********************************
     * some images gets rotated automatically when selected from gallery
     *********************************************************************
     * */
}

