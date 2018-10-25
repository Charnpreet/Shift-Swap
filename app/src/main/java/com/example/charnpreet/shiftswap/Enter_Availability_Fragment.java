package com.example.charnpreet.shiftswap;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// this class is to view data
// class name need to be changed
public class Enter_Availability_Fragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    DatabaseHelper databaseHelper;
    Utility utility;
    Cursor cursor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.enter_availability_fragment,container,false);
        InitRecyclerView(view);
        return view;
    }
    private void InitRecyclerView(View view){
        recyclerView = view.findViewById(R.id.enteravailability);
        databaseHelper = new DatabaseHelper(view.getContext());
        utility= Utility.getUtility();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new Enter_Availability_Adapter(utility.ExecutingDaysQuerry(databaseHelper, cursor), view.getContext()));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Availability");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }

    }
}
