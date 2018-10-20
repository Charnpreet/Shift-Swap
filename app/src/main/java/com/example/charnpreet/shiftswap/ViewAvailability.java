package com.example.charnpreet.shiftswap;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/// view holder class
public class ViewAvailability extends RecyclerView.ViewHolder  {
    TextView day;
    CheckBox amcheckbox, pmcheckbox, ndcheckbox,unAvaickbox;
    public ViewAvailability(@NonNull View itemView) {
        super(itemView);
        Init(itemView);

    }
    private  void Init(View itemView){
        day = itemView.findViewById(R.id.day);
        amcheckbox = itemView.findViewById(R.id.am);
        pmcheckbox = itemView.findViewById(R.id.pm);
        ndcheckbox= itemView.findViewById(R.id.nd);
        unAvaickbox=itemView.findViewById(R.id.un);
    }
    public TextView getTextView() {
        return day;
    }
}
