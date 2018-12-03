package com.example.charnpreet.shiftswap;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

public class Users implements Parcelable {
    private String name="";
    private String dob="";
    private Long mobNo;
    private static Users user =null;

    protected Users(Parcel in) {
        name = in.readString();
        dob = in.readString();
        if (in.readByte() == 0) {
            mobNo = null;
        } else {
            mobNo = in.readLong();
        }
    }

    public Users() {

    }

    //
    //
//    public static  Users UserInstance(){
//        if(user==null){
//            user= new Users();
//        }
//        return  user;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getMobNo() {
        return mobNo;
    }

    public void setMobNo(Long mobNo) {
        this.mobNo = mobNo;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {


        parcel.writeString(name);
        parcel.writeString(dob);
        if (mobNo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(mobNo);
        }
        parcel.writeString(name);
        parcel.writeString(dob);
    }
    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}
