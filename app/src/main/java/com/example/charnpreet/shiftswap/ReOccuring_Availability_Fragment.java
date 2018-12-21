package com.example.charnpreet.shiftswap;
import android.database.Cursor;
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

// this class is used to View availability
// it is used with view pager
//
public class ReOccuring_Availability_Fragment extends Fragment {
    RecyclerView recyclerView;
    View view;
    Utility utility;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.view_availability_fragment,container,false);
        InitRecyclerView(view);
        return view;
    }
    //
    //
    private void InitRecyclerView(View view){
        recyclerView = view.findViewById(R.id.enteravailability);
        utility= Utility.getUtility();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
