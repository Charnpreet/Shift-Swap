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
    Intent intent;
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
        //company_state_sppiner= rootView.findViewById(R.id.company_state_spinner);
        locationSppiner=    rootView.findViewById(R.id.location_sppiner);
        signUp.setOnClickListener(this);
        databaseHelper = new DatabaseHelper(rootView.getContext());
        SettingUp_comapnay_Name_Spinner();
        SettingUp_company_Position_sppiner();
        //SettingUp_company_state_sppiner();
        SettingUp_company_location_sppiner();
    }

    private Employee RecieveBundle(){
        Bundle bundle = getArguments();
        return (Employee) bundle.getParcelable("employee");
    }
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
                    Log.i("tag","Please choose 1 company atleast");
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
                Log.i("tag","Please choose 1 company atleast");
            }
        });
    }
//    //
//    //
//    private void SettingUp_company_state_sppiner(){
//        final String[] StateOptions = new String[]{"Select State","VIC","NSW","SA","WA","TAS"};
//
//        ArrayAdapter<String> spinnerAdapterForSppiner = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, StateOptions);
//        company_state_sppiner.setAdapter(spinnerAdapterForSppiner);
//        company_state_sppiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i!=0){
//                    selectedState= StateOptions[i];
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                Log.i("tag","Please choose 1 company atleast");
//            }
//        });
//    }
    //
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
                Log.i("tag","Please choose 1 company atleast");
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
            if (databaseHelper.AddToEmployee(employee.emp_no, employee.name, companyID, positionID)) {
                //return null;
                Log.i("tag","details saved to employee table successfully");
            }
            if(databaseHelper.AddToEmployeeCredentialTable(employee.emp_no, employee.emp_Password)){
                Log.i("tag","details saved to employee credential  table successfully");
            }
            return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
<<<<<<< HEAD
            Snackbar.make(coordinateVIewFOrSnackBar, "Signing Up", Snackbar.LENGTH_LONG).show();
=======
            Snackbar.make(coordinateVIewFOrSnackBar, "Signing Up", Snackbar.LENGTH_INDEFINITE).show();
>>>>>>> 714f9096761a0aab3f6d43105edd582b5a34d051
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i("tag","details saved successfully");
            Snackbar.make(coordinateVIewFOrSnackBar, "details saved successfully", Snackbar.LENGTH_LONG).show();
<<<<<<< HEAD
                RedirectBackToMainScreen();



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
=======
            intent = new Intent(getContext(),MainActivity.class);
             startActivity(intent);

>>>>>>> 714f9096761a0aab3f6d43105edd582b5a34d051
        }
    }
    private Cursor CursorForCopanyID(Cursor cursor){
       cursor= databaseHelper.getCompanyNameID(selectedCompany);
        return cursor;
    }
    private Cursor CursorForLocID(Cursor cursor){
        cursor= databaseHelper.getCompanyLocationID(selectedLocation);
        return cursor;
    }
    private Cursor CursorForPosID(Cursor cursor){
        cursor= databaseHelper.getCompanyPositionID(selectedPosition);
        return cursor;
    }

}
