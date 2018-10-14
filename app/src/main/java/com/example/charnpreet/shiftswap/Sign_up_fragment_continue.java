package com.example.charnpreet.shiftswap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private Button signUp;
    String selectedState, selectedCompany, selectedPosition, selectedLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sign_up_fragment_continue, container, false);
        Init();
        return rootView;
    }
    private void Init(){
        employee = RecieveBundle();
        signUp = rootView.findViewById(R.id.signUp_button);
        comapnay_Name_spiner = rootView.findViewById(R.id.company_name_spinner);
        company_Position_sppiner = rootView.findViewById(R.id.position_spinner);
        company_state_sppiner= rootView.findViewById(R.id.company_state_spinner);
        locationSppiner=    rootView.findViewById(R.id.location_sppiner);
        signUp.setOnClickListener(this);
        SettingUp_comapnay_Name_Spinner();
        SettingUp_company_Position_sppiner();
        SettingUp_company_state_sppiner();
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
        if(AllSectionsFilled(selectedState, selectedCompany, selectedPosition, selectedLocation)){
            Log.i("tag","sign -up Button Clicked");
            if(employee!=null){
                ExtractValues(employee);
                new AsyncTaskHelper().execute(employee);
            }

        }

    }
    //
    // class to be used to asyn task in background
    private class AsyncTaskHelper extends AsyncTask<Employee,Void,Void> {
        private ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(Employee... employees) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Processing.......");
            progressDialog.setMessage("Saving Details to DataBase....");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i("tag","details saved successfully");
        }
    }
    //
    //
    // checks  wether all sections are filled or not for sign in frgament
    public  boolean AllSectionsFilled(String selectedShift,String selectedCompany, String selectedPosition, String selectedLocation){
        if (!TextUtils.isEmpty(selectedShift) && !TextUtils.isEmpty(selectedCompany) && !TextUtils.isEmpty(selectedPosition) && !TextUtils.isEmpty(selectedLocation))
        {
            return  true;
        }
        return false;
    }
    //
    //
    private void SettingUp_comapnay_Name_Spinner(){

       final String[] companyOptions = new String[]{"Select your company","Regis","Blue Cross","Buppa"};
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
        final String[] companyPositionOptions = new String[]{"Select your Position","Pca","Rn","En","Fsa"};
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
    //
    //
    private void SettingUp_company_state_sppiner(){
        final String[] StateOptions = new String[]{"Select State","VIC","NSW","SA","WA","TAS"};
        ArrayAdapter<String> spinnerAdapterForSppiner = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, StateOptions);
        company_state_sppiner.setAdapter(spinnerAdapterForSppiner);
        company_state_sppiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0){
                    selectedState= StateOptions[i];
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
    private void SettingUp_company_location_sppiner(){
        final String[] LocationOptions = new String[]{"Select Location","Malvern East","Blackburne","Dandenong","Armadale","Mildura"};
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

}
