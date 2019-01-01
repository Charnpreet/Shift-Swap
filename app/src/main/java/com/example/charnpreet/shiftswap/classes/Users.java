package com.example.charnpreet.shiftswap.classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
    private String name="";
    private String dob="";
    private String key="";
    private String url="";
    private Long MObNumber;

    protected Users(Parcel in) {
        name = in.readString();
        dob = in.readString();
        if (in.readByte() == 0) {
            MObNumber = null;
        } else {
            MObNumber = in.readLong();
        }
    }

    public Users() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getMobNo() {
        return MObNumber;
    }

    public void setMobNo(Long mobNo) {
        this.MObNumber = mobNo;
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
        parcel.writeString(key);
        parcel.writeString(url);
        if (MObNumber == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(MObNumber);
        }
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
