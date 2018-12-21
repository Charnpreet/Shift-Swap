package com.example.charnpreet.shiftswap;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class chat_fragment_for_individual_adapter extends RecyclerView.Adapter<chat_fragment_for_individual_adapter.chat_fragment_for_individual_adapter_view_holder> {

    @NonNull
    @Override
    public chat_fragment_for_individual_adapter_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.individual_chat_fragment_adapter_layout,viewGroup, false);
        return new chat_fragment_for_individual_adapter_view_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chat_fragment_for_individual_adapter_view_holder chat_fragment_for_individual_adapter_view_holder, int i) {

    }

    @Override
    public int getItemCount() {

        return 150;
    }

    public class chat_fragment_for_individual_adapter_view_holder extends RecyclerView.ViewHolder {

        public chat_fragment_for_individual_adapter_view_holder(@NonNull View itemView) {
            super(itemView);
        }

        private void Init(){
            //((MainActivity)(getActivity())).setSupportActionBar(toolbar);

        }
    }
}
