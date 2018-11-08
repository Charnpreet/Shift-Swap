package com.example.charnpreet.shiftswap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Sign_up_fragment_continue extends Fragment implements View.OnClickListener {
    Spinner comapnay_Name_spiner, company_Position_sppiner, company_state_sppiner, locationSppiner;
    View rootView;
    Employee employee=null;
    DatabaseHelper databaseHelper;
    Cursor cursor;
    private Button signUp;
    String selectedState, selectedCompany, selectedPosition, selectedLocation;
    View coordinateVIewFOrSnackBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sign_up_fragment_continue, container, false);
        Init();
        return rootView;
    }
    private void Init(){
        employee = RecieveBundle();
        coordinateVIewFOrSnackBar=rootView.findViewById(R.id.myCoordinatorLayout);
        signUp = rootView.findViewById(R.id.signUp_button);
        comapnay_Name_spiner = rootView.findViewById(R.id.company_name_spinner);
        company_Position_sppiner = rootView.findViewById(R.id.position_spinner);
        locationSppiner=    rootView.findViewById(R.id.location_sppiner);
        AfterLogin.LoginEmployee_No=employee.emp_no;
        databaseHelper = new DatabaseHelper(rootView.getContext());
        signUp.setOnClickListener(this);
        SettingUp_comapnay_Name_Spinner();
        SettingUp_company_Position_sppiner();
        SettingUp_company_location_sppiner();
    }
    //
    //
    private Employee RecieveBundle(){
        Bundle bundle = getArguments();
        return (Employee) bundle.getParcelable("employee");
    }
    //
    //
    private  void ExtractValues(Employee employee){
        employee.company_name = selectedCompany;
        employee.company_loc_state=selectedState;
        employee.emp_positon = selectedPosition;
        employee.company_loc= selectedLocation;
    }
    @Override
    public void onClick(View view) {
        if(AllSectionsFilled(selectedCompany, selectedPosition, selectedLocation)){
            if(employee!=null){
                ExtractValues(employee);
                new AsyncTaskHelper().execute(employee);
            }

        }else{
            Snackbar.make(coordinateVIewFOrSnackBar, "Make Sure you select all the sections", Snackbar.LENGTH_LONG).show();
        }

    }
    //
    //
    // checks  wether all sections are filled or not for sign in frgament
    public  boolean AllSectionsFilled(String selectedCompany, String selectedPosition, String selectedLocation){
        if (!TextUtils.isEmpty(selectedCompany) && !TextUtils.isEmpty(selectedPosition) && !TextUtils.isEmpty(selectedLocation))
        {
            return  true;
        }
        return false;
    }
    //
    //
    private void SettingUp_comapnay_Name_Spinner(){
        cursor= databaseHelper.getCompanyNames();
       final String[] companyOptions = ExcuteAQuery(cursor);
        ArrayAdapter<String> spinnerAdapterForSppiner = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, companyOptions);
        comapnay_Name_spiner.setAdapter(spinnerAdapterForSppiner);
            comapnay_Name_spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(i!=0){
                        selectedCompany= companyOptions[i];
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
    }
    //
    //
    private void SettingUp_company_Position_sppiner(){
        if(cursor!=null){
            cursor=null;
            cursor= databaseHelper.getCompanyPosition();
        }else{
            cursor= databaseHelper.getCompanyPosition();
        }
        final String[] companyPositionOptions = ExcuteAQuery(cursor);
        ArrayAdapter<String> spinnerAdapterForSppiner = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, companyPositionOptions);
        company_Position_sppiner .setAdapter(spinnerAdapterForSppiner);
        company_Position_sppiner .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0){
                    selectedPosition= companyPositionOptions[i];
                }
            }
            ;
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    //
    private String[]  ExcuteAQuery(Cursor cursor) {
        String [] Options = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i =0; i<Options.length; i++){
            Options[i]= cursor.getString(0);
            cursor.moveToNext();
        }
        return Options;
    }
    //
    //
    private void SettingUp_company_location_sppiner(){
        cursor=null;
        cursor=databaseHelper.getCompanyLocations();
        final String[] LocationOptions = ExcuteAQuery(cursor);
        ArrayAdapter<String> spinnerAdapterForSppiner = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, LocationOptions);
        locationSppiner.setAdapter(spinnerAdapterForSppiner);
        locationSppiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0){
                    selectedLocation= LocationOptions[i];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    //
    // class to be used to asyn task in background
    private class AsyncTaskHelper extends AsyncTask<Employee,Void,Void> {
        private ProgressDialog progressDialog;
        int companyID;
        int positionID;
        int locationID;

        @Override
        protected Void doInBackground(Employee... employees) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Signingupuser();
            publishProgress();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        // this is used to sign up user
        private void Signingupuser(){
            cursor = CursorForCopanyID(cursor);
            cursor.moveToNext();
            companyID = cursor.getInt(0);
            cursor = null;
            cursor = CursorForLocID(cursor);
            cursor.moveToNext();
            locationID = cursor.getInt(0);
            cursor = null;
            cursor = CursorForPosID(cursor);
            cursor.moveToNext();
            positionID = cursor.getInt(0);
            if (databaseHelper.AddToEmployee(employee.emp_no, employee.emailAddress, employee.name, companyID, positionID)) {
                Log.i("tag","details saved to employee table successfully");
            }
            if(databaseHelper.AddToEmployeeCredentialTable(employee.emp_no, employee.emp_Password)){
                Log.i("tag","details saved to employee credential  table successfully");
            }
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(rootView.getContext());
            progressDialog.setTitle("Processing.......");
            progressDialog.setMessage("Saving Details....");
            progressDialog.show();
        }
        // this method is used setup default availbility for a user
        // it will only be called when user signed up for first time
        private void AddDefaultAvailability(){
            for(int i =0; i<7; i++){
                if(databaseHelper.AddToEmployeeAvailability(0,0,0,AfterLogin.LoginEmployee_No ,i)){
                    Log.i("tag", "avialbility  saved");
                }
            }
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AddDefaultAvailability();
            progressDialog.dismiss();
            RedirectBackToMainScreen();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            progressDialog.setTitle("Finished.......");
            progressDialog.setMessage("Details Saved \n Loading sign in page....");

        }

        //
        // this method is used to redirect user back to login screen
        private void RedirectBackToMainScreen(){
            sign_in_fragment sign_in_fragment = new sign_in_fragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_activity_fragment, sign_in_fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }
    //

    private Cursor CursorForCopanyID(Cursor cursor){
       cursor= databaseHelper.getCompanyNameID(selectedCompany);
        return cursor;
    }
    //
    //
    private Cursor CursorForLocID(Cursor cursor){
        cursor= databaseHelper.getCompanyLocationID(selectedLocation);
        return cursor;
    }
    //
    //
    private Cursor CursorForPosID(Cursor cursor){
        cursor= databaseHelper.getCompanyPositionID(selectedPosition);
        return cursor;
    }

}
