package com.example.charnpreet.shiftswap;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Employee implements Parcelable {
    String name=null;
    Integer emp_no = null;
    String emp_Password=null;
    String company_name=null;
    String company_loc= null;
    String company_loc_state=null;
    String emailAddress=null;
    String emp_positon=null;

    public Employee() {

    }

    public String getEmp_positon() {
        return emp_positon;
    }

    public Integer getEmp_no() {
        return emp_no;
    }

    public String getCompany_loc() {
        return company_loc;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getEmp_Password() {
        return emp_Password;
    }

    public String getGetCompany_loc_state() {
        return company_loc_state;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setCompany_loc(String company_loc) {
        this.company_loc = company_loc;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setEmp_no(Integer emp_no) {
        this.emp_no = emp_no;
    }

    public void setEmp_Password(String emp_Password) {
        this.emp_Password = emp_Password;
    }

    public void setGetCompany_loc_state(String getCompany_loc_state) {
        this.company_loc_state = getCompany_loc_state;
    }

    public void setEmp_positon(String emp_positon) {
        this.emp_positon = emp_positon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    protected Employee(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            emp_no = null;
        } else {
            emp_no = in.readInt();
        }
        emp_Password = in.readString();
        company_name = in.readString();
        company_loc = in.readString();
        company_loc_state = in.readString();
        emailAddress = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        if (emp_no == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(emp_no);
        }
        parcel.writeString(emp_Password);
        parcel.writeString(company_name);
        parcel.writeString(company_loc);
        parcel.writeString(company_loc_state);
        parcel.writeString(emailAddress);
    }
    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}
