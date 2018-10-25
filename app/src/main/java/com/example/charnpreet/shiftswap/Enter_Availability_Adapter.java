package com.example.charnpreet.shiftswap;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class Enter_Availability_Adapter extends RecyclerView.Adapter<Enter_Availability_Adapter.EnterAvailability> {
    View view;
    String[] weekDays=null;
    DatabaseHelper databaseHelper;
    Cursor cursor;
public Enter_Availability_Adapter(String[] weekDays, Context context){
    databaseHelper = new DatabaseHelper(context);
    this.weekDays=weekDays;
}
    @NonNull
    @Override
    public EnterAvailability onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from((viewGroup.getContext()));
        view = inflater.inflate(R.layout.enteravailability, viewGroup, false);
        return new EnterAvailability(view);
    }
    private void DownloadAvailabilityFromDatabase(EnterAvailability enterAvailability, int i){
        cursor = databaseHelper.ReterievingAvailabilityforLoginMember(AfterLogin.LoginEmployee_No,i);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            if((cursor.getInt(0)>0)) {
                enterAvailability.amcheckbox.setChecked(true);
            }
            if((cursor.getInt(1)>0)) {
                enterAvailability.pmcheckbox.setChecked(true);
            }
            if((cursor.getInt(2)>0)) {
                enterAvailability.ndcheckbox.setChecked(true);
            }
        }

    }

    @Override
    public void onBindViewHolder(@NonNull EnterAvailability enterAvailability, int i) {
        enterAvailability.day.setText(weekDays[i]);
        DownloadAvailabilityFromDatabase(enterAvailability, i);
    }

    @Override
    public int getItemCount() {
        return weekDays.length;
    }


    //
    //
    public class EnterAvailability extends RecyclerView.ViewHolder{
        TextView day;
        CheckBox amcheckbox, pmcheckbox, ndcheckbox, unAvaickbox;
        public EnterAvailability(@NonNull View itemView) {
            super(itemView);
            Init(itemView);
        }
        private  void Init(View itemView){
            day = itemView.findViewById(R.id.view_day);
            amcheckbox = itemView.findViewById(R.id.view_am);
            pmcheckbox = itemView.findViewById(R.id.view_pm);
            ndcheckbox= itemView.findViewById(R.id.view_nd);
            unAvaickbox=itemView.findViewById(R.id.view_un);

        }
        private void findingWhichCheckBoxHasBeenChecked(){

        }
    }

}
